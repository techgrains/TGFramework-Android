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
    private int tgStatusCode;
    private Map<String, String> tgHeaders;
    private String tgResponseString;
    private long tgNetworkTimeInMillis;
    private boolean tgModified;
    private TGError tgError;

    public int getTgStatusCode() {
        return tgStatusCode;
    }

    public void setTgStatusCode(int tgStatusCode) {
        this.tgStatusCode = tgStatusCode;
    }

    public String getTgResponseString() {
        return tgResponseString;
    }

    public void setTgResponseString(String tgResponseString) {
        this.tgResponseString = tgResponseString;
    }

    public Map<String, String> getTgHeaders() {
        return tgHeaders;
    }

    public void setTgHeaders(Map<String, String> tgHeaders) {
        this.tgHeaders = tgHeaders;
    }

    public long getTgNetworkTimeInMillis() {
        return tgNetworkTimeInMillis;
    }

    public void setTgNetworkTimeInMillis(long tgNetworkTimeInMillis) {
        this.tgNetworkTimeInMillis = tgNetworkTimeInMillis;
    }

    public boolean isTgModified() {
        return tgModified;
    }

    public void setTgModified(boolean tgModified) {
        this.tgModified = tgModified;
    }

    public TGError getTgError() {
        return tgError;
    }

    public void setTgError(TGError tgError) {
        this.tgError = tgError;
    }

    public boolean hasError() {
        return hasValue(tgError);
    }

    public boolean isSuccess() {
        return !hasValue(tgError);
    }
}
