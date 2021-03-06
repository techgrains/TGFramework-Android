package com.techgrains.service;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.techgrains.error.TGError;
import com.techgrains.error.TGException;
import com.techgrains.util.TGUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TGJsonBodyRequest<T extends TGResponse> extends Request<T> {

    /** Default charset for JSON request. */
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private final TGIResponseListener<T> listener;

    private final String mRequestBody;

    private final Type type;

    public Type getType() {
        return this.type;
    }

    public TGJsonBodyRequest(int method, String url, String requestBody, TGIResponseListener<T> listener) {
        super(method, url, null);
        setRetryPolicy();
        this.listener = listener;
        mRequestBody = requestBody;
        this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public TGJsonBodyRequest(int method, String url, String requestBody, TGIResponseListener<T> listener, Type type) {
        super(method, url, null);
        setRetryPolicy();
        this.listener = listener;
        mRequestBody = requestBody;
        this.type = type;
    }

    public TGJsonBodyRequest(int method, String url, String requestBody, TGIResponseListener<T> listener, int timeout, int maxRetries ) {
        super(method, url, null);
        setRetryPolicy(timeout, maxRetries);
        this.listener = listener;
        mRequestBody = requestBody;
        this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public TGJsonBodyRequest(int method, String url, String requestBody, TGIResponseListener<T> listener, Type type, int timeout, int maxRetries ) {
        super(method, url, null);
        setRetryPolicy(timeout, maxRetries);
        this.listener = listener;
        mRequestBody = requestBody;
        this.type = type;
    }

    private void setRetryPolicy() {
        setRetryPolicy(new DefaultRetryPolicy(
                TGRequest.DEFAULT_TIMEOUT, TGRequest.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void setRetryPolicy(int timeout, int maxRetries) {
        setRetryPolicy(new DefaultRetryPolicy(
                timeout, maxRetries,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * Parsing the network response into standard TGResponse
     *
     * @param networkResponse NetworkResponse
     * @return {@code Response<T>} where T is instance of TGResponse
     */
    @Override
    final protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        TGResponse response = createTGResponse(networkResponse);
        try {
            T jsonObject = (T) TGUtil.fromJson(response.getNetworkResponse(), getType());
            populateTGResponseCoreInfo(response, jsonObject);

            if(listener!=null && jsonObject!=null)
                listener.onSuccessBackgroundThread(jsonObject);
            return Response.success((T)jsonObject, HttpHeaderParser.parseCacheHeaders(networkResponse));

        } catch (JsonSyntaxException jse) {
            response.setError(new TGException(jse).getError());
            response.getError().setMessage("Unable to convert json response to object. Please match JSon syntax with expected response object." + jse.getMessage());
            response.getError().setDetailMessage(jse.getMessage());
        } catch (ClassCastException cce) {
            cce.printStackTrace();
            response.setError(new TGException(cce).getError());
            response.getError().setMessage("Unable to convert json response to object. " + cce.getMessage());
            response.getError().setDetailMessage(cce.getMessage());
        } catch (Exception e) {
            response.setError(new TGException(e).getError());
            response.getError().setDetailMessage(e!=null ? e.getMessage() : "");
        }
        if(listener!=null)
            listener.onError(response);
        return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override
    protected void deliverResponse(T response) {
        if(response!=null)
            listener.onSuccessMainThread(response);
    }

    /**
     * Delivers the error on TGIResponseListener
     *
     * @param error VolleyError
     */
    @Override
    public void deliverError(VolleyError error) {
        TGResponse response = createTGResponse(error.networkResponse);
        TGError tgError = new TGException(error).getError();
        response.setError(tgError);
        if(response!=null) {
            if(error.getClass().equals(TimeoutError.class))
                response.setTimeout(true);
            listener.onError(response);
        }
    }

    /**
     * @deprecated Use {@link #getBodyContentType()}.
     */
    @Override
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    /**
     * @deprecated Use {@link #getBody()}.
     */
    @Override
    public byte[] getPostBody() {
        return getBody();
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    private TGResponse createTGResponse(NetworkResponse networkResponse) {
        TGResponse response = new TGResponse();
        if(networkResponse!=null) {
            response.setStatusCode(networkResponse.statusCode);
            response.setNetworkResponse(new String(networkResponse.data));
            response.setHeaders(networkResponse.headers);
            response.setNetworkTimeInMillis(networkResponse.networkTimeMs);
            response.setModified(!networkResponse.notModified);
        }
        return response;
    }

    /**
     * Copy core TGResponse info from source to destination.
     *
     * @param source TGResponse
     * @param jsonObject TGResponse
     */
    private void populateTGResponseCoreInfo(TGResponse source, TGResponse jsonObject) {
        if(source!=null && jsonObject!=null) {
            jsonObject.setStatusCode(source.getStatusCode());
            jsonObject.setNetworkResponse(new String(source.getNetworkResponse()));
            jsonObject.setHeaders(source.getHeaders());
            jsonObject.setNetworkTimeInMillis(source.getNetworkTimeInMillis());
            jsonObject.setModified(source.isModified());
        }
    }

}
