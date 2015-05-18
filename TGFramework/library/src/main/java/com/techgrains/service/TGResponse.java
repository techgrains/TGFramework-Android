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
import com.techgrains.error.TGError;

import java.util.Map;

/**
 * TGResponse holds HTTP response information once TGRequest has been generated.
 */
public class TGResponse extends TGObject{
    private int httpStatusCode;
    private Map<String, String> headers;
    private String response;
    private long networkTimeInMillis;
    private boolean modified;
    private TGError error;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public long getNetworkTimeInMillis() {
        return networkTimeInMillis;
    }

    public void setNetworkTimeInMillis(long networkTimeInMillis) {
        this.networkTimeInMillis = networkTimeInMillis;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public TGError getError() {
        return error;
    }

    public void setError(TGError error) {
        this.error = error;
    }

    public boolean hasError() {
        return hasValue(error);
    }

    public boolean isSuccess() {
        return !hasValue(error);
    }
}
