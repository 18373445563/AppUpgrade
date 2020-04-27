package com.example.appupgrade.upgrade.internal;

/**
 * 版本信息对象
 *
 * @author panzhilong
 */
public class VersionInfo {

    /**
     * 例如100。安卓传versionCode，iOS传Build
     */
    private int versionCode;

    /**
     * 例如2.0.1。安卓传versionName，iOS传Version
     */
    private String versionName;

    /**
     * 下载链接
     */
    private String downloadUrl;

    /**
     * 包文件checksum
     */
    private String md5;

    /**
     * 是否强制升级
     */
    private int isMustUpgrade = 0;
    /**
     * versionDesc : 版本说明。服务端注意，换行使用\n
     */

    private String versionDesc;

    public VersionInfo() {
//        this.versionCode = 100;
//        this.version = "1.0.2";
//        this.versionDesc = "1";
//        this.downloadUrl = "http://120.79.59.8:3388/HPProg/pc_file/download?realName=1586229848722.apk";
//        this.md5 = "ererete";
//        this.isMustUpgrade = true;
    }

    public VersionInfo(int versionCode, String versionName,
                       String versionDesc, String downloadUrl, String md5, int isMustUpgrade) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.versionDesc = versionDesc;
        this.downloadUrl = downloadUrl;
        this.md5 = md5;
        this.isMustUpgrade = isMustUpgrade;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(":: VERSION -> ");
        builder.append("VersionCode:").append(versionCode).append(", ");
        builder.append("VersionName:").append(versionName).append(", ");
        builder.append("versionDesc:").append(versionDesc).append(", ");
        builder.append("downloadUrl:").append(downloadUrl).append(", ");
        builder.append("md5:").append(md5).append(", ");
        builder.append("IsMustUpgrade:").append(isMustUpgrade);
        return builder.toString();
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersion() {
        return versionName;
    }

    public void setVersion(String version) {
        this.versionName = version;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int isMustUpgrade() {
        return isMustUpgrade;
    }

    public void setMustUpgrade(int mustUpgrade) {
        isMustUpgrade = mustUpgrade;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }
}
