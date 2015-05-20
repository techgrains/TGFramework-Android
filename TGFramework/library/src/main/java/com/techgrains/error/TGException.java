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

import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;

public class TGException extends Exception {

    private static final long serialVersionUID = 1L;
    private TGError error;

    public TGException(TGError error) {
        this.error = error;
    }

    public TGException(Exception e) {
        if (e instanceof TGException) {
            this.error = ((TGException) e).getError();
        } else if (e instanceof NullPointerException) {
            this.error = TGErrorConstant.NULL_POINTER.getError();
        } else if (e instanceof ClassCastException) {
            this.error = TGErrorConstant.CLASS_CAST.getError();
        } else if (e instanceof FileNotFoundException) {
            this.error = TGErrorConstant.FILE_NOT_FOUND.getError();
        } else if (e instanceof JsonSyntaxException) {
            this.error = TGErrorConstant.NOT_VALID_JSON.getError();
        } else {
            this.error = TGErrorConstant.UNKNOWN_ERROR.getError();
        }
    }

    public TGError getError() {
        return error;
    }

}

// Pre defined error constants
enum TGErrorConstant {

    UNKNOWN_ERROR(900, "Unknown Error Occured"),
    NULL_POINTER(901, "Null Pointer Exception Occurs"),
    FILE_NOT_FOUND(902, "File not found Exception"),
    CLASS_CAST(903, "Class Cast Exception"),
    NOT_VALID_JSON(904, "Class Cast Exception");

    private TGError error;

    TGErrorConstant(int code, String message) {
        this.error = new TGError(code, message);
    }

    public TGError getError() {
        return error;
    }

}