/* Khursheed Alam Khan									Assignment: 3
 * 20i-0496											    AVL Dictionary
 * SE-R
 */
import java.io.IOException;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) throws IOException 
	
	{
		TreeAVL avl = new TreeAVL();
		
		Scanner cin = new Scanner(System.in);
		Scanner in = new Scanner(System.in);
		
		int option;
		
		System.out.println("-------------------------Welcome to Khursheed's AVL Tree Dictionary---------------------------------------");
		System.out.println();
		
		// stores words from files to AVL tree
		avl.start();
		
		do
		{
			System.out.println("1. Display AVL dictionary Tree in Pre-Order");
			System.out.println("2. Display AVL dictionary Tree in In-Order");
			System.out.println("3. Display AVL dictionary Tree in Post-Order");
			System.out.println("4. Find meaning of a word");
			System.out.println("5. Delete a word");
			System.out.println("6. Write/Save file in a seperate 'Pre Order Save.txt' file");
			System.out.println("7. Write/Save file in the same 'Dictionary.txt' file");
			System.out.println("8. Check AVL Tree balance factor");
			System.out.println("9. Quit");
			
			System.out.println();
			
			System.out.println("Enter your choice: ");
			option = cin.nextInt();
			
			switch(option)
			{
				case 1:
				{
					avl.preOrder(avl.root);
					System.out.println();
					break;
				}
				
				case 2:
				{
					avl.inOrder(avl.root);
					System.out.println();
					break;
				}
				
				case 3:
				{
					avl.postOrder(avl.root);
					System.out.println();
					break;
				}
				
				case 4:
				{
					System.out.println();
					System.out.println("Enter word: ");
					String word = in.nextLine();
					
					avl.ifNodePresent(avl.root, word);
					System.out.println();
					
					break;
				}
				
				case 5:
				{
					System.out.println();
					System.out.println("Enter word to delete: ");
					String word = in.nextLine();
					
					avl.deleteNode(avl.root, word);
					System.out.println();
					break;
				}
				
				case 6:
				{
					if(avl.preOrder.exists())
					{
						avl.preOrder.delete();
					}
						System.out.println();
						avl.writePreOrder(avl.root, avl.bw);
						System.out.println();
	
					break;
				}
				
				case 7: 
				{
					if(avl.word.exists())
					{
						avl.word.delete();
					}
						System.out.println();
						avl.writeFile(avl.root, avl.bw2);
						System.out.println();
					break;
				}
				
				case 8: 
				{
					System.out.println();
					avl.showBalance(avl.root);
					System.out.println();
					break;
				}
				
				case 9: 
				{
					// system quits
					break;
				}
				
				default:
				{
					System.out.println("Wrong option selected !!");
					break;
				}
			}
			
			
		}while(option!=9);
		
		System.out.println();
		System.out.println("Thanks for reading Khursheed's dictionary. Hope you come back to improve your vocabulary.");
		

	}

}
