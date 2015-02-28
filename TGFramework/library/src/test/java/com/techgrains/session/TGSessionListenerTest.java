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
package com.techgrains.session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TGSessionListenerTest {

    TGSession session;

    @Before
    public void setUp() {
        session = TGSession.instance();
    }

    @After
    public void tearDown() {
        session.invalidate();
    }

    @Test
    public void sessionListener() {
        TestSessionListener listener = new TestSessionListener();
        session.addSessionListener(listener);
        assertFalse(listener.isSessionInvalidate);

        // sessionInvalidate()
        session.invalidate();
        assertTrue(listener.isSessionInvalidate);
        listener.reset();

        // sessionCreated()
        session = TGSession.instance();
        assertFalse(listener.isSessionInvalidate);

        // Listener won't be called after removing listener
        session.removeSessionListener(listener);
        listener.reset();
        session.invalidate();
        assertFalse(listener.isSessionInvalidate);
        session = TGSession.instance();
        assertFalse(listener.isSessionInvalidate);
    }

    @Test
    public void sessionListenerCount() {
        TestSessionListener listener1 = new TestSessionListener();
        TestSessionListener listener2 = new TestSessionListener();
        session.addSessionListener(listener1);
        assertEquals(1, session.countSessionListeners());
        session.addSessionListener(listener2);
        assertEquals(2, session.countSessionListeners());
        session.addSessionListener(listener1);
        assertEquals(2, session.countSessionListeners());
        session.removeSessionListener(listener2);
        assertEquals(1, session.countSessionListeners());
        session.addSessionListener(listener2);
        session.removeAllSessionListeners();
        assertEquals(0, session.countSessionListeners());
    }

    @Test
    public void multipleSessionListeners() {
        TestSessionListener listener1 = new TestSessionListener();
        TestSessionListener listener2 = new TestSessionListener();
        session.addSessionListener(listener1);
        session.addSessionListener(listener2);
        assertFalse(listener1.isSessionInvalidate);
        assertFalse(listener2.isSessionInvalidate);
        session.invalidate();
        assertTrue(listener1.isSessionInvalidate);
        assertTrue(listener2.isSessionInvalidate);
    }

    @Test
    public void keyPut() {
        TestSessionListener listener = new TestSessionListener();
        session.addSessionListener(listener);
        assertEquals(null,listener.lastPutKey);
        session.put("Some Key","Some Value");
        assertEquals("Some Key",listener.lastPutKey);
    }

    @Test
    public void keyRemove() {
        TestSessionListener listener = new TestSessionListener();
        session.addSessionListener(listener);
        assertEquals(null,listener.lastPutKey);
        session.remove("Some Key");
        assertEquals("Some Key",listener.lastRemoveKey);
    }

    public class TestSessionListener implements TGSessionListener {
        boolean isSessionInvalidate;
        String lastPutKey;
        String lastRemoveKey;

        @Override
        public void sessionInvalidate() {
            isSessionInvalidate = true;
        }

        @Override
        public void keyPut(String key) {
            lastPutKey = key;
        }

        @Override
        public void keyRemove(String key) {
            lastRemoveKey = key;
        }

        public void reset() {
            isSessionInvalidate = false;
        }
    }
}
