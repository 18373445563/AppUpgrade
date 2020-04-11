package com.example.appupgrade.services;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import org.json.JSONObject;

public final class ApiCommonRequest {

    /**
     * 检查版本更新
     */
    public static void upgrade(Context context, String versionCode, HttpManager.HttpServiceRequestCallBack callBack) {
        //传参版本号，appName，appKey
        JSONObject params = new JSONObject();
        try {
         ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
//                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String value = appInfo.metaData.getString("APPKEY");
            params.put("APPKEY",value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpManager.getInstance().request(
                context,
                "/hpmt/upGradeApp.mvc",
                HttpManager.HttpMethod.HTTP_METHOD_POST,
                params,
                callBack);
    }
}
