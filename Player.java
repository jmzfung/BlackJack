import java.util.ArrayList;
public class Player// only accounts for the player using the program, not dealer or other players
{
    double money;
    double bet=0;
    /**
     * @param bank
     * player object is created
     */
    public Player(double bank)
    {
        money = bank;
    }
    /**
     * @param input
     * @return boolean
     * checks to see if the bet is a valid input
     * stores the bet amount
     */
    public boolean placeBet(String input)
    {
        input = input.toLowerCase();
        if(input.equals("10")||input.equals("10.0"))
        {
            bet = 10;
            return true;
        }
        else if (input.equals("ten")||input.equals("10.00"))
        {
            bet = 10;
            return true;
        }
        else if (input.equals("20")||input.equals("20.0"))
        {
            bet = 20;
            return true;
        }
        else if (input.equals("twenty")||input.equals("20.00"))
        {
            bet=20;
            return true;
        }
        else if (input.equals("30")||input.equals("30.0"))
        {
            bet=30;
            return true;
        }
        else if (input.equals("thirty")||input.equals("30.00"))
        {
            bet=30;
            return true;
        }
        else if (input.equals("40")||input.equals("40.0"))
        {
            bet=40;
            return true;
        }
        else if (input.equals("forty")||input.equals("40.00"))
        {
            bet=40;
            return true;
        }
        else if (input.equals("50")||input.equals("50.0"))
        {
            bet=50;
            return true;
        }
        else if (input.equals("fifty")||input.equals("50.00"))
        {
            bet=50;
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * @param hand
     * checks if player is able to split their hand
     */
    public boolean canSplit( ArrayList<Integer> hand)
    {
        int cardNum = 0;
        boolean canSplit = false;
        for (int j = 0; j< hand.size(); j++)
        {
            int cell = hand.get(j);
            while (cell > 13)
            {
                cell = cell - 13;
            }
            hand.set(j, cell);
        }
            for(int card = 1; card<=13; card++)
            {
                int numtimes = 0;
                for (int u = 0; u<hand.size();u++)
                {
                    if(hand.get(u)==card)
                    {
                        numtimes++;
                    }
                    if(numtimes==2)
                    {
                        canSplit = true; 
                        cardNum = card;
                    }
                }
            }
            return canSplit;
    }
    /**
     * @param hand
     * executes hand split
     */
    public void doSplit(ArrayList<Integer> hand)
    {
        int x = hand.size();
        hand.set(x-2, 0);
    }
    /**
     * @param hand
     * @param x
     * shows a split hand
     */
    public void showSplit(ArrayList<Integer> hand, int x)
    {
        int numZero = 0;
        int cell=0;
        while(numZero!=x) 
        {
            int f = hand.get(cell);
            cell++;
            if(f==0)
            {
                numZero++;
            }
            else
            {
                f++;
            }
        }
        for(int g =0; g<cell; g++)
        {
            int l = hand.get(g);
            Card card = new Card (l);
            System.out.print(card.getName() + card.getSuit());
            if( g != cell-1)
            {
                System.out.print(", ");
            }
        }
    }
    /**
     * @return HandTot
     * @param  hand
     * @param handNum
     * counts the player's hands when they're split
     */
    public int splitCount(ArrayList<Integer> hand, int handNum)
    {
        int HandTot = 0;
        boolean Ace = false;
        int AceNum = 0;
        int countedHands = 0;
        for (int g = 0; g < handNum; g++)
        {
        for(int r = 0; hand.get(r)!= 0; r++)
        {
            int cardNum = hand.get(r);
            while(cardNum > 13)
            {
                cardNum = cardNum - 13;
            }
            if(cardNum == 1)
            {
                Ace = true;
                AceNum++;
            }
            else if ( cardNum< 11)//card is not a face card
            {
                HandTot = HandTot + cardNum;
            }
            else//the card is a face card
            {
                HandTot = HandTot + 10;
            }
        }
        
        if (Ace)
            {
                for(int t = 1; t<=AceNum; t++)
                {
                    if(HandTot>10)
                    {
                        HandTot = HandTot + 1;
                    }
                    else
                    {
                        HandTot = HandTot + 11;
                    }
                }
            }
        countedHands++;
        if(countedHands != handNum)
        {
            HandTot = 0;
        }
    }
        return HandTot;
    }
    /**
     * counts the hands worth
     * @return HandTot
     */
    public int countHand(ArrayList<Integer> hand)//only counts value of hand
    {
        boolean Ace = false;
        int AceNum = 0;
        int HandTot = 0;
        for(int r = 0; r< hand.size(); r++)
        {
            int cardNum = hand.get(r);
            while(cardNum > 13)
            {
                cardNum = cardNum - 13;
            }
            if(cardNum == 1)
            {
                Ace = true;
                AceNum++;
            }
            else if ( cardNum< 11)//card is not a face card
            {
                HandTot = HandTot + cardNum;
            }
            else//the card is a face card
            {
                HandTot = HandTot + 10;
            }
        }
        if (Ace)
            {
                for(int t = 1; t<=AceNum; t++)
                {
                    if(HandTot>10)
                    {
                        HandTot = HandTot + 1;
                    }
                    else
                    {
                        HandTot = HandTot + 11;
                    }
                }
            }
        return HandTot;
    }
    /**
     * @return money
     */
    public double bankAccount()
    {
        return money;
    }
    /**
     * subtracts money from the player's amount
     */
    public void lose()
    {
        money = money - bet;
        System.out.println("YOU LOST: $" + bet);
        System.out.println("YOU NOW HAVE: $" + money);
        System.out.println();
    }
    /**
     * adds money to player's amount
     */
    public void win()
    {
        money = money + bet;
        System.out.println("YOU WON: $" + bet);
        System.out.println("YOU NOW HAVE : $" + money);
        System.out.println();
    }
    /**
     * prints end of game statement when there's a tie
     */
    public void tie()
    {
        System.out.println("YOU TIED");
        System.out.println("YOU NOW HAVE: $" + money);
        System.out.println();
    }
    /**
     * adds double the bet amount to player's amount
     * can only have blackjack with two cards dealt
     */
    public void blackjack()
    {
        money = money + 2*bet;
        System.out.println("YOU GOT BLACKJACK!!!");
        System.out.println("YOU WON: $" + 2*bet);
        System.out.println("YOU NOW HAVE: $" + money);
        System.out.println();
    }
    /**
     * @return boolean
     * checks if player has enough money to continue playing
     */
    public boolean broke()
    {
        if( money<0.01)
        {
            System.out.println("YOU HAVE NO MORE MONEY. GAME OVER.");
            return true;
        }
        return false;
    }
}