package com.example.helloword;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public static final int MENU_ABOUT = 1;
	public static final int MENU_PREFERENCES = 2;
	private static final int MENU_MORE = 3;
	public static final int PESQUISAR = 4;
	public static final int LIMPAR = 5;
	public static final int SAIR = 6;
	private static final int SELECIONAR_CONTATOS = 7;

	private Button botaoCentral;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		botaoCentral = (Button) findViewById(R.id.botaoCentral);
		botaoCentral.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SeccondActivity.class);
				intent.putExtra("mensagem", "teste bundle");
				startActivity(intent);
			}
		});
	}

	public void abrirNavegador(View v) {
		Uri uri = Uri.parse("http://www.google.com/");
		Intent abrirNavegador = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(abrirNavegador);
	}

	public void fazerLigacao(View v) {
		Uri uri = Uri.parse("tel:84081701");
		Intent fazerLigacao = new Intent(Intent.ACTION_CALL, uri);
		startActivity(fazerLigacao);
	}

	public void abrirAgenda(View v) {
		Uri uri = Uri.parse("content://com.android.contacts/contacts");
		Intent abrirAgenda = new Intent(Intent.ACTION_PICK, uri);
		startActivityForResult(abrirAgenda, SELECIONAR_CONTATOS);
	}

	@Override
	protected void onActivityResult(int codigo, int resultado, Intent intent) {
		if (codigo == SELECIONAR_CONTATOS && resultado == RESULT_OK) {
			Uri uri = intent.getData();
			Toast.makeText(this, "Contato: " + uri, Toast.LENGTH_SHORT).show();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_PREFERENCES, 0, "Configurações");
		menu.add(0, MENU_ABOUT, 1, "Sobre");

		SubMenu subMenu = menu.addSubMenu(0, MENU_MORE, 2, "Mais");

		subMenu.add(0, PESQUISAR, 0, "Pesquisar");
		subMenu.add(0, LIMPAR, 1, "Limpar");
		subMenu.add(0, SAIR, 2, "Sair");

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == MENU_ABOUT) {
			Toast.makeText(this, "Sobre - OK", Toast.LENGTH_LONG).show();
			return true;
		}
		if (itemId == MENU_PREFERENCES) {
			Toast.makeText(this, "Configurações - OK", Toast.LENGTH_LONG)
					.show();
			return true;
		}
		return false;
	}
}
