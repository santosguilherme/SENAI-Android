package br.senai.sc.android.churrascolator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class LinguicaActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seccond);
	}

	public void irTerceiraPagina(View v) {
		Bundle params = getIntent().getExtras();
		
		if (params == null) {
			return;
		}
		EditText inputQtdLinguica = (EditText) findViewById(R.id.inputQtdLinguica);

		Churrasco churrasco = (Churrasco) params.getSerializable(Churrasco.KEY_BUNDLE);
		
		Integer quantidadeLinguica = Integer.valueOf(inputQtdLinguica.getText().toString());
		churrasco.setQuantidadeLinguica(quantidadeLinguica);
		
		Intent irParaTerceiraPagina = new Intent(this, RefrigeranteActivity.class);
		irParaTerceiraPagina.putExtra(Churrasco.KEY_BUNDLE, churrasco);
		
		startActivity(irParaTerceiraPagina);
	}
}
