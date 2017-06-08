package weike.mainsdk;

import android.content.Context;

import com.weike.manager.CommandManager;

import weike.utils.AlertModel;

/**
 * 
 * @author wukejun
 * 
 * 注释：这个类调用的CommandManager里面的方法都会通过广播发送数据出来 广播发送指令是：command
 * 就是BroadcastData类里的 command 发送方式如下
 * 
 *   BroadcastData bData = new BroadcastData(BroadcastCommand.BLE_RECEIVE_DATA);
        if (bData.data != null){
            bData.data = null;
        }
        bData.data = data;
        final Intent intent = new Intent(
                BroadcastCommand.DATA_RECEIVED_FROM_ACTIVITY);
        intent.putExtra(BroadcastData.keyword, bData);
        try {
            //发送本地广播
            Log.d("lq","broadcastData");
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
 *
 */

/**
 * 协议类
 * Client为手环;  Host为Android客户端
 * 
 * @author wukejun
 *
 */
public class OperationMain {

	public CommandManager mManager;


	public void OperationMain(Context mContext){
		mManager = CommandManager.getInstance(mContext);
	}
	
	
	/**
	 * 查找手环
	 */
	public void findBand(){
		mManager.findBand();	
	}

	/**
	 * 一键测量
	 * 测试 心率 血压 血压 疲劳
	 * 
	 */
	public void initOnceMeasure(){
		mManager.oneKeyMeasure(1);
	}

	
	/**
	 * 血氧
	 * 0 关  1 开
	 */
	//单次测量
	public void BloodOxOnetimeMeasure(int open){
		mManager.realTimeAndOnceMeasure(0x11, open);
	}

	//实时测量  
	public void BloodOxOntimeMeasure(int open){
		mManager.realTimeAndOnceMeasure(0x12, open);
	}

	/*************************************************************/


	/**
	 * 血压   
	 * 0 关  1 开
	 */
	//单次测量
	public void BloodPresureOnceMeasure(int open){
		mManager.realTimeAndOnceMeasure(0x21, open);
	}


	//实时测量
	public void BloodPresureOntimeMeasure(int open){
		mManager.realTimeAndOnceMeasure(0x22, 0);
	}

	/**************************************************************/

	/**
	 * 心率
	 * 0 关  1开
	 * 
	 */
	//单次测量
	public void HeartRateOnceMesure(int open){
		mManager.realTimeAndOnceMeasure(0x09, open);
	}

	//实时测量
	public void HeartRateOnTimeMeasure(int open){
		mManager.realTimeAndOnceMeasure(0x0A, open);
	}

	/***************************************************************/


	/**
	 * 同步时间
	 */
	public void TimeSync(){
		mManager.setTimeSync();
	}
	
	
	/**
	 *
	 * 设置用户信息    Client -> Host
	 * age:年龄;  height：身高; weight:体重; systaltic：收缩压; diastolic舒张压
	 * 
	 */
	public void SetUser(int age,String height,String weight,String systaltic,String diastolic){
		mManager.setUserInfo(age, height, weight, systaltic, diastolic);
	}
	
	/**
	 * 下拉同步数据
	 * @param timeInMillis
	 *  Client -> Host
	 */
	public void getSyncData(long timeInMillis){
		mManager.setSyncData(timeInMillis);
	}
	
	
	/**
	 * 下拉同步睡眠数据数据  
	 * @param timeInMillis
	 * Client -> Host
	 */
	public void getSyncSleepData(long timeInMillis){
		mManager.setSyncSleepData(timeInMillis);
	}
	
	
	/**
	 * 设置闹钟
	 *  Host -> Client
	 */
	public void setClock(AlertModel alertModel){
//		mManager.setAlertClock(alertModel);
	}
	
	/**
	 * 清除数据
	 * Host -> Client
	 */
	public void Clear(){
		mManager.clearData();
	}
	
	/**
	 * 智能提醒
	 * MessageId:1、 来电话; 2 、挂电话; 3、Messages; 4、Mail; 5、Calendar; 6、FaceTime
	 * 7、QQ  8、Skype 9、wechat; 10、WhatsApp; 11、Gmail; 12、Hangout; 13、Inbox   
	 * 14、Line 15、Twitter 16、Facebook 
	 * 
	 * type:0:关 1：开 2:来消息通知
	 * Host -> Client
	 * 
	 */
	public void smartWarn(int MessageId,int type){
		mManager.smartWarn(MessageId, type);
	}
	
	/**
	 * 智能提醒 带消息内容
	 * @param MessageId 1、 来电话; 2 、挂电话; 3、Messages; 4、Mail; 5、Calendar; 6、FaceTime
	 * 7、QQ  8、Skype 9、wechat; 10、WhatsApp; 11、Gmail; 12、Hangout; 13、Inbox   
	 * 14、Line 15、Twitter 16、Facebook 
	 * @param type   0:关 1：开 2:来消息通知
	 * @param data   自定义消息内容
	 * Host -> Client
	 */
	public void smartWarnInfo(int MessageId, int type,String data){
		mManager.smartWarnInfo(MessageId, type, data);
	}
	
	/**
	 * 抬手亮屏
	 * 0：关 1：开
	 *  Host -> Client
	 */
	public void upHandLightScreen(int control){
		mManager.upHandLightScreen(control);
	}
	


	
	/**
	 * 整点测量
	 * 0：关 1：开
	 * Host -> Client
	 */
	public void onTimeMeasure(int control){
		mManager.onTimeMeasure(control);
	}
	
	
	/**
	 * 勿扰模式
	 * Host -> Client
	 */
	public void disturbanceModel(){
		mManager.disturbanceModel();
	}
	
	/**
	 * 久坐提醒
	 * Host -> Client
	 */
	public void sedentaryWarn(){
		mManager.sedentaryWarn();
	}
	
	
	/**
	 * 查看电量
	 * Client -> Host
	 */
	public void getBattery(){
		mManager.getBattery();
	}
	
	
	/**
	 * 查看版本信息
	 * Client -> Host 
	 */
	public void versionInfo(){
		mManager.versionInfo();
	}
	
	/**
	 * 中英文切换
	 * control:00:中文  01:英文
	 * Host-> Client 
	 */
	public void chineseEnglishSwitch(int control){
		mManager.chineseEnglishSwitch(control);
	}
	
	
	/**
	 * 同步时间
	 * Host -> Client
	 */
	public void setTimeSync(){
		mManager.setTimeSync();
	}
	

}
