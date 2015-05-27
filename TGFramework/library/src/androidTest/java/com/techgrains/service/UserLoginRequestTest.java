package com.techgrains.service;

public class UserLoginRequestTest extends TGJsonRequest<UserLoginResponseTest> {
    public UserLoginRequestTest(int method, String url, TGIResponseListener<UserLoginResponseTest> listener, TGParams params) {
        super(method, url, listener, params);
        setShouldCache(false);
    }
}
