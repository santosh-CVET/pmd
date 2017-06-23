/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
/* Generated By:JJTree: Do not edit this line. ASTClassOrInterfaceDeclaration.java */

package net.sourceforge.pmd.lang.java.ast;

public class ASTClassOrInterfaceDeclaration extends AbstractJavaAccessTypeNode implements ASTAnyTypeDeclaration {

    private boolean isInterface;
    private QualifiedName qualifiedName;

    public ASTClassOrInterfaceDeclaration(int id) {
        super(id);
    }

    public ASTClassOrInterfaceDeclaration(JavaParser p, int id) {
        super(p, id);
    }

    @Override
    public boolean isFindBoundary() {
        return isNested();
    }

    /**
     * Accept the visitor. *
     */
    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public boolean isNested() {
        return jjtGetParent() instanceof ASTClassOrInterfaceBodyDeclaration
            || jjtGetParent() instanceof ASTAnnotationTypeMemberDeclaration;
    }

    public boolean isInterface() {
        return this.isInterface;
    }

    public void setInterface() {
        this.isInterface = true;
    }

    @Override
    public QualifiedName getQualifiedName() {
        if (qualifiedName == null) {
            if (isNested()) {
                ASTClassOrInterfaceDeclaration parent = this.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
                QualifiedName parentQN = parent.getQualifiedName();
                qualifiedName = QualifiedName.makeNestedClassOf(parentQN, this.getImage());
                return qualifiedName;
            }

            qualifiedName = QualifiedName.makeOuterClassOf(this);
        }

        return qualifiedName;
    }
}
