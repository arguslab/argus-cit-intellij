/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.argus.cit.intellij.jawa.lang.parser.JawaElementTypeFactory;
import org.argus.cit.intellij.jawa.lang.psi.impl.*;

public interface JawaElementTypes {

  IElementType ACCESS_EXPRESSION = JawaElementTypeFactory.getElementType("ACCESS_EXPRESSION");
  IElementType ACCESS_FLAG_ANNOTATION = JawaElementTypeFactory.getElementType("ACCESS_FLAG_ANNOTATION");
  IElementType ANNOTATION = JawaElementTypeFactory.getElementType("ANNOTATION");
  IElementType ANNOTATION_KEY = JawaElementTypeFactory.getElementType("ANNOTATION_KEY");
  IElementType ARG_CLAUSE = JawaElementTypeFactory.getElementType("ARG_CLAUSE");
  IElementType ASSIGNMENT_STATEMENT = JawaElementTypeFactory.getElementType("ASSIGNMENT_STATEMENT");
  IElementType BINARY_EXPRESSION = JawaElementTypeFactory.getElementType("BINARY_EXPRESSION");
  IElementType CALL_LHS = JawaElementTypeFactory.getElementType("CALL_LHS");
  IElementType CALL_STATEMENT = JawaElementTypeFactory.getElementType("CALL_STATEMENT");
  IElementType CAST_EXPRESSION = JawaElementTypeFactory.getElementType("CAST_EXPRESSION");
  IElementType CATCH_CLAUSE = JawaElementTypeFactory.getElementType("CATCH_CLAUSE");
  IElementType CATCH_RANGE = JawaElementTypeFactory.getElementType("CATCH_RANGE");
  IElementType CLASS_OR_INTERFACE_DECLARATION = JawaElementTypeFactory.getElementType("CLASS_OR_INTERFACE_DECLARATION");
  IElementType CMP = JawaElementTypeFactory.getElementType("CMP");
  IElementType CMP_EXPRESSION = JawaElementTypeFactory.getElementType("CMP_EXPRESSION");
  IElementType CONST_CLASS_EXPRESSION = JawaElementTypeFactory.getElementType("CONST_CLASS_EXPRESSION");
  IElementType EXCEPTION_EXPRESSION = JawaElementTypeFactory.getElementType("EXCEPTION_EXPRESSION");
  IElementType EXPRESSION_LHS = JawaElementTypeFactory.getElementType("EXPRESSION_LHS");
  IElementType EXPRESSION_RHS = JawaElementTypeFactory.getElementType("EXPRESSION_RHS");
  IElementType EXTENDS_AND_IMPLEMENTS_CLAUSE = JawaElementTypeFactory.getElementType("EXTENDS_AND_IMPLEMENTS_CLAUSE");
  IElementType EXTEND_AND_IMPLEMENT = JawaElementTypeFactory.getElementType("EXTEND_AND_IMPLEMENT");
  IElementType FIELD_DECLARATION = JawaElementTypeFactory.getElementType("FIELD_DECLARATION");
  IElementType FIELD_DEF_SYMBOL = JawaElementTypeFactory.getElementType("FIELD_DEF_SYMBOL");
  IElementType FIELD_NAME_SYMBOL = JawaElementTypeFactory.getElementType("FIELD_NAME_SYMBOL");
  IElementType GOTO_STATEMENT = JawaElementTypeFactory.getElementType("GOTO_STATEMENT");
  IElementType IF_STATEMENT = JawaElementTypeFactory.getElementType("IF_STATEMENT");
  IElementType INDEXING_EXPRESSION = JawaElementTypeFactory.getElementType("INDEXING_EXPRESSION");
  IElementType INSTANCEOF_EXPRESSION = JawaElementTypeFactory.getElementType("INSTANCEOF_EXPRESSION");
  IElementType INSTANCE_FIELD_DECLARATION = JawaElementTypeFactory.getElementType("INSTANCE_FIELD_DECLARATION");
  IElementType INSTANCE_FIELD_DECLARATION_BLOCK = JawaElementTypeFactory.getElementType("INSTANCE_FIELD_DECLARATION_BLOCK");
  IElementType JW_BODY = JawaElementTypeFactory.getElementType("JW_BODY");
  IElementType JW_TYPE = JawaElementTypeFactory.getElementType("JW_TYPE");
  IElementType KIND_ANNOTATION = JawaElementTypeFactory.getElementType("KIND_ANNOTATION");
  IElementType LENGTH_EXPRESSION = JawaElementTypeFactory.getElementType("LENGTH_EXPRESSION");
  IElementType LITERAL_EXPRESSION = JawaElementTypeFactory.getElementType("LITERAL_EXPRESSION");
  IElementType LOCAL_VAR_DECLARATION = JawaElementTypeFactory.getElementType("LOCAL_VAR_DECLARATION");
  IElementType LOCATION = JawaElementTypeFactory.getElementType("LOCATION");
  IElementType LOCATION_DEF_SYMBOL = JawaElementTypeFactory.getElementType("LOCATION_DEF_SYMBOL");
  IElementType LOCATION_SYMBOL = JawaElementTypeFactory.getElementType("LOCATION_SYMBOL");
  IElementType METHOD_DECLARATION = JawaElementTypeFactory.getElementType("METHOD_DECLARATION");
  IElementType METHOD_DEF_SYMBOL = JawaElementTypeFactory.getElementType("METHOD_DEF_SYMBOL");
  IElementType METHOD_NAME_SYMBOL = JawaElementTypeFactory.getElementType("METHOD_NAME_SYMBOL");
  IElementType MONITOR_STATEMENT = JawaElementTypeFactory.getElementType("MONITOR_STATEMENT");
  IElementType NAME_EXPRESSION = JawaElementTypeFactory.getElementType("NAME_EXPRESSION");
  IElementType NEW_EXPRESSION = JawaElementTypeFactory.getElementType("NEW_EXPRESSION");
  IElementType NULL_EXPRESSION = JawaElementTypeFactory.getElementType("NULL_EXPRESSION");
  IElementType NUMBER_LITERAL = JawaElementTypeFactory.getElementType("NUMBER_LITERAL");
  IElementType PARAM = JawaElementTypeFactory.getElementType("PARAM");
  IElementType PARAM_CLAUSE = JawaElementTypeFactory.getElementType("PARAM_CLAUSE");
  IElementType RETURN_STATEMENT = JawaElementTypeFactory.getElementType("RETURN_STATEMENT");
  IElementType SIGNATURE_ANNOTATION = JawaElementTypeFactory.getElementType("SIGNATURE_ANNOTATION");
  IElementType SIGNATURE_SYMBOL = JawaElementTypeFactory.getElementType("SIGNATURE_SYMBOL");
  IElementType STATEMENT = JawaElementTypeFactory.getElementType("STATEMENT");
  IElementType STATIC_FIELD_DECLARATION = JawaElementTypeFactory.getElementType("STATIC_FIELD_DECLARATION");
  IElementType STATIC_FIELD_DEF_SYMBOL = JawaElementTypeFactory.getElementType("STATIC_FIELD_DEF_SYMBOL");
  IElementType STATIC_FIELD_NAME_SYMBOL = JawaElementTypeFactory.getElementType("STATIC_FIELD_NAME_SYMBOL");
  IElementType SWITCH_CASE = JawaElementTypeFactory.getElementType("SWITCH_CASE");
  IElementType SWITCH_DEFAULT_CASE = JawaElementTypeFactory.getElementType("SWITCH_DEFAULT_CASE");
  IElementType SWITCH_STATEMENT = JawaElementTypeFactory.getElementType("SWITCH_STATEMENT");
  IElementType THROW_STATEMENT = JawaElementTypeFactory.getElementType("THROW_STATEMENT");
  IElementType TUPLE_EXPRESSION = JawaElementTypeFactory.getElementType("TUPLE_EXPRESSION");
  IElementType TYPE_ANNOTATION = JawaElementTypeFactory.getElementType("TYPE_ANNOTATION");
  IElementType TYPE_DEF_SYMBOL = JawaElementTypeFactory.getElementType("TYPE_DEF_SYMBOL");
  IElementType TYPE_EXPRESSION = JawaElementTypeFactory.getElementType("TYPE_EXPRESSION");
  IElementType TYPE_FRAGMENT = JawaElementTypeFactory.getElementType("TYPE_FRAGMENT");
  IElementType TYPE_FRAGMENT_WITH_INIT = JawaElementTypeFactory.getElementType("TYPE_FRAGMENT_WITH_INIT");
  IElementType TYPE_SYMBOL = JawaElementTypeFactory.getElementType("TYPE_SYMBOL");
  IElementType UNARY_EXPRESSION = JawaElementTypeFactory.getElementType("UNARY_EXPRESSION");
  IElementType VAR_DEF_SYMBOL = JawaElementTypeFactory.getElementType("VAR_DEF_SYMBOL");
  IElementType VAR_SYMBOL = JawaElementTypeFactory.getElementType("VAR_SYMBOL");

