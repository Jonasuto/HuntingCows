package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

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
public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoPausa;
    private ITextureRegion regionControlSalto;
    private ITextureRegion regionBase;
    private ITextureRegion regionNave;
    private ITextureRegion regionHoyoNegro;
    private ITextureRegion regionOvni;

    private Random elQueSigue;

    private float[] posicionesEnemigos;

    private ArrayList<Enemigo> listaEnemigos;

    private ITextureRegion regionEnemigo;
    private ITextureRegion regionVida;

    private ITextureRegion vidas;
    private Sprite[] spriteVidas;

    private Sprite spriteNave;

    private Sprite spriteOvni;
    private Sprite spriteOvni2;
    private Sprite spriteOvni3;
    private Sprite spriteOvni4;
    private Sprite spriteOvni5;


    private boolean personajeSaltando = false;

    private AnalogOnScreenControl control;

    private Jugador spritePersonaje;
    private Enemigo spriteEnemigo;

    private boolean juegoCorriendo = true;


    private CameraScene escenaPausa;
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;


    private TiledTextureRegion regionPersonajeAnimado;

    private boolean personajeVolteandoDerecha=true;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondo2;
    private Sprite spriteFondo3;
    private Sprite spriteHoyoNegro;
    private Sprite spriteFondoPausa;

    private ButtonSprite btnSaltar;
    private ButtonSprite btnDisparar;
    private int cantidadVida;


    // Proyectiles del personaje
    private ITextureRegion regionProyectil;
    private ArrayList<Laser> listaProyectiles;

    private ArrayList<Vida> listaVidasEncontradas;



    @Override
    public void cargarRecursos() {


        elQueSigue=new Random();

        cantidadVida =2;
        vidas=cargarImagen("Imagenes/corazon.png");
        regionNave=cargarImagen("Imagenes/nave.png");
        regionFondo = cargarImagen("Imagenes/Niveles/fondo2.jpg");
        regionFondoPausa = cargarImagen("Imagenes/logoHuntingCows.png");
        regionBase=cargarImagen("Imagenes/baseJoystick.png");
        regionControlSalto =cargarImagen("Imagenes/joystick.png");
        regionEnemigo = cargarImagen("Imagenes/vacaDinosaurio.png");
        regionVida = cargarImagen("Imagenes/corazon.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 590, 138, 1, 4);
        // Pausa
        regionBtnPausa = cargarImagen("Imagenes/btnPausa.png");
        regionPausa = cargarImagen("Imagenes/pausa.png");
        regionHoyoNegro= cargarImagen("Imagenes/hoyoNegro.png");
        regionProyectil = cargarImagen("Imagenes/laser.png");


        regionOvni= cargarImagen("Imagenes/naveVaca.png");
    }

    @Override
    public void crearEscena() {


        listaProyectiles = new ArrayList<>();

        listaEnemigos = new ArrayList<>();

        listaVidasEncontradas = new ArrayList<>();

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        attachChild(spriteFondo);

        spriteFondo2 = cargarSprite(5970, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo2);

        spriteFondo3 = cargarSprite(8300, ControlJuego.ALTO_CAMARA/2 , regionFondo);
        spriteFondo.attachChild(spriteFondo3);

        spriteOvni=cargarSprite(200, 700, regionOvni);
        spriteOvni.setSize(spriteOvni.getWidth() - 30, spriteOvni.getHeight() - 30);
        spriteFondo.attachChild(spriteOvni);

        spriteOvni2=cargarSprite(1000, 700, regionOvni);
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

        spriteNave=cargarSprite(200, 270, regionNave);
        spriteFondo.attachChild(spriteNave);

        spriteVidas[0]= cargarSprite(1000, 780, vidas);
        attachChild(spriteVidas[0]);

        spriteVidas[1]= cargarSprite(1050, 780, vidas);
        attachChild(spriteVidas[1]);

        spriteVidas[2]= cargarSprite(1100, 780, vidas);
        attachChild(spriteVidas[2]);

        spriteHoyoNegro= cargarSprite(9500, 400, regionHoyoNegro);
        spriteFondo.attachChild(spriteHoyoNegro);

        spritePersonaje = new Jugador(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/4-100,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

        admMusica.cargarMusica(2);

        posicionarEnemigos();

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
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
    }


    private void pausarJuego() {
        if (juegoCorriendo) {
            escenaPausa.setChildScene(control);
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        } else {
            clearChildScene();
            EscenaCazaJurasica.this.setChildScene(control);
            juegoCorriendo = true;
        }
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_CAZA_JURASICA;
    }

    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }


        Log.i("fondo", spriteFondo.getX() + "");


        if(spritePersonaje.collidesWith(spriteHoyoNegro)){
            onBackKeyPressed();
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
                    onBackKeyPressed();
                }
                else{
                    enemigo.detachSelf();
                    spriteVidas[2- cantidadVida].detachSelf();
                    listaEnemigos.remove(enemigo);
                    admMusica.vibrar(200);
                    spritePersonaje.setSize(spritePersonaje.getWidth() - 20, spritePersonaje.getHeight() - 20);
                    cantidadVida--;
                }
            }
        }
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


                    if(spriteFondo.getX()>2000){
                        if(pValueX<0){
                        }
                        else{
                            pValueX = pValueX * (-1);
                            float x = spriteFondo.getX() + 25 * pValueX;
                            spriteFondo.setX(x);
                        }
                    }

                    else if(spriteFondo.getX()<-7013){
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