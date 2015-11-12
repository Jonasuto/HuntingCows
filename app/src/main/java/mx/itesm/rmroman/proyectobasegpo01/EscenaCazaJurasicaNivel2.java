package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.CameraScene;
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
    private Sprite[] listaPisos;

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

    private Sprite spriteOvni2;
    private Sprite spriteNavecita;
    private Sprite spritePua;

    private int contadorTiempo;


    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Jugador spritePersonajeizquierda;
    private Jugador spritePersonajeDerecha;

    private Enemigo spriteEnemigo;

    private boolean juegoCorriendo = true;

    private Sprite pisoActual;

    private ArrayList<Laser> listaProyectilesEnemigo;


    private CameraScene escenaPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;
    private TiledTextureRegion regionPersonajeAnimadoIzquierda;

    private boolean personajeVolteandoDerecha=true;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondo2;
    private Sprite spriteFondo4;
    private Sprite spriteFondo3;
    private Sprite spriteHoyoNegro;
    private Sprite spriteFondoPausa;


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

    private int cambiar=0;


    @Override
    public void cargarRecursos() {


        elQueSigue=new Random();
        poderDeSalto=4.1f;

        cantidadVida = 2;

        estoySobreUnaPalmera=false;

        regionLechitas = cargarImagen("Imagenes/leche.png");

        fontMonster = cargarFont("fonts/monster.ttf");

        vidas=cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionNave=cargarImagen("Imagenes/Roman/nave.png");
        regionFondo = cargarImagen("Imagenes/Niveles/CazaJurasica/fondos/fondo2.jpg");
        regionFondoPausa = cargarImagen("Imagenes/Logos/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/Roman/joystick.png");
        regionEnemigo = cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/vacaDinosaurio.png");
        regionVida = cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/Roman/prueba.png", 1000, 200, 1, 9);
        regionPersonajeAnimadoIzquierda = cargarImagenMosaico("Imagenes/Roman/pruebaiz.png", 1000, 200, 1, 9);

        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionHoyoNegro= cargarImagen("Imagenes/hoyoNegro.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
        regionPisoFlotante = cargarImagen("Imagenes/lineas.png");


        regionOvni= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/naveVaca.png");
        regionPua= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/pua.png");
        regionPicos= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/picos.png");

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

        reloj=0;

        contadorTiempo=0;

        listaProyectiles = new ArrayList<>();

        listaProyectilesEnemigo = new ArrayList<>();

        listaEnemigos = new ArrayList<>();

        listaVidasEncontradas = new ArrayList<>();

        spriteFondo = cargarSprite(2046, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteFondo2 = cargarSprite(6000, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo2);

        spriteFondo3 = cargarSprite(9954, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo3);

        spriteFondo4 = cargarSprite(13908, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo4);

        spriteOvni2=cargarSprite(10185, 300, regionOvni);
        spriteOvni2.setSize(spriteOvni2.getWidth() - 30, spriteOvni2.getHeight() - 30);
        spriteFondo.attachChild(spriteOvni2);

        spriteVidas= new Sprite[3];

        spriteBalas= new Sprite[10];

        numeroDeBalas=9;

        spriteNave=cargarSprite(280, 270, regionNave);
        spriteFondo.attachChild(spriteNave);

        spriteVidas[0]= cargarSprite(1100, 750, vidas);
        attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(1150, 750, vidas);
        attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1200, 750, vidas);
        attachChild(spriteVidas[2]);


        spriteBalas[0]= cargarSprite(1100, 600, regionProyectil);
        spriteBalas[0].setSize(spriteBalas[0].getWidth() - 20, spriteBalas[0].getHeight() - 20);
        attachChild(spriteBalas[0]);

        spriteBalas[1]= cargarSprite(1100, 550, regionProyectil);
        spriteBalas[1].setSize(spriteBalas[1].getWidth() - 20, spriteBalas[1].getHeight() - 20);
        attachChild(spriteBalas[1]);

        spriteBalas[2]= cargarSprite(1100, 500, regionProyectil);
        spriteBalas[2].setSize(spriteBalas[2].getWidth() - 20, spriteBalas[2].getHeight() - 20);
        attachChild(spriteBalas[2]);

        spriteBalas[3]= cargarSprite(1100, 450, regionProyectil);
        spriteBalas[3].setSize(spriteBalas[3].getWidth() - 20, spriteBalas[3].getHeight() - 20);
        attachChild(spriteBalas[3]);

        spriteBalas[4]= cargarSprite(1100, 400, regionProyectil);
        spriteBalas[4].setSize(spriteBalas[4].getWidth() - 20, spriteBalas[4].getHeight() - 20);
        attachChild(spriteBalas[4]);

        spriteBalas[5]= cargarSprite(1100, 350, regionProyectil);
        spriteBalas[5].setSize(spriteBalas[5].getWidth() - 20, spriteBalas[5].getHeight() - 20);
        attachChild(spriteBalas[5]);

        spriteBalas[6]= cargarSprite(1100, 300, regionProyectil);
        spriteBalas[6].setSize(spriteBalas[6].getWidth() - 20, spriteBalas[6].getHeight() - 20);
        attachChild(spriteBalas[6]);

        spriteBalas[7]= cargarSprite(1100, 250, regionProyectil);
        spriteBalas[7].setSize(spriteBalas[7].getWidth() - 20, spriteBalas[7].getHeight() - 20);
        attachChild(spriteBalas[7]);

        spriteBalas[8]= cargarSprite(1100, 200, regionProyectil);
        spriteBalas[8].setSize(spriteBalas[8].getWidth() - 20, spriteBalas[8].getHeight() - 20);
        attachChild(spriteBalas[8]);

        spriteBalas[9] = cargarSprite(1100, 150, regionProyectil);
        spriteBalas[9].setSize(spriteBalas[9].getWidth() - 20, spriteBalas[9].getHeight() - 20);
        attachChild(spriteBalas[9]);


        pisoActual= cargarSprite(10, 1500 , regionPisoFlotante);
        spriteFondo.attachChild(pisoActual);


        spriteHoyoNegro= cargarSprite(14700, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        aleatorio= new Random();

        spritePersonajeizquierda = new Jugador((ControlJuego.ANCHO_CAMARA/2)-200, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeAnimadoIzquierda, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeizquierda.animate(70);

        spritePersonajeDerecha = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeDerecha.animate(70);

        spritePersonaje=spritePersonajeDerecha;
        attachChild(spritePersonaje);

        spriteNavecita=null;

        admMusica.cargarMusica(2);

        posicionarEnemigos();

        posicionarPisosFlotantes();

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
        attachChild(btnPausa);
        registerTouchArea(btnPausa);

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
        spriteOffFinal.setAlpha(0.2f);
        escenaPausa.attachChild(spriteOffFinal);
        registerTouchArea(spriteOffFinal);



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
        spriteOnFinal.setAlpha(0.2f);
        escenaPausa.attachChild(spriteOnFinal);
        registerTouchArea(spriteOnFinal);

        // Agregar monedas
        agregarMonedas();

        agregarHUD();


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
                    attachChild(spriteBalas[0]);
                }


                else if(posicionBala==1) {
                    spriteBalas[1] = cargarSprite(1100, 550, regionProyectil);
                    spriteBalas[1].setSize(spriteBalas[1].getWidth() - 20, spriteBalas[1].getHeight() - 20);
                    attachChild(spriteBalas[1]);
                }

                else if(posicionBala==2) {
                    spriteBalas[2] = cargarSprite(1100, 500, regionProyectil);
                    spriteBalas[2].setSize(spriteBalas[2].getWidth() - 20, spriteBalas[2].getHeight() - 20);
                    attachChild(spriteBalas[2]);
                }

                else if(posicionBala==3) {
                    spriteBalas[3] = cargarSprite(1100, 450, regionProyectil);
                    spriteBalas[3].setSize(spriteBalas[3].getWidth() - 20, spriteBalas[3].getHeight() - 20);
                    attachChild(spriteBalas[3]);
                }

                else if(posicionBala==4) {
                    spriteBalas[4] = cargarSprite(1100, 400, regionProyectil);
                    spriteBalas[4].setSize(spriteBalas[4].getWidth() - 20, spriteBalas[4].getHeight() - 20);
                    attachChild(spriteBalas[4]);
                }

                else if(posicionBala==5) {
                    spriteBalas[5] = cargarSprite(1100, 350, regionProyectil);
                    spriteBalas[5].setSize(spriteBalas[5].getWidth() - 20, spriteBalas[5].getHeight() - 20);
                    attachChild(spriteBalas[5]);
                }

                else if(posicionBala==6) {
                    spriteBalas[6] = cargarSprite(1100, 300, regionProyectil);
                    spriteBalas[6].setSize(spriteBalas[6].getWidth() - 20, spriteBalas[6].getHeight() - 20);
                    attachChild(spriteBalas[6]);
                }

                else if(posicionBala==7) {
                    spriteBalas[7] = cargarSprite(1100, 250, regionProyectil);
                    spriteBalas[7].setSize(spriteBalas[7].getWidth() - 20, spriteBalas[7].getHeight() - 20);
                    attachChild(spriteBalas[7]);
                }

                else if(posicionBala==8) {
                    spriteBalas[8] = cargarSprite(1100, 200, regionProyectil);
                    spriteBalas[8].setSize(spriteBalas[8].getWidth() - 20, spriteBalas[8].getHeight() - 20);
                    attachChild(spriteBalas[8]);
                }


                else if(posicionBala==9) {
                    spriteBalas[9] = cargarSprite(1100, 150, regionProyectil);
                    spriteBalas[9].setSize(spriteBalas[9].getWidth() - 20, spriteBalas[9].getHeight() - 20);
                    attachChild(spriteBalas[9]);
                }


                contadorTiempo=0;
                numeroDeBalas++;
            }
        }


        for (int i= listaPisos.length-1; i>=0; i--) {

            final Sprite piso = listaPisos[i];

            if (spritePersonaje.collidesWith(piso) && spritePersonaje.getY()-130>piso.getY()) {


                pisoActual.setPosition(piso);


                if (nubecita == false) {

                    estoySobreUnaPalmera = true;

                    if (brincaSobrepalmera == false) {

                        enElAire = false;
                        paArriba = false;
                        gravedad = true;
                        spritePersonaje.setY(piso.getY()+150);
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
                    pisoActual.setPosition(10,1500);
                }
            }
        }

        DecimalFormat df = new DecimalFormat("##.##"); // Para formatear 2 decimales
        txtMarcador.setText(df.format(valorMarcador));

        if(spritePersonaje.collidesWith(spriteOvni2)){

            nubecita=true;
            spriteOvni2.detachSelf();
            spriteNavecita=cargarSprite(spritePersonaje.getX(), spritePersonaje.getY(), regionOvni);
            spriteNavecita.setSize(spriteNavecita.getWidth() - 30, spriteNavecita.getHeight() - 30);
            attachChild(spriteNavecita);

        }

        actualizarMonedas();

        actualizarProyectilesEnemigo();

        if(nubecita==true){
            spriteNavecita.setPosition(spritePersonaje.getX(), spritePersonaje.getY());
        }



        if(spritePersonaje.collidesWith(spriteHoyoNegro)){
            admEscenas.setcazaJurasicaDesbloqueado(true);
            admEscenas.liberarEscenaCazaJurasicaNivel2();
            admEscenas.crearEscenaMenu();
            admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        }

        if(gravedad==true && nubecita==false){
            if(spritePersonaje.getY()>ControlJuego.ALTO_CAMARA/4-20 && estoySobreUnaPalmera==false){
                spritePersonaje.setY(spritePersonaje.getY()-20);
                if(spritePersonaje.getY()<=ControlJuego.ALTO_CAMARA/4-20){
                    gravedad=false;
                }
            }
        }
        if(enElAire==true && nubecita==false){

            if(paArriba){
                spritePersonaje.setY(spritePersonaje.getY() + (5*poderDeSalto));
                poderDeSalto-=0.15f;
                if(poderDeSalto<0){
                    brincaSobrepalmera=false;
                }
                if(spritePersonaje.getY()<=ControlJuego.ALTO_CAMARA/4-20){
                    enElAire=false;
                    poderDeSalto=4.1f;
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

            if (spritePersonaje.collidesWith(enemigo)) {

                if(cantidadVida -1<0){
                    admEscenas.liberarEscenaCazaJurasicaNivel2();
                    admEscenas.crearEscenaPerdiste();
                    admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                }
                else{
                    enemigo.detachSelf();
                    listaEnemigos.remove(enemigo);
                    admMusica.vibrar(200);


                    if(nubecita==true){
                        spriteNavecita.detachSelf();
                        nubecita=false;
                        poderDeSalto=4.1f;
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
                        poderDeSalto=4.1f;
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
        regionPersonajeAnimadoIzquierda.getTexture().unload();
        regionPersonajeAnimadoIzquierda=null;
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


                    if (pValueX > 0) {
                        personajeVolteandoDerecha = true;
                        if(spritePersonaje!=spritePersonajeDerecha){
                            spritePersonajeDerecha.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje=spritePersonajeDerecha;
                            attachChild(spritePersonaje);
                        }
                    }

                    else if (pValueX < 0) {
                        personajeVolteandoDerecha = false;
                        if(spritePersonaje!=spritePersonajeizquierda){
                            spritePersonajeizquierda.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje=spritePersonajeizquierda;
                            attachChild(spritePersonaje);
                        }
                    }

                    if(spritePersonaje.getX()>20008){
                        if(pValueX>0){
                        }
                        else{
                            float x = spritePersonaje.getX()+22*pValueX;
                            spritePersonaje.setX(x);
                        }
                    }

                    else if(spritePersonaje.getX()<610){
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

        int maxima=1;

        posicionesEnemigosx = new float[maxima];
        posicionesEnemigosy = new float[maxima];
        brinca = new boolean[maxima];
        camina = new int[maxima];
        rota = new boolean[maxima];
        numPasos = new int[maxima];
        rotaCamina = new boolean[maxima];
        disparar = new boolean[maxima];
        noPuedeSerDestruido =new boolean[maxima];

        posicionesEnemigosx[0]=2710;

        posicionesEnemigosy[0]=150;

        brinca[0]=false;

        camina[0]=1;

        rota[0]=false;

        numPasos[0]=40;

        rotaCamina[0]=true;

        disparar[0]=false;

        noPuedeSerDestruido[0]=false;

        int cont;
        for( cont = 0; cont< posicionesEnemigosx.length;cont++){
            if(cont==5 || cont==10) {
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionOvni, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                spriteFondo.attachChild(nuevoEnemigo);
            }
            else if(cont==6 || cont==7 || cont==9 || cont==12 || cont==15){
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionPua, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                spriteFondo.attachChild(nuevoEnemigo);
            }
            else if(cont==16 || cont==17 || cont==18 || cont==19 || cont==20 || cont==21 || cont==22){
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionPicos, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                spriteFondo.attachChild(nuevoEnemigo);
            }
            else{
                Enemigo spriteEnemigo = new Enemigo(posicionesEnemigosx[cont], posicionesEnemigosy[cont], regionEnemigo, actividadJuego.getVertexBufferObjectManager(), camina[cont], brinca[cont], rota[cont], numPasos[cont],rotaCamina[cont],disparar[cont], noPuedeSerDestruido[cont]);
                Enemigo nuevoEnemigo = spriteEnemigo;
                listaEnemigos.add(nuevoEnemigo);
                spriteFondo.attachChild(nuevoEnemigo);
            }
        }
    }



    private void posicionarPisosFlotantes(){
        int maxima=5;
        posicionesPisosFlotantesX= new float [maxima];
        posicionesPisosFlotantesY=new float[maxima];
        comportamientoPiso=new int[maxima];
        numPasosPiso=new int[maxima];
        listaPisos= new Piso [maxima];
    }

    private void agregarMonedas() {
        // Agrega monedas a lo largo del mundo
        listaMonedas = new ArrayList<>();

        int maxima=1;

        posicionesMonedasX= new float[maxima];
        posicionesMonedasY= new float[maxima];

        posicionesMonedasX[0]=2000;

        posicionesMonedasY[0]=440;

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

                        poderDeSalto=4.1f;
                        enElAire=true;
                        paArriba=true;
                        gravedad=false;
                        if(estoySobreUnaPalmera==true){
                            brincaSobrepalmera=true;
                        }
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(btnSaltar);
        attachChild(btnSaltar);
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

    private void actualizarVidas(float tiempo) {

        // Se visita cada proyectil dentro de la lista, se recorre con el índice
        // porque se pueden borrar datos

        for (int i = listaVidasEncontradas.size() - 1; i>=0; i--) {
            Vida vida = listaVidasEncontradas.get(i);

            if (vida.collidesWith(spritePersonaje)) {

                if(cantidadVida<2){

                    if(cantidadVida==0){
                        spriteVidas[1]= cargarSprite(1150, 750, vidas);
                        attachChild(spriteVidas[1]);
                        cantidadVida++;
                    }
                    else if(cantidadVida==1){
                        spriteVidas[0]= cargarSprite(1100, 750, vidas);
                        attachChild(spriteVidas[0]);
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