package br.senai.sc.android.churrascolator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class RefrigeranteActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
	}

	public void irQuartaPagina(View v) {
		Bundle params = getIntent().getExtras();
		if (params == null) {
			return;
		}
		EditText inputQtdRefrigerante = (EditText) findViewById(R.id.inputQtdRefrigerante);

		Churrasco churrasco = (Churrasco) params
				.getSerializable(Churrasco.KEY_BUNDLE);
		Integer quantidadeRefrigerante = Integer.valueOf(inputQtdRefrigerante
				.getText().toString());
		churrasco.setQuantidadeRefrigerante(quantidadeRefrigerante);

		Intent irParaQuartaPagina = new Intent(this, PessoasActivity.class);
		irParaQuartaPagina.putExtra(Churrasco.KEY_BUNDLE, churrasco);
		startActivity(irParaQuartaPagina);
	}
}
