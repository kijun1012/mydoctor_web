package com.mydoctor.module;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;



/**
 * Created by PC on 2017-04-30.
 */

public class JsonPassingModule {

    public static Object jsonToObject(JSONObject jsonObj, Class c) {
        Gson gson = new Gson();
        Object returnObj = null;
        try {
            returnObj = gson.fromJson(jsonObj.toString(), c);
        }catch(Exception e) {
          
            return returnObj;
        }

        return returnObj;
    }

    public static List jsonArrayToObject(JSONArray jsonArray, Class c) {
        List<Object> returnList = new ArrayList<Object>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                Object parsingObj = jsonToObject(obj, c);
                returnList.add(parsingObj);
            }
        }catch(Exception e) {
            
        }

        return returnList;
    }

    public static String objectToJson(Object source) {
        Gson gson = new Gson();
        String returnStr = "";
        try {
            returnStr = gson.toJson(source);
        }catch (Exception e) {
           
            return returnStr;
        }

        return returnStr;
    }

    public static JSONObject objectToJsonObject(Object source) {
        Gson gson = new Gson();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(source));
        }catch (Exception e) {
           
            return jsonObject;
        }

        return jsonObject;
    }
}