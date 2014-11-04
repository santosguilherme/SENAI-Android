package com.example.appnotificacao;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void dispararNotificacao(View view){
    	//startActivity(new Intent(this, SegundaActivity.class));
    	String tickerText = "Você recebeu uma mensagem";
    	String titulo = "Título";
    	String mensagem = "Exemplo de notificação";

    	notificar(this, R.drawable.ic_launcher, tickerText, titulo, mensagem, SegundaActivity.class);
    }
    
    void notificar(Context ctx, int imagem, String ticker, String titulo, String msg, Class<? extends Activity> activity){
    	NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	//PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, activity), 0);
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, new Intent("NOME_DA_ACAO"), 0);
    	
    	Notification notification = new Notification.Builder(ctx)
    												.setTicker(ticker)
    												.setContentTitle(titulo)
    												.setSmallIcon(imagem)
    												.setContentText(msg)
    												.setContentIntent(pendingIntent)
    												.setWhen(System.currentTimeMillis())
    												//.setAutoCancel(true)
    												.build();
    	
    	notificationManager.notify(R.string.app_name, notification);
    	
    }

}
