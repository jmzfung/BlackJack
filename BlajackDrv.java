import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class BlajackDrv
{
    public static void main (String[]args)
    {
       int HandTot= 0;
       int DealerHandTot =0;
       int numDecks =6;
       boolean cont = true;//player can toggle and quit after a hand
       boolean bust = false;
       int bustNum =0;
       int numHands = 1;
       Deck myDeck = new Deck();
       Random gen = new Random();
         
       ArrayList <Integer> myHand = new ArrayList();//each card is labelled with an integer
       ArrayList <Integer> dealerHand = new ArrayList();
       
       double bank = 200;//different starting values can be used
       System.out.println("You are starting with: $"+ bank);
       Player me = new Player(bank);
       
       Player dealer = new Player(0);//only uses player methods to count hand value

       while (me.bankAccount()>0.00 && cont == true)
       {
         Scanner input = new Scanner(System.in);
         System.out.println();
         boolean t = false;
         while(t!=true)//checks to make sure bet is valid and places bet
         {
             System.out.println("How much money would you like to bet on this round?" + "\n" + "(You can only bet amounts of $10, $20, $30, $40, and $50");
             String hi = input.nextLine();
              t= me.placeBet(hi);
             if(t==false)
            {
             System.out.println("Please enter a valid amount");
            }
         }
         for (int cardsdealt = 0; cardsdealt<2; cardsdealt++)//each player gets two cards to start
         {
             int g;//number represents a card in the deck
             int h;
              do
              {
                g = gen.nextInt(52*numDecks) +1;
              }
              while(myDeck.Valid(g,numDecks)==false);//card is picked until it's valid
              dealerHand.add(g);
              myDeck.Remove(g);
              do
              {
                h = gen.nextInt(52*numDecks) +1;
              }
              while(myDeck.Valid(h,numDecks)==false);//card is picked until it's valid
              myHand.add(h);
              myDeck.Remove(h);
         }
         
         System.out.println();
         System.out.print("THE DEALER'S HAND: ");
         System.out.print("***, ");
         for(int d =1; d<dealerHand.size(); d++)//prints dealer's hand except one that's face down
         {
             int cardNumber = dealerHand.get(d);
             Card card = new Card (cardNumber);
             
             System.out.print(card.getName() + card.getSuit());
             if(d != dealerHand.size() -1)
             {
               System.out.print(", ");
             }
         }
         System.out.println();
         
         System.out.print("YOUR HAND: ");
         for(int u =0; u<myHand.size();u++)// prints player's hand
         {
             int cardNumber = myHand.get(u);
             Card card = new Card(cardNumber);
             
             System.out.print(card.getName() + card.getSuit());
             if( u!= myHand.size()-1)
             {
                 System.out.print(", ");
             }
         }
         System.out.println();
         System.out.println();
         if( me.countHand(myHand)==21)//they have blackjack
         {
             me.blackjack();
         }
         else
         {
                while( HandTot<=21)//start of player's turn
                {
                    Scanner next = new Scanner (System.in);
                    
                    System.out.println("What would you like to do?" + "\n" + "(You can: 'hit', 'stand', or 'split')");
                   String action = next.nextLine();
                   action = action.toLowerCase();
                   if(action.equals("split"))
                   {
                   if(me.canSplit(myHand))
                    {
                            me.doSplit(myHand);
                            numHands++;
                            
                            //do the turns with each hand
                            for(int i = 1; i <= numHands; i++)//gives the player turns per hand
                            {
                                HandTot = me.splitCount(myHand, i);
                                System.out.print("CURRENT HAND: ");
                                me.showSplit(myHand, i);
                                System.out.println();
                                while(HandTot<=21)
                                {
                                    System.out.println("What would you like to do to the current hand?" + "\n" + "(You can: 'hit', 'stand', or 'split')");
                                    action = next.nextLine();
                                    action = action.toLowerCase();
                                    if(action.equals("hit"))
                                    {
                                    int p;
                                    do
                                    {
                                        p = gen.nextInt(52*numDecks) + 1;
                                    }
                                    while(myDeck.Valid(p,numDecks)==false);
                                    myHand.add(p);
                                    Card myCard = new Card(p);
                                    System.out.print("CARD DRAWN: " + myCard.getName() + myCard.getSuit());
                                    System.out.println();
                    
                                    HandTot = me.countHand(myHand);
                                   }
                                   else if (action.equals("stand"))
                                   {
                                       break;
                                   }
                                   else if (action.equals("split"))
                                   {
                                       if(me.canSplit(myHand))
                                       {
                                                 me.doSplit(myHand);
                                                 numHands++;
                            
                                                 //do the turns with each hand
                                                 for(int o = 1; o <= numHands; o++)//gives the player turns per hand
                                                 {
                                                     HandTot = me.splitCount(myHand, o);
                                                     me.showSplit(myHand, o);
                                                     while(HandTot<=21)
                                                     {
                                                         System.out.println("What would you like to do to the current hand?" + "\n" + "(You can: 'hit', or 'stand')");
                                                         action = next.nextLine();
                                                         action = action.toLowerCase();
                                                         if(action.equals("hit"))
                                                         {
                                                             int e;
                                                             do
                                                             {
                                                                 e = gen.nextInt(52*numDecks) + 1;
                                                                }
                                                                while(myDeck.Valid(e,numDecks)==false);
                                                                myHand.add(e);
                                                                Card myCard = new Card(e);
                                                                System.out.print("CARD DRAWN: " + myCard.getName() + myCard.getSuit());
                                                                System.out.println();
                    
                                                                HandTot = me.countHand(myHand);
                                                            }
                                                            else if (action.equals("stand"))
                                                            {
                                                                break;
                                                            }
                                                            else
                                                            {
                                                                System.out.println("Please enter a valid command");
                                                            }
                                                        }
                                                        if (HandTot>21)
                                                        {
                                                            System.out.println();
                                                            System.out.println("BUST");
                                                            me.lose();
                                                            bustNum++;
                                                            if(bustNum==3)//with three hands, player can bust three times
                                                            {
                                                                bust = true;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    break;
                                       }
                                   }
                                   else
                                   {
                                       System.out.println("Please enter a valid command");
                                   }
                                }
                                if (HandTot>21)
                                {
                                    System.out.println();
                                    System.out.println("BUST");
                                    me.lose();
                                    bustNum++;
                                    if(bustNum==2)//with a split hand, player can bust twice
                                    {
                                         bust = true;
                                         break;
                                    }
                                }
                            }
                            break;
                        
                    }
                    else
                    {
                        System.out.println("You cannot split at this time.");
                    }
                }
                else if (action.equals("hit"))
                { 
                    int x;
                    do
                    {
                        x = gen.nextInt(52*numDecks) + 1;
                    }
                    while(myDeck.Valid(x,numDecks)==false);
                    myHand.add(x);
                    Card myCard = new Card (x);
                    
                    System.out.print("CARD DRAWN: " + myCard.getName() + myCard.getSuit());
                    System.out.println();
                    
                    HandTot = me.countHand(myHand);
                    
                    if(HandTot<=21)
                    {
                       //System.out.println("What would you like to do?");
                       //action = next.nextLine();
                    }
                    else // they have gone over 21 and bust, dealer automatically wins
                    {
                        System.out.println();
                        System.out.println("BUST");
                        me.lose();
                        bust = true;
                        break;
                    }
                }
                else if(action.equals("stand"))
                {
                    break;
                }
                else
                {
                    System.out.println("Please enter a valid command");
                }
               }
               
               
               
               if(HandTot<=21)//as long as the player didn't bust, the dealer will play
               {
                   System.out.println();
                   System.out.println("DEALER'S TURN");
                   System.out.println();
                   System.out.print("THE DEALER'S HAND: ");
                for(int d = 0; d < dealerHand.size(); d++)//prints info of hand
                {
                    int cardNumber = dealerHand.get(d);
                    Card dealerCard = new Card (cardNumber);
                         
                    System.out.print(dealerCard.getName() + dealerCard.getSuit());
                    if( d!= myHand.size()-1)
                    {
                        System.out.print(", ");
                    }
                }
                System.out.println();
                DealerHandTot = dealer.countHand(dealerHand);
                while(DealerHandTot <17)//dealer always hits with less than 17
                {
                    System.out.println("THE DEALER DECIDES TO TAKE A CARD");
                    int y;
                 do//finds a valid card
                 {
                     y = gen.nextInt(52*numDecks) +1;
                 }
                 while(myDeck.Valid(y,numDecks)==false);//card is picked until it's valid
                 dealerHand.add(y);
                 Card dealerCard = new Card(y);
                 System.out.println("CARD DRAWN: " + dealerCard.getName() + dealerCard.getSuit());
                 DealerHandTot = dealer.countHand(dealerHand);
                 if (DealerHandTot> 21)//the dealer busts
                 {
                     System.out.println("THE DEALER HAS BUST");
                     me.win();
                     bust = true;
                 }
                }
                System.out.println();
                HandTot = me.countHand(myHand);
                if(bust == false)
                {
                if(HandTot == DealerHandTot)
                {
                    me.tie();
                }
                else if(HandTot>DealerHandTot)
                {
                    me.win();
                }
                else
                {
                    me.lose();
                }
               }
            }
            
            
            if(me.broke())//checks if player is broke
            {
                break;
            }
        }        
         Scanner last = new Scanner(System.in);
        
         boolean invalid = true;
         while (invalid)
         {
             System.out.print("Would you like to continue?(enter 'yes' or 'no')  ");
             String h = last.nextLine();
             h = h.toLowerCase();
         if(h.equals("no"))
         {
             cont = false;
             System.out.println("Thank you for playing");
             invalid = false;
         }
         else if(h.equals("yes"))//clear both hands and shuffle
         {
             int mySize = myHand.size();
             for(int q = 0; q< mySize;q++)
             {
                 myHand.remove(0);
             }
             int dealerSize = dealerHand.size();
             for(int v =0; v< dealerSize; v++)
             {
                 dealerHand.remove(0);
             }
             myDeck.Shuffle();
             HandTot = 0;
             DealerHandTot = 0;
             bust = false;
             invalid = false;
             bustNum =0;
         }
         else
         {
             System.out.println("Please enter a valid command");
         }
        
    }
}
    }
}
/**
 * blackjack doesn't clear the hands
 * splitting cards
 * dealer's hand is still partially hidden at the end of the game
 */
           