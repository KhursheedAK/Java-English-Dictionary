/* Khursheed Alam Khan									Assignment: 3
 * 20i-0496											    AVL Dictionary
 * SE-R
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TreeAVL 
{
	public File word = new File("Dictionary.txt");
	
	public File preOrder = new File("Pre Order Save.txt");
	
	public BufferedWriter bw;
	public BufferedWriter bw2;
	Dictionary d = new Dictionary(null, null);
	
	TreeNodeAVL root;
	
//-------------------------------------------------------------------------- File Handling Functions-----------------------------------------------------------------------
	
	// 1) storing words into AVL tree from the file
	public void start() throws IOException
	{
		if(word.exists()==true)
		{
			try 
			{
			// reading and storing
			Scanner s = new Scanner(word);

			while(s.hasNextLine())
			{

				String data = s.nextLine();
				String[] info = data.split(":");
				d.setLetter(info[0]);
				d.setMeaning(info[1]);
			
					Dictionary newD = new Dictionary(d.getLetter(), d.getMeaning());
					
					// Storing in BST
					root = insert(root, newD);			
			}

			s.close();
			// completed
			
			System.out.println("Successfully stored dictionary from Dictionary.txt to AVL Tree !!!");
			System.out.println();
		}
			catch(NullPointerException e)
			{
				System.out.println(e);
			}
		}
		else
		{
			System.out.println("no file found!!!");
		}
	}
	
	// 2) writing to file in Pre-order
	public void writePreOrder(TreeNodeAVL node, BufferedWriter bw) throws IOException
	{	
		bw = new BufferedWriter(new FileWriter(preOrder, true));
		
		if(node != null)
		{
			try {
			
				bw.write(node.data.getLetter()+":");
				bw.write(node.data.getMeaning());
				bw.newLine();

				writePreOrder(node.left, bw);
				writePreOrder(node.right, bw);
				
				bw.flush();
				bw.close();
				
			}catch(NullPointerException e)
			{
				System.out.println("Error !!");
			}
		}
		bw.close();
		
	}
	
	// 3) writing to same file
		public void writeFile(TreeNodeAVL node, BufferedWriter bw2) throws IOException
		{	
			bw2 = new BufferedWriter(new FileWriter(word, true));
			
			if(node != null)
			{
				try {
				
					bw2.write(node.data.getLetter()+":");
					bw2.write(node.data.getMeaning());
					bw2.newLine();

					
					writeFile(node.left, bw2);
					writeFile(node.right, bw2);
					
					bw2.flush();
					bw2.close();
				}catch(NullPointerException e)
				{
					System.out.println("Error !!");
				}
			}
			bw2.close();
			
		}
		
	
	//-------------------------------------------------------------------------- Core Functionalities of AVL Tree --------------------------------------------------------------------------
	
	// 1) inserting word into AVL tree
	TreeNodeAVL insert(TreeNodeAVL node, Dictionary text)
	{
		if(node == null)
		{
			return (new TreeNodeAVL(text));
		}
		
		if(text.getLetter().compareToIgnoreCase(node.data.getLetter()) < 0)
		{
			node.left = insert(node.left, text);
		}
		
		else if(text.getLetter().compareToIgnoreCase(node.data.getLetter()) > 0)
		{
			node.right = insert(node.right, text);
		}
		
		else
		{
			return node;
		}
		
		// updating the height
		node.height = max(height(node.left), height(node.right)) + 1;
		
		// checking balance factor
		int balance = checkBalance(node);
		
		// Unbalanced tree has the 4 cases:
		
		// Left-Left heavy 
		if(balance > 1 && text.getLetter().compareToIgnoreCase(node.left.data.getLetter()) < 0)
		{
			return rotateRight(node);
		}
		
		// Right-Right heavy
		if(balance < -1 && text.getLetter().compareToIgnoreCase(node.right.data.getLetter()) > 0)
		{
			return rotateLeft(node);
		}
		
		// Left-Right heavy
		 if (balance > 1 && text.getLetter().compareToIgnoreCase(node.left.data.getLetter()) > 0) 
		 {
	            node.left = rotateLeft(node.left);
	            return rotateRight(node);
	     }
		 
		 // Right-Left heavy
        if (balance < -1 && text.getLetter().compareToIgnoreCase(node.right.data.getLetter()) < 0) 
        {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        
        return node;
		
	}
	
	// 2) Pre-Order Print
    void preOrder(TreeNodeAVL node) 
    {
        if (node != null) 
        {
            System.out.println(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    // 3) In-Order Print
    void inOrder(TreeNodeAVL node) 
    {
        if (node != null) 
        {
            preOrder(node.left);
            System.out.println(node.data + " ");
            preOrder(node.right);
        }
    }
    
    // 4) Post-Order print
    void postOrder(TreeNodeAVL node) 
    {
        if (node != null) 
        {
            preOrder(node.left);
            preOrder(node.right);
            System.out.println(node.data + " ");
        }
    }
    
    // 5) Search for the word & its meaning
	public void ifNodePresent(TreeNodeAVL node, String text)
	{
		if(node == null)
		{
			System.out.println("List Empty !!!");
		}
		
		boolean isPresent = false;
		
		while(node != null)
		{
			if(text.compareToIgnoreCase(node.data.getLetter()) < 0)
			{
				node = node.left;
			}
			else if(text.compareToIgnoreCase(node.data.getLetter()) > 0)
			{
				node = node.right;
			}
			else
			{
				isPresent=true;
				System.out.println(node.data);
				break;
			}
		}
		if(isPresent == false)
		{
			System.out.println("No such word '"+text+"' exists !!!");
		}
	}
	
	// 6) Search helper function to print if element is found or not
	public boolean ifNodePresentHelper(TreeNodeAVL node, String text)
	{
		if(node == null)
		{
			return false;
		}
		
		boolean isPresent = false;
		
		while(node != null)
		{
			if(text.compareToIgnoreCase(node.data.getLetter()) < 0)
			{
				node = node.left;
			}
			else if(text.compareToIgnoreCase(node.data.getLetter()) > 0)
			{
				node = node.right;
			}
			else
			{
				isPresent=true;
				break;
			}
		}
		
		return isPresent;
	}
	
	// 7) Returns the height of the tree
	int height(TreeNodeAVL node)
	{
		if(node == null)
		{
			return 0;
		}
		return node.height;
	}
	
	// 8) Returns the maximum between two entities
	int max(int val, int val2)
	{
		if(val > val2)
		{
			return val;
		}
		else
		{
			return val2;
		}
	}
	
	// 9) Single Rotation of tree: Right
	TreeNodeAVL rotateRight(TreeNodeAVL K1)
	{
		TreeNodeAVL K2 = K1.left;
		K1.left=K2.right;
		K2.right=K1;
		
		K1.height = max(height(K1.left), height(K1.right)) + 1;
		K2.height = max(height(K2.left), K1.height) + 1;
		
		return K2;
	}
	
	// 10) Single Rotation of tree: Left
		TreeNodeAVL rotateLeft(TreeNodeAVL K1)
		{
			TreeNodeAVL K2 = K1.right;
			K1.right=K2.left;
			K2.left=K1;
			
			K1.height = max(height(K1.left), height(K1.right)) + 1;
			K2.height = max(height(K2.right), height(K2.right)) + 1;
			
			return K2;
		} 
		
	// 11) Balance Factor Checker
		int checkBalance(TreeNodeAVL node)
		{
			if(node == null)
			{
				return 0;
			}
			
			else
			{
				return (height(node.left) - height(node.right));
			}
		}
		
		
	// 12) Returns minimum node in the AVL tree for balancing after deletion
		TreeNodeAVL minNode(TreeNodeAVL node)
		{
			TreeNodeAVL nodePtr = node; // traverser node
			
			// the loop locates the leaf to the extreme left of the AVL tree
			while(nodePtr.left!=null)
			{
				nodePtr = nodePtr.left;
			}
			
			return nodePtr;
		}
			
		
	// 13) Deletion of Word
		TreeNodeAVL deleteNode(TreeNodeAVL root, String text)
		{
			boolean flag = ifNodePresentHelper(root, text);
			
	        if(flag==true)
	        {
		        // text < root.data so left
		        if (text.compareToIgnoreCase(root.data.getLetter()) < 0)
		        {
		            root.left = deleteNode(root.left, text);
		        }
		        
		        // text > root.data so right
		        else if (text.compareToIgnoreCase(root.data.getLetter()) > 0)
		        {
		            root.right = deleteNode(root.right, text);
		        }
		        
		        // text found so deletion process begins
		        else if(root!=null)
		        {
		 
		            // case 1: Node with 1 or 0 child
		            if ((root.left == null) || (root.right == null))
		            {
		                TreeNodeAVL temp = null;
		                
		                if (temp == root.left)
		                {
		                    temp = root.right;
		                
		                }
		                else
		                {
		                    temp = root.left;
		                }
		                
		                // 1.1: Zero child 
		                if (temp == null)
		                {
		                    temp = root;
		                    root = null;
		                }
		             
		                // 1.2: one child
		                else 
		                {
		                    root = temp; 
		                }
		            }
		            
		            else
		            {
		 
		                // case 2: Two Children
		                TreeNodeAVL temp = minNode(root.right);
		 
		                root.data = temp.data;
		 
		                root.right = deleteNode(root.right, temp.data.getLetter());
		            }
		        }
		        
		     // If the tree had only one node then return
		        if (root == null)
		            return root;
		        
		        // current node height updated
		        root.height = max(height(root.left), height(root.right)) + 1;
		 
		        // Check balance factor of current node
		        int balance = checkBalance(root);
		 
		        // if tree is Unbalanced TreeLeft then: 
		        
		        // case 1:  Left-Left heavy
		        if (balance > 1 && checkBalance(root.left) >= 0)
		        {
		            return rotateRight(root);
		        }
		        
		        // case 2: Left-Right heavy
		        if (balance > 1 && checkBalance(root.left) < 0)
		        {
		            root.left = rotateLeft(root.left);
		            return rotateRight(root);
		        }
		 
		        // case 3: Right-Right heavy
		        if (balance < -1 && checkBalance(root.right) <= 0)
		            return rotateLeft(root);
		 
		        // case 4: Right-Left heavy
		        if (balance < -1 && checkBalance(root.right) > 0)
		        {
		            root.right = rotateRight(root.right);
		            return rotateLeft(root);
		        }
		        
		        return root;
	        }
	        else
	        {
	        	System.out.println("No such word Found !!!");
	        }
	        
	        if(flag==true)
	        {
	        	System.out.println("Successfully Deleted !!!");
	        }
	        return root;
	    }
		
		
	// 14) Show Balance factor of the Tree
		public void showBalance(TreeNodeAVL node)
		{
			int h1, h2;
			
			h1 = height(node.left);
			h2 = height(node.right);
			
			int value = (h1 - h2);
			
			if(value > 1 || value < -1)
			{
				if(value > 1)
				{
					System.out.println("Balance factor is: "+value);
					System.out.println("Tree not balanced !");
				}
				
				else if(value < -1)
				{
					System.out.println("Balance factor is: "+value);
					System.out.println("Tree not balanced !");
				}
			}
			
			else if(value == -1 || value == 0 || value == 1)
			{
				if(value == -1)
				{
					System.out.println("Balance factor is: "+value);
					System.out.println("Tree is balanced !");
				}
				
				else if(value == 0)
				{
					System.out.println("Balance factor is: "+value);
					System.out.println("Tree balanced !");
				}
				
				else if(value == 1)
				{
					System.out.println("Balance factor is: "+value);
					System.out.println("Tree is balanced !");
				}
			}
		}
		
		
		// Checking method
		TreeNodeAVL DeleteNode(TreeNodeAVL root, String text)
	    {
			
			boolean flag = ifNodePresentHelper(root, text);
		
	        if(flag==true)
	        {
	        	// text < parent text so must lie in left sub-tree
		        if (text.compareToIgnoreCase(root.data.getLetter()) < 0)
		        {
		            root.left = deleteNode(root.left, text);
		        }
		       // text > parent text so must lie in right sub-tree
		        else if (text.compareToIgnoreCase(root.data.getLetter()) > 0)
		        {
		            root.right = deleteNode(root.right, text);
		        }
		        // text found as parent text so this function runs
		        else if(root!=null)
		        {
		 
		            // Case with 0 or 1 child:
		            if ((root.left == null) || (root.right == null))
		            {
		                TreeNodeAVL temp = null;
		                if (temp == root.left)
		                {
		                    temp = root.right;
		                }
		                else
		                {
		                    temp = root.left;
		                }
		                
		                // Zero child case
		                if (temp == null)
		                {
		                    temp = root;
		                    root = null;
		                }
		                else // One child case
		                {
		                	root = temp;
		                }
		            }
		            else
		            {
		 
		                // two children case: in-order successor: smallest in the right sub-tree
		                TreeNodeAVL temp = minNode(root.right);
		 
		                // Copy the in-order successor's data to this node
		                root.data = temp.data;
		 
		                // Delete the in-order successor
		                root.right = deleteNode(root.right, temp.data.getLetter());
		            }
		        }
		 
		        // Tree has one node, then return
		        if (root == null)
		        {
		            return root;
		        }
		        // updates height of current node
		        root.height = max(height(root.left), height(root.right)) + 1;
		 
		        //check balance factor of current node
		        int balance = checkBalance(root);
		 
		        // Node unbalanced: then 4 cases determined
		        
		        // Left-Left heavy
		        if (balance > 1 && checkBalance(root.left) >= 0)
		        {
		            return rotateRight(root);
		        }
		        // Left-Right heavy
		        if (balance > 1 && checkBalance(root.left) < 0)
		        {
		            root.left = rotateLeft(root.left);
		            return rotateRight(root);
		        }
		 
		        // Right-Right heavy
		        if (balance < -1 && checkBalance(root.right) <= 0)
		        {
		            return rotateLeft(root);
		        }
		        // Right-Left heavy
		        if (balance < -1 && checkBalance(root.right) > 0)
		        {
		            root.right = rotateRight(root.right);
		            return rotateLeft(root);
		        }
		        
		        return root;
	        }
	        else
	        {
	        	System.out.println("No such Element Found !!!");
	        }
	        return root;
	    }
}