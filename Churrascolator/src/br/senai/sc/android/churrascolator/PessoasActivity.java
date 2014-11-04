package br.senai.sc.android.churrascolator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class PessoasActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
	}

	public void irResultadoPagina(View v) {
		Bundle params = getIntent().getExtras();
		if (params == null) {
			return;
		}
		EditText inputQtdPessoas = (EditText) findViewById(R.id.inputQtdPessoas);
		
		Churrasco churrasco = (Churrasco) params
				.getSerializable(Churrasco.KEY_BUNDLE);
		Integer quantidadePessoas = Integer.valueOf(inputQtdPessoas
				.getText().toString());
		churrasco.setNumeroPessoas(quantidadePessoas);

		Intent irParaResultadoPagina = new Intent(this, ChurrascoActivity.class);
		irParaResultadoPagina.putExtra(Churrasco.KEY_BUNDLE, churrasco);
		startActivity(irParaResultadoPagina);
	}
}
