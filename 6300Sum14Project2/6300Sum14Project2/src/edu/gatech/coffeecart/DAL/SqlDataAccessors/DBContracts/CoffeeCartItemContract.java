package edu.gatech.coffeecart.DAL.SqlDataAccessors.DBContracts;

import android.provider.BaseColumns;

final public class CoffeeCartItemContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public CoffeeCartItemContract() {}

    /* Inner class that defines the table contents */
    public static abstract class CoffeeCartItem implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}

