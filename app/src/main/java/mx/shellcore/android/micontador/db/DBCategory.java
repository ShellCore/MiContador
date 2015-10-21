package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import mx.shellcore.android.micontador.builders.CategoryBuilder;
import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.utils.Constants;

public class DBCategory extends DBBase<Category> {

    public DBCategory(Context context) {
        super(context, Constants.CATEGORY.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Category category) {
        return CategoryBuilder.createCategoryContent(category);
    }

    @Override
    protected Category createBO(Cursor cursor) {
        return CategoryBuilder.createCategory(cursor);
    }

    public ArrayList<Category> getAllByType(int type) {
        ArrayList<Category> list = new ArrayList<>();

        String[] whereArgs = new String[] {
                String.valueOf(type)
        };


        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constants.CATEGORY.TABLE, null, Constants.CATEGORY.C_TYPE + "=?", whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Category category = createBO(cursor);
                list.add(category);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
