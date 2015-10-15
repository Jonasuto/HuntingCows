package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
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
    private ITextureRegion regionControlSalto;

    private boolean personajeSaltando = false;


    private Jugador spritePersonaje;

    private TiledTextureRegion regionPersonajeAnimado;


    // Sprite para el fondo
    private Sprite spriteFondo;

    private ButtonSprite spriteFlechaIzquierda;
    private ButtonSprite spriteFlechaDerecha;
    private ButtonSprite btnSaltar;

    private AnalogOnScreenControl controlFlechas;
    private AnalogOnScreenControl controlSalta;

    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionFlecha=cargarImagen("Imagenes/Historia/flecha.png");
        regionControlSalto=cargarImagen("Imagenes/joystick.png");

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


        spriteFlechaIzquierda = new ButtonSprite(90,100,
                regionFlecha,actividadJuego.getVertexBufferObjectManager()) {


            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {


                if(pSceneTouchEvent.isActionDown()){

                    spritePersonaje.moverIzquierda();
                }
                else if(pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel() || pTouchAreaLocalX<20 || pTouchAreaLocalX >115 || pTouchAreaLocalY>70 || pTouchAreaLocalY<20){

                    spritePersonaje.detener();
                }

                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(spriteFlechaIzquierda);
        attachChild(spriteFlechaIzquierda);





        spriteFlechaDerecha = new ButtonSprite(220,100,
                regionFlecha,actividadJuego.getVertexBufferObjectManager()) {


            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {


                if(pSceneTouchEvent.isActionDown()){
                    //Log.i("Estoy bien aqui eje x= ", pTouchAreaLocalX + " eje y= "+pTouchAreaLocalY);
                    spritePersonaje.moverDerecha();
                }
                else if(pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel() || pTouchAreaLocalX<35 || pTouchAreaLocalX >110 || pTouchAreaLocalY>70 || pTouchAreaLocalY<20){
                    spritePersonaje.detener();
                }

                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(spriteFlechaDerecha);
        attachChild(spriteFlechaDerecha);






        btnSaltar = new ButtonSprite(900,100,
                regionControlSalto,actividadJuego.getVertexBufferObjectManager()) {


            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {


                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(btnSaltar);
        attachChild(btnSaltar);

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