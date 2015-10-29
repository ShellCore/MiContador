package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.utils.Constants;

public class BuilderAccount {

    public static ContentValues createContent (Account account) {
        ContentValues values = new ContentValues();

        if (account.getId() != 0) {
            values.put(Constants.ACCOUNT.C_ID, account.getId());
        }
        values.put(Constants.ACCOUNT.C_NAME, account.getName());
        values.put(Constants.ACCOUNT.C_TYPE, account.getType());
        values.put(Constants.ACCOUNT.C_CURRENCY_ID, account.getCurrency().getId());
        values.put(Constants.ACCOUNT.C_BEGINNING_BALANCE, account.getBeginningBalance());

        return values;
    }

    public static Account createAccount(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getInt(Constants.ACCOUNT.C_ID_INDEX));
        account.setName(cursor.getString(Constants.ACCOUNT.C_NAME_INDEX));
        account.setType(cursor.getInt(Constants.ACCOUNT.C_TYPE_INDEX));
        account.setBeginningBalance(cursor.getDouble(Constants.ACCOUNT.C_BEGINNING_BALANCE_INDEX));

        Currency currency = new Currency();
        currency.setId(cursor.getInt(Constants.ACCOUNT.C_CURRENCY_ID_INDEX));
        account.setCurrency(currency);

        return account;
    }
}
