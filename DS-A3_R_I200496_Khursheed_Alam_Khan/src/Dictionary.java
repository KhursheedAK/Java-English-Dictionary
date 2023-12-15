/* Khursheed Alam Khan									Assignment: 3
 * 20i-0496											    AVL Dictionary
 * SE-R
 */

public class Dictionary 
{
	private String letter;
	private String meaning;
	
	Dictionary(String letter, String meaning)
	{
		this.letter=letter;
		this.meaning=meaning;
	}

	
	public String getLetter() 
	{
		return letter;
	}

	public void setLetter(String letter) 
	{
		this.letter = letter;
	}

	public String getMeaning() 
	{
		return meaning;
	}

	public void setMeaning(String meaning) 
	{
		this.meaning = meaning;
	}
	
	
	public String toString()
	{
		return "Word: "+this.getLetter()+" | Meaning: "+this.getMeaning();
	}
}
