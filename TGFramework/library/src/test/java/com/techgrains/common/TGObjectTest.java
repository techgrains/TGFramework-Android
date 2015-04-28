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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class TGObjectTest {

    TGObject object;

    @Before
    public void setUp() {
        object = new TGObject();
    }

    @After
    public void tearDown() {
        object = null;
    }

    @Test
    public void hasValue() {
        // String
        String nullString = null;
        String emptyString = "";
        String string = "test";
        assertEquals(false, object.hasValue(nullString));
        assertEquals(false, object.hasValue(emptyString));
        assertEquals(true, object.hasValue(string));

        // Integer
        Integer nullInteger = null;
        Integer integer = Integer.valueOf(5);
        assertEquals(false, object.hasValue(nullInteger));
        assertEquals(true, object.hasValue(integer));

        // Collection
        Collection nullCollection = null;
        Collection emptyCollection = new ArrayList<String>();
        Collection collection = new ArrayList<String>();
        collection.add("One");

        assertEquals(false, object.hasValue(nullCollection));
        assertEquals(false, object.hasValue(emptyCollection));
        assertEquals(true, object.hasValue(collection));

        collection.clear();
        assertEquals(false, object.hasValue(collection));

    }

    @Test
    public void nullSafeString() {
        String someValue = "some value";
        String defaultValue = "default value";
        assertEquals("", object.nullSafeString(null));
        assertEquals(someValue, object.nullSafeString(someValue));
        assertEquals(defaultValue, object.nullSafeString(null, defaultValue));
        assertEquals(someValue, object.nullSafeString(someValue, defaultValue));
    }

    @Test
    public void nullSafeInteger() {
        Integer someValue = Integer.valueOf(15);
        Integer defaultValue = Integer.valueOf(25);
        assertEquals(Integer.valueOf(0), object.nullSafeInteger(null));
        assertEquals(someValue, object.nullSafeInteger(someValue));
        assertEquals(defaultValue, object.nullSafeInteger(null, defaultValue));
        assertEquals(someValue, object.nullSafeInteger(someValue, defaultValue));
    }

    @Test
    public void nullSafeLong() {
        Long someValue = Long.valueOf(30);
        Long defaultValue = Long.valueOf(50);
        assertEquals(Long.valueOf(0), object.nullSafeLong(null));
        assertEquals(someValue, object.nullSafeLong(someValue));
        assertEquals(defaultValue, object.nullSafeLong(null, defaultValue));
        assertEquals(someValue, object.nullSafeLong(someValue, defaultValue));
    }

    @Test
    public void nullSafeDouble() {
        Double someValue = Double.valueOf(123.456);
        Double defaultValue = Double.valueOf(222.555);
        assertEquals(Double.valueOf(0), object.nullSafeDouble(null));
        assertEquals(someValue, object.nullSafeDouble(someValue));
        assertEquals(defaultValue, object.nullSafeDouble(null, defaultValue));
        assertEquals(someValue, object.nullSafeDouble(someValue, defaultValue));
    }
}
