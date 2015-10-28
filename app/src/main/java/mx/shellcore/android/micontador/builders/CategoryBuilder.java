package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.model.CategoryImage;
import mx.shellcore.android.micontador.utils.Constants;

public class CategoryBuilder {

    public static ContentValues createCategoryContent(Category category) {
        ContentValues values = new ContentValues();

        if (category.getId() != 0) {
            values.put(Constants.CATEGORY.C_ID, category.getId());
        }
        values.put(Constants.CATEGORY.C_NAME, category.getName());
        values.put(Constants.CATEGORY.C_TYPE, category.getType());

        if (category.getLogo() != null) {
            values.put(Constants.CATEGORY.C_CATEGORY_IMAGE_ID, category.getLogo().getId());
        } else {
            values.put(Constants.CATEGORY.C_CATEGORY_IMAGE_ID, 0);
        }

        return values;
    }

    public static Category createCategory(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getInt(Constants.CATEGORY.C_ID_INDEX));
        category.setName(cursor.getString(Constants.CATEGORY.C_NAME_INDEX));
        category.setType(cursor.getInt(Constants.CATEGORY.C_TYPE_INDEX));

        if (cursor.getInt(Constants.CATEGORY_IMAGE.C_ID_INDEX) != 0) {
            CategoryImage categoryImage = new CategoryImage();
            categoryImage.setId(cursor.getInt(Constants.CATEGORY.C_CATEGORY_IMAGE_ID_INDEX));
            category.setLogo(categoryImage);
        }

        return category;
    }

    public static Category createBOComplete(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getInt(Constants.CATEGORY.C_ID_INDEX));
        category.setName(cursor.getString(Constants.CATEGORY.C_NAME_INDEX));
        category.setType(cursor.getInt(Constants.CATEGORY.C_TYPE_INDEX));

        if (cursor.getInt(Constants.CATEGORY_IMAGE.C_ID_INDEX) != 0) {
            CategoryImage categoryImage = new CategoryImage();
            categoryImage.setId(cursor.getInt(4));
            categoryImage.setImage(cursor.getString(5));
            category.setLogo(categoryImage);
        }

        return category;
    }
}
