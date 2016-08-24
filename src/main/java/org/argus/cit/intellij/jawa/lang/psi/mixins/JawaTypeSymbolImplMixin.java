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
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleSettingsFacade;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.OrFilter;
import com.intellij.psi.impl.CheckUtil;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.impl.source.PsiJavaCodeReferenceElementImpl;
import com.intellij.psi.impl.source.resolve.ClassResolverProcessor;
import com.intellij.psi.impl.source.resolve.JavaResolveUtil;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.impl.source.resolve.VariableResolverProcessor;
import com.intellij.psi.impl.source.tree.JavaSourceUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.scope.ElementClassFilter;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.scope.processor.FilterScopeProcessor;
import com.intellij.psi.scope.util.PsiScopesUtil;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
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
    private static final Logger LOG = Logger.getInstance("#org.argus.cit.intellij.jawa.lang.psi.mixins.JawaTypeSymbolImplMixin");

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
        return PsiImplUtil.multiResolveImpl(this, incompleteCode, JawaTypeSymbolImplMixin.OurGenericsResolver.INSTANCE);
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
        TreeElement nameChild = (TreeElement)getApostropheId();
        int startOffset = nameChild.getStartOffsetInParent();
        return new TextRange(startOffset, startOffset + nameChild.getTextLength());
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
        String refText = JavaSourceUtil.getReferenceText(this);
        return refText.replaceAll("`", "");
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
    public PsiReference getReference() {
        return this;
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

    private PsiSubstitutor updateSubstitutor(PsiSubstitutor subst, PsiClass psiClass) {
        PsiType[] parameters = this.getTypeParameters();
        if(psiClass != null) {
            subst = subst.putAll(psiClass, parameters);
        }

        return subst;
    }

    @NotNull
    private JavaResolveResult[] resolve(final int kind, @NotNull PsiFile containingFile) {
        switch (kind) {
            case PsiJavaCodeReferenceElementImpl.CLASS_FQ_NAME_KIND: {
                String text = getNormalizedText();
                if (StringUtil.isEmptyOrSpaces(text)) return JavaResolveResult.EMPTY_ARRAY;

                PsiClass aClass = JavaPsiFacade.getInstance(containingFile.getProject()).findClass(text, getResolveScope());
                if (aClass == null) return JavaResolveResult.EMPTY_ARRAY;

                if (!isQualified() && text.equals(aClass.getQualifiedName())) {
                    if (containingFile instanceof PsiJavaFile && !((PsiJavaFile)containingFile).getPackageName().isEmpty()) {
                        // classes in default (unnamed) package cannot be referenced from other packages
                        return JavaResolveResult.EMPTY_ARRAY;
                    }
                }

                return new JavaResolveResult[]{new CandidateInfo(aClass, updateSubstitutor(PsiSubstitutor.EMPTY, aClass), this, false)};
            }
            case PsiJavaCodeReferenceElementImpl.CLASS_IN_QUALIFIED_NEW_KIND: {
                PsiElement parent = getParent();
                final PsiExpression qualifier;
                if (parent instanceof PsiNewExpression) {
                    qualifier = ((PsiNewExpression)parent).getQualifier();
                    LOG.assertTrue(qualifier != null);
                }
                else if (parent instanceof PsiJavaCodeReferenceElement) {
                    return JavaResolveResult.EMPTY_ARRAY;
                }
                else {
                    LOG.error("Invalid java reference!");
                    return JavaResolveResult.EMPTY_ARRAY;
                }

                final PsiType qualifierType = qualifier.getType();
                if (qualifierType == null) return JavaResolveResult.EMPTY_ARRAY;
                if (!(qualifierType instanceof PsiClassType)) return JavaResolveResult.EMPTY_ARRAY;
                final JavaResolveResult result = PsiUtil.resolveGenericsClassInType(qualifierType);
                final PsiElement resultElement = result.getElement();
                if (resultElement == null) return JavaResolveResult.EMPTY_ARRAY;
                final PsiElement classNameElement = getReferenceNameElement();
                if (!(classNameElement instanceof PsiIdentifier)) return JavaResolveResult.EMPTY_ARRAY;
                final String className = classNameElement.getText();

                final ClassResolverProcessor processor = new ClassResolverProcessor(className, this, containingFile);
                resultElement.processDeclarations(processor, ResolveState.initial().put(PsiSubstitutor.KEY, result.getSubstitutor()), this, this);
                return processor.getResult();
            }
            case PsiJavaCodeReferenceElementImpl.CLASS_NAME_KIND: {
                final PsiElement classNameElement = getReferenceNameElement();
                if (!(classNameElement instanceof PsiIdentifier)) return JavaResolveResult.EMPTY_ARRAY;
                final String className = classNameElement.getText();
                final ClassResolverProcessor processor = new ClassResolverProcessor(className, this, containingFile);
                PsiScopesUtil.resolveAndWalk(processor, this, null);
                return processor.getResult();
            }
            case PsiJavaCodeReferenceElementImpl.PACKAGE_NAME_KIND: {
                String packageName = getNormalizedText();
                Project project = getManager().getProject();
                PsiPackage aPackage = JavaPsiFacade.getInstance(project).findPackage(packageName);
                if (aPackage == null || !aPackage.isValid()) {
                    return JavaPsiFacade.getInstance(project).isPartOfPackagePrefix(packageName) ?
                            CandidateInfo.RESOLVE_RESULT_FOR_PACKAGE_PREFIX_PACKAGE : JavaResolveResult.EMPTY_ARRAY;
                }
                return new JavaResolveResult[]{new CandidateInfo(aPackage, PsiSubstitutor.EMPTY)};
            }
            case PsiJavaCodeReferenceElementImpl.CLASS_FQ_OR_PACKAGE_NAME_KIND:
            case PsiJavaCodeReferenceElementImpl.CLASS_OR_PACKAGE_NAME_KIND: {
                int classKind = kind == PsiJavaCodeReferenceElementImpl.CLASS_OR_PACKAGE_NAME_KIND ? PsiJavaCodeReferenceElementImpl.CLASS_NAME_KIND : PsiJavaCodeReferenceElementImpl.CLASS_FQ_NAME_KIND;

                //A single-type-import declaration d in a compilation unit c of package p that imports a type named n shadows, throughout c, the declarations of:
                //any top level type named n declared in another compilation unit of p
                if (PsiTreeUtil.getParentOfType(this, PsiImportStatementBase.class) != null) {
                    JavaResolveResult[] result = resolve(PsiJavaCodeReferenceElementImpl.PACKAGE_NAME_KIND, containingFile);
                    return result.length == 0 ? resolve(classKind, containingFile) : result;
                }

                JavaResolveResult[] result = resolve(classKind, containingFile);

                if (result.length == 1 && !result[0].isAccessible()) {
                    JavaResolveResult[] packageResult = resolve(PsiJavaCodeReferenceElementImpl.PACKAGE_NAME_KIND, containingFile);
                    if (packageResult.length != 0) {
                        result = packageResult;
                    }
                }
                else if (result.length == 0) {
                    result = resolve(PsiJavaCodeReferenceElementImpl.PACKAGE_NAME_KIND, containingFile);
                }

                return result;
            }
        }

        LOG.error(this);
        return JavaResolveResult.EMPTY_ARRAY;
    }

    private static final class OurGenericsResolver implements ResolveCache.PolyVariantContextResolver<PsiJavaReference> {
        private static final JawaTypeSymbolImplMixin.OurGenericsResolver INSTANCE = new JawaTypeSymbolImplMixin.OurGenericsResolver();

        private OurGenericsResolver() {
        }

        @NotNull
        public ResolveResult[] resolve(@NotNull PsiJavaReference ref, @NotNull PsiFile containingFile, boolean incompleteCode) {
            JawaTypeSymbolImplMixin referenceElement = (JawaTypeSymbolImplMixin)ref;
            int kind = PsiJavaCodeReferenceElementImpl.CLASS_FQ_NAME_KIND;
            JavaResolveResult[] result = referenceElement.resolve(kind, containingFile);
            if(incompleteCode && result.length == 0 && kind != 4 && kind != 5) {
                VariableResolverProcessor processor = new VariableResolverProcessor(referenceElement, containingFile);
                PsiScopesUtil.resolveAndWalk(processor, referenceElement, (PsiElement)null, true);
                result = processor.getResult();
                if(result.length == 0 && kind == 1) {
                    result = referenceElement.resolve(2, containingFile);
                }
            }

            JavaResolveUtil.substituteResults(referenceElement, result);
            return result;
        }
    }
}
