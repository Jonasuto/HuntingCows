package mx.itesm.rmroman.huntingcows;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import org.andengine.audio.music.Music;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import java.io.IOException;

/*
Esta clase representa el EscenaJuego Principal. Muestra la escena de Splash y luego hace el cambio
a la primer escena que se quiera mostrar
 */

public class ControlJuego extends SimpleBaseGameActivity
{
    // Dimensiones de la cámara. Se ajustará (escalará) a cualquier pantalla física.
    public static final int ANCHO_CAMARA = 1280;
    public static final int ALTO_CAMARA = 800;
    // La cámara
    protected Camera camara;

    public Engine ingeniero = this.getEngine();
    // El administrador de escenas (se encarga de cambiar las escenas)

    private AdministradorEscenas admEscenas;

    //private mx.itesm.rmroman.proyectobasegpo01.AdministradorMusica admMusica;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /*
    Se crea la configuración del Engine.
    new FillResolutionPolicy() - Escala la cámara a lo ancho y alto de la pantalla
    new FixedResolutionPolicy(ANCHO_CAMARA, ALTO_CAMARA) - Mantiene la cámara con dimensiones fijas, si la pantalla es más pequeña se recorta, si es más grande quedan zonas blancas alrededor.
    new RatioResolutionPolicy(ANCHO_CAMARA, ALTO_CAMARA) - Mantiene la relación ancho/alto
     */

    @Override

    public EngineOptions onCreateEngineOptions() {


        camara = new Camera(0,0,ANCHO_CAMARA,ALTO_CAMARA);
        EngineOptions opciones = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
                new FillResolutionPolicy(),camara);

        // Para habilitar la reproducción de sonidos 'largos' -Música de fondo-
        opciones.getAudioOptions().setNeedsMusic(true);
        // Para habilitar la reproducciñon de sonidos 'cortos'. -Efectos-
        opciones.getAudioOptions().setNeedsSound(true);

        opciones.getTouchOptions().setNeedsMultiTouch(true);


        return opciones;
    }


    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions){
        return new LimitedFPSEngine(pEngineOptions,50);
    }


    // Crea los recursos del juego.
    @Override
    protected void onCreateResources() throws IOException {
        // Pasamos la información al administrador de escenas para que los comparta con cada Escena

        AdministradorEscenas.inicializarAdministrador(this, mEngine);
        // Obtenemos la referencia al objeto administrador
        AdministradorMusica.inicializarAdministrador(mEngine,this);

        admEscenas = AdministradorEscenas.getInstance();

        //mx.itesm.rmroman.proyectobasegpo01.AdministradorMusica.inicializarAdministrador(mEngine, this);
    }

    // Regresa la escena inicial.
    @Override
    protected Scene onCreateScene() {
        // Crea la primer escena que se quiere mostrar
        admEscenas.crearEscenaSplash();
        admEscenas.setEscena(TipoEscena.ESCENA_SPLASH);

        // Programa la carga de la segunda escena, después de cierto tiempo
        mEngine.registerUpdateHandler(new TimerHandler(2,
                new ITimerCallback() {
                    @Override
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        mEngine.unregisterUpdateHandler(pTimerHandler); // Invalida el timer
                        // Cambia a la escena del MENU
                        //** 1. CREA la escena del menú (la NUEVA)
                        //** 2. PONE la escena del menú (la NUEVA)
                        //** 3. LIBERA la escena de Splash (la ANTERIOR)
                        admEscenas.crearEscenaSplashHuntingCows();
                        admEscenas.setEscena(TipoEscena.ESCENA_SPLASH_HUNTING_COWS);
                        admEscenas.liberarEscenaSplash();
                    }
                }));

        return admEscenas.getEscenaActual();    // Regresa la primer escena que se muestra
    }
    // Atiende la tecla de BACK.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            if(admEscenas.getTipoEscenaActual()== TipoEscena.ESCENA_MENU) {
                // Si está en el menú, termina
                finish();
            } else {
                // La escena que se está mostrando maneja el evento
                admEscenas.getEscenaActual().onBackKeyPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public synchronized void onPauseGame(){
        SharedPreferences mSharedPrefs = getSharedPreferences("PrefMusica", Context.MODE_PRIVATE);
        SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
        boolean musicOn = mSharedPrefs.getBoolean("Musica", true);

        // Pausar la música en Niveles
        if (musicOn&&admEscenas!=null && admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_CAZA_JURASICA||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_CAZA_JURASICA_LVL2||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_ALEATORIEDAD||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_PIRATA||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_VIAJE_EGIPTO||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_INTRO_CAZA_JURASICA) {
            // Esta en AcercaDe, revisar si está reproduciendo música
            Music musicaFondo = admEscenas.getEscenaActual().admMusica.getMusicaNivel();
            if (musicaFondo!=null && musicaFondo.isPlaying()) {
                musicaFondo.pause();
            }
        }else if(musicOn&&admEscenas!=null && admEscenas.getTipoEscenaActual()!=TipoEscena.ESCENA_SPLASH){
            // Pausar la música en todas las escenas que ocnforman el menú
            Music musicaFondo = admEscenas.getEscenaActual().admMusica.getMusicaMenu();
            if(musicaFondo!=null && musicaFondo.isPlaying()){
                musicaFondo.pause();
            }
        }
        super.onPauseGame();
    }

    public synchronized void onResumeGame(){


        SharedPreferences mSharedPrefs = getSharedPreferences("PrefMusica", Context.MODE_PRIVATE);
        SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
        boolean musicOn = mSharedPrefs.getBoolean("Musica", true);

        if (admEscenas!=null && admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_CAZA_JURASICA||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_CAZA_JURASICA_LVL2||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_ALEATORIEDAD||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_PIRATA||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_VIAJE_EGIPTO||admEscenas.getTipoEscenaActual()==TipoEscena.ESCENA_INTRO_CAZA_JURASICA) {
            // Esta en AcercaDe, revisar si está reproduciendo música
            Music musicaFondo = admEscenas.getEscenaActual().admMusica.getMusicaNivel();
            if (musicaFondo!=null && musicOn && !musicaFondo.isPlaying()) {
                musicaFondo.play();
            }
        }else if(admEscenas!=null && admEscenas.getTipoEscenaActual()!=TipoEscena.ESCENA_SPLASH){
            // Pausar la música en todas las escenas que conforman el menú
            Music musicaFondo = admEscenas.getEscenaActual().admMusica.getMusicaMenu();
            if(musicaFondo!=null && musicOn && !musicaFondo.isPlaying()){
                musicaFondo.play();
            }
        }
        super.onResumeGame();
    }


    public AdministradorEscenas getAdmEscenas() {

        return admEscenas;
    }


    // La aplicación sale de memoria, la eliminamos completamente.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (admEscenas != null) {
            System.exit(0);
        }
    }
}
