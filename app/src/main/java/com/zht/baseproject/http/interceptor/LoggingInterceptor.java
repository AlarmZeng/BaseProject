package com.zht.baseproject.http.interceptor;

import com.zht.baseproject.utils.LogUtils;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by ZHT on 2017/4/19.
 *
 * OkHttp日志拦截器
 */
public class LoggingInterceptor implements Interceptor {

    public static final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //请求服务器的url
        String url = request.url().toString();
        //请求方法
        String method = request.method();
        long t1 = System.nanoTime();
        LogUtils.d(TAG, String.format(Locale.getDefault(), "Send Method : %s, Request Url : %s", method, url));

        //request body(请求体)
        RequestBody requestBody = request.body();
        if (null != requestBody) {
            StringBuilder sb = new StringBuilder("Request Body [");
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();

            if (null != contentType) {
                charset = contentType.charset();

                if (null == charset) {
                    charset = Charset.forName("UTF-8");
                }
            }

            if (isPlaintext(buffer)) {
                sb.append(buffer.readString(charset));
                if (null != contentType) {
                    sb.append("(Content-Type = ").append(contentType.toString()).append(", ")
                            .append(requestBody.contentLength()).append("-byte body");
                }
            } else {
                if (null != contentType) {
                    sb.append("(Content-Type = ").append(contentType.toString())
                            .append(", binary").append(requestBody.contentLength()).append("-byte body omitted");
                }
            }

            sb.append("]");
            LogUtils.d(TAG, String.format(Locale.getDefault(), "%s %s", method, sb.toString()));
        }

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        //打印响应时间
        LogUtils.d(TAG, String.format(Locale.getDefault(), "Received response for [url = %s] in %.1fms", url, (t2 - t1) / 1e6d));

        //响应状态，是否成功
        LogUtils.d(TAG, String.format(Locale.CHINA, "Received response is %s, message [%s], code[%d]", response.isSuccessful() ? "success" : "fail", response.message(), response.code()));

        //响应头
        LogUtils.d(TAG, String.format(Locale.getDefault(), "Received response header is %s", response.headers().toString()));

        //从网络上获取数据时打印相关信息
        LogUtils.d(TAG, "Received network response message is : " + response.networkResponse());

        //从缓存上获取数据时打印相关信息
        LogUtils.d(TAG, "Received cache response message is : " + response.cacheResponse());

        //响应数据
        ResponseBody body = response.body();

        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        Charset charset = Charset.defaultCharset();
        MediaType contentType = body.contentType();
        if (null != contentType) {
            charset = contentType.charset(charset);
        }
        String bodyString = buffer.clone().readString(charset);

        LogUtils.d(TAG, String.format(Locale.getDefault(), "Received response json string [%s]", bodyString));

        return response;
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            e.printStackTrace();
            return false;
        }
    }
}
