package com.example.appupgrade.upgrade.internal;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.example.appupgrade.event.UpgradeActivityFinishEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * 发现有版本可更新时的弹出框
 */
public class FoundVersionInfoDialog {

    private final Activity activity;
    private final VersionInfo version;
    private final VersionInfoDialogListener listener;
    private final boolean isAutoUpgrade;

    private long extiTime = 0;

    public FoundVersionInfoDialog(Activity activity, VersionInfo version, boolean isAutoUpgrade, VersionInfoDialogListener listener) {
        this.activity = activity;
        this.version = version;
        this.listener = listener;
        this.isAutoUpgrade = isAutoUpgrade;
    }

    public void show() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        MaterialDialog dialog;
        if (isAutoUpgrade) {
            //非强制更新
            if(!(version.isMustUpgrade()==1))
            {
                dialog = builder.title("检测到新版本：" + version.getVersion())
                        .theme(Theme.LIGHT)
                        .titleGravity(GravityEnum.CENTER)
                        .content(!TextUtils.isEmpty(version.getVersionDesc()) ? version.getVersionDesc() : "")
                        .positiveText("立即更新")
                        .neutralText("稍后提醒")
                        .buttonsGravity(GravityEnum.CENTER)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                if (listener != null) {
                                    listener.doUpdate(activity);
                                }
                            }
                        }).onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                if (listener != null) {
                                    listener.doRemindMeLater();
                                }
                            }
                        })
                        .build();
            }
            //强制更新
            else
            {
                dialog = builder.title("检测到新版本：" + version.getVersion())
                        .theme(Theme.LIGHT)
                        .titleGravity(GravityEnum.CENTER)
                        .content(!TextUtils.isEmpty(version.getVersionDesc()) ? "本次为强制更新，若点击返回会导致自动退出app\n"+version.getVersionDesc() : "")
                        .positiveText("立即更新")
                        .buttonsGravity(GravityEnum.CENTER)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                if (listener != null) {
                                    listener.doUpdate(activity);
                                }
                            }
                        }).build();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);

            }

        } else {
            dialog = builder.title("检测到新版本：" + version.getVersion())
                    .theme(Theme.LIGHT)
                    .titleGravity(GravityEnum.CENTER)
                    .content(!TextUtils.isEmpty(version.getVersionDesc()) ? version.getVersionDesc() : "")
                    .positiveText("立即更新")
                    .negativeText("稍后提醒")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            if (listener != null) {
                                listener.doUpdate(activity);
                            }
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            if (listener != null) {
                                listener.doRemindMeLater();
                            }
                        }
                    }).build();
        }

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                EventBus.getDefault().post(new UpgradeActivityFinishEvent());
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    //EventBus.getDefault().post(new UpgradeActivityFinishEvent());
                    if(version.isMustUpgrade()==1)
                    {
                     android.os.Process.killProcess(android.os.Process.myPid());   //获取PID
                     System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
                    }
                    else
                    {
                        dialog.dismiss();
                    }
                }
                return false;
               // return true;
            }
        });
        dialog.show();
//        if(version.isMustUpgrade())
//        {
//            dialog.setCanceledOnTouchOutside(false);//可选
//            dialog.setCancelable(false);//可选
//        }
//        else
//        {
//            dialog.setCanceledOnTouchOutside(true);//可选
//            dialog.setCancelable(true);//可选
//        }
    }
}
