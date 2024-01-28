package parkwisesystem_sec51_g4;

/*
*  CSC301 Assignment 3
*  Done by: Zayna Wasma (1084503)
*/

public class BST_Integer_Sec51_G4 extends BST_Sec51_G4<Integer> {
    
    public BST_Integer_Sec51_G4(Integer[] array) {
        super(array);
    }
    
    public Integer sum() {
        return sum(root);
    }
    
    protected Integer sum(TreeNode_Sec51_G4<Integer> root) {
        if (root == null) return 0;
        return root.element + sum(root.left)+ sum(root.right);
    }
    
    public Integer sumLeaves() {
        return sumLeaves(root);
    }
    
    protected Integer sumLeaves(TreeNode_Sec51_G4<Integer> node) {
        if (node == null) return 0;
        if (node.isLeaf()) return node.element;
        return sumLeaves(node.left)+ sumLeaves(node.right);
    }

    // CountOdd: counts number of nodes storing odd values
    public Integer countOdd() {
        return countOdd(root);
    }
    
    protected Integer countOdd(TreeNode_Sec51_G4<Integer> node) {
        if (node == null) return 0;
        if (node.element % 2 != 0) // it is odd
            return 1 + countOdd(node.left) + countOdd(node.right);
        else return 0 + countOdd(node.left) + countOdd(node.right);

        //simplified code
        //return ((node.element % 2 != 0) ? 1 : 0) + countOdd(node.left) + countOdd(node.right);
    }
} // end of class










