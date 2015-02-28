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

import com.techgrains.common.TGObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Singleton Session instance.
 *
 * Holds single instance of session in memory along with keyValues.
 * - Extremely handy to avoid local storage operation (shared preference, database etc.)
 * - Very handy to access from any layer of the application (from UI to Service)
 */
public class TGSession extends TGObject {

    // Static singleton instance
    private static TGSession session = null;

    // Non-static member variables
    private long createdAt;
    private Map<String, Object> keyValues;
    private Set<TGSessionListener> listeners;

    /**
     * Private constructor to avoid empty initialisation of session object.
     */
    private TGSession() {
    }

    /**
     * Returns current active session. It creates if not present or invalidated before.
     *
     * @return Current session if its already created. Otherwise creates new session.
     */
    public static TGSession instance() {
        if (!hasValue(session)) {
            createSession();
        }
        return session;
    }

    /**
     * Creates new session.
     */
    private static void createSession() {
        session = new TGSession();
        session.init();
    }

    /**
     * Checks session is valid or not by the session instance.
     *
     * @return boolean represents validity of session.
     */
    public static boolean isValid() {
        return hasValue(session);
    }

    /**
     * Initialize session member variables.
     */
    private void init() {
        createdAt = System.currentTimeMillis();
        keyValues = new HashMap<String, Object>();
        listeners = new HashSet<TGSessionListener>();
    }

    /**
     * Invalidate the current session. It also fires "sessionInvalidate" method of every added TGSessionListener.
     */
    public void invalidate() {
        for(TGSessionListener listener : listeners)
            listener.sessionInvalidate();

        session = null;
    }

    /**
     * Number of milliseconds passed since valid session has been created.
     *
     * @return Milliseconds
     */
    public long validSince() {
        return System.currentTimeMillis() - createdAt;
    }

    /**
     * Unique Session Id. (Timestamp of Session creation)
     *
     * @return Session Id
     */
    public long id() {
        return createdAt;
    }

    /* * * Key Values * * */

    /**
     * Store key-value pair as part of session. If provided key is already there, will be replaced with this new one.
     * Calls TGSessionListener.keyPut after adding into session.
     *
     * @param key Non-null String to represent as key
     * @param value Any Object as value paired with the key
     */
    public void put(String key, Object value) {
        keyValues.put(key, value);
        for(TGSessionListener listener : listeners)
            listener.keyPut(key);
    }

    /**
     * Fetches value from the session for the given key. It returns null if key is not available.
     *
     * @param key Non-null String to represent as key
     * @return Object which paired with the key as value
     */
    public Object get(String key) {
        return keyValues.get(key);
    }

    /**
     * Removes key-value pair from the session for the provided key. Calls TGSessionListener.keyRemove prior to remove from session.
     *
     * @param key Non-null String to represent as key
     */
    public void remove(String key) {
        for(TGSessionListener listener : listeners)
            listener.keyRemove(key);
        keyValues.remove(key);
    }

    /**
     * Checks key-value pair is available in the session.
     *
     * @param key Non-null String to represent as key
     * @return "true" if key is
     */
    public boolean hasKey(String key) {
        return keyValues.containsKey(key);
    }

    /**
     * Collects all keys from all the key-value pairs.
     *
     * @return Set of keys.
     */
    public Set<String> keys() {
        return keyValues.keySet();
    }

    /* * * Session Listener * * */
    /**
     * Adds given session listener to the collection of listeners.
     *
     * @param sessionListener TGSessionListener
     */
    public void addSessionListener(TGSessionListener sessionListener) {
        listeners.add(sessionListener);
    }

    /**
     * Removes provided session listener from the collection of listeners.
     *
     * @param sessionListener TGSessionListener
     */
    public void removeSessionListener(TGSessionListener sessionListener) {
        listeners.remove(sessionListener);
    }

    /**
     * Removes all Session listeners.
     */
    public void removeAllSessionListeners() {
        listeners.clear();
    }

    /**
     * Gives total counts of listeners
     *
     * @return count of session listeners
     */
    public int countSessionListeners() {
        return listeners.size();
    }

    /* * * Object Methods * * */

    @Override
    /**
     * Compares this instance with the specified object and indicates if they are equal.
     */
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof TGSession)) return false;

        TGSession other = (TGSession) object;
        if (other.id() != id()) return false;
        return true;
    }

    @Override
    /**
     * Returns an integer hash code for this object.
     */
    public int hashCode(){
        return Long.valueOf(id()).hashCode();
    }

}
