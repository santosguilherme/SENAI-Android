package com.example.helloword;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		textView = (TextView) findViewById(R.id.textoMensagem);
		Bundle params = getIntent().getExtras();
		if (params != null) {
			textView.setText(params.getString("mensagem"));
		}
	}
}
