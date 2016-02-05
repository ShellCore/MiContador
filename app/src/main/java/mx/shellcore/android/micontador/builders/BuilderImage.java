package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.DBTables;

public class BuilderImage {

    public static ContentValues createCategoryImageContent(Image image) {
        ContentValues values = new ContentValues();

        if (image.getId() != 0) {
            values.put(DBTables.IMAGE.C_ID, image.getId());
        }
        values.put(DBTables.IMAGE.C_PATH, image.getPath());

        return values;
    }

    public static Image createCategoryImage(Cursor cursor) {
        Image image = new Image();

        image.setId(cursor.getInt(cursor.getColumnIndex(DBTables.IMAGE.C_ID)));
        image.setPath(cursor.getString(cursor.getColumnIndex(DBTables.IMAGE.C_PATH)));
        image.setType(cursor.getInt(cursor.getColumnIndex(DBTables.IMAGE.C_TYPE)));

        return image;
    }
}
