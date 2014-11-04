package br.senai.sc.app;

import android.provider.BaseColumns;

public class Carro {

	public static String[] colunas = new String[]{Carros._ID, Carros.NOME, Carros.PLACA, Carros.ANO};
	
	public long id;
	public String nome;
	public String placa;
	public int ano;
	
	public static final class Carros implements BaseColumns{
		
		private Carros(){}
		
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
		public static final String NOME = "nome";
		public static final String ANO = "ano";
		public static final String PLACA = "placa";
	}
	
	@Override
	public String toString() {
		return "Nome: ".concat(nome).concat(", Placa: ".concat(placa).concat(", Ano: ").concat(String.valueOf(ano)));
	}
}
