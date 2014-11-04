package br.senai.sc.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import br.senai.sc.app.Carro.Carros;

public class RepositorioCarro {

	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS carro";
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
		"create table carro (_id integer primary key autoincrement," +
		"nome text not null, placa text not null, ano integer not null);",
		"insert into carro(nome, placa, ano) values ('Fusca A', 'ABC-1234', 1958);",
		"insert into carro(nome, placa, ano) values ('Brasilia B', 'DEF-5678', 1968);",
		"insert into carro(nome, placa, ano) values ('Chevete C', 'GHI-9999', 1978);"};
	
	private static final String NOME_BANCO = "livro_android";
	
	private static final int VERSAO_BANCO = 5;
	
	public static final String TABELA_CARRO = "carro";
	
	private SQLiteHelper dbHelper;
	
	protected SQLiteDatabase db;
	
	public RepositorioCarro(Context context){
		this.dbHelper = new SQLiteHelper(context, 
										NOME_BANCO, 
										VERSAO_BANCO, 
										SCRIPT_DATABASE_CREATE, 
										SCRIPT_DATABASE_DELETE);
	
		this.db = this.dbHelper.getWritableDatabase();
	}
	
	public long salvar(Carro carro){
		if(carro.id == 0){
			return inserir(carro);
		}
		atualizar(carro);
		return carro.id;
	}

	private long inserir(Carro carro){
		return inserir(createContentValues(carro));
	}

	private long inserir(ContentValues values) {
		return this.db.insert(TABELA_CARRO, "", values);
	}
	
	private ContentValues createContentValues(Carro carro) {
		ContentValues values = new ContentValues();
		values.put(Carros.NOME, carro.nome);
		values.put(Carros.PLACA, carro.placa);
		values.put(Carros.ANO, carro.ano);
		return values;
	}

	private int atualizar(Carro carro) {
		ContentValues values = createContentValues(carro);
		String _id = String.valueOf(carro.id);
		
		String where = Carros._ID.concat("=?");
		String[] whereArgs = new String[]{_id};
		
		return atualizar(values, where, whereArgs);
	}
	
	private int atualizar(ContentValues values, String where, String[] whereArgs) {
		return this.db.update(TABELA_CARRO, values, where, whereArgs);
	}

	public int deletar(long id){
		String where = Carros._ID.concat("=?");
		String _id = String.valueOf(id);
		String[] whereArgs = new String[]{_id};
		
		return deletar(where, whereArgs);
	}
	
	private int deletar(String where, String[] whereArgs) {
		return this.db.delete(TABELA_CARRO, where, whereArgs);
	}

	
	public Carro buscarCarro(long id){
		Cursor cursor = db.query(true, TABELA_CARRO, Carro.colunas, Carros._ID + "=" + id, null, null, null, null, null);
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			
			Carro carro = new Carro();
			carro.id = cursor.getLong(0);
			carro.nome = cursor.getString(1);
			carro.placa = cursor.getString(2);
			carro.ano = cursor.getInt(3);
			
			return carro;
		}
		return null;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(TABELA_CARRO, Carro.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public List<Carro> listarCarros() {
		Cursor cursor = getCursor();

		if(!cursor.moveToFirst()){
			return Collections.emptyList();
		}
		
		List<Carro> carros = new ArrayList<Carro>();
		int idxId = cursor.getColumnIndex(Carros._ID);
		int idxNome = cursor.getColumnIndex(Carros.NOME);
		int idxPlaca = cursor.getColumnIndex(Carros.PLACA);
		int idxAno = cursor.getColumnIndex(Carros.ANO);

		do {
			Carro carro = new Carro();
			carro.id = cursor.getLong(idxId);
			carro.nome = cursor.getString(idxNome);
			carro.placa = cursor.getString(idxPlaca);
			carro.ano = cursor.getInt(idxAno);

			carros.add(carro);
		} while (cursor.moveToNext());
		
		return carros;
	}
	
	public void fechar(){
		if(this.db != null){
			this.db.close();
		}
		if(this.dbHelper != null){
			dbHelper.close();
		}
	}
}