package com.example.appnotificacao;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

public class SegundaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segunda);
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(R.string.app_name);
	}
}
