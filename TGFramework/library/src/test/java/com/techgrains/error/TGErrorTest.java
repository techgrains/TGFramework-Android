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
package com.techgrains.error;

import org.junit.*;
import static org.junit.Assert.*;

public class TGErrorTest {

   TGError error;

    @Before
    public void setUp() {
        error = new TGError(0, null);
    }

    @Test
    public void errorCreation() {
        int code = 101;
        String message = "Some error.";
        error = new TGError(code, message);
        assertEquals(code, error.getCode());
        assertEquals(message, error.getMessage());
    }

    @After
    public void tearDown() {
       error = null;
    }
}
