/* Khursheed Alam Khan									Assignment: 3
 * 20i-0496											    AVL Dictionary
 * SE-R
 */

public class TreeNodeAVL 
{
	Dictionary data;
	TreeNodeAVL right;
	TreeNodeAVL left;
	int height;
	
	TreeNodeAVL(Dictionary data)
	{
		this.data=data;
		height=1;
	}
}
