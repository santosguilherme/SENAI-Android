package br.senai.sc.app;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CarroListAdapter extends BaseAdapter{

	private List<Carro> lista;
	private Context context;

	public CarroListAdapter(Context context, List<Carro> lista) {
		this.context = context;
		this.lista = lista;
	}

	@Override
	public int getCount() {
		return this.lista.size();
	}

	@Override
	public Object getItem(int index) {
		return this.lista.get(index);
	}

	@Override
	public long getItemId(int index) {
		return ((Carro) getItem(index)).id;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" }) 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Carro carro = (Carro) getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.carro_linha_tabela, null);
		
		TextView nome = (TextView) view.findViewById(R.id.nome);
		nome.setText(carro.nome);
		
		TextView placa = (TextView) view.findViewById(R.id.placa);
		placa.setText(carro.placa);
		
		TextView ano = (TextView) view.findViewById(R.id.ano);
		ano.setText(String.valueOf(carro.ano));
		
		return view;
	}

}
