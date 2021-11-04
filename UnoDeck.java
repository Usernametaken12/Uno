//10=skip 11=reverse 12=draw 2
//-1=wild, -2=wild draw 4
import java.util.*;
public class UnoDeck
{
   private ArrayList<UnoCard> deck=new ArrayList<UnoCard>();
   private ArrayList<UnoCard> discard=new ArrayList<UnoCard>();
   private int deckCount;
   
   public UnoDeck()
   {
      createUnoDeck();
      shuffleDeck();
      deckCount=1;
   }
   
   private void createUnoDeck()
   {
      boolean noRepeat=true;
      String color="RED";
      for(int i=1; i<13; i++)
      {
         addToDeck(i,color);
         if (i==12&&noRepeat)
         {
            i=-1;
            noRepeat=false;
         }
      }
      color="GREEN";
      noRepeat=true;
      for(int i=1; i<13; i++)
      {
         addToDeck(i,color);
         if (i==12&&noRepeat)
         {
            i=-1;
            noRepeat=false;
         }
      }
      color="YELLOW";
      noRepeat=true;
      for(int i=1; i<13; i++)
      {
         addToDeck(i,color);
         if (i==12&&noRepeat)
         {
            i=-1;
            noRepeat=false;
         }
      }  
      color="BLUE";
      noRepeat=true;
      for(int i=1; i<13; i++)
      {
         addToDeck(i,color);
         if (i==12&&noRepeat)
         {
            i=-1;
            noRepeat=false;
         }
      }
      color="WILD";
      for(int j=1; j<5; j++)
      {
         for (int i=-2; i<0; i++)
         {
            addToDeck(i, color);
         }
      }
   }
   
   public void shuffleDeck()
   {
      for (int i=0; i<200; i++)
      {
      int swap = (int)(Math.random()*deck.size());
      int otherSwap=(int)(Math.random()*deck.size()) ;
      UnoCard holder=deck.get(swap);
      deck.set(swap,deck.get(otherSwap));
      deck.set(otherSwap,holder);
      }
   }
   
   public void addToDeck(int a, String b)
   {
      UnoCard add=new UnoCard(a,b);
      deck.add(add);
   }
   
   public String toString()
   {
      String out="";
      int count=0;
      for (int i=0; i<deck.size(); i++)
      {
         out=out+deck.get(i).toString()+", ";
         count++;
      }
      return out;
   }
   
   public UnoCard draw()
   {
      if (deck.size()==0)
      {
         deck= (ArrayList<UnoCard>) discard.clone();
         discard.clear();
         if (deck.size()==0)
         {
            System.out.println("adding another deck...");
            createUnoDeck();
            shuffleDeck();
            deckCount++;
            if(deckCount==10)
            {
               System.out.println("There is no reason why anyone should have this many decks in play unless you are purposely trying to break this stupid game");
               Scanner keyboard=new Scanner(System.in);
               keyboard.nextLine();
            }
         }
      }
      UnoCard out=deck.get(0);
      deck.remove(0);
      return out;
   }
   
   public void discard(UnoCard d)
   {
      discard.add(d);
   }
   
   public void shuffleDiscard()
   {
      for (int i=0; i<200*deckCount; i++)
      {
      int swap = (int)(Math.random()*discard.size());
      int otherSwap=(int)(Math.random()*discard.size()) ;
      UnoCard holder=discard.get(swap);
      discard.set(swap,discard.get(otherSwap));
      discard.set(otherSwap,holder);
      }
   }
}