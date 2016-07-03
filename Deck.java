import java.util.ArrayList;
public class Deck
{ 
    ArrayList <Integer> myDeck;
    /**
     * creates empty array to symbolize the deck
     */
    public Deck()
    {
        myDeck = new ArrayList<Integer>();
    }
    /**
     * adds card to deck array to show that it has been picked
     */
    public void Remove(int cardVal)// card has been used
    {
        myDeck.add(cardVal);//cardVal ranges from 1 - 14
    }
    /**
     * checks to see if card has been used, if it has it isn't valid
     */
    public boolean Valid(int cardNum, int numDecks)//checks to see if the card has been used 
    {
        boolean valid = true;
        int used = 0;
        /*if(cardNum == 14)//changes aces to one variable
        {
            cardNum = 1;
        }*/
        
        for (int x = 0; x < myDeck.size(); x++)
        {
            if(myDeck.get(x) == cardNum)
            {
                used++;
            }
            if (used > 1*numDecks)
            {
                valid = false;
            }
        }
        return valid;
    }
    /**
     * empties the deck arraylist
     */
    public void Shuffle()//no cards have been used 
    {
        myDeck = new <Integer> ArrayList();
    }
}