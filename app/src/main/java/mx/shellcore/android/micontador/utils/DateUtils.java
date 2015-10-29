package mx.shellcore.android.micontador.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getString(Date date) {
        return new SimpleDateFormat(PATTERN).format(date);
    }

    public static Date getDate(String date) {
        try {
            return new SimpleDateFormat(PATTERN).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
