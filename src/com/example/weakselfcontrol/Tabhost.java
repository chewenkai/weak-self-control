package com.example.weakselfcontrol;


import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Tabhost extends ActivityGroup {

	private TabHost myTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host);
		
		myTabHost = (TabHost)findViewById(R.id.myTabhost);
		myTabHost.setup(this.getLocalActivityManager());
		
		Intent firstIntent = new Intent();
		firstIntent.setClass(Tabhost.this,FilterApplication.class);
		
		TabSpec tabSpec1 = myTabHost.newTabSpec("app");
		tabSpec1 = tabSpec1.setIndicator("应用选定");
		tabSpec1 = tabSpec1.setContent(firstIntent);
		myTabHost.addTab(tabSpec1);
		
		Intent secondIntent = new Intent();
		secondIntent.setClass(Tabhost.this,Help.class);
		
		TabSpec tabSpec2 = myTabHost.newTabSpec("help");
		tabSpec2 = tabSpec2.setIndicator("帮助");
		tabSpec2 = tabSpec2.setContent(secondIntent);
		myTabHost.addTab(tabSpec2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
