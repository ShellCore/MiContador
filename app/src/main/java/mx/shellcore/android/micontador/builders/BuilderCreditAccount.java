package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.model.CreditAccount;
import mx.shellcore.android.micontador.utils.DBTables;

public class BuilderCreditAccount {

    public static ContentValues createContent(CreditAccount creditAccount) {
        ContentValues values = new ContentValues();

        if (creditAccount.getId() != 0) {
            values.put(DBTables.CREDIT_ACCOUNT.C_ID, creditAccount.getId());
        }
        values.put(DBTables.CREDIT_ACCOUNT.C_COURT_DATE, creditAccount.getCourtDay());
        values.put(DBTables.CREDIT_ACCOUNT.C_LIMIT_PAY_DAYS, creditAccount.getLimitPayDay());
        values.put(DBTables.CREDIT_ACCOUNT.C_CREDIT_LIMIT, creditAccount.getCreditLimit());

        return values;
    }

    public static CreditAccount createCreditAccount(Cursor cursor) {
        CreditAccount creditAccount = new CreditAccount();

        Account account = new Account();
        account.setId(cursor.getInt(cursor.getColumnIndex(DBTables.CREDIT_ACCOUNT.C_ID)));

        creditAccount.setId(cursor.getInt(cursor.getColumnIndex(DBTables.CREDIT_ACCOUNT.C_ID)));
        creditAccount.setCourtDay(cursor.getInt(cursor.getColumnIndex(DBTables.CREDIT_ACCOUNT.C_COURT_DATE)));
        creditAccount.setLimitPayDay(cursor.getInt(cursor.getColumnIndex(DBTables.CREDIT_ACCOUNT.C_LIMIT_PAY_DAYS)));
        creditAccount.setCreditLimit(cursor.getDouble(cursor.getColumnIndex(DBTables.CREDIT_ACCOUNT.C_CREDIT_LIMIT)));

        creditAccount.setAccount(account);

        return creditAccount;
    }
}
