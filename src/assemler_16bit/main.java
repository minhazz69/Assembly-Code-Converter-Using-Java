
package assemler_16bit;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class main {
     private static Scanner sc;
    public static void main(String[] args) {
        
        int i,size=0;                                 //read instructions from text file
        String []opIns=new String[30];
        String []regIns=new String[30];
        String []bin= new String[30];
        String []hex=new String[30];
        try{
            sc=new Scanner(new File("input.txt"));
        }catch(Exception e){
            System.out.println("No instructions detected...!!");
        }
        while(sc.hasNext()){
            opIns[size]=sc.next();
            regIns[size]=sc.next();
            size++;
        }
        sc.close();
        
        for(i=0;i<size;i++){
            String opSmall=opIns[i].toLowerCase();
            
            if(opSmall.equals("j")){
                String opCode="0111";
                bin[i]=binaryConvert3(opCode,regIns[i]);
            }
            else if(opSmall.equals("lw")){
                String opCode="1000";
                bin[i]=binaryConvert5(opCode,regIns[i]);
            }
            else if(opSmall.equals("sw")){
                String opCode="0010";
                bin[i]=binaryConvert5(opCode,regIns[i]);
            }
            else if(opSmall.equals("addi")){
                String opCode="0011";
                bin[i]=binaryConvert2(opCode,regIns[i]);
            }
            else if(opSmall.equals("sll")){
                String opCode="1001";
                bin[i]=binaryConvert2(opCode,regIns[i]);
            }
           
            else if(opSmall.equals("beq")){
                String opCode="0100";
                bin[i]=binaryConvert2(opCode,regIns[i]);
            }
            
            else if(opSmall.equals("add")){
                String opCode="0000";
                bin[i]=binaryConvert1(opCode,regIns[i]);   
            }
            else if(opSmall.equals("sub")){
                String opCode="0101";
                bin[i]=binaryConvert1(opCode,regIns[i]);
            }
            else if(opSmall.equals("and")){
                String opCode="0001";
                bin[i]=binaryConvert1(opCode,regIns[i]);
            }
             else if(opSmall.equals("slt")){
                String opCode="0110";
                bin[i]=binaryConvert1(opCode,regIns[i]);
            }
            else{
                bin[i]="invalid";
            }
        }
        
        
        
        try{
             FileWriter writer = new FileWriter("output.txt");
             for (i=0;i<size;i++) {
                writer.write(bin[i]+"\n");
             }
             writer.close();
        }catch(Exception e){
            System.out.println("Not properly written to file");
        }
        
        for(i=0;i<size;i++){
            String s1=hexConvert(bin[i].substring(0,4));
            String s2=hexConvert(bin[i].substring(4,8));
            String s3=hexConvert(bin[i].substring(8,12));
            String s4=hexConvert(bin[i].substring(12));
            hex[i]=s1+s2+s3+s4;
            
        }
        try{
             FileWriter writer = new FileWriter("hexout.txt");
             writer.write("v2.0 raw\n");
             for (i=0;i<size;i++) {
                writer.write(hex[i]+"\n");
             }
             writer.close();
        }catch(Exception e){
            System.out.println("Not properly written to file");
        }
        
        for(i=0;i<size;i++){
            System.out.println(opIns[i]+" "+regIns[i]);
            System.out.println(bin[i]);
            System.out.println(hex[i]);
        }
       
        
    }
    
    public static String hexConvert(String binary){
        if(binary.equals("0000")){
            return "0";
        }
        else if(binary.equals("0001")){
            return "1";
        }
        else if(binary.equals("0010")){
            return "2";
        }
        else if(binary.equals("0011")){
            return "3";
        }
        else if(binary.equals("0100")){
            return "4";
        }
        else if(binary.equals("0101")){
            return "5";
        }
        else if(binary.equals("0110")){
            return "6";
        }
        else if(binary.equals("0111")){
            return "7";
        }
        else if(binary.equals("1000")){
            return "8";
        }
        else if(binary.equals("1001")){
            return "9";
        }
        else if(binary.equals("1010")){
            return "a";
        }
        else if(binary.equals("1011")){
            return "b";
        }
        else if(binary.equals("1100")){
            return "c";
        }
        else if(binary.equals("1101")){
            return "d";
        }
        else if(binary.equals("1110")){
            return "e";
        }
        else if(binary.equals("1111")){
            return "f";
        }
        else{
            return "";
        }
    }
    
   
    
    public static String regValue(String a){
        if(a.contains("$t0")){
            return "0001";
        }
        else if(a.contains("$t1")){
            return "0010";
        }
        else if(a.contains("$t2")){
            return "0011";
        }
        else if(a.contains("$t3")){
            return "0100";
        }
        else if(a.contains("$t4")){
            return "0101";
        }
        else if(a.contains("$t5")){
            return "0110";
        }
        else if(a.contains("$t6")){
            return "0111";
        }
        else if(a.contains("$t7")){
            return "1000";
        }
        else if(a.contains("$s0")){
            return "1001";
        }
        else if(a.contains("$s1")){
            return "1010";
        }
        else if(a.contains("$s2")){
            return "1011";
        }
        else if(a.contains("$s3")){
            return "1100";
        }
        else if(a.contains("$s4")){
            return "1101";
        }
        else if(a.contains("$s5")){
            return "1110";
        }
        else if(a.contains("$s6")){
            return "1111";
        }
        else if(a.contains("$zero")){
            return "0000";
        }
        else{
            return " null ";
        }
    }
    public static String binaryConvert1(String o,String r){
        String r1=r.substring(0,3).toLowerCase();
        String r2=r.substring(4,7).toLowerCase();
        String r3=r.substring(8).toLowerCase();
        String rd=regValue(r1);
        String rs=regValue(r2);
        String rt=regValue(r3);
        String insBinary=o+rd+rs+rt;
        return insBinary; 
    }
    public static String binaryConvert2(String o,String r){
        String r1=r.substring(0,3).toLowerCase();
        String r2=r.substring(4,7).toLowerCase();
        String r3=r.substring(8);
        String sign=r3.substring(0,1);
        String value=r3.substring(1);
        
        String rd=regValue(r1);
        String rs=regValue(r2);
        if(sign.contains("-")){
            String sig="1";
            int num=Integer.parseInt(value);
            String dtb=decimalToBinary(num);
            String sF=bitCorrector(sig,dtb,3);
            String insBinary=o+rd+rs+sF;
            return insBinary;
            
        }
        else{
            String sig="0";
            int num=Integer.parseInt(r3);
            String dtb=decimalToBinary(num);
            String sF=bitCorrector(sig,dtb,3);
            String insBinary=o+rd+rs+sF;
            return insBinary;
        }
         
    }
    
   
    public static String decimalToBinary(int deci){
        int []binary = new int[20];
        int []revBinary = new int[20];
        String []str=new String [20];
        int i,index = 0,c=0;
        while(deci > 0){
        revBinary[index++] = deci%2;
        deci = deci/2;
        c++;
        }
        for(i=0;i<index;i++){
            binary[c-1]=revBinary[i];
            c--;
        }
        for(i=0;i<index;i++){
            str[i]=String.valueOf(binary[i]);
        }
        
        StringBuffer sb = new StringBuffer();
        for(i=0;i<index; i++) {
            sb.append(str[i]);
        }
        String s1 = sb.toString();
        return s1;
    }
    
   public static String binaryConvert3(String o,String r){
        String r1=r.substring(0,1);
        String r2=r.substring(1);
        String gap="00000000";
        
        
        if(r1.contains("-")){
            int num=Integer.parseInt(r2);
            String dtb=decimalToBinary(num);
            String si="1";
            String sFinal=o+gap+bitCorrector(si,dtb,3);
            return sFinal;
        }
        else{
            int num=Integer.parseInt(r);
            String dtb=decimalToBinary(num);
            String si="0";
            String sFinal=o+gap+bitCorrector(si,dtb,3);
            return sFinal;
        }
       
    }
    
    public static String bitCorrector(String sign,String data,int bit){
        
        String dtb=data;
        String si=sign;
        int total=bit;
        int binSize=dtb.length();
        int remain=total-binSize;
        char []ch=new char[bit];
        char []temp=new char[bit];
        for(int i=0;i<binSize;i++){
            ch[i]=dtb.charAt(i);
        }
        for(int i=0;i<binSize;i++){
            temp[i]=ch[i];
        }
        for(int i=0;i<remain;i++){
            ch[i]='0';
        }
        for(int i=0;i<binSize;i++){
            ch[remain]=temp[i];
            remain++;
        }
        String s2= String.valueOf(ch);
        String s3=si+s2;
        return s3;
    
    }
    
    
    
    public static String binaryConvert5(String o,String r){
        String r1=r.substring(0,3).toLowerCase();
        String r2=r.substring(4);
        int w=r2.indexOf('(');
        String r3=r2.substring(0,w);
        String r4=r2.substring(w+1,w+4);
        
        String rd=regValue(r1);
        String rs=regValue(r4);
        int num=Integer.parseInt(r3);
        String dtb=decimalToBinary(num);
        String insBin=o+rd+rs+bitCorrector("0",dtb,3);
        return insBin;
    }
    
}
