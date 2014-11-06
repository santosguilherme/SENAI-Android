package br.senai.sc.taskmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import br.senai.sc.taskmanager.Tarefa.Tarefas;

public class RepositorioTarefa {

	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS tarefa";
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
		"create table tarefa (_id integer primary key autoincrement," +
				"titulo text not null, descricao text not null, lembrete integer not null);",
				"insert into tarefa(titulo, descricao, lembrete) values ('Título 1', 'Descrição da tarefa 1', 1412049600000);",
				"insert into tarefa(titulo, descricao, lembrete) values ('Título 2', 'Descrição da tarefa 2', 1412049600000);"};
	
	private static final String NOME_BANCO = "exercicio_av_2";
	
	private static final int VERSAO_BANCO = 1;
	
	public static final String TABELA_TAREFA = "tarefa";
	
	private SQLiteHelper dbHelper;
	
	protected SQLiteDatabase db;
	
	public RepositorioTarefa(Context context) {
		this.dbHelper = new SQLiteHelper(context, 
				NOME_BANCO, 
				VERSAO_BANCO, 
				SCRIPT_DATABASE_CREATE, 
				SCRIPT_DATABASE_DELETE);

		this.db = this.dbHelper.getWritableDatabase();
	}

	public List<Tarefa> listarTarefas() {
		Cursor cursor = getCursor();

		if(!cursor.moveToFirst()){
			return Collections.emptyList();
		}
		
		List<Tarefa> carros = new ArrayList<>();
		int idxId = cursor.getColumnIndex(Tarefas._ID);
		int idxTitulo = cursor.getColumnIndex(Tarefas.TITULO);
		int idxDescricao = cursor.getColumnIndex(Tarefas.DESCRICAO);
		int idxLembrete = cursor.getColumnIndex(Tarefas.LEMBRETE);

		do {
			Tarefa tarefa = new Tarefa();
			tarefa.id = cursor.getLong(idxId);
			tarefa.titulo = cursor.getString(idxTitulo);
			tarefa.descricao = cursor.getString(idxDescricao);
			tarefa.lembrete = cursor.getLong(idxLembrete);

			carros.add(tarefa);
		} while (cursor.moveToNext());

		return carros;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(TABELA_TAREFA, Tarefa.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			return null;
		}
	}

	public Tarefa salvar(Tarefa tarefa) {
		tarefa.id = this.db.insert(TABELA_TAREFA, "", createContentValues(tarefa));
		return tarefa;
	}

	private ContentValues createContentValues(Tarefa tarefa) {
		ContentValues values = new ContentValues();
		values.put(Tarefas.TITULO, tarefa.titulo);
		values.put(Tarefas.DESCRICAO, tarefa.descricao);
		values.put(Tarefas.LEMBRETE, tarefa.lembrete);
		return values;
	}
	
	public boolean deletar(long id) {
		String where = Tarefas._ID.concat("=?");
		String _id = String.valueOf(id);
		String[] whereArgs = new String[]{_id};
		
		return deletar(where, whereArgs) == 1;
	}
	
	private int deletar(String where, String[] whereArgs) {
		return this.db.delete(TABELA_TAREFA, where, whereArgs);
	}

	public void fechar() {
		if(this.db != null){
			this.db.close();
		}
		if(this.dbHelper != null){
			dbHelper.close();
		}
	}
}