package br.senai.sc.taskmanager;

import java.util.Date;

import br.senai.sc.taskmanager.Tarefa.Tarefas;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class CadastroTarefa extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_tarefa);
	}

	public void agendarTarefa(View view) {
		Tarefa tarefa = new Tarefa();

		EditText titulo = (EditText) findViewById(R.id.input_titulo);
		tarefa.titulo = titulo.getText().toString();

		EditText descricao = (EditText) findViewById(R.id.input_descricao);
		tarefa.descricao = descricao.getText().toString();

		DatePicker data = (DatePicker) findViewById(R.id.input_data);
		int dia = data.getDayOfMonth();
		int mes = data.getMonth();
		int ano = data.getYear();

		TimePicker horaMinuto = (TimePicker) findViewById(R.id.input_hora);
		int horas = horaMinuto.getCurrentHour();
		int minutos = horaMinuto.getCurrentMinute();

		tarefa.lembrete = new Date(ano, mes, dia, horas, minutos).getTime();

		tarefa = ListaTarefas.repositorio.salvar(tarefa);

		agendarNotificacao(tarefa.id, tarefa.lembrete, tarefa.titulo, tarefa.descricao);
		
		startActivityForResult(new Intent(this, ListaTarefas.class), RESULT_OK);

		Toast.makeText(getApplicationContext(), "Tarefa agendada", Toast.LENGTH_SHORT).show();

	}

	public void agendarNotificacao(long tarefaId, long when, String tituloNotificacao, String descricaoNotificacao){
		String tickerText = "Tarefa agendada para agora!";
    	String mensagem = descricaoNotificacao.concat(" \n(Clique para deletá-la!)");
    	Intent intent = new Intent("NOME_DA_ACAO");
    	intent.putExtra(Tarefas._ID, tarefaId);
    	
    	notificar(this, R.drawable.ic_launcher, tickerText, tituloNotificacao, mensagem, intent, when);
	}
	
	public void notificar(Context ctx, int imagem, String ticker, String titulo, String msg, Intent intent, long quando){
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, activity), 0);
		//PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, new Intent("NOME_DA_ACAO"), 0);
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
