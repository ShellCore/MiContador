package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import mx.shellcore.android.micontador.builders.BuilderImage;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.DBTables;

public class DBImage extends DBBase<Image> {

    public DBImage(Context context) {
        super(context, DBTables.IMAGE.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Image image) {
        return BuilderImage.createCategoryImageContent(image);
    }

    @Override
    protected Image createBO(Cursor cursor) {
        return BuilderImage.createCategoryImage(cursor);
    }

    public ArrayList<Image> getAllByType(int type) {
        ArrayList<Image> list = new ArrayList<>();

        String[] whereArgs = new String[] {
                String.valueOf(type)
        };

        String sql = "SELECT *"
                + " FROM " + DBTables.IMAGE.TABLE + " a"
                + " WHERE a." + DBTables.IMAGE.C_TYPE + " = ?"
                + " ORDER BY " + DBTables.IMAGE.C_PATH;

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, whereArgs);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Image image = BuilderImage.createCategoryImage(cursor);
                list.add(image);
                cursor.moveToNext();
            }
        }

        return list;
    }
}
