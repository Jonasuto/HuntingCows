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

    private ITextureRegion regionControlDer;
    private ITextureRegion regionControlIz;
    private ITextureRegion regionControlUp;
    private ITextureRegion regionControlDown;


    private CameraScene escenaPausa;
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;

    private Sprite spriteFondo;
    private Sprite btnDisparar;

    private Sprite der;
    private Sprite iz;
    private Sprite up;
    private Sprite down;

    private Jugador spriteNaveParado;
    private Jugador spriteNaveActual;

    private int cambiar=0;

    private TiledTextureRegion regionNavenimadoParado;

    private boolean personajeParado=true;

    private Sprite spriteHoyoNegro;

    private Sprite spriteFondoPausa;

    private ArrayList<Laser> listaProyectiles;
    private ITextureRegion regionProyectil;

    private boolean musicaEncendida;
    private Sprite spritebtnOff;
    private Sprite spritebtnOn;

    private ITextureRegion regionMenuPausa;
    private ITextureRegion regionMenuOffoff;
    private ITextureRegion regionMenuOffon;
    private ITextureRegion regionMenuOnoff;
    private ITextureRegion regionMenuOnon;
    private ITextureRegion regionMenuMusica;
    private ITextureRegion regionMenuContinuar;
    private ITextureRegion regionIrAMenu;
    private Sprite spriteMenuPausa;
    private ITextureRegion regionDisparar;
    private Sprite spritebtnMusica;
    private Sprite spritebtnContinuar;
    private Sprite spritebtnIrAMenu;


    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("Imagenes/fondo_hunting.jpg");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto.setTextureSize(1,1);
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/Ajustes2/pausa_fondo.png");
        regionHoyoNegro = cargarImagen("Imagenes/Planetas/planeta_verde.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
        regionDisparar=cargarImagen("Imagenes/Roman/boton_fuego.png");
        regionNavenimadoParado = cargarImagenMosaico("Imagenes/Roman/naveStand.png", 800, 267, 1, 2);
        regionControlDer=cargarImagen("Imagenes/Joystick/derecha_joystick.png");
        regionControlIz=cargarImagen("Imagenes/Joystick/izquiera_joystick.png");
        regionControlUp=cargarImagen("Imagenes/Joystick/arriba_joystick.png");
        regionControlDown=cargarImagen("Imagenes/Joystick/abajo_joystick.png");

        regionMenuPausa = cargarImagen("Imagenes/Ajustes2/pausa_fondo.png");
        regionMenuOffoff=cargarImagen("Imagenes/Ajustes2/music_off_OFF.png");
        regionMenuOffon=cargarImagen("Imagenes/Ajustes2/music_off_ON.png");
        regionMenuOnoff=cargarImagen("Imagenes/Ajustes2/music_on_OFF.png");
        regionMenuOnon=cargarImagen("Imagenes/Ajustes2/music_on_ON.png");
        regionIrAMenu=cargarImagen("Imagenes/Ajustes2/pausa_menu.png");
        regionMenuMusica=cargarImagen("Imagenes/Ajustes2/pausa_musica.png");
        regionMenuContinuar=cargarImagen("Imagenes/Ajustes2/pausa_continue.png");
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

        //admMusica.cargarMusica(2);

        agregarJoystick();


        //original 120,115
        der = cargarSprite(190, 115, regionControlDer);
        control.attachChild(der);

        iz = cargarSprite(50, 115, regionControlIz);
        control.attachChild(iz);

        up = cargarSprite(120, 185, regionControlUp);
        control.attachChild(up);

        down = cargarSprite(120, 45, regionControlDown);
        control.attachChild(down);

        Sprite btnPausa = new Sprite(100, 710,
                regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                    /*if(admMusica.getMusicaEncendida()==true) {
                        admMusica.reproduceio();
                    }*/
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnPausa);
        registerTouchArea(btnPausa);

        musicaEncendida=true;
        //musicaEncendida=admMusica.getMusicaEncendida();

        // Crear la escena de PAUSA, pero NO lo agrega a la escena
        escenaPausa = new CameraScene(actividadJuego.camara);
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2,
                regionMenuPausa);
        escenaPausa.attachChild(fondoPausa);
        escenaPausa.setBackgroundEnabled(false);

        spriteMenuPausa=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionMenuPausa);
        escenaPausa.attachChild(spriteMenuPausa);

        spritebtnMusica=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2-45, regionMenuMusica);
        escenaPausa.attachChild(spritebtnMusica);

        spritebtnContinuar = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2 + 300,
                regionMenuContinuar, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    onBackKeyPressed();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(spritebtnContinuar);
        registerTouchArea(spritebtnContinuar);

        spritebtnIrAMenu = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2-300,
                regionIrAMenu, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    admEscenas.liberarEscenaIntroCazaJurasica();
                    admEscenas.crearEscenaMenu();
                    admEscenas.setEscena(TipoEscena.ESCENA_MENU);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(spritebtnIrAMenu);
        registerTouchArea(spritebtnIrAMenu);

        if (musicaEncendida == true) {
            spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffoff);
            escenaPausa.attachChild(spritebtnOff);

            spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnon);
            escenaPausa.attachChild(spritebtnOn);
        }

        else {
            spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffon);
            escenaPausa.attachChild(spritebtnOff);

            spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnoff);
            escenaPausa.attachChild(spritebtnOn);
        }

        Sprite spriteOffFinal = new Sprite(ControlJuego.ANCHO_CAMARA / 2 + 155,ControlJuego.ALTO_CAMARA / 2 - 45,
                regionMenuOffoff, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {


                    if(musicaEncendida==true && cambiar==0){


                        spritebtnOff.detachSelf();
                        spritebtnOn.detachSelf();

                        //admMusica.vibrar(100);

                        //admMusica.setMusicaEncendida(false,2);
                        musicaEncendida=false;

                        cambiar=1;
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        spriteOffFinal.setAlpha(0.2f);
        escenaPausa.attachChild(spriteOffFinal);
        registerTouchArea(spriteOffFinal);



        Sprite spriteOnFinal = new Sprite(ControlJuego.ANCHO_CAMARA / 2+45, ControlJuego.ALTO_CAMARA / 2-45,
                regionMenuOnoff, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    if(musicaEncendida==false && cambiar==0){

                        //admMusica.vibrar(100);

                        spritebtnOn.detachSelf();
                        spritebtnOff.detachSelf();

                        //admMusica.setMusicaEncendida(true,2);
                        musicaEncendida=true;

                        cambiar=1;
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        spriteOnFinal.setAlpha(0.2f);
        escenaPausa.attachChild(spriteOnFinal);
        registerTouchArea(spriteOnFinal);
    }

    @Override
    public void onBackKeyPressed() {

        pausarJuego();
        /*if(admMusica.getMusicaEncendida()==true) {
            admMusica.reproduceio();
        }*/
    }

    private void pausarJuego() {
        if (juegoCorriendo) {
            escenaPausa.setChildScene(control);
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        }

        else {
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
            if(cambiar==1) {
                if (musicaEncendida == true) {
                    spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffoff);
                    escenaPausa.attachChild(spritebtnOff);

                    spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnon);
                    escenaPausa.attachChild(spritebtnOn);
                } else {
                    spritebtnOff = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 155, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOffon);
                    escenaPausa.attachChild(spritebtnOff);

                    spritebtnOn = cargarSprite(ControlJuego.ANCHO_CAMARA / 2 + 45, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuOnoff);
                    escenaPausa.attachChild(spritebtnOn);
                }
                cambiar=0;
            }
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
        //admMusica.liberarMusica();
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);
        regionFondo.getTexture().unload();
        regionFondo=null;
    }

    private void agregarJoystick() {
        control = new AnalogOnScreenControl(120, 115, actividadJuego.camara,
                regionBase, regionControlSalto,
                0.03f, 100, actividadJuego.getVertexBufferObjectManager(), new AnalogOnScreenControl.IAnalogOnScreenControlListener() {

            @Override
            public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
            }
            @Override
            public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {

                if(juegoCorriendo){
                    if(pValueX>0){
                        der.setAlpha(1);
                        iz.setAlpha(0.001f);
                    }
                    if(pValueX<0){
                        iz.setAlpha(1);
                        der.setAlpha(0.001f);
                    }
                    if(pValueX==0){
                        der.setAlpha(0.001f);
                        iz.setAlpha(0.001f);
                    }

                    if(pValueY>0){
                        up.setAlpha(1);
                        down.setAlpha(0.001f);
                    }
                    if(pValueY<0){
                        up.setAlpha(0.001f);
                        down.setAlpha(1);
                    }
                    if(pValueY==0){
                        up.setAlpha(0.001f);
                        down.setAlpha(0.001f);
                    }

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
}
