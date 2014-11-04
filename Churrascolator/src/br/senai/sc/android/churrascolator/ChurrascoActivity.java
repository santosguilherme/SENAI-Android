package br.senai.sc.android.churrascolator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class ChurrascoActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_churrasco);

		CharSequence mensagemResultado = "Houve um erro no meio do caminho!";

		Churrasco churrasco = getChurrasco();
		if (churrasco != null) {
			mensagemResultado = churrasco.getResultadoCalculo();
		}
		((TextView) findViewById(R.id.textResultadoCalculo))
				.setText(mensagemResultado);
	}

	private Churrasco getChurrasco() {
		Churrasco churrasco = null;
		Bundle params = getIntent().getExtras();
		if (params != null) {
			churrasco = (Churrasco) params
					.getSerializable(Churrasco.KEY_BUNDLE);
		}
		return churrasco;
	}

}
