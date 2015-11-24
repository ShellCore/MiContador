package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.shellcore.android.micontador.builders.BuilderCreditAccount;
import mx.shellcore.android.micontador.model.CreditAccount;
import mx.shellcore.android.micontador.utils.Constants;

public class DBCreditAccount extends DBBase<CreditAccount> {

    public DBCreditAccount(Context context) {
        super(context, Constants.CREDIT_ACCOUNT.TABLE);
    }

    @Override
    protected ContentValues createContentValue(CreditAccount creditAccount) {
        return BuilderCreditAccount.createContent(creditAccount);
    }

    @Override
    protected CreditAccount createBO(Cursor cursor) {
        return BuilderCreditAccount.createCreditAccount(cursor);
    }

    public CreditAccount getByAccountId(int idAccount) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constants.CREDIT_ACCOUNT.TABLE, null, Constants.CREDIT_ACCOUNT.C_ACCOUNT_ID + "=" + idAccount, null, null, null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        return BuilderCreditAccount.createCreditAccount(cursor);
    }
}
