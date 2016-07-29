/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.incremental.BuilderCategory;
import org.jetbrains.jps.incremental.BuilderService;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;
import org.jetbrains.jps.incremental.resources.ResourcesBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public class JawaBuilderService extends BuilderService {
    static {
        ResourcesBuilder.registerEnabler(module -> true);
    }

    @NotNull
    @Override
    public List<? extends ModuleLevelBuilder> createModuleLevelBuilders() {
        return Arrays.asList(
                new IdeaIncrementalBuilder(BuilderCategory.SOURCE_PROCESSOR),
                new IdeaIncrementalBuilder(BuilderCategory.OVERWRITING_TRANSLATOR)
        );
    }
}