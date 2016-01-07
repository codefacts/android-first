package com.imslbd.android_first.model;

import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/**
 * Created by shahadat on 1/3/16.
 */
public enum ViewTypes {
    //Text Widgets
    TEXT_VIEW(1001, CalendarView.class),
    LARGE_TEXT_VIEW(1002, RatingBar.class),
    MEDIUM_TEXT_VIEW(1003, TimePicker.class),
    SMALL_TEXT_VIEW(1004, TimePicker.class),
    //BUtton Widgets
    SMALL_BUTTON(1005, RadioButton.class),
    BUTTON(1006, RadioGroup.class),
    //
    RADIO_BUTTON(1007, Switch.class),
    TOGGLE_BUTTON(1008, ToggleButton.class),
    CHECKBOX(1009, CheckBox.class),
    SWITCH(10010, DatePicker.class),
    //
    ImageButton(10011, Switch.class),
    ImageView(10012, ToggleButton.class),
    //
    ProgressBarLarge(10013, CheckBox.class),
    ProgressBarNormal(10014, DatePicker.class),
    ProgressBarSmall(10015, DatePicker.class),
    ProgressBarHorizontal(10016, DatePicker.class),
    //
    SeekBar(10017, DatePicker.class),
    RatingBar(10018, DatePicker.class),
    Spinner(10019, DatePicker.class),
    WebView(10020, DatePicker.class),
    //INPUT_TYPES
    TEXT_INPUT(2001, EditText.class),
    TEXT_PERSON_NAME(2002, EditText.class),
    TEXT_PASSWORD(2003, EditText.class),
    TEXT_EMAIL_ADDRESS(2004, EditText.class),
    PHONE(2005, EditText.class),
    TEXT_POSTAL_ADDRESS(2006, EditText.class),
    TEXT_MULTI_LINE(2007, EditText.class),
    TIME(2008, EditText.class),
    DATE(2009, EditText.class),
    NUMBER(20010, EditText.class),
    NUMBER_SIGNED(2009, EditText.class),
    NUMBER_DECIMAL(20010, EditText.class),
    //Layouts
    FRAME_LAYOUT(3001, EditText.class),
    LINEAR_LAYOUT_HORIZONTAL(3002, EditText.class),
    LINEAR_LAYOUT_VERTICAL(3003, EditText.class),
    TABLE_LAYOUT(3004, EditText.class),
    TABLE_ROW(3005, EditText.class),
    GRID_LAYOUT(3006, EditText.class),
    RELATIVE_LAYOUT(3007, EditText.class),
    //Containers
    RadioGroup(4001, EditText.class),
    ListView(4002, EditText.class),
    GridView(4003, EditText.class),
    ExpandableListView(4004, EditText.class),
    ScrollView(4005, EditText.class),
    HorizontalScrollView(4006, EditText.class),
    TabHost(4007, EditText.class),
    VideoView(4008, EditText.class),
    DialerFilter(4009, EditText.class),
    //DateTime
    TextClock(5001, EditText.class),
    AnalogClock(5002, EditText.class),
    DatePicker(5003, EditText.class),
    TimePicker(5004, EditText.class),
    CalendarView(5005, EditText.class);

    private final int code;
    private final Class<? extends View> aClass;

    ViewTypes(int code, Class<? extends View> aClass) {
        this.code = code;
        this.aClass = aClass;
    }

    public int code() {
        return code;
    }
}
