package com.example.appalarmmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.i("onCreate", "onCreate");

    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        agendarPara10Segundos();
    }

	private void agendarPara10Segundos() {
		Intent intencao = new Intent("ALARME_TESTE");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intencao, 0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, 10);
		long agendamentoEmMilis = calendar.getTimeInMillis();
		
		int intervalo = 20 * 1000;
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, agendamentoEmMilis, intervalo, pendingIntent);
	}
	
	@Override
	protected void onDestroy() {
		Log.i("onDestroy", "onDestroy");

		super.onDestroy();
		
		Intent intencao = new Intent("ALARME_TESTE");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intencao, 0);
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
	}
}
