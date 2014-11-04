package com.example.appservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Contador extends Service implements Runnable {

	public static final String INTENT_NAME = "CONTADOR_SERVICE";
	private static final double MAX = 100000000000000D;
	protected int count;
	private boolean ativo;

	@Override
	public void onCreate() {
		this.ativo = true;
		new Thread(this).start();
	}
	
	@Override
	public void onDestroy() {
		this.ativo = false;
		Log.i("Finalizando", getClass().getName().concat("..."));
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void run() {
		while (this.ativo && this.count < MAX) {
			String contadorString = String.valueOf(this.count);
			Log.i("exemplo", getClass().getName().concat("em execução...".concat(contadorString)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Log.e("erro no sleep da thread", e.getMessage());
			}
			this.count++;
		}
		super.stopSelf();
	}
}
