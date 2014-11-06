package br.senai.sc.taskmanager;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;

public class ListaTarefas extends ListActivity {

	private static final int INSERIR_EDITAR = 1;
	public static RepositorioTarefa repositorio;
	private List<Tarefa> tarefas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		repositorio = new RepositorioTarefa(this);
		atualizarTarefas();
		registerForContextMenu(getListView());
	}

	@Override
	protected void onStart() {
		super.onStart();
		atualizarTarefas();
	}
	
	private void atualizarTarefas() {
		this.tarefas = repositorio.listarTarefas();
		setListAdapter(new TarefaListAdapter(this, this.tarefas));
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu_tarefa, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		 AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		    switch (item.getItemId()) {
		        case R.id.item_deletar:
		            deletarTarefa(info.id);
		            return true;
		        default:
		            return super.onContextItemSelected(item);
		    }
	}

	private void deletarTarefa(long id) {
		repositorio.deletar(id);
		Toast.makeText(this, "Tarefa deletada", Toast.LENGTH_SHORT).show();
		atualizarTarefas();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERIR_EDITAR, 0, "Nova tarefa");
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
			case INSERIR_EDITAR: {
				startActivity(new Intent(this, CadastroTarefa.class));
				break;
			}
		}
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			atualizarTarefas();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		repositorio.fechar();
	}
}