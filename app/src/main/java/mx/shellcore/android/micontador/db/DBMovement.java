package mx.shellcore.android.micontador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.shellcore.android.micontador.builders.BuilderMovement;
import mx.shellcore.android.micontador.model.Movement;
import mx.shellcore.android.micontador.utils.DBTables;

public class DBMovement extends DBBase<Movement> {

    public DBMovement(Context context) {
        super(context, DBTables.MOVEMENT.TABLE);
    }

    @Override
    protected ContentValues createContentValue(Movement movement) {
        return BuilderMovement.createContentValue(movement);
    }

    @Override
    protected Movement createBO(Cursor cursor) {
        return BuilderMovement.createMovement(cursor);
    }
}
