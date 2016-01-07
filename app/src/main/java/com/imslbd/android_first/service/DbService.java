package com.imslbd.android_first.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imslbd.android_first.intfs.AsyncHandler;
import com.imslbd.android_first.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by shahadat on 1/7/16.
 */
public class DbService {
    private static MyDbHelper dbHelper;
    private static final ValueObtainer[] OBTAINERS;

    public static void initDb(Context context) {
        if (dbHelper != null) dbHelper = new MyDbHelper(context);

        dbHelper.addContact(new Contact("Ravi", "9100000000"));
        dbHelper.addContact(new Contact("Srinivas", "9199999999"));
        dbHelper.addContact(new Contact("Tommy", "9522222222"));
        dbHelper.addContact(new Contact("Karthik", "9533333333"));
    }

    public static void query(JSONObject js, AsyncHandler<JSONArray> handler) throws JSONException {
        ArrayList<String> params = new ArrayList<>();
        String query = JsonToSqlParser.toSqlQuery(js, params);
        SQLiteDatabase dbr = dbr();
        Cursor cursor = null;
        try {
            JSONArray array = new JSONArray();
            cursor = dbr.rawQuery(query, params.toArray(new String[params.size()]));

            for (; cursor.moveToNext(); ) {

                JSONObject jsObj = new JSONObject();
                final int columnCount = cursor.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    OBTAINERS[cursor.getType(i)].put(jsObj, cursor.getColumnName(i), cursor, i);
                }
                array.put(jsObj);
            }
            handler.accept(null, array);
        } catch (Exception ex) {
            handler.accept(ex, null);
        } finally {
            if (cursor != null) cursor.close();
            dbr.close();
        }
    }

    public static void insert(JSONObject js, AsyncHandler<Long> handler) {
        SQLiteDatabase dbw = dbw();
        try {
            long id = dbw.insertOrThrow(js.optString("tableName"), null, toContentValues(js.optJSONObject("data")));
            handler.accept(null, id);
        } catch (Exception ex) {
            handler.accept(ex, null);
        } finally {
            dbw.close();
        }
    }

    public static void update(JSONObject js, AsyncHandler<Integer> handler) {
        SQLiteDatabase dbw = dbw();
        try {
            ArrayList<String> params = new ArrayList<>();
            String whereStr = JsonToSqlParser.whereStr(js, new StringBuilder(), params).toString();
            int update = dbw.update(js.optString("tableName"), toContentValues(js.optJSONObject("data")), whereStr, params.toArray(new String[params.size()]));
            handler.accept(null, update);
        } catch (Exception ex) {
            handler.accept(ex, null);
        } finally {
            dbw.close();
        }
    }

    public static void delete(JSONObject js, AsyncHandler<Integer> handler) {
        SQLiteDatabase dbw = dbw();
        try {
            ArrayList<String> params = new ArrayList<>();
            String where = JsonToSqlParser.whereStr(js.optJSONObject("where"), new StringBuilder(), params).toString();
            int delete = dbw.delete(js.optString("tableName"), where, params.toArray(new String[params.size()]));
            handler.accept(null, delete);
        } catch (Exception ex) {
            handler.accept(ex, null);
        } finally {
            dbw.close();
        }
    }

    private static ContentValues toContentValues(JSONObject js) {
        ContentValues contentValues = new ContentValues();
        Iterator<String> keys = js.keys();
        for (; keys.hasNext(); ) {
            String key = keys.next();
            Object val = js.opt(key);

            if (val instanceof Number) {
                if (val instanceof Float || val instanceof Double) {
                    contentValues.put(key, ((Number) val).floatValue());
                } else {
                    contentValues.put(key, ((Number) val).intValue());
                }
            } else if (val instanceof Boolean) {
                contentValues.put(key, (Boolean) val);
            } else if (val instanceof String) {
                contentValues.put(key, ((String) val));
            } else if (val instanceof byte[]) {
                contentValues.put(key, ((byte[]) val));
            } else {
                throw new RuntimeException("Invalid value :" + key + " -> " + val);
            }
        }
        return contentValues;
    }

    private static SQLiteDatabase dbw() {
        return dbHelper.getWritableDatabase();
    }

    private static SQLiteDatabase dbr() {
        return dbHelper.getReadableDatabase();
    }

    private static interface ValueObtainer {
        void put(JSONObject js, String key, Cursor cursor, int columnIndex) throws JSONException;
    }

    static {
        OBTAINERS = new ValueObtainer[5];
        OBTAINERS[Cursor.FIELD_TYPE_NULL] = new ValueObtainer() {
            @Override
            public void put(JSONObject js, String key, Cursor cursor, int columnIndex) throws JSONException {
                js.putOpt(key, null);
            }
        };
        OBTAINERS[Cursor.FIELD_TYPE_INTEGER] = new ValueObtainer() {
            @Override
            public void put(JSONObject js, String key, Cursor cursor, int columnIndex) throws JSONException {
                js.putOpt(key, cursor.getInt(columnIndex));
            }
        };
        OBTAINERS[Cursor.FIELD_TYPE_FLOAT] = new ValueObtainer() {
            @Override
            public void put(JSONObject js, String key, Cursor cursor, int columnIndex) throws JSONException {
                js.putOpt(key, cursor.getFloat(columnIndex));
            }
        };
        OBTAINERS[Cursor.FIELD_TYPE_STRING] = new ValueObtainer() {
            @Override
            public void put(JSONObject js, String key, Cursor cursor, int columnIndex) throws JSONException {
                js.putOpt(key, cursor.getString(columnIndex));
            }
        };
        OBTAINERS[Cursor.FIELD_TYPE_BLOB] = new ValueObtainer() {
            @Override
            public void put(JSONObject js, String key, Cursor cursor, int columnIndex) throws JSONException {
                js.putOpt(key, cursor.getBlob(columnIndex));
            }
        };
    }
}
