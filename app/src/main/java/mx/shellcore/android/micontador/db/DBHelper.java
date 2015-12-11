package mx.shellcore.android.micontador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.model.Currency;
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
        db.execSQL(createCurrencyTable());
        db.execSQL(createAccountTable());

        initializeImages(db);
        initializeCurrencies(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.CATEGORY.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.CATEGORY_IMAGE.TABLE);
        onCreate(db);
    }

    private String createCategoryTable() {
        return "CREATE TABLE " + Constants.CATEGORY.TABLE
                + " ("
                + " " + Constants.CATEGORY.C_ID + " INTEGER PRIMARY KEY,"
                + " " + Constants.CATEGORY.C_NAME + " TEXT,"
                + " " + Constants.CATEGORY.C_TYPE + " INT,"
                + " " + Constants.CATEGORY.C_CATEGORY_IMAGE_ID + " INTEGER,"
                + " FOREIGN KEY ( " + Constants.CATEGORY.C_CATEGORY_IMAGE_ID + " ) REFERENCES " + Constants.CATEGORY_IMAGE.TABLE + " ( " + Constants.CATEGORY_IMAGE.C_ID + " ) "
                + " )";
    }

    private String createCategoryImageTable() {
        return "CREATE TABLE " + Constants.CATEGORY_IMAGE.TABLE
                + " ("
                + " " + Constants.CATEGORY_IMAGE.C_ID + " INTEGER PRIMARY KEY,"
                + " " + Constants.CATEGORY_IMAGE.C_IMAGE + " BLOB"
                + " )";
    }

    private String createCurrencyTable() {
        return "CREATE TABLE " + Constants.CURRENCY.TABLE
                + " ("
                + " " + Constants.CURRENCY.C_ID + " INTEGER PRYMARY KEY,"
                + " " + Constants.CURRENCY.C_CURRENCY + " TEXT,"
                + " " + Constants.CURRENCY.C_SYMBOL + " TEXT"
                +" )";
    }

    private String createAccountTable() {
        return "CREATE TABLE " + Constants.ACCOUNT.TABLE
                + " ("
                + " " + Constants.ACCOUNT.C_ID + " INTEGER PRIMARY KEY,"
                + " " + Constants.ACCOUNT.C_NAME + " TEXT,"
                + " " + Constants.ACCOUNT.C_TYPE + " INT,"
                + " " + Constants.ACCOUNT.C_CURRENCY_ID + " INTEGER,"
                + " " + Constants.ACCOUNT.C_BEGINNING_BALANCE + " REAL,"
                + " FOREIGN KEY ( " + Constants.ACCOUNT.C_CURRENCY_ID + " ) REFERENCES " + Constants.CURRENCY.TABLE + " ( " + Constants.CURRENCY.C_ID + " ) "
                + " )";
    }

    private String createCreditTable() {
        return "CREATE TABLE " + Constants.CREDIT_ACCOUNT.TABLE
                + " ("
                + " " + Constants.CREDIT_ACCOUNT.C_ID + " INTEGER PRIMARY KEY,"
                + " " + Constants.CREDIT_ACCOUNT.C_ACCOUNT_ID + " INTEGER,"
                + " " + Constants.CREDIT_ACCOUNT.C_COURT_DATE + " TEXT,"
                + " " + Constants.CREDIT_ACCOUNT.C_LIMIT_PAY_DAYS + " INT,"
                + " FOREIGN KEY ( " + Constants.CREDIT_ACCOUNT.C_ACCOUNT_ID + " ) REFERENCES " + Constants.ACCOUNT.TABLE + " ( " + Constants.ACCOUNT.C_ID + " ) "
                + " )";

    }

    private void initializeImages(SQLiteDatabase db) {
        ArrayList<String> images = getImages();
        for (String image : images) {

            String sql = "INSERT INTO " + Constants.CATEGORY_IMAGE.TABLE
                    + " ("
                    + Constants.CATEGORY_IMAGE.C_IMAGE
                    + " )"
                    + " VALUES (?)";

            SQLiteStatement insertStatement = db.compileStatement(sql);
            insertStatement.clearBindings();
            insertStatement.bindString(1, image);
            insertStatement.executeInsert();
        }
    }

    private void initializeCurrencies(SQLiteDatabase db) {
        ArrayList<Currency> currencies = getCurrencies();

        for (Currency currency : currencies) {
            String sql = "INSERT INTO " + Constants.CURRENCY.TABLE
                    + " ("
                    + Constants.CURRENCY.C_CURRENCY
                    + " )"
                    + " VALUES (?, ?)";

            SQLiteStatement insertStatement = db.compileStatement(sql);
            insertStatement.clearBindings();
            insertStatement.bindString(1, currency.getCurrency());
            insertStatement.bindString(2, currency.getSymbol());
            insertStatement.executeInsert();
        }
    }

    private ArrayList<String> getImages() {
        ArrayList<String> images = new ArrayList<>();

        images.add(getPath(R.drawable.alien));
        images.add(getPath(R.drawable.analytics));
        images.add(getPath(R.drawable.apartment));
        images.add(getPath(R.drawable.application_map));
        images.add(getPath(R.drawable.baby_mobile));
        images.add(getPath(R.drawable.bag_present));
        images.add(getPath(R.drawable.batman));
        images.add(getPath(R.drawable.battery_charging));
        images.add(getPath(R.drawable.beach));
        images.add(getPath(R.drawable.bell));
        images.add(getPath(R.drawable.bonsai));
        images.add(getPath(R.drawable.bus));
        images.add(getPath(R.drawable.camera_front));
        images.add(getPath(R.drawable.candles));
        images.add(getPath(R.drawable.candy));
        images.add(getPath(R.drawable.canoe));
        images.add(getPath(R.drawable.captain_shield));
        images.add(getPath(R.drawable.car_jumper));
        images.add(getPath(R.drawable.cashier));
        images.add(getPath(R.drawable.cement_mixer));
        images.add(getPath(R.drawable.chair));
        images.add(getPath(R.drawable.chat));
        images.add(getPath(R.drawable.checklist));
        images.add(getPath(R.drawable.cheese));
        images.add(getPath(R.drawable.chessboard));
        images.add(getPath(R.drawable.clipboard_plan));
        images.add(getPath(R.drawable.cloud_music));
        images.add(getPath(R.drawable.cloudy));
        images.add(getPath(R.drawable.coding_html));
        images.add(getPath(R.drawable.coffin));
        images.add(getPath(R.drawable.conveyor_belt));
        images.add(getPath(R.drawable.database_cloud));
        images.add(getPath(R.drawable.desert));
        images.add(getPath(R.drawable.dna));
        images.add(getPath(R.drawable.download_computer));
        images.add(getPath(R.drawable.engagement_ring));
        images.add(getPath(R.drawable.euro_coin));
        images.add(getPath(R.drawable.eyeglass));
        images.add(getPath(R.drawable.food_dome));
        images.add(getPath(R.drawable.furby));
        images.add(getPath(R.drawable.gold_cart));
        images.add(getPath(R.drawable.graph_magnifier));
        images.add(getPath(R.drawable.hat));
        images.add(getPath(R.drawable.heart_watch));
        images.add(getPath(R.drawable.images));
        images.add(getPath(R.drawable.images_cloud));
        images.add(getPath(R.drawable.key));
        images.add(getPath(R.drawable.laptop_signal));
        images.add(getPath(R.drawable.locked_cloud));
        images.add(getPath(R.drawable.love_letter));
        images.add(getPath(R.drawable.makeup));
        images.add(getPath(R.drawable.medal));
        images.add(getPath(R.drawable.microchip));
        images.add(getPath(R.drawable.microscope));
        images.add(getPath(R.drawable.mind_map_paper));
        images.add(getPath(R.drawable.money_graph));
        images.add(getPath(R.drawable.money_increase));
        images.add(getPath(R.drawable.music_equalizer));
        images.add(getPath(R.drawable.nuclear_mushroom));
        images.add(getPath(R.drawable.old_car));
        images.add(getPath(R.drawable.online_shopping));
        images.add(getPath(R.drawable.open_sign));
        images.add(getPath(R.drawable.pantone));
        images.add(getPath(R.drawable.paper_plane));
        images.add(getPath(R.drawable.party_poppers));
        images.add(getPath(R.drawable.phone_booth));
        images.add(getPath(R.drawable.polaroid));
        images.add(getPath(R.drawable.programming));
        images.add(getPath(R.drawable.projector));
        images.add(getPath(R.drawable.radio));
        images.add(getPath(R.drawable.record_player));
        images.add(getPath(R.drawable.santa));
        images.add(getPath(R.drawable.santa_sled));
        images.add(getPath(R.drawable.settings));
        images.add(getPath(R.drawable.settings_2));
        images.add(getPath(R.drawable.shop));
        images.add(getPath(R.drawable.smartphone_message));
        images.add(getPath(R.drawable.sneakers));
        images.add(getPath(R.drawable.street_view));
        images.add(getPath(R.drawable.surgeon));
        images.add(getPath(R.drawable.t_shirt));
        images.add(getPath(R.drawable.tablet_chart));
        images.add(getPath(R.drawable.television_shelf));
        images.add(getPath(R.drawable.tower));
        images.add(getPath(R.drawable.video_camera));
        images.add(getPath(R.drawable.wind_wheel));
        images.add(getPath(R.drawable.wooden_horse));
        images.add(getPath(R.drawable.xylophone));
        images.add(getPath(R.drawable.yin_yang));

        return images;
    }

    private ArrayList<Currency> getCurrencies() {
        ArrayList <Currency> currencies = new ArrayList<>();

        currencies.add(new Currency(0, "USD", "$"));
        currencies.add(new Currency(0, "SEK", "kr"));
        currencies.add(new Currency(0, "HKD", "$"));
        currencies.add(new Currency(0, "AUD", "$"));
        currencies.add(new Currency(0, "CHF", "CHF"));
        currencies.add(new Currency(0, "NZD", "$"));
        currencies.add(new Currency(0, "GBP", "£"));
        currencies.add(new Currency(0, "MXN", "$"));
        currencies.add(new Currency(0, "NOK", "kr"));
        currencies.add(new Currency(0, "EUR", "€"));
        currencies.add(new Currency(0, "JPY", "¥"));

        return currencies;
    }

    private String getPath(int img) {
        Uri path = Uri.parse("android.resource://mx.shellcore.android.micontador/" + img);
        return path.toString();
    }
}
