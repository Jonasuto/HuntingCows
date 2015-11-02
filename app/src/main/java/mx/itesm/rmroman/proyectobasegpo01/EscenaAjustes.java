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
    private ITextureRegion regionMenuMusica;
    private ITextureRegion regionMenuContinuar;
    private ITextureRegion regionIrAMenu;
    private ITextureRegion regionOvni;

    private boolean musicaEncendida;

    private Sprite spriteMenuPausa;
    private Sprite spriteFondo; //(el fondo de la escena, estático)
    private Sprite spritebtnOn;
    private Sprite spritebtnOff;
    private Sprite spritebtnMusica;
    private Sprite spritebtnContinuar;
    private Sprite spritebtnIrAMenu;

    private int cambiar=0;


    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("Imagenes/MenuInicio/fondoMenu.jpg");

        regionMenuPausa = cargarImagen("Imagenes/Ajustes2/pausa_fondo.png");
        regionMenuOffoff=cargarImagen("Imagenes/Ajustes2/music_off_OFF.png");
        regionMenuOffon=cargarImagen("Imagenes/Ajustes2/music_off_ON.png");
        regionMenuOnoff=cargarImagen("Imagenes/Ajustes2/music_on_OFF.png");
        regionMenuOnon=cargarImagen("Imagenes/Ajustes2/music_on_ON.png");
        regionIrAMenu=cargarImagen("Imagenes/Ajustes2/pausa_menu.png");
        regionMenuMusica=cargarImagen("Imagenes/Ajustes2/pausa_musica.png");
        regionMenuContinuar=cargarImagen("Imagenes/Ajustes2/pausa_continue.png");

        regionOvni= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/naveVaca.png");

    }

    @Override
    public void crearEscena() {


        musicaEncendida=admMusica.getMusicaEncendida();

        // Creamos el sprite de manera óptima
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);

        // Crea el fondo de la pantalla
        SpriteBackground fondo = new SpriteBackground(1,1,1, spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        spriteMenuPausa=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionMenuPausa);
        attachChild(spriteMenuPausa);

        spritebtnMusica=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2-45, regionMenuMusica);
        attachChild(spritebtnMusica);

        spritebtnContinuar=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2+300, regionMenuContinuar);
        attachChild(spritebtnContinuar);

        spritebtnIrAMenu=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2-300, regionIrAMenu);
        attachChild(spritebtnIrAMenu);

        if (musicaEncendida == true) {
            spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffoff);
            attachChild(spritebtnOff);

            spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnon);
            attachChild(spritebtnOn);
        }

        else {
            spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffon);
            attachChild(spritebtnOff);

            spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnoff);
            attachChild(spritebtnOn);
        }




        Sprite spriteOffFinal = new Sprite(ControlJuego.ANCHO_CAMARA / 2 + 155,ControlJuego.ALTO_CAMARA / 2 - 45,
                regionMenuOffoff, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {


                    if(musicaEncendida==true && cambiar==0){


                        spritebtnOff.detachSelf();
                        spritebtnOn.detachSelf();

                        admMusica.vibrar(100);

                        admMusica.setMusicaEncendida(false,-1);
                        musicaEncendida=false;

                        cambiar=1;
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        spriteOffFinal.setAlpha(0.2f);
        attachChild(spriteOffFinal);
        registerTouchArea(spriteOffFinal);


        Sprite spriteOnFinal = new Sprite(ControlJuego.ANCHO_CAMARA / 2+45, ControlJuego.ALTO_CAMARA / 2-45,
                regionMenuOnoff, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    if(musicaEncendida==false && cambiar==0){

                        admMusica.vibrar(100);

                        spritebtnOn.detachSelf();
                        spritebtnOff.detachSelf();

                        admMusica.setMusicaEncendida(true,-1);
                        musicaEncendida=true;

                        cambiar=1;
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        spriteOnFinal.setAlpha(0.2f);
        attachChild(spriteOnFinal);
        registerTouchArea(spriteOnFinal);
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

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);


        if(cambiar==1) {

            if (musicaEncendida == true) {
                spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffoff);
                attachChild(spritebtnOff);

                spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnon);
                attachChild(spritebtnOn);
            } else {
                spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffon);
                attachChild(spritebtnOff);

                spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnoff);
                attachChild(spritebtnOn);
            }


            cambiar=0;
        }

    }

}
