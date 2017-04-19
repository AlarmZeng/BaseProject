package com.zht.baseproject.http;

import com.zht.baseproject.entity.GankEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ZHT on 2017/4/19.
 * Retrofit接口，定义请求方法
 */

public interface HttpApi {

    String BASE_URL = "http://gank.io/api/";

    /**
     * 获取分类数据
     * @param category 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param pageSize 请求个数： 数字，大于0
     * @param page     第几页：数字，大于0
     * @return
     */
    @GET("data/{category}/{pageSize}/{page}")
    Observable<HttpResult<List<GankEntity>>> getCategoryData(@Path("category") String category,
                                                             @Path("pageSize") int pageSize,
                                                             @Path("page") int page);
}
