import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Module6 {
    public static void main(String[] args) {
        /*System.out.println("TASK 1");
        Task1();
        System.out.println("TASK 2");
        Task2();
        System.out.println("TASK 3");
        Task3();*/
        System.out.println("TASK 4");
        Task4();
        /*System.out.println("TASK 5");
        Task5();
        System.out.println("TASK 6");
        Task6();
        System.out.println("TASK 7");
        Task7();
        System.out.println("TASK 8");
        Task8();
        System.out.println("TASK 9");
        Task9();
        System.out.println("TASK 10");
        Task10();*/
    }

    public static void Task1() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter number: ");
        int num = scanstr.nextInt();
        System.out.println("Result: " + bell(num));
    }
    public static int bell(int n) {
        int[] arr0 = new int[n];
        int[] arr1 = new int[n];
        arr0[0] = 1;
        arr1[0] = 1;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < i; ++j)
                arr1[j] = arr0[j - 1] + arr1[j - 1];
            for (int k = 0; k < i; ++k)
                arr0[k] = arr1[k];
            arr1[0] = arr1[i - 1];
        }
        return arr1[n - 1];
    }

    public static void Task2() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter word: ");
        String str = scanstr.nextLine();
        System.out.println("Result: " + translateSentence(str));
    }

    public static String translateWord(String word) {
        String vowels = "aeiouAEIOU";
        String ans, part;
        boolean firstUpper;
        char c;
        if(word.length() == 0){
            return word;
        }
        c = word.charAt(0);
        firstUpper = false;
        if((c >= 'A') && (c <= 'Z')){
            firstUpper = true;
        }
        ans = word;
        if (vowels.indexOf(c) == -1){
            while(vowels.indexOf(c) == -1){
                part = ans.substring(0, 1);
                part = part.toLowerCase();
                ans = ans.substring(1) + part;
                c = ans.charAt(0);
            }
            ans += "ay";
            if(firstUpper){
                part = ans.substring(0, 1);
                part = part.toUpperCase();
                ans = part + ans.substring(1);
            }
        }
        else{
            ans += "yay";
        }
        return ans;
    }

    public static String translateSentence(String sentence) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String txt, pig, word, part;
        char c;
        if(sentence.length() == 0){
            return "";
        }
        txt = sentence;
        pig = "";
        word = "";
        for(int i=0; i<txt.length(); i++){
            c = txt.charAt(i);
            if(letters.indexOf(c) != -1){
                word += c;
            }
            else{
                pig += translateWord(word);
                word = "";
                pig += c;
            }
        }
        if(word.length() > 0){
            pig += translateWord(word);
        }
        part = pig.substring(0, 1);
        part = part.toUpperCase();
        pig = part + pig.substring(1);
        return pig;
    }

    public static void Task3() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter color: ");
        String color = scanstr.nextLine();
        System.out.println("Result: " + validColor(color));
    }

    public static boolean validColor(String color)
    {
        int index = color.indexOf('(');
        int index1 = color.lastIndexOf(')');
        if (color.charAt(index-1) == ' ') return false;
        String sub = color.substring(0,index);
        String temp = color.substring(index+1,index1);
        for (int i = 1; i < color.length(); i++)
        {
            if (color.charAt(i) == ',' && color.charAt(i-1) == ',') return false;
        }
        String[] words = temp.split(",");
        for (int i = 0; i < words.length; i++)
        {
            if (words[i].contains("%"))
            {
                String s = words[i].substring(0,words[i].length()-1);
                int d1 = Integer.parseInt(s);
                if (d1 > 100 || d1 < 0) return false;
            }
            else if (!words[i].contains("%"))
            {
                double d2 = Double.parseDouble(words[i]);
                if (d2 > 255.0 || d2 < 0.0) return false;
            }
        }
        if (sub.equals("rgb") && words.length != 3) return false;
        if (sub.equals("rgba"))
        {
            if (words.length != 4) return false;
            double d = Double.parseDouble(words[3]);
            if (d > 1.0 || d < 0.0) return false;
        }
        return true;
    }

    public static void Task4() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter url: ");
        String url = scanstr.nextLine();System.out.println("Enter the length of the array:");
        int length = scanstr.nextInt();
        String[] myArray = new String[length+1];
        System.out.println("Enter params:");
        for (int i = 0; i < length+1; i++) {
            myArray[i] = scanstr.nextLine();
        }
        System.out.println("Result: " + stripUrlParams(url, myArray));
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {
        if (!url.contains("?")) return url;

        String[] surl = url.split("\\?");
        String[] params = surl[1].split("\\&");
        HashMap<String, String> dict = new HashMap<>();

        for (String v : params) {
            dict.put(v.substring(0, 1), v);
        }

        if (paramsToStrip != null) for (String k : paramsToStrip) {
            dict.remove(k);
        }

        return surl[0] + "?" + String.join("&", dict.values());
    }

    public static void Task5() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter string: ");
        String str = scanstr.nextLine();
        System.out.println("Result: " + getHashTags(str));
    }

    public static String[] getHashTags(String str) {
        String[]x=str.replaceAll("[^a-zA-z ]","").split(" ");
        String[]y=new String[3];
        String z="";
        int count=0;
        ArrayList<String> al = new ArrayList<String>();
        if(x.length<3){
            for(int k=0;k<x.length;k++){
                z=z+"#"+x[k].toLowerCase()+" ";
            }
            return(z.trim().split(" "));
        }
        for( String h:x){
            al.add(h);
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<al.size();j++){
                if(al.get(j).length()>count){
                    count=al.get(j).length();
                    z=al.get(j);
                }
            }
            y[i]="#"+z.toLowerCase();;
            count=0;
            al.remove(z);
        }
        return y;
    }


    public static void Task6() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter number: ");
        int ulamnum = scanstr.nextInt();
        System.out.println("Result: " + ulam(ulamnum));
    }

    public static int ulam(int n) {
        ArrayList<Integer> ulam = new ArrayList<Integer>();
        ulam.add(1); ulam.add(2);
        int currentNum = ulam.get(ulam.size() - 1);
        while (ulam.size() != n) {
            currentNum++;
            int counter = 0;
            for(int i = 0; i < ulam.size(); i++){
                int result = currentNum - ulam.get(i);
                if (ulam.contains(result) && result - ulam.get(i) != 0) counter++;
            }
            if (counter == 2) ulam.add(currentNum);
        }
        return ulam.get(ulam.size() - 1);
    }

    public static void Task7() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter string: ");
        String str = scanstr.nextLine();
        System.out.println("Enter: " + longestNonrepeatingSubstring(str));
    }

    public static String longestNonrepeatingSubstring(String str) {
        String longest="";

        for (int i=0; i<str.length()-1; i++) {
            String buffer="";
            for (int n=i; n<str.length(); n++)
                if (!buffer.contains(str.substring(n,n+1)))
                    buffer+=str.substring(n,n+1);
                else
                    break;
            if (buffer.length()>longest.length())
                longest=buffer;
        }

        return longest;
    }

    public static void Task8() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter num: ");
        int num = scanstr.nextInt();
        System.out.println("Result:" + convertToRoman(num));
    }

    public static String convertToRoman(int num) {
        int[] decimal_value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_value = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String output = "";
        for(int i = 0; i < decimal_value.length; i++){
            while(decimal_value[i] <= num){
                output += roman_value[i];
                num -= decimal_value[i];
            }
        }

        return output;
    }

    public static void Task9() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter formula: ");
        String formula = scanstr.nextLine();
        System.out.println("Result:" + formula(formula));
    }

    public static boolean formula(String str) {
        String []w=str.split("=");
        for(int j=1;j<w.length;j++) {
            if(sum(w[j-1])!=sum(w[j])) return false;
        }
        return true;
    }
    public static int sum(String q){
        String[]x=q.replaceAll("[ ()]","").replaceAll("a","4").split("[+-//*\\/=]");
        if(x.length==1) return Integer.parseInt(x[0].trim());
        String y=q.replaceAll("[0-9a() ]","");
        int z=Integer.parseInt(x[0].trim());
        for(int i=0;i<y.length();i++) {
            if(y.charAt(i)=='+') {
                z=z+Integer.parseInt(x[i+1]);
            }else if(y.charAt(i)=='-') {
                z=z-Integer.parseInt(x[i+1]);
            }else if(y.charAt(i)=='*') {
                z=z*Integer.parseInt(x[i+1]);
            }else if(y.charAt(i)=='/') {
                z=z/Integer.parseInt(x[i+1]);
            }
        }
        return z;
    }

    public static void Task10() {
        Scanner scanstr = new Scanner(System.in);
        System.out.println("Enter num: ");
        int pal = scanstr.nextInt();
        System.out.println("Result:" + palindromeDescendant(pal));
    }

    public static boolean palindromeDescendant(int num) {
        String ns = Integer.toString(num);
        String rs = "";
        for(int i = 0; i < ns.length(); i++) rs = ns.charAt(i) + rs;

        for(int i = 0; ns.length() >= 2; i++){
            if(ns.equals(rs)) return true;
            ns = "";
            for(int y = rs.length() - 1; y > 0; y = y - 2) ns += Character.getNumericValue(rs.charAt(y)) + Character.getNumericValue(rs.charAt(y - 1));
            rs = "";
            for(int x = 0; x < ns.length(); x++) rs = ns.charAt(x) + rs;
        }
        return false;
    }
}
