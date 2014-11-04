package com.example.apphandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;


public class SplashActivity extends Activity {

	private HandlerSplash handlerSplash = new HandlerSplash(); 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	requestWindowFeature(Window.FEATURE_NO_TITLE); 
        handlerSplash.sendMessageDelayed(new Message(), 3000);
    }
    
    private class HandlerSplash extends Handler {
    	
    	@Override
    	public void handleMessage(Message msg) {
    	
    		Intent irParaTelaInicial = new Intent(getApplicationContext(), HomeActivity.class);
    		finish();
    		startActivity(irParaTelaInicial);
    	}
    }
}
