package com.zht.baseproject.utils;

import java.util.List;

/**
 * Created by ZHT on 2017/4/19.
 * 权限回调接口
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
