package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.shellcore.android.micontador.builders.BuilderAccount;
import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.utils.Constants;

public class DBAccount extends DBBase<Account> {

    public DBAccount(Context context) {
        super(context, Constants.ACCOUNT.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Account account) {
        return BuilderAccount.createContent(account);
    }

    @Override
    protected Account createBO(Cursor cursor) {
        return BuilderAccount.createAccount(cursor);
    }
}
