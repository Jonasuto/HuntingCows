package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Campos on 11/10/15.
 */
public class EscenaOdiseaPirata extends EscenaBase {

    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionBase;
    private ITextureRegion regionDisparar;

    private Text txtMarcador; // Por ahora con valorMarcador
    private IFont fontMonster;

    private HUD hud;


    private Sprite der;
    private Sprite iz;
    private Sprite up;
    private Sprite down;

    private ITextureRegion regionControlDer;
    private ITextureRegion regionControlIz;
    private ITextureRegion regionControlUp;
    private ITextureRegion regionControlDown;

    private float valorMarcador;

    private ArrayList<Barco> listaEnemigos;

    private ITextureRegion regionVida;

    private int contadorBarcos;
    private int contadorTiempoBarcos;

    private int numeroDeBalas;

    private Random aleatorio;

    private ITextureRegion vidas;

    private Sprite[] spriteVidas;

    private Text txtBalas; // Por ahora con valorMarcador

    private Text txtTotalBalas; // Por ahora con valorMarcador

    private int valorBalas;

    private int contadorTiempo;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Jugador spritePersonajeParado;
    private Jugador spritePersonajeMovimiento;

    private boolean juegoCorriendo = true;

    private ArrayList<Laser> listaProyectilesEnemigo;


    private CameraScene escenaPausa;
    private ITextureRegion regionBtnPausa;

    private TiledTextureRegion regionPersonajeParado;
    private TiledTextureRegion regionPersonajeMovimiento;
    private TiledTextureRegion regionEnemigoSimple;
    private TiledTextureRegion regionEnemigoFinal;


    private Sprite spriteFondoPausa;

    private ButtonSprite btnDisparar;
    private int cantidadVida;

    // Proyectiles del personaje
    private ITextureRegion regionProyectil;
    private ArrayList<Laser> listaProyectiles;

    private ArrayList<Vida> listaVidasEncontradas;


    private ITextureRegion regionFondo;

    private ITextureRegion regionMenuPausa;
    private ITextureRegion regionMenuOffoff;
    private ITextureRegion regionMenuOffon;
    private ITextureRegion regionMenuOnoff;
    private ITextureRegion regionMenuOnon;
    private ITextureRegion regionMenuMusica;
    private ITextureRegion regionMenuContinuar;
    private ITextureRegion regionIrAMenu;
    private boolean miniJuego;

    private boolean musicaEncendida;

    private Sprite spriteMenuPausa;
    private Sprite spritebtnOn;
    private Sprite spritebtnOff;
    private Sprite spritebtnMusica;
    private Sprite spritebtnContinuar;
    private Sprite spritebtnIrAMenu;

    private int cambiar=0;

    @Override
    public void cargarRecursos() {

        aleatorio=new Random();

        cantidadVida = 2;

        fontMonster = cargarFont("fonts/monster.ttf");

        vidas = cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionFondo = cargarImagen("Imagenes/playa.jpg");
        regionFondoPausa = cargarImagen("Imagenes/Logos/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionDisparar=cargarImagen("Imagenes/Roman/boton_fuego.png");
        regionVida = cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionPersonajeParado = cargarImagenMosaico("Imagenes/Roman/spritelanchaparada.png", 400, 150, 1, 2);
        regionPersonajeMovimiento = cargarImagenMosaico("Imagenes/Roman/lanchamovimiento.png", 450, 150, 1, 2);
        regionEnemigoSimple= cargarImagenMosaico("Imagenes/Roman/spritePirata.png", 1120, 300, 1, 4);
        regionEnemigoFinal= cargarImagenMosaico("Imagenes/Roman/spritesbosspirata.png", 1000, 400, 1, 2);

        regionControlDer=cargarImagen("Imagenes/Joystick/derecha_joystick.png");
        regionControlIz=cargarImagen("Imagenes/Joystick/izquiera_joystick.png");
        regionControlUp=cargarImagen("Imagenes/Joystick/arriba_joystick.png");
        regionControlDown=cargarImagen("Imagenes/Joystick/abajo_joystick.png");


        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");

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

        actividadJuego.camara.setHUD(new HUD());

        actividadJuego.camara.setCenter(640,400);

 // Fondo animado
        AutoParallaxBackground fondoAnimado = new AutoParallaxBackground(1, 1, 1, 5);

        // Fondo atrás
        Sprite spriteFondoAtras = cargarSprite(ControlJuego.ANCHO_CAMARA/2,
                ControlJuego.ALTO_CAMARA/2, regionFondo);
        fondoAnimado.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(20, spriteFondoAtras));

        setBackground(fondoAnimado);

        agregarHUD();

        numeroDeBalas=7;

        contadorBarcos=0;

        contadorTiempoBarcos=0;

        contadorTiempo=0;

        listaProyectiles = new ArrayList<>();

        listaProyectilesEnemigo = new ArrayList<>();

        listaEnemigos = new ArrayList<>();

        listaVidasEncontradas = new ArrayList<>();

        spriteVidas= new Sprite[3];

        spriteVidas[0]= cargarSprite(1100, 750, vidas);
        hud.attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(1150, 750, vidas);
        hud.attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1200, 750, vidas);
        hud.attachChild(spriteVidas[2]);

