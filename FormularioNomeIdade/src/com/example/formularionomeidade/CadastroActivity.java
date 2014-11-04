package com.example.formularionomeidade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class CadastroActivity extends ActionBarActivity {

	private EditText inputNome;
	private EditText inputIdade;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		this.inputNome = (EditText) findViewById(R.id.inputNome);
		this.inputIdade = (EditText) findViewById(R.id.inputIdade);
	}

	public void salvar(View view) {
		Intent intentSalvar = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("nome", inputNome.getText().toString());
		bundle.putString("idade", inputIdade.getText().toString());
		intentSalvar.putExtras(bundle);
		setResult(RESULT_OK, intentSalvar);
		finish();
	}
}
