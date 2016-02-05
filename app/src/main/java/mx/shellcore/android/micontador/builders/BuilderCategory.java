package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.DBTables;

public class BuilderCategory {

    public static ContentValues createCategoryContent(Category category) {
        ContentValues values = new ContentValues();

        if (category.getId() != 0) {
            values.put(DBTables.CATEGORY.C_ID, category.getId());
        }
        values.put(DBTables.CATEGORY.C_NAME, category.getName());
        values.put(DBTables.CATEGORY.C_TYPE, category.getType());

        if (category.getLogo() != null) {
            values.put(DBTables.CATEGORY.C_CATEGORY_IMAGE_ID, category.getLogo().getId());
        } else {
            values.put(DBTables.CATEGORY.C_CATEGORY_IMAGE_ID, 0);
        }

        return values;
    }

    public static Category createCategory(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_ID)));
        category.setName(cursor.getString(cursor.getColumnIndex(DBTables.CATEGORY.C_NAME)));
        category.setType(cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_TYPE)));

        if (cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_CATEGORY_IMAGE_ID)) != 0) {
            Image image = new Image();
            image.setId(cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_CATEGORY_IMAGE_ID)));
            category.setLogo(image);
        }

        return category;
    }

    public static Category createBOComplete(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_ID)));
        category.setName(cursor.getString(cursor.getColumnIndex(DBTables.CATEGORY.C_NAME)));
        category.setType(cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_TYPE)));

        if (cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_CATEGORY_IMAGE_ID)) != 0) {
            Image image = new Image();
            image.setId(cursor.getInt(cursor.getColumnIndex(DBTables.CATEGORY.C_CATEGORY_IMAGE_ID))); //4
            image.setPath(cursor.getString(cursor.getColumnIndex(DBTables.IMAGE.C_PATH))); //5
            image.setType(cursor.getInt(cursor.getColumnIndex(DBTables.IMAGE.C_TYPE)));
            category.setLogo(image);
        }

        return category;
    }
}
