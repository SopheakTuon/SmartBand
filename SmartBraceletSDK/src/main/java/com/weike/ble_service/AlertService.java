package com.weike.ble_service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.weike.manager.CommandManager;

/**
 * 提醒服务
 */
@SuppressLint("NewApi")
public class AlertService extends NotificationListenerService {

    private CommandManager mManager;
    private String messageContent;

    @Override
    public void onCreate() {
        super.onCreate();
        mManager=CommandManager.getInstance(this);
    }

    //当系统收到新的通知后出发回调
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        //获取应用包名
        String packageName = sbn.getPackageName();
        //获取notification对象
        Notification notification = sbn.getNotification();
        //获取消息内容
        CharSequence tickerText = notification.tickerText;
        if (tickerText.length() > 11) {
            messageContent = tickerText.subSequence(0, 11).toString() + "...";
        }else{
            messageContent = tickerText.toString();
        }
        Log.e("lq369","packageName:"+packageName + "    tickerText:" + tickerText.toString());
        //过滤包名
        switch (sbn.getPackageName()){
//            //qq
//            case "com.tencent.mobileqq":
//                if (AppApplication.isQQWarnOn) {
//                    mManager.smartWarnInfo(BleCommand.MessageId.QQ, 2, messageContent);
//                }
//                break;
//            // 微信
//            case "com.tencent.mm":
//                if (AppApplication.isWeiXinWarnOn){
//                    mManager.smartWarnInfo(BleCommand.MessageId.wechat, 2, messageContent);
//                }
//                break;
//            //微博
//            case "com.sina.weibo":
//                if (AppApplication.isWeiBoWarnOn){
//                    mManager.smartWarnInfo(BleCommand.MessageId.Weibo, 2, messageContent);
//                }
//                break;
//            //facebook
//            case "com.facebook.katana":
//                mManager.smartWarnInfo(BleCommand.MessageId.Facebook, 2, messageContent);
//                break;
//            //Twitter
//            case "com.twitter.android":
//                mManager.smartWarnInfo(BleCommand.MessageId.Twitter, 2, messageContent);
//                break;
//            //Whats
//            case "com.whatsapp":
//                if (AppApplication.isWhatsAppOn) {
//                    mManager.smartWarnInfo(BleCommand.MessageId.WhatsApp, 2, messageContent);
//                    break;
//                }
        }
    }

    //当系统通知被删掉后出发回调
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }

}
