package mx.itesm.rmroman.proyectobasegpo01;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.widget.Toast;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.audio.music.Music;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.controller.MultiTouch;
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
    // El administrador de escenas (se encarga de cambiar las escenas)

    private mx.itesm.rmroman.proyectobasegpo01.AdministradorEscenas admEscenas;
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

        if(MultiTouch.isSupported(this)) {
            if(MultiTouch.isSupportedDistinct(this)) {
                Toast.makeText(this, "Touch si!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "hay error.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "no lo agarra.)\n\nControls are placed at different vertical locations.", Toast.LENGTH_LONG).show();
        }


        opciones.getTouchOptions().setNeedsMultiTouch(true);


        return opciones;
    }

    // Crea los recursos del juego.
    @Override
    protected void onCreateResources() throws IOException {
        // Pasamos la información al administrador de escenas para que los comparta con cada Escena

        mx.itesm.rmroman.proyectobasegpo01.AdministradorEscenas.inicializarAdministrador(this, mEngine);
        // Obtenemos la referencia al objeto administrador
        admEscenas = mx.itesm.rmroman.proyectobasegpo01.AdministradorEscenas.getInstance();
        mx.itesm.rmroman.proyectobasegpo01.AdministradorMusica.inicializarAdministrador(mEngine, this);
    }

    // Regresa la escena inicial.
    @Override
    protected Scene onCreateScene() {
        // Crea la primer escena que se quiere mostrar
        admEscenas.crearEscenaSplash();
        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_SPLASH);

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

                        mEngine.registerUpdateHandler(new TimerHandler(2,
                                new ITimerCallback() {
                                    @Override
                                    public void onTimePassed(TimerHandler pTimerHandler) {
                                        mEngine.unregisterUpdateHandler(pTimerHandler); // Invalida el timer
                                        // Cambia a la escena del MENU
                                        //** 1. CREA la escena del menú (la NUEVA)
                                        //** 2. PONE la escena del menú (la NUEVA)
                                        //** 3. LIBERA la escena de Splash (la ANTERIOR)

                                        admEscenas.crearEscenaMenu();
                                        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
                                        admEscenas.liberarEscenaSplashHuntingCows();
                                    }
                                }));

                    }
                }));

        return admEscenas.getEscenaActual();    // Regresa la primer escena que se muestra
    }

    // Atiende la tecla de BACK.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            if(admEscenas.getTipoEscenaActual()== mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU) {
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

    // La aplicación sale de memoria, la eliminamos completamente.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (admEscenas != null) {
            System.exit(0);
        }
    }

}