import java.util.*;
public class CompPlayer extends UnoPlayer
{
static int numComputerPlayers=0;

public CompPlayer()
{
super("Computer "+(numComputerPlayers+1));
numComputerPlayers++;
}

public boolean isComputerPlayer()
{
return true;
}

public UnoCard makeMove()
{
   ArrayList<UnoCard> playableCards = new ArrayList<UnoCard>();
   for(int i=0; i<hand.size(); i++)
   {
      if(Uno.check(hand.get(i)))
         playableCards.add(hand.get(i));   
   }
   if(playableCards.size()==0)
   {
      UnoCard allowDraw = new UnoCard(13, "There is a bug in the CompPlayer class if you see this"); //placeholder to signal the Uno class to give the player a new card
      return allowDraw;
   }
   UnoCard play=playableCards.get(0);
   for(int i=1; i<playableCards.size(); i++)
   {
      if((play.getNumber()<playableCards.get(i).getNumber()&&!(play.getNumber()==11&&playableCards.get(i).getNumber()==12))||(play.getNumber()==121&&playableCards.get(i).getNumber()==11))
      play=playableCards.get(i);
   }
   return play;
}
public String chooseWildColor()
{
   double yellowCount=Math.random();
   double redCount=Math.random();
   double greenCount=Math.random();
   double blueCount=Math.random();
   for(int i=0; i<hand.size(); i++)
   {
      if(hand.get(i).getColor().equals("YELLOW"))
         yellowCount++;
      else if(hand.get(i).getColor().equals("GREEN"))
         greenCount++;
      else if(hand.get(i).getColor().equals("RED"))
         redCount++; 
      else if(hand.get(i).getColor().equals("BLUE"))
         blueCount++; 
   }
   if(yellowCount>redCount&&yellowCount>greenCount&&yellowCount>blueCount)
      return "YELLOW";
   else if(blueCount>redCount&&blueCount>yellowCount&&blueCount>greenCount)
      return "BLUE";
   else if(redCount>blueCount&&redCount>greenCount&&redCount>yellowCount)
      return "RED";
   else
      return "GREEN";
}
public boolean challengeWildDraw4(int handSize)
{
   Scanner keyboard=new Scanner(System.in);
   int chance=0;
   if(handSize==0)
      chance=0;
   else if(handSize==1)
      chance=100;
   else if(handSize==2)
      chance=10;
   else if(handSize==3)
      chance=20;   
   else if(handSize>=17)
      chance=99;
   else if(handSize>=15)
      chance=95;
   else if(handSize>=12)
      chance=90;
   else if(handSize>=7)
      chance=80;
   else if(handSize>=5)
      chance=60;
   else if(handSize==4)
      chance=30;
   double roll=(Math.random()*100);
   if(roll<chance)
      {
      System.out.println("The Computer challenges the wild draw 4!");
      System.out.print("Press Enter to continue");
      keyboard.nextLine();
      return true;
      }
   else
      {
      System.out.println("The Computer declines to challenge the wild draw 4.");
      System.out.print("Press Enter to continue");
      keyboard.nextLine();
      return false;
      }
} 
}