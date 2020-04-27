package com.example.appupgrade.services;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.json.JSONObject;

import java.util.Locale;

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
            String value = appInfo.metaData.getString("appName");
            //语言
            String locale = Locale.getDefault().getLanguage();
            String lang;
            //版本号
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if(locale.equals("en"))
            {
                lang="2";
            }
            else
            {
                lang="1";
            }
            params.put("appName",value);
            params.put("language",lang);
            params.put("versionCode",packageInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpManager.getInstance().request(
                context,
                "/file/getVersion",
                HttpManager.HttpMethod.HTTP_METHOD_POST,
                params,
                callBack);
    }
}
