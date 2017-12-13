package in.digitechlab.travelmatepro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 11/4/2017.
 */

public class TravelDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "travel_database";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "tbl_user";
    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";

    public static final String TABLE_EVENT = "tbl_event";
    public static final String EVENT_ID = "event_id";
    public static final String EVENT_NAME = "event_name";
    public static final String STARTING_LOCATION = "starting_location";
    public static final String DESTINATION = "destination";
    public static final String DEPARTURE_DATE = "departure_date";
    public static final String ESTIMATED_BUDGET = "estimated_budget";
    public static final String EVENT_STATUS = "event_status";
    public static final String EVENT_CREATED_TIME = "event_created_time";

    public static final String TABLE_EXPENSE = "tbl_expense";
    public static final String EXPENSE_ID = "expense_id";
    public static final String EXPENSE_AMOUNT = "expense_amount";
    public static final String EXPENSE_TITLE = "expense_title";
    public static final String EXPENSE_ENTRY_TIME = "expense_entry_time";

    public static final String TABLE_MOMENT = "tbl_moment";
    public static final String MOMENT_ID = "moment_id";
    public static final String MOMENT_IMAGE = "moment_image";
    public static final String MOMENT_TIME = "moment_time";
    public static final String MOMENT_NAME = "moment_name";

    public static String CREATE_TABLE_USER = "create table "+TABLE_USER+"("
            +USER_ID+" integer primary key, "
            +NAME+" text, "
            +EMAIL+" text, "
            +PASSWORD+" text, "
            +PHONE+" text);";

    public static String CREATE_TABLE_EVENT = "create table "+TABLE_EVENT+"("
            +EVENT_ID+" integer primary key, "
            +EVENT_NAME+" text, "
            +STARTING_LOCATION+" text, "
            +DESTINATION+" text, "
            +DEPARTURE_DATE+" text, "
            +ESTIMATED_BUDGET+" real, "
            +EVENT_STATUS+" text, "
            +EVENT_CREATED_TIME+" text, "
            +USER_ID+" integer);";

    public static String CREATE_TABLE_EXPENSE = "create table "+TABLE_EXPENSE+"("
            +EXPENSE_ID+" integer primary key, "
            +EXPENSE_AMOUNT+" real, "
            +EXPENSE_TITLE+" text, "
            +EXPENSE_ENTRY_TIME+" text, "
            +USER_ID+" integer, "
            +EVENT_ID+" integer);";

    public static String CREATE_TABLE_MOMENTS = "create table "+TABLE_MOMENT+"("
            +MOMENT_ID+" integer primary key, "
            +MOMENT_IMAGE+" text, "
            +MOMENT_TIME+" text, "
            +MOMENT_NAME+" text, "
            +EVENT_ID+" integer);";


    public TravelDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_EVENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_MOMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOMENT);

        // create new tables
        onCreate(sqLiteDatabase);
    }
}
