package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.adt.color.Color;


/**
 * Created by Campos on 11/10/15.
 */
public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionEnemigo;
    private ITextureRegion regionMercancia;

    private ITextureRegion[] vidas;

    private boolean personajeSaltando = false;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Enemigo spriteEnemigo;
    private Mercancia spriteMercancia;

    private boolean juegoCorriendo = true;

    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondoPausa;

    private ButtonSprite btnSaltar;

    @Override
    public void cargarRecursos() {


        
        vidas = new ITextureRegion[4];
        vidas[0]=cargarImagen("Imagenes/corazon.png");
        vidas[1] = cargarImagen("Imagenes/corazon.png");
        vidas[2] = cargarImagen("Imagenes/corazon.png");
        vidas[3] = cargarImagen("Imagenes/corazon.png");

        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionFondoPausa = cargarImagen("Imagenes/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/joystick.png");
        regionEnemigo = cargarImagen("Imagenes/vacaDinosaurio.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 600, 158, 1, 4);
        regionMercancia=cargarImagen("Imagenes/corazon.png");

        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/pausa.png");

    }

    @Override
    public void crearEscena() {

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        spritePersonaje = new Jugador(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/4,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

        spriteEnemigo = new Enemigo(200, 200,regionEnemigo, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteEnemigo);

        spriteMercancia = new Mercancia(700, 200,regionMercancia, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteMercancia);

        admMusica.cargarMusica(2);

        agregarJoystick();
        agregarBotonSalto();

        Sprite btnPausa = new Sprite(regionBtnPausa.getWidth()/2, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2,
                regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                //Log.i("onAreaTouched", "BTN Touch");
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        btnPausa.setAlpha(0.4f);
        attachChild(btnPausa);
        registerTouchArea(btnPausa);


    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
    }

    private void pausarJuego() {
        //Log.i("pausarJuego", "pausando");
        if (juegoCorriendo) {
            spriteFondoPausa = cargarSprite(ControlJuego.
                    ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondoPausa);
            spriteFondoPausa.setAlpha(0.4f);
            attachChild(spriteFondoPausa);
            juegoCorriendo = false;
        } else {
            spriteFondoPausa.detachSelf();
            juegoCorriendo = true;
        }
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_CAZA_JURASICA;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }

        if(spritePersonaje.collidesWith(spriteEnemigo)){
            admMusica.vibrar(1000);
        }
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

                if(juegoCorriendo){
                    pValueX=pValueX*(-1);
                    float x = spriteFondo.getX() + 20 * pValueX;
                    spriteFondo.setX(x);
                }
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

                if (juegoCorriendo) {
                    if (pSceneTouchEvent.isActionDown() && !personajeSaltando) {
                        personajeSaltando = true;
                        // Animar sprite central
                        JumpModifier salto = new JumpModifier(1, spritePersonaje.getX(), spritePersonaje.getX(),
                                spritePersonaje.getY(), spritePersonaje.getY(), -300);
                        RotationModifier rotacion = new RotationModifier(1, 360, 0);
                        ParallelEntityModifier paralelo = new ParallelEntityModifier(salto, rotacion) {
                            @Override
                            protected void onModifierFinished(IEntity pItem) {
                                personajeSaltando = false;
                                unregisterEntityModifier(this);
                                super.onModifierFinished(pItem);
                            }
                        };
                        spritePersonaje.registerEntityModifier(paralelo);
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(btnSaltar);
        attachChild(btnSaltar);
    }
}