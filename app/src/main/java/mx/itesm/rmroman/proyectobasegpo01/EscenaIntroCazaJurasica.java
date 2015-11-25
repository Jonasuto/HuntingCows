package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import java.util.ArrayList;

/**
 * Created by Campos on 24/10/15.
 */
public class EscenaIntroCazaJurasica extends EscenaBase {


    private ITextureRegion regionFondo;
    private ITextureRegion regionBase;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionHoyoNegro;
    private AnalogOnScreenControl control;
    private boolean juegoCorriendo = true;

    private CameraScene escenaPausa;
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;

    private Sprite spriteFondo;
    private Sprite btnDisparar;

    private Jugador spriteNaveParado;
    private Jugador spriteNaveActual;

    private TiledTextureRegion regionNavenimadoParado;

    private boolean personajeParado=true;

    private Sprite spriteHoyoNegro;

    private Sprite spriteFondoPausa;

    private ArrayList<Laser> listaProyectiles;
    private ITextureRegion regionProyectil;



    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("Imagenes/fondo_hunting.jpg");
        regionFondoPausa = cargarImagen("Imagenes/Logos/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/Roman/joystick.png");
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/Ajustes/pausa.png");
        regionHoyoNegro = cargarImagen("Imagenes/Planetas/planeta_verde.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
        regionNavenimadoParado = cargarImagenMosaico("Imagenes/Roman/naveStand.png", 800, 267, 1, 2);
    }

    @Override
    public void crearEscena() {
        listaProyectiles = new ArrayList<>();
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteNaveParado = new Jugador((ControlJuego.ANCHO_CAMARA/2)-200, (ControlJuego.ALTO_CAMARA/4)-20,regionNavenimadoParado, actividadJuego.getVertexBufferObjectManager());
        spriteNaveParado.animate(30);

        spriteNaveActual=spriteNaveParado;
        attachChild(spriteNaveActual);

        spriteHoyoNegro= cargarSprite(1100, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        admMusica.cargarMusica(2);

        agregarJoystick();
        agregarBotonDisparar();

        Sprite btnPausa = new Sprite(100, 710,
                regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                    if(admMusica.getMusicaEncendida()==true) {
                        admMusica.reproduceio();
                    }
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
        pausarJuego();
    }

    private void pausarJuego() {
        if (juegoCorriendo) {
            escenaPausa.setChildScene(control);
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        } else {
            clearChildScene();
            EscenaIntroCazaJurasica.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_INTRO_CAZA_JURASICA;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }

        if(spriteNaveActual.collidesWith(spriteHoyoNegro)){
            admEscenas.liberarEscenaIntroCazaJurasica();
            admEscenas.crearEscenaHistoriaCazaJurasica();
            admEscenas.setEscena(TipoEscena.ESCENA_HISTORIA_CAZA_JURASICA);
        }


        actualizarProyectiles(pSecondsElapsed);

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


                    if(spriteNaveActual.getX()>1200){
                        if(pValueX>0){
                        }
                        else{
                            float x = spriteNaveActual.getX() + 15 * pValueX;
                            spriteNaveActual.setX(x);
                        }
                    } else if(spriteNaveActual.getX()<160){
                        if(pValueX<0){
                        }
                        else{
                            float x = spriteNaveActual.getX() + 15 * pValueX;
                            spriteNaveActual.setX(x);
                        }
                    }

                    else if(spriteNaveActual.getY()<0){
                        if(pValueY<0){
                        }
                        else{
                            float y = spriteNaveActual.getY() + 15 * pValueY;
                            spriteNaveActual.setY(y);
                        }
                    }

                    else if(spriteNaveActual.getY()>800){
                        if(pValueY>0){
                        }
                        else{
                            float y = spriteNaveActual.getY() + 15 * pValueY;
                            spriteNaveActual.setY(y);
                        }
                    }

                    else{
                        float x = spriteNaveActual.getX() + 15 * pValueX;
                        float y = spriteNaveActual.getY() + 15 * pValueY;
                        spriteNaveActual.setPosition(x,y);
                    }
                }
            }

        });
        EscenaIntroCazaJurasica.this.setChildScene(control);
    }


    private void agregarBotonDisparar() {
        btnDisparar = new ButtonSprite(1190,200,
                regionControlSalto,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (juegoCorriendo) {
                    if(pSceneTouchEvent.isActionDown()){

                        dispararProyectil();
                        admMusica.vibrar(100);
                        admMusica.reproducirMusicaBoton();
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(btnDisparar);
        attachChild(btnDisparar);
    }

    private void actualizarProyectiles(float tiempo) {

        // Se visita cada proyectil dentro de la lista, se recorre con el índice
        // porque se pueden borrar datos
        for (int i = listaProyectiles.size() - 1; i>=0; i--) {
            Laser proyectil = listaProyectiles.get(i);
            proyectil.mover();

            if (proyectil.getX() > ControlJuego.ANCHO_CAMARA) {
                detachChild(proyectil);
                listaProyectiles.remove(proyectil);
                continue;
            }
        }
    }

    private void dispararProyectil() {
        // Crearlo
        Laser spriteProyectil = new Laser(spriteNaveActual.getX(),  spriteNaveActual.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),true,false);
        attachChild(spriteProyectil);   // Lo agrega a la escena
        listaProyectiles.add(spriteProyectil);  // Lo agrega a la lista
    }

}
