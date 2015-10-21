package mx.shellcore.android.micontador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import mx.shellcore.android.micontador.utils.Base64Images;
import mx.shellcore.android.micontador.utils.Constants;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "micontador.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createCategoryTable());
        db.execSQL(createCategoryImageTable());
        inicializarImagenes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.CATEGORY_IMAGE.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.CATEGORY.TABLE);
        onCreate(db);
    }

    private String createCategoryImageTable() {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE " + Constants.CATEGORY_IMAGE.TABLE)
                .append(" (")
                .append(" " + Constants.CATEGORY_IMAGE.C_ID + " INTEGER PRIMARY KEY,")
                .append(" " + Constants.CATEGORY_IMAGE.C_IMAGE + " BLOB")
                .append(" )");

        return sql.toString();
    }

    private String createCategoryTable() {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE " + Constants.CATEGORY.TABLE)
                .append(" (")
                .append(" " + Constants.CATEGORY.C_ID + " INTEGER PRIMARY KEY,")
                .append(" " + Constants.CATEGORY.C_NAME + " TEXT,")
                .append(" " + Constants.CATEGORY.C_IMAGE + " TEXT,")
                .append(" " + Constants.CATEGORY.C_TYPE + " INT")
                .append(" )");

        return sql.toString();
    }

    private void inicializarImagenes(SQLiteDatabase db) {
        ArrayList<String> listaImagenes = obtenerListaImagenes();
        for (String imagen : listaImagenes) {
            String sql = "INSERT INTO " + Constants.CATEGORY_IMAGE.TABLE + " (" + Constants.CATEGORY_IMAGE.C_IMAGE + ") VALUES (?)";
            SQLiteStatement insertStatement = db.compileStatement(sql);
            insertStatement.clearBindings();
            insertStatement.bindString(1, imagen);
            insertStatement.executeInsert();
        }
    }

    private ArrayList<String> obtenerListaImagenes() {
        ArrayList<String> imagenes = new ArrayList<>();
        imagenes.add(Base64Images.ALIEN);
        imagenes.add(Base64Images.ANALYTICS);
        imagenes.add(Base64Images.APARTMENT);
        imagenes.add(Base64Images.APPLICATION_MAP);
        imagenes.add(Base64Images.BABY_MOBILE);
        imagenes.add(Base64Images.BAG_PRESENT);
        imagenes.add(Base64Images.BATMAN);
        imagenes.add(Base64Images.BATTERY_CHARGING);
        imagenes.add(Base64Images.BEACH);
        imagenes.add(Base64Images.BELL);
        imagenes.add(Base64Images.BONSAI);
        imagenes.add(Base64Images.BUS);
        imagenes.add(Base64Images.CAMERA_FRONT);
        imagenes.add(Base64Images.CANDLES);
        imagenes.add(Base64Images.CANDY);
        imagenes.add(Base64Images.CANOE);
        imagenes.add(Base64Images.CAPTAIN_SHIELD);
        imagenes.add(Base64Images.CAR_JUMPER);
        imagenes.add(Base64Images.CASHIER);
        imagenes.add(Base64Images.CEMENT_MIXER);
        imagenes.add(Base64Images.CHAIR);
        imagenes.add(Base64Images.CHAT);
        imagenes.add(Base64Images.CHECKLIST);
        imagenes.add(Base64Images.CHEESE);
        imagenes.add(Base64Images.CHESSBOARD);
        imagenes.add(Base64Images.CLIPBOARD_PLAN);
        imagenes.add(Base64Images.CLOUDY);
        imagenes.add(Base64Images.CLOUD_MUSIC);
        imagenes.add(Base64Images.CODING_HTML);
        imagenes.add(Base64Images.COFFIN);
        imagenes.add(Base64Images.CONVEYOR_BELT);
        imagenes.add(Base64Images.DATABASE_CLOUD);
        imagenes.add(Base64Images.DESERT);
        imagenes.add(Base64Images.DNA);
        imagenes.add(Base64Images.DOWNLOAD_COMPUTER);
        imagenes.add(Base64Images.ENGAGEMENT_RING);
        imagenes.add(Base64Images.EURO_COIN);
        imagenes.add(Base64Images.EYEGLASS);
        imagenes.add(Base64Images.FOOD_DOME);
        imagenes.add(Base64Images.FURBY);
        imagenes.add(Base64Images.GOLD_CART);
        imagenes.add(Base64Images.GRAPH_MAGNIFIER);
        imagenes.add(Base64Images.HAT);
        imagenes.add(Base64Images.HEART_WATCH);
        imagenes.add(Base64Images.IMAGES);
        imagenes.add(Base64Images.IMAGES_CLOUD);
        imagenes.add(Base64Images.KEY);
        imagenes.add(Base64Images.LAPTOP_SIGNAL);
        imagenes.add(Base64Images.LOCKED_CLOUD);
        imagenes.add(Base64Images.LOVE_LETTER);
        imagenes.add(Base64Images.MAKEUP);
        imagenes.add(Base64Images.MEDAL);
        imagenes.add(Base64Images.MICROCHIP);
        imagenes.add(Base64Images.MICROSCOPE);
        imagenes.add(Base64Images.MIND_MAP_PAPER);
        imagenes.add(Base64Images.MONEY_GRAPH);
        imagenes.add(Base64Images.MONEY_INCREASE);
        imagenes.add(Base64Images.MUSIC_EQUALIZER);
        imagenes.add(Base64Images.NUCLEAR_MUSHROOM);
        imagenes.add(Base64Images.OLD_CAR);
        imagenes.add(Base64Images.ONLINE_SHOPPING);
        imagenes.add(Base64Images.OPEN_SIGN);
        imagenes.add(Base64Images.PANTONE);
        imagenes.add(Base64Images.PAPER_PLANE);
        imagenes.add(Base64Images.PARTY_POPPERS);
        imagenes.add(Base64Images.PHONE_BOOTH);
        imagenes.add(Base64Images.POLAROID);
        imagenes.add(Base64Images.PROGRAMMING);
        imagenes.add(Base64Images.PROJECTOR);
        imagenes.add(Base64Images.RADIO);
        imagenes.add(Base64Images.RECORD_PLAYER);
        imagenes.add(Base64Images.SANTA);
        imagenes.add(Base64Images.SANTA_SLED);
        imagenes.add(Base64Images.SETTINGS);
        imagenes.add(Base64Images.SETTINGS_2);
        imagenes.add(Base64Images.SHOP);
        imagenes.add(Base64Images.SMARTPHONE_MESSAGE);
        imagenes.add(Base64Images.SNEAKERS);
        imagenes.add(Base64Images.STREET_VIEW);
        imagenes.add(Base64Images.SURGEON);
        imagenes.add(Base64Images.TABLET_CHART);
        imagenes.add(Base64Images.TELEVISION_SHELF);
        imagenes.add(Base64Images.TOWER);
        imagenes.add(Base64Images.T_SHIRT);
        imagenes.add(Base64Images.VIDEO_CAMERA);
        imagenes.add(Base64Images.WIND_WHEEL);
        imagenes.add(Base64Images.WOODEN_HORSE);
        imagenes.add(Base64Images.XYLOPHONE);
        imagenes.add(Base64Images.YIN_YANG);

        return imagenes;
    }
}
