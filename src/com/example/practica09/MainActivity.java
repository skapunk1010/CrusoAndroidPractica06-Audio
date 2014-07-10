package com.example.practica09;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn01;
	private Button btn02;
	private Button btn03;
	private Button btnStop;
	private MediaPlayer musica;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn01 = (Button)findViewById(R.id.button1);
		btn02 = (Button)findViewById(R.id.button2);
		btn03 = (Button)findViewById(R.id.button3);
		btnStop = (Button)findViewById(R.id.button4);
		
		btn01.setOnClickListener(this);
		btn02.setOnClickListener(this);
		btn03.setOnClickListener(this);
		btnStop.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view){
		if(musica != null){
			musica.release();
		}
		switch(view.getId())
		{
			case R.id.button1: //Clandestino
				musica = MediaPlayer.create(this, R.raw.clandestino);
				break;
			case R.id.button2: //Bongo bong
				musica = MediaPlayer.create(this, R.raw.bongo_bong);
				break;
			case R.id.button3: //Desaparecido
				musica = MediaPlayer.create(this, R.raw.desaparecido);
				break;
			case R.id.button4: //Stop
				if(musica != null){
					musica.release();
				}
				break;
			default:
				break;
		}
		if(view.getId() != R.id.button4 && musica != null){
			musica.seekTo(0);
			musica.start();
		}
	}
	
	
	
	
}
