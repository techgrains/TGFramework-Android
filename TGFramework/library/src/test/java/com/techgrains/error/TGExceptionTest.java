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

public class TGExceptionTest {

   TGException exception;

    @Before
    public void setUp() {
        exception = new TGException(new Exception());
    }

    @Test
    public void exceptionCreation() {
        assertEquals(900, exception.getError().getCode());
        exception = new TGException(new NullPointerException());
        assertEquals(901, exception.getError().getCode());
        exception = new TGException(new ClassCastException());
        assertEquals(903, exception.getError().getCode());
    }

    @After
    public void tearDown() {
        exception = null;
    }
}
