package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

public abstract class DBBase<BO> {

    private DBHelper dbHelper;

    private SQLiteDatabase database;
    private final String table;
    private final String C_ID = BaseColumns._ID;

    public DBBase(Context context, String table) {
        dbHelper = new DBHelper(context);
        this.table = table;
    }

    public void create(BO bo) {
        ContentValues values = createContentValue(bo);
        database = dbHelper.getWritableDatabase();
        try {
            database.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        } finally {
            database.close();
        }
    }

    public ArrayList<BO> getAll() {
        ArrayList<BO> list = new ArrayList<>();

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(table, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                BO bo = createBO(cursor);
                list.add(bo);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public BO getById(int id) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(table, null, C_ID + "=" + id, null, null, null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        return createBO(cursor);
    }

    public void update(BO bo, int id) {
        ContentValues values = createContentValue(bo);

        database = dbHelper.getWritableDatabase();
        try {
            database.updateWithOnConflict(table, values, C_ID + "=" + id, null, SQLiteDatabase.CONFLICT_IGNORE);
        } finally {
            dbHelper.close();
        }
    }

    public void delete(int id) {
        database = dbHelper.getWritableDatabase();
        database.delete(table, C_ID + "=" + id, null);
        dbHelper.close();
    }

    protected abstract ContentValues createContentValue(BO bo);
    protected abstract BO createBO(Cursor cursor);
}
