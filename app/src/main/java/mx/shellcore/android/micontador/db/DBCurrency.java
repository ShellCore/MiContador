package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import mx.shellcore.android.micontador.builders.BuilderCurrency;
import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.utils.DBTables;

public class DBCurrency extends DBBase<Currency> {

    public DBCurrency(Context context) {
        super(context, DBTables.CURRENCY.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Currency currency) {
        return BuilderCurrency.createContent(currency);
    }

    @Override
    protected Currency createBO(Cursor cursor) {
        return BuilderCurrency.createCurrency(cursor);
    }

    public ArrayList<Currency> getAllByCurrency() {
        ArrayList<Currency> currencies = new ArrayList<>();

        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBTables.CURRENCY.TABLE, null, null, null, null, null, DBTables.CURRENCY.C_NAME);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Currency currency = BuilderCurrency.createCurrency(cursor);
                currencies.add(currency);
                cursor.moveToNext();
            }
        }

        return currencies;
    }
}
