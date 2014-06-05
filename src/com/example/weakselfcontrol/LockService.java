package com.example.weakselfcontrol;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LockService extends Service{

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String classname = intent.getStringExtra("classname");
		String packagename = intent.getStringExtra("packagename");
		TestPackageName = packagename;
		TestClassName = classname;
		startTimer();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		stopForeground(true);
		mTimer.cancel();
		mTimer.purge();
		mTimer = null;
		super.onDestroy();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		String classname = intent.getStringExtra("classname");
		String packagename = intent.getStringExtra("packagename");
		Toast.makeText(LockService.this, "该应用禁止使用  "+classname+" : "+packagename, Toast.LENGTH_SHORT).show();
		//}
		Log.d(getClass().getName(), "Service starting..");
	}

	//采取每秒检查一次Task的方式
	private Timer mTimer;
	private void startTimer(){
		if(mTimer == null){
			mTimer = new Timer();
			LockTask lockTask = new LockTask(this);
			mTimer.schedule(lockTask,0L, 500L);
		}
	}

	//从当前任务中取出顶端activity，判断是否为被锁定应用。若是，则杀死该进程
	public class LockTask extends TimerTask{
		public static final String TAG = "LockTask";
		private Context mContext;

		private ActivityManager mActivityManager;

		public LockTask(Context context){
			mContext = context;
			mActivityManager = (ActivityManager) context.getSystemService("activity");
		}
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ComponentName topActivity = mActivityManager.getRunningTasks(1).get(0).topActivity;
			String packageName = topActivity.getPackageName();
			String className = topActivity.getClassName();
			Log.v(TAG, "packageName "+packageName);
			Log.v(TAG, "className "+className);
			System.out.println("className : "+TestClassName+"    packageName : "+TestPackageName);
			if(className.equals("com.example.weakselfcontrol.Settings")){
				System.out.println("Stop!!!");
				onDestroy();
			}
			if(TestPackageName.equals(packageName)){
				mActivityManager.killBackgroundProcesses(packageName);
				mActivityManager.restartPackage(packageName);
				Intent intent = new Intent();
				intent.setClassName("com.example.weakselfcontrol", "com.example.weakselfcontrol.Warning");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(intent);
			}
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		startForeground(FOREGROUND_ID, new Notification());
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	private static final int FOREGROUND_ID = 0;
	private String TestPackageName = new String();
	private String TestClassName = new String();
	//private String TestPackageName = null;
}
