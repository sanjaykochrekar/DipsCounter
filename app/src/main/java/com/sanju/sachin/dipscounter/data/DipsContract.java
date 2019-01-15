package com.sanju.sachin.dipscounter.data;

import android.provider.BaseColumns;

public final class DipsContract {
    private DipsContract() {
    }

    public static class DipsEntry implements BaseColumns {
        public static String TABLE_NAME = "entry";
        public static String _ID = "id";
        public static String COLUMN_DIPS_COUNT = "dips";
        public static String COLUMN_DATE = "date";
        public static String COLUMN_TIME = "time";
    }
}
