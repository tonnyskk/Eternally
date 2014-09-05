package eternal.carl.com.eternally.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import eternal.carl.com.eternally.app.EternalContextHelper;

/**
 * Created by Administrator on 2014/9/5.
 */
public class TimerDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "eternally";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_DESC = "desc";
    public static final String COL_TIME = "time";
    public static final String COL_IS_LUNA = "isLuna";
    public static final String COL_IS_SERIAL = "isSerial";
    public static final String COL_PRIORITY = "priority";
    private static final int DB_VERSION = 1;

    private static class InnerHelper {
        private static  TimerDbHelper instance = new TimerDbHelper(EternalContextHelper.getInstance().getAppInfo().getAppContext());
    }
    public static TimerDbHelper getInstance() {
        return InnerHelper.instance;
    }

    private TimerDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + DB_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " VARCHAR, " +
                COL_DESC + " VARCHAR, " +
                COL_TIME + " INTEGER, " +
                COL_IS_LUNA + " SMALLINT DEFAULT 0," +
                COL_IS_SERIAL + " SMALLINT DEFAULT 0," +
                COL_PRIORITY + " SMALLINT DEFAULT 1)");
        sqLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS idx_eternal_priority ON " + DB_NAME + "(" + COL_PRIORITY + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }
}
