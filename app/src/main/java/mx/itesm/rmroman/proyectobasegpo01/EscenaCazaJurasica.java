package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

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
public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionNave;
    private ITextureRegion regionHoyoNegro;
    private ITextureRegion regionOvni;

    private Random elQueSigue;

    private boolean enElAire=false;
    private boolean gravedad=false;
    private boolean paArriba=false;
    private boolean nubecita=false;
    private boolean brincaSobrepalmera=false;

    private float[] posicionesEnemigos;
    private float[] posicionesPisosFlotantes;

    private Text txtMarcador; // Por ahora con valorMarcador
    private IFont fontMonster;

    private HUD hud;

    private float valorMarcador;

    private ITextureRegion regionLechitas;

    private ArrayList<Sprite> listaMonedas;
    private static final int NUM_MONEDAS = 30;

    private ArrayList<Enemigo> listaEnemigos;
    private ArrayList<Sprite> listapisosFlotantes;

    private ITextureRegion regionEnemigo;
    private ITextureRegion regionVida;

    private int numeroDeBalas;

    private ITextureRegion vidas;
    private Sprite[] spriteVidas;

    private Sprite[] spriteBalas;

    private Sprite spriteNave;

    private boolean estoySobreUnaPalmera;

    private float poderDeSalto;

    private Sprite spriteOvni;
    private Sprite spriteOvni2;
    private Sprite spriteOvni3;
    private Sprite spriteOvni4;
    private Sprite spriteOvni5;
    private Sprite spriteNavecita;

    private int contadorTiempo;


    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Enemigo spriteEnemigo;

    private boolean juegoCorriendo = true;


    private CameraScene escenaPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;

    private boolean personajeVolteandoDerecha=true;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondo2;
    private Sprite spriteFondo3;
    private Sprite spriteHoyoNegro;
    private Sprite spriteFondoPausa;

    private Sprite spritePiso0;
    private Sprite spritePiso1;
    private Sprite spritePiso2;
    private Sprite spritePiso3;

    private ButtonSprite btnSaltar;
    private ButtonSprite btnDisparar;
    private int cantidadVida;

    private ITextureRegion regionPisoFlotante;

    // Proyectiles del personaje
    private ITextureRegion regionProyectil;
    private ArrayList<Laser> listaProyectiles;

    private ArrayList<Vida> listaVidasEncontradas;



    //_______

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
        poderDeSalto=5.1f;

        cantidadVida =2;

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
        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionHoyoNegro= cargarImagen("Imagenes/hoyoNegro.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
        regionPisoFlotante = cargarImagen("Imagenes/lineas.png");


        regionOvni= cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/naveVaca.png");



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



        contadorTiempo=0;

        listaProyectiles = new ArrayList<>();

        listaEnemigos = new ArrayList<>();

        listapisosFlotantes = new ArrayList<>();

        listaVidasEncontradas = new ArrayList<>();

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2+300, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteFondo2 = cargarSprite(5970, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo2);

        spriteFondo3 = cargarSprite(8300, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo3);

        spriteOvni=cargarSprite(200, 700, regionOvni);
        spriteOvni.setSize(spriteOvni.getWidth() - 30, spriteOvni.getHeight() - 30);
        spriteFondo.attachChild(spriteOvni);

        spriteOvni2=cargarSprite(1000, 300, regionOvni);
        spriteOvni2.setSize(spriteOvni2.getWidth() - 30, spriteOvni2.getHeight() - 30);
        spriteOvni2.setRotation(25);
        spriteFondo.attachChild(spriteOvni2);

        spriteOvni3=cargarSprite(1900, 700, regionOvni);
        spriteOvni3.setSize(spriteOvni3.getWidth() - 30, spriteOvni3.getHeight() - 30);
        spriteFondo.attachChild(spriteOvni3);

        spriteOvni4=cargarSprite(2300, 700, regionOvni);
        spriteOvni4.setSize(spriteOvni4.getWidth() - 30, spriteOvni4.getHeight() - 30);
        spriteOvni4.setRotation(-25);
        spriteFondo.attachChild(spriteOvni4);

        spriteOvni5=cargarSprite(2900, 700, regionOvni);
        spriteOvni5.setSize(spriteOvni5.getWidth() - 30, spriteOvni5.getHeight() - 30);
        spriteFondo.attachChild(spriteOvni5);

        spriteVidas= new Sprite[3];

        spriteBalas= new Sprite[10];

        numeroDeBalas=9;

        spriteNave=cargarSprite(200, 270, regionNave);
        spriteFondo.attachChild(spriteNave);

        spriteVidas[0]= cargarSprite(1000, 780, vidas);
        attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(1050, 780, vidas);
        attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1100, 780, vidas);
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

        spriteBalas[9]= cargarSprite(1100, 150, regionProyectil);
        spriteBalas[9].setSize(spriteBalas[9].getWidth() - 20, spriteBalas[9].getHeight() - 20);
        attachChild(spriteBalas[9]);





        spriteHoyoNegro= cargarSprite(9500, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        spritePersonaje = new Jugador((ControlJuego.ANCHO_CAMARA/2)-200, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(70);
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
            EscenaCazaJurasica.this.setChildScene(control);
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

        return mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_CAZA_JURASICA;
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



            if(chocoConElPiso()==true && nubecita==false){

                estoySobreUnaPalmera=true;

                if(brincaSobrepalmera==false){

                    enElAire=false;
                    paArriba=false;
                    gravedad=true;
                    spritePersonaje.setY(spritePiso0.getY()+70);
                }
                else{
                    enElAire=true;
                    paArriba=true;
                    gravedad=false;
                }
            }
            else{
                estoySobreUnaPalmera=false;
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

        if(nubecita==true){
            spriteNavecita.setPosition(spritePersonaje.getX(), spritePersonaje.getY());
        }


        if(spritePersonaje.collidesWith(spriteHoyoNegro)){
            admEscenas.setcazaJurasicaDesbloqueado(true);
            admEscenas.crearEscenaCazaJurasicaBossFinal();
            admEscenas.setEscena(TipoEscena.ESCENA_CAZA_JURASICA_BOSS_FINAL);
            admEscenas.liberarEscenaCazaJurasica();
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
                    poderDeSalto=5f;
                }

            }
        }

        actualizarProyectiles(pSecondsElapsed);

        actualizarVidas(pSecondsElapsed);

        for (int i= listaEnemigos.size()-1; i>=0; i--) {

            final Enemigo enemigo = listaEnemigos.get(i);


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
                    admEscenas.crearEscenaMenu();
                    admEscenas.setEscena(TipoEscena.ESCENA_MENU);
                    admEscenas.liberarEscenaCazaJurasica();
                }
                else{
                    enemigo.detachSelf();
                    listaEnemigos.remove(enemigo);
                    admMusica.vibrar(200);


                    if(nubecita==true){
                        spriteNavecita.detachSelf();
                        nubecita=false;
                    }

                    else {
                        spriteVidas[2 - cantidadVida].detachSelf();
                        spritePersonaje.setSize(spritePersonaje.getWidth() - 20, spritePersonaje.getHeight() - 20);
                        cantidadVida--;
                    }

                }
            }
        }
    }


    private boolean chocoConElPiso(){
        if(spritePersonaje.collidesWith(spritePiso0) || spritePersonaje.collidesWith(spritePiso1) || spritePersonaje.collidesWith(spritePiso2) || spritePersonaje.collidesWith(spritePiso3)){
            return true;
        }
        else{
            return false;
        }
    }

    private void agregarMonedas() {
        // Agrega monedas a lo largo del mundo
        listaMonedas = new ArrayList<>(NUM_MONEDAS);
        for (int i=0; i<NUM_MONEDAS; i++) {
            float x = (float)(Math.random()*(regionFondo.getWidth()-ControlJuego.ANCHO_CAMARA))+ControlJuego.ANCHO_CAMARA/2;
            float y = (float)(Math.random()*(ControlJuego.ALTO_CAMARA-regionPersonajeAnimado.getHeight()))+regionPersonajeAnimado.getHeight()/2;
            Sprite lechita = new Sprite(x,y,regionLechitas,actividadJuego.getVertexBufferObjectManager());
            spriteFondo.attachChild(lechita);
            listaMonedas.add(lechita);
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
                                float y = spritePersonaje.getY() + 15 * pValueY;
                                spritePersonaje.setY(y);
                            }
                        }

                        else{
                            float y = spritePersonaje.getY() + 15 * pValueY;
                            spritePersonaje.setY(y);
                        }

                    }


                        if (pValueX > 0) {
                            personajeVolteandoDerecha = true;
                        } else if (pValueX < 0) {
                            personajeVolteandoDerecha = false;
                        }


                        if (spriteFondo.getX() > 2000) {
                            if (pValueX < 0) {
                            } else {
                                pValueX = pValueX * (-1);
                                float x = spriteFondo.getX() + 25 * pValueX;
                                spriteFondo.setX(x);
                            }
                        }
                        else if (spriteFondo.getX() < -7013) {
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

        posicionesEnemigos= new float[10];
        posicionesEnemigos[0]=2500;
        posicionesEnemigos[1]=3000;
        posicionesEnemigos[2]=3500;
        posicionesEnemigos[3]=4000;
        posicionesEnemigos[4]=4250 ;
        posicionesEnemigos[5]=4500 ;
        posicionesEnemigos[6]=5000 ;
        posicionesEnemigos[7]=5500 ;
        posicionesEnemigos[8]=6000 ;
        posicionesEnemigos[9]=7000 ;


        int cont;
        for( cont = 0; cont<posicionesEnemigos.length;cont++){


            boolean brinca = elQueSigue.nextBoolean();
            int camina =elQueSigue.nextInt(3);
            boolean rotacion=elQueSigue.nextBoolean();

            Enemigo spriteEnemigo = new Enemigo(posicionesEnemigos[cont], 150,regionEnemigo, actividadJuego.getVertexBufferObjectManager(),camina,brinca,rotacion);
            Enemigo nuevoEnemigo = spriteEnemigo;
            listaEnemigos.add(nuevoEnemigo);
            spriteFondo.attachChild(nuevoEnemigo);

        }
    }


    private void posicionarPisosFlotantes(){


            spritePiso0 = cargarSprite(1000, 300 , regionPisoFlotante);
            spriteFondo.attachChild(spritePiso0);

        spritePiso1 = cargarSprite(3000, 300 , regionPisoFlotante);
        spriteFondo.attachChild(spritePiso1);

        spritePiso2 = cargarSprite(5000, 300 , regionPisoFlotante);
        spriteFondo.attachChild(spritePiso2);

        spritePiso3 = cargarSprite(7000, 300 , regionPisoFlotante);
        spriteFondo.attachChild(spritePiso3);

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

                        poderDeSalto=5f;
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

                    if(enemigo.getRegalo()==1 || enemigo.getRegalo()==3){
                        crearVida(enemigo);
                    }

                    enemigo.detachSelf();
                    listaEnemigos.remove(enemigo);
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
                        spriteVidas[1]= cargarSprite(1050, 780, vidas);
                        attachChild(spriteVidas[1]);
                        cantidadVida++;
                        spritePersonaje.setSize(spritePersonaje.getWidth() + 20, spritePersonaje.getHeight() + 20);


                    }
                    else if(cantidadVida==1){
                        spriteVidas[0]= cargarSprite(1000, 780, vidas);
                        attachChild(spriteVidas[0]);
                        spritePersonaje.setSize(spritePersonaje.getWidth() + 20, spritePersonaje.getHeight() + 20);
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
        Laser spriteProyectil = new Laser(spritePersonaje.getX(),  spritePersonaje.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),personajeVolteandoDerecha);

        if(personajeVolteandoDerecha==false){
            spriteProyectil.setRotation(-180);
        }
        attachChild(spriteProyectil);   // Lo agrega a la escena
        listaProyectiles.add(spriteProyectil);  // Lo agrega a la lista
    }

    private void crearVida(Enemigo enemigoDestruido) {

        // Crearlo
        Vida spriteVida = new Vida(enemigoDestruido.getX(), 200,regionVida, actividadJuego.getVertexBufferObjectManager());
        spriteFondo.attachChild(spriteVida);
        listaVidasEncontradas.add(spriteVida);  // Lo agrega a la lista
    }
}