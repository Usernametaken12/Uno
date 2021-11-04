import java.util.*;
public class Uno
{
    private UnoPlayer[] players;
    private UnoDeck deck;
    private static UnoCard faceUp;
    private int nowServing;
    private int turnModifier=1;
    private static String wildColor;
    private static String previousWildColor;
    private boolean stop;
	 private boolean wildDraw4Played;
        
    public Uno(int playerNum, int compNum)
    {
      deck=new UnoDeck();
      if (playerNum<0)
         playerNum=0;
      players=new UnoPlayer[playerNum+compNum];
      for(int i=0; i<playerNum; i++)
      {
         players[i]=addPlayer(i);
      }
      for (int i=playerNum; i<playerNum+compNum; i++)
      {
         players[i]=addCompPlayer();
      }
      dealCards();
      nowServing=0;
      wildColor="notSet";
      setFaceUpCard();
      stop=false;
	   wildDraw4Played=false;
    }
    
    private UnoPlayer addPlayer(int playerNumber)
    {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter the name of player "+(playerNumber+1)+" :: ");
      String playerName=keyboard.next();
      UnoPlayer test=new UnoPlayer(playerName);
      return test;
    }  
    
    private CompPlayer addCompPlayer()
    {
      CompPlayer help= new CompPlayer();
      return help;
    }
    
    public void playGame()
    {
      while(!stop)
      {
         if (!players[nowServing].isComputerPlayer())
         {
            play();
            if(!stop)
               System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
         }
         else
            computerTurn();
      }
    }
    
    private void addCard(UnoPlayer here)
    {
      here.addCard(deck.draw());
    }
    
    private void dealCards()
    {
      for (int j=0; j<7; j++)
      {
        for (int i=0; i<players.length; i++)
        {
            addCard(players[i]);
        }
      }
    }
    
    public String playerHandsToString()
    {
      String out="";
      for (int i =0; i<players.length; i++)
      {
         out=out+players[i].getHand()+"\n";
      }
      return out;
    }
    
    private void setFaceUpCard()
    {
      Scanner keyboard=new Scanner(System.in);
      faceUp=deck.draw();
      deck.discard(faceUp); //intersection of poor naming conventions. Marks the face up card as being in the discard pile
      if (faceUp.getNumber()>9||faceUp.getNumber()<0)
      {
         int j=faceUp.getNumber();
         switch (j)
         {
            case -2 :
               for (int i=0; i<4; i++)
               {
                 players[nowServing].addCard(deck.draw());
               }
               System.out.println(players[nowServing].getName()+" has been forced to draw four cards due to a wild draw four being flipped and has been skipped, but may still choose its color");
               playerChooseColor();
               changeTurns(); break;
            case -1 :
               System.out.println(players[nowServing].getName()+" may choose the color of the wild on the top of the deck");
               playerChooseColor();
               break;
            case 10 :
               System.out.println(players[nowServing].getName()+" has lost their turn due to a skip being on the top of the deck");
               System.out.print("Press enter to continue");
               keyboard.nextLine();
               changeTurns();
               break;
            case 11 :
               turnModifier*=-1; break;
            case 12 :
               System.out.println(players[nowServing].getName()+" has been forced to draw two cards due to a draw two being flipped and has been skipped");
               for (int i=0; i<2; i++)
               {
                  players[nowServing].addCard(deck.draw());
               }
         }
      }
    }
    public static boolean check(UnoCard suspect)
    {
      if (suspect.getNumber()==faceUp.getNumber()||suspect.getColor().equals(faceUp.getColor())||suspect.getNumber()<0||(suspect.getColor().equals(wildColor)&&faceUp.getColor().equals("WILD")))
         return true;
      else
         return false;
    }
    
