package com.example.appnotificacao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MeuReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
	}
}
