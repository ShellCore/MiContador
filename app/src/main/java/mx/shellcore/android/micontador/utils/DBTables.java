package mx.shellcore.android.micontador.utils;

import android.provider.BaseColumns;

public class DBTables {

    public class CATEGORY {
        public static final String TABLE = "category";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_NAME = "cat_name";
        public static final String C_CATEGORY_IMAGE_ID = "cat_image_id";
        public static final String C_TYPE = "cat_type";
    }

    public class IMAGE {
        public static final String TABLE = "image";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_PATH = "img_path";
        public static final String C_TYPE = "img_type";
    }

    public class CURRENCY {
        public static final String TABLE = "currency";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_NAME = "cur_name";
        public static final String C_SYMBOL = "cur_symbol";
    }

    public class ACCOUNT {
        public static final String TABLE = "account";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_NAME = "acc_name";
        public static final String C_TYPE = "acc_type";
        public static final String C_BEGINNING_BALANCE = "acc_beginning_balance";
        public static final String C_ACCOUNT_CURRENCY_ID = "acc_currency_id";
        public static final String C_ACCOUNT_IMAGE_ID = "acc_image_id";
    }

    public class CREDIT_ACCOUNT {
        public static final String TABLE = "credit_account";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_COURT_DATE = "cac_court_date";
        public static final String C_LIMIT_PAY_DAYS = "cac_limit_pay_days";
        public static final String C_CREDIT_LIMIT = "cac_credit_limit";
    }

    public class MOVEMENT {
        public static final String TABLE = "movement";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_AMOUNT = "mov_amount";
        public static final String C_TYPE = "mov_type";
        public static final String C_DATE = "mov_date";
        public static final String C_DESCRIPTION = "mov_description";
        public static final String C_ACCOUNT_ID = "mov_account_id";
        public static final String C_CATEGORY_ID = "mov_category_id";
    }
}