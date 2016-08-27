/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.filters.*;
import com.intellij.psi.impl.PsiClassImplUtil;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.impl.source.resolve.JavaResolveCache;
import com.intellij.psi.impl.source.resolve.JavaResolveUtil;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.impl.source.resolve.VariableResolverProcessor;
import com.intellij.psi.impl.source.tree.JavaSourceUtil;
import com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl;
import com.intellij.psi.scope.ElementClassFilter;
import com.intellij.psi.scope.MethodProcessorSetupFailedException;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.scope.processor.FilterScopeProcessor;
import com.intellij.psi.scope.processor.MethodResolverProcessor;
import com.intellij.psi.scope.util.PsiScopesUtil;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiUtil;
import com.intellij.psi.util.TypeConversionUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Function;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.NullableFunction;
import gnu.trove.THashSet;
import org.argus.cit.intellij.jawa.lang.psi.api.expr.JawaReferenceExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaReferenceExpressionPsiElement extends JawaExpressionPsiElement implements JawaReferenceExpression {

    private static final Logger LOG = Logger.getInstance("#org.argus.cit.intellij.jawa.lang.psi.JawaReferenceExpressionPsiElement");

    public JawaReferenceExpressionPsiElement(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiExpression getQualifierExpression() {
        return null;
    }

    @Override
    public PsiElement bindToElementViaStaticImport(@NotNull PsiClass psiClass) throws IncorrectOperationException {
        return this;
    }

    @Override
    public void setQualifierExpression(@Nullable PsiExpression newQualifier) throws IncorrectOperationException {
        final PsiExpression oldQualifier = getQualifierExpression();
        if (newQualifier == null) {
            if (oldQualifier != null) {
                deleteChildInternal(oldQualifier.getNode());
            }
        }
        else {
            if (oldQualifier != null) {
                oldQualifier.replace(newQualifier);
            }
        }
    }

    private static final Function<JawaReferenceExpressionPsiElement, PsiType> TYPE_EVALUATOR = new TypeEvaluator();

    private static class TypeEvaluator implements NullableFunction<JawaReferenceExpressionPsiElement, PsiType> {
        @Override
        public PsiType fun(final JawaReferenceExpressionPsiElement expr) {
            PsiFile file = expr.getContainingFile();
            Project project = file.getProject();
            ResolveResult[] results = ResolveCache.getInstance(project).resolveWithCaching(expr, OurGenericsResolver.INSTANCE, true, false, file);
            JavaResolveResult result = results.length == 1 ? (JavaResolveResult)results[0] : null;

            PsiElement resolve = result == null ? null : result.getElement();
            if (resolve == null) {
                return null;
            }

            PsiTypeParameterListOwner owner = null;
            PsiType ret = null;
            if (resolve instanceof PsiVariable) {
                PsiType type = ((PsiVariable)resolve).getType();
                ret = type instanceof PsiEllipsisType ? ((PsiEllipsisType)type).toArrayType() : type;
                if (ret != null && !ret.isValid()) {
                    LOG.error("invalid type of " + resolve + " of class " + resolve.getClass() + ", valid=" + resolve.isValid());
                }
                if (resolve instanceof PsiField && !((PsiField)resolve).hasModifierProperty(PsiModifier.STATIC)) {
                    owner = ((PsiField)resolve).getContainingClass();
                }
            }
            else if (resolve instanceof PsiMethod) {
                PsiMethod method = (PsiMethod)resolve;
                ret = method.getReturnType();
                if (ret != null) {
                    PsiUtil.ensureValidType(ret);
                }
                owner = method;
            }
            if (ret == null) return null;

            final LanguageLevel languageLevel = PsiUtil.getLanguageLevel(file);
            if (ret instanceof PsiClassType) {
                ret = ((PsiClassType)ret).setLanguageLevel(languageLevel);
            }

            if (languageLevel.isAtLeast(LanguageLevel.JDK_1_5)) {
                final PsiSubstitutor substitutor = result.getSubstitutor();
                if (owner == null || !PsiUtil.isRawSubstitutor(owner, substitutor)) {
                    PsiType substitutedType = substitutor.substitute(ret);
                    PsiUtil.ensureValidType(substitutedType);
                    PsiType normalized = PsiImplUtil.normalizeWildcardTypeByPosition(substitutedType, expr);
                    PsiUtil.ensureValidType(normalized);
                    return PsiClassImplUtil.correctType(normalized, expr.getResolveScope());
                }
            }

            return PsiClassImplUtil.correctType(TypeConversionUtil.erasure(ret), expr.getResolveScope());
        }
    }

    @Nullable
    @Override
    public PsiType getType() {
        return JavaResolveCache.getInstance(getProject()).getType(this, TYPE_EVALUATOR);
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
        return getCanonicalText();
    }

    @Override
    public void processVariants(@NotNull PsiScopeProcessor processor) {
        final OrFilter filter = new OrFilter();
        filter.addFilter(ElementClassFilter.CLASS);
        if(this.isQualified()) {
            filter.addFilter(ElementClassFilter.PACKAGE_FILTER);
        }

        filter.addFilter(new AndFilter(ElementClassFilter.METHOD, new NotFilter(new ConstructorFilter()), new ElementFilter() {
            public boolean isAcceptable(Object element, @Nullable PsiElement context) {
                return LambdaUtil.isValidQualifier4InterfaceStaticMethodCall((PsiMethod)element, JawaReferenceExpressionPsiElement.this, null, PsiUtil.getLanguageLevel(JawaReferenceExpressionPsiElement.this));
            }

            public boolean isClassAcceptable(Class hintClass) {
                return true;
            }
        }));
        filter.addFilter(ElementClassFilter.VARIABLE);
        FilterScopeProcessor filterProcessor = new FilterScopeProcessor(filter, processor) {
            private final Set<String> myVarNames = new THashSet<>();

            public boolean execute(@NotNull PsiElement element, @NotNull ResolveState state) {
                if(!(element instanceof PsiLocalVariable) && !(element instanceof PsiParameter)) {
                    if(element instanceof PsiField && this.myVarNames.contains(((PsiVariable)element).getName())) {
                        return true;
                    }

                    if(element instanceof PsiClass && PsiReferenceExpressionImpl.seemsScrambled((PsiClass)element)) {
                        return true;
                    }
                } else {
                    this.myVarNames.add(((PsiVariable)element).getName());
                }

                return super.execute(element, state);
            }
        };
        PsiScopesUtil.resolveAndWalk(filterProcessor, this, null, true);
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
        return PsiImplUtil.multiResolveImpl(this, incompleteCode, OurGenericsResolver.INSTANCE);
    }

    @Nullable
    @Override
    public PsiElement getQualifier() {
        return this.getQualifierExpression();
    }

    @Nullable
    @Override
    public String getReferenceName() {
        PsiElement element = this.getReferenceNameElement();
        return element != null?element.getText():null;
    }

    @Override
    public PsiElement getElement() {
        return this;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return this.advancedResolve(false).getElement();
    }

    private volatile String myCachedNormalizedText;

    @NotNull
    @Override
    public String getCanonicalText() {
        PsiElement element = this.resolve();
        if(element instanceof PsiClass) {
            String fqn = ((PsiClass)element).getQualifiedName();
            if(fqn != null) {
                return fqn;
            }
        }

        return this.getCachedNormalizedText();
    }

    private String getCachedNormalizedText() {
        String whiteSpaceAndComments = myCachedNormalizedText;
        if (whiteSpaceAndComments == null) {
            myCachedNormalizedText = whiteSpaceAndComments = JavaSourceUtil.getReferenceText(this);
        }
        return whiteSpaceAndComments;
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        if (getQualifierExpression() != null) {
            return renameDirectly(newElementName);
        }
        final JavaResolveResult resolveResult = advancedResolve(false);
        if (resolveResult.getElement() == null) {
            return renameDirectly(newElementName);
        }
        final PsiElementFactory factory = JavaPsiFacade.getInstance(getProject()).getElementFactory();
        final PsiReferenceExpression expression = (PsiReferenceExpression)factory.createExpressionFromText("X." + newElementName, this);
        final PsiReferenceExpression result = (PsiReferenceExpression)replace(expression);
        assert result.getQualifierExpression() != null;
        return result;
    }

    private PsiElement renameDirectly(String newElementName) throws IncorrectOperationException {
        PsiElement oldIdentifier = this;
        final String oldRefName = oldIdentifier.getText();
        if (PsiKeyword.THIS.equals(oldRefName) || PsiKeyword.SUPER.equals(oldRefName) || Comparing.strEqual(oldRefName, newElementName)) return this;
        PsiIdentifier identifier = JavaPsiFacade.getInstance(getProject()).getElementFactory().createIdentifier(newElementName);
        oldIdentifier.replace(identifier);
        return this;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        return null;
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        boolean resolvingToMethod = element instanceof PsiMethod;
        PsiElement parent = getParent();
        boolean parentIsMethodCall = parent instanceof PsiMethodCallExpression;
        // optimization: methodCallExpression should resolve to a method
        return parentIsMethodCall == resolvingToMethod && element.getManager().areElementsEquivalent(element, advancedResolve(true).getElement());

    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Override
    public boolean isSoft() {
        return false;
    }

    @Override
    public PsiReference getReference() {
        return this;
    }

    @NotNull
    private JavaResolveResult[] resolve(IElementType parentType, @NotNull PsiFile containingFile) {
        if (parentType == JawaElementTypes.CALL_STATEMENT) {
            return resolveToMethod(containingFile);
        }

        return resolveToVariable(containingFile);
    }

    @NotNull
    private JavaResolveResult[] resolveToMethod(@NotNull PsiFile containingFile) {
        final PsiMethodCallExpression methodCall = (PsiMethodCallExpression)getParent();
        final MethodResolverProcessor processor = new MethodResolverProcessor(methodCall, containingFile);
        try {
            PsiScopesUtil.setupAndRunProcessor(processor, methodCall, false);
        }
        catch (MethodProcessorSetupFailedException e) {
            return JavaResolveResult.EMPTY_ARRAY;
        }
        return processor.getResult();
    }

    @NotNull
    private JavaResolveResult[] resolveToVariable(@NotNull PsiFile containingFile) {
        final VariableResolverProcessor processor = new VariableResolverProcessor(this, containingFile);
        PsiScopesUtil.resolveAndWalk(processor, this, null);
        return processor.getResult();
    }

    private static final class OurGenericsResolver implements ResolveCache.PolyVariantContextResolver<PsiJavaReference> {
        static final OurGenericsResolver INSTANCE = new OurGenericsResolver();

        @NotNull
        @Override
        public ResolveResult[] resolve(@NotNull PsiJavaReference ref, @NotNull PsiFile containingFile, boolean incompleteCode) {
            JawaReferenceExpressionPsiElement expression = (JawaReferenceExpressionPsiElement)ref;
            PsiElement parent = expression.getParent();
            IElementType parentType = parent == null ? null : parent.getNode().getElementType();
            JavaResolveResult[] result = expression.resolve(parentType, containingFile);
            JavaResolveUtil.substituteResults(expression, result);
            return result;
        }
    }
}
