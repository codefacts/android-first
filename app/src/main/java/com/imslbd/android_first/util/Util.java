package com.imslbd.android_first.util;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shahadat on 1/3/16.
 */
public class Util {
    public static final String DATE_FORMAT = "dd-MMM-yyyy hh:mm:ss a";
    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    public static final JSONArray EMPTY_JSON_ARRAY = new JSONArray();
    public static final JSONObject EMPTY_JSON_OBJECT = new JSONObject();
    public static final Date MIN_DATE = new Date(0);
    public static final Date MAX_DATE = new Date(Long.MAX_VALUE);

    public static String formatDate(Date date) {
        return formatDate(date);
    }

    public static <R> R or(R val, R dv) {
        return val == null ? dv : val;
    }

    public static int intValue(Object value) {
        return as(value, Number.class).intValue();
    }

    public static int intValue(Object value, int dv) {
        return as(value, Number.class, dv).intValue();
    }

    public static long longValue(Object value) {
        return as(value, Number.class).longValue();
    }

    public static long longValue(Object value, long dv) {
        return as(value, Number.class, dv).longValue();
    }

    public static float floatValue(Object value) {
        return as(value, Number.class).floatValue();
    }

    public static float floatValue(Object value, float dv) {
        return as(value, Number.class, dv).floatValue();
    }

    public static double doubleValue(Object value) {
        return as(value, Number.class).doubleValue();
    }

    public static double doubleValue(Object value, double dv) {
        return as(value, Number.class, dv).doubleValue();
    }

    public static <R> R as(Object value, Class<R> rClass) {
        return rClass.cast(value);
    }

    public static <R> R as(Object value, Class<R> rClass, R defaultValue) {
        return value != null && rClass.isInstance(value) ? rClass.cast(value) : defaultValue;
    }

    public static Date parseDate(String value) {
        try {
            return DATE_FORMATTER.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseDate(Object value, Date defaultValue) {
        try {
            return value == null || value.toString().trim().isEmpty() ? defaultValue : DATE_FORMATTER.parse((String) value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject loadFromFile(File file) throws IOException, JSONException {
        return new JSONObject(toString(file));
    }

    public static String toString(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        try {
            byte[] bytes = new byte[(int) raf.length()];
            raf.readFully(bytes);
            return new String(bytes);
        } finally {
            raf.close();
        }
    }

    public static void storeInFile(byte[] buffer, File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "w");
        try {
            raf.write(buffer);
        } catch (Exception ex) {
            raf.close();
        }
    }

    public static File createFileIfNotExists(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static StringBuilder join(final String delimeter, Iterator<String> iterator, final StringBuilder builder) {
        Stream.of(iterator).forEach(new Consumer<String>() {
            @Override
            public void accept(String value) {
                builder.append(value).append(delimeter);
            }
        });
        if (builder.length() > 0)
            return builder.delete(builder.length() - delimeter.length(), builder.length());
        else return builder;
    }

    public static <T> List<T> toList(JSONArray array) {
        final ArrayList<T> objects = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            objects.add((T) array.opt(i));
        }
        return objects;
    }
}
