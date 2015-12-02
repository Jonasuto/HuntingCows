package mx.itesm.rmroman.huntingcows;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.scene.CameraScene;
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

import java.util.Random;


/**
 * Created by Campos on 11/10/15.
 */
public class EscenaAleatoriedad extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionHoyoNegro;

    private IFont fontMonster;

    private Random elQueSigue;

    private int reloj;

    private float[] posicionesPisosFlotantesX;
    private float[] posicionesPisosFlotantesY;

    private float[] posicionesBolasFlotantesX;
    private float[] posicionesBolasFlotantesY;

    private int valorBalas;

    private Sprite[] listaPisos;
    private Teletransportadora[] listaBolas;

    private int contadorTiempo;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Jugador spritePersonajeDerecha;

    private boolean juegoCorriendo = true;

    private Sprite pisoActual;

    private CameraScene escenaPausa;
    private ITextureRegion regionBtnPausa;

    private int numMagico;

    private TiledTextureRegion regionPersonajeAnimado;

    private boolean personajeVolteandoDerecha=true;

    private boolean daleChance;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteHoyoNegro;
    private Sprite spriteFondoPausa;

    private ITextureRegion regionPisoAzulTele;
    private ITextureRegion regionPisoAzulClaro;
    private ITextureRegion regionPisoGrandeTele;

    private HUD hud;

    private ITextureRegion regionPisoRosaTele;

    private Sprite der;
    private Sprite iz;
    private Sprite up;
    private Sprite down;

    private Text txtTotalBalas; // Por ahora con valorMarcador

    private ITextureRegion regionControlDer;
    private ITextureRegion regionControlIz;
    private ITextureRegion regionControlUp;
    private ITextureRegion regionControlDown;

    private ITextureRegion regionBolaTeletransportadoraAmarilla;
    private ITextureRegion regionBolaTeletransportadoraAzul;

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

    private Random aleatorio;

    private int cambiar=0;

    private Sprite bolaActual;


    @Override
    public void cargarRecursos() {
        elQueSigue=new Random();
        regionFondo = cargarImagen("Imagenes/EscenaAleatoriedad/fondo_teletrans.png");
        regionFondoPausa = cargarImagen("Imagenes/Logos/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/Roman/joystick.png");
        regionControlSalto.setTextureSize(1,1);
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/Roman/prueba.png", 870, 200, 1, 8);

        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionHoyoNegro= cargarImagen("Imagenes/hoyoNegro.png");
        regionMenuPausa = cargarImagen("Imagenes/Ajustes2/pausa_fondo.png");
        regionMenuOffoff=cargarImagen("Imagenes/Ajustes2/music_off_OFF.png");
        regionMenuOffon=cargarImagen("Imagenes/Ajustes2/music_off_ON.png");
        regionMenuOnoff=cargarImagen("Imagenes/Ajustes2/music_on_OFF.png");
        regionMenuOnon=cargarImagen("Imagenes/Ajustes2/music_on_ON.png");
        regionIrAMenu=cargarImagen("Imagenes/Ajustes2/pausa_menu.png");
        regionMenuMusica=cargarImagen("Imagenes/Ajustes2/pausa_musica.png");
        regionMenuContinuar=cargarImagen("Imagenes/Ajustes2/pausa_continue.png");

        fontMonster = cargarFont("fonts/monster.ttf");

        regionPisoAzulTele = cargarImagen("Imagenes/EscenaAleatoriedad/azul_tele.png");
        regionPisoAzulClaro = cargarImagen("Imagenes/EscenaAleatoriedad/azulclaro_tele.png");
        regionPisoGrandeTele = cargarImagen("Imagenes/EscenaAleatoriedad/grande_tele.png");
        regionPisoRosaTele = cargarImagen("Imagenes/EscenaAleatoriedad/rosa_tele.png");

        regionControlDer=cargarImagen("Imagenes/Joystick/derecha_joystick.png");
        regionControlIz=cargarImagen("Imagenes/Joystick/izquiera_joystick.png");
        regionControlUp=cargarImagen("Imagenes/Joystick/arriba_joystick.png");
        regionControlDown=cargarImagen("Imagenes/Joystick/abajo_joystick.png");

        regionBolaTeletransportadoraAzul= cargarImagen("Imagenes/EscenaAleatoriedad/teletrans_tele.png");
        regionBolaTeletransportadoraAmarilla= cargarImagen("Imagenes/EscenaAleatoriedad/teletransamarillo_tele.png");
    }

    @Override
    public void crearEscena() {

        reloj=0;

        valorBalas = 10;

        actividadJuego.camara.setCenter(640,400);

        actividadJuego.camara.setHUD(new HUD());

        contadorTiempo=0;

        numMagico=0;

        daleChance=true;

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, (ControlJuego.ALTO_CAMARA/2)+100 , regionFondo);
        attachChild(spriteFondo);

        pisoActual= cargarSprite(10, 1500 , regionPisoAzulTele);
        pisoActual.setAlpha(0.1f);
        spriteFondo.attachChild(pisoActual);

        spriteHoyoNegro= cargarSprite(14700, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        aleatorio= new Random();

        posicionarPisosFlotantes();
        posicionarBolasFlotantes();

        spritePersonajeDerecha = new Jugador((ControlJuego.ANCHO_CAMARA/2), (ControlJuego.ALTO_CAMARA/4),regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeDerecha.animate(70);
        spritePersonajeDerecha.setSize(spritePersonajeDerecha.getWidth()-10,spritePersonajeDerecha.getHeight()-10);

        spritePersonaje=spritePersonajeDerecha;
        attachChild(spritePersonaje);

        //admMusica.cargarMusica(2);

        posicionarPisosFlotantes();
        posicionarBolasFlotantes();

        agregarJoystick();

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

       // musicaEncendida=admMusica.getMusicaEncendida();

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
                    pausarJuego();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(spritebtnContinuar);
        escenaPausa.registerTouchArea(spritebtnContinuar);

        spritebtnIrAMenu = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2-300,
                regionIrAMenu, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    admEscenas.liberarEscenaAleatoriedad();
                    admEscenas.crearEscenaMenu();
                    admEscenas.setEscena(TipoEscena.ESCENA_MENU);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(spritebtnIrAMenu);
        escenaPausa.registerTouchArea(spritebtnIrAMenu);

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

        der = cargarSprite(190, 115, regionControlDer);
        der.setAlpha(0.001f);
        control.attachChild(der);

        iz = cargarSprite(50, 115, regionControlIz);
        iz.setAlpha(0.001f);
        control.attachChild(iz);

        up = cargarSprite(120, 185, regionControlUp);
        up.setAlpha(0.001f);
        control.attachChild(up);

        down = cargarSprite(120, 45, regionControlDown);
        down.setAlpha(0.001f);
        control.attachChild(down);
    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        pausarJuego();/*
        if(admMusica.getMusicaEncendida()==true) {
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
            EscenaAleatoriedad.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }


    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_ALEATORIEDAD;
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


        for (int i= listaPisos.length-1; i>=0; i--) {

            final Sprite piso = listaPisos[i];

            if (spritePersonaje.collidesWith(piso) && spritePersonaje.getY()-130>piso.getY()) {
                pisoActual.setPosition(piso);
                spritePersonaje.setY(piso.getY()+150);

            }

            else{
                if(spritePersonaje.collidesWith(pisoActual)==false){
                    pisoActual.setPosition(10,1500);
                }
            }
        }

        for (int i= listaBolas.length-1; i>=0; i--) {

            final Teletransportadora bola = listaBolas[i];

            if(i!=numMagico){
                bola.setpuedeTeletransportar(true);
            }
            else{
                bola.setpuedeTeletransportar(false);
            }
            if (spritePersonaje.collidesWith(bola)) {
                if(bola.getpuedeTeletransportar()) {
                    //admMusica.vibrar(90);
                    numMagico=getNumAleatorio(numMagico);
                    spritePersonaje.setPosition(posicionesBolasFlotantesX[numMagico],posicionesBolasFlotantesY[numMagico]);
                }
                else{
                    daleChance=true;
                }
            }
            else{
                if(i==numMagico){
                    daleChance=false;
                }
            }
        }

        if(spritePersonaje.getY()>=780){
            admEscenas.liberarEscenaAleatoriedad();
            admEscenas.crearEscenaGanaste(3);
            admEscenas.setEscena(TipoEscena.ESCENA_GANASTE);
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

    @Override
    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();
    }

    public int getNumAleatorio(int numMagico){
        int num;
        while(true){
            num=aleatorio.nextInt(20);
            if(num!=numMagico){
                break;
            }
        }
        return num;
    }

    @Override
    public void liberarRecursos() {

        //admMusica.liberarMusica();
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);
        regionFondo.getTexture().unload();
        regionFondo=null;
        regionBase.getTexture().unload();
        regionBase=null;
        regionControlSalto.getTexture().unload();
        regionControlSalto=null;
        regionPersonajeAnimado.getTexture().unload();
        regionPersonajeAnimado=null;
        regionBtnPausa.getTexture().unload();
        regionBtnPausa=null;
        regionHoyoNegro.getTexture().unload();
        regionHoyoNegro=null;
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
                regionBase, regionControlSalto,
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

                    if (pValueX > 0) {
                        if(personajeVolteandoDerecha==false) {
                            personajeVolteandoDerecha = true;
                            spritePersonaje.setFlippedHorizontal(false);
                        }
                    }
                    else if (pValueX < 0) {
                        if(personajeVolteandoDerecha==true) {
                            personajeVolteandoDerecha = false;
                            spritePersonaje.setFlippedHorizontal(true);
                        }
                    }
                    else{
                        spritePersonaje.animate(70);
                    }

                    if((numMagico==2 || numMagico==4 || numMagico==6 || numMagico==8 || numMagico==10 || numMagico==12 || numMagico==15 || numMagico==17 || numMagico==19) && daleChance==false){
                        if(pValueX>0){
                        }
                        else{
                            float x = spritePersonaje.getX()+22*pValueX;
                            spritePersonaje.setX(x);
                        }
                    }

                    else if((numMagico==1 || numMagico==3 || numMagico==5 || numMagico==7 || numMagico==9 || numMagico==11 || numMagico==14 || numMagico==16 || numMagico==18) && daleChance==false){
                        if(pValueX<0){
                        }
                        else{
                            float x = spritePersonaje.getX()+12*pValueX;
                            spritePersonaje.setX(x);
                        }
                    }

                    else{
                        float x = spritePersonaje.getX()+12*pValueX;
                        spritePersonaje.setX(x);
                    }
                }
            }

        });
        EscenaAleatoriedad.this.setChildScene(control);
    }

    private void posicionarPisosFlotantes(){

        int maxima=10;

        posicionesPisosFlotantesX= new float[maxima];
        posicionesPisosFlotantesY= new float[maxima];
        listaPisos = new Sprite[maxima];

        posicionesPisosFlotantesX[0]=650;
        posicionesPisosFlotantesX[1]=200;
        posicionesPisosFlotantesX[2]=200;
        posicionesPisosFlotantesX[3]=200;
        posicionesPisosFlotantesX[4]=670;
        posicionesPisosFlotantesX[5]=670;
        posicionesPisosFlotantesX[6]=670;
        posicionesPisosFlotantesX[7]=1100;
        posicionesPisosFlotantesX[8]=1100;
        posicionesPisosFlotantesX[9]=1100;

        posicionesPisosFlotantesY[0]=100;
        posicionesPisosFlotantesY[1]=200;
        posicionesPisosFlotantesY[2]=400;
        posicionesPisosFlotantesY[3]=600;
        posicionesPisosFlotantesY[4]=300;
        posicionesPisosFlotantesY[5]=500;
        posicionesPisosFlotantesY[6]=700;
        posicionesPisosFlotantesY[7]=200;
        posicionesPisosFlotantesY[8]=400;
        posicionesPisosFlotantesY[9]=600;

        int cont;

        for( cont = 0; cont< posicionesPisosFlotantesX.length;cont++){

            if(cont==0) {

                Sprite spritePiso = cargarSprite(posicionesPisosFlotantesX[cont], posicionesPisosFlotantesY[cont], regionPisoGrandeTele);
                listaPisos[cont] = (spritePiso);
                attachChild(spritePiso);
            }
            else if(cont==1 || cont==5 || cont==7) {

                Sprite spritePiso = cargarSprite(posicionesPisosFlotantesX[cont], posicionesPisosFlotantesY[cont], regionPisoAzulClaro);
                listaPisos[cont] = (spritePiso);
                attachChild(spritePiso);
            }
            else if(cont==2 || cont==8) {

                Sprite spritePiso = cargarSprite(posicionesPisosFlotantesX[cont], posicionesPisosFlotantesY[cont], regionPisoAzulTele);
                listaPisos[cont] = (spritePiso);
                attachChild(spritePiso);
            }
            else {
                Sprite spritePiso = cargarSprite(posicionesPisosFlotantesX[cont], posicionesPisosFlotantesY[cont], regionPisoRosaTele);
                listaPisos[cont] = (spritePiso);
                attachChild(spritePiso);
            }
        }
    }

    private void posicionarBolasFlotantes(){

        int maxima=20;

        posicionesBolasFlotantesX= new float[maxima];
        posicionesBolasFlotantesY= new float[maxima];
        listaBolas = new Teletransportadora[maxima];

        posicionesBolasFlotantesX[0]=650;
        posicionesBolasFlotantesX[1]=480;
        posicionesBolasFlotantesX[2]=820;
        posicionesBolasFlotantesX[3]=130;
        posicionesBolasFlotantesX[4]=270;
        posicionesBolasFlotantesX[5]=130;
        posicionesBolasFlotantesX[6]=270;
        posicionesBolasFlotantesX[7]=130;
        posicionesBolasFlotantesX[8]=270;
        posicionesBolasFlotantesX[9]=600;
        posicionesBolasFlotantesX[10]=710;
        posicionesBolasFlotantesX[11]=600;
        posicionesBolasFlotantesX[12]=710;
        posicionesBolasFlotantesX[13]=670;
        posicionesBolasFlotantesX[14]=1030;
        posicionesBolasFlotantesX[15]=1170;
        posicionesBolasFlotantesX[16]=1030;
        posicionesBolasFlotantesX[17]=1170;
        posicionesBolasFlotantesX[18]=1030;
        posicionesBolasFlotantesX[19]=1170;

        posicionesBolasFlotantesY[0]=200;
        posicionesBolasFlotantesY[1]=200;
        posicionesBolasFlotantesY[2]=200;
        posicionesBolasFlotantesY[3]=300;
        posicionesBolasFlotantesY[4]=300;
        posicionesBolasFlotantesY[5]=500;
        posicionesBolasFlotantesY[6]=500;
        posicionesBolasFlotantesY[7]=700;
        posicionesBolasFlotantesY[8]=700;
        posicionesBolasFlotantesY[9]=400;
        posicionesBolasFlotantesY[10]=400;
        posicionesBolasFlotantesY[11]=600;
        posicionesBolasFlotantesY[12]=600;
        posicionesBolasFlotantesY[13]=800;
        posicionesBolasFlotantesY[14]=300;
        posicionesBolasFlotantesY[15]=300;
        posicionesBolasFlotantesY[16]=500;
        posicionesBolasFlotantesY[17]=500;
        posicionesBolasFlotantesY[18]=700;
        posicionesBolasFlotantesY[19]=700;

        int cont;

        for( cont = 0; cont< posicionesBolasFlotantesX.length;cont++){

            if(cont==0) {

                Teletransportadora spriteBola = new Teletransportadora(posicionesBolasFlotantesX[cont], posicionesBolasFlotantesY[cont], regionBolaTeletransportadoraAmarilla,actividadJuego.getVertexBufferObjectManager());
                listaBolas[cont] = (spriteBola);
                bolaActual= spriteBola;
                attachChild(spriteBola);
            }
            else {
                Teletransportadora spriteBola = new Teletransportadora(posicionesBolasFlotantesX[cont], posicionesBolasFlotantesY[cont], regionBolaTeletransportadoraAzul,actividadJuego.getVertexBufferObjectManager());
                listaBolas[cont] = (spriteBola);
                attachChild(spriteBola);
            }

        }
    }
}