package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Campos on 11/10/15.
 */
public class EscenaCazaJurasicaBossFinal extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionEnemigoBossFinal;
    private Random aleatorio;

    private Random elQueSigue;

    private float[] posicionesEnemigos;

    private boolean temblor;

    private boolean paArribaEnemigo;
    private ArrayList<Enemigo> listaEnemigos;

    private ITextureRegion regionEnemigo;
    private ITextureRegion regionVida;

    private ITextureRegion vidas;
    private Sprite[] spriteVidas;

    private Sprite[] spriteVidasEnemigo;


    private boolean personajeSaltando = false;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;

    private Enemigo spriteEnemigo;
    private Enemigo spriteEnemigoBossFinal;


    private boolean juegoCorriendo = true;

    private boolean superSalto;


    private CameraScene escenaPausa;
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;

    private boolean personajeVolteandoDerecha=true;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondoPausa;

    private ButtonSprite btnSaltar;
    private ButtonSprite btnDisparar;
    private int cantidadVida;


    // Proyectiles del personaje
    private ITextureRegion regionProyectil;
    private ArrayList<Laser> listaProyectiles;

    private ArrayList<Vida> listaVidasEncontradas;

    private int contadorTiempo;


    @Override
    public void cargarRecursos() {


        contadorTiempo=0;
        elQueSigue=new Random();
        cantidadVida =2;
        vidas=cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionFondo = cargarImagen("Imagenes/Niveles/CazaJurasica/fondos/fondo2Boss.jpg");
        regionFondoPausa = cargarImagen("Imagenes/Logos/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/Roman/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/Roman/joystick.png");
        regionEnemigo = cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/vacaDinosaurio.png");
        regionVida = cargarImagen("Imagenes/Niveles/CazaJurasica/corazon.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/Roman/kiki.png", 590, 138, 1, 4);
        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/Niveles/CazaJurasica/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/Ajustes/pausa.png");
        regionProyectil = cargarImagen("Imagenes/Roman/laser.png");
        regionEnemigoBossFinal=cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/boss_Jurasico.png");

    }

    @Override
    public void crearEscena() {


        superSalto=false;

        paArribaEnemigo=false;

        temblor = false;

        aleatorio= new Random();

        listaProyectiles = new ArrayList<>();

        listaEnemigos = new ArrayList<>();

        listaVidasEncontradas = new ArrayList<>();

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteVidas= new Sprite[3];

        spriteVidasEnemigo= new Sprite[8];

        spriteEnemigoBossFinal = new Enemigo(1400, 200,regionEnemigoBossFinal, actividadJuego.getVertexBufferObjectManager(),3,true,true,40,false,false,false);
        spriteFondo.attachChild(spriteEnemigoBossFinal);


        spriteVidas[0]= cargarSprite(200, 780, vidas);
        attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(250, 780, vidas);
        attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(300, 780, vidas);
        attachChild(spriteVidas[2]);

        spriteVidasEnemigo[0]= cargarSprite(600, 780, vidas);
        attachChild(spriteVidasEnemigo[0]);

        spriteVidasEnemigo[1]= cargarSprite(650, 780, vidas);
        attachChild(spriteVidasEnemigo[1]);

        spriteVidasEnemigo[2]= cargarSprite(700, 780, vidas);
        attachChild(spriteVidasEnemigo[2]);

        spriteVidasEnemigo[3]= cargarSprite(750, 780, vidas);
        attachChild(spriteVidasEnemigo[3]);

        spriteVidasEnemigo[4]= cargarSprite(800, 780, vidas);
        attachChild(spriteVidasEnemigo[4]);

        spriteVidasEnemigo[5]= cargarSprite(850, 780, vidas);
        attachChild(spriteVidasEnemigo[5]);

        spriteVidasEnemigo[6]= cargarSprite(900, 780, vidas);
        attachChild(spriteVidasEnemigo[6]);

        spriteVidasEnemigo[7]= cargarSprite(950, 780, vidas);
        attachChild(spriteVidasEnemigo[7]);

        spritePersonaje = new Jugador((ControlJuego.ANCHO_CAMARA/2)-300, ControlJuego.ALTO_CAMARA/4-100,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

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
        pausarJuego();
    }

    private void pausarJuego() {
        if (juegoCorriendo) {
            escenaPausa.setChildScene(control);
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        } else {
            clearChildScene();
            EscenaCazaJurasicaBossFinal.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_CAZA_JURASICA_BOSS_FINAL;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }

        if(temblor==true){

        }


            if (spriteEnemigoBossFinal.getBrincando() == false) {


                if(contadorTiempo<10) {
                    contadorTiempo+=aleatorio.nextInt(5);
                    spriteEnemigoBossFinal.setBrincando(true);
                    // Animar sprite central
                    JumpModifier salto = new JumpModifier(1, spriteEnemigoBossFinal.getX(), spriteEnemigoBossFinal.getX(),
                            spriteEnemigoBossFinal.getY(), spriteEnemigoBossFinal.getY(), -400);
                    ParallelEntityModifier paralelo = new ParallelEntityModifier(salto) {
                        @Override
                        protected void onModifierFinished(IEntity pItem) {
                            spriteEnemigoBossFinal.setBrincando(false);
                            unregisterEntityModifier(this);
                            super.onModifierFinished(pItem);
                        }
                    };
                    spriteEnemigoBossFinal.registerEntityModifier(paralelo);

                }

                else{

                    contadorTiempo=0;
                    superSalto=true;
                    spriteEnemigoBossFinal.setBrincando(true);
                    paArribaEnemigo=true;

                }


            }


        if(superSalto==true ){
            if(spriteEnemigoBossFinal.getY()<1950 && paArribaEnemigo==true){
                spriteEnemigoBossFinal.setY(spriteEnemigoBossFinal.getY()+55);
            }
            else{
                paArribaEnemigo=false;
            }

            if(paArribaEnemigo==false){
                if(spriteEnemigoBossFinal.getY()>200){
                    spriteEnemigoBossFinal.setY(spriteEnemigoBossFinal.getY()-55);
                }
                else{
                    superSalto=false;
                    admMusica.vibrar(100);
                    spriteEnemigoBossFinal.setBrincando(false);
                    temblor=true;
                }
            }
        }



        actualizarProyectiles(pSecondsElapsed);

        actualizarVidas(pSecondsElapsed);
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

                    if(pValueX>0){
                        personajeVolteandoDerecha=true;
                    }

                    else if(pValueX<0){
                        personajeVolteandoDerecha=false;
                    }


                    if(spriteFondo.getX()>1002){
                        if(pValueX<0){
                        }
                        else{
                            pValueX = pValueX * (-1);
                            float x = spriteFondo.getX() + 25 * pValueX;
                            spriteFondo.setX(x);
                        }
                    }

                    else if(spriteFondo.getX()<304){
                        if(pValueX>0){
                        }
                        else{
                            pValueX = pValueX * (-1);
                            float x = spriteFondo.getX() + 25 * pValueX;
                            spriteFondo.setX(x);
                        }
                    }

                    else{
                        pValueX = pValueX * (-1);
                        float x = spriteFondo.getX() + 25 * pValueX;
                        spriteFondo.setX(x);
                    }
                }
            }

        });
        EscenaCazaJurasicaBossFinal.this.setChildScene(control);
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
        Laser spriteProyectil = new Laser(spritePersonaje.getX(),  spritePersonaje.getY(),regionProyectil, actividadJuego.getVertexBufferObjectManager(),personajeVolteandoDerecha,false);

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