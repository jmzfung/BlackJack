import java.util.Random;
public class Card//data is only kept temporarily and is stored in the driver
{
    int cardVal;
    String suit;
    String name;
    int cardNum;
    /**
     * @param cardNumber
     * creates card objects and all information about the card is found through it's number
     */
    public  Card (int cardNumber)//cardvalue is handled in player
    {
         cardNum = cardNumber;
        if (cardNum <=13)
        {
            suit = " of hearts";
        }
        else if(cardNum <=26)
        {
            suit = " of diamonds";
        }
        else if(cardNum <= 39)
        {
            suit = " of spades";
        }
        else 
        {
            suit = " of clubs";
        }
    }
    /**
     * @return name
     * @param the card's number
     * based on the number of the card, 1- 52, the name of the card is returned
     */
    public String getName()
    {
        String name = "";
        while(cardNum>13)
        {
            cardNum = cardNum -13;
        }
        switch(cardNum)
        {
            case 1: name = "Ace";
            break;
            case 2: name = "2";
            break;
            case 3: name = "3";
            break;
            case 4: name = "4";
            break;
            case 5: name = "5";
            break;
            case 6: name = "6";
            break;
            case 7: name = "7";
            break;
            case 8: name = "8";
            break;
            case 9: name = "9";
            break;
            case 10: name = "10";
            break;
            case 11: name = "Jack";
            break;
            case 12: name = "Queen";
            break;
            case 13: name = "King";
            break;
            }
            return name;
        }
    
    /**
     * @return suit
     */
    public String getSuit()
    {
        return suit;
    }
}