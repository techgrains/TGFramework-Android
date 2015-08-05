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

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.junit.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class TGUtilTest {

    long timeInMillis;
    Date date;

    @Before
    public void setUp() {
        // Equals to "2005-03-16 7:30:45"
        timeInMillis = 1110938445000L;
        date = new Date(timeInMillis);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void parseDate() throws ParseException {
        Date parsedDate = TGUtil.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss");
        assertEquals(date, parsedDate);
    }

    @Test
    public void parseAndFormatDateWithTimeZone() throws ParseException {
        Date date = TGUtil.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("EST"));
        assertEquals("2005-03-16 12:30:45.000+0000", TGUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss.SSSZ", TimeZone.getTimeZone("GMT")));
    }

    @Test
    public void formatDate() {
        assertEquals("03/16/05", TGUtil.formatDate(date, "MM/dd/yy"));
        assertTrue(TGUtil.formatDate(date).startsWith("2005-03-16"));
    }

    @Test
    public void fromJsonSuccess() {
        Employee employee = (Employee) TGUtil.fromJson(employeeJson(), new TypeToken<Employee>(){}.getType());

        assertNotNull(employee);
        assertEquals("vishal", employee.name);
        assertEquals(35, employee.age);
        assertEquals(54376.43, employee.salary, 2);
        assertEquals(2, employee.departments.size());
        assertEquals(1, employee.departments.get(0).id);
        assertEquals("hr", employee.departments.get(0).name);
        assertEquals(2, employee.departments.get(1).id);
        assertEquals("it", employee.departments.get(1).name);
    }

    @Test
    public void fromJsonFailure() {
        try {
            Employee employee = (Employee) TGUtil.fromJson("{type:wrong json}", new TypeToken<Employee>() {}.getType());
            fail("Exception must be thrown.");
        } catch (JsonSyntaxException jse) {
            assertNotNull(jse);
        }
    }

    private String employeeJson() {
        return "{name:vishal, age:35, salary:54376.43, departments:[{id:1,name:hr},{id:2,name:it}]}";
    }
}

class Employee {
    String name;
    int age;
    float salary;
    List<Department> departments;
}

class Department {
    int id;
    String name;
}
