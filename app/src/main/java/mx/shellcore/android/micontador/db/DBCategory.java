package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mx.shellcore.android.micontador.builders.CategoryBuilder;
import mx.shellcore.android.micontador.model.Category;

public class DBCategory implements DBBase<Category> {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBCategory(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void create(Category category) {
        ContentValues values = CategoryBuilder.createCategoryContent(category);

        database = dbHelper.getWritableDatabase();
        try {
            database.insertWithOnConflict(DBHelper.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        } finally {
            database.close();
        }
    }

    @Override
    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();

        database = dbHelper.getReadableDatabase();
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

    @Override
    public Category getById(int id) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE, null, DBHelper.C_ID + "=" + id, null, null, null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        return CategoryBuilder.createCategory(cursor);
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }


}
