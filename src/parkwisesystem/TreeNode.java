package parkwisesystem;

public class TreeNode<E extends Comparable<E>> {
    protected E element;
    protected TreeNode<E> left, right;
    
    public TreeNode(E e) {
        element = e;
    }
    
    public boolean hasLeft () { return left != null;}
    public boolean hasRight () { return right != null;}
    public boolean hasTwoChildren () { return left != null && right != null;}
    public boolean isLeaf () { return left == null && right == null;} 
    
    public void print() {
        print("", this, false);
    }
    
    public void print(String prefix, TreeNode<E> n, boolean isLeft) {
        if (n != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "└-- ") + n.element);
            if (n.left != null || n.right != null) {
                print(prefix + (isLeft ? "|  " : "    "), n.left, true);
                print(prefix + (isLeft ? "|   " : "    "), n.right, false);
            }
        }
    }

} // end of class
