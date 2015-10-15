package mx.shellcore.android.micontador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "micontador.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE = "category";

    public static final String C_ID = BaseColumns._ID;
    public static final String C_NAME = "name";
    public static final String C_IMAGE = "image";
    public static final String C_TYPE = "type";

    public static final int C_ID_INDEX = 0;
    public static final int C_NAME_INDEX = 1;
    public static final int C_IMAGE_INDEX = 2;
    public static final int C_TYPE_INDEX = 3;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE " + TABLE)
                .append(" (")
                .append(" " + C_ID + " INTEGER PRIMARY KEY,")
                .append(" " + C_NAME + " TEXT,")
                .append(" " + C_IMAGE + " TEXT,")
                .append(" " + C_TYPE + " INT")
                .append(" )");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
