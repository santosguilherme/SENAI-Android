package br.senai.sc.app;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import br.senai.sc.app.Carro.Carros;

public class CadastrarCarro extends ListActivity{

	protected static final int INSERIR_EDITAR = 1;
	protected static final int BUSCAR = 2;
	
	public static RepositorioCarro repositorio;
	
	private List<Carro> carros;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		repositorio = new RepositorioCarro(this);
		atualizarLista();
	}

	private void atualizarLista() {
		this.carros = repositorio.listarCarros();
		setListAdapter(new CarroListAdapter(this, this.carros));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERIR_EDITAR, 0, "Inserir Novo");
		menu.add(0, BUSCAR, 0, "Buscar");
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
			case INSERIR_EDITAR: {
				startActivityForResult(new Intent(this, EditarCarro.class), INSERIR_EDITAR);
				break;
			}
			case BUSCAR: {
				startActivityForResult(new Intent(this, BuscarCarro.class), BUSCAR);
				break;
			}
		}
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		editarCarro(position);
	}

	private void editarCarro(int position) {
		Carro carro = this.carros.get(position);
		Intent intent = new Intent(this, EditarCarro.class);
		intent.putExtra(Carros._ID, carro.id);
		startActivityForResult(intent, INSERIR_EDITAR);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			atualizarLista();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		repositorio.fechar();
	}
}
