import java.util.*;
public class UnoRunner //855 lines of code to make a text only UNO... 
{
   public static void main( String[] args )
   {
    Scanner keyboard=new Scanner(System.in);
    System.out.print("Enter the number of players :: ");
    int x=keyboard.nextInt();
    System.out.print("Enter the number of computer opponents you would like to play with :: ");
    int y=keyboard.nextInt();
    Uno game= new Uno(x,y);
    game.playGame();
   }
}