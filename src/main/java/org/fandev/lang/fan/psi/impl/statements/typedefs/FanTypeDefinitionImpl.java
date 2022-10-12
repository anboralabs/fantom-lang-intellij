package org.fandev.lang.fan.psi.impl.statements.typedefs;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiReferenceList;
import com.intellij.psi.impl.ElementBase;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.IconManager;
import com.intellij.ui.icons.RowIcon;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.VisibilityIcons;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanInheritanceClause;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanReferenceList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinitionBody;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanClassReferenceType;
import org.fandev.lang.fan.psi.impl.synthetic.FanLightIdentifier;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.utils.PsiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FanTypeDefinitionImpl extends FanBaseElementImpl<FanTypeDefinitionStub> implements FanTypeDefinition {
    protected FanField[] fanFields;
    private static final Logger logger = Logger.getInstance("org.fandev.lang.fan.psi.impl.statements.typedefs.FanTypeDefinitionImpl");
    protected FanMethod[] fanMethods;
    protected FanSlot[] fanSlots;

    public FanTypeDefinitionImpl(FanTypeDefinitionStub stubElement, @NotNull IStubElementType iStubElementType) {
        super(stubElement, iStubElementType);
    }

    public FanTypeDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }


    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            public String getPresentableText() {
                return FanTypeDefinitionImpl.this.getName();
            }

            @Nullable
            public String getLocationString() {
                PsiFile file = FanTypeDefinitionImpl.this.getContainingFile();
                if (file instanceof FanFile) {
                    FanFile fanFile = (FanFile) file;

                    return (fanFile.getPodName().length() > 0) ? ("(" + fanFile.getPodName() + ")") : "";
                }
                return "";
            }

            @Nullable
            public Icon getIcon(boolean open) {
                return FanTypeDefinitionImpl.this.getIcon(3);
            }


            @Nullable
            public TextAttributesKey getTextAttributesKey() {
                return null;
            }
        };
    }

    @NotNull
    public PsiClassType[] getExtendsListTypes() {
        List<PsiClassType> extendsTypes = getReferenceListTypes((FanReferenceList) getInheritanceClause());
        return extendsTypes.<PsiClassType>toArray(new PsiClassType[extendsTypes.size()]);
    }

    @Nullable
    public FanInheritanceClause getInheritanceClause() {
        return (FanInheritanceClause) findChildByClass(FanInheritanceClause.class);
    }

    @NotNull
    public PsiClassType[] getImplementsListTypes() {
        return new PsiClassType[0];
    }

    public PsiClass getSuperClass() {
        PsiClassType[] superTypes = getSuperTypes();
        for (PsiClassType psiClassType : superTypes) {
            FanTypeDefinition fanType = (FanTypeDefinition) psiClassType.resolve();
            if (fanType instanceof org.fandev.lang.fan.psi.api.statements.typeDefs.FanClassDefinition) {
                return (PsiClass) fanType;
            }
        }
        if (!"Obj".equals(getName())) {
            return (PsiClass) getFanObjType();
        }
        return null;
    }

    public FanTypeDefinition getSuperType() {
        return (FanTypeDefinition) getSuperClass();
    }

    @NotNull
    public PsiClass[] getSupers() {
        PsiClassType[] superTypes = getSuperTypes();
        List<PsiClass> result = new ArrayList<PsiClass>();
        for (PsiClassType superType : superTypes) {
            PsiClass superClass = superType.resolve();
            if (superClass != null) {
                result.add(superClass);
            }
        }

        return result.<PsiClass>toArray(new PsiClass[result.size()]);
    }

    @NotNull
    public PsiClassType[] getSuperTypes() {
        return getExtendsListTypes();
    }

    public PsiReferenceList getExtendsList() {
        return null;
    }

    public PsiReferenceList getImplementsList() {
        return null;
    }

    private static List<PsiClassType> getReferenceListTypes(@Nullable FanReferenceList list) {
        ArrayList<PsiClassType> types = new ArrayList<PsiClassType>();
        if (list != null) {
            for (FanCodeReferenceElement ref : list.getReferenceElements()) {
                types.add(new FanClassReferenceType(ref));
            }
        }
        return types;
    }

    @NotNull
    public String getPodName() {
        PsiFile psiFile = getContainingFile();
        if (psiFile instanceof FanFile) {
            return ((FanFile) psiFile).getPodName();
        }
        logger.warn("Fantom type '" + getName() + "' is not in a fan file !");
        return "NotFanFile";
    }

    public int getTextOffset() {
        PsiIdentifier identifier = getNameIdentifier();
        return (identifier == null) ? 0 : identifier.getTextRange().getStartOffset();
    }


    public String getName() {
        PsiIdentifier id = getNameIdentifier();
        return (id != null) ? id.getText() : "NO NAME";
    }

    @Nullable
    public PsiIdentifier getNameIdentifier() {
        PsiElement element = null;
        try {
            element = findChildByType(FanElementTypes.NAME_ELEMENT);
        } catch (Exception e) {
            logger.error("Error looking for name element for " + this, e);
        }
        if (element != null) {
            return (PsiIdentifier) new FanLightIdentifier((PsiManager) getManager(), getContainingFile(), element.getTextRange());
        }
        return null;
    }

    public String getQualifiedName() {
        return getPodName() + "::" + getName();
    }

    public PsiModifierList getModifierList() {
        return (PsiModifierList) findChildByClass(FanModifierList.class);
    }

    public boolean hasModifierProperty(String name) {
        return getModifierList().hasModifierProperty(name);
    }

    @Nullable
    public Icon getIcon(int flags) {
        Icon icon = getIconInner();
        boolean isLocked = ((flags & 0x2) != 0 && !isWritable());
        RowIcon rowIcon = IconManager.getInstance().createLayeredIcon(this, icon, PsiUtil.getFlags((PsiModifierListOwner) this, isLocked));
        VisibilityIcons.setVisibilityIcon(getModifierList(), rowIcon);
        return (Icon) rowIcon;
    }

    @NotNull
    public FanMethod[] getFanMethods() {
        if (this.fanMethods == null) {
            FanSlot[] fanSlots = getSlots();
            List<FanMethod> list = new ArrayList<FanMethod>();
            for (FanSlot fanSlot : fanSlots) {
                if (fanSlot instanceof FanMethod) {
                    list.add((FanMethod) fanSlot);
                }
            }
            this.fanMethods = list.<FanMethod>toArray(new FanMethod[list.size()]);
        }
        return this.fanMethods;
    }

    @NotNull
    public FanField[] getFanFields() {
        if (this.fanFields == null) {
            FanSlot[] fanSlots = getSlots();
            List<FanField> list = new ArrayList<FanField>();
            for (FanSlot fanSlot : fanSlots) {
                if (fanSlot instanceof FanField) {
                    list.add((FanField) fanSlot);
                }
            }
            this.fanFields = list.<FanField>toArray(new FanField[list.size()]);
        }
        return this.fanFields;
    }

    @NotNull
    public FanSlot[] getSlots() {
        if (this.fanSlots == null) {
            List<FanSlot> slots = new ArrayList<FanSlot>();
            PsiElement element = findChildByType(getBodyElementType());
            if (element != null) {
                PsiElement[] bodyEls = element.getChildren();
                for (PsiElement bodyEl : bodyEls) {
                    if (bodyEl instanceof FanSlot) {
                        slots.add((FanSlot) bodyEl);
                    }
                }
            }
            this.fanSlots = slots.<FanSlot>toArray(new FanSlot[slots.size()]);
        }
        return this.fanSlots;
    }

    public FanField getFieldByName(String name) {
        FanField[] fields = getFanFields();
        for (FanField field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    public FanMethod getMethodByName(String name) {
        FanMethod[] methods = getFanMethods();
        for (FanMethod method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    @NotNull
    public FanSlot[] getSlots(String modifier) {
        List<FanSlot> slots = new ArrayList<FanSlot>();
        for (FanSlot slot : getSlots()) {
            if (slot.hasModifierProperty(modifier)) {
                slots.add(slot);
            }
        }
        return slots.<FanSlot>toArray(new FanSlot[slots.size()]);
    }

    @NotNull
    public FanMethod[] getFanMethods(String modifier) {
        List<FanMethod> methods = new ArrayList<FanMethod>();
        for (FanMethod method : getFanMethods()) {
            if (method.hasModifierProperty(modifier)) {
                methods.add(method);
            }
        }
        return methods.<FanMethod>toArray(new FanMethod[methods.size()]);
    }

    @NotNull
    public FanField[] getFanFields(String modifier) {
        List<FanField> fields = new ArrayList<FanField>();
        for (FanField field : getFanFields()) {
            if (field.hasModifierProperty(modifier)) {
                fields.add(field);
            }
        }
        return fields.<FanField>toArray(new FanField[fields.size()]);
    }

    public String getJavaQualifiedName() {
        return "fan." + getPodName() + "." + getName();
    }

    public FanTypeDefinitionBody getBodyElement() {
        return (FanTypeDefinitionBody) findChildByType(getBodyElementType());
    }


    public PsiElement addMemberDeclaration(@NotNull PsiElement decl, PsiElement anchorBefore) throws IncorrectOperationException {
        ASTNode anchorNode;
        FanTypeDefinitionBody body = getBodyElement();
        if (body == null) throw new IncorrectOperationException("Type definition without a body");

        if (anchorBefore != null) {
            anchorNode = anchorBefore.getNode();
        } else {
            PsiElement child = body.getLastChild();
            assert child != null;
            anchorNode = child.getNode();
        }
        ASTNode bodyNode = body.getNode();
        bodyNode.addChild(decl.getNode(), anchorNode);
        bodyNode.addLeaf(FanTokenTypes.WHITE_SPACE, " ", decl.getNode());
        bodyNode.addLeaf(FanTokenTypes.WHITE_SPACE, " ", anchorNode);
        return decl;
    }

    protected abstract Icon getIconInner();

    protected abstract IElementType getBodyElementType();
}