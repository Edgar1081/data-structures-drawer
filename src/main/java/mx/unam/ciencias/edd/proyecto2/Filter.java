package mx.unam.ciencias.edd.proyecto2;

public class Filter{
    public static boolean validCharClass(int c){
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    public static boolean validCharNumber(int n){
        return (n >= 48 && n <= 57) || (n == 45);
    }

    public static boolean isBlank(int b){
        char blank = (char)b;
        return blank == '\n' || blank == '\t' || blank == ' ';
    }
    public static boolean isAl(int b){
        char blank = (char)b;
        return blank == '#';
    }
    public static boolean invalid(int b){
        return !validCharClass(b) && !validCharNumber(b) && !isBlank(b) && !isAl(b);
    }
}
