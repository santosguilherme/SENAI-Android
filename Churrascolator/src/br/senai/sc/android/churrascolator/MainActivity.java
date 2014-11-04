package br.senai.sc.android.churrascolator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void irSegundaPagina(View v) {
		EditText inputQtdCarne = (EditText) findViewById(R.id.inputQtdCarne);
		
		Churrasco churrasco = new Churrasco();
		churrasco.setQuantidadeCarne(Integer.valueOf(inputQtdCarne.getText().toString()));
		
		Intent irParaSegundaPagina = new Intent(this, LinguicaActivity.class);
		irParaSegundaPagina.putExtra(Churrasco.KEY_BUNDLE, churrasco);
		startActivity(irParaSegundaPagina);
	}
}
