// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JawaParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ACCESS_EXPRESSION) {
      r = AccessExpression(b, 0);
    }
    else if (t == ACCESS_FLAG_ANNOTATION) {
      r = AccessFlagAnnotation(b, 0);
    }
    else if (t == ANNOTATION) {
      r = Annotation(b, 0);
    }
    else if (t == ANNOTATION_KEY) {
      r = AnnotationKey(b, 0);
    }
    else if (t == ARG_CLAUSE) {
      r = ArgClause(b, 0);
    }
    else if (t == ASSIGNMENT_STATEMENT) {
      r = AssignmentStatement(b, 0);
    }
    else if (t == BINARY_EXPRESSION) {
      r = BinaryExpression(b, 0);
    }
    else if (t == CMP) {
      r = CMP(b, 0);
    }
    else if (t == CALL_LHS) {
      r = CallLhs(b, 0);
    }
    else if (t == CALL_STATEMENT) {
      r = CallStatement(b, 0);
    }
    else if (t == CAST_EXPRESSION) {
      r = CastExpression(b, 0);
    }
    else if (t == CATCH_CLAUSE) {
      r = CatchClause(b, 0);
    }
    else if (t == CATCH_RANGE) {
      r = CatchRange(b, 0);
    }
    else if (t == CLASS_OR_INTERFACE_DECLARATION) {
      r = ClassOrInterfaceDeclaration(b, 0);
    }
    else if (t == CMP_EXPRESSION) {
      r = CmpExpression(b, 0);
    }
    else if (t == CONST_CLASS_EXPRESSION) {
      r = ConstClassExpression(b, 0);
    }
    else if (t == EXCEPTION_EXPRESSION) {
      r = ExceptionExpression(b, 0);
    }
    else if (t == EXPRESSION_LHS) {
      r = Expression_Lhs(b, 0);
    }
    else if (t == EXPRESSION_RHS) {
      r = Expression_Rhs(b, 0);
    }
    else if (t == EXTEND_AND_IMPLEMENT) {
      r = ExtendAndImplement(b, 0);
    }
    else if (t == EXTENDS_AND_IMPLEMENTS_CLAUSE) {
      r = ExtendsAndImplementsClause(b, 0);
    }
    else if (t == FIELD_DECLARATION) {
      r = FieldDeclaration(b, 0);
    }
    else if (t == FIELD_DEF_SYMBOL) {
      r = FieldDefSymbol(b, 0);
    }
    else if (t == FIELD_NAME_SYMBOL) {
      r = FieldNameSymbol(b, 0);
    }
    else if (t == GOTO_STATEMENT) {
      r = GotoStatement(b, 0);
    }
    else if (t == IF_STATEMENT) {
      r = IfStatement(b, 0);
    }
    else if (t == INDEXING_EXPRESSION) {
      r = IndexingExpression(b, 0);
    }
    else if (t == INSTANCE_FIELD_DECLARATION) {
      r = InstanceFieldDeclaration(b, 0);
    }
    else if (t == INSTANCE_FIELD_DECLARATION_BLOCK) {
      r = InstanceFieldDeclarationBlock(b, 0);
    }
    else if (t == INSTANCEOF_EXPRESSION) {
      r = InstanceofExpression(b, 0);
    }
    else if (t == JW_BODY) {
      r = JwBody(b, 0);
    }
    else if (t == JW_TYPE) {
      r = JwType(b, 0);
    }
    else if (t == KIND_ANNOTATION) {
      r = KindAnnotation(b, 0);
    }
    else if (t == LENGTH_EXPRESSION) {
      r = LengthExpression(b, 0);
    }
    else if (t == LITERAL_EXPRESSION) {
      r = LiteralExpression(b, 0);
    }
    else if (t == LOCAL_VAR_DECLARATION) {
      r = LocalVarDeclaration(b, 0);
    }
    else if (t == LOCATION) {
      r = Location(b, 0);
    }
    else if (t == LOCATION_DEF_SYMBOL) {
      r = LocationDefSymbol(b, 0);
    }
    else if (t == LOCATION_SYMBOL) {
      r = LocationSymbol(b, 0);
    }
    else if (t == METHOD_DECLARATION) {
      r = MethodDeclaration(b, 0);
    }
    else if (t == METHOD_DEF_SYMBOL) {
      r = MethodDefSymbol(b, 0);
    }
    else if (t == METHOD_NAME_SYMBOL) {
      r = MethodNameSymbol(b, 0);
    }
    else if (t == MONITOR_STATEMENT) {
      r = MonitorStatement(b, 0);
    }
    else if (t == NUMBER_LITERAL) {
      r = NUMBER_LITERAL(b, 0);
    }
    else if (t == NAME_EXPRESSION) {
      r = NameExpression(b, 0);
    }
    else if (t == NEW_EXPRESSION) {
      r = NewExpression(b, 0);
    }
    else if (t == NULL_EXPRESSION) {
      r = NullExpression(b, 0);
    }
    else if (t == PARAM) {
      r = Param(b, 0);
    }
    else if (t == PARAM_CLAUSE) {
      r = ParamClause(b, 0);
    }
    else if (t == RETURN_STATEMENT) {
      r = ReturnStatement(b, 0);
    }
    else if (t == SIGNATURE_ANNOTATION) {
      r = SignatureAnnotation(b, 0);
    }
    else if (t == SIGNATURE_SYMBOL) {
      r = SignatureSymbol(b, 0);
    }
    else if (t == STATEMENT) {
      r = Statement(b, 0);
    }
    else if (t == STATIC_FIELD_DECLARATION) {
      r = StaticFieldDeclaration(b, 0);
    }
    else if (t == STATIC_FIELD_DEF_SYMBOL) {
      r = StaticFieldDefSymbol(b, 0);
    }
    else if (t == STATIC_FIELD_NAME_SYMBOL) {
      r = StaticFieldNameSymbol(b, 0);
    }
    else if (t == SWITCH_CASE) {
      r = SwitchCase(b, 0);
    }
    else if (t == SWITCH_DEFAULT_CASE) {
      r = SwitchDefaultCase(b, 0);
    }
    else if (t == SWITCH_STATEMENT) {
      r = SwitchStatement(b, 0);
    }
    else if (t == THROW_STATEMENT) {
      r = ThrowStatement(b, 0);
    }
    else if (t == TUPLE_EXPRESSION) {
      r = TupleExpression(b, 0);
    }
    else if (t == TYPE_ANNOTATION) {
      r = TypeAnnotation(b, 0);
    }
    else if (t == TYPE_DEF_SYMBOL) {
      r = TypeDefSymbol(b, 0);
    }
    else if (t == TYPE_EXPRESSION) {
      r = TypeExpression(b, 0);
    }
    else if (t == TYPE_FRAGMENT) {
      r = TypeFragment(b, 0);
    }
    else if (t == TYPE_FRAGMENT_WITH_INIT) {
      r = TypeFragmentWithInit(b, 0);
    }
    else if (t == TYPE_SYMBOL) {
      r = TypeSymbol(b, 0);
    }
    else if (t == UNARY_EXPRESSION) {
      r = UnaryExpression(b, 0);
    }
    else if (t == VAR_DEF_SYMBOL) {
      r = VarDefSymbol(b, 0);
    }
    else if (t == VAR_SYMBOL) {
      r = VarSymbol(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return CompilationUnit(b, l + 1);
  }

  /* ********************************************************** */
  // VarSymbol '.' FieldNameSymbol
  public static boolean AccessExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AccessExpression")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarSymbol(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && FieldNameSymbol(b, l + 1);
    exit_section_(b, m, ACCESS_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // '@AccessFlag' ID?
  public static boolean AccessFlagAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AccessFlagAnnotation")) return false;
    if (!nextTokenIs(b, ACCESS_FLAG_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ACCESS_FLAG_KEY);
    r = r && AccessFlagAnnotation_1(b, l + 1);
    exit_section_(b, m, ACCESS_FLAG_ANNOTATION, r);
    return r;
  }

  // ID?
  private static boolean AccessFlagAnnotation_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AccessFlagAnnotation_1")) return false;
    consumeToken(b, ID);
    return true;
  }

  /* ********************************************************** */
  // TypeAnnotation | SignatureAnnotation | KindAnnotation | AccessFlagAnnotation | AnnotationKey ID?
  public static boolean Annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION, "<annotation>");
    r = TypeAnnotation(b, l + 1);
    if (!r) r = SignatureAnnotation(b, l + 1);
    if (!r) r = KindAnnotation(b, l + 1);
    if (!r) r = AccessFlagAnnotation(b, l + 1);
    if (!r) r = Annotation_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // AnnotationKey ID?
  private static boolean Annotation_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AnnotationKey(b, l + 1);
    r = r && Annotation_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID?
  private static boolean Annotation_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation_4_1")) return false;
    consumeToken(b, ID);
    return true;
  }

  /* ********************************************************** */
  // '@' ID
  public static boolean AnnotationKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationKey")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, ANNOTATION_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // '(' (VarSymbol (',' VarSymbol)*)? ')'
  public static boolean ArgClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgClause")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && ArgClause_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ARG_CLAUSE, r);
    return r;
  }

  // (VarSymbol (',' VarSymbol)*)?
  private static boolean ArgClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgClause_1")) return false;
    ArgClause_1_0(b, l + 1);
    return true;
  }

  // VarSymbol (',' VarSymbol)*
  private static boolean ArgClause_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgClause_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarSymbol(b, l + 1);
    r = r && ArgClause_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' VarSymbol)*
  private static boolean ArgClause_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgClause_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ArgClause_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArgClause_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' VarSymbol
  private static boolean ArgClause_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgClause_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && VarSymbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression_Lhs ':=' Expression_Rhs KindAnnotation? TypeAnnotation?
  public static boolean AssignmentStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentStatement")) return false;
    if (!nextTokenIs(b, "<assignment statement>", ID, STATIC_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_STATEMENT, "<assignment statement>");
    r = Expression_Lhs(b, l + 1);
    r = r && consumeToken(b, ASSIGN_OP);
    r = r && Expression_Rhs(b, l + 1);
    r = r && AssignmentStatement_3(b, l + 1);
    r = r && AssignmentStatement_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KindAnnotation?
  private static boolean AssignmentStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentStatement_3")) return false;
    KindAnnotation(b, l + 1);
    return true;
  }

  // TypeAnnotation?
  private static boolean AssignmentStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentStatement_4")) return false;
    TypeAnnotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // VarSymbol binary_op (VarSymbol|NUMBER_LITERAL|'null')
  public static boolean BinaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinaryExpression")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarSymbol(b, l + 1);
    r = r && binary_op(b, l + 1);
    r = r && BinaryExpression_2(b, l + 1);
    exit_section_(b, m, BINARY_EXPRESSION, r);
    return r;
  }

  // VarSymbol|NUMBER_LITERAL|'null'
  private static boolean BinaryExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinaryExpression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarSymbol(b, l + 1);
    if (!r) r = NUMBER_LITERAL(b, l + 1);
    if (!r) r = consumeToken(b, NULL_LITERAL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'fcmpl' | 'fcmpg' | 'dcmpl' | 'dcmpg' | 'lcmp'
  public static boolean CMP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CMP")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CMP, "<cmp>");
    r = consumeToken(b, FCMPL);
    if (!r) r = consumeToken(b, FCMPG);
    if (!r) r = consumeToken(b, DCMPL);
    if (!r) r = consumeToken(b, DCMPG);
    if (!r) r = consumeToken(b, LCMP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VarSymbol ':='
  public static boolean CallLhs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallLhs")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarSymbol(b, l + 1);
    r = r && consumeToken(b, ASSIGN_OP);
    exit_section_(b, m, CALL_LHS, r);
    return r;
  }

  /* ********************************************************** */
  // 'call' CallLhs? MethodNameSymbol ArgClause SignatureAnnotation TypeAnnotation KindAnnotation
  public static boolean CallStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallStatement")) return false;
    if (!nextTokenIs(b, CALL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CALL);
    r = r && CallStatement_1(b, l + 1);
    r = r && MethodNameSymbol(b, l + 1);
    r = r && ArgClause(b, l + 1);
    r = r && SignatureAnnotation(b, l + 1);
    r = r && TypeAnnotation(b, l + 1);
    r = r && KindAnnotation(b, l + 1);
    exit_section_(b, m, CALL_STATEMENT, r);
    return r;
  }

  // CallLhs?
  private static boolean CallStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallStatement_1")) return false;
    CallLhs(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '(' JwType ')' VarSymbol
  public static boolean CastExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastExpression")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && JwType(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && VarSymbol(b, l + 1);
    exit_section_(b, m, CAST_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // 'catch' JwType CatchRange 'goto' LocationSymbol ';'
  public static boolean CatchClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchClause")) return false;
    if (!nextTokenIs(b, CATCH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CATCH);
    r = r && JwType(b, l + 1);
    r = r && CatchRange(b, l + 1);
    r = r && consumeToken(b, GOTO);
    r = r && LocationSymbol(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, CATCH_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // '@' '[' LocationSymbol '..' LocationSymbol ']'
  public static boolean CatchRange(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchRange")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CATCH_RANGE, null);
    r = consumeToken(b, AT);
    r = r && consumeToken(b, LBRACKET);
    p = r; // pin = 2
    r = r && report_error_(b, LocationSymbol(b, l + 1));
    r = p && report_error_(b, consumeToken(b, RANGE)) && r;
    r = p && report_error_(b, LocationSymbol(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // 'record' TypeDefSymbol KindAnnotation AccessFlagAnnotation ExtendsAndImplementsClause? InstanceFieldDeclarationBlock StaticFieldDeclaration* MethodDeclaration*
  public static boolean ClassOrInterfaceDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassOrInterfaceDeclaration")) return false;
    if (!nextTokenIs(b, CLASS_OR_INTERFACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CLASS_OR_INTERFACE);
    r = r && TypeDefSymbol(b, l + 1);
    r = r && KindAnnotation(b, l + 1);
    r = r && AccessFlagAnnotation(b, l + 1);
    r = r && ClassOrInterfaceDeclaration_4(b, l + 1);
    r = r && InstanceFieldDeclarationBlock(b, l + 1);
    r = r && ClassOrInterfaceDeclaration_6(b, l + 1);
    r = r && ClassOrInterfaceDeclaration_7(b, l + 1);
    exit_section_(b, m, CLASS_OR_INTERFACE_DECLARATION, r);
    return r;
  }

  // ExtendsAndImplementsClause?
  private static boolean ClassOrInterfaceDeclaration_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassOrInterfaceDeclaration_4")) return false;
    ExtendsAndImplementsClause(b, l + 1);
    return true;
  }

  // StaticFieldDeclaration*
  private static boolean ClassOrInterfaceDeclaration_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassOrInterfaceDeclaration_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!StaticFieldDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ClassOrInterfaceDeclaration_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // MethodDeclaration*
  private static boolean ClassOrInterfaceDeclaration_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassOrInterfaceDeclaration_7")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MethodDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ClassOrInterfaceDeclaration_7", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // CMP '(' VarSymbol ',' VarSymbol ')'
  public static boolean CmpExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmpExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CMP_EXPRESSION, "<cmp expression>");
    r = CMP(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && VarSymbol(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && VarSymbol(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ClassOrInterfaceDeclaration*
  static boolean CompilationUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompilationUnit")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ClassOrInterfaceDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CompilationUnit", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // 'constclass' TypeAnnotation
  public static boolean ConstClassExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstClassExpression")) return false;
    if (!nextTokenIs(b, CONST_CLASS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONST_CLASS);
    r = r && TypeAnnotation(b, l + 1);
    exit_section_(b, m, CONST_CLASS_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // 'Exception'
  public static boolean ExceptionExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExceptionExpression")) return false;
    if (!nextTokenIs(b, EXCEPTION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXCEPTION);
    exit_section_(b, m, EXCEPTION_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // AccessExpression
  //                    |IndexingExpression
  //                    |NameExpression
  public static boolean Expression_Lhs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_Lhs")) return false;
    if (!nextTokenIs(b, "<expression lhs>", ID, STATIC_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_LHS, "<expression lhs>");
    r = AccessExpression(b, l + 1);
    if (!r) r = IndexingExpression(b, l + 1);
    if (!r) r = NameExpression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NewExpression
  //                    |CmpExpression
  //                    |ExceptionExpression
  //                    |ConstClassExpression
  //                    |LengthExpression
  //                    |InstanceofExpression
  //                    |NullExpression
  //                    |TupleExpression
  //                    |CastExpression
  //                    |LiteralExpression
  //                    |UnaryExpression
  //                    |AccessExpression
  //                    |IndexingExpression
  //                    |BinaryExpression
  //                    |NameExpression
  public static boolean Expression_Rhs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_Rhs")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_RHS, "<expression rhs>");
    r = NewExpression(b, l + 1);
    if (!r) r = CmpExpression(b, l + 1);
    if (!r) r = ExceptionExpression(b, l + 1);
    if (!r) r = ConstClassExpression(b, l + 1);
    if (!r) r = LengthExpression(b, l + 1);
    if (!r) r = InstanceofExpression(b, l + 1);
    if (!r) r = NullExpression(b, l + 1);
    if (!r) r = TupleExpression(b, l + 1);
    if (!r) r = CastExpression(b, l + 1);
    if (!r) r = LiteralExpression(b, l + 1);
    if (!r) r = UnaryExpression(b, l + 1);
    if (!r) r = AccessExpression(b, l + 1);
    if (!r) r = IndexingExpression(b, l + 1);
    if (!r) r = BinaryExpression(b, l + 1);
    if (!r) r = NameExpression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TypeSymbol KindAnnotation
  public static boolean ExtendAndImplement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtendAndImplement")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TypeSymbol(b, l + 1);
    r = r && KindAnnotation(b, l + 1);
    exit_section_(b, m, EXTEND_AND_IMPLEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // 'extends' ExtendAndImplement (',' ExtendAndImplement)*
  public static boolean ExtendsAndImplementsClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtendsAndImplementsClause")) return false;
    if (!nextTokenIs(b, EXTENDS_AND_IMPLEMENTS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXTENDS_AND_IMPLEMENTS_CLAUSE, null);
    r = consumeToken(b, EXTENDS_AND_IMPLEMENTS);
    r = r && ExtendAndImplement(b, l + 1);
    p = r; // pin = 2
    r = r && ExtendsAndImplementsClause_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' ExtendAndImplement)*
  private static boolean ExtendsAndImplementsClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtendsAndImplementsClause_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ExtendsAndImplementsClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ExtendsAndImplementsClause_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' ExtendAndImplement
  private static boolean ExtendsAndImplementsClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtendsAndImplementsClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ExtendAndImplement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // JwType (FieldDefSymbol|StaticFieldDefSymbol) AccessFlagAnnotation
  public static boolean FieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDeclaration")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = JwType(b, l + 1);
    r = r && FieldDeclaration_1(b, l + 1);
    r = r && AccessFlagAnnotation(b, l + 1);
    exit_section_(b, m, FIELD_DECLARATION, r);
    return r;
  }

  // FieldDefSymbol|StaticFieldDefSymbol
  private static boolean FieldDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FieldDefSymbol(b, l + 1);
    if (!r) r = StaticFieldDefSymbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // APOSTROPHE_ID
  public static boolean FieldDefSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDefSymbol")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, APOSTROPHE_ID);
    exit_section_(b, m, FIELD_DEF_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // APOSTROPHE_ID
  public static boolean FieldNameSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldNameSymbol")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, APOSTROPHE_ID);
    exit_section_(b, m, FIELD_NAME_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // 'goto' LocationSymbol
  public static boolean GotoStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GotoStatement")) return false;
    if (!nextTokenIs(b, GOTO)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GOTO);
    r = r && LocationSymbol(b, l + 1);
    exit_section_(b, m, GOTO_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // 'if' BinaryExpression 'then' 'goto' LocationSymbol
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && BinaryExpression(b, l + 1);
    r = r && consumeToken(b, THEN);
    r = r && consumeToken(b, GOTO);
    r = r && LocationSymbol(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // VarSymbol '[' (VarSymbol|NUMBER_LITERAL) ']'
  public static boolean IndexingExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexingExpression")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INDEXING_EXPRESSION, null);
    r = VarSymbol(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    p = r; // pin = 2
    r = r && report_error_(b, IndexingExpression_2(b, l + 1));
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // VarSymbol|NUMBER_LITERAL
  private static boolean IndexingExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexingExpression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarSymbol(b, l + 1);
    if (!r) r = NUMBER_LITERAL(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FieldDeclaration ';'
  public static boolean InstanceFieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceFieldDeclaration")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FieldDeclaration(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, INSTANCE_FIELD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // '{' InstanceFieldDeclaration* '}'
  public static boolean InstanceFieldDeclarationBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceFieldDeclarationBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && InstanceFieldDeclarationBlock_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, INSTANCE_FIELD_DECLARATION_BLOCK, r);
    return r;
  }

  // InstanceFieldDeclaration*
  private static boolean InstanceFieldDeclarationBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceFieldDeclarationBlock_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!InstanceFieldDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InstanceFieldDeclarationBlock_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // 'instanceof' '@' ID VarSymbol TypeAnnotation
  public static boolean InstanceofExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceofExpression")) return false;
    if (!nextTokenIs(b, INSTANCEOF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSTANCEOF);
    r = r && consumeToken(b, AT);
    r = r && consumeToken(b, ID);
    r = r && VarSymbol(b, l + 1);
    r = r && TypeAnnotation(b, l + 1);
    exit_section_(b, m, INSTANCEOF_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // '{' LocalVarDeclaration* Location* CatchClause* '}'
  public static boolean JwBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "JwBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && JwBody_1(b, l + 1);
    r = r && JwBody_2(b, l + 1);
    r = r && JwBody_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, JW_BODY, r);
    return r;
  }

  // LocalVarDeclaration*
  private static boolean JwBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "JwBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!LocalVarDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "JwBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Location*
  private static boolean JwBody_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "JwBody_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Location(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "JwBody_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // CatchClause*
  private static boolean JwBody_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "JwBody_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!CatchClause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "JwBody_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // TypeSymbol TypeFragment*
  public static boolean JwType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "JwType")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TypeSymbol(b, l + 1);
    r = r && JwType_1(b, l + 1);
    exit_section_(b, m, JW_TYPE, r);
    return r;
  }

  // TypeFragment*
  private static boolean JwType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "JwType_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!TypeFragment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "JwType_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // '@kind' ID
  public static boolean KindAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KindAnnotation")) return false;
    if (!nextTokenIs(b, KIND_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KIND_KEY);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, KIND_ANNOTATION, r);
    return r;
  }

  /* ********************************************************** */
  // 'length' '@' ID VarSymbol
  public static boolean LengthExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LengthExpression")) return false;
    if (!nextTokenIs(b, LENGTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LENGTH);
    r = r && consumeToken(b, AT);
    r = r && consumeToken(b, ID);
    r = r && VarSymbol(b, l + 1);
    exit_section_(b, m, LENGTH_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER_LITERAL|STRING_LITERAL|'null'
  public static boolean LiteralExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_EXPRESSION, "<literal expression>");
    r = NUMBER_LITERAL(b, l + 1);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    if (!r) r = consumeToken(b, NULL_LITERAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // JwType VarDefSymbol ';'
  public static boolean LocalVarDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVarDeclaration")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = JwType(b, l + 1);
    r = r && VarDefSymbol(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, LOCAL_VAR_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // LocationDefSymbol Statement? ';'?
  public static boolean Location(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Location")) return false;
    if (!nextTokenIs(b, LOCATION_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LocationDefSymbol(b, l + 1);
    r = r && Location_1(b, l + 1);
    r = r && Location_2(b, l + 1);
    exit_section_(b, m, LOCATION, r);
    return r;
  }

  // Statement?
  private static boolean Location_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Location_1")) return false;
    Statement(b, l + 1);
    return true;
  }

  // ';'?
  private static boolean Location_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Location_2")) return false;
    consumeToken(b, SEMI);
    return true;
  }

  /* ********************************************************** */
  // LOCATION_ID
  public static boolean LocationDefSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocationDefSymbol")) return false;
    if (!nextTokenIs(b, LOCATION_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LOCATION_ID);
    exit_section_(b, m, LOCATION_DEF_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean LocationSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocationSymbol")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, LOCATION_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // 'procedure' JwType MethodDefSymbol ParamClause TypeAnnotation SignatureAnnotation AccessFlagAnnotation JwBody
  public static boolean MethodDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodDeclaration")) return false;
    if (!nextTokenIs(b, METHOD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, METHOD);
    r = r && JwType(b, l + 1);
    r = r && MethodDefSymbol(b, l + 1);
    r = r && ParamClause(b, l + 1);
    r = r && TypeAnnotation(b, l + 1);
    r = r && SignatureAnnotation(b, l + 1);
    r = r && AccessFlagAnnotation(b, l + 1);
    r = r && JwBody(b, l + 1);
    exit_section_(b, m, METHOD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // APOSTROPHE_ID
  public static boolean MethodDefSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodDefSymbol")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, APOSTROPHE_ID);
    exit_section_(b, m, METHOD_DEF_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // APOSTROPHE_ID
  public static boolean MethodNameSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MethodNameSymbol")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, APOSTROPHE_ID);
    exit_section_(b, m, METHOD_NAME_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // '@' ('monitorenter'|'monitorexit') VarSymbol
  public static boolean MonitorStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MonitorStatement")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MONITOR_STATEMENT, null);
    r = consumeToken(b, AT);
    r = r && MonitorStatement_1(b, l + 1);
    p = r; // pin = 2
    r = r && VarSymbol(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // 'monitorenter'|'monitorexit'
  private static boolean MonitorStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MonitorStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MONITOR_ENTER);
    if (!r) r = consumeToken(b, MONITOR_EXIT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // number ['i'|'I'|'l'|'L'|'f'|'F'|'d'|'D']
  public static boolean NUMBER_LITERAL(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NUMBER_LITERAL")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    r = r && NUMBER_LITERAL_1(b, l + 1);
    exit_section_(b, m, NUMBER_LITERAL, r);
    return r;
  }

  // ['i'|'I'|'l'|'L'|'f'|'F'|'d'|'D']
  private static boolean NUMBER_LITERAL_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NUMBER_LITERAL_1")) return false;
    NUMBER_LITERAL_1_0(b, l + 1);
    return true;
  }

  // 'i'|'I'|'l'|'L'|'f'|'F'|'d'|'D'
  private static boolean NUMBER_LITERAL_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NUMBER_LITERAL_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "i");
    if (!r) r = consumeToken(b, "I");
    if (!r) r = consumeToken(b, "l");
    if (!r) r = consumeToken(b, "L");
    if (!r) r = consumeToken(b, "f");
    if (!r) r = consumeToken(b, "F");
    if (!r) r = consumeToken(b, "d");
    if (!r) r = consumeToken(b, "D");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // VarSymbol|StaticFieldNameSymbol
  public static boolean NameExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NameExpression")) return false;
    if (!nextTokenIs(b, "<name expression>", ID, STATIC_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NAME_EXPRESSION, "<name expression>");
    r = VarSymbol(b, l + 1);
    if (!r) r = StaticFieldNameSymbol(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'new' TypeSymbol TypeFragmentWithInit*
  public static boolean NewExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression")) return false;
    if (!nextTokenIs(b, NEW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW);
    r = r && TypeSymbol(b, l + 1);
    r = r && NewExpression_2(b, l + 1);
    exit_section_(b, m, NEW_EXPRESSION, r);
    return r;
  }

  // TypeFragmentWithInit*
  private static boolean NewExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewExpression_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!TypeFragmentWithInit(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NewExpression_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // 'null' TypeAnnotation
  public static boolean NullExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NullExpression")) return false;
    if (!nextTokenIs(b, NULL_LITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NULL_LITERAL);
    r = r && TypeAnnotation(b, l + 1);
    exit_section_(b, m, NULL_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // JwType VarDefSymbol KindAnnotation?
  public static boolean Param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Param")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = JwType(b, l + 1);
    r = r && VarDefSymbol(b, l + 1);
    r = r && Param_2(b, l + 1);
    exit_section_(b, m, PARAM, r);
    return r;
  }

  // KindAnnotation?
  private static boolean Param_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Param_2")) return false;
    KindAnnotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '(' (Param (',' Param)*)? ')'
  public static boolean ParamClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamClause")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && ParamClause_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, PARAM_CLAUSE, r);
    return r;
  }

  // (Param (',' Param)*)?
  private static boolean ParamClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamClause_1")) return false;
    ParamClause_1_0(b, l + 1);
    return true;
  }

  // Param (',' Param)*
  private static boolean ParamClause_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamClause_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Param(b, l + 1);
    r = r && ParamClause_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' Param)*
  private static boolean ParamClause_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamClause_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ParamClause_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ParamClause_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' Param
  private static boolean ParamClause_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamClause_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'return' VarSymbol? KindAnnotation?
  public static boolean ReturnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN);
    r = r && ReturnStatement_1(b, l + 1);
    r = r && ReturnStatement_2(b, l + 1);
    exit_section_(b, m, RETURN_STATEMENT, r);
    return r;
  }

  // VarSymbol?
  private static boolean ReturnStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement_1")) return false;
    VarSymbol(b, l + 1);
    return true;
  }

  // KindAnnotation?
  private static boolean ReturnStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement_2")) return false;
    KindAnnotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '@signature' SignatureSymbol
  public static boolean SignatureAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SignatureAnnotation")) return false;
    if (!nextTokenIs(b, SIGNATURE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SIGNATURE_KEY);
    r = r && SignatureSymbol(b, l + 1);
    exit_section_(b, m, SIGNATURE_ANNOTATION, r);
    return r;
  }

  /* ********************************************************** */
  // APOSTROPHE_ID
  public static boolean SignatureSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SignatureSymbol")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, APOSTROPHE_ID);
    exit_section_(b, m, SIGNATURE_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // CallStatement
  //               |ThrowStatement
  //               |IfStatement
  //               |SwitchStatement
  //               |ReturnStatement
  //               |GotoStatement
  //               |MonitorStatement
  //               |AssignmentStatement
  //               |Annotation*
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = CallStatement(b, l + 1);
    if (!r) r = ThrowStatement(b, l + 1);
    if (!r) r = IfStatement(b, l + 1);
    if (!r) r = SwitchStatement(b, l + 1);
    if (!r) r = ReturnStatement(b, l + 1);
    if (!r) r = GotoStatement(b, l + 1);
    if (!r) r = MonitorStatement(b, l + 1);
    if (!r) r = AssignmentStatement(b, l + 1);
    if (!r) r = Statement_8(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation*
  private static boolean Statement_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_8")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Statement_8", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // 'global' FieldDeclaration ';'
  public static boolean StaticFieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StaticFieldDeclaration")) return false;
    if (!nextTokenIs(b, STATIC_FIELD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STATIC_FIELD);
    r = r && FieldDeclaration(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, STATIC_FIELD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // STATIC_ID
  public static boolean StaticFieldDefSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StaticFieldDefSymbol")) return false;
    if (!nextTokenIs(b, STATIC_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STATIC_ID);
    exit_section_(b, m, STATIC_FIELD_DEF_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // STATIC_ID
  public static boolean StaticFieldNameSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StaticFieldNameSymbol")) return false;
    if (!nextTokenIs(b, STATIC_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STATIC_ID);
    exit_section_(b, m, STATIC_FIELD_NAME_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // '|' NUMBER_LITERAL '=>' 'goto' LocationSymbol
  public static boolean SwitchCase(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCase")) return false;
    if (!nextTokenIs(b, BAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_CASE, null);
    r = consumeToken(b, BAR);
    r = r && NUMBER_LITERAL(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, consumeToken(b, ARROW));
    r = p && report_error_(b, consumeToken(b, GOTO)) && r;
    r = p && LocationSymbol(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // '|' 'else' '=>' 'goto' LocationSymbol
  public static boolean SwitchDefaultCase(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchDefaultCase")) return false;
    if (!nextTokenIs(b, BAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_DEFAULT_CASE, null);
    r = consumeToken(b, BAR);
    r = r && consumeToken(b, ELSE);
    p = r; // pin = 2
    r = r && report_error_(b, consumeToken(b, ARROW));
    r = p && report_error_(b, consumeToken(b, GOTO)) && r;
    r = p && LocationSymbol(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // 'switch' VarSymbol SwitchCase* SwitchDefaultCase?
  public static boolean SwitchStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchStatement")) return false;
    if (!nextTokenIs(b, SWITCH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SWITCH);
    r = r && VarSymbol(b, l + 1);
    r = r && SwitchStatement_2(b, l + 1);
    r = r && SwitchStatement_3(b, l + 1);
    exit_section_(b, m, SWITCH_STATEMENT, r);
    return r;
  }

  // SwitchCase*
  private static boolean SwitchStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchStatement_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!SwitchCase(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchStatement_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // SwitchDefaultCase?
  private static boolean SwitchStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchStatement_3")) return false;
    SwitchDefaultCase(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'throw' VarSymbol
  public static boolean ThrowStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ThrowStatement")) return false;
    if (!nextTokenIs(b, THROW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THROW);
    r = r && VarSymbol(b, l + 1);
    exit_section_(b, m, THROW_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // '(' (NUMBER_LITERAL (',' NUMBER_LITERAL)*)? ')'
  public static boolean TupleExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleExpression")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && TupleExpression_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, TUPLE_EXPRESSION, r);
    return r;
  }

  // (NUMBER_LITERAL (',' NUMBER_LITERAL)*)?
  private static boolean TupleExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleExpression_1")) return false;
    TupleExpression_1_0(b, l + 1);
    return true;
  }

  // NUMBER_LITERAL (',' NUMBER_LITERAL)*
  private static boolean TupleExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NUMBER_LITERAL(b, l + 1);
    r = r && TupleExpression_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' NUMBER_LITERAL)*
  private static boolean TupleExpression_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleExpression_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!TupleExpression_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TupleExpression_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' NUMBER_LITERAL
  private static boolean TupleExpression_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TupleExpression_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && NUMBER_LITERAL(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ('@owner'|'@type'|'@classDescriptor') TypeExpression
  public static boolean TypeAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeAnnotation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_ANNOTATION, "<type annotation>");
    r = TypeAnnotation_0(b, l + 1);
    r = r && TypeExpression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '@owner'|'@type'|'@classDescriptor'
  private static boolean TypeAnnotation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeAnnotation_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OWNER_KEY);
    if (!r) r = consumeToken(b, TYPE_KEY);
    if (!r) r = consumeToken(b, CLASS_DESCRIPTOR_KEY);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // APOSTROPHE_ID
  public static boolean TypeDefSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeDefSymbol")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, APOSTROPHE_ID);
    exit_section_(b, m, TYPE_DEF_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // '^' JwType
  public static boolean TypeExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeExpression")) return false;
    if (!nextTokenIs(b, HAT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HAT);
    r = r && JwType(b, l + 1);
    exit_section_(b, m, TYPE_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // '[' ']'
  public static boolean TypeFragment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeFragment")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, TYPE_FRAGMENT, r);
    return r;
  }

  /* ********************************************************** */
  // '[' VarSymbol (',' VarSymbol)* ']'
  public static boolean TypeFragmentWithInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeFragmentWithInit")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && VarSymbol(b, l + 1);
    r = r && TypeFragmentWithInit_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, TYPE_FRAGMENT_WITH_INIT, r);
    return r;
  }

  // (',' VarSymbol)*
  private static boolean TypeFragmentWithInit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeFragmentWithInit_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!TypeFragmentWithInit_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeFragmentWithInit_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' VarSymbol
  private static boolean TypeFragmentWithInit_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeFragmentWithInit_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && VarSymbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // APOSTROPHE_ID
  public static boolean TypeSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSymbol")) return false;
    if (!nextTokenIs(b, APOSTROPHE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, APOSTROPHE_ID);
    exit_section_(b, m, TYPE_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // unary_op VarSymbol
  public static boolean UnaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNARY_EXPRESSION, "<unary expression>");
    r = unary_op(b, l + 1);
    r = r && VarSymbol(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean VarDefSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDefSymbol")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, VAR_DEF_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean VarSymbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSymbol")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, VAR_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // '+' | '-' | '*' | '/' | '%%' | '^&' | '^|' | '^~' | '^<' | '^>' | '^>>'
  static boolean binary_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_op")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADD);
    if (!r) r = consumeToken(b, SUB);
    if (!r) r = consumeToken(b, MUL);
    if (!r) r = consumeToken(b, DIV);
    if (!r) r = consumeToken(b, REM);
    if (!r) r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, OR);
    if (!r) r = consumeToken(b, XOR);
    if (!r) r = consumeToken(b, SHL);
    if (!r) r = consumeToken(b, SHR);
    if (!r) r = consumeToken(b, USHR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '-' | '~'
  static boolean unary_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_op")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUB);
    if (!r) r = consumeToken(b, "~");
    exit_section_(b, m, null, r);
    return r;
  }

}
