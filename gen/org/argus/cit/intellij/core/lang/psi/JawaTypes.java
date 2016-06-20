// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.argus.cit.intellij.core.lang.psi.impl.*;

public interface JawaTypes {

  IElementType ACCESS_EXPRESSION = new JawaElementType("ACCESS_EXPRESSION");
  IElementType ANNOTATION = new JawaElementType("ANNOTATION");
  IElementType ANNOTATION_KEY = new JawaElementType("ANNOTATION_KEY");
  IElementType ANNOTATION_VALUE = new JawaElementType("ANNOTATION_VALUE");
  IElementType ARG_CLAUSE = new JawaElementType("ARG_CLAUSE");
  IElementType ASSIGNMENT_STATEMENT = new JawaElementType("ASSIGNMENT_STATEMENT");
  IElementType BINARY_EXPRESSION = new JawaElementType("BINARY_EXPRESSION");
  IElementType BODY = new JawaElementType("BODY");
  IElementType CALL_LHS = new JawaElementType("CALL_LHS");
  IElementType CALL_STATEMENT = new JawaElementType("CALL_STATEMENT");
  IElementType CAST_EXPRESSION = new JawaElementType("CAST_EXPRESSION");
  IElementType CATCH_CLAUSE = new JawaElementType("CATCH_CLAUSE");
  IElementType CATCH_RANGE = new JawaElementType("CATCH_RANGE");
  IElementType CLASS_OR_INTERFACE_DECLARATION = new JawaElementType("CLASS_OR_INTERFACE_DECLARATION");
  IElementType CMP = new JawaElementType("CMP");
  IElementType CMP_EXPRESSION = new JawaElementType("CMP_EXPRESSION");
  IElementType CONST_CLASS_EXPRESSION = new JawaElementType("CONST_CLASS_EXPRESSION");
  IElementType EXCEPTION_EXPRESSION = new JawaElementType("EXCEPTION_EXPRESSION");
  IElementType EXPRESSION_LHS = new JawaElementType("EXPRESSION_LHS");
  IElementType EXPRESSION_RHS = new JawaElementType("EXPRESSION_RHS");
  IElementType EXTENDS_AND_IMPLEMENTS_CLAUSES = new JawaElementType("EXTENDS_AND_IMPLEMENTS_CLAUSES");
  IElementType EXTEND_AND_IMPLEMENT = new JawaElementType("EXTEND_AND_IMPLEMENT");
  IElementType FIELD_DEF_SYMBOL = new JawaElementType("FIELD_DEF_SYMBOL");
  IElementType FIELD_NAME_SYMBOL = new JawaElementType("FIELD_NAME_SYMBOL");
  IElementType GOTO_STATEMENT = new JawaElementType("GOTO_STATEMENT");
  IElementType IF_STATEMENT = new JawaElementType("IF_STATEMENT");
  IElementType INDEXING_EXPRESSION = new JawaElementType("INDEXING_EXPRESSION");
  IElementType INSTANCEOF_EXPRESSION = new JawaElementType("INSTANCEOF_EXPRESSION");
  IElementType INSTANCE_FIELD_DECLARATION = new JawaElementType("INSTANCE_FIELD_DECLARATION");
  IElementType INSTANCE_FIELD_DECLARATION_BLOCK = new JawaElementType("INSTANCE_FIELD_DECLARATION_BLOCK");
  IElementType LENGTH_EXPRESSION = new JawaElementType("LENGTH_EXPRESSION");
  IElementType LITERAL_EXPRESSION = new JawaElementType("LITERAL_EXPRESSION");
  IElementType LOCAL_VAR_DECLARATION = new JawaElementType("LOCAL_VAR_DECLARATION");
  IElementType LOCATION = new JawaElementType("LOCATION");
  IElementType LOCATION_DEF_SYMBOL = new JawaElementType("LOCATION_DEF_SYMBOL");
  IElementType LOCATION_ID = new JawaElementType("LOCATION_ID");
  IElementType LOCATION_SYMBOL = new JawaElementType("LOCATION_SYMBOL");
  IElementType METHOD_DECLARATION = new JawaElementType("METHOD_DECLARATION");
  IElementType METHOD_DEF_SYMBOL = new JawaElementType("METHOD_DEF_SYMBOL");
  IElementType METHOD_NAME_SYMBOL = new JawaElementType("METHOD_NAME_SYMBOL");
  IElementType MONITOR_STATEMENT = new JawaElementType("MONITOR_STATEMENT");
  IElementType NAME_EXPRESSION = new JawaElementType("NAME_EXPRESSION");
  IElementType NEW_EXPRESSION = new JawaElementType("NEW_EXPRESSION");
  IElementType NULL_EXPRESSION = new JawaElementType("NULL_EXPRESSION");
  IElementType NUMBER_LITERAL = new JawaElementType("NUMBER_LITERAL");
  IElementType PARAM = new JawaElementType("PARAM");
  IElementType PARAM_CLAUSE = new JawaElementType("PARAM_CLAUSE");
  IElementType RETURN_STATEMENT = new JawaElementType("RETURN_STATEMENT");
  IElementType SIGNATURE_ANNOTATION = new JawaElementType("SIGNATURE_ANNOTATION");
  IElementType STATEMENT = new JawaElementType("STATEMENT");
  IElementType STATIC_FIELD_DECLARATION = new JawaElementType("STATIC_FIELD_DECLARATION");
  IElementType STATIC_FIELD_DEF_SYMBOL = new JawaElementType("STATIC_FIELD_DEF_SYMBOL");
  IElementType STATIC_FIELD_NAME_SYMBOL = new JawaElementType("STATIC_FIELD_NAME_SYMBOL");
  IElementType SWITCH_CASE = new JawaElementType("SWITCH_CASE");
  IElementType SWITCH_DEFAULT_CASE = new JawaElementType("SWITCH_DEFAULT_CASE");
  IElementType SWITCH_STATEMENT = new JawaElementType("SWITCH_STATEMENT");
  IElementType THROW_STATEMENT = new JawaElementType("THROW_STATEMENT");
  IElementType TUPLE_EXPRESSION = new JawaElementType("TUPLE_EXPRESSION");
  IElementType TYPE = new JawaElementType("TYPE");
  IElementType TYPE_ANNOTATION = new JawaElementType("TYPE_ANNOTATION");
  IElementType TYPE_DEF_SYMBOL = new JawaElementType("TYPE_DEF_SYMBOL");
  IElementType TYPE_EXPRESSION = new JawaElementType("TYPE_EXPRESSION");
  IElementType TYPE_FRAGMENT = new JawaElementType("TYPE_FRAGMENT");
  IElementType TYPE_FRAGMENT_WITH_INIT = new JawaElementType("TYPE_FRAGMENT_WITH_INIT");
  IElementType TYPE_SYMBOL = new JawaElementType("TYPE_SYMBOL");
  IElementType UNARY_EXPRESSION = new JawaElementType("UNARY_EXPRESSION");
  IElementType VAR_DEF_SYMBOL = new JawaElementType("VAR_DEF_SYMBOL");
  IElementType VAR_SYMBOL = new JawaElementType("VAR_SYMBOL");

