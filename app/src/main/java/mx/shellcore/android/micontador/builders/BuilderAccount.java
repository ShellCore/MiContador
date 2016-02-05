package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.DBTables;

public class BuilderAccount {

    public static ContentValues createContent (Account account) {
        ContentValues values = new ContentValues();

        if (account.getId() != 0) {
            values.put(DBTables.ACCOUNT.C_ID, account.getId());
        }
        values.put(DBTables.ACCOUNT.C_NAME, account.getName());
        values.put(DBTables.ACCOUNT.C_TYPE, account.getType());
        values.put(DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID, account.getCurrency().getId());
        values.put(DBTables.ACCOUNT.C_BEGINNING_BALANCE, account.getBeginningBalance());

        return values;
    }

    public static Account createAccount(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ID)));
        account.setName(cursor.getString(cursor.getColumnIndex(DBTables.ACCOUNT.C_NAME)));
        account.setType(cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_TYPE)));
        account.setBeginningBalance(cursor.getDouble(cursor.getColumnIndex(DBTables.ACCOUNT.C_BEGINNING_BALANCE)));

        if (cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID)) != 0) {
            Currency currency = new Currency();
            currency.setId(cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID)));
            account.setCurrency(currency);
        }

        return account;
    }

    public static Account createAccountComplete(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ID)));
        account.setName(cursor.getString(cursor.getColumnIndex(DBTables.ACCOUNT.C_NAME)));
        account.setBeginningBalance(cursor.getDouble(cursor.getColumnIndex(DBTables.ACCOUNT.C_BEGINNING_BALANCE)));
        account.setType(cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_TYPE)));

        if (cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID)) != 0) {
            Currency currency = new Currency();
            currency.setId(cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID)));
            currency.setCurrency(cursor.getString(cursor.getColumnIndex(DBTables.CURRENCY.C_NAME)));
            currency.setSymbol(cursor.getString(cursor.getColumnIndex(DBTables.CURRENCY.C_SYMBOL)));
            account.setCurrency(currency);
        }

        if (cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ACCOUNT_IMAGE_ID)) != 0) {
            Image image = new Image();
            image.setId(cursor.getInt(cursor.getColumnIndex(DBTables.ACCOUNT.C_ACCOUNT_IMAGE_ID)));
            image.setPath(cursor.getString(cursor.getColumnIndex(DBTables.IMAGE.C_PATH)));
            image.setType(cursor.getInt(cursor.getColumnIndex(DBTables.IMAGE.C_TYPE)));
            account.setImage(image);
        }

        return account;
    }
}
