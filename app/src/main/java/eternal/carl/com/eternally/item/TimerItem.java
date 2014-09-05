package eternal.carl.com.eternally.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2014/9/5.
 */
public class TimerItem implements Parcelable {
    private int id;
    private String title;
    private String desc;
    private long timestamp;
    private boolean isLuna;
    private boolean isSerial;
    private int priority;
    private int icon;

    public TimerItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isLuna() {
        return isLuna;
    }

    public void setLuna(int isLuna) {
        this.isLuna = isLuna == 0 ? false : true;
    }

    public boolean isSerial() {
        return isSerial;
    }

    public void setSerial(int isSerial) {
        this.isSerial = isSerial == 0 ? false : true;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.desc);
        parcel.writeLong(this.timestamp);
        parcel.writeInt(this.isLuna ? 1 : 0);
        parcel.writeInt(this.isSerial ? 1 : 0);
        parcel.writeInt(this.priority);
        parcel.writeInt(this.icon);
    }


    public static final Parcelable.Creator<TimerItem> CREATOR = new Parcelable.Creator<TimerItem>() {
        public TimerItem createFromParcel(Parcel in) {
            return new TimerItem(in);
        }

        public TimerItem[] newArray(int size) {
            return new TimerItem[size];
        }
    };

    private TimerItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.desc = in.readString();
        this.isLuna = in.readInt() == 1 ? true : false;
        this.isSerial = in.readInt() == 1 ? true : false;
        this.priority = in.readInt();
        this.icon = in.readInt();
    }
}
