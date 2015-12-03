package mx.itesm.rmroman.huntingcows;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;

import java.io.IOException;

/**
 * Reproducir, parar y liberar audio. Tambíen se manejan efectos como la vibracion
 */
public class AdministradorMusica {
    // Instancia única de la clase
    private static final AdministradorMusica INSTANCE = new AdministradorMusica();

    public Engine engine;
    public ControlJuego actividadJuego;

    // ***** MUSICA *****
    private Music musicaMenu;
    private Music musicaNivel;

    private Sound efectoSonido;

    public static AdministradorMusica getInstance() {

        return INSTANCE;
    }

    public static void inicializarAdministrador(Engine engine,ControlJuego control) {
        getInstance().engine = engine;
        getInstance().actividadJuego=control;
    }

    public void cargarMusicaMenu(){
        try {
            musicaMenu = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                    actividadJuego, "Musica/MenuTheme/theme3.mp3");
        } catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }

        musicaMenu.setLooping(true);

        reproducirMusicaMenu();
    }

    public void cargarMusicaNivel(){
        // ***** Música de fondo del Menu
        try {
            musicaNivel = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                    actividadJuego, "Musica/themeNivel1.wav");
        } catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }
        musicaNivel.setLooping(true);
        reproducirMusicaNivel();
    }

    public void cargarMusicaHistoria(){
        // ***** Música de fondo del Menu
        try {
            musicaNivel = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                    actividadJuego, "Musica/theme3.wav");
        } catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }
        musicaNivel.setLooping(true);
        reproducirMusicaNivel();
    }

    public void cargarMusicaSplash(){
        // ***** Música de fondo del Menu
        try {
            musicaNivel = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                    actividadJuego, "Musica/MenuTheme/theme.wav");
        } catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }
        musicaNivel.setLooping(true);
        reproducirMusicaNivel();
    }

    public void cargarMusicaAcercaDe(){
        // ***** Música de fondo del Menu
        try {
            musicaNivel = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                    actividadJuego, "Musica/MenuTheme/acercaDeTheme.wav");
        } catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }
        musicaNivel.setLooping(true);
        reproducirMusicaNivel();
    }


    public void cargarMusicaPerdiste(){
        // ***** Música de fondo del Menu
        try {
            musicaNivel = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                    actividadJuego, "Musica/soundGameOver.wav");
        } catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }
        musicaNivel.setLooping(true);
        reproducirMusicaNivel();
    }

    public void cargarMusicaGanaste(){
        // ***** Música de fondo del Menu
        try {
            musicaNivel = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
                    actividadJuego, "Musica/soundGameOver.wav");
        } catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }
        musicaNivel.setLooping(true);
        reproducirMusicaNivel();
    }


    public void liberarMusicaNivel(){

        musicaNivel.release();
    }

    public void liberarMusicaMenu(){

        musicaMenu.release();
    }

    public void pararMusicaMenu(){
        if(musicaMenu.isPlaying()){
            musicaMenu.pause();
        }
    }

    public void pararMusicaNivel(){
        if(musicaNivel.isPlaying()){
            musicaNivel.pause();
        }


    }

    public void reproducirMusicaMenu(){
        if(leerPreferenciaMusica()){
            if(musicaMenu.isPlaying()){
                musicaMenu.resume();
            }else{
                musicaMenu.play();
            }
        }
    }

    public void reproducirMusicaNivel(){
        if(leerPreferenciaMusica()){
            if(musicaNivel.isPlaying()){
                musicaNivel.resume();
            }else{
                musicaNivel.play();
            }
        }
    }

    public Music getMusicaMenu() {

        return musicaMenu;
    }

    public Music getMusicaNivel() {
        return musicaNivel;
    }

    public void vibrar(int i){
        if(leerPreferenciaEfectos()){
            Vibrator v = (Vibrator)actividadJuego.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(i);
        }
    }


    // Si está en true entonces se escucha la musica

    public boolean leerPreferenciaMusica() {
        SharedPreferences mSharedPrefs = actividadJuego.getSharedPreferences("PrefMusica", Context.MODE_PRIVATE);
        SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
        boolean musicOn = mSharedPrefs.getBoolean("Musica", true);
        return musicOn;
    }

    public void modificarPreferenciaMusica(boolean soundflag) {
        SharedPreferences mSharedPrefs = actividadJuego.getSharedPreferences("PrefMusica", Context.MODE_PRIVATE);
        SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
        mPrefsEditor.putBoolean("Musica", soundflag);
        mPrefsEditor.commit();
    }

    public boolean leerPreferenciaEfectos() {
        SharedPreferences mSharedPrefs = actividadJuego.getSharedPreferences("PrefEfectos", Context.MODE_PRIVATE);
        SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
        boolean eff = mSharedPrefs.getBoolean("Efectos", true);
        return eff;
    }

    public void modificarPreferenciaEfectos(boolean effflag) {
        SharedPreferences mSharedPrefs = actividadJuego.getSharedPreferences("PrefEfectos", Context.MODE_PRIVATE);
        SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
        mPrefsEditor.putBoolean("Efectos", effflag);
        mPrefsEditor.commit();
    }

    public void cargarSonidos() {
        // Efecto de sonid
        try {
            efectoSonido = SoundFactory.createSoundFromAsset(engine.getSoundManager(),
                    actividadJuego, "Musica/sonidoBoton.wav");
        }
        catch (IOException e) {
            Log.i("cargarSonidos", "No se puede cargar demo.ogg");
        }

        efectoSonido.setLooping(false);


        //efectoExplosion.setVolume(0.5f);    // Volumen de este archivo
        // *** Ver onManagedUpdate, onSceneTouchEvent, onBackKeyPressed, liberarEscena
    }

    public void reproducirMusicaBoton(){

        efectoSonido.play();

    }
}
