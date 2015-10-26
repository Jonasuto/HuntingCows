package mx.itesm.rmroman.proyectobasegpo01;

/**
 * Created by Campos on 24/10/15.
 */
import android.util.Log;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;

/**
 * La escena que se muestra cuando corre la aplicación (Logo del TEC)
 */
public class EscenaAjustes extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionMenuPausa;
    private ITextureRegion regionMenuOffoff;
    private ITextureRegion regionMenuOffon;
    private ITextureRegion regionMenuOnoff;
    private ITextureRegion regionMenuOnon;

    private Sprite spriteMenuPausa;
    private Sprite spriteFondo; //(el fondo de la escena, estático)
    private Sprite spriteOn;
    private Sprite spriteOff;


    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("Imagenes/MenuInicio/fondoMenu.jpg");

        regionMenuPausa = cargarImagen("Imagenes/menuAjustes.png");

        regionMenuOffoff=cargarImagen("Imagenes/off_apagado.png");
        regionMenuOffon=cargarImagen("Imagenes/off_encendido.png");
        regionMenuOnoff=cargarImagen("Imagenes/on_apagado.png");
        regionMenuOnon=cargarImagen("Imagenes/on_encendido.png");
    }

    @Override
    public void crearEscena() {
        // Creamos el sprite de manera óptima
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);

        // Crea el fondo de la pantalla
        SpriteBackground fondo = new SpriteBackground(1,1,1, spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        spriteMenuPausa=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionMenuPausa);
        attachChild(spriteMenuPausa);

        spriteOn=cargarSprite(ControlJuego.ANCHO_CAMARA / 2+40, ControlJuego.ALTO_CAMARA / 2-30, regionMenuOnon);
        attachChild(spriteOn);

        spriteOff=cargarSprite(ControlJuego.ANCHO_CAMARA /2 -60, ControlJuego.ALTO_CAMARA / 2-30, regionMenuOffoff);
        attachChild(spriteOff);

    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.liberarEscenaAjustes();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);

    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_AJUSTES;
    }

    @Override
    public void liberarEscena() {
        // Liberar cada recurso usado en esta escena
        this.detachSelf();      // La escena se deconecta del engine
        this.dispose();         // Libera la memoria
    }

    @Override
    public void liberarRecursos() {

    }
}
