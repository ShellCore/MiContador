package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import mx.shellcore.android.micontador.builders.BuilderCategory;
import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.utils.Constants;

public class DBCategory extends DBBase<Category> {

    public DBCategory(Context context) {
        super(context, Constants.CATEGORY.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Category category) {
        return BuilderCategory.createCategoryContent(category);
    }

    @Override
    protected Category createBO(Cursor cursor) {
        return BuilderCategory.createCategory(cursor);
    }

    public ArrayList<Category> getAllByTypeFull(int type) {
        ArrayList<Category> list = new ArrayList<>();

        String[] whereArgs = new String[] {
                String.valueOf(type)
        };

        String sql = "SELECT *"
                + " FROM " + Constants.CATEGORY.TABLE + " a"
                + " INNER JOIN " + Constants.IMAGE.TABLE + " b"
                + " ON a." + Constants.CATEGORY.C_CATEGORY_IMAGE_ID + " = b." + Constants.IMAGE.C_ID
                + " WHERE a." + Constants.CATEGORY.C_TYPE + " = ?"
                + " ORDER BY " + Constants.CATEGORY.C_NAME;

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, whereArgs);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Category category = BuilderCategory.createBOComplete(cursor);
                list.add(category);
                cursor.moveToNext();
            }
        }

        return list;
    }

    public Category getByIdComplete(int category) {
        String[] whereArgs = new String[] {
                String.valueOf(category)
        };

        String sql = "SELECT *"
                + " FROM " + Constants.CATEGORY.TABLE + " a"
                + " INNER JOIN " + Constants.IMAGE.TABLE + " b"
                + " ON a." + Constants.CATEGORY.C_CATEGORY_IMAGE_ID + " = b." + Constants.IMAGE.C_ID
                + " WHERE a." + Constants.CATEGORY.C_ID + " = ?";

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, whereArgs);

        if (cursor.moveToFirst()) {
            return BuilderCategory.createBOComplete(cursor);
        }
        return null;
    }
}
