package com.example.weakselfcontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.TimePicker;

public class Settings extends Activity{

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private TextView label;
	private Chronometer chronometer;
	private Button button1;
	private Button button2;
	private TimePicker timePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		Bundle bundle = this.getIntent().getExtras();
		final String className = bundle.getString("classname");
		final String packageName = bundle.getString("packagename");
		
		label = (TextView)findViewById(R.id.label);
		label.setText("当前应用名: "+className);
		
		chronometer = (Chronometer)findViewById(R.id.chronometer1);
		chronometer.setFormat("计时： %s");
		chronometer.start();
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(button1.getText().equals("Start")){
					chronometer.start();
					button1.setText("Stop");
				}
				else {
					chronometer.stop();
					button1.setText("Start");
				}
			}
		});
		
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Settings.this, MainActivity.class);
				startActivity(intent);
				Intent startService = new Intent(Settings.this, LockService.class);
				startService.setAction("com.example.weakselfcontrol.LockService");
				startService.putExtra("classname", className);
				startService.putExtra("packagename", packageName);
				Settings.this.startService(startService);
				finish();
			}
		});
		
		timePicker = (TimePicker)findViewById(R.id.timepicker);
		timePicker.setIs24HourView(true);
		
//		Intent intent = getIntent();
//		String i = intent.getStringExtra("factor1");
//		result.setText("i");
	}

}
