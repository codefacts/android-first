package com.imslbd.android_first.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shahadat on 1/6/16.
 */
public class DbServiceTest {

    public static String selectStr() throws Exception {
        return DbService.selectStr(new JSONArray()
            .put(new JSONObject().put("name", "br_id").put("as", "b"))
            .put(new JSONObject().put("name", "name").put("as", "c").put("rename", "consumerName"))
            .put(new JSONObject().put("name", "id")), new StringBuilder(), new ArrayList<String>()).toString();
    }

    public static void joinStrTestSingle() {
        try {
            StringBuilder stringBuilder = DbService.joinStr(new JSONArray(Arrays.asList(
                new JSONObject().put("type", "inner join").put("name", "contacts").put("as", "c").put("field", "br_id")
                    .put("on", new JSONObject().put("id", "b"))
            )), new StringBuilder());
            System.out.println(stringBuilder);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String joinStrTestMulti() {
        try {
            StringBuilder stringBuilder = DbService.joinStr(new JSONArray(Arrays.asList(
                new JSONObject().put("type", "inner join").put("name", "brs").put("as", "b").put("field", "br_id")
                    .put("on", new JSONObject().put("br_id", "c")),
                new JSONObject().put("type", "inner join").put("name", "distribution_houses").put("as", "h").put("field", "house_id")
                    .put("on", new JSONObject().put("house_id", "b")),
                new JSONObject().put("type", "inner join").put("name", "areas_batb").put("as", "btb").put("field", "area_id")
                    .put("on", new JSONObject().put("area_id_batb", "h"))
            )), new StringBuilder());
            return stringBuilder.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String orderByTest() throws Exception {
        return DbService.orderByStr(new JSONArray(Arrays.asList(
            new JSONObject().put("as", "b").put("field", "name").put("order", "asc"),
            new JSONObject().put("as", "c").put("field", "name").put("order", "desc"),
            new JSONObject().put("as", "d").put("field", "name").put("order", "desc")
        )), new StringBuilder()).toString();
    }

    public static String whereTest() throws Exception {
        return DbService.whereStr(
            new JSONObject().put("type", OperatorTypes.BINERY)
                .put("op", "and")
                .put("opnd1", new JSONObject()
                    .put("type", OperatorTypes.BINERY)
                    .put("op", "<=").put("opnd1", 10).put("opnd2", 65))
                .put("opnd2", new JSONObject()
                    .put("type", OperatorTypes.BINERY)
                    .put("op", "or")
                    .put("opnd1", new JSONObject().put("type", OperatorTypes.BINERY)
                        .put("op", "==").put("opnd1", 778).put("opnd2", 75))
                    .put("opnd2", new JSONObject().put("type", OperatorTypes.UNERY_PREFIX)
                        .put("op", "!")
                        .put("opnd", new JSONObject()
                            .put("type", OperatorTypes.BINERY)
                            .put("op", "=")
                            .put("opnd1", new JSONObject()
                                .put("type", OperatorTypes.BINERY)
                                .put("op", "in")
                                .put("opnd1", 82)
                                .put("opnd2", new JSONObject()
                                    .put("type", OperatorTypes.LIST)
                                    .put("before", "(")
                                    .put("opnds", new JSONArray().put("sohan").put(1).put(true).put("5"))
                                    .put("seperator", ", ")
                                    .put("after", ")")))
                            .put("opnd2", new JSONObject()
                                .put("type", OperatorTypes.FIELD)
                                .put("name", "br_id")
                                .put("as", "b"))))), new StringBuilder(), new ArrayList<String>()).toString();
    }

    public static String whereTest2() {
        return DbService.whereStr(
            new JSONObject(), new StringBuilder(), new ArrayList<String>()).toString();
    }

    public static String toSqlTest() throws Exception {
        return DbService.toSql(
            new JSONObject()
                .putOpt("select", new JSONArray().put("br_name").put(new JSONObject().put("br_id", "b")).put(new JSONObject().put("name", "name").put("as", "c").put("rename", "consumerName")))
                .putOpt("from", new JSONArray().put(new JSONObject().putOpt("contacts", "c")))
                .putOpt("join", new JSONArray().put(new JSONObject().put("name", "brs").put("as", "b").put("type", "inner join").put("field", "br_id")
                    .putOpt("on", new JSONObject().put("br_id", "c")))
                    .put(new JSONObject().put("name", "distribution_houses").put("as", "h").put("type", "inner join").put("field", "house_id")
                        .put("on", new JSONObject().put("house_id", "b")))
                    .put(new JSONObject().put("name", "areas_imsl").put("as", "aml").put("type", "inner join").put("field", "area_id")
                        .put("on", new JSONObject().put("area_id_imsl", "h")))
                    .put(new JSONObject().put("name", "areas_batb").put("as", "btb").put("type", "inner join").put("field", "area_id")
                        .put("on", new JSONObject().put("area_id_batb", "h")))
                    .put(new JSONObject().put("name", "region").put("as", "r").put("type", "inner join").put("field", "region_id")
                        .put("on", new JSONObject().put("region_id", "aml"))))
                .put("where", 1)
                .put("groupBy", new JSONArray()
                    .put(new JSONObject().put("region_id", "r"))
                    .put(new JSONObject().put("area_id_batb", "btb"))
                    .put(new JSONObject().put("area_id_imsl", "aml"))
                    .put(new JSONObject().put("distribution_house_id", "h"))
                    .put(new JSONObject().put("br_id", "b")))
        );
    }
}
