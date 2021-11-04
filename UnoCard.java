public class UnoCard
{
   private int number;
   private String color;
   
   public UnoCard(int num, String col)
   {
   number=num;
   color=col;
   }
   
   public String toString()
   {
   return color +" "+ number;
   }
   
   public String getColor()
   {
   return color;
   }
   
   public int getNumber()
   {
   return number;
   }
   
   public boolean equals(UnoCard other)
   {
   if(this.getColor().equals(other.getColor())&&this.getNumber()==other.getNumber())
   return true;
   else
   return false;
   }
   
   public static String getCardToString(UnoCard translate)
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

         
}