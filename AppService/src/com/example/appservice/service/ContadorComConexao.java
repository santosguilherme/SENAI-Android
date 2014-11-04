package com.example.appservice.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.appservice.service.api.IContador;

public class ContadorComConexao extends Contador implements IContador {

	private final IBinder conexao = new LocalBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return this.conexao;
	}

	@Override
	public int getValorAtual() {
		return super.count;
	}

	public class LocalBinder extends Binder {
		public IContador getContador(){
			return ContadorComConexao.this;
		}
	}
}
