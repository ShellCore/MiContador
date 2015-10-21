package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.CategoryImage;
import mx.shellcore.android.micontador.utils.Constants;

public class CategoryImageBuilder {

    public static ContentValues createCategoryImageContent(CategoryImage categoryImage) {
        ContentValues values = new ContentValues();

        if (categoryImage.getId() != 0) {
            values.put(Constants.CATEGORY_IMAGE.C_ID, categoryImage.getId());
        }
        values.put(Constants.CATEGORY_IMAGE.C_IMAGE, categoryImage.getImage());

        return values;
    }

    public static CategoryImage createCategoryImage(Cursor cursor) {
        CategoryImage categoryImage = new CategoryImage();

        categoryImage.setId(cursor.getInt(Constants.CATEGORY_IMAGE.C_ID_INDEX));
        categoryImage.setImage(cursor.getString(Constants.CATEGORY_IMAGE.C_IMAGE_INDEX));

        return categoryImage;
    }
}
