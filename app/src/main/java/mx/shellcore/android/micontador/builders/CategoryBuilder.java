package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.utils.Constants;

public class CategoryBuilder {

    public static ContentValues createCategoryContent(Category category) {
        ContentValues values = new ContentValues();

        if (category.getId() != 0) {
            values.put(Constants.CATEGORY.C_ID, category.getId());
        }
        values.put(Constants.CATEGORY.C_NAME, category.getName());
        values.put(Constants.CATEGORY.C_IMAGE, category.getLogo());
        values.put(Constants.CATEGORY.C_TYPE, category.getType());

        return values;
    }

    public static Category createCategory(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getInt(Constants.CATEGORY.C_ID_INDEX));
        category.setName(cursor.getString(Constants.CATEGORY.C_NAME_INDEX));
        category.setLogo(cursor.getString(Constants.CATEGORY.C_IMAGE_INDEX));
        category.setType(cursor.getInt(Constants.CATEGORY.C_TYPE_INDEX));

        return category;
    }
}
