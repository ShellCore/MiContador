package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.db.DBHelper;
import mx.shellcore.android.micontador.model.Category;

public class CategoryBuilder {

    public static ContentValues createCategoryContent(Category category) {
        ContentValues values = new ContentValues();

        if (category.getId() != 0) {
            values.put(DBHelper.C_ID, category.getId());
        }
        values.put(DBHelper.C_NAME, category.getName());
        values.put(DBHelper.C_IMAGE, category.getLogo());
        values.put(DBHelper.C_TYPE, category.getType());

        return values;
    }

    public static Category createCategory(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getInt(DBHelper.C_ID_INDEX));
        category.setName(cursor.getString(DBHelper.C_NAME_INDEX));
        category.setLogo(cursor.getString(DBHelper.C_IMAGE_INDEX));
        category.setType(cursor.getInt(DBHelper.C_TYPE_INDEX));

        return category;
    }
}
