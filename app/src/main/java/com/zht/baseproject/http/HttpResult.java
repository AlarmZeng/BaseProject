package com.zht.baseproject.http;

/**
 * Created by ZHT on 2017/4/19.
 * 响应数据的"基类"，通过指定泛型获取想要的数据类型
 */

public class HttpResult<T> {

    private int count;

    private boolean error;

    private T results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
