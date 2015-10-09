package mx.itesm.jcampos.huntingcows;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;

/**
 * Created by Campos on 09/10/15.
 */

public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoFrente;
    private Sprite spriteFondo;


    public void cargarRecursos() {
        regionFondo = cargarImagen("Imagenes/Niveles/fondoJuego.jpg");
        regionFondoFrente = cargarImagen("starsFront.png");
    }


    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
        admMusica.cargarMusica(1);

    }

    public void onBackKeyPressed() {
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaCazaJurasica();
    }

    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_CAZA_JURASICA;
    }


    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();

    }

    public void liberarRecursos() {
        // Detiene el acelerómetro
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);

        regionFondo.getTexture().unload();
        regionFondo = null;
        regionFondoFrente.getTexture().unload();
        regionFondoFrente = null;

    }



}
