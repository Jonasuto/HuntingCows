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

import java.util.ArrayList;


/**
 * Created by Campos on 11/10/15.
 */
public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionNave;

    private ArrayList<Sprite> listaProyectilesEnemigo;


    private float[] posicionesEnemigos;

    private ArrayList<Enemigo> listaEnemigos;

    private ITextureRegion regionEnemigo;
    private ITextureRegion regionBala;
    private ITextureRegion regionVida;


    private boolean[] brincosEnemigos;

    private ITextureRegion vidas;
    private Sprite[] spriteVidas;

    private Sprite spriteNave;

    private boolean personajeSaltando = false;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Enemigo spriteEnemigo;
    private Vida spriteVida;

    private boolean juegoCorriendo = true;


    private CameraScene escenaPausa;
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondoPausa;

    private ButtonSprite btnSaltar;
    private ButtonSprite btnDisparar;
    private int vida;

    @Override
    public void cargarRecursos() {

        vida=2;
        vidas=cargarImagen("Imagenes/corazon.png");
        regionNave=cargarImagen("Imagenes/nave.png");
        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionFondoPausa = cargarImagen("Imagenes/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/joystick.png");
        regionEnemigo = cargarImagen("Imagenes/vacaDinosaurio.png");
        regionBala = cargarImagen("Imagenes/laser.png");
        regionVida = cargarImagen("Imagenes/corazon.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 590, 138, 1, 4);
        // Pausa

        brincosEnemigos=new boolean[9];
        brincosEnemigos[0]=false;
        brincosEnemigos[3]=false;
        brincosEnemigos[6]=false;
        brincosEnemigos[7]=false;


            regionBtnPausa = cargarImagen("Imagenes/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/pausa.png");

    }

    @Override
    public void crearEscena() {

        listaEnemigos = new ArrayList<>();

        listaProyectilesEnemigo= new ArrayList<>();

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        spriteVidas= new Sprite[3];

        spriteNave=cargarSprite(200, 270, regionNave);
        spriteFondo.attachChild(spriteNave);

        spriteVidas[0]= cargarSprite(900, 750, vidas);
        attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(950, 750, vidas);
        attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1000, 750, vidas);
        attachChild(spriteVidas[2]);

        spritePersonaje = new Jugador(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/4,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

        spriteVida = new Vida(1800, 200,regionVida, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteVida);

        admMusica.cargarMusica(2);

        posicionarEnemigos();

        agregarJoystick();
        agregarBotonSalto();
        agregarBotonDisparar();

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




        if (!juegoCorriendo) {
            return;
        }

        for (int i= listaEnemigos.size()-1; i>=0; i--) {
            Enemigo enemigo = listaEnemigos.get(i);

            enemigo.mover();

            if(i==0 || i==3 || i==6 || i==7){

                final int subindice=i;

                if (brincosEnemigos[i]==false) {
                    brincosEnemigos[i] = true;
                    // Animar sprite central
                    JumpModifier salto = new JumpModifier(1, enemigo.getX(), enemigo.getX(),
                            enemigo.getY(), enemigo.getY(), -400);
                    ParallelEntityModifier paralelo = new ParallelEntityModifier(salto) {
                        @Override
                        protected void onModifierFinished(IEntity pItem) {
                            brincosEnemigos[subindice] = false;
                            unregisterEntityModifier(this);
                            super.onModifierFinished(pItem);
                        }
                    };
                    enemigo.registerEntityModifier(paralelo);
                }
            }
            else{
                enemigo.mover();
            }


            if (spritePersonaje.collidesWith(enemigo)) {
                enemigo.detachSelf();
                spriteVidas[2-vida].detachSelf();
                vida--;
                listaEnemigos.remove(enemigo);
                admMusica.vibrar(200);
                spritePersonaje.setSize(spritePersonaje.getWidth()-20,spritePersonaje.getHeight()-20);
            }

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

                    Log.i("estoy",spriteFondo.getX()+"");

                    if(spriteFondo.getX()>1070){
                        if(pValueX<0){
                        }
                        else{
                            pValueX = pValueX * (-1);
                            float x = spriteFondo.getX() + 30 * pValueX;
                            spriteFondo.setX(x);
                        }
                    }

                    else{
                        pValueX = pValueX * (-1);
                        float x = spriteFondo.getX() + 30 * pValueX;
                        spriteFondo.setX(x);
                    }

                }
            }

        });
        EscenaCazaJurasica.this.setChildScene(control);
    }


    private void posicionarEnemigos(){

        posicionesEnemigos= new float[10];
        posicionesEnemigos[0]=0 ;
        posicionesEnemigos[1]=-30 ;
        posicionesEnemigos[2]=-80 ;
        posicionesEnemigos[3]=1800 ;
        posicionesEnemigos[4]=1900 ;
        posicionesEnemigos[5]=2000 ;
        posicionesEnemigos[6]=2600 ;
        posicionesEnemigos[7]=3000 ;
        posicionesEnemigos[8]=3400 ;
        posicionesEnemigos[9]=4000 ;


        int cont;
        for( cont = 0; cont<posicionesEnemigos.length;cont++){

            Enemigo spriteEnemigo = new Enemigo(posicionesEnemigos[cont], 200,regionEnemigo, actividadJuego.getVertexBufferObjectManager());

            Enemigo nuevoEnemigo = spriteEnemigo;
            listaEnemigos.add(nuevoEnemigo);
            spriteFondo.attachChild(nuevoEnemigo);

        }
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


    private void agregarBotonDisparar() {
        btnDisparar = new ButtonSprite(1190,200,
                regionControlSalto,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (juegoCorriendo) {
                    //dispararProyectil();

                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(btnDisparar);
        attachChild(btnDisparar);
    }


    /*
    private void dispararProyectil() {
        // Crearlo
        Sprite spriteProyectil = cargarSprite(spritePersonaje.getX(), spritePersonaje.getY(), regionProyectil);
        attachChild(spriteProyectil);   // Lo agrega a la escena
        listaProyectiles.add(spriteProyectil);  // Lo agrega a la lista
    }
    */



}