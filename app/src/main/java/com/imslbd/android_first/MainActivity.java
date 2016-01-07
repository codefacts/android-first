package com.imslbd.android_first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.imslbd.android_first.service.DbServiceTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DbServiceTest.joinStrTestSingle();
        setContentView(R.layout.form_layout);
    }

//    private void createDummyFileIfNotExists(File filesDir) throws JSONException, IOException {
//        File file = new File(filesDir, Service.FORM_JSON);
//        Util.createFileIfNotExists(file);
//        RandomAccessFile raf = new RandomAccessFile(file, "rw");
//        raf.write(createDummyFormJson().toString(2).getBytes());
//    }
//
//    private static JSONObject createDummyFormJson() throws JSONException {
//        JSONObject jsonObject = new JSONObject().put("form", new JSONArray());
//        for (ViewTypes model : ViewTypes.values()) {
//            if (model == ViewTypes.INPUT_TYPE_CALENDAR) continue;
//            if (model == ViewTypes.INPUT_TYPE_DATE_PICKER) continue;
//            jsonObject.getJSONArray("form").put(new JSONObject().put("type", model.code()));
//        }
//        return jsonObject;
//    }
}