    public void play()
    {
      Scanner keyboard= new Scanner(System.in);
      System.out.println("It is " +players[nowServing].getName() +"\'s turn");
      System.out.println("Hit enter to begin your turn");
      keyboard.nextLine();
      System.out.println(getCardCounts(players[nowServing]));
      System.out.println(faceUp.getCardToString(faceUp));
      if (faceUp.getColor().equals("WILD"))
         System.out.println("Is to be considered a "+wildColor+" card");
      System.out.println("Your hand :: ");
      System.out.println(players[nowServing].getHand());
      Boolean repeat=true;
      while(repeat)
      {
         System.out.print("Enter the card that you want to play corresponding to its order left to right if you cannot play enter \"draw\":: ");
         String input = keyboard.next();
         repeat=false;
         if(input.equals("draw"))
         {
            players[nowServing].addCard(deck.draw());
            System.out.println("You drew a "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-1)));
            System.out.println("This is the "+players[nowServing].getHandSize()+" card in your hand");
            repeat=true;
         }
         else if (input.equals("1")||input.equals("2")||input.equals("3")||input.equals("4")||input.equals("5")||input.equals("6")||input.equals("7")||input.equals("8")||input.equals("9")||input.equals("10")||input.equals("11")||input.equals("12")||input.equals("13")||input.equals("14")||input.equals("15")||input.equals("16")||input.equals("17")||input.equals("18")||input.equals("19")||input.equals("20")||input.equals("21")||input.equals("22")||input.equals("23")||input.equals("24")||input.equals("25")||input.equals("26")||input.equals("27")||input.equals("28")||input.equals("29")||input.equals("30")||input.equals("31")||input.equals("32")||input.equals("33")||input.equals("34")||input.equals("35")||input.equals("36")||input.equals("37")||input.equals("38")||input.equals("39")||input.equals("40")||input.equals("42")||input.equals("41")||input.equals("43")||input.equals("44")||input.equals("45")||input.equals("51")||input.equals("52")||input.equals("53")||input.equals("54")||input.equals("55")||input.equals("56")||input.equals("57")||input.equals("58")||input.equals("59")||input.equals("50")||input.equals("46")||input.equals("47")||input.equals("48")||input.equals("49")||input.equals("61")||input.equals("62")||input.equals("63")||input.equals("64")||input.equals("65")||input.equals("66")||input.equals("67")||input.equals("68")||input.equals("69")||input.equals("60")||input.equals("71")||input.equals("72")||input.equals("73")||input.equals("74")||input.equals("75")||input.equals("76")||input.equals("77")||input.equals("78")||input.equals("79")||input.equals("70")||input.equals("81")||input.equals("82")||input.equals("83")||input.equals("84")||input.equals("85")||input.equals("86")||input.equals("87")||input.equals("88")||input.equals("89")||input.equals("80")||input.equals("91")||input.equals("92")||input.equals("93")||input.equals("94")||input.equals("95")||input.equals("96")||input.equals("97")||input.equals("98")||input.equals("99")||input.equals("90")||input.equals("101")||input.equals("102")||input.equals("103")||input.equals("104")||input.equals("105")||input.equals("106")||input.equals("107")||input.equals("108")||input.equals("100"))
         {
            if (Integer.parseInt(input)-1>players[nowServing].getHandSize()-1)
            {
               System.out.println("The card you selected was not valid");
               repeat=true;
            }
            else
            {
               UnoCard playing=players[nowServing].getCard(Integer.parseInt(input)-1);
               boolean hasPlayed=playCard(playing);
               if(!hasPlayed)
                  {
                     System.out.println("The card you selected was not valid");
                     repeat=true;
                  }
               else
                  repeat=false;
            }
         }
         else
         {
            System.out.println("Please enter a valid command.");
            repeat=true;
         }
      }
    }
    
    public boolean playCard(UnoCard the)
    {
      if(!check(the))
      {
         return false;
      }
      else
      {
         int test=the.getNumber();
         players[nowServing].removeCard(the);
         Scanner keyboard=new Scanner(System.in);
         String name =players[nowServing].getName();
         boolean WildDraw4=false;
         switch(test)
         {
            case -2 : 
               playerChooseColor();
               WildDraw4=true; break;
            case -1 : 
               playerChooseColor(); break;
            case 10 : 
               name=players[nowServing].getName();
               changeTurns(); 
               System.out.print(players[nowServing].getName()+" has been skipped by "+name+ "! \n Press enter to continue");  
               keyboard.nextLine(); break;
            case 11 : 
               turnModifier*=-1; 
               System.out.println("Order of play has been reversed!"); break;
            case 12 : 
               name=players[nowServing].getName();
               for (int i=0; i<2; i++)
               {
                  changeTurns();
                  players[nowServing].addCard(deck.draw());
                  revertTurns();
               }
               changeTurns();
               System.out.print(players[nowServing].getName()+" has been forced to draw two cards and has been skipped by "+name+ "! \n Press enter to continue");  
               keyboard.nextLine(); 
               if(!players[nowServing].isComputerPlayer())
               {
                  System.out.println("You Drew "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-2))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-1))+"\n"+"Press Enter to Continue"); 
                  keyboard.nextLine();
               }
               break;
          }
          if (WildDraw4)
          {
	         WildDraw4=false;
            changeTurns();
            System.out.println(players[nowServing].getName()+" has been forced to draw four cards by "+name+ "!");   
            if(!players[nowServing].isComputerPlayer())
            {
               revertTurns();
               System.out.print("Challenge? \"y\"/\"n\" :: ");
               boolean repeat=true;
               while(repeat)
	            {
	               repeat=false;
	               String input=keyboard.next();
	               if(input.equals("y"))
	               {
	                  challenge();
                  }
	               else if(input.equals("n"))
	               {
                     name =players[nowServing].getName();
                     for (int i=0; i<4; i++)
                     {
                        changeTurns();
                        players[nowServing].addCard(deck.draw());
                        revertTurns();
                     }
                     changeTurns();
	                  System.out.println("You Drew "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-4))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-3))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-2))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-1))); 
                     System.out.print("Press enter to continue");
                     keyboard.nextLine();
                     keyboard.nextLine();
	               }
	               else
	               {
	               repeat=true;
	               System.out.println("Enter a valid command \nChallenge? \"y\"/\"n\" :: ");
	               }
	            }
	         }
            else
            {
               revertTurns();
               int playerHandSize=players[nowServing].getHandSize();
               changeTurns();
               if(((CompPlayer)players[nowServing]).challengeWildDraw4(playerHandSize))
               {
                  revertTurns();
                  challenge();
               }
               else
               {
                 for(int i=0; i<4; i++)
                  {
                     players[nowServing].addCard(deck.draw());
                  }
               }
            }
         }
         faceUp=the;
         deck.discard(the);
         int store=checkWin();
         if (store!=-1)
         {
            hasWon(store);
         }
         changeTurns();
         return true;
       }
    }
    
    private void changeTurns()
    {
      nowServing+=turnModifier;
      if(nowServing<0)
         nowServing=(players.length)-1;
      if(nowServing>players.length-1)
         nowServing=0;
    }
    
    private void revertTurns() 
    {
      nowServing-=turnModifier;
      if(nowServing<0)
         nowServing=players.length-1;
      if(nowServing>players.length-1)
         nowServing=0;
    }
    
    private int checkWin()
    {
      int out=-1;
      for (int i=0; i<players.length; i++)
      {
         if (players[i].getHandSize()==0)
         {
            out=i;
            break;
         }
      }
      return out;
    }
    
    private void hasWon(int a)
    {
      System.out.println(players[a].getName()+" has won!");
      stop=true;
    }
    
    public String getCardCounts(UnoPlayer exclude)
    {
      String out="";
      for (int i=0; i<players.length; i++)
      {
         if(!(exclude.equals(players[i])))
         {
            out+=players[i].getName()+" has "+players[i].getHandSize()+" card(s) in their hand.\n";
         }
      }
      return out;
    } 
    
    private boolean wildDraw4IsValid()
    {
       if (players[nowServing].hasColor(faceUp.getColor())||faceUp.getColor().equals("WILD")&&players[nowServing].hasColor(previousWildColor))
         return false;
      return true;
    }
    
    private void computerTurn()
    {
      Scanner keyboard= new Scanner(System.in);
      CompPlayer comp =(CompPlayer) players[nowServing];
      UnoCard move=comp.makeMove();
      while (move.getNumber()==13)
      {
         comp.addCard(deck.draw());
         System.out.println(players[nowServing].getName()+" drew a card");
         System.out.println("Press enter to continue");
         keyboard.nextLine();
         move=comp.makeMove();
      }
      System.out.println(comp.getName()+" has played a "+UnoCard.getCardToString(move));
      System.out.println("Press enter to continue");
      keyboard.nextLine();
      playCard(move);
    }
    
    private void challenge()
    {
      if(wildDraw4IsValid())
	   {
	      Scanner keyboard = new Scanner(System.in);
         System.out.println("Challenge failed");
	      System.out.print("Press enter to continue");
         keyboard.nextLine();
	      String name =players[nowServing].getName();
         for (int i=0; i<6; i++)
         {
            changeTurns();
            players[nowServing].addCard(deck.draw());
            revertTurns();
         }
         changeTurns();
	      if (!players[nowServing].isComputerPlayer())
         {
            System.out.println("You Drew "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-6))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-5))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-4))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-3))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-2))+", "+players[nowServing].getCardToString(players[nowServing].getCard(players[nowServing].getHandSize()-1))); 
            System.out.print("Press enter to continue");
            keyboard.nextLine();
         }
	      changeTurns();
	   }
	   else
	   {
	      System.out.println("Challange Successful!");
	      System.out.print(players[nowServing].getName()+" has been forced to draw four cards!");
	      for (int i=0; i<4; i++)
         {
            players[nowServing].addCard(deck.draw());
	      }
	   }

    }
    private void playerChooseColor()
    {
      Scanner keyboard=new Scanner(System.in);
      if (!players[nowServing].isComputerPlayer())
      {
            System.out.println("Your hand :: "+players[nowServing].getHand());
            System.out.print("Choose a color :: ");
            previousWildColor=wildColor;
            wildColor=keyboard.next();
            while(!(wildColor.equals("GREEN")||wildColor.equals("YELLOW")||wildColor.equals("BLUE")||wildColor.equals("RED")))
               {
                  System.out.print("Please enter a valid color from \"GREEN\", \"RED\", \"YELLOW\", \"BLUE\" :: ");
                  wildColor=keyboard.next();
               }
       }
       else
       {
          wildColor=((CompPlayer) players[nowServing]).chooseWildColor();
          System.out.println(players[nowServing].getName()+" chose "+wildColor);
       }   
    }
}   