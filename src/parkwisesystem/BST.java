package parkwisesystem_sec51_g4;

/*
*  CSC301 Assignment 3
*  Done by: Zayna Wasma (1084503)
*/

public class BST_Sec51_G4<E extends Comparable<E>> extends AbstractTree_Sec51_G4<E> {
    protected TreeNode_Sec51_G4<E> root;
    protected int size = 0;
    
    public void print() {
        print("", this.root, true);
    }
    
    public void print(String prefix, TreeNode_Sec51_G4<E> n, boolean isLeft) {
        if (n != null) {
            System.out.println(prefix + (isLeft ? "|--" : " └--") + n.element);
            print(prefix + (isLeft ? "|   " : "    "), n.right, true);
            print(prefix + (isLeft ? "|  " : "   "), n.left, false);
        }
    }
    
    /* Create a default binary search tree */
    public BST_Sec51_G4() { }
    
    /* Create a binary search tree from an array of objects */
    public BST_Sec51_G4(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }
    
    @Override /* Return true if the element is in the tree */
    public boolean search(E e) {
        TreeNode_Sec51_G4<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) 
                current = current.left;
            else if (e.compareTo(current.element) > 0) 
                current = current.right;
            else // element matches current.element
                return true; // Element is found
        }
        return false;
    }
    
    protected TreeNode_Sec51_G4<E> createNewNode(E e) {
        return new TreeNode_Sec51_G4<>(e);
    }
    
    @Override /* Insert element e into the binary search tree.
  	       * Return true if the element is inserted successfully. */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode_Sec51_G4<E> parent = null;
            TreeNode_Sec51_G4<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false; // Duplicate node not inserted

                // Create the new node and attach it to the parent node
                if (e.compareTo(parent.element) < 0)
                    parent.left = createNewNode(e);
                else
                    parent.right = createNewNode(e);
        }
        size++; 
	return true; // Element inserted successfully
    }
    
    @Override /* Inorder traversal from the root */
    public void inorder() {
        inorder(root);
    }
    
    /* Inorder traversal from a subtree */
    protected void inorder(TreeNode_Sec51_G4<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print("\t (" + root.element + ") ");
        inorder(root.right);
    }
    
    @Override /* Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }
    
    /* Postorder traversal from a subtree */
    protected void postorder(TreeNode_Sec51_G4<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print("\t (" + root.element + ") ");
    }
    
    @Override /* Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }
    
    /* Preorder traversal from a subtree */
    protected void preorder(TreeNode_Sec51_G4<E> root) {
        if (root == null) return;
        System.out.print("\t (" + root.element + ") ");
        preorder(root.left);
        preorder(root.right);
    }
    
    @Override /* Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }
    
    /* Returns the root of the tree */
    public TreeNode_Sec51_G4<E> getRoot() {
        return root;
    }
    
    /* Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
    
    /* Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode_Sec51_G4<E>> path(E e) {
        java.util.ArrayList<TreeNode_Sec51_G4<E>> list = new java.util.ArrayList<>();
        TreeNode_Sec51_G4<E> current = root; // Start from the root
	boolean found = false;
	while (current != null && !found) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) 
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else found = true;
        }
        return list; // Return an array list of nodes
    }
    
    @Override /* Delete an element from the binary search tree.
               * Return true if the element is deleted successfully.
               * Return false if the element is not in the tree. */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
	TreeNode_Sec51_G4<E> parent = null;
	TreeNode_Sec51_G4<E> current = root; 
	boolean found = false;
	while (current != null && !found) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                found = true ; // Element is in the tree pointed at by current
        }
        
        if (found) {
            // Case 1: current has no left child
            if (current.left == null) {
                // Connect the parent with the right child of the current node
                if (parent == null) 
                    root = current.right;
                else if (e.compareTo(parent.element) < 0)
                    parent.left = current.right; 
                else
                    parent.right = current.right; 
            } else {
                // Case 2: The current node has a left child.   
                // Locate the rightmost node in the left subtree of
                // the current node and also its parent.
	        TreeNode_Sec51_G4<E> parentOfRightMost = current; 
	        TreeNode_Sec51_G4<E> rightMost = current.left; 
                
                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // Keep going to the right
	        }
	
	        // Replace the element in current by the element in rightMost
	        current.element = rightMost.element; 
	
	        // Eliminate rightmost node
	        if (parentOfRightMost.right == rightMost)
                    parentOfRightMost.right = rightMost.left;
	        else
                    // Special case: parentOfRightMost == current
                    parentOfRightMost.left = rightMost.left;
            }
            size--;
        }
        return found; // Element deleted successfully
    }
    
    @Override /* Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list
        
        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }
        
        /* Inorder traversal from the root*/
        private void inorder() {
            inorder(root); 
        }
        
        /* Inorder traversal from a subtree */
        private void inorder(TreeNode_Sec51_G4<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }
        
        @Override /* More elements for traversing? */
        public boolean hasNext() {
            if (current < list.size())
                return true;
            return false;
        }
        
        @Override /* Get the current element and move to the next */
        public E next() {
            return list.get(current++);
        }
        
        @Override /* Remove the current element */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    } // end of interator class
} // end of bst class