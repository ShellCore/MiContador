package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.shellcore.android.micontador.builders.CategoryBuilder;
import mx.shellcore.android.micontador.model.Category;

public class DBCategory extends DBBase<Category> {

    public DBCategory(Context context) {
        super(context, DBHelper.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Category category) {
        return CategoryBuilder.createCategoryContent(category);
    }

    @Override
    protected Category createBO(Cursor cursor) {
        return CategoryBuilder.createCategory(cursor);
    }
}
