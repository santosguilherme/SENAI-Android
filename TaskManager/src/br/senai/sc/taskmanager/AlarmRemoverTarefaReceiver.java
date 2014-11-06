package br.senai.sc.taskmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmRemoverTarefaReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Tarefa tarefa = (Tarefa) intent.getExtras().get(Tarefa.KEY);
		new RepositorioTarefa(context).deletar(tarefa.id);
		Toast.makeText(context, "Tarefa ".concat(tarefa.titulo).concat(" removida!"), Toast.LENGTH_SHORT).show();
	}
}