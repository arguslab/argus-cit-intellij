/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.mixins;


import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleSettingsFacade;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.OrFilter;
import com.intellij.psi.impl.CheckUtil;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.impl.source.tree.JavaSourceUtil;
import com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl;
import com.intellij.psi.scope.ElementClassFilter;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.scope.processor.FilterScopeProcessor;
import com.intellij.psi.scope.util.PsiScopesUtil;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.JawaTypeSymbol;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaTypeSymbolStub;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaTypeSymbolImplMixin
        extends JawaStubBasedPsiElementBase<JawaTypeSymbolStub>
        implements JawaTypeSymbol {

    public JawaTypeSymbolImplMixin(@NotNull JawaTypeSymbolStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaTypeSymbolImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getReferenceNameElement() {
        return getApostropheId();
    }

    @Nullable
    @Override
    public PsiReferenceParameterList getParameterList() {
        return null;
    }

    @NotNull
    @Override
    public PsiType[] getTypeParameters() {
        return new PsiType[0];
    }

    @Override
    public boolean isQualified() {
        return getQualifier() != null;
    }

    @Override
    public String getQualifiedName() {
        return getJawaType().jawaName();
    }

    @Override
    public void processVariants(@NotNull PsiScopeProcessor processor) {
        final OrFilter filter = new OrFilter();
        filter.addFilter(ElementClassFilter.PACKAGE_FILTER);
        if (isQualified()) {
            filter.addFilter(ElementClassFilter.CLASS);
        }
        final FilterScopeProcessor proc = new FilterScopeProcessor(filter, processor);
        PsiScopesUtil.resolveAndWalk(proc, this, null, true);
    }

    @NotNull
    @Override
    public JavaResolveResult advancedResolve(boolean incompleteCode) {
        final JavaResolveResult[] results = multiResolve(incompleteCode);
        return results.length == 1 ? results[0] : JavaResolveResult.EMPTY;
    }

    @NotNull
    @Override
    public JavaResolveResult[] multiResolve(boolean incompleteCode) {
        return PsiImplUtil.multiResolveImpl(this, incompleteCode, PsiReferenceExpressionImpl.OurGenericsResolver.INSTANCE);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        throw new IncorrectOperationException("cannot set name");
    }

    @Nullable
    @Override
    public PsiElement getQualifier() {
        return getApostropheId();
    }

    @Nullable
    @Override
    public String getReferenceName() {
        return getJawaType().jawaName();
    }

    @Override
    public PsiElement getElement() {
        return getApostropheId();
    }

    @Override
    public TextRange getRangeInElement() {
        return getApostropheId().getTextRange();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return advancedResolve(false).getElement();
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return getNormalizedText();
    }

    private String getNormalizedText() {
        return JavaSourceUtil.getReferenceText(this);
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        final PsiElement oldIdentifier = getReferenceNameElement();
        if (oldIdentifier == null) {
            throw new IncorrectOperationException();
        }
        final PsiElement identifier = JavaPsiFacade.getInstance(getProject()).getElementFactory().createIdentifier(newElementName);
        oldIdentifier.replace(identifier);
        return this;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        PsiFile containingFile = getContainingFile();
        CheckUtil.checkWritable(containingFile);
        if (isReferenceTo(element)) return this;
        if (!(element instanceof PsiClass)) {
            throw cannotBindError(element);
        }
        return bindToClass((PsiClass)element, containingFile);
    }

    private static IncorrectOperationException cannotBindError(PsiElement element) {
        return new IncorrectOperationException("Cannot bind to " + element);
    }

    private PsiElement bindToClass(@NotNull PsiClass aClass, @NotNull PsiFile containingFile) throws IncorrectOperationException {
        String qName = aClass.getQualifiedName();
        Project project = containingFile.getProject();
        boolean preserveQualification = JavaCodeStyleSettingsFacade.getInstance(project).useFQClassNames();
        JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
        if (qName == null) {
            qName = aClass.getName();
            assert qName != null : aClass;
            PsiClass psiClass = facade.getResolveHelper().resolveReferencedClass(qName, this);
            if (!getManager().areElementsEquivalent(psiClass, aClass)) {
                throw cannotBindError(aClass);
            }
        }
        else if (facade.findClass(qName, getResolveScope()) == null && !preserveQualification) {
            return this;
        }

//        List<PsiAnnotation> annotations = getAnnotations();
        String text = qName;
        PsiReferenceParameterList parameterList = getParameterList();
        if (parameterList != null) {
            text += parameterList.getText();
        }

        PsiJavaCodeReferenceElement ref;
        try {
            ref = facade.getParserFacade().createReferenceFromText(text, getParent());
        }
        catch (IncorrectOperationException e) {
            throw new IncorrectOperationException(e.getMessage() + " [qname=" + qName + " class=" + aClass + ";" + aClass.getClass().getName() + "]");
        }

//        ((PsiJavaCodeReferenceElementImpl)ref).setAnnotations(annotations);
//        getTreeParent().replaceChildInternal(this, (TreeElement)ref.getNode());

        if (!preserveQualification) {
            JavaCodeStyleManager codeStyleManager = JavaCodeStyleManager.getInstance(project);
            ref = (PsiJavaCodeReferenceElement)codeStyleManager.shortenClassReferences(ref, JavaCodeStyleManager.INCOMPLETE_CODE);
        }

        return ref;
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        if (!(element instanceof PsiClass)) return false;
        final String qName = ((PsiClass)element).getQualifiedName();
        return qName != null && qName.equals(getCanonicalText());
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        final ElementFilter filter;
        filter = new OrFilter();
        ((OrFilter)filter).addFilter(ElementClassFilter.PACKAGE_FILTER);
        if (isQualified()) {
            ((OrFilter)filter).addFilter(ElementClassFilter.CLASS);
        }
        return PsiImplUtil.getReferenceVariantsByFilter(this, filter);
    }

    @Override
    public boolean isSoft() {
        return false;
    }

    @Override
    public String name() {
        return getJawaType().jawaName();
    }

    @Override
    public PsiElement nameId() {
        return getApostropheId();
    }
}
