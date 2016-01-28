package mx.shellcore.android.micontador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.utils.Constants;
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

        initializeImages(db);
        initializeCurrencies(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.CATEGORY.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.IMAGE.TABLE);
        onCreate(db);
    }

    private String createCategoryTable() {
        return "CREATE TABLE " + Constants.CATEGORY.TABLE
                + " ("
                + " " + Constants.CATEGORY.C_ID + " INTEGER PRIMARY KEY,"
                + " " + Constants.CATEGORY.C_NAME + " TEXT,"
                + " " + Constants.CATEGORY.C_TYPE + " INT,"
                + " " + Constants.CATEGORY.C_CATEGORY_IMAGE_ID + " INTEGER,"
                + " FOREIGN KEY ( " + Constants.CATEGORY.C_CATEGORY_IMAGE_ID + " ) REFERENCES " + Constants.IMAGE.TABLE + " ( " + Constants.IMAGE.C_ID + " ) "
                + " )";
    }

    private String createCategoryImageTable() {
        return "CREATE TABLE " + Constants.IMAGE.TABLE
                + " ("
                + " " + Constants.IMAGE.C_ID + " INTEGER PRIMARY KEY,"
                + " " + Constants.IMAGE.C_IMAGE + " STRING,"
                + " " + Constants.IMAGE.C_TYPE + " INT"
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
        ArrayList<Image> images = getImages();
        for (Image image : images) {

            String sql = "INSERT INTO " + Constants.IMAGE.TABLE
                    + " ("
                    + Constants.IMAGE.C_IMAGE + ", "
                    + Constants.IMAGE.C_TYPE
                    + " )"
                    + " VALUES (?, ?)";

            SQLiteStatement insertStatement = db.compileStatement(sql);
            insertStatement.clearBindings();
            insertStatement.bindString(Constants.IMAGE.C_IMAGE_INDEX, image.getImage());
            insertStatement.bindLong(Constants.IMAGE.C_TYPE_INDEX, image.getType());
            insertStatement.executeInsert();
        }
    }

    private void initializeCurrencies(SQLiteDatabase db) {
        ArrayList<Currency> currencies = getCurrencies();

        for (Currency currency : currencies) {
            String sql = "INSERT INTO " + Constants.CURRENCY.TABLE
                    + " ("
                    + Constants.CURRENCY.C_CURRENCY + ", "
                    + Constants.CURRENCY.C_SYMBOL
                    + " )"
                    + " VALUES (?, ?)";

            SQLiteStatement insertStatement = db.compileStatement(sql);
            insertStatement.clearBindings();
            insertStatement.bindString(1, currency.getCurrency());
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
}
