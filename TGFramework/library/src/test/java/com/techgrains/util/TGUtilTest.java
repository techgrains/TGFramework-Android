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

import org.junit.*;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class TGUtilTest {

    TGUtil util;

    long timeInMillis;
    Date date;

    @Before
    public void setUp() {
        util = new TGUtil();

        // Equals to "2005-03-16 7:30:45"
        timeInMillis = 1110938445000L;
        date = new Date(timeInMillis);
    }

    @After
    public void tearDown() {
        util = null;
    }

    @Test
    public void parseDate() throws ParseException {
        Date parsedDate = util.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss");
        assertEquals(date, parsedDate);
    }

    @Test
    public void parseAndFormatDateWithTimeZone() throws ParseException {
        Date date = util.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("EST"));
        assertEquals("2005-03-16 12:30:45.000+0000", util.formatDate(date, "yyyy-MM-dd HH:mm:ss.SSSZ", TimeZone.getTimeZone("GMT")));
    }

    @Test
    public void formatDate() {
        assertEquals("03/16/05", util.formatDate(date, "MM/dd/yy"));
    }

}
