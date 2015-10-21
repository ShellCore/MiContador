package mx.shellcore.android.micontador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import mx.shellcore.android.micontador.utils.Base64Images;
import mx.shellcore.android.micontador.utils.Constants;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "micontador.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createCategoryTable());
        db.execSQL(createCategoryImageTable());
        inicializarImagenes(db);
    }

    private String createCategoryImageTable() {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE " + Constants.CATEGORY_IMAGE.TABLE)
                .append(" (")
                .append(" " + Constants.CATEGORY_IMAGE.C_ID + " INTEGER PRIMARY KEY,")
                .append(" " + Constants.CATEGORY_IMAGE.C_IMAGE + " BLOB")
                .append(" )");

        return sql.toString();
    }

    private String createCategoryTable() {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE " + Constants.CATEGORY.TABLE)
                .append(" (")
                .append(" " + Constants.CATEGORY.C_ID + " INTEGER PRIMARY KEY,")
                .append(" " + Constants.CATEGORY.C_NAME + " TEXT,")
                .append(" " + Constants.CATEGORY.C_IMAGE + " TEXT,")
                .append(" " + Constants.CATEGORY.C_TYPE + " INT")
                .append(" )");

        return sql.toString();
    }

    private void inicializarImagenes(SQLiteDatabase db) {
        String sql = "INSERT INTO " + Constants.CATEGORY_IMAGE.TABLE + " (" + Constants.CATEGORY_IMAGE.C_IMAGE + ") VALUES (?)";
        SQLiteStatement insertStatement = db.compileStatement(sql);
        insertStatement.clearBindings();
        insertStatement.bindString(1, Base64Images.ALIEN);
        insertStatement.executeInsert();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.CATEGORY_IMAGE.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.CATEGORY.TABLE);
        onCreate(db);
    }
}
