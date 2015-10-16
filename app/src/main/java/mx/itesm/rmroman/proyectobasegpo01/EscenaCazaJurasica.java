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

    private ITextureRegion regionFondo;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionEnemigo;

    private boolean personajeSaltando = false;


    private AnalogOnScreenControl control;
    private AnalogOnScreenControl controlDos;

    private Jugador spritePersonaje;
    private Jugador zombraSpritePersonaje;
    private Enemigo spriteEnemigo;

    private TiledTextureRegion regionPersonajeAnimado;


    // Sprite para el fondo
    private Sprite spriteFondo;

    private ButtonSprite btnSaltar;

    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionBase=cargarImagen("Imagenes/baseJoystick.png");
        regionControlSalto=cargarImagen("Imagenes/joystick.png");
        regionEnemigo=cargarImagen("Imagenes/alienblaster.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 600, 158, 1, 4);


    }

    @Override
    public void crearEscena() {

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        spritePersonaje = new Jugador(-ControlJuego.ANCHO_CAMARA/4+ControlJuego.ANCHO_CAMARA, ControlJuego.ALTO_CAMARA/4,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

        spriteEnemigo = new Enemigo(200, 200,regionEnemigo, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteEnemigo);

        admMusica.cargarMusica(2);

        agregarJoystick();
        agregarBotonSalto();

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


    private void agregarJoystick() {
        control = new AnalogOnScreenControl(100, 100, actividadJuego.camara,
                regionBase, regionControlSalto,
                0.03f, 100, actividadJuego.getVertexBufferObjectManager(), new AnalogOnScreenControl.IAnalogOnScreenControlListener() {
            @Override
            public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
            }
            @Override
            public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {

                pValueX=pValueX*(-1);
                float x = (spriteFondo.getX() + 10 * pValueX);

                if (x > 3800 || x < 0) {
                    x = spriteFondo.getX();
                }
                spriteFondo.setX(x);
            }

        });
        EscenaCazaJurasica.this.setChildScene(control);
    }

    private void agregarBotonSalto() {
        btnSaltar = new ButtonSprite(1100,100,
                regionControlSalto,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown() && !personajeSaltando) {
                    personajeSaltando = true;
                    // Animar sprite central
                    JumpModifier salto = new JumpModifier(1, spritePersonaje.getX(), spritePersonaje.getX(),
                            spritePersonaje.getY(), spritePersonaje.getY(),-200);
                    RotationModifier rotacion = new RotationModifier(1, 360, 0);
                    ParallelEntityModifier paralelo = new ParallelEntityModifier(salto,rotacion)
                    {
                        @Override
                        protected void onModifierFinished(IEntity pItem) {
                            personajeSaltando = false;
                            unregisterEntityModifier(this);
                            super.onModifierFinished(pItem);
                        }
                    };
                    spritePersonaje.registerEntityModifier(paralelo);
                }

                if (pSceneTouchEvent.isActionDown()) {
                    // El usuario toca la pantalla
                    float x = pSceneTouchEvent.getX();
                    float y = pSceneTouchEvent.getY();
                    spritePersonaje.setPosition(x, y);
                }
                if (pSceneTouchEvent.isActionMove()) {
                    // El usuario mueve el dedo sobre la pantalla
                    float x = pSceneTouchEvent.getX();
                    float y = pSceneTouchEvent.getY();
                    spritePersonaje.setPosition(x, y);
                }
                if (pSceneTouchEvent.isActionUp()) {
                    // El usuario deja de tocar la pantalla
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(btnSaltar);
        attachChild(btnSaltar);
    }


}