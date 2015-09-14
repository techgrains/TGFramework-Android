/*
 * Copyright 2015 Techgrains Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techgrains.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.techgrains.common.TGObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Utility class for TGFramework library, which includes date parsing and formatting.
 */
public class TGUtil extends TGObject {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * Parse Date by provided pattern along with target Time Zone.
     * Pattern Reference: http://developer.android.com/reference/java/text/SimpleDateFormat.html
     *
     * @param dateString String
     * @param pattern String
     * @param targetTimeZone TimeZone
     * @return java.util.Date which represents based on provided dateString and pattern
     * @throws ParseException ParseException
     */
    public static Date parseDate(String dateString, String pattern, TimeZone targetTimeZone) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if(targetTimeZone!=null)
            simpleDateFormat.setTimeZone(targetTimeZone);
        return simpleDateFormat.parse(dateString);
    }

    public static Date parseDate(String dateString, String pattern) throws ParseException {
        return parseDate(dateString, pattern, null);
    }

    /**
     * Format Date by provided pattern along with target Time Zone
     * Pattern Reference: http://developer.android.com/reference/java/text/SimpleDateFormat.html
     * @param date Date
     * @param pattern String
     * @param targetTimeZone TimeZone
     * @return String - formatted string
     */
    public static String formatDate(Date date, String pattern, TimeZone targetTimeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if(targetTimeZone!=null)
            simpleDateFormat.setTimeZone(targetTimeZone);
        return simpleDateFormat.format(date);
    }

    /**
     * Format Date by provided pattern
     * Pattern Reference: http://developer.android.com/reference/java/text/SimpleDateFormat.html
     * @param date Date
     * @param pattern String
     * @return String - formatted string
     */
    public static String formatDate(Date date, String pattern) {
        return formatDate(date, pattern, null);
    }

    /**
     * Format Date with default "yyyy-MM-dd'T'HH:mm:ss" pattern
     * @param date Date
     * @return String - formatted string
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATE_TIME_PATTERN, null);
    }

    /**
     * Current date and time in format of "yyyy-MM-dd'T'HH:mm:ss"
     * @return String
     */
    public static String currentDateTime() {
        return formatDate(new Date(), DATE_TIME_PATTERN, null);
    }

    /**
     * Current date in format of "yyyy-MM-dd"
     * @return String
     */
    public static String currentDate() {
        return formatDate(new Date(), DATE_PATTERN, null);
    }

    /**
     * Current time in format of "HH:mm:ss"
     * @return String
     */
    public static String currentTime() {
        return formatDate(new Date(), TIME_PATTERN, null);
    }

    /**
     * Object created from provided Json string.
     * Provide type: {@code Type type = new TypeToken<Employee>(){}.getType();}
     *
     * @param json String
     * @param type java.lang.reflect.Type
     * @return Object
     * @throws JsonSyntaxException Json Systax Fails
     */
    public static Object fromJson(String json, Type type) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * String created by provided Object.
     *
     * @param obj Object
     * @return String JSon
     */
    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    /**
     * Convert an object into HashMap.
     *
     * @param obj Object
     * @return Map
     */
    public static Map<String, Object> convertToMap(Object obj) {
//        return (Map<String, Object>)new Gson().fromJson(new Gson().toJson(obj), new TypeToken<HashMap<String, Object>>() {}.getType());
//        String json = new Gson().toJson(obj);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<Object>() {}.getType(), new IntegerDeserializer())
                .create();

        return (Map<String, Object>)gson.fromJson(new Gson().toJson(obj), new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    /**
     * Read the reader and gives content as String
     *
     * @param reader Reader
     * @return String
     * @throws IOException if unable to read the reader
     */
    public static String readReader(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * Read file and gives file content as String
     *
     * @param filename String
     * @return String
     * @throws IOException if unable to read file
     */
    public static String readFile(String filename) throws IOException {
        return readReader(new FileReader(filename));
    }

    /**
     * Converts InputStream to String
     *
     * @param inputStream InputStream
     * @return String
     * @throws IOException if unable to read input stream
     */
    public static String readInputStream(InputStream inputStream) throws IOException {
        return readReader(new InputStreamReader(inputStream));
    }

    /**
     * Generates random UUID.
     * @return String
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Trims params from URL.
     * @param url String
     * @return String URL without trailing params
     */
    public static String trimParamsFromUrl(String url) {
        int qIndex = url.indexOf("?");
        if(qIndex>=0)
            return url.substring(0, qIndex);
        return url;
    }

    private static class IntegerDeserializer implements JsonDeserializer<Object>
    {
        @Override
        public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException
        {
            if(true) new String("123");
            if(!json.isJsonNull()) {
                if (json.isJsonPrimitive()) {
                    return new Integer(10);
                }
            }
            return json;
        }
    }
}
