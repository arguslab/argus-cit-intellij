// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaPsiReferenceList;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaLocalVariable;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaCodeBlock;
import org.argus.cit.intellij.jawa.lang.psi.api.expr.JawaReferenceExpression;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaTypeElement;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaModifierList;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaParameterList;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaPsiField;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaPsiMethod;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaParameter;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaReference;
import org.argus.cit.intellij.jawa.lang.psi.api.expr.JawaExpressionList;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaJwStatement;
import org.argus.cit.intellij.jawa.lang.psi.api.expr.JawaMethodCallExpression;

public class JawaVisitor extends PsiElementVisitor {

  public void visitAccessExpression(@NotNull JawaAccessExpression o) {
    visitPsiElement(o);
  }

  public void visitAccessFlagAnnotation(@NotNull JawaAccessFlagAnnotation o) {
    visitModifierList(o);
  }

  public void visitAnnotation(@NotNull JawaAnnotation o) {
    visitPsiElement(o);
  }

  public void visitArgClause(@NotNull JawaArgClause o) {
    visitExpressionList(o);
  }

  public void visitAssignmentStatement(@NotNull JawaAssignmentStatement o) {
    visitPsiElement(o);
  }

  public void visitBinaryExpression(@NotNull JawaBinaryExpression o) {
    visitPsiElement(o);
  }

  public void visitCmp(@NotNull JawaCmp o) {
    visitPsiElement(o);
  }

  public void visitCallLhs(@NotNull JawaCallLhs o) {
    visitPsiElement(o);
  }

  public void visitCallStatement(@NotNull JawaCallStatement o) {
    visitMethodCallExpression(o);
  }

  public void visitCastExpression(@NotNull JawaCastExpression o) {
    visitPsiElement(o);
  }

  public void visitCatchClause(@NotNull JawaCatchClause o) {
    visitPsiElement(o);
  }

  public void visitCatchRange(@NotNull JawaCatchRange o) {
    visitPsiElement(o);
  }

  public void visitClassOrInterfaceDeclaration(@NotNull JawaClassOrInterfaceDeclaration o) {
    visitTypeDefinition(o);
  }

  public void visitCmpExpression(@NotNull JawaCmpExpression o) {
    visitPsiElement(o);
  }

  public void visitCondBinaryExpression(@NotNull JawaCondBinaryExpression o) {
    visitPsiElement(o);
  }

  public void visitConstClassExpression(@NotNull JawaConstClassExpression o) {
    visitPsiElement(o);
  }

  public void visitDefaultAnnotation(@NotNull JawaDefaultAnnotation o) {
    visitPsiElement(o);
  }

  public void visitExceptionExpression(@NotNull JawaExceptionExpression o) {
    visitPsiElement(o);
  }

  public void visitExpressionLhs(@NotNull JawaExpressionLhs o) {
    visitPsiElement(o);
  }

  public void visitExpressionRhs(@NotNull JawaExpressionRhs o) {
    visitPsiElement(o);
  }

  public void visitExtendAndImplement(@NotNull JawaExtendAndImplement o) {
    visitPsiElement(o);
  }

  public void visitExtendsAndImplementsClause(@NotNull JawaExtendsAndImplementsClause o) {
    visitPsiReferenceList(o);
  }

  public void visitFieldDeclaration(@NotNull JawaFieldDeclaration o) {
    visitPsiField(o);
  }

  public void visitFieldDefSymbol(@NotNull JawaFieldDefSymbol o) {
    visitPsiElement(o);
  }

  public void visitFieldNameSymbol(@NotNull JawaFieldNameSymbol o) {
    visitReferenceExpression(o);
  }

  public void visitGotoStatement(@NotNull JawaGotoStatement o) {
    visitPsiElement(o);
  }

  public void visitIfStatement(@NotNull JawaIfStatement o) {
    visitPsiElement(o);
  }

  public void visitIndexingExpression(@NotNull JawaIndexingExpression o) {
    visitPsiElement(o);
  }

  public void visitInstanceFieldDeclaration(@NotNull JawaInstanceFieldDeclaration o) {
    visitPsiElement(o);
  }

  public void visitInstanceFieldDeclarationBlock(@NotNull JawaInstanceFieldDeclarationBlock o) {
    visitPsiElement(o);
  }

  public void visitInstanceofExpression(@NotNull JawaInstanceofExpression o) {
    visitPsiElement(o);
  }

  public void visitJwBody(@NotNull JawaJwBody o) {
    visitCodeBlock(o);
  }

  public void visitJwType(@NotNull JawaJwType o) {
    visitTypeElement(o);
  }

  public void visitKindAnnotation(@NotNull JawaKindAnnotation o) {
    visitPsiElement(o);
  }

  public void visitLengthExpression(@NotNull JawaLengthExpression o) {
    visitPsiElement(o);
  }

  public void visitLiteralExpression(@NotNull JawaLiteralExpression o) {
    visitPsiElement(o);
  }

  public void visitLocalVarDeclaration(@NotNull JawaLocalVarDeclaration o) {
    visitLocalVariable(o);
  }

  public void visitLocation(@NotNull JawaLocation o) {
    visitJwStatement(o);
  }

  public void visitLocationDefSymbol(@NotNull JawaLocationDefSymbol o) {
    visitLocalVariable(o);
  }

