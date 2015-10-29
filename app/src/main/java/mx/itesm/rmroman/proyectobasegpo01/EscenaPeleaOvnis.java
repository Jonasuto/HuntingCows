package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;

/**
 * Created by Campos on 24/10/15.
 */
public class EscenaPeleaOvnis extends EscenaBase {


    private ITextureRegion regionFondo;
    private ITextureRegion regionNave;
    private ITextureRegion regionBase;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionHoyoNegro;
    private Sprite spriteNave;
    private AnalogOnScreenControl control;
    private boolean juegoCorriendo = true;


    private CameraScene escenaPausa;
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;

    private Sprite spriteFondo;
    private Sprite btnDisparar;


    private Sprite spriteHoyoNegro;

    private Sprite spriteFondoPausa;

    private ArrayList<Laser> listaProyectiles;
    private ITextureRegion regionProyectil;



    @Override
    public void cargarRecursos() {
        regionNave=cargarImagen("Imagenes/Roman/nave2.png");
        regionFondo = cargarImagen("Imagenes/fondo_hunting.jpg");
        regionFondoPausa = cargarImagen("Imagenes/Logos/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/Roman/joystick.png");
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/Ajustes/pausa.png");
        regionHoyoNegro = cargarImagen("Imagenes/MenuInicio/botonesMenu/boton_verdeplaneta.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
    }

    @Override
    public void crearEscena() {
        listaProyectiles = new ArrayList<>();
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteNave=cargarSprite(100, 270, regionNave);
        spriteFondo.attachChild(spriteNave);

        spriteHoyoNegro= cargarSprite(1100, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        admMusica.cargarMusica(2);

        agregarJoystick();
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
        pausarJuego();
    }

    private void pausarJuego() {
        if (juegoCorriendo) {
            escenaPausa.setChildScene(control);
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        } else {
            clearChildScene();
            EscenaPeleaOvnis.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_INTRO_CAZA_JURASICA;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }

        Log.i("estoy",spriteNave.getX()+"   "+spriteNave.getY());


        if(spriteNave.collidesWith(spriteHoyoNegro)){
            admEscenas.liberarEscenaIntroCazaJurasica();
            admEscenas.crearEscenaCazaJurasica();
            admEscenas.setEscena(TipoEscena.ESCENA_CAZA_JURASICA);
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


                    if(spriteNave.getX()>1200){
                        if(pValueX>0){
                        }
                        else{
                            float x = spriteNave.getX() + 15 * pValueX;
                            spriteNave.setX(x);
                        }
                    } else if(spriteNave.getX()<160){
                        if(pValueX<0){
                        }
                        else{
                            float x = spriteNave.getX() + 15 * pValueX;
                            spriteNave.setX(x);
                        }
                    }

                    else if(spriteNave.getY()<0){
                        if(pValueY<0){
                        }
                        else{
                            float y = spriteNave.getY() + 15 * pValueY;
                            spriteNave.setY(y);
                        }
                    }

                    else if(spriteNave.getY()>800){
                        if(pValueY>0){
                        }
                        else{
                            float y = spriteNave.getY() + 15 * pValueY;
                            spriteNave.setY(y);
                        }
                    }

                    else{
                        float x = spriteNave.getX() + 15 * pValueX;
                        float y = spriteNave.getY() + 15 * pValueY;
                        spriteNave.setPosition(x,y);
                    }
                }
            }

        });
        EscenaPeleaOvnis.this.setChildScene(control);
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
        Laser spriteProyectil = new Laser(spriteNave.getX(),  spriteNave.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),true);
        attachChild(spriteProyectil);   // Lo agrega a la escena
        listaProyectiles.add(spriteProyectil);  // Lo agrega a la lista
    }

}
