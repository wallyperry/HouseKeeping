package com.cjcm.housekeeping.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import com.cjcm.housekeeping.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("unused")
public class DateTimePickUtils implements NumberPicker.OnValueChangeListener, View.OnClickListener {
    private String initDateTime;
    private Context activity;
    private Calendar calendar;
    private NumberPicker hourPicker;
    private NumberPicker minutePicker;
    private NumberPicker datePicker;
    private String[] minuteArr;
    private String hourStr;
    private String minuteStr;
    private String dateStr;
    private Dialog dialog;
    private TextView startDateTime;
    private TextView tv_time;
    private String[] dayArrays;
    private long currentTimeMillis;
    private String hintTip = "请选择正确的有效时间";
    private long oneDay = 24 * 60 * 60 * 1000;  //1天

    public DateTimePickUtils(Context activity, String initDateTime, TextView startDateTime) {
        this.startDateTime = startDateTime;
        this.activity = activity;
        this.initDateTime = initDateTime;
    }

    /**
     * 弹出日期时间选择框方法
     */
    @SuppressWarnings("deprecation")
    public void dateTimePicKDialog() {
        View dateTimeLayout = View.inflate(activity, R.layout.common_datetime2, null);
        dateTimeLayout.findViewById(R.id.tv_cancel).setOnClickListener(this);
        dateTimeLayout.findViewById(R.id.tv_confirm).setOnClickListener(this);
        tv_time = dateTimeLayout.findViewById(R.id.tv_time);
        datePicker = dateTimeLayout.findViewById(R.id.datePicker);
        hourPicker = dateTimeLayout.findViewById(R.id.hourPicker);
        minutePicker = dateTimeLayout.findViewById(R.id.minutePicker);
        hourPicker.setWrapSelectorWheel(false);
        minutePicker.setWrapSelectorWheel(false);
        datePicker.setWrapSelectorWheel(false);
        datePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        hourPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        minutePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        initPicker();
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dateTimeLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        assert window != null;
        window.setWindowAnimations(R.style.time_picker_style);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = ((Activity) activity).getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);// 设置点击外围隐藏
        dialog.show();
        onDateChanged();
    }

    private void initPicker() {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        if (50 <= minutes) minutes = 1;
        else if (40 <= minutes) minutes = 0;
        else if (30 <= minutes) minutes = 5;
        else if (20 <= minutes) minutes = 4;
        else if (10 <= minutes) minutes = 3;
        else minutes = 2;
        // 设置日期 7天以内
        dayArrays = new String[7];
        for (int i = 0; i < 7; i++) {
            currentTimeMillis = System.currentTimeMillis() + i * oneDay;
            calendar.setTime(new Date(currentTimeMillis));
            dayArrays[i] = calendar.get(Calendar.MONTH) + 1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
        }
        currentTimeMillis = System.currentTimeMillis();// 设置当前时间的毫秒值
        datePicker.setOnValueChangedListener(this);
        datePicker.setDisplayedValues(dayArrays);
        datePicker.setMinValue(0);
        datePicker.setMaxValue(dayArrays.length - 1);
        datePicker.setValue(0);
        dateStr = dayArrays[0];// 初始值

        hourPicker.setOnValueChangedListener(this);
        hourPicker.setMaxValue(20);
        hourPicker.setMinValue(9);
        // 设置小时 预约两个小时以后
//        hourPicker.setValue(minutes == 0 ? hours + 3 : hours + 2);
//        hourStr = minutes == 0 ? hours + 3 + "" : hours + 2 + "";
        // 设置分钟
        minuteArr = new String[]{"00", "10", "20", "30", "40", "50"};
        minutePicker.setOnValueChangedListener(this);
        minutePicker.setDisplayedValues(minuteArr);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(minuteArr.length - 1);
        minutePicker.setValue(minutes);
        minuteStr = minuteArr[minutes];// 初始值
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("SimpleDateFormat")
    private void onDateChanged() {
        // 获得日历实例
        calendar = Calendar.getInstance();
        calendar.setTime(new Date(currentTimeMillis));
        Date date = calendar.getTime();
        date.setHours(Integer.parseInt(hourStr));
        date.setMinutes(Integer.parseInt(minuteStr));
        calendar.setTime(date);
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()
                || calendar.getTimeInMillis() > System.currentTimeMillis() + 7 * oneDay) {
            tv_time.setText(hintTip);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            initDateTime = sdf.format(calendar.getTime()) + " " + hourStr + ":" + minuteStr + ":" + "00";
            tv_time.setText(initDateTime);
        }
    }

    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast indexOrLast
     * @param frontOrBack frontOrBack
     * @return String
     */
    public static String spliteString(String srcStr, String pattern, String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = indexOrLast.equalsIgnoreCase("index") ? srcStr.indexOf(pattern) : srcStr.lastIndexOf(pattern);
        if (loc != -1)
            result = frontOrBack.equalsIgnoreCase("front") ? srcStr.substring(0, loc) : srcStr.substring(loc + 1, srcStr.length());
        return result;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()) {
            case R.id.datePicker:
                currentTimeMillis = System.currentTimeMillis() + newVal * oneDay;
                dateStr = dayArrays[newVal];
                onDateChanged();
                break;
            case R.id.hourPicker:
                hourStr = newVal + "";
                onDateChanged();
                break;
            case R.id.minutePicker:
                minuteStr = minuteArr[newVal];
                onDateChanged();
                break;
        }
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("SimpleDateFormat")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
            case R.id.tv_confirm:
                if (calendar.getTimeInMillis() <= System.currentTimeMillis()
                        || calendar.getTimeInMillis() > System.currentTimeMillis() + 7 * oneDay) {
                    Toast.makeText(activity, hintTip, Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = null;
                    try {
                        date = sdf.parse(initDateTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (date != null) {
                        if (date.getHours() >= 9 && date.getHours() <= 20) {
                            startDateTime.setText(initDateTime);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(activity, hintTip, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }
}