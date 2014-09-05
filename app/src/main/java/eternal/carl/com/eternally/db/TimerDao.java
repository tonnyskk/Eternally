package eternal.carl.com.eternally.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eternal.carl.com.eternally.item.TimerItem;

/**
 * Created by Administrator on 2014/9/5.
 */
public class TimerDao {
    public TimerDao() {
    }

    public ArrayList<TimerItem> getItemList() throws Exception {
        ArrayList<TimerItem> timerItemList = new ArrayList<TimerItem>();
        Cursor cursor = null;
        try {

            SQLiteDatabase db = TimerDbHelper.getInstance().getReadableDatabase();

            String orderBy = TimerDbHelper.COL_PRIORITY + " DESC, " + TimerDbHelper.COL_ID;

            cursor = db.query(TimerDbHelper.DB_NAME,
                    new String[]{TimerDbHelper.COL_ID,
                            TimerDbHelper.COL_TITLE,
                            TimerDbHelper.COL_DESC,
                            TimerDbHelper.COL_TIME,
                            TimerDbHelper.COL_IS_LUNA,
                            TimerDbHelper.COL_IS_SERIAL
                    }, null, null, null, null, orderBy, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    TimerItem timerItem = new TimerItem();
                    timerItem.setId(cursor.getInt(0));
                    timerItem.setTitle(cursor.getString(1));
                    timerItem.setDesc(cursor.getString(2));
                    timerItem.setTimestamp(cursor.getLong(3));
                    timerItem.setLuna(cursor.getInt(4));
                    timerItem.setSerial(cursor.getInt(5));
                    timerItemList.add(timerItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return timerItemList;
    }

    public void saveItem(TimerItem timerItem)  throws Exception{
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimerDbHelper.COL_ID, timerItem.getId() <= 0 ? null : timerItem.getId());
        contentValues.put(TimerDbHelper.COL_TITLE, timerItem.getTitle());
        contentValues.put(TimerDbHelper.COL_DESC, timerItem.getDesc());
        contentValues.put(TimerDbHelper.COL_TIME, timerItem.getTimestamp());
        contentValues.put(TimerDbHelper.COL_IS_LUNA, timerItem.isLuna() ? 1 : 0);
        contentValues.put(TimerDbHelper.COL_IS_SERIAL, timerItem.isSerial() ? 1 : 0);
        contentValues.put(TimerDbHelper.COL_PRIORITY, timerItem.getPriority());

        try {
            SQLiteDatabase db = TimerDbHelper.getInstance().getWritableDatabase();
            db.insert(TimerDbHelper.DB_NAME, "", contentValues);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            contentValues.clear();
        }
    }
}
