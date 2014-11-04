package com.example.helloword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SeccondActivity extends Activity {

	private EditText inputTexto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle params = getIntent().getExtras();
		if (params != null) {
			String texto = params.getString("mensagem");
			Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
		}
		setContentView(R.layout.activity_seccond);
		inputTexto = (EditText) findViewById(R.id.inputTexto);
	}

	public void sendText(View view) {
		Intent enviarDados = new Intent(getApplicationContext(),
				ResultActivity.class);
		enviarDados.putExtra("mensagem", inputTexto.getText().toString());
		startActivity(enviarDados);
	}
}
