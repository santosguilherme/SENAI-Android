package com.example.appservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appservice.service.ContadorComConexao;
import com.example.appservice.service.ContadorComConexao.LocalBinder;
import com.example.appservice.service.api.IContador;


public class MainActivity extends Activity implements ServiceConnection{

	private IContador contador;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarServico(View view){
    	Intent iniciarContador = new Intent(MainActivity.this, ContadorComConexao.class);
    	startService(iniciarContador);
    	bindService(iniciarContador, this, Context.BIND_AUTO_CREATE);
    }
    
    public void pararServico(View view){
    	unbindService(this);
    }
    
    public void visualizarServico(View view){
    	int valorAtual = this.contador.getValorAtual();
    	Toast.makeText(MainActivity.this, "Count> ".concat(String.valueOf(valorAtual)), Toast.LENGTH_LONG).show();
    }

	@Override
	public void onServiceConnected(ComponentName componentName, IBinder service) {
		Log.i("Conectado", getClass().getCanonicalName());
		this.contador = ((LocalBinder) service).getContador();
	}

	@Override
	public void onServiceDisconnected(ComponentName componentName) {
		Log.i("Desconectado", getClass().getCanonicalName());
		this.contador = null;
	}
    
}
