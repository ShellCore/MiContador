package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import mx.shellcore.android.micontador.builders.BuilderAccount;
import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.utils.DBTables;

public class DBAccount extends DBBase<Account> {

    public DBAccount(Context context) {
        super(context, DBTables.ACCOUNT.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Account account) {
        return BuilderAccount.createContent(account);
    }

    @Override
    protected Account createBO(Cursor cursor) {
        return BuilderAccount.createAccount(cursor);
    }

    public Account getByIdComplete(int account) {
        String[] whereArgs = new String[]{
                String.valueOf(account)
        };

        String sql = "SELECT"
                + " a.*,"
                + " b." + DBTables.IMAGE.C_PATH + ","
                + " b." + DBTables.IMAGE.C_TYPE + ","
                + " c." + DBTables.CURRENCY.C_NAME + ","
                + " c." + DBTables.CURRENCY.C_SYMBOL
                + " FROM " + DBTables.ACCOUNT.TABLE + " a"
                + " INNER JOIN " + DBTables.IMAGE.TABLE + " b"
                + " ON a." + DBTables.ACCOUNT.C_ACCOUNT_IMAGE_ID + " = b." + DBTables.IMAGE.C_ID
                + " INNER JOIN " + DBTables.CURRENCY.TABLE + " c"
                + " ON a." + DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID + " = c." + DBTables.CURRENCY.C_ID
                + " WHERE a." + DBTables.ACCOUNT.C_ID + " = ?";

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, whereArgs);

        if (cursor.moveToFirst()) {
            return BuilderAccount.createAccountComplete(cursor);
        }
        return null;
    }

    public ArrayList<Account> getAllFull() {
        ArrayList<Account> list = new ArrayList<>();

        String sql = "SELECT"
                + " a.*,"
                + " b." + DBTables.IMAGE.C_PATH + ","
                + " b." + DBTables.IMAGE.C_TYPE + ","
                + " c." + DBTables.CURRENCY.C_NAME + ","
                + " c." + DBTables.CURRENCY.C_SYMBOL
                + " FROM " + DBTables.ACCOUNT.TABLE + " a"
                + " INNER JOIN " + DBTables.IMAGE.TABLE + " b"
                + " ON a." + DBTables.ACCOUNT.C_ACCOUNT_IMAGE_ID + " = b." + DBTables.IMAGE.C_ID
                + " INNER JOIN " + DBTables.CURRENCY.TABLE + " c"
                + " ON a." + DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID + " = c." + DBTables.CURRENCY.C_ID;

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            if (!cursor.isAfterLast()) {
                Account account = BuilderAccount.createAccountComplete(cursor);
                list.add(account);
                cursor.moveToNext();
            }
        }

        return list;
    }
}
