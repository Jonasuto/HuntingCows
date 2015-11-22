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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Campos on 11/10/15.
 */
public class EscenaCazaJurasicaNivel2 extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionNave;
    private ITextureRegion regionHoyoNegro;
    private ITextureRegion regionOvni;
    private ITextureRegion regionPua;
    private ITextureRegion regionPicos;
    private ITextureRegion regionLava;

    private Random elQueSigue;

    private int reloj;

    private boolean enElAire=false;
    private boolean gravedad=false;
    private boolean paArriba=false;
    private boolean nubecita=false;
    private boolean brincaSobrepalmera=false;

    private float[] posicionesEnemigosx;
    private float[] posicionesEnemigosy;

    private float[] posicionesPisosFlotantesX;
    private float[] posicionesPisosFlotantesY;

    private float[] posicionesMonedasX;
    private float[] posicionesMonedasY;

    private boolean[] brinca;
    private int[] camina;
    private boolean[] rota;
    private boolean[] rotaCamina;
    private int[] numPasos;
    private boolean[] disparar;
    private boolean[] noPuedeSerDestruido;

    private int[] comportamientoPiso;
    private int[] numPasosPiso;

    private Text txtMarcador; // Por ahora con valorMarcador
    private IFont fontMonster;

    private HUD hud;

    private float valorMarcador;

    private ITextureRegion regionLechitas;

    private ArrayList<Sprite> listaMonedas;
    private static final int NUM_MONEDAS = 30;

    private ArrayList<Enemigo> listaEnemigos;
    private Piso[] listaPisos;

    private ITextureRegion regionEnemigo;
    private ITextureRegion regionVida;

    private int numeroDeBalas;

    private Random aleatorio;

    private ITextureRegion vidas;
    private Sprite[] spriteVidas;

    private Sprite[] spriteBalas;

    private Sprite spriteNave;

    private boolean estoySobreUnaPalmera;

    private float poderDeSalto;

    private Sprite spriteNavecita;

    private boolean estoySaltando;
    private int contadorTiempo;

    private boolean permiso;
    private boolean permiso2;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Jugador spritePersonajeParado;
    private Jugador spritePersonajeDerecha;
    private Jugador spritePersonajeSaltandoUno;
    private Jugador spritePersonajeSaltandoDos;

    private Enemigo spriteEnemigo;

    private boolean juegoCorriendo = true;

    private Piso pisoActual;

    private ArrayList<Laser> listaProyectilesEnemigo;


    private CameraScene escenaPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;
    private TiledTextureRegion regionPersonajeSaltandoUno;
    private TiledTextureRegion regionPersonajeSaltandoDos;
    private TiledTextureRegion regionPersonajeSaltandoCompleto;
    private TiledTextureRegion regionPersonajeParado;

    private boolean personajeVolteandoDerecha=true;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondo2;
    private Sprite spriteFondo4;
    private Sprite spriteFondo3;
    private Sprite spriteFondo5;
    private Sprite spriteFondo6;
    private Sprite spriteFondo7;
    private Sprite spriteHoyoNegro;
    private Sprite spriteFondoPausa;

    private int contadorPersigue;


    private ButtonSprite btnSaltar;
    private ButtonSprite btnDisparar;
    private int cantidadVida;

    private ITextureRegion regionPisoFlotante;

    // Proyectiles del personaje
    private ITextureRegion regionProyectil;
    private ArrayList<Laser> listaProyectiles;

    private ArrayList<Vida> listaVidasEncontradas;


    private ITextureRegion regionMenuPausa;
    private ITextureRegion regionMenuOffoff;
    private ITextureRegion regionMenuOffon;
    private ITextureRegion regionMenuOnoff;
    private ITextureRegion regionMenuOnon;
    private ITextureRegion regionMenuMusica;
    private ITextureRegion regionMenuContinuar;
    private ITextureRegion regionIrAMenu;

    private boolean musicaEncendida;

    private Sprite spriteMenuPausa;
    private Sprite spritebtnOn;
    private Sprite spritebtnOff;
    private Sprite spritebtnMusica;
    private Sprite spritebtnContinuar;
    private Sprite spritebtnIrAMenu;

    private boolean yaBajo;

    private int cambiar=0;
    private boolean unicoQuePersigue;


    @Override
    public void cargarRecursos() {


        elQueSigue=new Random();
        poderDeSalto=0;

        cantidadVida = 2;

        estoySobreUnaPalmera=false;

        regionLechitas = cargarImagen("Imagenes/leche.png");

        fontMonster = cargarFont("fonts/monster.ttf");

        contadorPersigue=0;


        vidas=cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionNave=cargarImagen("Imagenes/Roman/nave.png");
        regionFondo = cargarImagen("Imagenes/Niveles/CazaJurasica/fondos/fondo2.jpg");
        regionFondoPausa = cargarImagen("Imagenes/Logos/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/Roman/joystick.png");
        regionEnemigo = cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/vacaDinosaurio.png");
        regionVida = cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/Roman/prueba.png", 870, 200, 1, 8);
        regionPersonajeParado = cargarImagenMosaico("Imagenes/Roman/parado.png", 500, 200, 1, 4);
        regionPersonajeSaltandoUno = cargarImagenMosaico("Imagenes/Roman/spritesaltouno.png", 370, 200, 1, 3);
        regionPersonajeSaltandoDos = cargarImagenMosaico("Imagenes/Roman/spritesaltodos.png", 370, 200, 1, 3);
        regionPersonajeSaltandoCompleto = cargarImagenMosaico("Imagenes/Roman/spritesalto.png", 700, 200, 1, 6);

        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionHoyoNegro= cargarImagen("Imagenes/hoyoNegro.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
        regionPisoFlotante = cargarImagen("Imagenes/lineas.png");


        regionOvni= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/naveVaca.png");
        regionPua= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/pua.png");
        regionPicos= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/picos.png");
        regionLava= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/lava.png");

        regionMenuPausa = cargarImagen("Imagenes/Ajustes2/pausa_fondo.png");
        regionMenuOffoff=cargarImagen("Imagenes/Ajustes2/music_off_OFF.png");
        regionMenuOffon=cargarImagen("Imagenes/Ajustes2/music_off_ON.png");
        regionMenuOnoff=cargarImagen("Imagenes/Ajustes2/music_on_OFF.png");
        regionMenuOnon=cargarImagen("Imagenes/Ajustes2/music_on_ON.png");
        regionIrAMenu=cargarImagen("Imagenes/Ajustes2/pausa_menu.png");
        regionMenuMusica=cargarImagen("Imagenes/Ajustes2/pausa_musica.png");
        regionMenuContinuar=cargarImagen("Imagenes/Ajustes2/pausa_continue.png");
        unicoQuePersigue=false;
    }

    @Override
    public void crearEscena() {

        estoySaltando=false;

        yaBajo=false;

        spriteFondo = cargarSprite(1900, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteFondo2 = cargarSprite(6000, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo2);

        spriteFondo3 = cargarSprite(9954, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo3);

        spriteFondo4 = cargarSprite(13908, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo4);

        spriteFondo5 = cargarSprite(17862, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo5);

        spriteFondo6 = cargarSprite(21816, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo6);

        spriteFondo7 = cargarSprite(25770, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo7);

        agregarHUD();

        reloj=0;

        contadorTiempo=0;

        listaProyectiles = new ArrayList<>();

        listaProyectilesEnemigo = new ArrayList<>();

        listaEnemigos = new ArrayList<>();

        listaVidasEncontradas = new ArrayList<>();

        posicionarPisosFlotantes();
        posicionarEnemigos();

        spriteVidas= new Sprite[3];

        spriteBalas= new Sprite[10];

        permiso=true;
        permiso2=true;

        numeroDeBalas=9;

        spriteNave=cargarSprite(320, 290, regionNave);
        spriteFondo.attachChild(spriteNave);

        spriteVidas[0]= cargarSprite(1100, 750, vidas);
        hud.attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(1150, 750, vidas);
        hud.attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1200, 750, vidas);
        hud.attachChild(spriteVidas[2]);


        spriteBalas[0]= cargarSprite(1100, 600, regionProyectil);
        spriteBalas[0].setSize(spriteBalas[0].getWidth() - 20, spriteBalas[0].getHeight() - 20);
        hud.attachChild(spriteBalas[0]);

        spriteBalas[1]= cargarSprite(1100, 550, regionProyectil);
        spriteBalas[1].setSize(spriteBalas[1].getWidth() - 20, spriteBalas[1].getHeight() - 20);
        hud.attachChild(spriteBalas[1]);

        spriteBalas[2]= cargarSprite(1100, 500, regionProyectil);
        spriteBalas[2].setSize(spriteBalas[2].getWidth() - 20, spriteBalas[2].getHeight() - 20);
        hud.attachChild(spriteBalas[2]);

        spriteBalas[3]= cargarSprite(1100, 450, regionProyectil);
        spriteBalas[3].setSize(spriteBalas[3].getWidth() - 20, spriteBalas[3].getHeight() - 20);
        hud.attachChild(spriteBalas[3]);

        spriteBalas[4]= cargarSprite(1100, 400, regionProyectil);
        spriteBalas[4].setSize(spriteBalas[4].getWidth() - 20, spriteBalas[4].getHeight() - 20);
        hud.attachChild(spriteBalas[4]);

        spriteBalas[5]= cargarSprite(1100, 350, regionProyectil);
        spriteBalas[5].setSize(spriteBalas[5].getWidth() - 20, spriteBalas[5].getHeight() - 20);
        hud.attachChild(spriteBalas[5]);

        spriteBalas[6]= cargarSprite(1100, 300, regionProyectil);
        spriteBalas[6].setSize(spriteBalas[6].getWidth() - 20, spriteBalas[6].getHeight() - 20);
        hud.attachChild(spriteBalas[6]);

        spriteBalas[7]= cargarSprite(1100, 250, regionProyectil);
        spriteBalas[7].setSize(spriteBalas[7].getWidth() - 20, spriteBalas[7].getHeight() - 20);
        hud.attachChild(spriteBalas[7]);

        spriteBalas[8]= cargarSprite(1100, 200, regionProyectil);
        spriteBalas[8].setSize(spriteBalas[8].getWidth() - 20, spriteBalas[8].getHeight() - 20);
        hud.attachChild(spriteBalas[8]);

        spriteBalas[9] = cargarSprite(1100, 150, regionProyectil);
        spriteBalas[9].setSize(spriteBalas[9].getWidth() - 20, spriteBalas[9].getHeight() - 20);
        hud.attachChild(spriteBalas[9]);

        pisoActual= new Piso(10, 1500, regionPisoFlotante,actividadJuego.getVertexBufferObjectManager(),4,0);
        attachChild(pisoActual);

        spriteHoyoNegro= cargarSprite(25283, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        aleatorio= new Random();

        spritePersonajeDerecha = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeDerecha.animate(70);

        spritePersonajeSaltandoUno = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeSaltandoUno, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeSaltandoDos = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeSaltandoDos, actividadJuego.getVertexBufferObjectManager());


        spritePersonajeParado = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeParado, actividadJuego.getVertexBufferObjectManager());

        spritePersonaje=spritePersonajeParado;
        attachChild(spritePersonaje);

        spriteNavecita=null;

        admMusica.cargarMusica(2);

        agregarJoystick();
        agregarBotonSalto();
        agregarBotonDisparar();

        Sprite btnPausa = new Sprite(regionBtnPausa.getWidth(), ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight(),
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
        hud.attachChild(btnPausa);
        hud.registerTouchArea(btnPausa);

        musicaEncendida=admMusica.getMusicaEncendida();

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

                        admMusica.vibrar(100);

                        admMusica.setMusicaEncendida(false,2);
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

                        admMusica.vibrar(100);

                        spritebtnOn.detachSelf();
                        spritebtnOff.detachSelf();

                        admMusica.setMusicaEncendida(true,2);
                        musicaEncendida=true;

                        cambiar=1;
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(spriteOnFinal);
        escenaPausa.registerTouchArea(spriteOnFinal);

        // Agregar monedas
        agregarMonedas();
    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        pausarJuego();
        if(admMusica.getMusicaEncendida()==true) {
            admMusica.reproduceio();
        }
    }

    private void pausarJuego() {
        if (juegoCorriendo) {
            escenaPausa.setChildScene(control);
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        }

        else {
            clearChildScene();
            EscenaCazaJurasicaNivel2.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    private void agregarHUD() {
        hud = new HUD();


        // Marcador/valorMarcador
        txtMarcador = new Text(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA-100,
                fontMonster,"    0    ",actividadJuego.getVertexBufferObjectManager());
        hud.attachChild(txtMarcador);
        valorMarcador = 0;

        actividadJuego.camara.setHUD(hud);
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_CAZA_JURASICA_NIVEL_2;
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


        if(numeroDeBalas<9){
            contadorTiempo+=1;

            if(contadorTiempo>=150){
                int posicionBala=8-numeroDeBalas;


                if(posicionBala==0) {
                    spriteBalas[0] = cargarSprite(1100, 600, regionProyectil);
                    spriteBalas[0].setSize(spriteBalas[0].getWidth() - 20, spriteBalas[0].getHeight() - 20);
                    hud.attachChild(spriteBalas[0]);
                }


                else if(posicionBala==1) {
                    spriteBalas[1] = cargarSprite(1100, 550, regionProyectil);
                    spriteBalas[1].setSize(spriteBalas[1].getWidth() - 20, spriteBalas[1].getHeight() - 20);
                    hud.attachChild(spriteBalas[1]);
                }

                else if(posicionBala==2) {
                    spriteBalas[2] = cargarSprite(1100, 500, regionProyectil);
                    spriteBalas[2].setSize(spriteBalas[2].getWidth() - 20, spriteBalas[2].getHeight() - 20);
                    hud.attachChild(spriteBalas[2]);
                }

                else if(posicionBala==3) {
                    spriteBalas[3] = cargarSprite(1100, 450, regionProyectil);
                    spriteBalas[3].setSize(spriteBalas[3].getWidth() - 20, spriteBalas[3].getHeight() - 20);
                    hud.attachChild(spriteBalas[3]);
                }

                else if(posicionBala==4) {
                    spriteBalas[4] = cargarSprite(1100, 400, regionProyectil);
                    spriteBalas[4].setSize(spriteBalas[4].getWidth() - 20, spriteBalas[4].getHeight() - 20);
                    hud.attachChild(spriteBalas[4]);
                }

                else if(posicionBala==5) {
                    spriteBalas[5] = cargarSprite(1100, 350, regionProyectil);
                    spriteBalas[5].setSize(spriteBalas[5].getWidth() - 20, spriteBalas[5].getHeight() - 20);
                    hud.attachChild(spriteBalas[5]);
                }

                else if(posicionBala==6) {
                    spriteBalas[6] = cargarSprite(1100, 300, regionProyectil);
                    spriteBalas[6].setSize(spriteBalas[6].getWidth() - 20, spriteBalas[6].getHeight() - 20);
                    hud.attachChild(spriteBalas[6]);
                }

                else if(posicionBala==7) {
                    spriteBalas[7] = cargarSprite(1100, 250, regionProyectil);
                    spriteBalas[7].setSize(spriteBalas[7].getWidth() - 20, spriteBalas[7].getHeight() - 20);
                    hud.attachChild(spriteBalas[7]);
                }

                else if(posicionBala==8) {
                    spriteBalas[8] = cargarSprite(1100, 200, regionProyectil);
                    spriteBalas[8].setSize(spriteBalas[8].getWidth() - 20, spriteBalas[8].getHeight() - 20);
                    hud.attachChild(spriteBalas[8]);
                }


                else if(posicionBala==9) {
                    spriteBalas[9] = cargarSprite(1100, 150, regionProyectil);
                    spriteBalas[9].setSize(spriteBalas[9].getWidth() - 20, spriteBalas[9].getHeight() - 20);
                    hud.attachChild(spriteBalas[9]);
                }


                contadorTiempo=0;
                numeroDeBalas++;
            }
        }


        contadorPersigue+=1;

        if(contadorPersigue>190 && unicoQuePersigue==false){
            Enemigo spriteEnemigo = new Enemigo(30000, 720, regionOvni, actividadJuego.getVertexBufferObjectManager(), 19, false, false, 10,false,true, false);
            Enemigo nuevoEnemigo = spriteEnemigo;
            nuevoEnemigo.setPersigue(true);
            listaEnemigos.add(nuevoEnemigo);
            attachChild(nuevoEnemigo);
            contadorPersigue=0;
        }

        for (int i= listaPisos.length-1; i>=0; i--) {

            final Piso piso = listaPisos[i];

            if (spritePersonaje.collidesWith(piso) && piso.getY()+110<spritePersonaje.getY() && poderDeSalto<0.3) {

                if(piso.getLimiteDerecho()==10){
                    piso.setBalsa(true);
                }
                pisoActual=piso;

                if (nubecita == false) {

                    estoySobreUnaPalmera = true;

                    if (brincaSobrepalmera == false) {

                        enElAire = false;
                        paArriba = false;
                        gravedad = true;
                        spritePersonaje.setY(piso.getY()+135);
                        estoySaltando=false;
                        if(yaBajo) {
                            if (personajeVolteandoDerecha == false) {
                                spritePersonajeParado.setFlippedHorizontal(true);
                            } else {
                                spritePersonajeParado.setFlippedHorizontal(false);
                            }
                            spritePersonajeParado.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje = spritePersonajeParado;
                            attachChild(spritePersonaje);
                            yaBajo=false;
                        }
                    } else {

                        enElAire = true;
                        paArriba = true;
                        gravedad = false;
                    }
                }
            }

            else{

                if(spritePersonaje.collidesWith(pisoActual)==false){
                    estoySobreUnaPalmera = false;
                }
            }

            if(piso.getMover()){
                piso.mover(pSecondsElapsed);
            }
        }

        DecimalFormat df = new DecimalFormat("##.##"); // Para formatear 2 decimales
        txtMarcador.setText(df.format(valorMarcador));

        actualizarMonedas();

        actualizarProyectilesEnemigo();

        if(spritePersonaje.collidesWith(spriteHoyoNegro)){
            admEscenas.liberarEscenaCazaJurasicaNivel2();
            admEscenas.crearEscenaMenu();
            admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        }

        if(gravedad==true && nubecita==false){
            if(spritePersonaje.getY()>ControlJuego.ALTO_CAMARA/4-20 && estoySobreUnaPalmera==false){
                spritePersonaje.setY(spritePersonaje.getY()-20);
                permiso2=true;
                saltar(false);
                if(spritePersonaje.getY()<=ControlJuego.ALTO_CAMARA/4-20){
                    gravedad=false;
                    estoySaltando=false;
                    if (personajeVolteandoDerecha == false) {
                        spritePersonajeParado.setFlippedHorizontal(true);
                    } else {
                        spritePersonajeParado.setFlippedHorizontal(false);
                    }
                    spritePersonajeParado.setPosition(spritePersonaje);
                    spritePersonaje.detachSelf();
                    spritePersonaje = spritePersonajeParado;
                    attachChild(spritePersonaje);
                    spritePersonaje.setY((ControlJuego.ALTO_CAMARA / 4) - 20);
                }
            }
        }
        if(enElAire==true && nubecita==false){
            if(paArriba){
                spritePersonaje.setY(spritePersonaje.getY() + (5*poderDeSalto));
                poderDeSalto-=0.15f;
                yaBajo=true;
                saltar(true);
                if(poderDeSalto<0){
                    saltar(false);
                }
                if(poderDeSalto<0){
                    brincaSobrepalmera=false;
                }
                if(spritePersonaje.getY()<=ControlJuego.ALTO_CAMARA/4-20){
                    enElAire=false;
                    estoySaltando=false;
                    if (personajeVolteandoDerecha == false) {
                        spritePersonajeParado.setFlippedHorizontal(true);
                    } else {
                        spritePersonajeParado.setFlippedHorizontal(false);
                    }
                    spritePersonajeParado.setPosition(spritePersonaje);
                    spritePersonaje.detachSelf();
                    spritePersonaje = spritePersonajeParado;
                    attachChild(spritePersonaje);
                    spritePersonaje.setY((ControlJuego.ALTO_CAMARA / 4) - 20);
                }
            }
        }

        actualizarProyectiles(pSecondsElapsed);

        actualizarVidas(pSecondsElapsed);

        for (int i= listaEnemigos.size()-1; i>=0; i--) {

            final Enemigo enemigo = listaEnemigos.get(i);

            if(enemigo.getPuedeDisparar()==true){

                if(enemigo.getDisparara()==1){
                    dispararEnemigo(enemigo);
                }

            }
            if(enemigo.getBrinco()==true){

                if(enemigo.getRotacion()){
                    if (enemigo.getBrincando()==false) {
                        enemigo.setBrincando(true);
                        // Animar sprite central
                        JumpModifier salto = new JumpModifier(1, enemigo.getX(), enemigo.getX(),
                                enemigo.getY(), enemigo.getY(), -400);
                        RotationModifier rotacion = new RotationModifier(1, 360, 0);
                        ParallelEntityModifier paralelo = new ParallelEntityModifier(salto, rotacion) {
                            @Override
                            protected void onModifierFinished(IEntity pItem) {
                                enemigo.setBrincando(false);
                                unregisterEntityModifier(this);
                                super.onModifierFinished(pItem);
                            }
                        };
                        enemigo.registerEntityModifier(paralelo);
                    }
                }


                else{
                    if (enemigo.getBrincando()==false) {
                        enemigo.setBrincando(true);
                        // Animar sprite central
                        JumpModifier salto = new JumpModifier(1, enemigo.getX(), enemigo.getX(),
                                enemigo.getY(), enemigo.getY(), -400);
                        ParallelEntityModifier paralelo = new ParallelEntityModifier(salto) {
                            @Override
                            protected void onModifierFinished(IEntity pItem) {
                                enemigo.setBrincando(false);
                                unregisterEntityModifier(this);
                                super.onModifierFinished(pItem);
                            }
                        };
                        enemigo.registerEntityModifier(paralelo);
                    }
                }

            }
            else{
                enemigo.mover(pSecondsElapsed);
            }
            if(enemigo.getPersigue()==true){

                unicoQuePersigue=true;
                float dx =spritePersonaje.getX()-enemigo.getX();
                if(dx>50){
                    enemigo.setX(3+enemigo.getX());
                }
                else if(dx<-50){
                    enemigo.setX(enemigo.getX()-3);
                }
            }


            if (spritePersonaje.collidesWith(enemigo)) {

                if(cantidadVida -1<0){
                    admEscenas.liberarEscenaCazaJurasicaNivel2();
                    admEscenas.crearEscenaPerdiste();
                    admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                }
                else{
                    if(enemigo.getPersigue()){
                        unicoQuePersigue=false;
                    }
                    enemigo.detachSelf();
                    listaEnemigos.remove(enemigo);
                    admMusica.vibrar(200);


                    if(nubecita==true){
                        spriteNavecita.detachSelf();
                        nubecita=false;
                        enElAire=true;
                        paArriba=true;
                        gravedad=false;
                    }

                    else {
                        if(2 - cantidadVida>=0) {
                            spriteVidas[2 - cantidadVida].detachSelf();
                            cantidadVida--;
                        }
                        else{
                            admEscenas.liberarEscenaCazaJurasicaNivel2();
                            admEscenas.crearEscenaPerdiste();
                            admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                        }
                    }

                }
            }


        }
    }

    private Font cargarFont(String archivo) {
        // La imagen que contiene cada símbolo
        final ITexture fontTexture = new BitmapTextureAtlas(actividadJuego.getEngine().getTextureManager(),512,256);
        // Carga el archivo, tamaño 56, antialias y color
        org.andengine.opengl.font.Font tipoLetra = FontFactory.createFromAsset(actividadJuego.getEngine().getFontManager(),
                fontTexture, actividadJuego.getAssets(), archivo, 56, true, 0xFF00FF00);
        tipoLetra.load();
        tipoLetra.prepareLetters("InicoFnalMed 01234567890.".toCharArray());

        return tipoLetra;
    }

    private void saltar(boolean arriba){
            if(arriba){
                if(permiso) {
                    permiso=false;
                    if (personajeVolteandoDerecha == false) {
                        spritePersonajeSaltandoUno.setFlippedHorizontal(true);
                    } else {
                        spritePersonajeSaltandoUno.setFlippedHorizontal(false);
                    }
                    spritePersonajeSaltandoUno.setPosition(spritePersonaje);
                    spritePersonajeSaltandoUno.animate(70, false);
                    spritePersonaje.detachSelf();
                    spritePersonaje = spritePersonajeSaltandoUno;
                    attachChild(spritePersonaje);
                }
            }
            else{
                if(permiso2) {
                    permiso2=false;
                    if (personajeVolteandoDerecha == false) {
                        spritePersonajeSaltandoDos.setFlippedHorizontal(true);
                    } else {
                        spritePersonajeSaltandoDos.setFlippedHorizontal(false);
                    }
                    spritePersonajeSaltandoDos.setPosition(spritePersonaje);
                    spritePersonajeSaltandoDos.animate(70, false);
                    spritePersonaje.detachSelf();
                    spritePersonaje = spritePersonajeSaltandoDos;
                    attachChild(spritePersonaje);
                }
            }
    }
    private void actualizarProyectilesEnemigo() {
        // Se visita cada proyectil dentro de la lista, se recorre con el índice
        // porque se pueden borrar datos
        for (int i = listaProyectilesEnemigo.size() - 1; i >= 0; i--) {

            Laser proyectil = listaProyectilesEnemigo.get(i);
            proyectil.mover();
            if (proyectil.getY() < 200 ) {
                proyectil.detachSelf();
                listaProyectilesEnemigo.remove(proyectil);
                continue;
            }
            if (proyectil.collidesWith(spritePersonaje)) {
                // Baja puntos/vida
                if(cantidadVida-1<0){
                    admEscenas.liberarEscenaCazaJurasicaNivel2();
                    admEscenas.crearEscenaPerdiste();
                    admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                }
                else{
                    proyectil.detachSelf();
                    listaProyectilesEnemigo.remove(proyectil);
                    admMusica.vibrar(200);
                    if(nubecita==true){
                        spriteNavecita.detachSelf();
                        nubecita=false;
                        enElAire=true;
                        paArriba=true;
                        gravedad=false;
                    }

                    else {
                        if(2-cantidadVida>=0) {
                            spriteVidas[2 - cantidadVida].detachSelf();
                            cantidadVida--;
                        }
                        else{
                            admEscenas.liberarEscenaCazaJurasicaNivel2();
                            admEscenas.crearEscenaMenu();
                            admEscenas.setEscena(TipoEscena.ESCENA_MENU);
                        }
                    }
                }
            }
        }
    }

    private void dispararEnemigo(Enemigo enemigo) {
        // Crearlo
        Laser spriteProyectil = new Laser(enemigo.getX(),  enemigo.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),personajeVolteandoDerecha,true);
        spriteFondo.attachChild(spriteProyectil);   // Lo agrega a la escena
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

        admMusica.liberarMusica();
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);
        regionFondo.getTexture().unload();
        regionFondo=null;
        regionNave.getTexture().unload();
        regionNave=null;
        regionFondoPausa.getTexture().unload();
        regionFondoPausa=null;
        regionBase.getTexture().unload();
        regionBase=null;
        regionControlSalto.getTexture().unload();
        regionControlSalto=null;
        regionEnemigo.getTexture().unload();
        regionEnemigo=null;
        regionVida.getTexture().unload();
        regionVida=null;
        regionPersonajeAnimado.getTexture().unload();
        regionPersonajeAnimado=null;
        regionBtnPausa.getTexture().unload();
        regionBtnPausa=null;
        regionHoyoNegro.getTexture().unload();
        regionHoyoNegro=null;
        regionProyectil.getTexture().unload();
        regionProyectil=null;
        regionPisoFlotante.getTexture().unload();
        regionPisoFlotante=null;
        regionOvni.getTexture().unload();
        regionOvni=null;
        regionPua.getTexture().unload();
        regionPua=null;
        regionPicos.getTexture().unload();
        regionPicos=null;
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
        control = new AnalogOnScreenControl(100, 100, actividadJuego.camara,
                regionBase, regionControlSalto,
                0.03f, 100, actividadJuego.getVertexBufferObjectManager(), new AnalogOnScreenControl.IAnalogOnScreenControlListener() {

            @Override
            public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
            }
            @Override
            public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {

                if(juegoCorriendo) {

                    if (nubecita == true) {

                        if(spritePersonaje.getY()<200){
                            if(pValueY<0){
                            }
                            else{
                                float y = spritePersonaje.getY() + 15 * pValueY;
                                spritePersonaje.setY(y);
                            }
                        }

                        else if(spritePersonaje.getY()>700){
                            if(pValueY>0){
                            }
                            else{
                                float y = spritePersonaje.getY() + 20 * pValueY;
                                spritePersonaje.setY(y);
                            }
                        }

                        else{
                            float y = spritePersonaje.getY() + 20 * pValueY;
                            spritePersonaje.setY(y);
                        }

                    }


                    if (pValueX > 0 && estoySaltando==false) {
                            personajeVolteandoDerecha = true;
                            spritePersonajeDerecha.setFlippedHorizontal(false);
                            spritePersonajeDerecha.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje=spritePersonajeDerecha;
                            attachChild(spritePersonaje);

                    }
                    else if (pValueX > 0 && estoySaltando==true) {
                        personajeVolteandoDerecha = true;
                        spritePersonaje.setFlippedHorizontal(false);
                    }
                    else if (pValueX < 0 && estoySaltando==false) {
                            personajeVolteandoDerecha = false;
                            spritePersonajeDerecha.setFlippedHorizontal(true);
                            spritePersonajeDerecha.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje=spritePersonajeDerecha;
                            attachChild(spritePersonaje);

                    }
                    else if (pValueX < 0 && estoySaltando==true) {
                        personajeVolteandoDerecha = false;
                        spritePersonaje.setFlippedHorizontal(true);
                    }
                    else{

                        if(estoySaltando==false) {

                            if (personajeVolteandoDerecha == false) {
                                spritePersonajeParado.setFlippedHorizontal(true);
                            } else {
                                spritePersonajeParado.setFlippedHorizontal(false);
                            }
                            spritePersonajeParado.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje = spritePersonajeParado;
                            attachChild(spritePersonaje);
                        }
                    }

                    if(spritePersonaje.getX()>200008){
                        if(pValueX>0){
                        }
                        else{
                            float x = spritePersonaje.getX()+22*pValueX;
                            spritePersonaje.setX(x);
                        }
                    }

                    else if(spritePersonaje.getX()<630){
                        if(pValueX<0){
                        }
                        else{
                            float x = spritePersonaje.getX()+22*pValueX;
                            spritePersonaje.setX(x);
                        }
                    }

                    else{
                        float x = spritePersonaje.getX()+22*pValueX;
                        spritePersonaje.setX(x);
                    }

                    actividadJuego.camara.setCenter(spritePersonaje.getX(), ControlJuego.ALTO_CAMARA / 2);

                }
            }

        });
        EscenaCazaJurasicaNivel2.this.setChildScene(control);
    }

    private void posicionarEnemigos(){

        int maxima=22;

        posicionesEnemigosx = new float[maxima];
        posicionesEnemigosy = new float[maxima];
        brinca = new boolean[maxima];
        camina = new int[maxima];
        rota = new boolean[maxima];
        numPasos = new int[maxima];
        rotaCamina = new boolean[maxima];
        disparar = new boolean[maxima];
        noPuedeSerDestruido =new boolean[maxima];

        posicionesEnemigosx[0]=2820;
        posicionesEnemigosx[1]=3810;
        posicionesEnemigosx[2]=5510;
        posicionesEnemigosx[3]=6010;
        posicionesEnemigosx[4]=9810;
        posicionesEnemigosx[5]=12220;
        posicionesEnemigosx[6]=13500;
        posicionesEnemigosx[7]=14780;
        posicionesEnemigosx[8]=16060;
        posicionesEnemigosx[9]=17340;
        posicionesEnemigosx[10]=18620;
        posicionesEnemigosx[11]=19900;
        posicionesEnemigosx[12]=21180;
        posicionesEnemigosx[13]=22419;
        posicionesEnemigosx[14]=23846;
        posicionesEnemigosx[15]=23996;
        posicionesEnemigosx[16]=24446;
        posicionesEnemigosx[17]=24596;
        posicionesEnemigosx[18]=24746;
        posicionesEnemigosx[19]=22449;
        posicionesEnemigosx[20]=22460;
        posicionesEnemigosx[21]=23740;

        posicionesEnemigosy[0]=334;
        posicionesEnemigosy[1]=80;
        posicionesEnemigosy[2]=200;
        posicionesEnemigosy[3]=130;
        posicionesEnemigosy[4]=730;
        posicionesEnemigosy[5]=80;
        posicionesEnemigosy[6]=80;
        posicionesEnemigosy[7]=80;
        posicionesEnemigosy[8]=80;
        posicionesEnemigosy[9]=80;
        posicionesEnemigosy[10]=80;
        posicionesEnemigosy[11]=80;
        posicionesEnemigosy[12]=80;
        posicionesEnemigosy[13]=290;

        posicionesEnemigosy[14]=660;
        posicionesEnemigosy[15]=660;
        posicionesEnemigosy[16]=660;
        posicionesEnemigosy[17]=660;
        posicionesEnemigosy[18]=660;
        posicionesEnemigosy[19]=290;
        posicionesEnemigosy[20]=80;
        posicionesEnemigosy[21]=80;

        brinca[0]=false;
        brinca[1]=false;
        brinca[2]=true;
        brinca[3]=false;
        brinca[4]=false;
        brinca[5]=false;
        brinca[6]=false;
        brinca[7]=false;
        brinca[8]=false;
        brinca[9]=false;
        brinca[10]=false;
        brinca[11]=false;
        brinca[12]=false;
        brinca[13]=false;
        brinca[14]=false;
        brinca[15]=false;
        brinca[16]=false;
        brinca[17]=false;
        brinca[18]=false;
        brinca[19]=false;
        brinca[20]=false;
        brinca[21]=false;

        camina[0]=1;
        camina[1]=4;
        camina[2]=4;
        camina[3]=0;
        camina[4]=1;
        camina[5]=4;
        camina[6]=4;
        camina[7]=4;
        camina[8]=4;
        camina[9]=4;
        camina[10]=4;
        camina[11]=4;
        camina[12]=4;
        camina[13]=4;

        camina[14]=4;
        camina[15]=4;
        camina[16]=4;
        camina[17]=4;
        camina[18]=4;
        camina[19]=4;
        camina[20]=4;
        camina[21]=4;

        rota[0]=false;
        rota[1]=false;
        rota[2]=true;
        rota[3]=true;
        rota[4]=false;
        rota[5]=false;
        rota[6]=false;
        rota[7]=false;
        rota[8]=false;
        rota[9]=false;
        rota[10]=false;
        rota[11]=false;
        rota[12]=false;
        rota[13]=false;

        rota[14]=false;
        rota[15]=false;
        rota[16]=false;
        rota[17]=false;
        rota[18]=false;
        rota[19]=false;
        rota[20]=false;
        rota[21]=false;

        numPasos[0]=20;
        numPasos[1]=1;
        numPasos[2]=1;
        numPasos[3]=30;
        numPasos[4]=30;
        numPasos[5]=30;
        numPasos[6]=30;
        numPasos[7]=30;
        numPasos[8]=30;
        numPasos[9]=30;
        numPasos[10]=30;
        numPasos[11]=30;
        numPasos[12]=30;
        numPasos[13]=30;

        numPasos[14]=30;
        numPasos[15]=30;
        numPasos[16]=30;
        numPasos[17]=30;
        numPasos[18]=30;
        numPasos[19]=30;
        numPasos[20]=30;
        numPasos[21]=30;

        rotaCamina[0]=false;
        rotaCamina[1]=false;
        rotaCamina[2]=false;
        rotaCamina[3]=true;
        rotaCamina[4]=false;
        rotaCamina[5]=false;
        rotaCamina[6]=false;
        rotaCamina[7]=false;
        rotaCamina[8]=false;
        rotaCamina[9]=false;
        rotaCamina[10]=false;
        rotaCamina[11]=false;
        rotaCamina[12]=false;
        rotaCamina[13]=false;

        rotaCamina[14]=false;
        rotaCamina[15]=false;
        rotaCamina[16]=false;
        rotaCamina[17]=false;
        rotaCamina[18]=false;
        rotaCamina[19]=false;
        rotaCamina[20]=false;
        rotaCamina[21]=false;

        disparar[0]=false;
        disparar[1]=false;
        disparar[2]=false;
        disparar[3]=false;
        disparar[4]=true;
        disparar[5]=false;
        disparar[6]=false;
        disparar[7]=false;
        disparar[8]=false;
        disparar[9]=false;
        disparar[10]=false;
        disparar[11]=false;
        disparar[12]=false;
        disparar[13]=false;
        disparar[14]=false;
        disparar[15]=false;
        disparar[16]=false;
        disparar[17]=false;
        disparar[18]=false;
        disparar[19]=false;
        disparar[20]=false;
        disparar[21]=false;


        noPuedeSerDestruido[0]=false;
        noPuedeSerDestruido[1]=true;
        noPuedeSerDestruido[2]=false;
        noPuedeSerDestruido[3]=false;
        noPuedeSerDestruido[4]=false;
        noPuedeSerDestruido[5]=true;
        noPuedeSerDestruido[6]=true;
        noPuedeSerDestruido[7]=true;
        noPuedeSerDestruido[8]=true;
        noPuedeSerDestruido[9]=true;
        noPuedeSerDestruido[10]=true;
        noPuedeSerDestruido[11]=true;
        noPuedeSerDestruido[12]=true;
        noPuedeSerDestruido[13]=false;
        noPuedeSerDestruido[14]=false;
        noPuedeSerDestruido[15]=false;
        noPuedeSerDestruido[16]=false;
        noPuedeSerDestruido[17]=false;
        noPuedeSerDestruido[18]=false;
        noPuedeSerDestruido[19]=false;
        noPuedeSerDestruido[20]=true;
        noPuedeSerDestruido[21]=true;


        int cont;
        for( cont = 0; cont< posicionesEnemigosx.length;cont++){
            if(cont==1 || cont==5 || cont==6 || cont==7 || cont==8 || cont==9 || cont==10 || cont==11 || cont==12 || cont==20 || cont==21){
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionLava, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                if(cont==6 || cont==8 || cont==10 || cont == 12 || cont == 21){
                    nuevoEnemigo.setFlippedHorizontal(true);
                }
                spriteFondo.attachChild(nuevoEnemigo);
            }
            else if(cont==4) {
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionOvni, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                attachChild(nuevoEnemigo);
            }
            else if(cont==0 || cont==2){
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionPua, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                attachChild(nuevoEnemigo);
            }
            else if(cont==13 || cont==14 || cont==15 || cont==16 || cont==17 || cont==18 || cont==19){
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionPicos, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                if(cont==14 || cont==15 || cont==16 || cont==17 || cont==18){
                    nuevoEnemigo.setRotation(180);
                }
                attachChild(nuevoEnemigo);
            }
            else{
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionEnemigo, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                attachChild(nuevoEnemigo);
            }
        }
    }



    private void posicionarPisosFlotantes(){
        int maxima=72;
        posicionesPisosFlotantesX= new float [maxima];
        posicionesPisosFlotantesY=new float[maxima];
        comportamientoPiso=new int[maxima];
        numPasosPiso=new int[maxima];
        listaPisos= new Piso [maxima];

        posicionesPisosFlotantesX[0]=1700;
        //primera piramide
        posicionesPisosFlotantesX[1]=2170;
        posicionesPisosFlotantesX[2]=2430;
        posicionesPisosFlotantesX[3]=2690;
        posicionesPisosFlotantesX[4]=2950;
        posicionesPisosFlotantesX[5]=2430;
        posicionesPisosFlotantesX[6]=2690;
        posicionesPisosFlotantesX[7]=2950;
        posicionesPisosFlotantesX[8]=2690;
        posicionesPisosFlotantesX[9]=2950;
        posicionesPisosFlotantesX[10]=2950;
        posicionesPisosFlotantesX[11]=5110;
        posicionesPisosFlotantesX[12]=4850;
        posicionesPisosFlotantesX[13]=4590;
        posicionesPisosFlotantesX[14]=4330;
        posicionesPisosFlotantesX[15]=4850;
        posicionesPisosFlotantesX[16]=4590;
        posicionesPisosFlotantesX[17]=4330;
        posicionesPisosFlotantesX[18]=4590;
        posicionesPisosFlotantesX[19]=4330;
        posicionesPisosFlotantesX[20]=4330;
        posicionesPisosFlotantesX[21]=3635;
        posicionesPisosFlotantesX[22]=5895;
        // fin primera piramide

        //segunda media piramide
        posicionesPisosFlotantesX[23]=7210;
        posicionesPisosFlotantesX[24]=7470;
        posicionesPisosFlotantesX[25]=7730;
        posicionesPisosFlotantesX[26]=7990;
        posicionesPisosFlotantesX[27]=7470;
        posicionesPisosFlotantesX[28]=7730;
        posicionesPisosFlotantesX[29]=7990;
        posicionesPisosFlotantesX[30]=7730;
        posicionesPisosFlotantesX[31]=7990;
        posicionesPisosFlotantesX[32]=7990;

        posicionesPisosFlotantesX[33]=8890;
        posicionesPisosFlotantesX[34]=9150;
        posicionesPisosFlotantesX[35]=9410;

        //tercera media piramide
        posicionesPisosFlotantesX[36]=10580;
        posicionesPisosFlotantesX[37]=10840;
        posicionesPisosFlotantesX[38]=11100;
        posicionesPisosFlotantesX[39]=11360;
        posicionesPisosFlotantesX[40]=10840;
        posicionesPisosFlotantesX[41]=11100;
        posicionesPisosFlotantesX[42]=11360;
        posicionesPisosFlotantesX[43]=11100;
        posicionesPisosFlotantesX[44]=11360;
        posicionesPisosFlotantesX[45]=11360;
        posicionesPisosFlotantesX[46]=12140;
        posicionesPisosFlotantesX[47]=13230;
        posicionesPisosFlotantesX[48]=14300;
        posicionesPisosFlotantesX[49]=14980;
        posicionesPisosFlotantesX[50]=16050;
        posicionesPisosFlotantesX[51]=17148;
        posicionesPisosFlotantesX[52]=17684;
        posicionesPisosFlotantesX[53]=18500;
        posicionesPisosFlotantesX[54]=19609;
        posicionesPisosFlotantesX[55]=18855;
        posicionesPisosFlotantesX[56]=20466;
        posicionesPisosFlotantesX[57]=21326;
        posicionesPisosFlotantesX[58]=21586;
        posicionesPisosFlotantesX[59]=21846;
        posicionesPisosFlotantesX[60]=22106;
        posicionesPisosFlotantesX[61]=22366;
        posicionesPisosFlotantesX[62]=21253;
        posicionesPisosFlotantesX[63]=22319;
        posicionesPisosFlotantesX[64]=22419;

        posicionesPisosFlotantesX[65]=23846;
        posicionesPisosFlotantesX[66]=24106;
        posicionesPisosFlotantesX[67]=24366;
        posicionesPisosFlotantesX[68]=24626;
        posicionesPisosFlotantesX[69]=24886;
        posicionesPisosFlotantesX[70]=22449;
        posicionesPisosFlotantesX[71]=24270;

        posicionesPisosFlotantesY[0]=330;
        //primera piramide
        posicionesPisosFlotantesY[1]=44;
        posicionesPisosFlotantesY[2]=44;
        posicionesPisosFlotantesY[3]=44;
        posicionesPisosFlotantesY[4]=44;
        posicionesPisosFlotantesY[5]=140;
        posicionesPisosFlotantesY[6]=140;
        posicionesPisosFlotantesY[7]=140;
        posicionesPisosFlotantesY[8]=238;
        posicionesPisosFlotantesY[9]=238;
        posicionesPisosFlotantesY[10]=334;
        posicionesPisosFlotantesY[11]=44;
        posicionesPisosFlotantesY[12]=44;
        posicionesPisosFlotantesY[13]=44;
        posicionesPisosFlotantesY[14]=44;
        posicionesPisosFlotantesY[15]=140;
        posicionesPisosFlotantesY[16]=140;
        posicionesPisosFlotantesY[17]=140;
        posicionesPisosFlotantesY[18]=238;
        posicionesPisosFlotantesY[19]=238;
        posicionesPisosFlotantesY[20]=334;
        posicionesPisosFlotantesY[21]=334;
        posicionesPisosFlotantesY[22]=400;

        // fin primera piramide

        //segunda media piramide

        posicionesPisosFlotantesY[23]=132;
        posicionesPisosFlotantesY[24]=132;
        posicionesPisosFlotantesY[25]=132;
        posicionesPisosFlotantesY[26]=132;
        posicionesPisosFlotantesY[27]=230;
        posicionesPisosFlotantesY[28]=230;
        posicionesPisosFlotantesY[29]=230;
        posicionesPisosFlotantesY[30]=328;
        posicionesPisosFlotantesY[31]=328;
        posicionesPisosFlotantesY[32]=426;

        posicionesPisosFlotantesY[33]=600;
        posicionesPisosFlotantesY[34]=600;
        posicionesPisosFlotantesY[35]=600;

        //tercera media piramide
        posicionesPisosFlotantesY[36]=44;
        posicionesPisosFlotantesY[37]=44;
        posicionesPisosFlotantesY[38]=44;
        posicionesPisosFlotantesY[39]=44;
        posicionesPisosFlotantesY[40]=140;
        posicionesPisosFlotantesY[41]=140;
        posicionesPisosFlotantesY[42]=140;
        posicionesPisosFlotantesY[43]=238;
        posicionesPisosFlotantesY[44]=238;
        posicionesPisosFlotantesY[45]=334;
        posicionesPisosFlotantesY[46]=400;
        posicionesPisosFlotantesY[47]=400;
        posicionesPisosFlotantesY[48]=250;
        posicionesPisosFlotantesY[49]=500;
        posicionesPisosFlotantesY[50]=300;
        posicionesPisosFlotantesY[51]=200;
        posicionesPisosFlotantesY[52]=400;
        posicionesPisosFlotantesY[53]=600;
        posicionesPisosFlotantesY[54]=500;
        posicionesPisosFlotantesY[55]=250;
        posicionesPisosFlotantesY[56]=200;
        posicionesPisosFlotantesY[57]=760;
        posicionesPisosFlotantesY[58]=760;
        posicionesPisosFlotantesY[59]=760;
        posicionesPisosFlotantesY[60]=760;
        posicionesPisosFlotantesY[61]=760;
        posicionesPisosFlotantesY[62]=200;
        posicionesPisosFlotantesY[63]=500;
        posicionesPisosFlotantesY[64]=200;

        posicionesPisosFlotantesY[65]=760;
        posicionesPisosFlotantesY[66]=760;
        posicionesPisosFlotantesY[67]=760;
        posicionesPisosFlotantesY[68]=760;
        posicionesPisosFlotantesY[69]=760;
        posicionesPisosFlotantesY[70]=200;
        posicionesPisosFlotantesY[71]=200;


        comportamientoPiso[0]=0;
        comportamientoPiso[1]=4;
        comportamientoPiso[2]=4;
        comportamientoPiso[3]=4;
        comportamientoPiso[4]=4;
        comportamientoPiso[5]=4;
        comportamientoPiso[6]=4;
        comportamientoPiso[7]=4;
        comportamientoPiso[8]=4;
        comportamientoPiso[9]=4;
        comportamientoPiso[10]=4;
        comportamientoPiso[11]=4;
        comportamientoPiso[12]=4;
        comportamientoPiso[13]=4;
        comportamientoPiso[14]=4;
        comportamientoPiso[15]=4;
        comportamientoPiso[16]=4;
        comportamientoPiso[17]=4;
        comportamientoPiso[18]=4;
        comportamientoPiso[19]=4;
        comportamientoPiso[20]=4;
        comportamientoPiso[21]=0;
        comportamientoPiso[22]=2;
        comportamientoPiso[23]=4;
        comportamientoPiso[24]=4;
        comportamientoPiso[25]=4;
        comportamientoPiso[26]=4;
        comportamientoPiso[27]=4;
        comportamientoPiso[28]=4;
        comportamientoPiso[29]=4;
        comportamientoPiso[30]=4;
        comportamientoPiso[31]=4;
        comportamientoPiso[32]=4;
        comportamientoPiso[33]=4;
        comportamientoPiso[34]=4;
        comportamientoPiso[35]=4;
        comportamientoPiso[36]=4;
        comportamientoPiso[37]=4;
        comportamientoPiso[38]=4;
        comportamientoPiso[39]=4;
        comportamientoPiso[40]=4;
        comportamientoPiso[41]=4;
        comportamientoPiso[42]=4;
        comportamientoPiso[43]=4;
        comportamientoPiso[44]=4;
        comportamientoPiso[45]=4;
        comportamientoPiso[46]=4;
        comportamientoPiso[47]=0;
        comportamientoPiso[48]=1;
        comportamientoPiso[49]=0;
        comportamientoPiso[50]=1;
        comportamientoPiso[51]=4;
        comportamientoPiso[52]=4;
        comportamientoPiso[53]=4;
        comportamientoPiso[54]=2;
        comportamientoPiso[55]=4;
        comportamientoPiso[56]=0;
        comportamientoPiso[57]=4;
        comportamientoPiso[58]=4;
        comportamientoPiso[59]=4;
        comportamientoPiso[60]=4;
        comportamientoPiso[61]=4;
        comportamientoPiso[62]=10;
        comportamientoPiso[63]=4;
        comportamientoPiso[64]=4;
        comportamientoPiso[65]=4;
        comportamientoPiso[66]=4;
        comportamientoPiso[67]=4;
        comportamientoPiso[68]=4;
        comportamientoPiso[69]=4;
        comportamientoPiso[70]=4;
        comportamientoPiso[71]=4;

        numPasosPiso[0]=50;
        numPasosPiso[1]=50;
        numPasosPiso[2]=50;
        numPasosPiso[3]=50;
        numPasosPiso[4]=50;
        numPasosPiso[5]=50;
        numPasosPiso[6]=50;
        numPasosPiso[7]=50;
        numPasosPiso[8]=50;
        numPasosPiso[9]=50;
        numPasosPiso[10]=50;
        numPasosPiso[11]=50;
        numPasosPiso[12]=50;
        numPasosPiso[13]=50;
        numPasosPiso[14]=50;
        numPasosPiso[15]=50;
        numPasosPiso[16]=50;
        numPasosPiso[17]=50;
        numPasosPiso[18]=50;
        numPasosPiso[19]=50;
        numPasosPiso[20]=50;
        numPasosPiso[21]=45;
        numPasosPiso[22]=45;
        numPasosPiso[23]=50;
        numPasosPiso[24]=50;
        numPasosPiso[25]=50;
        numPasosPiso[26]=50;
        numPasosPiso[27]=50;
        numPasosPiso[28]=50;
        numPasosPiso[29]=50;
        numPasosPiso[30]=50;
        numPasosPiso[31]=50;
        numPasosPiso[32]=50;
        numPasosPiso[33]=50;
        numPasosPiso[34]=50;
        numPasosPiso[35]=50;
        numPasosPiso[36]=50;
        numPasosPiso[37]=50;
        numPasosPiso[38]=50;
        numPasosPiso[39]=50;
        numPasosPiso[40]=50;
        numPasosPiso[41]=50;
        numPasosPiso[42]=50;
        numPasosPiso[43]=50;
        numPasosPiso[44]=50;
        numPasosPiso[45]=50;
        numPasosPiso[46]=50;
        numPasosPiso[47]=115;
        numPasosPiso[48]=35;
        numPasosPiso[49]=45;
        numPasosPiso[50]=75;
        numPasosPiso[51]=75;
        numPasosPiso[52]=75;
        numPasosPiso[53]=75;
        numPasosPiso[54]=45;
        numPasosPiso[55]=45;
        numPasosPiso[56]=65;
        numPasosPiso[57]=35;
        numPasosPiso[58]=35;
        numPasosPiso[59]=35;
        numPasosPiso[60]=35;
        numPasosPiso[61]=35;
        numPasosPiso[62]=35;
        numPasosPiso[63]=35;
        numPasosPiso[64]=35;
        numPasosPiso[65]=35;
        numPasosPiso[66]=35;
        numPasosPiso[67]=35;
        numPasosPiso[68]=35;
        numPasosPiso[69]=35;
        numPasosPiso[70]=35;
        numPasosPiso[71]=35;

        int cont;


        for(cont=0; cont<posicionesPisosFlotantesX.length;cont++){
            Piso spritePiso= new Piso(posicionesPisosFlotantesX[cont], posicionesPisosFlotantesY[cont], regionPisoFlotante,actividadJuego.getVertexBufferObjectManager(),comportamientoPiso[cont],numPasosPiso[cont]);
            listaPisos[cont]=spritePiso;
            attachChild(spritePiso);
        }

    }

    private void agregarMonedas() {
        // Agrega monedas a lo largo del mundo
        listaMonedas = new ArrayList<>();

        int maxima=10;

        posicionesMonedasX= new float[maxima];
        posicionesMonedasY= new float[maxima];

        posicionesMonedasX[0]=2000;
        posicionesMonedasX[1]=3635;
        posicionesMonedasX[2]=3335;
        posicionesMonedasX[3]=4035;
        posicionesMonedasX[4]=5935;
        posicionesMonedasX[5]=6135;
        posicionesMonedasX[6]=6235;
        posicionesMonedasX[7]=8890;
        posicionesMonedasX[8]=9150;
        posicionesMonedasX[9]=9410;

        posicionesMonedasY[0]=750;
        posicionesMonedasY[1]=454;
        posicionesMonedasY[2]=454;
        posicionesMonedasY[3]=454;
        posicionesMonedasY[4]=684;
        posicionesMonedasY[5]=684;
        posicionesMonedasY[6]=684;
        posicionesMonedasY[7]=720;
        posicionesMonedasY[8]=720;
        posicionesMonedasY[9]=720;

        for (int i=0; i<posicionesMonedasX.length; i++) {
            float x = posicionesMonedasX[i];
            float y = posicionesMonedasY[i];
            Sprite lechita = new Sprite(x,y,regionLechitas,actividadJuego.getVertexBufferObjectManager());
            spriteFondo.attachChild(lechita);
            listaMonedas.add(lechita);
        }
    }

    private void agregarBotonSalto() {
        btnSaltar = new ButtonSprite(1100,100,
                regionControlSalto,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (juegoCorriendo) {
                    if (pSceneTouchEvent.isActionDown() && enElAire==false) {

                        if(nubecita==true){
                            spriteNavecita.detachSelf();
                            nubecita=false;
                        }
                        estoySaltando=true;
                        permiso=true;
                        permiso2=true;
                        enElAire=true;
                        paArriba=true;
                        gravedad=false;
                        poderDeSalto=4.1f;
                        if(estoySobreUnaPalmera==true){
                            brincaSobrepalmera=true;
                        }
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        hud.registerTouchArea(btnSaltar);
        hud.attachChild(btnSaltar);
    }

    private void desaparecerMoneda(final Sprite monedaD) {
        ScaleModifier escala = new ScaleModifier(0.3f,1,0) {
            @Override
            protected void onModifierFinished(IEntity pItem) {
                unregisterEntityModifier(this);
                super.onModifierFinished(pItem);
            }
        };
        monedaD.registerEntityModifier(escala);
    }

    private void actualizarMonedas() {
        for (int i=listaMonedas.size()-1; i>=0; i--) {
            final Sprite moneda = listaMonedas.get(i);
            moneda.setRotation(moneda.getRotation()+5);
            // Prueba colisión
            if (spritePersonaje.collidesWith(moneda)) {
                // Desaparecer moneda
                animacionTexto();
                desaparecerMoneda(moneda);
            }
            // Salen las monedas que han desaparecido
            if (moneda.getScaleX()==0) {
                valorMarcador += 100;
                moneda.detachSelf();
                listaMonedas.remove(moneda);
            }
        }
    }


    private void agregarBotonDisparar() {
        btnDisparar = new ButtonSprite(1190,200,
                regionControlSalto,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (juegoCorriendo) {
                    if(pSceneTouchEvent.isActionDown()){

                        if(numeroDeBalas>=0) {
                            dispararProyectil();
                            admMusica.vibrar(100);
                            admMusica.reproducirMusicaBoton();
                            int posicionBala=9-numeroDeBalas;
                            spriteBalas[posicionBala].detachSelf();
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

    private void actualizarProyectiles(float tiempo) {

        // Se visita cada proyectil dentro de la lista, se recorre con el índice
        // porque se pueden borrar datos
        for (int i = listaProyectiles.size() - 1; i>=0; i--) {
            Laser proyectil = listaProyectiles.get(i);
            proyectil.mover();

            if (proyectil.getX()-spritePersonaje.getX() > 650 || proyectil.getX()-spritePersonaje.getX() < -650) {
                detachChild(proyectil);
                listaProyectiles.remove(proyectil);
                continue;
            }
            // Probar si colisionó con un enemigo
            // Se visita cada proyectil dentro de la lista, se recorre con el índice
            // porque se pueden borrar datos
            for (int k = listaEnemigos.size() - 1; k >= 0; k--) {
                Enemigo enemigo = listaEnemigos.get(k);
                if (proyectil.collidesWith(enemigo)) {
                    // Lo destruye

                    if(enemigo.getNoPuedeSerDestruido()==false){
                        if (enemigo.getRegalo() == 1 || enemigo.getRegalo() == 3) {
                            crearVida(enemigo);
                        }

                        if(enemigo.getPersigue()){
                            unicoQuePersigue=false;
                        }
                        enemigo.detachSelf();
                        listaEnemigos.remove(enemigo);
                    }
                    // desaparece el proyectil
                    detachChild(proyectil);
                    listaProyectiles.remove(proyectil);
                    break;

                }
            }
        }
    }

    private void animacionTexto(){

        IFont tipo = cargarFont("fonts/monster.ttf");
        final Text txtPuntos = new Text(spritePersonaje.getX(),spritePersonaje.getY(),tipo,"+$1",12,actividadJuego.getVertexBufferObjectManager());
        txtPuntos.setColor(0,0,0);
        attachChild(txtPuntos);

        MoveYModifier modY = new MoveYModifier(1, txtPuntos.getY(),txtPuntos.getY()+60){
            @Override
            protected void onModifierStarted(IEntity pItem)
            {
                super.onModifierStarted(pItem);

            }
            @Override
            protected void onModifierFinished(IEntity pItem)
            {
                super.onModifierFinished(pItem);
                txtPuntos.setText("");
            }
        };
        txtPuntos.registerEntityModifier(modY);
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
        Laser spriteProyectil = new Laser(spritePersonaje.getX(),  spritePersonaje.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),personajeVolteandoDerecha,false);

        if(personajeVolteandoDerecha==false){
            spriteProyectil.setRotation(-180);
        }
        attachChild(spriteProyectil);   // Lo agrega a la escena
        listaProyectiles.add(spriteProyectil);  // Lo agrega a la lista
    }


    private void crearVida(Enemigo enemigoDestruido) {

        // Crearlo
        Vida spriteVida = new Vida(enemigoDestruido.getX(), enemigoDestruido.getY(),regionVida, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteVida);
        listaVidasEncontradas.add(spriteVida);  // Lo agrega a la lista
    }
}