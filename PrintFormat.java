//Andrea Shulman
//Print Formatting
//contest 2
//2013-2014

import java.util.ArrayList;

public class PrintFormat
{
   private boolean comma,period,money,asterisk,exp;
   private ArrayList<String> out;
   private int len;
   private String value;
   String str;
   
   public PrintFormat(String str1, double val)
   {
      str=str1;
      String temp="";
      len=str.length();
      value=""+val;
      for(int i=0; i<len;i++)
      {
         temp=str.substring(i,i+1);
         if(temp.equals(","))
            comma=true;
         if(temp.equals("."))
            period=true;
         if(temp.equals("$"))
            money=true;
         if(temp.equals("*"))
            asterisk=true;
         if(temp.equals("E"))
            exp=true;
      }
      out=new ArrayList<String>();
      if(!exp)   
         decimals();
      value=hasComma(value);
      if(money)
         mon();
      if(!exp&&!money)
      {
         for(int i=0;i<value.length();i++)
            out.add(value.substring(i,i+1));
      }
      if(!comma)
         addAster();
      if(exp)
      {
         hasExp();
         decimals();
      }
      
      for(int j=0;j<out.size();j++)
         System.out.print(out.get(j));
      System.out.println("");
   }
   
   public void mon()
   {
      int extra=(len-1)-value.length();
      int hold=0;
      out.add(0,"$");
      int num=0;
      for(int i=extra-2;i<len&&num!=6;i++)
      {
         out.add(value.substring(num,num+1));
         num++;
      }
   }
   
   public String hasComma(String value)
   {
      if(!comma)
         return value;
      for(int i=value.length()-3;i>0;i-=3)
         value=value.substring(0,i)+","+value.substring(i);
      return value;
   }
   
   public void rounder()//rounds if period
   {
      int dec=value.indexOf(".");
      if(value.substring(dec+1,dec+2).equals("0"))
         value=value.substring(0,value.indexOf("."));
      if(dec+3>=value.length())
         return;
      String thou=value.substring(dec+3,dec+4);
      int round=Integer.parseInt(thou);
      if(round==9)
      {
         round=Integer.parseInt(value.substring(dec+1,dec+2))+1;
         value=value.substring(0,dec+1)+round+"0";
         if(str.substring(str.indexOf(".")).length()>2)
            value+="0";
         return;
      }
      else if(round>=5)
         round=Integer.parseInt(value.substring(dec+2,dec+3))+1;
      else
         round=Integer.parseInt(value.substring(dec+2));
      value=value.substring(0,dec+2)+""+round;
   }
   
   public void addAster()
   {
      if(value.length()>=len)
         return;
      int extra=len-out.size();
      if(asterisk||money)
         extra--;
      int hold=0;
      while(hold<extra)
      {
         out.add(hold,"*");
         hold++;
      }
   }
   
   public void hasExp()
   {
      String hold=value.substring(0,value.indexOf("."));
      int exp=hold.length()-1;
      hold=value.substring(value.indexOf(".")+1);
      value=value.substring(0,1)+"."+value.substring(1,value.indexOf("."))+hold;
      if(len!=value.length())
         rounder();
      value+="E"+exp;
      System.out.print(value);
   }
   
   public void decimals()
   {
      int zeros=0;
      if(period&&(((str.substring(str.indexOf("."))).length())-((value.substring(value.indexOf("."))).length())>0))
      {
         zeros=(str.substring(str.indexOf("."))).length()-(value.substring(value.indexOf("."))).length();
         for(int i=0;i<zeros;i++)
            value+="0";
      }
      else if(exp)
      {
         zeros=(len-1)-value.length();
         for(int i=0;i<zeros;i++)
            value+="0";
      }
      else
         rounder();
   }
   
   public static void main(String[]args)
   {
      /*PrintFormat obj=new PrintFormat("&&&&",25);
      PrintFormat obj2=new PrintFormat("&,&&&&&",12345);
      PrintFormat obj3=new PrintFormat("&&&.&&&&",12.34);
      PrintFormat obj4=new PrintFormat("&&&.&&",12.345);
      PrintFormat obj5=new PrintFormat("$&&&&.&&",123.45);
      PrintFormat obj6=new PrintFormat("*$&&&&&&.&&",123.45);
      PrintFormat obj7=new PrintFormat("&&&E",25376);
      
      PrintFormat inp=new PrintFormat("&&&&&&",456);
      PrintFormat inp2=new PrintFormat("&&&&&&,&",1000000);
      PrintFormat inp3=new PrintFormat("$&&&&.&&",123.38);
      PrintFormat inp4=new PrintFormat("&&&.&&&",23.49);
      PrintFormat inp5=new PrintFormat("&&&.&&&",23.4999);
      PrintFormat inp6=new PrintFormat("&&&E",45);*/
      
      PrintFormat inp=new PrintFormat("&&&&",25);
      PrintFormat inp2=new PrintFormat("&&.&&&",3.78);
      PrintFormat inp3=new PrintFormat("*$&&&.&&",45.598);
      PrintFormat inp4=new PrintFormat("&&&E",1000);
      PrintFormat inp5=new PrintFormat("&,&&",384);
      
   }
}