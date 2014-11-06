package br.senai.sc.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmNotificacaoReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Tarefa tarefa = (Tarefa) intent.getExtras().get(Tarefa.KEY);
		agendarNotificacao(context, tarefa);
	}
	
	public void agendarNotificacao(Context ctx, final Tarefa tarefa){
		String tickerText = "Tarefa agendada para agora!";
    	String mensagem = tarefa.descricao.concat(" \n(Clique para deletá-la!)");

		Intent intent = new Intent("REMOVER_TAREFA");
		intent.putExtra(Tarefa.KEY, tarefa);
    	
    	notificar(ctx, R.drawable.ic_launcher, tickerText, tarefa.titulo, mensagem, intent, System.currentTimeMillis());
	}
	
	private void notificar(Context ctx, int imagem, String ticker, String titulo, String msg, Intent intent, long quando){
		NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);

		Notification notification = new Notification.Builder(ctx)
		.setTicker(ticker)
		.setContentTitle(titulo)
		.setSmallIcon(imagem)
		.setContentText(msg)
		.setContentIntent(pendingIntent)
		.setWhen(quando)
		.setAutoCancel(true)
		.build();

		notificationManager.notify(R.string.app_name, notification);
	}
}