  IElementType ADD = new JawaTokenType("+");
  IElementType AND = new JawaTokenType("^&");
  IElementType APOSTROPHE_ID = new JawaTokenType("APOSTROPHE_ID");
  IElementType ARROW = new JawaTokenType("=>");
  IElementType ASSIGN_OP = new JawaTokenType(":=");
  IElementType AT = new JawaTokenType("@");
  IElementType BAR = new JawaTokenType("|");
  IElementType CALL = new JawaTokenType("call");
  IElementType CATCH = new JawaTokenType("catch");
  IElementType CLASS_DESCRIPTOR_KEY = new JawaTokenType("@classDescriptor");
  IElementType CLASS_OR_INTERFACE = new JawaTokenType("record");
  IElementType COMMA = new JawaTokenType(",");
  IElementType CONST_CLASS = new JawaTokenType("constclass");
  IElementType DCMPG = new JawaTokenType("dcmpg");
  IElementType DCMPL = new JawaTokenType("dcmpl");
  IElementType DIV = new JawaTokenType("/");
  IElementType DOT = new JawaTokenType(".");
  IElementType ELSE = new JawaTokenType("else");
  IElementType EXCEPTION = new JawaTokenType("Exception");
  IElementType EXTENDS_AND_IMPLEMENTS = new JawaTokenType("extends");
  IElementType FCMPG = new JawaTokenType("fcmpg");
  IElementType FCMPL = new JawaTokenType("fcmpl");
  IElementType GOTO = new JawaTokenType("goto");
  IElementType HAT = new JawaTokenType("^");
  IElementType ID = new JawaTokenType("id");
  IElementType IF = new JawaTokenType("if");
  IElementType INSTANCEOF = new JawaTokenType("instanceof");
  IElementType LBRACE = new JawaTokenType("{");
  IElementType LBRACKET = new JawaTokenType("[");
  IElementType LCMP = new JawaTokenType("lcmp");
  IElementType LENGTH = new JawaTokenType("length");
  IElementType LPAREN = new JawaTokenType("(");
  IElementType METHOD = new JawaTokenType("procedure");
  IElementType MONITOR_ENTER = new JawaTokenType("monitorenter");
  IElementType MONITOR_EXIT = new JawaTokenType("monitorexit");
  IElementType MUL = new JawaTokenType("*");
  IElementType NEW = new JawaTokenType("new");
  IElementType NULL_LITERAL = new JawaTokenType("null");
  IElementType NUMBER = new JawaTokenType("number");
  IElementType OR = new JawaTokenType("^|");
  IElementType OWNER_KEY = new JawaTokenType("@owner");
  IElementType POUND = new JawaTokenType("#");
  IElementType RANGE = new JawaTokenType("..");
  IElementType RBRACE = new JawaTokenType("}");
  IElementType RBRACKET = new JawaTokenType("]");
  IElementType REM = new JawaTokenType("%%");
  IElementType RETURN = new JawaTokenType("return");
  IElementType RPAREN = new JawaTokenType(")");
  IElementType SEMI = new JawaTokenType(";");
  IElementType SHL = new JawaTokenType("^<");
  IElementType SHR = new JawaTokenType("^>");
  IElementType SIGNATURE_KEY = new JawaTokenType("@signature");
  IElementType STATIC_FIELD = new JawaTokenType("global");
  IElementType STATIC_ID = new JawaTokenType("STATIC_ID");
  IElementType STATIC_PREFIX = new JawaTokenType("@@");
  IElementType STRING_LITERAL = new JawaTokenType("STRING_LITERAL");
  IElementType SUB = new JawaTokenType("-");
  IElementType SWITCH = new JawaTokenType("switch");
  IElementType THEN = new JawaTokenType("then");
  IElementType THROW = new JawaTokenType("throw");
  IElementType TYPE_KEY = new JawaTokenType("@type");
  IElementType USHR = new JawaTokenType("^>>");
  IElementType XOR = new JawaTokenType("^~");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ACCESS_EXPRESSION) {
        return new JawaAccessExpressionImpl(node);
      }
      else if (type == ANNOTATION) {
        return new JawaAnnotationImpl(node);
      }
      else if (type == ANNOTATION_KEY) {
        return new JawaAnnotationKeyImpl(node);
      }
      else if (type == ANNOTATION_VALUE) {
        return new JawaAnnotationValueImpl(node);
      }
      else if (type == ARG_CLAUSE) {
        return new JawaArgClauseImpl(node);
      }
      else if (type == ASSIGNMENT_STATEMENT) {
        return new JawaAssignmentStatementImpl(node);
      }
      else if (type == BINARY_EXPRESSION) {
        return new JawaBinaryExpressionImpl(node);
      }
      else if (type == BODY) {
        return new JawaBodyImpl(node);
      }
      else if (type == CALL_LHS) {
        return new JawaCallLhsImpl(node);
      }
      else if (type == CALL_STATEMENT) {
        return new JawaCallStatementImpl(node);
      }
      else if (type == CAST_EXPRESSION) {
        return new JawaCastExpressionImpl(node);
      }
      else if (type == CATCH_CLAUSE) {
        return new JawaCatchClauseImpl(node);
      }
      else if (type == CATCH_RANGE) {
        return new JawaCatchRangeImpl(node);
      }
      else if (type == CLASS_OR_INTERFACE_DECLARATION) {
        return new JawaClassOrInterfaceDeclarationImpl(node);
      }
      else if (type == CMP) {
        return new JawaCmpImpl(node);
      }
      else if (type == CMP_EXPRESSION) {
        return new JawaCmpExpressionImpl(node);
      }
      else if (type == CONST_CLASS_EXPRESSION) {
        return new JawaConstClassExpressionImpl(node);
      }
      else if (type == EXCEPTION_EXPRESSION) {
        return new JawaExceptionExpressionImpl(node);
      }
      else if (type == EXPRESSION_LHS) {
        return new JawaExpressionLhsImpl(node);
      }
      else if (type == EXPRESSION_RHS) {
        return new JawaExpressionRhsImpl(node);
      }
      else if (type == EXTENDS_AND_IMPLEMENTS_CLAUSES) {
        return new JawaExtendsAndImplementsClausesImpl(node);
      }
      else if (type == EXTEND_AND_IMPLEMENT) {
        return new JawaExtendAndImplementImpl(node);
      }
      else if (type == FIELD_DEF_SYMBOL) {
        return new JawaFieldDefSymbolImpl(node);
      }
      else if (type == FIELD_NAME_SYMBOL) {
        return new JawaFieldNameSymbolImpl(node);
      }
      else if (type == GOTO_STATEMENT) {
        return new JawaGotoStatementImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new JawaIfStatementImpl(node);
      }
      else if (type == INDEXING_EXPRESSION) {
        return new JawaIndexingExpressionImpl(node);
      }
      else if (type == INSTANCEOF_EXPRESSION) {
        return new JawaInstanceofExpressionImpl(node);
      }
      else if (type == INSTANCE_FIELD_DECLARATION) {
        return new JawaInstanceFieldDeclarationImpl(node);
      }
      else if (type == INSTANCE_FIELD_DECLARATION_BLOCK) {
        return new JawaInstanceFieldDeclarationBlockImpl(node);
      }
      else if (type == LENGTH_EXPRESSION) {
        return new JawaLengthExpressionImpl(node);
      }
      else if (type == LITERAL_EXPRESSION) {
        return new JawaLiteralExpressionImpl(node);
      }
      else if (type == LOCAL_VAR_DECLARATION) {
        return new JawaLocalVarDeclarationImpl(node);
      }
      else if (type == LOCATION) {
        return new JawaLocationImpl(node);
      }
      else if (type == LOCATION_DEF_SYMBOL) {
        return new JawaLocationDefSymbolImpl(node);
      }
      else if (type == LOCATION_ID) {
        return new JawaLocationIdImpl(node);
      }
      else if (type == LOCATION_SYMBOL) {
        return new JawaLocationSymbolImpl(node);
      }
      else if (type == METHOD_DECLARATION) {
        return new JawaMethodDeclarationImpl(node);
      }
      else if (type == METHOD_DEF_SYMBOL) {
        return new JawaMethodDefSymbolImpl(node);
      }
      else if (type == METHOD_NAME_SYMBOL) {
        return new JawaMethodNameSymbolImpl(node);
      }
      else if (type == MONITOR_STATEMENT) {
        return new JawaMonitorStatementImpl(node);
      }
      else if (type == NAME_EXPRESSION) {
        return new JawaNameExpressionImpl(node);
      }
      else if (type == NEW_EXPRESSION) {
        return new JawaNewExpressionImpl(node);
      }
      else if (type == NULL_EXPRESSION) {
        return new JawaNullExpressionImpl(node);
      }
      else if (type == NUMBER_LITERAL) {
        return new JawaNumberLiteralImpl(node);
      }
      else if (type == PARAM) {
        return new JawaParamImpl(node);
      }
      else if (type == PARAM_CLAUSE) {
        return new JawaParamClauseImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new JawaReturnStatementImpl(node);
      }
      else if (type == SIGNATURE_ANNOTATION) {
        return new JawaSignatureAnnotationImpl(node);
      }
      else if (type == STATEMENT) {
        return new JawaStatementImpl(node);
      }
      else if (type == STATIC_FIELD_DECLARATION) {
        return new JawaStaticFieldDeclarationImpl(node);
      }
      else if (type == STATIC_FIELD_DEF_SYMBOL) {
        return new JawaStaticFieldDefSymbolImpl(node);
      }
      else if (type == STATIC_FIELD_NAME_SYMBOL) {
        return new JawaStaticFieldNameSymbolImpl(node);
      }
      else if (type == SWITCH_CASE) {
        return new JawaSwitchCaseImpl(node);
      }
      else if (type == SWITCH_DEFAULT_CASE) {
        return new JawaSwitchDefaultCaseImpl(node);
      }
      else if (type == SWITCH_STATEMENT) {
        return new JawaSwitchStatementImpl(node);
      }
      else if (type == THROW_STATEMENT) {
        return new JawaThrowStatementImpl(node);
      }
      else if (type == TUPLE_EXPRESSION) {
        return new JawaTupleExpressionImpl(node);
      }
      else if (type == TYPE) {
        return new JawaTypeImpl(node);
      }
      else if (type == TYPE_ANNOTATION) {
        return new JawaTypeAnnotationImpl(node);
      }
      else if (type == TYPE_DEF_SYMBOL) {
        return new JawaTypeDefSymbolImpl(node);
      }
      else if (type == TYPE_EXPRESSION) {
        return new JawaTypeExpressionImpl(node);
      }
      else if (type == TYPE_FRAGMENT) {
        return new JawaTypeFragmentImpl(node);
      }
      else if (type == TYPE_FRAGMENT_WITH_INIT) {
        return new JawaTypeFragmentWithInitImpl(node);
      }
      else if (type == TYPE_SYMBOL) {
        return new JawaTypeSymbolImpl(node);
      }
      else if (type == UNARY_EXPRESSION) {
        return new JawaUnaryExpressionImpl(node);
      }
      else if (type == VAR_DEF_SYMBOL) {
        return new JawaVarDefSymbolImpl(node);
      }
      else if (type == VAR_SYMBOL) {
        return new JawaVarSymbolImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
