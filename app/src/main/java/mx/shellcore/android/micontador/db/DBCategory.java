package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mx.shellcore.android.micontador.builders.CategoryBuilder;
import mx.shellcore.android.micontador.model.Category;

public class DBCategory {

    private DBHelper dbHelper;

    public DBCategory(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void create(ContentValues values) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            database.insertWithOnConflict(DBHelper.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        } finally {
            database.close();
        }
    }

    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Category category = CategoryBuilder.createCategory(cursor);
                categories.add(category);
                cursor.moveToNext();
            }
        }

        return categories;
    }
}
