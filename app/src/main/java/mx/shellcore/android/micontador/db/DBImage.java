package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import mx.shellcore.android.micontador.builders.BuilderCategoryImage;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.Constants;

public class DBImage extends DBBase<Image> {

    public DBImage(Context context) {
        super(context, Constants.IMAGE.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Image image) {
        return BuilderCategoryImage.createCategoryImageContent(image);
    }

    @Override
    protected Image createBO(Cursor cursor) {
        return BuilderCategoryImage.createCategoryImage(cursor);
    }

    public ArrayList<Image> getAllByType(int type) {
        ArrayList<Image> list = new ArrayList<>();

        String[] whereArgs = new String[] {
                String.valueOf(type)
        };

        String sql = "SELECT *"
                + " FROM " + Constants.IMAGE.TABLE + " a"
                + " WHERE a." + Constants.IMAGE.C_TYPE + " = ?"
                + " ORDER BY " + Constants.IMAGE.C_IMAGE;

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, whereArgs);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Image image = BuilderCategoryImage.createCategoryImage(cursor);
                list.add(image);
                cursor.moveToNext();
            }
        }

        return list;
    }
}
