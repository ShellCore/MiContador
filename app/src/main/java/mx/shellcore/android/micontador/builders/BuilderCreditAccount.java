package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.model.CreditAccount;
import mx.shellcore.android.micontador.utils.Constants;
import mx.shellcore.android.micontador.utils.DateUtils;

public class BuilderCreditAccount {

    public static ContentValues createContent(CreditAccount creditAccount) {
        ContentValues values = new ContentValues();

        if (creditAccount.getId() != 0) {
            values.put(Constants.CREDIT_ACCOUNT.C_ID, creditAccount.getId());
        }
        values.put(Constants.CREDIT_ACCOUNT.C_ACCOUNT_ID, creditAccount.getAccount().getId());
        values.put(Constants.CREDIT_ACCOUNT.C_COURT_DATE, DateUtils.getString(creditAccount.getCourtDate()));
        values.put(Constants.CREDIT_ACCOUNT.C_LIMIT_PAY_DAYS, creditAccount.getLimitPayDays());

        return values;
    }

    public static CreditAccount createCreditAccount(Cursor cursor) {
        CreditAccount creditAccount = new CreditAccount();

        Account account = new Account();
        account.setId(cursor.getInt(Constants.CREDIT_ACCOUNT.C_ACCOUNT_ID_INDEX));

        creditAccount.setId(cursor.getInt(Constants.CREDIT_ACCOUNT.C_ID_INDEX));
        creditAccount.setCourtDate(DateUtils.getDate(cursor.getString(Constants.CREDIT_ACCOUNT.C_COURT_DATE_INDEX)));
        creditAccount.setLimitPayDays(cursor.getInt(Constants.CREDIT_ACCOUNT.C_LIMIT_PAY_DAYS_INDEX));

        creditAccount.setAccount(account);

        return creditAccount;
    }
}