  public void visitLocationSymbol(@NotNull JawaLocationSymbol o) {
    visitReferenceExpression(o);
  }

  public void visitMethodDeclaration(@NotNull JawaMethodDeclaration o) {
    visitPsiMethod(o);
  }

  public void visitMethodDefSymbol(@NotNull JawaMethodDefSymbol o) {
    visitPsiElement(o);
  }

  public void visitMethodNameSymbol(@NotNull JawaMethodNameSymbol o) {
    visitReferenceExpression(o);
  }

  public void visitMonitorStatement(@NotNull JawaMonitorStatement o) {
    visitPsiElement(o);
  }

  public void visitNumberLiteral(@NotNull JawaNumberLiteral o) {
    visitPsiElement(o);
  }

  public void visitNameExpression(@NotNull JawaNameExpression o) {
    visitPsiElement(o);
  }

  public void visitNewExpression(@NotNull JawaNewExpression o) {
    visitPsiElement(o);
  }

  public void visitNullExpression(@NotNull JawaNullExpression o) {
    visitPsiElement(o);
  }

  public void visitParam(@NotNull JawaParam o) {
    visitParameter(o);
  }

  public void visitParamClause(@NotNull JawaParamClause o) {
    visitParameterList(o);
  }

  public void visitReturnStatement(@NotNull JawaReturnStatement o) {
    visitPsiElement(o);
  }

  public void visitSignatureAnnotation(@NotNull JawaSignatureAnnotation o) {
    visitPsiElement(o);
  }

  public void visitSignatureSymbol(@NotNull JawaSignatureSymbol o) {
    visitPsiElement(o);
  }

  public void visitStatement(@NotNull JawaStatement o) {
    visitPsiElement(o);
  }

  public void visitStaticFieldDeclaration(@NotNull JawaStaticFieldDeclaration o) {
    visitPsiElement(o);
  }

  public void visitStaticFieldDefSymbol(@NotNull JawaStaticFieldDefSymbol o) {
    visitPsiElement(o);
  }

  public void visitStaticFieldNameSymbol(@NotNull JawaStaticFieldNameSymbol o) {
    visitReferenceExpression(o);
  }

  public void visitSwitchCase(@NotNull JawaSwitchCase o) {
    visitPsiElement(o);
  }

  public void visitSwitchDefaultCase(@NotNull JawaSwitchDefaultCase o) {
    visitPsiElement(o);
  }

  public void visitSwitchStatement(@NotNull JawaSwitchStatement o) {
    visitPsiElement(o);
  }

  public void visitThrowStatement(@NotNull JawaThrowStatement o) {
    visitPsiElement(o);
  }

  public void visitTupleExpression(@NotNull JawaTupleExpression o) {
    visitPsiElement(o);
  }

  public void visitTypeAnnotation(@NotNull JawaTypeAnnotation o) {
    visitPsiElement(o);
  }

  public void visitTypeDefSymbol(@NotNull JawaTypeDefSymbol o) {
    visitPsiElement(o);
  }

  public void visitTypeExpression(@NotNull JawaTypeExpression o) {
    visitPsiElement(o);
  }

  public void visitTypeFragment(@NotNull JawaTypeFragment o) {
    visitPsiElement(o);
  }

  public void visitTypeFragmentWithInit(@NotNull JawaTypeFragmentWithInit o) {
    visitPsiElement(o);
  }

  public void visitTypeSymbol(@NotNull JawaTypeSymbol o) {
    visitReference(o);
  }

  public void visitUnaryExpression(@NotNull JawaUnaryExpression o) {
    visitPsiElement(o);
  }

  public void visitVarDefSymbol(@NotNull JawaVarDefSymbol o) {
    visitPsiElement(o);
  }

  public void visitVarSymbol(@NotNull JawaVarSymbol o) {
    visitReferenceExpression(o);
  }

  public void visitCodeBlock(@NotNull JawaCodeBlock o) {
    visitPsiElement(o);
  }

  public void visitExpressionList(@NotNull JawaExpressionList o) {
    visitPsiElement(o);
  }

  public void visitJwStatement(@NotNull JawaJwStatement o) {
    visitPsiElement(o);
  }

  public void visitLocalVariable(@NotNull JawaLocalVariable o) {
    visitPsiElement(o);
  }

  public void visitMethodCallExpression(@NotNull JawaMethodCallExpression o) {
    visitPsiElement(o);
  }

  public void visitModifierList(@NotNull JawaModifierList o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull JawaParameter o) {
    visitPsiElement(o);
  }

  public void visitParameterList(@NotNull JawaParameterList o) {
    visitPsiElement(o);
  }

  public void visitPsiField(@NotNull JawaPsiField o) {
    visitPsiElement(o);
  }

  public void visitPsiMethod(@NotNull JawaPsiMethod o) {
    visitPsiElement(o);
  }

  public void visitPsiReferenceList(@NotNull JawaPsiReferenceList o) {
    visitPsiElement(o);
  }

  public void visitReference(@NotNull JawaReference o) {
    visitPsiElement(o);
  }

  public void visitReferenceExpression(@NotNull JawaReferenceExpression o) {
    visitPsiElement(o);
  }

  public void visitTypeDefinition(@NotNull JawaTypeDefinition o) {
    visitPsiElement(o);
  }

  public void visitTypeElement(@NotNull JawaTypeElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
