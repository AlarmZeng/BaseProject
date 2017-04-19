package com.zht.baseproject.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZHT on 2017/4/19.
 */

public class GankEntity implements Serializable {


    /**
     * _id : 58ed9624421aa9544825f85b
     * createdAt : 2017-04-12T10:51:16.759Z
     * desc : React-native 实现的 Android BottomSheetBehavior 效果
     * images : ["http://img.gank.io/475bb89a-a9c1-4464-adea-8ce583f7a14a"]
     * publishedAt : 2017-04-12T12:12:18.213Z
     * source : chrome
     * type : Android
     * url : https://github.com/cesardeazevedo/react-native-bottom-sheet-behavior
     * used : true
     * who : 代码家
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
