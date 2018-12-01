package com.example.reproductormusica;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private TextView time;
    private TextView titulo;
    private TextView artista;
    private ImageView imagen;
    private ProgressBar psBar;
    private Handler skHandler = new Handler();
    private MediaPlayer c1;
    private MediaPlayer c2;
    private MediaPlayer c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton principal = (ImageButton) findViewById(R.id.selector);
        ImageButton anterior = (ImageButton) findViewById(R.id.previo);
        ImageButton siguiente = (ImageButton) findViewById(R.id.siguiente);
        c1 = MediaPlayer.create(MainActivity.this,R.raw.cancion1);
        c2 = MediaPlayer.create(MainActivity.this,R.raw.cancion2);
        c3 = MediaPlayer.create(MainActivity.this,R.raw.cancion3);
        mp = c1;
        time = (TextView) findViewById(R.id.time);
        titulo = (TextView) findViewById(R.id.titulo);
        artista = (TextView) findViewById(R.id.artista);
        imagen = (ImageView) findViewById(R.id.image);
        psBar = (ProgressBar) findViewById(R.id.psBar);
        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                    principal.setImageResource(android.R.drawable.ic_media_pause);
                } else {
                    mp.start();
                    principal.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                mp.reset();
                principal.setImageResource(android.R.drawable.ic_media_play);
                if(mp.getDuration()== c1.getDuration()) {
                    mp = c2;
                    titulo.setText("Glitter");
                    artista.setText("Ballpoint");
                    imagen.setImageResource(R.drawable.cancion2);
                } else if(mp.getDuration()== c2.getDuration()) {
                    mp = c3;
                    titulo.setText("The Fairies");
                    artista.setText("Ramin");
                    imagen.setImageResource(R.drawable.cancion3);
                } else if(mp.getDuration()== c3.getDuration()){
                    mp = c1;
                    titulo.setText("Beauty In The Mundane");
                    artista.setText("Bird Of Figment");
                    imagen.setImageResource(R.drawable.cancion1);
                }
            }
        });
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                mp.reset();
                principal.setImageResource(android.R.drawable.ic_media_play);
                if(mp.getDuration()== c1.getDuration()) {
                    mp = c3;
                    titulo.setText("The Fairies");
                    artista.setText("Ramin");
                    imagen.setImageResource(R.drawable.cancion3);
                } else if(mp.getDuration()== c2.getDuration()) {
                    mp = c1;
                    titulo.setText("Beauty In The Mundane");
                    artista.setText("Bird Of Figment");
                    imagen.setImageResource(R.drawable.cancion1);
                } else if(mp.getDuration()== c3.getDuration()){
                    mp = c2;
                    titulo.setText("Glitter");
                    artista.setText("Ballpoint");
                    imagen.setImageResource(R.drawable.cancion2);
                }
            }
        });
        time.setText( getFormat(mp.getDuration()) );
        psBar.setMax(mp.getDuration());
        psBar.setProgress(mp.getCurrentPosition());
        skHandler.postDelayed(updateskSong, 1000);
    }

    private String getFormat(int milliseconds )
    {
        int seconds = (int) (milliseconds / 1000) % 60 ;
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
        return ((hours<10)?"0"+hours:hours) + ":" +
                ((minutes<10)?"0"+minutes:minutes) + ":" +
                ((seconds<10)?"0"+seconds:seconds);
    }
    Runnable updateskSong = new Runnable() {
        @Override
        public void run() {
            psBar.setProgress( mp.getCurrentPosition() );
            time.setText(getFormat(mp.getCurrentPosition()) + "                                                   " + getFormat(mp.getDuration()));
            skHandler.postDelayed(updateskSong, 1000);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPause(){
        finishAffinity();
        super.onPause();
    }


}
