package com.imslbd.android_first.service;

import com.imslbd.android_first.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.crm.intfs.ConsumerUnchecked;

import static com.imslbd.android_first.util.Util.EMPTY_JSON_ARRAY;
import static com.imslbd.android_first.util.Util.EMPTY_JSON_OBJECT;
import static com.imslbd.android_first.util.Util.or;

/**
 * Created by shahadat on 1/5/16.
 */
public class JsonToSqlParser {
    public static void inset(String tableName, JSONObject data, ConsumerUnchecked<Long> consumerUnchecked) {

    }

    public static void main(String... args) {
//        toSqlQuery(new JSONObject()
//                .put("select", new JSONArray()
//                                .put(new JSONObject().put("id", "").put("as", ""))
//                                .put(new JSONObject().put("name", "name").put("as", ""))
//                )
//                .put("from", new JSONArray()
//                        .put(new JSONObject().put("name", ))));
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject().putOpt("id", "").putOpt("name", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String toSqlInsert(JSONObject js, List<String> params) {
        JSONObject data = js.optJSONObject("data");
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO `").append(js.optString("tableName")).append("` ");
        Util.join(", ", data.keys(), builder, "`", "`")
            .append(") ")
            .append("VALUES (");

        values(data, builder, params);

        builder.append(")");

        return builder.toString();
    }

    public static String toSqlUpdate(JSONObject js, List<String> params) {
        JSONObject data = js.optJSONObject("data");
        StringBuilder builder = new StringBuilder();

        builder.append("UPDATE ").append("`").append(js.optString("tableName")).append("`").append(" SET ");

        Iterator<String> keys = data.keys();
        final int length = builder.length();
        for (; keys.hasNext(); ) {
            String key = keys.next();
            builder.append("`").append(key).append("`").append(" = ").append(singleOperand(data.opt(key), params)).append(", ");
        }
        if (builder.length() > length) {
            builder.delete(builder.length() - ", ".length(), builder.length());
        }
        String where = whereStr(js.optJSONObject("where"), new StringBuilder(), params).toString();
        where = where.trim().isEmpty() ? "" : "where " + where;

        return builder.append(" ").append(where).toString();
    }

    public static String toSqlDelete(JSONObject js, ArrayList<String> params) {
        StringBuilder builder = new StringBuilder();
        builder.append("delete from ").append(js.optString("tableName"));
        String where = whereStr(js.optJSONObject("where"), new StringBuilder(), params).toString();
        where = where.trim().isEmpty() ? "" : "where " + where;
        return builder.append(" ").append(where).toString();
    }

    public static String toSqlQuery(JSONObject query, List<String> params) {
        return toSqlQuery(or(query.optJSONArray("select"), EMPTY_JSON_ARRAY),
            or(query.optJSONArray("from"), EMPTY_JSON_ARRAY),
            or(query.optJSONArray("join"), EMPTY_JSON_ARRAY),
            or(query.optJSONObject("where"), EMPTY_JSON_OBJECT),
            or(query.optJSONArray("orderBy"), EMPTY_JSON_ARRAY),
            or(query.optJSONArray("groupBy"), EMPTY_JSON_ARRAY),
            or(query.optJSONObject("having"), EMPTY_JSON_OBJECT), params);
    }

    public static String toSqlQuery(JSONArray select, JSONArray from, JSONArray join,
                                    JSONObject where, JSONArray orderBy,
                                    JSONArray groupBy, JSONObject having, List<String> params) {

        String selectStr = selectStr(select, new StringBuilder(), params).toString();
        String fromStr = fromStr(from, new StringBuilder()).toString();
        String joinStr = joinStr(join, new StringBuilder()).toString();
        String whereStr = whereStr(where, new StringBuilder(), params).toString();
        String groupByStr = groupByStr(groupBy, new StringBuilder()).toString();
        String havingStr = havingStr(having, new StringBuilder(), params).toString();
        String orderByStr = orderByStr(orderBy, new StringBuilder()).toString();

        selectStr = selectStr.isEmpty() ? "select *" : "select " + selectStr;
        fromStr = "from " + fromStr;
        whereStr = whereStr.trim().isEmpty() ? "" : "where " + whereStr;

        if (!groupByStr.trim().isEmpty()) {
            groupByStr = "group by " + groupByStr;
            havingStr = havingStr.trim().isEmpty() ? "" : "having " + havingStr;
        }

        return selectStr + " " + fromStr + " " + joinStr + " " + whereStr + " " + groupByStr + " " + havingStr + " " + orderByStr;
    }

    public static StringBuilder joinStr(JSONArray joinList, final StringBuilder builder) {
        final int length = builder.length();
        for (int i = 0; i < joinList.length(); i++) {
            JSONObject value = joinList.optJSONObject(i);
            JSONObject on = value.optJSONObject("on");
            String toField = on.keys().next();
            builder.append(value.optString("type", "")).append(" ").append(value.optString("name"))
                .append(" ").append(value.optString("as"))
                .append(" on ")
                .append(value.optString("as").isEmpty() ? "" : value.optString("as") + ".")
                .append(value.optString("field"))
                .append(" = ")
                .append(on.optString(toField).isEmpty() ? "" : on.optString(toField) + ".")
                .append(toField).append(" ");
        }
        return builder.length() > length ? builder.delete(builder.length() - " ".length(), builder.length()) : builder;
    }

    public static StringBuilder fromStr(JSONArray from, StringBuilder builder) {
        final int length = builder.length();
        for (int i = 0; i < from.length(); i++) {
            Object obj = from.opt(i);

            if (obj instanceof JSONObject) {
                JSONObject js = (JSONObject) obj;
                if (js.length() == 1) {
                    String name = js.keys().next();
                    String as = js.optString(name, "");
                    builder.append(name).append(as.isEmpty() ? "" : " " + as).append(", ");
                } else {
                    throw new RuntimeException("Json Object does not contain a valid key to parse. JS: " + js.toString());
                }
            } else if (obj instanceof String) {
                builder.append((String) obj).append(", ");
            } else {
                throw new RuntimeException("Input Object is not valid. Obj: " + obj);
            }
        }
        return builder.length() > length ? builder.delete(builder.length() - ", ".length(), builder.length()) : builder;
    }

    public static StringBuilder selectStr(JSONArray select, final StringBuilder builder, List<String> params) {
        final int length = builder.length();

        for (int i = 0; i < select.length(); i++) {
            Object obj = select.opt(i);

            if (obj instanceof JSONObject) {
                JSONObject js = (JSONObject) obj;
                if (js.length() == 1) {
                    if (js.has("$param")) {
                        builder.append(singleOperand(js.opt("$param"), params));
                    } else {
                        String name = js.keys().next();
                        String as = js.optString(name, "");
                        builder.append(as.isEmpty() ? "" : as + ".").append(name).append(", ");
                    }
                } else if (js.has("rename")) {
                    builder.append(js.optString("as", "").isEmpty() ? "" : js.optString("as") + ".")
                        .append(js.optString("name")).append(js.optString("rename", "").isEmpty() ? "" : " as " + js.optString("rename"))
                        .append(", ");
                } else {
                    throw new RuntimeException("Json Object does not contain a valid key to parse. JS: " + js.toString());
                }
            } else if (obj instanceof String) {
                builder.append((String) obj).append(", ");
            } else {
                throw new RuntimeException("Input Object is not valid. Obj: " + obj);
            }
        }
        return builder.length() > length ? builder.delete(builder.length() - ", ".length(), builder.length()) : builder;
    }

    public static StringBuilder havingStr(JSONObject having, StringBuilder builder, List<String> params) {
        return parseOp(having, new StringBuilder(), params);
    }

    public static StringBuilder orderByStr(JSONArray orderBy, final StringBuilder builder) {
        final int length = builder.length();
        for (int i = 0; i < orderBy.length(); i++) {
            JSONObject value = orderBy.optJSONObject(i);
            builder.append(value.optString("as") == null ? "" : value.optString("as") + ".")
                .append(value.optString("name")).append(" ").append(value.optString("order"))
                .append(", ");
        }
        return builder.length() > length ? builder.delete(builder.length() - ", ".length(), builder.length()) : builder;
    }

    public static StringBuilder groupByStr(JSONArray groupBy, final StringBuilder builder) {
        final int length = builder.length();

        for (int i = 0; i < groupBy.length(); i++) {

            Object obj = groupBy.opt(i);

            if (obj instanceof JSONObject) {
                JSONObject js = (JSONObject) obj;
                if (js.length() == 1) {

                    String name = js.keys().next();
                    String as = js.optString(name, "");

                    builder.append(as.isEmpty() ? "" : as + ".")
                        .append(name).append(", ");
                } else {
                    throw new RuntimeException("Json Object does not contain a valid key to parse. JS: " + js.toString());
                }
            } else if (obj instanceof String) {
                builder.append((String) obj).append(", ");
            } else {
                throw new RuntimeException("Input Object is not valid. Obj: " + obj);
            }
        }

        return builder.length() > length ? builder.delete(builder.length() - ", ".length(), builder.length()) : builder;
    }

    public static StringBuilder whereStr(JSONObject where, StringBuilder builder, List<String> params) {
        return parseOp(where, builder, params);
    }

    public static String escapeQuote(String s) {
        return "'" + s.replace("'", "\\'") + "'";
    }

    private static StringBuilder values(JSONObject js, StringBuilder builder, List<String> params) {
        Iterator<String> keys = js.keys();
        final int length = builder.length();
        for (; keys.hasNext(); ) {
            String key = keys.next();
            Object obj = js.opt(key);
            if (obj instanceof String) {
                builder.append("?").append(", ");
                params.add((String) obj);
            } else {
                builder.append(obj).append(", ");
            }
        }
        if (builder.length() > length) {
            builder.delete(builder.length() - ", ".length(), builder.length());
        }
        return builder;
    }

    private static String singleOperand(Object operand, List<String> params) {
        if (operand instanceof String) {
            params.add((String) operand);
            return "?";
        }
        return operand.toString();
    }

    private static StringBuilder parseOp(JSONObject js, StringBuilder builder, List<String> params) {

        int type = js.optInt("type");

        if (type == OperatorTypes.UNERY_PREFIX) {

            builder.append(js.optString("op")).append(" ");

            if (js.opt("opnd") instanceof JSONObject) {
                parseOp(js.optJSONObject("opnd"), builder, params);
            } else {
                builder.append(singleOperand(js.opt("opnd"), params));
            }

        } else if (type == OperatorTypes.BINERY) {

            builder.append("(");
            if (js.opt("opnd1") instanceof JSONObject) {
                parseOp(js.optJSONObject("opnd1"), builder, params);
            } else {
                builder.append(singleOperand(js.opt("opnd1"), params));
            }
            builder.append(" ").append(js.optString("op")).append(" ");
            if (js.opt("opnd2") instanceof JSONObject) {
                parseOp(js.optJSONObject("opnd2"), builder, params);
            } else {
                builder.append(singleOperand(js.opt("opnd2"), params));
            }
            builder.append(")");
        } else if (type == OperatorTypes.FIELD) {
            builder.append(js.optString("as", "").isEmpty() ? js.optString("name") : js.optString("as") + "." + js.optString("name"));

        } else if (type == OperatorTypes.LIST) {
            builder.append(js.optString("before"));
            String seperator = js.optString("seperator");
            JSONArray list = js.optJSONArray("opnds");
            final int length = builder.length();
            for (int i = 0; i < list.length(); i++) {
                builder.append(singleOperand(list.opt(i), params)).append(seperator);
            }
            if (builder.length() > length) {
                builder.delete(builder.length() - seperator.length(), builder.length());
            }
            builder.append(js.optString("after"));
        }
        return builder;
    }
}
