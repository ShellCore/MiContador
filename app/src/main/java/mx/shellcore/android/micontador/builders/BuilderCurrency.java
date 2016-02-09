package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.utils.DBTables;

public class BuilderCurrency {
    public static ContentValues createContent(Currency currency) {
        ContentValues values = new ContentValues();

        if (currency.getId() != 0) {
            values.put(DBTables.CURRENCY.C_ID, currency.getId());
        }
        values.put(DBTables.CURRENCY.C_NAME, currency.getName());
        values.put(DBTables.CURRENCY.C_SYMBOL, currency.getSymbol());

        return values;
    }

    public static Currency createCurrency(Cursor cursor) {
        Currency currency = new Currency();

        currency.setId(cursor.getInt(cursor.getColumnIndex(DBTables.CURRENCY.C_ID)));
        currency.setName(cursor.getString(cursor.getColumnIndex(DBTables.CURRENCY.C_NAME)));
        currency.setSymbol(cursor.getString(cursor.getColumnIndex(DBTables.CURRENCY.C_SYMBOL)));

        return currency;
    }
}
