package mx.itesm.rmroman.proyectobasegpo01;

/**
 * Created by Campos on 24/10/15.
 */

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

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
    private boolean musicaEncendida=true;

    private Sprite spriteMenuPausa;
    private Sprite spriteFondo; //(el fondo de la escena, estático)
    private Sprite spritebtnOn;
    private Sprite spritebtnOff;


    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("Imagenes/MenuInicio/fondoMenu.jpg");

        regionMenuPausa = cargarImagen("Imagenes/Ajustes/pausa.png");

        regionMenuOffoff=cargarImagen("Imagenes/Ajustes/off_apagado.png");
        regionMenuOffon=cargarImagen("Imagenes/Ajustes/off_encendido.png");
        regionMenuOnoff=cargarImagen("Imagenes/Ajustes/on_apagado.png");
        regionMenuOnon=cargarImagen("Imagenes/Ajustes/on_encendido.png");
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

        spritebtnOff=cargarSprite(ControlJuego.ANCHO_CAMARA /2 +155, ControlJuego.ALTO_CAMARA / 2-45, regionMenuOffoff);
        attachChild(spritebtnOff);

        spritebtnOn=cargarSprite(ControlJuego.ANCHO_CAMARA / 2+45, ControlJuego.ALTO_CAMARA / 2-45, regionMenuOnon);
        attachChild(spritebtnOn);

        Sprite spriteOff = new Sprite(ControlJuego.ANCHO_CAMARA / 2 + 155,ControlJuego.ALTO_CAMARA / 2 - 45,
                regionMenuOffoff, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    spritebtnOff.detachSelf();
                    spritebtnOn.detachSelf();

                    spritebtnOff=cargarSprite(ControlJuego.ANCHO_CAMARA /2 +155, ControlJuego.ALTO_CAMARA / 2-45, regionMenuOffon);
                    attachChild(spritebtnOff);

                    spritebtnOn=cargarSprite(ControlJuego.ANCHO_CAMARA / 2+45, ControlJuego.ALTO_CAMARA / 2-45, regionMenuOnoff);
                    attachChild(spritebtnOn);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteOff);
        registerTouchArea(spriteOff);


        Sprite spriteOn = new Sprite(ControlJuego.ANCHO_CAMARA / 2+45, ControlJuego.ALTO_CAMARA / 2-45,
                regionMenuOnon, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    spritebtnOff.detachSelf();
                    spritebtnOn.detachSelf();

                    spritebtnOff=cargarSprite(ControlJuego.ANCHO_CAMARA /2 +155, ControlJuego.ALTO_CAMARA / 2-45, regionMenuOffoff);
                    attachChild(spritebtnOff);

                    spritebtnOn=cargarSprite(ControlJuego.ANCHO_CAMARA / 2+45, ControlJuego.ALTO_CAMARA / 2-45, regionMenuOnon);
                    attachChild(spritebtnOn);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteOn);
        registerTouchArea(spriteOn);
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
