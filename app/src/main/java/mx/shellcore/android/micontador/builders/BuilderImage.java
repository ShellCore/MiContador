package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.Constants;
import mx.shellcore.android.micontador.utils.PathUtils;

public class BuilderImage {

    public static ContentValues createCategoryImageContent(Image image) {
        ContentValues values = new ContentValues();

        if (image.getId() != 0) {
            values.put(Constants.IMAGE.C_ID, image.getId());
        }
        values.put(Constants.IMAGE.C_IMAGE, image.getImage());

        return values;
    }

    public static Image createCategoryImage(Cursor cursor) {
        Image image = new Image();

        image.setId(cursor.getInt(Constants.IMAGE.C_ID_INDEX));
        image.setImage(cursor.getString(Constants.IMAGE.C_IMAGE_INDEX));
        image.setType(cursor.getInt(Constants.IMAGE.C_TYPE_INDEX));

        return image;
    }
}
