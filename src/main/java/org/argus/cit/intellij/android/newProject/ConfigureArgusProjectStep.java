/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.android.newProject;

import com.android.tools.idea.npw.WizardUtils;
import com.android.tools.idea.wizard.WizardConstants;
import com.android.tools.idea.wizard.dynamic.DynamicWizardStepWithHeaderAndDescription;
import com.android.tools.idea.wizard.dynamic.ScopedStateStore;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.Set;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public class ConfigureArgusProjectStep extends DynamicWizardStepWithHeaderAndDescription {
    private TextFieldWithBrowseButton myProjectLocation;
    private TextFieldWithBrowseButton myAppPath;
    private JPanel myPanel;
    private JLabel myPackageName;

    public ConfigureArgusProjectStep(@NotNull Disposable disposable) {
        this("Configure your new project", disposable);
    }


    public ConfigureArgusProjectStep(String title, Disposable parentDisposable) {
        super(title, null, parentDisposable);
        setBodyComponent(myPanel);
    }

    @Override
    public void init() {
        register(WizardConstants.APPLICATION_NAME_KEY, myAppPath);
        register(WizardConstants.PACKAGE_NAME_KEY, myPackageName);
        register(WizardConstants.PROJECT_LOCATION_KEY, myProjectLocation);
        registerValueDeriver(WizardConstants.PROJECT_LOCATION_KEY, myProjectLocationDeriver);

        FileSaverDescriptor apkFileSaverDescriptor = new FileSaverDescriptor("Apk Path", "Please choose an APK");
        myAppPath.addActionListener(e -> browseForFile(myAppPath, apkFileSaverDescriptor));
        FileSaverDescriptor projectlocationFileSaverDescriptor = new FileSaverDescriptor("Project Location", "Please choose a location for your project");
        myProjectLocation.addActionListener(e -> browseForFile(myProjectLocation, projectlocationFileSaverDescriptor));
    }

    @Override
    public boolean validate() {
        if (!myPath.validate()) return false;
        WizardUtils.ValidationResult locationValidationResult = WizardUtils.validateLocation(myState.get(WizardConstants.PROJECT_LOCATION_KEY));
        setErrorHtml(locationValidationResult.isOk() ? "" : locationValidationResult.getFormattedMessage());
        return !locationValidationResult.isError();
    }

    private void browseForFile(TextFieldWithBrowseButton button, FileSaverDescriptor saver) {
        File currentPath = new File(button.getText());
        File parentPath = currentPath.getParentFile();
        if (parentPath == null) {
            String homePath = System.getProperty("user.home");
            parentPath = homePath == null ? new File("/") : new File(homePath);
        }
        VirtualFile parent = LocalFileSystem.getInstance().findFileByIoFile(parentPath);
        String filename = currentPath.getName();
        VirtualFileWrapper fileWrapper =
                FileChooserFactory.getInstance().createSaveFileDialog(saver, (Project)null).save(parent, filename);
        if (fileWrapper != null) {
            button.setText(fileWrapper.getFile().getAbsolutePath());
        }
    }

    private static final ValueDeriver<String> myProjectLocationDeriver = new ValueDeriver<String>() {
        @Nullable
        @Override
        public Set<ScopedStateStore.Key<?>> getTriggerKeys() {
            return makeSetOf(WizardConstants.APPLICATION_NAME_KEY);
        }

        @Nullable
        @Override
        public String deriveValue(@NotNull ScopedStateStore state, @Nullable ScopedStateStore.Key changedKey, @Nullable String currentValue) {
            String name = state.get(WizardConstants.APPLICATION_NAME_KEY);
            name = name == null ? "" : name;
            name = name.replaceAll(WizardConstants.INVALID_FILENAME_CHARS, "");
            name = name.replaceAll("\\s", "");
            File baseDirectory = WizardUtils.getProjectLocationParent();
            File projectDir = new File(baseDirectory, name);
            int i = 2;
            while (projectDir.exists()) {
                projectDir = new File(baseDirectory, name + i);
                i++;
            }
            return projectDir.getPath();
        }
    };

    @NotNull
    @Override
    public String getStepName() {
        return "Create Argus-CIT Project";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return myAppPath;
    }

    @NotNull
    @Override
    protected WizardStepHeaderSettings getStepHeader() {
        return ConfigureArgusProjectPath$.MODULE$.buildConfigurationHeader();
    }
}
