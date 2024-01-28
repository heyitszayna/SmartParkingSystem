package parkwisesystem_sec51_g4;

/*
*  CSC301 Assignment 3
*  Done by: Zayna Wasma (1084503)
*/

public abstract class AbstractTree_Sec51_G4<E> implements Tree_Sec51_G4<E> {
    
    @Override /** Inorder traversal from the root*/
    public void inorder() {}
    
    @Override /** Postorder traversal from the root */
    public void postorder() {}
    
    @Override /** Preorder traversal from the root */
    public void preorder() {}
    
    @Override /** Return true if the tree is empty */
    public boolean isEmpty() { return getSize() == 0; }
    
} // end of class

