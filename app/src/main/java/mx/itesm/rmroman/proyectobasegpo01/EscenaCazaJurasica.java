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
    private ITextureRegion regionVida;


    private ITextureRegion vidas;
    private Sprite[] spriteVidas;

    private boolean personajeSaltando = false;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Enemigo spriteEnemigo;
    private Enemigo spriteVida;

    private boolean juegoCorriendo = true;


    private CameraScene escenaPausa;
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondoPausa;

    private ButtonSprite btnSaltar;
    private int vida;

    @Override
    public void cargarRecursos() {

        vida=2;
        vidas=cargarImagen("Imagenes/corazon.png");
        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionFondoPausa = cargarImagen("Imagenes/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/joystick.png");
        regionEnemigo = cargarImagen("Imagenes/vacaDinosaurio.png");
        regionVida = cargarImagen("Imagenes/vacaDinosaurio.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 590, 138, 1, 4);
        // Pausa


        regionBtnPausa = cargarImagen("Imagenes/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/pausa.png");

    }

    @Override
    public void crearEscena() {

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        spriteVidas= new Sprite[3];

        spriteVidas[0]= cargarSprite(900, 750, vidas);
        attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(950, 750, vidas);
        attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1000, 750, vidas);
        attachChild(spriteVidas[2]);

        spritePersonaje = new Jugador(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/4,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

        spriteEnemigo = new Enemigo(200, 200,regionEnemigo, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteEnemigo);

        spriteVida = new Enemigo(1800, 200,regionVida, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteVida);

        admMusica.cargarMusica(2);

        agregarJoystick();
        agregarBotonSalto();

        Sprite btnPausa = new Sprite(regionBtnPausa.getWidth(), ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight(),
                regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnPausa);
        registerTouchArea(btnPausa);

        // Crear la escena de PAUSA, pero NO lo agrega a la escena
        escenaPausa = new CameraScene(actividadJuego.camara);
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2,
                regionPausa);
        escenaPausa.attachChild(fondoPausa);
        escenaPausa.setBackgroundEnabled(false);


    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
    }


    private void pausarJuego() {
        if (juegoCorriendo) {
            escenaPausa.setChildScene(control);
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        } else {
            clearChildScene();
            EscenaCazaJurasica.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_CAZA_JURASICA;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        Log.i("tienes vidas",vida+"");

        if (!juegoCorriendo) {
            return;
        }

        if(spritePersonaje.collidesWith(spriteEnemigo)){

            spriteEnemigo.detachSelf();
            spriteVidas[2-vida].detachSelf();
            vida--;
            admMusica.vibrar(200);
        }

        if(spritePersonaje.collidesWith(spriteVida)){

            spriteVida.detachSelf();

            if(vida<2){

                if(vida==0){
                    spriteVidas[1]= cargarSprite(950, 750, vidas);
                    attachChild(spriteVidas[1]);
                    vida++;
                }
                else if(vida==1){
                    spriteVidas[0]= cargarSprite(900, 750, vidas);
                    attachChild(spriteVidas[0]);
                    vida++;
                }
            }
            admMusica.vibrar(200);
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
                    float x = spriteFondo.getX() + 29 * pValueX;
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
                                spritePersonaje.getY(), spritePersonaje.getY(), -400);
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