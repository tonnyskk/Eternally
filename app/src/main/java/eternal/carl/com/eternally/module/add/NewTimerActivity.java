package eternal.carl.com.eternally.module.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import eternal.carl.com.eternally.BaseActivity;
import eternal.carl.com.eternally.BaseModel;
import eternal.carl.com.eternally.R;
import eternal.carl.com.eternally.item.TimerItem;

import static com.sleepbot.datetimepicker.time.TimePickerDialog.OnTimeSetListener;
import static com.sleepbot.datetimepicker.time.TimePickerDialog.newInstance;

public class NewTimerActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, OnTimeSetListener {
    public static final String DATE_PICKER_TAG = "date_picker";
    public static final String TIME_PICKER_TAG = "time_picker";

    private EditText addTimerTitle;
    private EditText addTimerDesc;
    private TextView addTimerDate;
    private TextView addTimerTime;
    private TextView addTimerButton;
    private TextView cancelTimerButton;

    enum Keys {
        TimerItem
    }

    enum Actions {
        SaveTimer
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timer);

        addTimerTitle = (EditText) findViewById(R.id.addTimerTitle);
        addTimerDesc = (EditText) findViewById(R.id.addTimerDesc);
        addTimerDate = (TextView) findViewById(R.id.addTimerDate);
        addTimerTime = (TextView) findViewById(R.id.addTimerTime);
        addTimerButton = (TextView) findViewById(R.id.addTimerButton);
        cancelTimerButton= (TextView) findViewById(R.id.cancelTimerButton);
        setupDateTimer(savedInstanceState);
        setupButton();

    }

    private void setupDateTimer(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
        final TimePickerDialog timePickerDialog = newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false, false);

        addTimerDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1950, 2036);
                datePickerDialog.setCloseOnSingleTapDay(true);
                datePickerDialog.show(getSupportFragmentManager(), DATE_PICKER_TAG);
            }
        });

        addTimerTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.setVibrate(true);
                timePickerDialog.setCloseOnSingleTapMinute(false);
                timePickerDialog.show(getSupportFragmentManager(), TIME_PICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATE_PICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }

            TimePickerDialog tpd = (TimePickerDialog) getSupportFragmentManager().findFragmentByTag(TIME_PICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeSetListener(this);
            }
        }
    }

    private void setupButton() {
        addTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  NewTimerActivity.this.getIntent();
                TimerItem item = new TimerItem();
                item.setTitle(addTimerTitle.getText().toString());
                item.setDesc(addTimerDesc.getText().toString());
                item.setTimestamp(getTimeStamp());
                intent.putExtra(Keys.TimerItem.name(), item);

                NewTimerActivity.this.postAsync(Actions.SaveTimer.name());
            }
        });

        cancelTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTimerActivity.this.finish();
            }
        });
    }

    @Override
    public BaseModel createModel() {
        return new NewTimerModel(this);
    }

    @Override
    public void onPostExecuteSuccessful(String action) {
        this.finish();
    }

    @Override
    public void onPostExecuteFailed(String action) {
        this.finish();
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        addTimerDate.setText(getFormatDate(year, month, day));
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        addTimerTime.setText(getFormatTime(hourOfDay, minute));
    }

    private String getFormatDate(int year, int month, int day) {
        return year + "-" + format(month) + "-" + format(day);
    }

    private String getFormatTime(int hourOfDay, int minute) {
        return format(hourOfDay) + ":" + format(minute);
    }

    private String format(Object mObject) {
        return String.format("%02d", mObject);
    }

    private long getTimeStamp() {
        String date = addTimerDate.getText().toString();
        String time = addTimerTime.getText().toString();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date finalDate = format.parse(date + " " + time);
            return finalDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().getTime();
    }
}
