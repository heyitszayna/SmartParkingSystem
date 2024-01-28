package parkwisesystem_sec51_g4;

/*
*  CSC301 Assignment 3
*  Done by: Zayna Wasma (1084503)
*/

public class TreeNode_Sec51_G4<E extends Comparable<E>> {
    protected E element;
    protected TreeNode_Sec51_G4<E> left, right;
    
    public TreeNode_Sec51_G4(E e) {
        element = e;
    }
    
    public boolean hasLeft () { return left != null;}
    public boolean hasRight () { return right != null;}
    public boolean hasTwoChildren () { return left != null && right != null;}
    public boolean isLeaf () { return left == null && right == null;} 
    
    public void print() {
        print("", this, false);
    }
    
    public void print(String prefix, TreeNode_Sec51_G4<E> n, boolean isLeft) {
        if (n != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "└-- ") + n.element);
            if (n.left != null || n.right != null) {
                print(prefix + (isLeft ? "|  " : "    "), n.left, true);
                print(prefix + (isLeft ? "|   " : "    "), n.right, false);
            }
        }
    }

} // end of class
