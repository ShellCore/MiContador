package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.shellcore.android.micontador.builders.CategoryImageBuilder;
import mx.shellcore.android.micontador.model.CategoryImage;
import mx.shellcore.android.micontador.utils.Constants;

public class DBCategoryImage extends DBBase<CategoryImage> {

    public DBCategoryImage(Context context) {
        super(context, Constants.CATEGORY_IMAGE.TABLE);
    }

    @Override
    protected ContentValues createContentValue(CategoryImage categoryImage) {
        return CategoryImageBuilder.createCategoryImageContent(categoryImage);
    }

    @Override
    protected CategoryImage createBO(Cursor cursor) {
        return CategoryImageBuilder.createCategoryImage(cursor);
    }
}
