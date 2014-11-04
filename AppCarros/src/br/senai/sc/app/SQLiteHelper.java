package br.senai.sc.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper{

	private String[] scriptSQLCreate;
	private String scriptSQLDelete;
	
	SQLiteHelper(Context context, String nomeDoBanco, 
			int versaoBanco, String[] scriptSQLCreate, String scriptSQLDelete) {
		super(context, nomeDoBanco, null, versaoBanco);
		
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase) {
		int qtdScripts = this.scriptSQLCreate.length;
		for (int indice = 0; indice < qtdScripts; indice++) {
			dataBase.execSQL(this.scriptSQLCreate[indice]); 
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase dataBase, int versaoAntiga, int novaVersao) {
		dataBase.execSQL(this.scriptSQLDelete);
		onCreate(dataBase);
	}
}
