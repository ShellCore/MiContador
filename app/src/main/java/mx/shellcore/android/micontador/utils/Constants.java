package mx.shellcore.android.micontador.utils;

import android.provider.BaseColumns;

public class Constants {

    public class CATEGORY {
        public static final String TABLE = "category";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_NAME = "name";
        public static final String C_CATEGORY_IMAGE_ID = "category_image_id";
        public static final String C_TYPE = "type";

        public static final int C_ID_INDEX = 0;
        public static final int C_NAME_INDEX = 1;
        public static final int C_TYPE_INDEX = 2;
        public static final int C_CATEGORY_IMAGE_ID_INDEX = 3;
    }

    public class IMAGE {
        public static final String TABLE = "image";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_IMAGE = "image";
        public static final String C_TYPE = "type";

        public static final int C_ID_INDEX = 0;
        public static final int C_IMAGE_INDEX = 1;
        public static final int C_TYPE_INDEX = 2;
    }

    public class CURRENCY {
        public static final String TABLE = "currency";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_CURRENCY = "currency";
        public static final String C_SYMBOL = "symbol";

        public static final int C_ID_INDEX = 0;
        public static final int C_CURRENCY_INDEX = 1;
        public static final int C_SYMBOL_INDEX = 2;
    }

    public class ACCOUNT {
        public static final String TABLE = "account";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_NAME = "name";
        public static final String C_TYPE = "type";
        public static final String C_CURRENCY_ID = "currency";
        public static final String C_BEGINNING_BALANCE = "beginning_balance";

        public static final int C_ID_INDEX = 0;
        public static final int C_NAME_INDEX = 1;
        public static final int C_TYPE_INDEX = 2;
        public static final int C_CURRENCY_ID_INDEX = 3;
        public static final int C_BEGINNING_BALANCE_INDEX = 4;

    }

    public class CREDIT_ACCOUNT {
        public static final String TABLE = "credit_account";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_ACCOUNT_ID = "account_id";
        public static final String C_COURT_DATE = "court_date";
        public static final String C_LIMIT_PAY_DAYS = "limit_pay_days";
        public static final String C_CREDIT_LIMIT = "credit_limit";

        public static final int C_ID_INDEX = 0;
        public static final int C_ACCOUNT_ID_INDEX = 1;
        public static final int C_COURT_DATE_INDEX = 2;
        public static final int C_LIMIT_PAY_DAYS_INDEX = 3;
        public static final int C_CREDIT_LIMIT_INDEX = 4;
    }
}