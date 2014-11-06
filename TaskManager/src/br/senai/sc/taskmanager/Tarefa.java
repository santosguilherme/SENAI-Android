package br.senai.sc.taskmanager;

import java.io.Serializable;

import android.provider.BaseColumns;

public class Tarefa implements Serializable{

	private static final long serialVersionUID = 1336517367166672438L;

	public static String[] colunas = new String[]{Tarefas._ID, Tarefas.TITULO, Tarefas.DESCRICAO, Tarefas.LEMBRETE};
	
	public static final String KEY = "tarefa_notification";
	
	public Tarefa(long id, String titulo, String descricao, long lembrete) {
		this(titulo, descricao, lembrete);
		this.id = id;
	}
	
	public Tarefa(String titulo, String descricao, long lembrete) {
		this();
		this.titulo = titulo;
		this.descricao = descricao;
		this.lembrete = lembrete;
	}

	public Tarefa() {
	}

	public long id;
	public String titulo;
	public String descricao;
	public long lembrete;
	
	public static final class Tarefas implements BaseColumns{
		
		private Tarefas(){}
		
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
		public static final String TITULO = "titulo";
		public static final String DESCRICAO = "descricao";
		public static final String LEMBRETE = "lembrete";
	}
}