/*     */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.openapi.util.Key;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import com.intellij.psi.tree.TokenSet;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.FanTokenTypes;
/*     */ import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
/*     */ import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
/*     */ import org.fandev.lang.fan.parsing.expression.Expression;
/*     */ import org.fandev.lang.fan.parsing.statements.Block;
/*     */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*     */ import org.fandev.lang.fan.parsing.types.TypeSpec;
/*     */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FieldDefinition
/*     */ {
/*  50 */   public static final Key<String> FIELD_NAME = new Key("fan.parser.fieldName");
/*     */   
/*  52 */   private static final TokenSet FIELD_DEF_STOPPER = TokenSet.create(new IElementType[] { FanTokenTypes.SEMICOLON, FanTokenTypes.NLS, FanTokenTypes.LBRACE });
/*     */   
/*     */   public static boolean parse(PsiBuilder builder) {
/*  55 */     PsiBuilder.Marker declMarker = builder.mark();
/*     */     
/*  57 */     Facet.parse(builder);
/*     */     
/*  59 */     TokenSet modifiers = Modifiers.parse(builder, DeclarationType.FIELD);
/*  60 */     boolean modifiersParsed = ((modifiers.getTypes()).length > 0);
/*     */     
/*  62 */     PsiBuilder.Marker beforeType = builder.mark();
/*  63 */     if (!TypeSpec.parse(builder)) {
/*  64 */       declMarker.drop();
/*  65 */       return false;
/*     */     } 
/*     */     
/*  68 */     if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/*     */       
/*  70 */       beforeType.rollbackTo();
/*     */     } else {
/*  72 */       beforeType.drop();
/*     */     } 
/*  74 */     if (!ParserUtils.parseName(builder)) {
/*  75 */       declMarker.drop();
/*  76 */       return false;
/*     */     } 
/*     */     
/*  79 */     boolean hasInitValue = false;
/*     */     
/*  81 */     IElementType firstTokenAfter = ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS });
/*     */     
/*  83 */     if (FanTokenTypes.COLON_EQ.equals(firstTokenAfter)) {
/*  84 */       ParserUtils.removeNls(builder);
/*  85 */       ParserUtils.advanceNoNls(builder);
/*  86 */       builder.putUserData(FIELD_NAME, "on");
/*  87 */       Expression.parseExpr(builder, FIELD_DEF_STOPPER, FanElementTypes.FIELD_DEFAULT);
/*  88 */       builder.putUserData(FIELD_NAME, null);
/*  89 */       firstTokenAfter = ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS });
/*  90 */       hasInitValue = true;
/*     */     } 
/*     */ 
/*     */     
/*  94 */     if (FanTokenTypes.LBRACE.equals(firstTokenAfter)) {
/*  95 */       ParserUtils.removeNls(builder);
/*  96 */       if (hasInitValue);
/*     */ 
/*     */       
/*  99 */       PsiBuilder.Marker getterSetter = builder.mark();
/*     */       
/* 101 */       ParserUtils.advanceNoNls(builder);
/* 102 */       parseGetterSetter(builder, getterSetter);
/*     */     } 
/*     */     
/* 105 */     if (builder.eof() || !FanTokenTypes.EOS.contains(builder.getTokenType())) {
/* 106 */       declMarker.error(FanBundle.message("separator.expected", new Object[0]));
/* 107 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 111 */     ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/*     */     
/* 113 */     declMarker.done(FanElementTypes.FIELD_DEFINITION);
/*     */     
/* 115 */     return true;
/*     */   }
/*     */   
/*     */   private static void parseGetterSetter(PsiBuilder builder, PsiBuilder.Marker getterSetter) {
/* 119 */     if (testEndGetterSetter(builder, getterSetter)) {
/*     */       return;
/*     */     }
/* 122 */     PropertyBlock blockType = findPropertyBlockType(builder);
/* 123 */     if (blockType == PropertyBlock.GETTER) {
/* 124 */       parseGetBlock(builder);
/* 125 */       if (testEndGetterSetter(builder, getterSetter)) {
/*     */         return;
/*     */       }
/* 128 */       blockType = findPropertyBlockType(builder);
/*     */     } 
/* 130 */     if (blockType == PropertyBlock.SETTER) {
/* 131 */       ParserUtils.removeNls(builder);
/* 132 */       PsiBuilder.Marker defMark = builder.mark();
/* 133 */       TokenSet modifiers = Modifiers.parse(builder, DeclarationType.INNER_SET);
/*     */       
/* 135 */       if (ParserUtils.parseName(builder)) {
/* 136 */         parseGetSetBlock(builder);
/* 137 */         defMark.done(FanElementTypes.SETTER_FIELD_DEFINITION);
/*     */       }
/* 139 */       else if ((modifiers.getTypes()).length > 0) {
/* 140 */         defMark.error("Found modifiers for setter but no set");
/*     */       } else {
/* 142 */         defMark.error("set block error");
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     if (!testEndGetterSetter(builder, getterSetter)) {
/* 147 */       getterSetter.error("Did not find } or <eos>");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static PropertyBlock findPropertyBlockType(PsiBuilder builder) {
/* 154 */     PropertyBlock blockType = PropertyBlock.NONE;
/* 155 */     PsiBuilder.Marker rb = builder.mark();
/* 156 */     ParserUtils.removeNls(builder);
/*     */     
/* 158 */     if (FanTokenTypes.LBRACE == builder.getTokenType()) {
/* 159 */       ParserUtils.advanceNoNls(builder);
/*     */     }
/* 161 */     while (!builder.eof()) {
/* 162 */       if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 163 */         String anId = builder.getTokenText();
/* 164 */         if ("get".equals(anId)) {
/* 165 */           blockType = PropertyBlock.GETTER;
/*     */           break;
/*     */         } 
/* 168 */         if ("set".equals(anId)) {
/* 169 */           blockType = PropertyBlock.SETTER;
/*     */           
/*     */           break;
/*     */         } 
/* 173 */         ParserUtils.advanceNoNls(builder); continue;
/* 174 */       }  if (FanTokenTypes.ALL_MODIFIERS.contains(builder.getTokenType()) || FanTokenTypes.NLS == builder.getTokenType())
/*     */       {
/* 176 */         ParserUtils.advanceNoNls(builder);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 182 */     rb.rollbackTo();
/* 183 */     return blockType;
/*     */   }
/*     */   
/*     */   private static boolean parseGetSetBlock(PsiBuilder builder) {
/* 187 */     ParserUtils.removeNls(builder);
/* 188 */     if (FanTokenTypes.LBRACE.equals(builder.getTokenType()))
/* 189 */       return Block.parse(builder, FanElementTypes.METHOD_BODY); 
/* 190 */     if (FanTokenTypes.SEMICOLON.equals(builder.getTokenType()))
/*     */     {
/* 192 */       builder.advanceLexer();
/*     */     }
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean testEndGetterSetter(PsiBuilder builder, PsiBuilder.Marker getterSetter) {
/* 203 */     IElementType firstAfter = ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS });
/* 204 */     if (FanTokenTypes.RBRACE.equals(firstAfter)) {
/* 205 */       ParserUtils.removeNls(builder);
/*     */       
/* 207 */       builder.advanceLexer();
/* 208 */       getterSetter.done(FanElementTypes.GETTER_SETTER_FIELD_DEFINITION);
/* 209 */       return true;
/*     */     } 
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean parseGetBlock(PsiBuilder builder) {
/* 216 */     PsiBuilder.Marker defMark = builder.mark();
/* 217 */     boolean res = ParserUtils.parseName(builder);
/* 218 */     if (res) {
/* 219 */       res = parseGetSetBlock(builder);
/*     */     }
/* 221 */     defMark.done(FanElementTypes.GETTER_FIELD_DEFINITION);
/* 222 */     if (!res) {
/* 223 */       builder.error("Expected get block");
/*     */     }
/* 225 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/members/FieldDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */