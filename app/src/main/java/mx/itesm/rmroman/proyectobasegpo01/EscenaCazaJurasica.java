package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;


/**
 * Created by Campos on 11/10/15.
 */
public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFlecha;
    private ITextureRegion regionFondo;



    private Jugador spritePersonaje;

    private TiledTextureRegion regionPersonajeAnimado;


    // Sprite para el fondo
    private Sprite spriteFondo;

    private Sprite spriteFlechaIzquierda;
    private Sprite spriteFlechaDerecha;

    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionFlecha=cargarImagen("Imagenes/Historia/flecha.png");

        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 600, 158, 1, 4);


    }

    @Override
    public void crearEscena() {

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        spritePersonaje = new Jugador(-ControlJuego.ANCHO_CAMARA/4+ControlJuego.ANCHO_CAMARA, ControlJuego.ALTO_CAMARA/4,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

        admMusica.cargarMusica(2);

        spriteFlechaIzquierda= spriteFondo = cargarSprite((ControlJuego.ANCHO_CAMARA/2)-130, ControlJuego.ALTO_CAMARA / 2, regionFlecha);
        spriteFlechaIzquierda.setRotation(-180);
        attachChild(spriteFlechaIzquierda);

        spriteFlechaDerecha= spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFlecha);
        attachChild(spriteFlechaDerecha);


    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_CAZA_JURASICA;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

    }



    @Override
    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {

        admMusica.liberarMusica();
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);
        regionFondo.getTexture().unload();
        regionFondo=null;
    }
}