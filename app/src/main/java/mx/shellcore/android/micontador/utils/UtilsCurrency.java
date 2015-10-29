package mx.shellcore.android.micontador.utils;

public class UtilsCurrency {

    public static long getIntegerPart(double num) {
        return (long) num;
    }

    public static double getFractionalPart(double num) {
        return ((num - getIntegerPart(num)) * 100);
    }
}
