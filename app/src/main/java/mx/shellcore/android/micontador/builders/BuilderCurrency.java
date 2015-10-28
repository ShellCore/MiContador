package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.utils.Constants;

public class BuilderCurrency {
    public static ContentValues createContent(Currency currency) {
        ContentValues values = new ContentValues();

        if (currency.getId() != 0) {
            values.put(Constants.CURRENCY.C_ID, currency.getId());
        }
        values.put(Constants.CURRENCY.C_CURRENCY, currency.getCurrency());

        return values;
    }

    public static Currency createCurrency(Cursor cursor) {
        Currency currency = new Currency();

        currency.setId(cursor.getInt(Constants.CURRENCY.C_ID_INDEX));
        currency.setCurrency(cursor.getString(Constants.CURRENCY.C_CURRENCY_INDEX));

        return currency;
    }
}
