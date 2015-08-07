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

import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class TGAndroidUtilTest extends TestCase {

    final static String MOCK_DIR = "mock";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsNetworkAvailable() throws Exception {
        try {
            boolean check = TGAndroidUtil.isNetworkAvailable();
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Exception in isNetworkAvailable()", false);
        }
    }

    public void testReadFile() throws IOException {
        String filename = MOCK_DIR + "/UserLoginResponse.json";
        String content = TGAndroidUtil.readFileFromAssets(filename);
        assertTrue(TGUtil.hasValue(content));
    }

    public void testSerializeDeserializeString() {
        try {
            String serialized = TGAndroidUtil.serialize("Vishal Patel");
            //assertEquals("test", serialized);
            String deserialized = (String) TGAndroidUtil.deserialize(serialized);
            assertEquals("Vishal Patel", deserialized);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception must not occur.");
        }
    }

    public void testSerializeDeserializeObject() {
        Employee employee = new Employee("Vishal", 35, 50000);
        try {
            String serialized = TGAndroidUtil.serialize(employee);
            Employee deserialized = (Employee) TGAndroidUtil.deserialize(serialized);
            assertNotNull(deserialized);
            assertEquals(employee.name, deserialized.name);
            assertEquals(employee.age, deserialized.age);
            assertTrue(employee.salary == deserialized.salary);
        } catch (Exception e) {
            fail("Exception must not occur.");
        }
    }
}

class Employee implements Serializable {
    String name;
    int age;
    float salary;
    List<Department> departments;

    public Employee() {}
    public Employee(String name, int age, float salary) {
        this.name=name;
        this.age=age;
        this.salary=salary;
    }
}

class Department {
    int id;
    String name;
}

