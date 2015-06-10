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
package com.techgrains.service;

import com.techgrains.common.TGObject;

import java.util.Map;
import java.util.HashMap;

/**
 * TGParams holds the parameters for TGRequest
 */
public class TGParams extends TGObject {

    private Map<String, String> params = new HashMap<String, String>();

    /**
     * Params as Map
     *
     * @return Map
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * Sets Map of Params
     * @param params Map
     */
    public void setParams(Map<String, String> params) {
        if(params==null) this.params.clear();
        else this.params = params;
    }

    /**
     * Put new param into the Map of Params
     *
     * @param key String
     * @param value String
     */
    public void putParam(String key, String value) {
        params.put(key, value);
    }

    /**
     * Checks key-value pair is available in the params.
     *
     * @param key Non-null String to represent as key
     * @return "true" if key is
     */
    public boolean hasKey(String key) {
        return params.containsKey(key);
    }

    /**
     * Fetches value from the params for the given key. It returns null if key is not available.
     *
     * @param key Non-null String to represent as key
     * @return Object which paired with the key as value
     */
    public Object get(String key) {
        return params.get(key);
    }

    /**
     * Removes key-value pair from the params for the provided key.
     *
     * @param key Non-null String to represent as key
     */
    public void remove(String key) {
        params.remove(key);
    }

}
