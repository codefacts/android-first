package com.imslbd.android_first.service;

//import android.app.ActionBar;
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CalendarView;
//import android.widget.CheckBox;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RatingBar;
//import android.widget.Switch;
//import android.widget.TimePicker;
//import android.widget.ToggleButton;
//
//import com.imslbd.android_first.model.ViewTypes;
//import com.imslbd.android_first.util.Util;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;

//import static com.imslbd.android_first.util.Util.or;

/**
 * Created by shahadat on 1/3/16.
 */
public class Service {
    public static final String FORM_JSON = "form.json";

//    public static LinearLayout createForm(LinearLayout form, Context context, JSONObject formModel) throws JSONException {
//        final Map<String, View> map = new HashMap<>();
//        JSONArray array = or(formModel.optJSONArray("form"), Util.EMPTY_JSON_ARRAY);
//        for (int i = 0; i < array.length(); i++) {
//            View view = createInputView(
//                    or(array.optJSONObject(i), Util.EMPTY_JSON_OBJECT), context, map, i);
//            view.setLayoutParams(
//                    new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            form.addView(view);
//        }
//        Button button = new Button(context);
//        button.setText("Submit");
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, Object> fMap = parseValue(map);
//            }
//        });
//        form.addView(button);
//        return form;
//    }
//
//    private static Map<String, Object> parseValue(Map<String, View> map) {
//        Map<String, Object> fMap = new HashMap<>();
//        Set<Map.Entry<String, View>> entries = map.entrySet();
//        for (Map.Entry<String, View> e : entries) {
////            if (e.getValue().getClass()) {
////
////            }
//        }
//        return fMap;
//    }
//
//    private static View createInputView(JSONObject model, Context context, Map<String, View> map, int i) throws JSONException {
//
//        int type = model.optInt("type", 0);
//        String name = model.optString("name", i + "");
//
//        if (type == ViewTypes.INPUT_TYPE_CALENDAR.code()) {
//            return add(createCalendar(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_CHECKBOX.code()) {
//            return add(createCheckbox(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_DATE_PICKER.code()) {
//            return add(createDatepicker(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_PASSWORD.code()) {
//            return add(createPasswordField(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_RADIO_BUTTON.code()) {
//            return add(createRadioButton(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_SWITCH.code()) {
//            return add(createSwitch(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_TEXT_FIELD.code()) {
//            return add(createText(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_TIME_PICKER.code()) {
//            return add(createTimePicker(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_RATING_BAR.code()) {
//            return add(createRatingBar(model, context), name, map);
//        } else if (type == ViewTypes.INPUT_TYPE_TOGGLE_BUTTON.code()) {
//            return add(createToggleButton(model, context), name, map);
//        }
//        return null;
//    }
//
//    public static View add(View view, String name, Map map) {
//        map.put(name, view);
//        return view;
//    }
//
//    private static View createToggleButton(JSONObject model, Context context) throws JSONException {
//        ToggleButton toggleButton = new ToggleButton(context);
//        toggleButton.setHint(model.optString("label", ""));
//        toggleButton.setChecked(model.optBoolean("value", false));
//        return toggleButton;
//    }
//
//    private static View createRatingBar(JSONObject model, Context context) throws JSONException {
//        RatingBar ratingBar = new RatingBar(context);
//        ratingBar.setRating((float) model.optDouble("value", 0));
//        ratingBar.setNumStars(model.optInt("numStars", 0));
//        ratingBar.setIsIndicator(model.optBoolean("isIndicator", false));
//        ratingBar.setMax(model.optInt("max", 0));
//        ratingBar.setStepSize(model.optInt("stepSize", 0));
//        return ratingBar;
//    }
//
//    private static View createTimePicker(JSONObject model, Context context) throws JSONException {
//        TimePicker timePicker = new TimePicker(context);
//        timePicker.setEnabled(model.optBoolean("enabled", false));
////        timePicker.setHour(model.optJSONObject("value").optInt("hour"));
////        timePicker.setMinute(model.optJSONObject("value").optInt("minite"));
//        timePicker.setIs24HourView(model.optBoolean("is2HourView", false));
//        return timePicker;
//    }
//
//    private static View createText(JSONObject model, Context context) throws JSONException {
//        EditText text = new EditText(context);
//        text.setHint(model.optString("label", ""));
//        text.setEnabled(model.optBoolean("enabled", true));
//        text.setText(model.optString("value"));
//        return text;
//    }
//
//    private static View createSwitch(JSONObject model, Context context) throws JSONException {
//        Switch aSwitch = new Switch(context);
//        aSwitch.setChecked(model.optBoolean("value"));
//        aSwitch.setHint(model.optString("label"));
//        aSwitch.setText(model.optString("text"));
//        aSwitch.setEnabled(model.optBoolean("enabled"));
//        return aSwitch;
//    }
//
//    private static View createRadioButton(JSONObject model, Context context) throws JSONException {
//        RadioButton radioButton = new RadioButton(context);
//        radioButton.setChecked(model.optBoolean("value"));
//        radioButton.setHint(model.optString("label"));
//        return radioButton;
//    }
//
//    private static View createPasswordField(JSONObject model, Context context) throws JSONException {
//        return createText(model, context);
//    }
//
//    private static View createDatepicker(JSONObject model, Context context) throws JSONException {
//        DatePicker datePicker = new DatePicker(context);
//        datePicker.setEnabled(model.optBoolean("enabled"));
//        datePicker.setMaxDate(Util.parseDate(model.optString("maxDate"), Util.MAX_DATE).getTime());
//        datePicker.setMinDate(Util.parseDate(model.optString("minDate"), Util.MIN_DATE).getTime());
//        if (!model.isNull("value")) {
//            Date dateValue = Util.parseDate(model.optString("value"), new Date());
//            Calendar instance = Calendar.getInstance();
//            instance.setTime(dateValue);
//            datePicker.setFirstDayOfWeek(instance.getFirstDayOfWeek());
//        }
//        return datePicker;
//    }
//
//    private static View createCheckbox(JSONObject model, Context context) throws JSONException {
//        CheckBox checkBox = new CheckBox(context);
//        checkBox.setChecked(model.optBoolean("value"));
//        checkBox.setHint(model.optString("label"));
//        checkBox.setEnabled(model.optBoolean("enabled"));
//        checkBox.setText(model.optString("text"));
//        return checkBox;
//    }
//
//    private static View createCalendar(JSONObject model, Context context) throws JSONException {
//        CalendarView calendarView = new CalendarView(context);
//        calendarView.setFirstDayOfWeek(model.optInt("firstDayOfWeek"));
//        calendarView.setEnabled(model.optBoolean("enabled"));
//        calendarView.setMaxDate(Util.parseDate(model.optString("maxDate"), Util.MAX_DATE).getTime());
//        calendarView.setMinDate(Util.parseDate(model.optString("minDate"), Util.MIN_DATE).getTime());
//        calendarView.setDate(Util.parseDate(model.optString("value"), new Date()).getTime());
//        return calendarView;
//    }
}
