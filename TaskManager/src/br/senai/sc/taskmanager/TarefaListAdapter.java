package br.senai.sc.taskmanager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TarefaListAdapter extends BaseAdapter {

	private List<Tarefa> lista;
	private Context context;
	
	public TarefaListAdapter(Context context, List<Tarefa> tarefas) {
		this.context = context;
		this.lista = tarefas;
	}

	@Override
	public int getCount() {
		return this.lista.size();
	}

	@Override
	public Tarefa getItem(int position) {
		return this.lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).id;
	}

	@SuppressLint({ "ViewHolder", "InflateParams", "SimpleDateFormat" }) 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tarefa tarefa = (Tarefa) getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.linha_lista_tarefa, null);
		
		TextView titulo = (TextView) view.findViewById(R.id.titulo);
		titulo.setText(tarefa.titulo);
		
		TextView descricao = (TextView) view.findViewById(R.id.descricao);
		descricao.setText(tarefa.descricao);
		
		TextView lembrete = (TextView) view.findViewById(R.id.lembrate);
		lembrete.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(tarefa.lembrete)));
		
		return view;
	}
}