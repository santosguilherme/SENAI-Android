package br.senai.sc.taskmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
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
		( (TimePicker) findViewById(R.id.input_hora)).setIs24HourView(true);
	}

	public void agendarTarefa(View view) {
		Tarefa tarefa = recuperarTarefaDaView();
		tarefa = ListaTarefas.repositorio.salvar(tarefa);

		agendarNotificacao(tarefa);
		startActivityForResult(new Intent(this, ListaTarefas.class), RESULT_OK);
		Toast.makeText(getApplicationContext(), "Tarefa agendada", Toast.LENGTH_SHORT).show();
	}

	private Tarefa recuperarTarefaDaView() {
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

		Calendar calendar = Calendar.getInstance();
		calendar.set(ano, mes, dia, horas, minutos, 0);
		
		tarefa.lembrete = calendar.getTimeInMillis();
		
		return tarefa;
	}

	
	private void agendarNotificacao(final Tarefa tarefa){
		Intent intencao = new Intent("ALARME_NOTIFICACAO_TAREFA");
		intencao.putExtra(Tarefa.KEY, tarefa);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intencao, 0);
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, tarefa.lembrete, pendingIntent);
	}
}