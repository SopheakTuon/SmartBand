package com.weike.ble_service;

import java.util.List;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.weike.manager.CommandManager;

/**
 * 提醒服务
 */
public class AlertAccessibilityService extends AccessibilityService {
    private CommandManager mManager;
    private String warnText;

    public AlertAccessibilityService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType=event.getEventType();
//        switch (eventType){
            //通知栏状态改变
//            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
//                List<CharSequence> texts = event.getText();
//                if (!texts.isEmpty()){
//                    if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
//                        mManager= CommandManager.getInstance(this);
//                        Notification notification = (Notification) event.getParcelableData();
//                        PendingIntent pendingIntent = notification.contentIntent;
//                        for (CharSequence text : texts) {
//                            if (text.length() > 11) {
//                                warnText = text.subSequence(0, 11).toString() + "...";
//                            }else{
//                                warnText = text.toString();
//                            }
//                        }
//                        Log.d("lq12","通知warnText:"+warnText);
//                        switch (pendingIntent.getCreatorPackage()){
//                            //微信
//                            case "com.tencent.mm":
//                                if (AppApplication.isWeiXinWarnOn){
//                                    mManager.smartWarnInfo(BleCommand.MessageId.wechat, 2, warnText);
//                                }
//                                break;
//                            //qq
//                            case "com.tencent.mobileqq":
//                                if (AppApplication.isQQWarnOn) {
//                                    mManager.smartWarnInfo(BleCommand.MessageId.QQ, 2, warnText);
//                                }
//                                break;
//                            //微博
//                            case "com.sina.weibo":
//                                if (AppApplication.isWeiBoWarnOn){
//                                    mManager.smartWarnInfo(BleCommand.MessageId.Weibo, 2, warnText);
//                                }
//                                break;
//                            //facebook
//                            case "com.facebook.katana":
//                                if (AppApplication.isFacebookOn){
//                                    mManager.smartWarnInfo(BleCommand.MessageId.Facebook, 2, warnText);
//                                }
//                                break;
//                            //Twitter
//                            case "com.twitter.android":
//                                if (AppApplication.isTwitterOn){
//                                    mManager.smartWarnInfo(BleCommand.MessageId.Twitter, 2, warnText);
//                                }
//                                break;
//                            //Whats
//                            case "com.whatsapp":
//                                if (AppApplication.isWhatsAppOn){
//                                    mManager.smartWarnInfo(BleCommand.MessageId.WhatsApp, 2, warnText);
//                                }
//                                break;
//                        }
//                    }
//                }
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 服务连接的时候调用
     */
    @SuppressLint("NewApi")
	@Override
    protected void onServiceConnected() {
        //设置服务信息
        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        serviceInfo.packageNames=new String[]{"com.tencent.mm","com.tencent.mobileqq","com.sina.weibo","com.facebook.katana","com.twitter.android","com.whatsapp"};
        setServiceInfo(serviceInfo);
        super.onServiceConnected();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
