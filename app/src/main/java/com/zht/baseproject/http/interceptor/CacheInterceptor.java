package com.zht.baseproject.http.interceptor;

import com.zht.baseproject.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZHT on 2016/12/25.
 *
 * OkHttp缓存拦截器
 * 只能缓存get请求，不能缓存post请求
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //没网络的情况强制读取缓存(必须判断，避免断网情况下获取不到缓存)
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);
        if (NetworkUtils.isConnected()) {
            int maxAge = 60;  //在有网络的情况下，缓存失效的时间为60秒
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;  //没有网络的情况下，缓存失效的时间为4周
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }

    }
}
