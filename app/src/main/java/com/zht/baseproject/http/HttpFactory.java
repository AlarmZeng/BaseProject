package com.zht.baseproject.http;

/**
 * Created by ZHT on 2017/4/19.
 * 提供单例的HttpApi
 */

public class HttpFactory {

    private static final Object monitor = new Object();

    private static HttpApi httpApi = null;

    public static boolean isDebug = true;

    public static HttpApi getHttpApiSingleton() {
        if (httpApi == null) {
            synchronized (monitor) {
                if (httpApi == null) {
                    httpApi = new HttpRetrofit().getHttpApi();
                }
            }
        }

        return httpApi;
    }
}
