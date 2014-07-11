package com.example.practica09;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn01;
	private Button btn02;
	private Button btn03;
	private Button btnStop;
	private MediaPlayer musica;
	private String externalPath;
	
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
		
		//el objeto música no se había instanciado. Ahora se instancia primero como a continuación:
		musica = new MediaPlayer();
		
		/**El método Environment.getExternalStoragePublicDirectory();
		 * nos regresa el directorio del almacenamiento interno del dispositivo, que sería algo similar a lo siguiente:
		 * 		storage/sdcard0/
		 * 
		 *  El parámetro que recibe el método anterior, que en este caso es Environment.DIRECTORY_MUSIC, nos regresa
		 *  la carpeta donde como su nombre lo dice, es un directorio donde se almacena música. Android nos da estas
		 *  constantes ya, y es por eso que aveces nos crea algunas carpetas predeterminadas para audio, video, música
		 *  y fotos que se realizan con el dispositivo. En este caso, esta constante nos regresa el directorio donde 
		 *  se almacenan música. ya con este parámetro nos regresa un path diferente. Sería el siguiente:
		 *  	storage/sdcard0/Music
		 *  Existen más directorios que se crean con las constantes de la clase Environment, si se desea algún
		 *  otro las pueden checar en la API de la página de Android
		 *  
		 *  En algunos dispositivos el directorio ya está creado. Sino está creado, se debe de crear al menos para este ejemplo.
		 *  Y dentro del directorio es donde se deben de alojar sus archivos de audio para que se puedan reproducir.
		 */
		File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
		externalPath = file.getAbsolutePath();
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
			/** Se cambia el método release() por el reset() por que al llamar al método release(), 
			 * el estado del objeto música pasaba a terminado, entonces cuando se quería volver a iniciar
			 * e invocábamos al método prepare(), era un estado inválido y nos marcaba una excepción de tipo
			 * IlegalStateException. Se puede apreciar mejor en el diagrama de estados que les puse en clase:
			 * http://developer.android.com/reference/android/media/MediaPlayer.html
			 */
			musica.reset();
		}
		System.out.println(externalPath);
		try {
			
			/**Una vez que se hace el switch, se concatena el path con el nombre de la canción.
			 */
			switch(view.getId())
			{
				case R.id.button1: //Clandestino
					Toast.makeText(getApplicationContext(), externalPath+"/clandestino.mp3", Toast.LENGTH_SHORT).show();
					musica.setDataSource(externalPath+"/clandestino.mp3");
					break;
				case R.id.button2: //Bongo bong
					Toast.makeText(getApplicationContext(), externalPath+"/bongo_bong.mp3", Toast.LENGTH_SHORT).show();
					musica.setDataSource(externalPath+"/bongo_bong.mp3");
					break;
				case R.id.button3: //Desaparecido
					Toast.makeText(getApplicationContext(), externalPath+"/desaparecido.mp3", Toast.LENGTH_SHORT).show();
					musica.setDataSource(externalPath+"/desaparecido.mp3");
					break;
				case R.id.button4: //Stop
					if(musica != null){
						musica.reset();
					}
					break;
				default:
					break;
			}	
			if(view.getId() != R.id.button4 && musica != null){
				/**Aquí se prepara el archivo para reproducirse*/
				musica.prepare();
				
				/**Se posiciona el cursor al principio para la reproducción desde el inicio*/
				musica.seekTo(0);
				
				/**Se inicia la reproducción del archivo de audio*/
				musica.start();
			}
			
			/**Este bloque de excepciones nos las piden al momento de usar el objeto de la 
			 * clase MediaPlayer que son los posibles errores en tiempo de ejecución que se pudieran presentar.
			 * Se podría implementar código según el caso de cada excepción.
			 */
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
