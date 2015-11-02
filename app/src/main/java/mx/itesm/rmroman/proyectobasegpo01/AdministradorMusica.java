package mx.itesm.rmroman.proyectobasegpo01;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import android.os.Vibrator;
import java.io.IOException;


public class AdministradorMusica {
    // Instancia única de la clasea
    private static final AdministradorMusica INSTANCE = new AdministradorMusica();

    public Engine engine;
    public mx.itesm.rmroman.proyectobasegpo01.ControlJuego actividadJuego;


    private Sound musicaBoton;
    private Music musicaTodo;

    private boolean musicaEncendida=true;

    public int reproducesiono=1;


    public static AdministradorMusica getInstance() {

        return INSTANCE;
    }

    public static void inicializarAdministrador(Engine engine, mx.itesm.rmroman.proyectobasegpo01.ControlJuego control) {
        getInstance().engine = engine;
        getInstance().actividadJuego=control;
    }

    public void cargarMusica(int num){


        if(musicaEncendida==true) {

            if (num == 0) {
                try {
                    musicaTodo = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                            actividadJuego, "Musica/MenuTheme/theme.wav");
                } catch (IOException e) {
                    Log.i("cargarSonidos", "No se puede cargar demo.ogg");
                }

                musicaTodo.setLooping(true);

                reproducirMusica();
                reproducesiono = 1;
            }

            else if (num == 1) {
                try {
                    musicaTodo = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                            actividadJuego, "Musica/MenuTheme/acercaDeTheme.wav");
                } catch (IOException e) {
                    Log.i("cargarSonidos", "No se puede cargar demo.ogg");
                }
                musicaTodo.setLooping(true);
                reproducirMusica();
            }

            else if (num == 2) {
                try {
                    musicaTodo = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                            actividadJuego, "Musica/themeNivel1.wav");
                } catch (IOException e) {
                    Log.i("cargarSonidos", "No se puede cargar demo.ogg");
                }
                musicaTodo.setLooping(true);
                reproducirMusica();
            }
        }

        else {
            try {
                musicaTodo = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                        actividadJuego, "Musica/silencio.wav");
            } catch (IOException e) {
                Log.i("cargarSonidos", "No se puede cargar demo.ogg");
            }
            musicaTodo.setLooping(false);
            reproducirMusica();
        }
    }


    public void setMusicaEncendida(boolean musicaEncendida,int NumEscena){

        if(musicaEncendida==false && NumEscena != (-1) ){
            this.musicaEncendida=musicaEncendida;
            this.liberarMusica();
        }

        else if(musicaEncendida==false && NumEscena==(-1)){
            this.musicaEncendida=musicaEncendida;
        }

        if(musicaEncendida==true && NumEscena != (-1) ){
            this.musicaEncendida=musicaEncendida;
            cargarMusica(NumEscena);
        }

        else if(musicaEncendida==true && NumEscena==(-1)){
            this.musicaEncendida=musicaEncendida;
        }





    }

    public boolean getMusicaEncendida(){

        return musicaEncendida;
    }

    public void cargarMusicaBoton(){

        try {
            musicaBoton = SoundFactory.createSoundFromAsset(engine.getSoundManager(),
                    actividadJuego, "Musica/sonidoBoton.wav");
        }
        catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }

        musicaBoton.setLooping(false);
    }

    public void vibrar(int i){

        if(musicaEncendida==true) {
            Vibrator vibrador = (Vibrator) actividadJuego.getSystemService(Context.VIBRATOR_SERVICE);
            vibrador.vibrate(i);
        }
    }


    public void reproducirMusica(){

        musicaTodo.play();
    }


    public void reproducirMusicaBoton(){

        if(musicaEncendida==true) {
            musicaBoton.play();
        }
    }

    public void reproduceio(){

        if(reproducesiono==0){
            musicaTodo.resume();
            reproducesiono=1;
        }
        else{
            musicaTodo.pause();
            reproducesiono=0;
        }
    }

    public void liberarMusica(){

        musicaTodo.release();
    }
}
