package com.example.formularionomeidade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static final int SALVAR_NOME_IDADE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void abrirFormulario(View view) {
		Intent intent = new Intent(getApplicationContext(),
				CadastroActivity.class);
		startActivityForResult(intent, SALVAR_NOME_IDADE);
	}

	@Override
	protected void onActivityResult(int codigo, int resultado, Intent intent) {
		if (codigo == SALVAR_NOME_IDADE && resultado == RESULT_OK) {
			Bundle bundle = intent.getExtras();
			Toast.makeText(
					this,
					"Nome: " + bundle.getString("nome") + "\nIdade:"
							+ bundle.getString("idade"), Toast.LENGTH_SHORT)
					.show();
		}
	}
}
