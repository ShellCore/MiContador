package mx.shellcore.android.micontador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.DBTables;
import mx.shellcore.android.micontador.utils.PathUtils;

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
        db.execSQL(createCreditTable());
        db.execSQL(createMovementTable());

        initializeImages(db);
        initializeCurrencies(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBTables.CATEGORY.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBTables.IMAGE.TABLE);
        onCreate(db);
    }

    private String createCategoryTable() {
        return "CREATE TABLE " + DBTables.CATEGORY.TABLE
                + " ("
                + " " + DBTables.CATEGORY.C_ID + " INTEGER PRIMARY KEY,"
                + " " + DBTables.CATEGORY.C_NAME + " TEXT,"
                + " " + DBTables.CATEGORY.C_TYPE + " INT,"
                + " " + DBTables.CATEGORY.C_CATEGORY_IMAGE_ID + " INTEGER,"
                + " FOREIGN KEY ( " + DBTables.CATEGORY.C_CATEGORY_IMAGE_ID + " ) REFERENCES " + DBTables.IMAGE.TABLE + " ( " + DBTables.IMAGE.C_ID + " ) "
                + " )";
    }

    private String createCategoryImageTable() {
        return "CREATE TABLE " + DBTables.IMAGE.TABLE
                + " ("
                + " " + DBTables.IMAGE.C_ID + " INTEGER PRIMARY KEY,"
                + " " + DBTables.IMAGE.C_PATH + " STRING,"
                + " " + DBTables.IMAGE.C_TYPE + " INT"
                + " )";
    }

    private String createCurrencyTable() {
        return "CREATE TABLE " + DBTables.CURRENCY.TABLE
                + " ("
                + " " + DBTables.CURRENCY.C_ID + " INTEGER PRIMARY KEY,"
                + " " + DBTables.CURRENCY.C_NAME + " TEXT,"
                + " " + DBTables.CURRENCY.C_SYMBOL + " TEXT"
                +" )";
    }

    private String createAccountTable() {
        return "CREATE TABLE " + DBTables.ACCOUNT.TABLE
                + " ("
                + " " + DBTables.ACCOUNT.C_ID + " INTEGER PRIMARY KEY,"
                + " " + DBTables.ACCOUNT.C_NAME + " TEXT,"
                + " " + DBTables.ACCOUNT.C_TYPE + " INT,"
                + " " + DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID + " INTEGER,"
                + " " + DBTables.ACCOUNT.C_BEGINNING_BALANCE + " REAL,"
                + " " + DBTables.ACCOUNT.C_ACCOUNT_IMAGE_ID + " INTEGER,"
                + " FOREIGN KEY ( " + DBTables.ACCOUNT.C_ACCOUNT_CURRENCY_ID + " ) REFERENCES " + DBTables.CURRENCY.TABLE + " ( " + DBTables.CURRENCY.C_ID + " ) "
                + " FOREIGN KEY ( " + DBTables.ACCOUNT.C_ACCOUNT_IMAGE_ID + " ) REFERENCES " + DBTables.IMAGE.TABLE + " ( " + DBTables.IMAGE.C_ID + " ) "
                + " )";
    }

    private String createCreditTable() {
        return "CREATE TABLE " + DBTables.CREDIT_ACCOUNT.TABLE
                + " ("
                + " " + DBTables.CREDIT_ACCOUNT.C_ID + " INTEGER PRIMARY KEY,"
                + " " + DBTables.CREDIT_ACCOUNT.C_CREDIT_LIMIT + " REAL,"
                + " " + DBTables.CREDIT_ACCOUNT.C_COURT_DATE + " TEXT,"
                + " " + DBTables.CREDIT_ACCOUNT.C_LIMIT_PAY_DAYS + " INT,"
                + " FOREIGN KEY ( " + DBTables.CREDIT_ACCOUNT.C_ID + " ) REFERENCES " + DBTables.ACCOUNT.TABLE + " ( " + DBTables.ACCOUNT.C_ID + " ) "
                + " )";

    }

    private String createMovementTable() {
        return "CREATE TABLE " + DBTables.MOVEMENT.TABLE
                + " ("
                + " " + DBTables.MOVEMENT.C_ID + " INTEGER PRIMARY KEY,"
                + " " + DBTables.MOVEMENT.C_AMOUNT + " REAL,"
                + " " + DBTables.MOVEMENT.C_DATE + " STRING,"
                + " " + DBTables.MOVEMENT.C_DESCRIPTION + " STRING,"
                + " " + DBTables.MOVEMENT.C_TYPE + " INT,"
                + " " + DBTables.MOVEMENT.C_ACCOUNT_ID + " INTEGER,"
                + " " + DBTables.MOVEMENT.C_CATEGORY_ID + " INTEGER,"
                + " FOREIGN KEY ( " + DBTables.MOVEMENT.C_ACCOUNT_ID + " ) REFERENCES " + DBTables.ACCOUNT.TABLE + " ( " + DBTables.ACCOUNT.C_ID + " ) "
                + " FOREIGN KEY ( " + DBTables.MOVEMENT.C_CATEGORY_ID + " ) REFERENCES " + DBTables.CATEGORY.TABLE + " ( " + DBTables.CATEGORY.C_ID + " ) "
                + " ) ";
    }

    private void initializeImages(SQLiteDatabase db) {
        ArrayList<Image> images = getImages();
        for (Image image : images) {

            String sql = "INSERT INTO " + DBTables.IMAGE.TABLE
                    + " ("
                    + DBTables.IMAGE.C_PATH + ", "
                    + DBTables.IMAGE.C_TYPE
                    + " )"
                    + " VALUES (?, ?)";

            SQLiteStatement insertStatement = db.compileStatement(sql);
            insertStatement.clearBindings();
            insertStatement.bindString(1, image.getPath());
            insertStatement.bindLong(2, image.getType());
            insertStatement.executeInsert();
        }
    }

    private void initializeCurrencies(SQLiteDatabase db) {
        ArrayList<Currency> currencies = getCurrencies();

        for (Currency currency : currencies) {
            String sql = "INSERT INTO " + DBTables.CURRENCY.TABLE
                    + " ("
                    + DBTables.CURRENCY.C_NAME + ", "
                    + DBTables.CURRENCY.C_SYMBOL
                    + " )"
                    + " VALUES (?, ?)";

            SQLiteStatement insertStatement = db.compileStatement(sql);
            insertStatement.clearBindings();
            insertStatement.bindString(1, currency.getName());
            insertStatement.bindString(2, currency.getSymbol());
            insertStatement.executeInsert();
        }
    }

    private ArrayList<Image> getImages() {
        ArrayList<Image> images = new ArrayList<>();

        images.add(new Image(PathUtils.getImagePath(R.drawable.alien), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.analytics), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.apartment), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.application_map), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.baby_mobile), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.bag_present), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.batman), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.battery_charging), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.beach), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.bell), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.bonsai), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.bus), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.camera_front), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.candles), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.candy), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.canoe), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.captain_shield), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.car_jumper), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.cashier), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.cement_mixer), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.chair), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.chat), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.checklist), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.cheese), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.chessboard), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.clipboard_plan), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.cloud_music), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.cloudy), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.coding_html), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.coffin), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.conveyor_belt), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.database_cloud), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.desert), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.dna), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.download_computer), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.engagement_ring), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.euro_coin), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.eyeglass), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.food_dome), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.furby), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.gold_cart), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.graph_magnifier), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.hat), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.heart_watch), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.images), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.images_cloud), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.key), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.laptop_signal), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.locked_cloud), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.love_letter), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.makeup), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.medal), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.microchip), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.microscope), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.mind_map_paper), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.money_graph), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.money_increase), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.music_equalizer), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.nuclear_mushroom), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.old_car), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.online_shopping), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.open_sign), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.pantone), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.paper_plane), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.party_poppers), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.phone_booth), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.polaroid), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.programming), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.projector), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.radio), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.record_player), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.santa), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.santa_sled), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.settings), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.settings_2), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.shop), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.smartphone_message), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.sneakers), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.street_view), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.surgeon), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.t_shirt), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.tablet_chart), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.television_shelf), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.tower), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.video_camera), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.wind_wheel), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.wooden_horse), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.xylophone), Image.IMG_CATEGORY));
        images.add(new Image(PathUtils.getImagePath(R.drawable.yin_yang), Image.IMG_CATEGORY));

        images.add(new Image(PathUtils.getImagePath(R.drawable.card_01), Image.IMG_ACCOUNT));
        images.add(new Image(PathUtils.getImagePath(R.drawable.card_02), Image.IMG_ACCOUNT));
        images.add(new Image(PathUtils.getImagePath(R.drawable.cash), Image.IMG_ACCOUNT));
        images.add(new Image(PathUtils.getImagePath(R.drawable.check), Image.IMG_ACCOUNT));
        images.add(new Image(PathUtils.getImagePath(R.drawable.coins), Image.IMG_ACCOUNT));
        images.add(new Image(PathUtils.getImagePath(R.drawable.moneybox), Image.IMG_ACCOUNT));
        images.add(new Image(PathUtils.getImagePath(R.drawable.safe), Image.IMG_ACCOUNT));

        return images;
    }

    private ArrayList<Currency> getCurrencies() {
        ArrayList <Currency> currencies = new ArrayList<>();

        currencies.add(new Currency("USD", "$"));
        currencies.add(new Currency("SEK", "kr"));
        currencies.add(new Currency("HKD", "$"));
        currencies.add(new Currency("AUD", "$"));
        currencies.add(new Currency("CHF", "CHF"));
        currencies.add(new Currency("NZD", "$"));
        currencies.add(new Currency("GBP", "£"));
        currencies.add(new Currency("MXN", "$"));
        currencies.add(new Currency("NOK", "kr"));
        currencies.add(new Currency("EUR", "€"));
        currencies.add(new Currency("JPY", "¥"));

        return currencies;
    }
}
