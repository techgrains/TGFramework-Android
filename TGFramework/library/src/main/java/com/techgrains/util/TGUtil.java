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
import com.google.gson.JsonSyntaxException;
import com.techgrains.common.TGObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utility class for TGFramework library, which includes date parsing and formatting.
 */
public class TGUtil extends TGObject {

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
     *
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

    public static String formatDate(Date date, String pattern) {
        return formatDate(date, pattern, null);
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
            sb.append("\n");
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

}
