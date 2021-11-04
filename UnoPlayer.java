import java.util.ArrayList;
public class UnoPlayer
{
protected ArrayList<UnoCard> hand=new ArrayList<UnoCard>();
private static int numPlayers=0;
private int playerNumber;
private String playerName;
   
   public UnoPlayer(String name)
   {
   numPlayers++;
   playerName=name;
   playerNumber=numPlayers;
   }
   
   public void addCard(UnoCard a)
   {
   hand.add(a);
   }
   
   public String getHand()
   {
   String out ="";
   for(int i=0; i<hand.size(); i++)
   {
   String add=hand.get(i).toString();
   if (hand.get(i).getNumber()<0||hand.get(i).getNumber()>9)
   {
   switch (hand.get(i).getNumber())
   {
   case -2 : add=hand.get(i).getColor()+" DRAW +4"; break;
   case -1 : add=hand.get(i).getColor(); break;
   case 10 : add=hand.get(i).getColor()+" SKIP"; break;
   case 11 : add=hand.get(i).getColor()+" REVERSE"; break;
   case 12 : add=hand.get(i).getColor()+" DRAW +2"; break;
   }
   }
   out=out+add+", ";
   }
   return out;
   }
   
   public int getPlayerNumber()
   {
   return playerNumber;
   }
   
   public UnoCard getCard(int loc)
   {
   return hand.get(loc);
   }
   
   public String getCardToString(UnoCard translate)
   {
   String add=translate.toString();
   if (translate.getNumber()<0||translate.getNumber()>9)
   {
   switch (translate.getNumber())
   {
   case -2 : add=translate.getColor()+" DRAW +4"; break;
   case -1 : add=translate.getColor(); break;
   case 10 : add=translate.getColor()+" SKIP"; break;
   case 11 : add=translate.getColor()+" REVERSE"; break;
   case 12 : add=translate.getColor()+" DRAW +2"; break;
   }
   }
   return add;   
   }
   
   public int getHandSize()
   {
   return hand.size();
   }
   
   public String getName()
   {
   return playerName;
   }
   
   public void removeCard(UnoCard card)
   {
      int take=-1;
      for(int i=0; i<hand.size(); i++)
      {
         if (card.equals(hand.get(i)))
         {
         take=i;
         }
      }
      hand.remove(take);
   }
   
   public boolean equals(UnoPlayer other)
   {
   if (getName().equals(other.getName())&&getHandSize()==other.getHandSize())
   return true;
   else 
   return false;
   }
   
   public boolean hasColor(String color)
   {
   for (int i=0; i<hand.size(); i++)
   {
   if (hand.get(i).getColor().equals(color))
   return true;
   }
   return false;
   }
   
   public boolean isComputerPlayer()
   {
   return false;
   }
   }