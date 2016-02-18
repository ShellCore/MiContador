package mx.shellcore.android.micontador.builders;

import android.content.ContentValues;
import android.database.Cursor;

import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.model.Movement;
import mx.shellcore.android.micontador.utils.DBTables;
import mx.shellcore.android.micontador.utils.DateUtils;

public class BuilderMovement {

    public static ContentValues createContentValue(Movement movement) {
        ContentValues contentValues = new ContentValues();

        if (movement.getId() != 0) {
            contentValues.put(DBTables.MOVEMENT.C_ID, movement.getId());
        }
        contentValues.put(DBTables.MOVEMENT.C_AMOUNT, movement.getAmount());
        contentValues.put(DBTables.MOVEMENT.C_TYPE, movement.getType());
        contentValues.put(DBTables.MOVEMENT.C_DATE, DateUtils.getString(movement.getDate()));
        contentValues.put(DBTables.MOVEMENT.C_DESCRIPTION, movement.getDescription());
        contentValues.put(DBTables.MOVEMENT.C_ACCOUNT_ID, movement.getAccount().getId());
        contentValues.put(DBTables.MOVEMENT.C_CATEGORY_ID, movement.getCategory().getId());

        return contentValues;
    }

    public static Movement createMovement(Cursor cursor) {
        Movement movement = new Movement();

        movement.setId(cursor.getInt(cursor.getColumnIndex(DBTables.MOVEMENT.C_ID)));
        movement.setAmount(cursor.getDouble(cursor.getColumnIndex(DBTables.MOVEMENT.C_AMOUNT)));
        movement.setType(cursor.getInt(cursor.getColumnIndex(DBTables.MOVEMENT.C_TYPE)));
        movement.setDate(DateUtils.getDate(cursor.getString(cursor.getColumnIndex(DBTables.MOVEMENT.C_DATE))));
        movement.setDescription(cursor.getString(cursor.getColumnIndex(DBTables.MOVEMENT.C_DESCRIPTION)));

        Account account = new Account();
        account.setId(cursor.getInt(cursor.getColumnIndex(DBTables.MOVEMENT.C_ACCOUNT_ID)));
        movement.setAccount(account);

        Category category = new Category();
        category.setId(cursor.getInt(cursor.getColumnIndex(DBTables.MOVEMENT.C_CATEGORY_ID)));
        movement.setCategory(category);

        return movement;
    }
}
