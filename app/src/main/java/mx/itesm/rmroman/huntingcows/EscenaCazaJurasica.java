package mx.itesm.rmroman.huntingcows;

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
public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionBase2;
    private ITextureRegion regionNave;
    private ITextureRegion regionHoyoNegro;
    private ITextureRegion regionOvni;
    private ITextureRegion regionNaveRoman;
    private ITextureRegion regionPua;
    private ITextureRegion regionPicos;

    private boolean terminoelJuego=false;
    private Random elQueSigue;

    private boolean permiso;
    private boolean permiso2;

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


    private Text txtMarcador; // Por ahora con valorMarcador
    private IFont fontMonster;
    private Text txtBalas; // Por ahora con valorMarcador

    private Text txtTotalBalas; // Por ahora con valorMarcador


    private HUD hud;

    private float valorMarcador;
    private int valorBalas;

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

    private Sprite spriteNave;

    private boolean estoySobreUnaPalmera;

    private float poderDeSalto;

    private Sprite spritePua;
    private Sprite spriteNaveRoman;

    private int contadorTiempo;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Jugador spritePersonajeParado;
    private Jugador spritePersonajeDerecha;
    private Jugador spritePersonajeSaltandoUno;
    private Jugador spritePersonajeSaltandoDos;
    private Jugador spritePersonajeNave;

    private Enemigo spriteEnemigo;

    private boolean juegoCorriendo = true;

    private Sprite pisoActual;

    private ArrayList<Laser> listaProyectilesEnemigo;


    private CameraScene escenaPausa;
    private ITextureRegion regionBtnPausa;

    private TiledTextureRegion regionPersonajeAnimado;
    private TiledTextureRegion regionPersonajeSaltandoUno;
    private TiledTextureRegion regionPersonajeSaltandoDos;
    private TiledTextureRegion regionPersonajeSaltandoCompleto;
    private TiledTextureRegion regionPersonajeParado;
    private TiledTextureRegion regionPersonajeNave;

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
    private ITextureRegion regionDisparar;

    private Sprite der;
    private Sprite iz;
    private Sprite up;
    private Sprite down;

    private ITextureRegion regionControlDer;
    private ITextureRegion regionControlIz;
    private ITextureRegion regionControlUp;
    private ITextureRegion regionControlDown;


    private boolean musicaEncendida;

    private Sprite spriteMenuPausa;
    private Sprite spritebtnOn;
    private Sprite spritebtnOff;
    private Sprite spritebtnMusica;
    private Sprite spritebtnContinuar;
    private Sprite spritebtnIrAMenu;

    private boolean yaBajo;

    private boolean estoySaltando;

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
        regionBase2=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionBase2.setTextureSize(1,1);
        regionControlSalto =cargarImagen("Imagenes/Roman/jump.png");
        regionEnemigo = cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/vacaDinosaurio.png");
        regionVida = cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/Roman/prueba.png", 870, 200, 1, 8);
        regionPersonajeParado = cargarImagenMosaico("Imagenes/Roman/parado.png", 500, 200, 1, 4);
        regionPersonajeSaltandoUno = cargarImagenMosaico("Imagenes/Roman/spritesaltouno.png", 370, 200, 1, 3);
        regionPersonajeSaltandoDos = cargarImagenMosaico("Imagenes/Roman/spritesaltodos.png", 370, 200, 1, 3);
        regionPersonajeSaltandoCompleto = cargarImagenMosaico("Imagenes/Roman/spritesalto.png", 700, 200, 1, 6);
        regionPersonajeNave = cargarImagenMosaico("Imagenes/Roman/lanchajuego.png", 500, 300, 1, 1);
        regionDisparar=cargarImagen("Imagenes/Roman/boton_fuego.png");

        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionHoyoNegro= cargarImagen("Imagenes/hoyoNegro.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
        regionPisoFlotante = cargarImagen("Imagenes/lineas.png");


        regionOvni= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/naveVaca.png");
        regionPua= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/pua.png");
        regionPicos= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/picos.png");
        regionNaveRoman = cargarImagen("Imagenes/Roman/lanchajuego.png");

        regionMenuPausa = cargarImagen("Imagenes/Ajustes2/pausa_fondo.png");
        regionMenuOffoff=cargarImagen("Imagenes/Ajustes2/music_off_OFF.png");
        regionMenuOffon=cargarImagen("Imagenes/Ajustes2/music_off_ON.png");
        regionMenuOnoff=cargarImagen("Imagenes/Ajustes2/music_on_OFF.png");
        regionMenuOnon=cargarImagen("Imagenes/Ajustes2/music_on_ON.png");
        regionIrAMenu=cargarImagen("Imagenes/Ajustes2/pausa_menu.png");
        regionMenuMusica=cargarImagen("Imagenes/Ajustes2/pausa_musica.png");
        regionMenuContinuar=cargarImagen("Imagenes/Ajustes2/pausa_continue.png");

        regionControlDer=cargarImagen("Imagenes/Joystick/derecha_joystick.png");
        regionControlIz=cargarImagen("Imagenes/Joystick/izquiera_joystick.png");
        regionControlUp=cargarImagen("Imagenes/Joystick/arriba_joystick.png");
        regionControlDown=cargarImagen("Imagenes/Joystick/abajo_joystick.png");
    }

    @Override
    public void crearEscena() {

        estoySaltando=false;

        yaBajo=false;

        actividadJuego.camara.setHUD(new HUD());

        actividadJuego.camara.setCenter(640,400);

        agregarHUD();

        reloj=0;

        contadorTiempo=0;

        admMusica.cargarMusicaNivel();

        listaProyectiles = new ArrayList<>();

        listaProyectilesEnemigo = new ArrayList<>();

        listaEnemigos = new ArrayList<>();

        listaVidasEncontradas = new ArrayList<>();

        spriteFondo = cargarSprite(1950, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteFondo2 = cargarSprite(6000, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo2);

        spriteFondo3 = cargarSprite(9954, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo3);

        spriteFondo4 = cargarSprite(13908, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo4);

        spriteNaveRoman=cargarSprite(10185, 300, regionNaveRoman);
        spriteFondo.attachChild(spriteNaveRoman);

        spriteVidas= new Sprite[3];

        numeroDeBalas=10;

        spriteNave=cargarSprite(320, 290, regionNave);
        spriteFondo.attachChild(spriteNave);

        spriteVidas[0]= cargarSprite(1050, 750, vidas);
        attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(1130, 750, vidas);
        attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1210, 750, vidas);
        attachChild(spriteVidas[2]);

        pisoActual= cargarSprite(10, 1500 , regionPisoFlotante);
        spriteFondo.attachChild(pisoActual);


        spriteHoyoNegro= cargarSprite(14700, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        aleatorio= new Random();

        spritePersonajeDerecha = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeDerecha.animate(70);

        spritePersonajeSaltandoUno = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeSaltandoUno, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeSaltandoDos = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeSaltandoDos, actividadJuego.getVertexBufferObjectManager());


        spritePersonajeParado = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeParado, actividadJuego.getVertexBufferObjectManager());

        spritePersonajeNave = new Jugador((ControlJuego.ANCHO_CAMARA/2)-100, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeNave, actividadJuego.getVertexBufferObjectManager());

        spritePersonaje=spritePersonajeParado;
        attachChild(spritePersonaje);

        //admMusica.cargarMusica(2);

        posicionarEnemigos();

        posicionarPisosFlotantes();

        agregarJoystick();
        agregarBotonSalto();
        agregarBotonDisparar();

        musicaEncendida=true;

        // musicaEncendida=admMusica.getMusicaEncendida();


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


        // Crear la escena de PAUSA, pero NO lo agrega a la escena
        escenaPausa = new CameraScene(actividadJuego.camara);
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2,
                regionMenuPausa);
        escenaPausa.attachChild(fondoPausa);
        escenaPausa.setBackgroundEnabled(false);

        spriteMenuPausa = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionMenuPausa);
        escenaPausa.attachChild(spriteMenuPausa);

        spritebtnMusica=cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2 - 45, regionMenuMusica);
        escenaPausa.attachChild(spritebtnMusica);

        spritebtnContinuar = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2 + 300,
                regionMenuContinuar, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    pausarJuego();
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
                    admEscenas.liberarEscenaCazaJurasica();
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

        der = cargarSprite(190, 115, regionControlDer);
        control.attachChild(der);

        iz = cargarSprite(50, 115, regionControlIz);
        control.attachChild(iz);

        up = cargarSprite(120, 185, regionControlUp);
        control.attachChild(up);

        down = cargarSprite(120, 45, regionControlDown);
        control.attachChild(down);

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

        // Agregar monedas
        agregarMonedas();

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
            EscenaCazaJurasica.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    private void agregarHUD() {
        hud = new HUD();


        // Marcador/valorMarcador
        txtMarcador = new Text(1220, 500,
                fontMonster,"    0    ",actividadJuego.getVertexBufferObjectManager());
        txtMarcador.setColor(Color.BLUE);
        hud.attachChild(txtMarcador);
        valorMarcador = 0;

        txtBalas = new Text(1170, 700,
                fontMonster,"    10    ",actividadJuego.getVertexBufferObjectManager());
        txtBalas.setColor(Color.BLUE);
        hud.attachChild(txtBalas);
        valorBalas = 10;

        txtTotalBalas = new Text(1220, 700,
                fontMonster,"    /10    ",actividadJuego.getVertexBufferObjectManager());
        txtTotalBalas.setColor(Color.BLUE);
        hud.attachChild(txtTotalBalas);

        actividadJuego.camara.setHUD(hud);
    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_CAZA_JURASICA;
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


        if(numeroDeBalas<10){
            contadorTiempo+=1;
            if(contadorTiempo>=150){
                numeroDeBalas++;
                valorBalas++;
                contadorTiempo=0;
            }
        }


        for (int i= listaPisos.length-1; i>=0; i--) {

            final Sprite piso = listaPisos[i];

            if (spritePersonaje.collidesWith(piso) && spritePersonaje.getY()-110>piso.getY() && poderDeSalto<2) {


                pisoActual.setPosition(piso);


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
                    pisoActual.setPosition(10,1500);
                }
            }
        }

        DecimalFormat df = new DecimalFormat("##.##"); // Para formatear 2 decimales
        txtMarcador.setText(df.format(valorMarcador));

        txtBalas.setText(df.format(valorBalas));

        if(spritePersonaje.collidesWith(spriteNaveRoman)){

            nubecita=true;
            spriteNaveRoman.detachSelf();
            spritePersonajeNave.setPosition(spritePersonaje.getX(),450);
            spritePersonaje.detachSelf();
            spritePersonaje = spritePersonajeNave;
            attachChild(spritePersonaje);

        }

        actualizarMonedas();

        actualizarProyectilesEnemigo();

        if(spritePersonaje.collidesWith(spriteHoyoNegro)){
            terminoelJuego=true;
            admEscenas.liberarEscenaCazaJurasica();
            admEscenas.crearEscenaCazaJurasicaLvl2();
            admEscenas.setEscena(TipoEscena.ESCENA_CAZA_JURASICA_LVL2);
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

            if (spritePersonaje.collidesWith(enemigo)) {

                if(cantidadVida -1<0){
                    terminoelJuego=true;
                    admEscenas.liberarEscenaCazaJurasica();
                    admEscenas.crearEscenaPerdiste(1);
                    admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                }
                else{
                    enemigo.detachSelf();
                    listaEnemigos.remove(enemigo);
                    //admMusica.vibrar(200);


                    if(nubecita==true){
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
                            terminoelJuego=true;
                            admEscenas.liberarEscenaCazaJurasica();
                            admEscenas.crearEscenaPerdiste(1);
                            admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                        }
                    }
                }
                break;
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
                    terminoelJuego=true;
                    admEscenas.liberarEscenaCazaJurasica();
                    admEscenas.crearEscenaPerdiste(1);
                    admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                }
                else{
                    proyectil.detachSelf();
                    listaProyectilesEnemigo.remove(proyectil);
                    //admMusica.vibrar(200);
                    if(nubecita==true){
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
                            terminoelJuego=true;
                            admEscenas.liberarEscenaCazaJurasica();
                            admEscenas.crearEscenaPerdiste(1);
                            admEscenas.setEscena(TipoEscena.ESCENA_PERDISTE);
                        }
                    }
                }
                break;
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
        admMusica.liberarMusicaNivel();
        liberarRecursos();
        hud.detachChildren();
        this.detachSelf();
        this.dispose();
        this.detachChildren();
    }

    @Override
    public void liberarRecursos() {

        //admMusica.liberarMusica();
        btnDisparar.detachSelf();
        hud.detachChildren();
        hud.detachSelf();
        regionFondo.getTexture().unload();
        regionFondo=null;
        regionNave.getTexture().unload();
        regionNave=null;
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
        control = new AnalogOnScreenControl(120, 115, actividadJuego.camara,
                regionBase, regionBase2,
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
                                float y = spritePersonaje.getY() + 15 * pValueY;
                                spritePersonaje.setY(y);
                            }
                        }

                        else{
                            float y = spritePersonaje.getY() + 15 * pValueY;
                            spritePersonaje.setY(y);
                        }

                    }


                    if (pValueX > 0 && estoySaltando==false) {
                        if (nubecita == false) {
                            personajeVolteandoDerecha = true;
                            spritePersonajeDerecha.setFlippedHorizontal(false);
                            spritePersonajeDerecha.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje = spritePersonajeDerecha;
                            attachChild(spritePersonaje);
                        }
                        else{
                            personajeVolteandoDerecha = true;
                            spritePersonaje.setFlippedHorizontal(false);
                        }

                    }
                    else if (pValueX > 0 && estoySaltando==true) {
                        personajeVolteandoDerecha = true;
                        spritePersonaje.setFlippedHorizontal(false);
                    }
                    else if (pValueX < 0 && estoySaltando==false) {
                        if (nubecita == false) {
                            personajeVolteandoDerecha = false;
                            spritePersonajeDerecha.setFlippedHorizontal(true);
                            spritePersonajeDerecha.setPosition(spritePersonaje);
                            spritePersonaje.detachSelf();
                            spritePersonaje = spritePersonajeDerecha;
                            attachChild(spritePersonaje);
                        }
                        else{
                            personajeVolteandoDerecha = false;
                            spritePersonaje.setFlippedHorizontal(true);
                        }

                    }
                    else if (pValueX < 0 && estoySaltando==true) {
                        personajeVolteandoDerecha = false;
                        spritePersonaje.setFlippedHorizontal(true);
                    }
                    else{

                        if(estoySaltando==false) {

                            if(nubecita == false) {
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
                    }

                        if (spriteFondo.getX() > 1980) {
                            if (pValueX < 0) {
                            } else {
                                pValueX = pValueX * (-1);
                                float x = spriteFondo.getX() + 25 * pValueX;
                                spriteFondo.setX(x);
                            }
                        }
                        else if (spriteFondo.getX() < -12480) {
                            if (pValueX > 0) {
                            } else {
                                pValueX = pValueX * (-1);
                                float x = spriteFondo.getX() + 25 * pValueX;
                                spriteFondo.setX(x);
                            }
                        } else {
                            pValueX = pValueX * (-1);
                            float x = spriteFondo.getX() + 25 * pValueX;
                            spriteFondo.setX(x);
                        }

                }
            }

        });
        EscenaCazaJurasica.this.setChildScene(control);
    }

    private void posicionarEnemigos(){

        int maxima=16;

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
        posicionesEnemigosx[1]=3180;
        posicionesEnemigosx[2]=4350;
        posicionesEnemigosx[3]=5340;
        posicionesEnemigosx[4]=6680;
        posicionesEnemigosx[5]=7140;
        posicionesEnemigosx[6]=8640;
        posicionesEnemigosx[7]=8640;
        posicionesEnemigosx[8]=10500;
        posicionesEnemigosx[9]=11200;
        posicionesEnemigosx[10]=11900;
        posicionesEnemigosx[11]=12140;
        posicionesEnemigosx[12]=12600;
        posicionesEnemigosx[13]=13400;
        posicionesEnemigosx[14]=13400;
        posicionesEnemigosx[15]=13390;

        posicionesEnemigosy[0]=150;
        posicionesEnemigosy[1]=625;
        posicionesEnemigosy[2]=150;
        posicionesEnemigosy[3]=645;
        posicionesEnemigosy[4]=150;
        posicionesEnemigosy[5]=750;
        posicionesEnemigosy[6]=130;
        posicionesEnemigosy[7]=130;
        posicionesEnemigosy[8]=130;
        posicionesEnemigosy[9]=525;
        posicionesEnemigosy[10]=750;
        posicionesEnemigosy[11]=150;
        posicionesEnemigosy[12]=130;
        posicionesEnemigosy[13]=456;
        posicionesEnemigosy[14]=456;
        posicionesEnemigosy[15]=680;

        brinca[0]=false;
        brinca[1]=false;
        brinca[2]=true;
        brinca[3]=false;
        brinca[4]=false;
        brinca[5]=false;
        brinca[6]=false;
        brinca[7]=false;
        brinca[8]=true;
        brinca[9]=false;
        brinca[10]=false;
        brinca[11]=false;
        brinca[12]=false;
        brinca[13]=false;
        brinca[14]=false;
        brinca[15]=false;

        camina[0]=1;
        camina[1]=0;
        camina[2]=3;
        camina[3]=1;
        camina[4]=0;
        camina[5]=1;
        camina[6]=1;
        camina[7]=0;
        camina[8]=3;
        camina[9]=1;
        camina[10]=0;
        camina[11]=1;
        camina[12]=0;
        camina[13]=0;
        camina[14]=1;
        camina[15]=0;

        rota[0]=false;
        rota[1]=false;
        rota[2]=true;
        rota[3]=false;
        rota[4]=false;
        rota[5]=false;
        rota[6]=false;
        rota[7]=false;
        rota[8]=true;
        rota[9]=false;
        rota[10]=false;
        rota[11]=true;
        rota[12]=false;
        rota[13]=false;
        rota[14]=false;
        rota[15]=false;

        numPasos[0]=40;
        numPasos[1]=10;
        numPasos[2]=10;
        numPasos[3]=40;
        numPasos[4]=50;
        numPasos[5]=50;
        numPasos[6]=65;
        numPasos[7]=65;
        numPasos[8]=5;
        numPasos[9]=35;
        numPasos[10]=95;
        numPasos[11]=45;
        numPasos[12]=35;
        numPasos[13]=45;
        numPasos[14]=45;
        numPasos[15]=25;

        rotaCamina[0]=true;
        rotaCamina[1]=true;
        rotaCamina[2]=true;
        rotaCamina[3]=true;
        rotaCamina[4]=true;
        rotaCamina[5]=true;
        rotaCamina[6]=false;
        rotaCamina[7]=false;
        rotaCamina[8]=false;
        rotaCamina[9]=false;
        rotaCamina[10]=true;
        rotaCamina[11]=true;
        rotaCamina[12]=false;
        rotaCamina[13]=true;
        rotaCamina[14]=true;
        rotaCamina[15]=false;

        disparar[0]=false;
        disparar[1]=false;
        disparar[2]=false;
        disparar[3]=false;
        disparar[4]=false;
        disparar[5]=true;
        disparar[6]=false;
        disparar[7]=false;
        disparar[8]=false;
        disparar[9]=false;
        disparar[10]=true;
        disparar[11]=false;
        disparar[12]=false;
        disparar[13]=false;
        disparar[14]=false;
        disparar[15]=false;

        noPuedeSerDestruido[0]=false;
        noPuedeSerDestruido[1]=false;
        noPuedeSerDestruido[2]=false;
        noPuedeSerDestruido[3]=false;
        noPuedeSerDestruido[4]=false;
        noPuedeSerDestruido[5]=false;
        noPuedeSerDestruido[6]=false;
        noPuedeSerDestruido[7]=false;
        noPuedeSerDestruido[8]=false;
        noPuedeSerDestruido[9]=false;
        noPuedeSerDestruido[10]=false;
        noPuedeSerDestruido[11]=false;
        noPuedeSerDestruido[12]=false;
        noPuedeSerDestruido[13]=false;
        noPuedeSerDestruido[14]=false;
        noPuedeSerDestruido[15]=false;

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

        int maxima=36;

        posicionesPisosFlotantesX= new float[maxima];
        posicionesPisosFlotantesY= new float[maxima];
        listaPisos = new Sprite[maxima];

        posicionesPisosFlotantesX[0]=2000;
        posicionesPisosFlotantesX[1]=2750;
        posicionesPisosFlotantesX[2]=3190;
        posicionesPisosFlotantesX[3]=3520;
        posicionesPisosFlotantesX[4]=4000;
        posicionesPisosFlotantesX[5]=5210;
        posicionesPisosFlotantesX[6]=5470;
        //Piramide
        posicionesPisosFlotantesX[7]=7170;
        posicionesPisosFlotantesX[8]=7430;
        posicionesPisosFlotantesX[9]=7690;
        posicionesPisosFlotantesX[10]=7950;
        posicionesPisosFlotantesX[11]=7430;
        posicionesPisosFlotantesX[12]=7690;
        posicionesPisosFlotantesX[13]=7950;
        posicionesPisosFlotantesX[14]=7690;
        posicionesPisosFlotantesX[15]=7950;
        posicionesPisosFlotantesX[16]=7950;
        posicionesPisosFlotantesX[17]=10110;
        posicionesPisosFlotantesX[18]=9850;
        posicionesPisosFlotantesX[19]=9590;
        posicionesPisosFlotantesX[20]=9330;
        posicionesPisosFlotantesX[21]=9850;
        posicionesPisosFlotantesX[22]=9590;
        posicionesPisosFlotantesX[23]=9330;
        posicionesPisosFlotantesX[24]=9590;
        posicionesPisosFlotantesX[25]=9330;
        posicionesPisosFlotantesX[26]=9330;
        posicionesPisosFlotantesX[27]=8640;
        posicionesPisosFlotantesX[28]=10960;
        posicionesPisosFlotantesX[29]=11220;
        posicionesPisosFlotantesX[30]=13000;
        posicionesPisosFlotantesX[31]=13260;
        posicionesPisosFlotantesX[32]=13520;
        posicionesPisosFlotantesX[33]=13780;
        posicionesPisosFlotantesX[34]=13260;
        posicionesPisosFlotantesX[35]=13520;


        posicionesPisosFlotantesY[0]=330;
        posicionesPisosFlotantesY[1]=460;
        posicionesPisosFlotantesY[2]=490;
        posicionesPisosFlotantesY[3]=220;
        posicionesPisosFlotantesY[4]=580;
        posicionesPisosFlotantesY[5]=510;
        posicionesPisosFlotantesY[6]=510;

        //piramide
        posicionesPisosFlotantesY[7]=174;
        posicionesPisosFlotantesY[8]=174;
        posicionesPisosFlotantesY[9]=174;
        posicionesPisosFlotantesY[10]=174;
        posicionesPisosFlotantesY[11]=270;
        posicionesPisosFlotantesY[12]=270;
        posicionesPisosFlotantesY[13]=270;
        posicionesPisosFlotantesY[14]=368;
        posicionesPisosFlotantesY[15]=368;
        posicionesPisosFlotantesY[16]=464;
        posicionesPisosFlotantesY[17]=174;
        posicionesPisosFlotantesY[18]=174;
        posicionesPisosFlotantesY[19]=174;
        posicionesPisosFlotantesY[20]=174;
        posicionesPisosFlotantesY[21]=270;
        posicionesPisosFlotantesY[22]=270;
        posicionesPisosFlotantesY[23]=270;
        posicionesPisosFlotantesY[24]=368;
        posicionesPisosFlotantesY[25]=368;
        posicionesPisosFlotantesY[26]=464;
        posicionesPisosFlotantesY[27]=600;
        posicionesPisosFlotantesY[28]=410;
        posicionesPisosFlotantesY[29]=410;
        posicionesPisosFlotantesY[30]=330;
        posicionesPisosFlotantesY[31]=330;
        posicionesPisosFlotantesY[32]=330;
        posicionesPisosFlotantesY[33]=330;
        posicionesPisosFlotantesY[34]=580;
        posicionesPisosFlotantesY[35]=580;


        int cont;

        for( cont = 0; cont< posicionesPisosFlotantesX.length;cont++){

            Sprite spritePiso = cargarSprite(posicionesPisosFlotantesX[cont], posicionesPisosFlotantesY[cont] , regionPisoFlotante);
            listaPisos[cont]=(spritePiso);
            spriteFondo.attachChild(spritePiso);
        }
    }

    private void agregarMonedas() {
        // Agrega monedas a lo largo del mundo
        listaMonedas = new ArrayList<>();

        int maxima=60;

        posicionesMonedasX= new float[maxima];
        posicionesMonedasY= new float[maxima];

        posicionesMonedasX[0]=2000;
        posicionesMonedasX[1]=2750;
        posicionesMonedasX[2]=3190;
        posicionesMonedasX[3]=2920;
        posicionesMonedasX[4]=4000;
        posicionesMonedasX[5]=5340;
        posicionesMonedasX[6]=5160;
        posicionesMonedasX[7]=5520;
        posicionesMonedasX[8]=5340;
        posicionesMonedasX[9]=6680;
        posicionesMonedasX[10]=6800;
        posicionesMonedasX[11]=6920;
        posicionesMonedasX[12]=7040;
        posicionesMonedasX[13]=8190;
        posicionesMonedasX[14]=8310;
        posicionesMonedasX[15]=8430;
        posicionesMonedasX[16]=8550;
        posicionesMonedasX[17]=8670;
        posicionesMonedasX[18]=8790;
        posicionesMonedasX[19]=8910;
        posicionesMonedasX[20]=9030;
        posicionesMonedasX[21]=9150;
        posicionesMonedasX[22]=8190;
        posicionesMonedasX[23]=8310;
        posicionesMonedasX[24]=8430;
        posicionesMonedasX[25]=8550;
        posicionesMonedasX[26]=8670;
        posicionesMonedasX[27]=8790;
        posicionesMonedasX[28]=8910;
        posicionesMonedasX[29]=9030;
        posicionesMonedasX[30]=9150;
        posicionesMonedasX[31]=8190;
        posicionesMonedasX[32]=8310;
        posicionesMonedasX[33]=8430;
        posicionesMonedasX[34]=8550;
        posicionesMonedasX[35]=8670;
        posicionesMonedasX[36]=8790;
        posicionesMonedasX[37]=8910;
        posicionesMonedasX[38]=9030;
        posicionesMonedasX[39]=9150;
        posicionesMonedasX[40]=10600;
        posicionesMonedasX[41]=10720;
        posicionesMonedasX[42]=10840;
        posicionesMonedasX[43]=10960;
        posicionesMonedasX[44]=11080;
        posicionesMonedasX[45]=11200;
        posicionesMonedasX[46]=11320;
        posicionesMonedasX[47]=11440;
        posicionesMonedasX[48]=11080;
        posicionesMonedasX[49]=11900;
        posicionesMonedasX[50]=12140;
        posicionesMonedasX[51]=12020;
        posicionesMonedasX[52]=12020;
        posicionesMonedasX[53]=13000;
        posicionesMonedasX[54]=13260;
        posicionesMonedasX[55]=13520;
        posicionesMonedasX[56]=13780;
        posicionesMonedasX[57]=13260;
        posicionesMonedasX[58]=13520;
        posicionesMonedasX[59]=12740;


        posicionesMonedasY[0]=440;
        posicionesMonedasY[1]=580;
        posicionesMonedasY[2]=610;
        posicionesMonedasY[3]=170;
        posicionesMonedasY[4]=700;
        posicionesMonedasY[5]=620;
        posicionesMonedasY[6]=680;
        posicionesMonedasY[7]=680;
        posicionesMonedasY[8]=750;
        posicionesMonedasY[9]=520;
        posicionesMonedasY[10]=400;
        posicionesMonedasY[11]=280;
        posicionesMonedasY[12]=160;
        posicionesMonedasY[13]=170;
        posicionesMonedasY[14]=170;
        posicionesMonedasY[15]=170;
        posicionesMonedasY[16]=170;
        posicionesMonedasY[17]=170;
        posicionesMonedasY[18]=170;
        posicionesMonedasY[19]=170;
        posicionesMonedasY[20]=170;
        posicionesMonedasY[21]=170;
        posicionesMonedasY[22]=270;
        posicionesMonedasY[23]=270;
        posicionesMonedasY[24]=270;
        posicionesMonedasY[25]=270;
        posicionesMonedasY[26]=270;
        posicionesMonedasY[27]=270;
        posicionesMonedasY[28]=270;
        posicionesMonedasY[29]=270;
        posicionesMonedasY[30]=270;
        posicionesMonedasY[31]=370;
        posicionesMonedasY[32]=370;
        posicionesMonedasY[33]=370;
        posicionesMonedasY[34]=370;
        posicionesMonedasY[35]=370;
        posicionesMonedasY[36]=370;
        posicionesMonedasY[37]=370;
        posicionesMonedasY[38]=370;
        posicionesMonedasY[39]=370;
        posicionesMonedasY[40]=170;
        posicionesMonedasY[41]=290;
        posicionesMonedasY[42]=410;
        posicionesMonedasY[43]=530;
        posicionesMonedasY[44]=530;
        posicionesMonedasY[45]=410;
        posicionesMonedasY[46]=290;
        posicionesMonedasY[47]=170;
        posicionesMonedasY[48]=675;
        posicionesMonedasY[49]=480;
        posicionesMonedasY[50]=480;
        posicionesMonedasY[51]=600;
        posicionesMonedasY[52]=360;
        posicionesMonedasY[53]=450;
        posicionesMonedasY[54]=450;
        posicionesMonedasY[55]=450;
        posicionesMonedasY[56]=450;
        posicionesMonedasY[57]=700;
        posicionesMonedasY[58]=700;
        posicionesMonedasY[59]=450;



        for (int i=0; i<posicionesMonedasX.length; i++) {
            float x = posicionesMonedasX[i];
            float y = posicionesMonedasY[i];
            Sprite lechita = new Sprite(x,y,regionLechitas,actividadJuego.getVertexBufferObjectManager());
            spriteFondo.attachChild(lechita);
            listaMonedas.add(lechita);
        }
    }

    private void agregarBotonSalto() {
        btnSaltar = new ButtonSprite(1070,100,
                regionControlSalto,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (juegoCorriendo) {
                    if (pSceneTouchEvent.isActionDown() && enElAire==false) {

                        if(nubecita==true){
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
                desaparecerMoneda(moneda);
                animacionTexto(moneda.getX(),moneda.getY()+50);
                break;
            }
            // Salen las monedas que han desaparecido
            if (moneda.getScaleX()==0) {
                valorMarcador += 100;
                moneda.detachSelf();
                listaMonedas.remove(moneda);
            }
        }
    }

    private void animacionTexto(float x, float y){

        IFont tipo = cargarFont("fonts/monster.ttf");
        final Text txtPuntos = new Text(x,y,tipo,"+1",12,actividadJuego.getVertexBufferObjectManager());
        txtPuntos.setColor(0,0,0);
        spriteFondo.attachChild(txtPuntos);

        MoveYModifier modY = new MoveYModifier(1, txtPuntos.getY(),txtPuntos.getY()+1){
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


    private void agregarBotonDisparar() {
        btnDisparar = new ButtonSprite(1200,200,
                regionDisparar,actividadJuego.getVertexBufferObjectManager()) {
            // Aquí el código que ejecuta el botón cuando es presionado
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (juegoCorriendo && terminoelJuego==false) {
                    if(pSceneTouchEvent.isActionDown()){
                        if(numeroDeBalas>0) {
                            admMusica.reproducirMusicaBoton();
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
                        spriteVidas[1]= cargarSprite(1130, 750, vidas);
                        attachChild(spriteVidas[1]);
                        cantidadVida++;
                    }
                    else if(cantidadVida==1){
                        spriteVidas[0]= cargarSprite(1050, 750, vidas);
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