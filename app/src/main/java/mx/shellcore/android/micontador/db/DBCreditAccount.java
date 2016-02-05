package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.shellcore.android.micontador.builders.BuilderCreditAccount;
import mx.shellcore.android.micontador.model.CreditAccount;
import mx.shellcore.android.micontador.utils.DBTables;

public class DBCreditAccount extends DBBase<CreditAccount> {

    public DBCreditAccount(Context context) {
        super(context, DBTables.CREDIT_ACCOUNT.TABLE);
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

        String[] whereArgs = {String.valueOf(idAccount)};

        Cursor cursor = database.query(DBTables.CREDIT_ACCOUNT.TABLE, null, DBTables.CREDIT_ACCOUNT.C_ID + " = ?", whereArgs, null, null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        return BuilderCreditAccount.createCreditAccount(cursor);
    }
}
