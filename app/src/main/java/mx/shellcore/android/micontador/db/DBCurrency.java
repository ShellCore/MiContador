package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.shellcore.android.micontador.builders.BuilderCurrency;
import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.utils.Constants;

public class DBCurrency extends DBBase<Currency> {

    public DBCurrency(Context context) {
        super(context, Constants.CURRENCY.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Currency currency) {
        return BuilderCurrency.createContent(currency);
    }

    @Override
    protected Currency createBO(Cursor cursor) {
        return BuilderCurrency.createCurrency(cursor);
    }
}
