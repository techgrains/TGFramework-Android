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
package com.techgrains.common;

import java.util.Collection;

/**
 * Base class for TGFramework library, having all the basic supportive methods.
 */
public class TGObject {

    /**
     * Check any object having value or not, currently supports: Object, String, Collection
     *
     * @param object Object
     * @return If provided object is not null and not empty "true", otherwise "false"
     */
    public static boolean hasValue(Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof String) {
            return ((String) object).length() > 0;
        } else if (object instanceof Collection) {
            return ((Collection) object).size() > 0;
        }
        return true;
    }

    /**
     * Null safe by replacing it with empty string
     *
     * @param string String
     * @return Input param string if its not null, otherwise empty string
     */
    public static String nullSafeString(String string) {
        return nullSafeString(string, "");
    }

    /**
     * Null safe by replacing it with provided default value in case of null
     *
     * @param string       String
     * @param defaultValue String
     * @return Input param string if its not null, otherwise provided default value
     */
    public static String nullSafeString(String string, String defaultValue) {
        return string == null ? defaultValue : string;
    }

    /**
     * Null safe by replacing it with Integer having value 0 (zero)
     *
     * @param integer Integer
     * @return Input param integer if its not null, otherwise Integer having value 0 (zero)
     */
    public static Integer nullSafeInteger(Integer integer) {
        return nullSafeInteger(integer, Integer.valueOf(0));
    }

    /**
     * Null safe by replacing it with provided default value in case of null
     *
     * @param integer      Integer
     * @param defaultValue Integer
     * @return Input param integer if its not null, otherwise provided default value
     */
    public static Integer nullSafeInteger(Integer integer, Integer defaultValue) {
        return integer == null ? defaultValue : integer;
    }

    /**
     * Null safe by replacing it with Long having value 0 (zero)
     *
     * @param aLong Long
     * @return Input param aLong if its not null, otherwise Long having value 0 (zero)
     */
    public static Long nullSafeLong(Long aLong) {
        return nullSafeLong(aLong, Long.valueOf(0));
    }

    /**
     * Null safe by replacing it with provided default value in case of null
     *
     * @param aLong        Long
     * @param defaultValue Long
     * @return Input param aLong if its not null, otherwise provided default value
     */
    public static Long nullSafeLong(Long aLong, Long defaultValue) {
        return aLong == null ? defaultValue : aLong;
    }

    /**
     * Null safe by replacing it with Double having value 0 (zero)
     *
     * @param aDouble Double
     * @return Input param aDouble if its not null, otherwise Double having value 0 (zero)
     */
    public static Double nullSafeDouble(Double aDouble) {
        return nullSafeDouble(aDouble, Double.valueOf(0));
    }

    /**
     * Null safe by replacing it with provided default value in case of null
     *
     * @param aDouble      Double
     * @param defaultValue Double
     * @return Input param aDouble if its not null, otherwise provided default value
     */
    public static Double nullSafeDouble(Double aDouble, Double defaultValue) {
        return aDouble == null ? defaultValue : aDouble;
    }

}
