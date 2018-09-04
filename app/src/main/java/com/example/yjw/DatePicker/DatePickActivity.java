package com.example.yjw.DatePicker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期时间选择
 */
public class DatePickActivity extends BaseActivity implements TextView.OnClickListener{
    private TextView starttime,endtime,textView2,textView4;
    private int mYear,mMonth,mDay,mhourOfDay,mminute;
    private Dialog dataPickerDialog = null,timePickerDialog =null;
    private int mStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_pick);
        starttime =findViewById(R.id.starttime);
        endtime =findViewById(R.id.endtime);
        textView2 =findViewById(R.id.textView2);
        textView4 =findViewById(R.id.textView4);
        starttime.setOnClickListener(this);
        endtime.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView4.setOnClickListener(this);

        //初始化日期
        final Calendar calendar=Calendar.getInstance();
        mYear =calendar.get(Calendar.YEAR);
        mMonth =calendar.get(Calendar.MONTH);
        mDay =calendar.get(Calendar.DAY_OF_MONTH);
        SetDateTimeToTextView(starttime,"00:00:00");
        SetDateTimeToTextView(endtime,"23:59:59");

        mhourOfDay=calendar.get(Calendar.HOUR_OF_DAY);
        mminute =calendar.get(Calendar.MINUTE);
        SetTimeToTextView(textView2,"");
        SetTimeToTextView(textView4,"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.starttime:
                mStatus = 0;
                dataPickerDialog = new DatePickerDialog(DatePickActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, mdateListener, mYear, mMonth, mDay);
                dataPickerDialog.show();
                break;
            case R.id.endtime:
                mStatus = 1;
                dataPickerDialog = new DatePickerDialog(DatePickActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,mdateListener, mYear, mMonth, mDay);
                dataPickerDialog.show();
                break;
            case R.id.textView2:
                mStatus = 3;
                timePickerDialog=new TimePickerDialog(this,mtimeListener,mhourOfDay,mminute,true);
                timePickerDialog.show();
                break;
            case R.id.textView4:
                mStatus = 4;
                timePickerDialog=new TimePickerDialog(this,mtimeListener,mhourOfDay,mminute,true);
                timePickerDialog.show();
                break;
                default:
        }
    }

    private TimePickerDialog.OnTimeSetListener mtimeListener =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mhourOfDay =hourOfDay;
            mminute = minute;
            if (mStatus == 3){
                SetTimeToTextView(textView2,"");
            }
            if (mStatus == 4){
                SetTimeToTextView(textView4,"");
            }
        }
    };

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            if(mStatus == 0)
                SetDateTimeToTextView(starttime,"00:00:00");
            if(mStatus == 1)
                SetDateTimeToTextView(endtime,"23:59:59");
        }
    };

    void SetDateTimeToTextView(TextView txtView, String EndTimeStr)
    {
        String newDateStr = mYear+"-" + (mMonth + 1)+"-"+mDay;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            java.util.Date date = sdf.parse(newDateStr);
            txtView.setText(new SimpleDateFormat("yyyy-MM-dd").format(date)+" " + EndTimeStr);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void SetTimeToTextView(TextView txtView, String EndTimeStr)
    {
        String newDateStr = mhourOfDay+":" + mminute;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");//小写的mm表示的是分钟
            java.util.Date date = sdf.parse(newDateStr);
            txtView.setText(new SimpleDateFormat("hh:mm").format(date)+" " + EndTimeStr);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
