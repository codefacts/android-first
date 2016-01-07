package com.imslbd.android_first.util;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by shahadat on 1/3/16.
 */
public class TypeMap implements Map<String, Object> {

    private final Map<String, Object> map;

    private TypeMap(Map<String, Object> map) {
        this.map = map;
    }

    public static TypeMap createTypeMap(Map<String, Object> map, TypeMap defaultTypeMap) {
        return map == null ? defaultTypeMap : new TypeMap(map);
    }

    public static TypeMap createTypeMap(Map<String, Object> map) {
        return new TypeMap(map);
    }

    public Number getNumber(String fieldName) {
        return (Number) map.get(fieldName);
    }

    public Long getLong(String fieldName) {
        Number num = (Number) map.get(fieldName);
        return num == null ? null : num.longValue();
    }

    public Integer getInteger(String fieldName) {
        Number num = (Number) map.get(fieldName);
        return num == null ? null : num.intValue();
    }

    public Boolean getBoolean(String fieldName) {
        return (Boolean) map.get(fieldName);
    }

    public byte[] getBinary(String fieldName) {
        String encoded = (String) map.get(fieldName);
        return Base64.decode(encoded, Base64.NO_WRAP);
    }

    public String getString(String fieldName, String def) {
        String str = getString(fieldName);
        return str == null ? def : str;
    }

    public String getString(String key) {
        CharSequence cs = (CharSequence) map.get(key);
        return cs == null ? null : cs.toString();
    }

    public boolean getBoolean(String fieldName, boolean def) {
        Boolean b = getBoolean(fieldName);
        return b == null ? def : b;
    }

    public Number getNumber(String fieldName, int def) {
        Number n = getNumber(fieldName);
        return n == null ? def : n;
    }

    public byte[] getBinary(String fieldName, byte[] def) {
        byte[] b = getBinary(fieldName);
        return b == null ? def : b;
    }

    public <R> R getValueAs(String key, Class<R> aClass, R defaultValue) {
        R valueAs = getValueAs(key, aClass);
        return valueAs == null ? defaultValue : valueAs;
    }

    public <R> R getValueAs(String key, Class<R> aClass) {
        return aClass.cast(map.get(key));
    }

    @NonNull
    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @NonNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object object) {
        return map.equals(object);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> map) {
        this.map.putAll(map);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }


}
