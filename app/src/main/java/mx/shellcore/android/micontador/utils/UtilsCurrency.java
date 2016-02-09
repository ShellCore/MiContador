package mx.shellcore.android.micontador.utils;

public class UtilsCurrency {

    public static long getIntegerPart(double num) {
        return (long) num;
    }

    public static long getFractionalPart(double num) {
        return (long) ((num - getIntegerPart(num)) * 100);
    }
}