  IElementType ACCESS_FLAG_KEY = new JawaTokenType("@AccessFlag");
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
  IElementType ID = new JawaTokenType("ID");
  IElementType IF = new JawaTokenType("if");
  IElementType INSTANCEOF = new JawaTokenType("instanceof");
  IElementType KIND_KEY = new JawaTokenType("@kind");
  IElementType LBRACE = new JawaTokenType("{");
  IElementType LBRACKET = new JawaTokenType("[");
  IElementType LCMP = new JawaTokenType("lcmp");
  IElementType LENGTH = new JawaTokenType("length");
  IElementType LOCATION_ID = new JawaTokenType("LOCATION_ID");
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
      else if (type == ACCESS_FLAG_ANNOTATION) {
        return new JawaAccessFlagAnnotationImpl(node);
      }
      else if (type == ANNOTATION) {
        return new JawaAnnotationImpl(node);
      }
      else if (type == ANNOTATION_KEY) {
        return new JawaAnnotationKeyImpl(node);
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
      else if (type == EXTENDS_AND_IMPLEMENTS_CLAUSE) {
        return new JawaExtendsAndImplementsClauseImpl(node);
      }
      else if (type == EXTEND_AND_IMPLEMENT) {
        return new JawaExtendAndImplementImpl(node);
      }
      else if (type == FIELD_DECLARATION) {
        return new JawaFieldDeclarationImpl(node);
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
      else if (type == JW_BODY) {
        return new JawaJwBodyImpl(node);
      }
      else if (type == JW_TYPE) {
        return new JawaJwTypeImpl(node);
      }
      else if (type == KIND_ANNOTATION) {
        return new JawaKindAnnotationImpl(node);
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
      else if (type == SIGNATURE_SYMBOL) {
        return new JawaSignatureSymbolImpl(node);
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
