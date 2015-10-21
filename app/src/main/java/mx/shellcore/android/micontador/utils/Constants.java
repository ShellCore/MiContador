package mx.shellcore.android.micontador.utils;

import android.provider.BaseColumns;

public class Constants {

    public class CATEGORY {
        public static final String TABLE = "category";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_NAME = "name";
        public static final String C_IMAGE = "image";
        public static final String C_TYPE = "type";

        public static final int C_ID_INDEX = 0;
        public static final int C_NAME_INDEX = 1;
        public static final int C_IMAGE_INDEX = 2;
        public static final int C_TYPE_INDEX = 3;
    }

    public class CATEGORY_IMAGE {
        public static final String TABLE = "category_image";

        public static final String C_ID = BaseColumns._ID;
        public static final String C_IMAGE = "image";

        public static final int C_ID_INDEX = 0;
        public static final int C_IMAGE_INDEX = 1;
    }
}
