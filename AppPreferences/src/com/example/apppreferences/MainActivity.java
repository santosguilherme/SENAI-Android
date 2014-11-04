package com.example.apppreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void salvarPreferencias(View view) {
    	SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
    	Editor editor = preferences.edit();
    	TextView textPreferencias = (TextView) findViewById(R.id.textPreferencias);
    	
    	editor.putString("key", textPreferencias.getText().toString());
    	editor.commit();
    	
    	textPreferencias.setText(null);
	}
    
    public void mostrarPreferencias(View view){
    	SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
    	String textPreferencias = preferences.getString("key", "Valor não preenchido!");
    	Toast.makeText(this, "Preference: ".concat(textPreferencias), Toast.LENGTH_SHORT).show();
    }
    
    public void irParaSettingActivity(View view){
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    }
}