        aleatorio= new Random();

        //admMusica.cargarMusica(2);

        agregarJoystick();
        agregarBotonDisparar();

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
        hud.attachChild(btnPausa);
        hud.registerTouchArea(btnPausa);

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

        spritebtnContinuar=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2 + 300, regionMenuContinuar);
        escenaPausa.attachChild(spritebtnContinuar);

        spritebtnIrAMenu=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2-300, regionIrAMenu);
        escenaPausa.attachChild(spritebtnIrAMenu);

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
        escenaPausa.attachChild(spriteOffFinal);
        escenaPausa.registerTouchArea(spriteOffFinal);

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
        escenaPausa.attachChild(spriteOnFinal);
        escenaPausa.registerTouchArea(spriteOnFinal);



        spritePersonajeParado = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeParado, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeParado.animate(75);

        spritePersonajeMovimiento = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeMovimiento, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeMovimiento.animate(75);


        spritePersonaje=spritePersonajeParado;
        attachChild(spritePersonaje);

        Barco spriteEnemigo = new Barco(1250, (aleatorio.nextInt(7)+1)*100, regionEnemigoSimple, actividadJuego.getVertexBufferObjectManager());
        spriteEnemigo.animate(75);
        listaEnemigos.add(spriteEnemigo);
        attachChild(spriteEnemigo);

        crearEnemigos();

        der = cargarSprite(190, 115, regionControlDer);
        control.attachChild(der);

        iz = cargarSprite(50, 115, regionControlIz);
        control.attachChild(iz);

        up = cargarSprite(120, 185, regionControlUp);
        control.attachChild(up);

        down = cargarSprite(120, 45, regionControlDown);
        control.attachChild(down);

    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
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
            EscenaOdiseaPirata.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    private void agregarHUD() {
        hud = new HUD();

        // Marcador/valorMarcador
        txtMarcador = new Text(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA-100,
                fontMonster,"    0    ",actividadJuego.getVertexBufferObjectManager());
        txtMarcador.setColor(Color.WHITE);
        hud.attachChild(txtMarcador);
        valorMarcador = 0;

        txtBalas = new Text(1170, 700,
                fontMonster,"    10    ",actividadJuego.getVertexBufferObjectManager());
        txtBalas.setColor(Color.BLUE);
        hud.attachChild(txtBalas);
        valorBalas = 7;

        txtTotalBalas = new Text(1220, 700,
                fontMonster,"    /7    ",actividadJuego.getVertexBufferObjectManager());
        txtTotalBalas.setColor(Color.BLUE);
        hud.attachChild(txtTotalBalas);

        actividadJuego.camara.setHUD(hud);
    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_PIRATA;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {

            if (cambiar == 1) {

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
                cambiar = 0;
            }
            return;
        }

        if(numeroDeBalas<7){
            contadorTiempo+=1;
            if(contadorTiempo>=150){
                numeroDeBalas++;
                valorBalas++;
                contadorTiempo=0;
            }
        }


        DecimalFormat df = new DecimalFormat("##.##"); // Para formatear 2 decimales
        txtMarcador.setText(df.format(valorMarcador));

        txtBalas.setText(df.format(valorBalas));

        actualizarProyectilesEnemigo();

        actualizarProyectiles();

        actualizarVidas(pSecondsElapsed);

        for (int i = listaEnemigos.size() - 1; i >= 0; i--) {

            Barco enemigo = listaEnemigos.get(i);

                if (spritePersonaje.collidesWith(enemigo) && spritePersonaje.getX()-enemigo.getX()>-20 && spritePersonaje.getX()-enemigo.getX()<20 && spritePersonaje.getY()-enemigo.getY()<20 && spritePersonaje.getY()-enemigo.getY()>-20 ) {


                    if(cantidadVida -1<0){
                        admEscenas.liberarEscenaPirata();
                        admEscenas.crearEscenaPerdiste(4);
                        admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                    }
                    else{

                        enemigo.detachSelf();
                        listaEnemigos.remove(enemigo);
                        //admMusica.vibrar(200);



                        if(2 - cantidadVida>=0) {
                            spriteVidas[2 - cantidadVida].detachSelf();
                            cantidadVida--;
                        }
                        else{
                            admEscenas.liberarEscenaPirata();
                            admEscenas.crearEscenaPerdiste(4);
                            admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                        }


                    }
                    break;
                }



            if(enemigo.getBoss()==false){
                enemigo.setX(enemigo.getX()-1);
                if(enemigo.getX()<0){
                    //admMusica.vibrar(20);
                    enemigo.detachSelf();
                    listaEnemigos.remove(enemigo);
                }
            }
            else{
                if(enemigo.getX()>=900){
                    enemigo.setX(enemigo.getX()-1);
                }

            }
            }

        if(listaEnemigos.size()==0){
            Barco spriteEnemigo = new Barco(1250, 400, regionEnemigoFinal, actividadJuego.getVertexBufferObjectManager());
            spriteEnemigo.setBoss(true);
            spriteEnemigo.animate(75);
            listaEnemigos.add(spriteEnemigo);
            attachChild(spriteEnemigo);
        }
    }

    private Font cargarFont(String archivo) {
        // La imagen que contiene cada símbolo
        final ITexture fontTexture = new BitmapTextureAtlas(actividadJuego.getEngine().getTextureManager(),512,256);
        // Carga el archivo, tamaño 56, antialias y color
        Font tipoLetra = FontFactory.createFromAsset(actividadJuego.getEngine().getFontManager(),
                fontTexture, actividadJuego.getAssets(), archivo, 56, true, 0xFF00FF00);
        tipoLetra.load();
        tipoLetra.prepareLetters("InicoFnalMed 01234567890.".toCharArray());

        return tipoLetra;
    }

    private void actualizarProyectilesEnemigo() {
        // Se visita cada proyectil dentro de la lista, se recorre con el índice
        // porque se pueden borrar datos
        if(miniJuego == false) {
            for (int i = listaProyectilesEnemigo.size() - 1; i >= 0; i--) {

                Laser proyectil = listaProyectilesEnemigo.get(i);
                if (proyectil.getY() < 200) {
                    proyectil.detachSelf();
                    listaProyectilesEnemigo.remove(proyectil);
                    continue;
                }
                    if (proyectil.collidesWith(spritePersonaje) && proyectil.getX()-spritePersonaje.getX()>-20 && proyectil.getY()-spritePersonaje.getY()<20 ) {


                        // Baja puntos/vida
                    if (cantidadVida - 1 < 0) {
                        admEscenas.liberarEscenaPirata();
                        admEscenas.crearEscenaPerdiste(4);
                        admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                    } else {
                        proyectil.detachSelf();
                        listaProyectilesEnemigo.remove(proyectil);
                        //admMusica.vibrar(200);

                            if (2 - cantidadVida >= 0) {
                                spriteVidas[2 - cantidadVida].detachSelf();
                                cantidadVida--;
                            } else {
                                admEscenas.liberarEscenaPirata();
                                admEscenas.crearEscenaMenu();
                                admEscenas.setEscena(TipoEscena.ESCENA_MENU);
                            }

                    }
                    break;
                }
            }
        }
    }

    private void dispararEnemigo(Enemigo enemigo) {
        // Crearlo
        Laser spriteProyectil = new Laser(enemigo.getX(),  enemigo.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),true,true);
        attachChild(spriteProyectil);   // Lo agrega a la escena
        listaProyectilesEnemigo.add(spriteProyectil);  // Lo agrega a la lista
    }


    @Override
    public void liberarEscena() {
        liberarRecursos();
        hud.detachChildren();
        this.detachChildren();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {

        //admMusica.liberarMusica();
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);
        regionFondo.getTexture().unload();
        regionFondo=null;
        regionBase.getTexture().unload();
        regionBase=null;
        regionVida.getTexture().unload();
        regionVida=null;
        regionBtnPausa.getTexture().unload();
        regionBtnPausa=null;
        regionProyectil.getTexture().unload();
        regionProyectil=null;
        regionMenuPausa.getTexture().unload();
        regionMenuPausa=null;
        regionMenuOffoff.getTexture().unload();
        regionMenuOffoff=null;
        regionMenuOffon.getTexture().unload();
        regionMenuOffon=null;
        regionMenuOnoff.getTexture().unload();
        regionMenuOnoff=null;
        regionMenuOnon.getTexture().unload();
        regionMenuOnon=null;
        regionIrAMenu.getTexture().unload();
        regionIrAMenu=null;
        regionMenuMusica.getTexture().unload();
        regionMenuMusica=null;
        regionMenuContinuar.getTexture().unload();
        regionMenuContinuar=null;
    }

    private void agregarJoystick() {
        control = new AnalogOnScreenControl(160, 160, actividadJuego.camara,
                regionBase, regionBase,
                0.03f, 100, actividadJuego.getVertexBufferObjectManager(), new AnalogOnScreenControl.IAnalogOnScreenControlListener() {

            @Override
            public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
            }
            @Override
            public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {

                if(juegoCorriendo) {

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

                    if(pValueX!=0){
                        spritePersonajeMovimiento.setPosition(spritePersonaje);
                        spritePersonaje.detachSelf();
                        spritePersonaje=spritePersonajeMovimiento;
                        attachChild(spritePersonaje);
                    }
                    else{
                        spritePersonajeParado.setPosition(spritePersonaje);
                        spritePersonaje.detachSelf();
                        spritePersonaje=spritePersonajeParado;
                        attachChild(spritePersonaje);
                    }

                        if(spritePersonaje.getY()<0){
                            if(pValueY<0){
                            }
                            else{
                                float y = spritePersonaje.getY() + 6 * pValueY;
                                spritePersonaje.setY(y);
                            }
                        }

                        else if(spritePersonaje.getY()>700){
                            if(pValueY>0){
                            }
                            else{
                                float y = spritePersonaje.getY() + 6 * pValueY;
                                spritePersonaje.setY(y);
                            }
                        }

                        else{
                            float y = spritePersonaje.getY() + 6 * pValueY;
                            spritePersonaje.setY(y);
                        }

                    if(spritePersonaje.getX()>1200){
                        if(pValueX>0){
                        }
                        else{
                            float x = spritePersonaje.getX()+6*pValueX;
                            spritePersonaje.setX(x);
                        }
                    }

                    else if(spritePersonaje.getX()<0){
                        if(pValueX<0){
                        }
                        else{
                            float x = spritePersonaje.getX()+6*pValueX;
                            spritePersonaje.setX(x);
                        }
                    }

                    else {
                        float x = spritePersonaje.getX() + 6 * pValueX;
                        spritePersonaje.setX(x);
                    }
                }
            }

        });
        EscenaOdiseaPirata.this.setChildScene(control);
    }

    private void crearEnemigos(){

        for(int cont=0;cont<10;cont++){
            Barco spriteEnemigo = new Barco(((aleatorio.nextInt(7)+3)*350)+1250, (aleatorio.nextInt(7)+1)*100, regionEnemigoSimple, actividadJuego.getVertexBufferObjectManager());
            spriteEnemigo.animate(75);
            listaEnemigos.add(spriteEnemigo);
            attachChild(spriteEnemigo);
        }
    }

    private void agregarBotonDisparar() {
        btnDisparar = new ButtonSprite(1150,150,
                regionDisparar,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (juegoCorriendo) {
                    if(pSceneTouchEvent.isActionDown()){

                        if(numeroDeBalas>0) {
                            dispararProyectil();
                            //admMusica.vibrar(100);
                            //admMusica.reproducirMusicaBoton();
                            valorBalas--;
                            numeroDeBalas--;
                        }
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        hud.registerTouchArea(btnDisparar);
        hud.attachChild(btnDisparar);
    }

    private void actualizarProyectiles() {

        // Se visita cada proyectil dentro de la lista, se recorre con el índice
        // porque se pueden borrar datos

            for (int i = listaProyectiles.size() - 1; i >= 0; i--) {
                final Laser balaRoman = listaProyectiles.get(i);

                if(balaRoman.getY()-200>balaRoman.getYinicial()){
                    balaRoman.setSeSupero(true);
                }

                if(balaRoman.getYaDespegue()==false){
                    balaRoman.setYaDespegue(true);
                    // Animar sprite central
                    JumpModifier salto = new JumpModifier(1, balaRoman.getX()+10, balaRoman.getX()+530,
                            balaRoman.getY(), balaRoman.getY(),-300);
                    RotationModifier rotacion = new RotationModifier(1, -95, 0);
                    ParallelEntityModifier paralelo = new ParallelEntityModifier(salto,rotacion)
                    {
                        @Override
                        protected void onModifierFinished(IEntity pItem) {
                            unregisterEntityModifier(this);
                            super.onModifierFinished(pItem);
                        }
                    };
                    balaRoman.registerEntityModifier(paralelo);
                }

                else {

                    if(balaRoman.getseSupero()) {

                        if (balaRoman.getYinicial()== balaRoman.getY()) {
                    for (int k = listaEnemigos.size() - 1; k >= 0; k--) {
                        Barco enemigo = listaEnemigos.get(k);
                        if (balaRoman.collidesWith(enemigo) && (balaRoman.getX()-enemigo.getX()>-80 && balaRoman.getX()-enemigo.getX()<80) && (balaRoman.getY()-enemigo.getY()<80 && balaRoman.getY()-enemigo.getY()>-80) ) {
                            // Lo destruye

                            if (enemigo.getRegalo() == 1 || enemigo.getRegalo() == 3) {
                                crearVida(enemigo);
                            }

                            enemigo.detachSelf();
                            listaEnemigos.remove(enemigo);

                            // desaparece el proyectil
                            detachChild(balaRoman);
                            listaProyectiles.remove(balaRoman);
                            break;

                        }
                        else{
                            detachChild(balaRoman);
                        }
                    }
                        }
                    }
                }
            }
    }


    private void actualizarVidas(float tiempo) {
        // Se visita cada proyectil dentro de la lista, se recorre con el índice
        // porque se pueden borrar datos
        for (int i = listaVidasEncontradas.size() - 1; i>=0; i--) {
            Vida vida = listaVidasEncontradas.get(i);

            if (vida.collidesWith(spritePersonaje)) {

                if(cantidadVida<2){

                    if(cantidadVida==0){
                        spriteVidas[1]= cargarSprite(1150, 750, vidas);
                        hud.attachChild(spriteVidas[1]);
                        cantidadVida++;
                    }
                    else if(cantidadVida==1){
                        spriteVidas[0]= cargarSprite(1100, 750, vidas);
                        hud.attachChild(spriteVidas[0]);
                        cantidadVida++;
                    }
                }

                vida.detachSelf();
                listaVidasEncontradas.remove(vida);
                break;
            }

        }
    }


    private void dispararProyectil() {
        // Crearlo
        Laser spriteProyectil = new Laser(spritePersonaje.getX(),  spritePersonaje.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),true,false);
        attachChild(spriteProyectil);   // Lo agrega a la escena
        listaProyectiles.add(spriteProyectil);  // Lo agrega a la lista
    }


    private void crearVida(Barco enemigoDestruido) {

        // Crearlo
        Vida spriteVida = new Vida(enemigoDestruido.getX(), enemigoDestruido.getY(),regionVida, actividadJuego.getVertexBufferObjectManager());
        attachChild(spriteVida);
        listaVidasEncontradas.add(spriteVida);  // Lo agrega a la lista
    }
}