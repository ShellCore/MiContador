package mx.shellcore.android.micontador.utils;


import android.net.Uri;

public class PathUtils {

    public static String getImagePath(int img) {
        Uri path = Uri.parse("android.resource://mx.shellcore.android.micontador/" + img);
        return path.toString();
    }
}
