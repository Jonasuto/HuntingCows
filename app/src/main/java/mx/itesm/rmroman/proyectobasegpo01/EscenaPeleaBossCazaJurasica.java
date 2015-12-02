package mx.itesm.rmroman.proyectobasegpo01;

import android.util.Log;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import java.util.Random;

/**
 * Created by Campos on 09/10/15.
 */
public class EscenaPeleaBossCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionBase;
    private ITextureRegion regionBaseBoss;
    private ITextureRegion regionFuego1;
    private ITextureRegion regionFuego2;

    private float fuerzaSalto;

    private Random aleatorio;

    private int contadorTiempoSalto;

    private ITextureRegion regionEnemigoBossFinal;

    private Enemigo spriteEnemigoBossFinal;


    // Sprites sobre la escena
    private Sprite spriteFondo;
    private Sprite spriteBase;
    private Sprite spriteBaseBoss;
    private Sprite spriteFuego1;
    private Sprite spriteFuego2;
    private Sprite spriteFuegoActual;

    private TiledTextureRegion regionPersonajeAnimado;
    private TiledTextureRegion regionPersonajeAnimadoIzquierda;

    private Jugador spritePersonaje;
    private Jugador spritePersonajeizquierda;
    private Jugador spritePersonajeDerecha;

    private boolean esFuego1;

    private int contadorDeTiempoBoss;

    private boolean temblor;

    private boolean paArribaEnemigo;

    private boolean superSalto;

    private boolean saltoNormal;

    // Carga todos los recursos para ESTA ESCENA.
    @Override
    public void cargarRecursos() {

        esFuego1=true;

        contadorTiempoSalto=0;

        aleatorio= new Random();

        contadorDeTiempoBoss=0;

        regionFondo = cargarImagen("Imagenes/EscenarioBoss/fondo-boss.jpg");
        regionBase= cargarImagen("Imagenes/EscenarioBoss/base_boss.png");

        regionBaseBoss= cargarImagen("Imagenes/EscenarioBoss/base2_boss.png");

        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/Roman/prueba.png", 1000, 200, 1, 9);
        regionPersonajeAnimadoIzquierda = cargarImagenMosaico("Imagenes/Roman/pruebaiz.png", 1000, 200, 1, 9);

        regionFuego1=cargarImagen("Imagenes/EscenarioBoss/fuego_1.png");
        regionFuego2=cargarImagen("Imagenes/EscenarioBoss/fuego_2.png");

        regionEnemigoBossFinal=cargarImagen("Imagenes/Niveles/CazaJurasica/Enemigos/boss_Jurasico.png");


    }

    // Arma la escena que se presentará en pantalla
    @Override
    public void crearEscena() {

        // Crea el(los) sprite(s) de la escena
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2,
                regionFondo);

        // Crea el fondo de la pantalla que significa el 0.28 f y eso?
        SpriteBackground fondo = new SpriteBackground(0.28f, 0.63f, 0.92f,spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        //admMusica.cargarMusica(0);

        spriteBase= cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/4, regionBase);
        spriteFondo.attachChild(spriteBase);

        spriteBaseBoss= cargarSprite(1200, 500, regionBaseBoss);
        spriteFondo.attachChild(spriteBaseBoss);

        spriteEnemigoBossFinal = new Enemigo(1000, 400,regionEnemigoBossFinal, actividadJuego.getVertexBufferObjectManager(),3,true,true,40,false,false,false);
        spriteFondo.attachChild(spriteEnemigoBossFinal);

        spritePersonajeizquierda = new Jugador((ControlJuego.ANCHO_CAMARA/2)-200, (ControlJuego.ALTO_CAMARA/4)-20,regionPersonajeAnimadoIzquierda, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeizquierda.animate(70);

        spritePersonajeDerecha = new Jugador((ControlJuego.ANCHO_CAMARA/2), (ControlJuego.ALTO_CAMARA/2),regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonajeDerecha.animate(70);

        spritePersonaje=spritePersonajeDerecha;
        spriteFondo.attachChild(spritePersonaje);

        spriteFuego1= cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/4, regionFuego1);
        spriteFuego2= cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/4, regionFuego2);

        spriteFuegoActual=spriteFuego1;
        spriteFondo.attachChild(spriteFuegoActual);

    }

    // La escena se debe actualizar en este método que se repite "varias" veces por segundo
    // Aquí es donde programan TODA la acción de la escena (movimientos, choques, disparos, etc.)
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);


        contadorDeTiempoBoss+=1;
        if(contadorDeTiempoBoss>2){
            if(esFuego1){
                spriteFuegoActual.detachSelf();
                esFuego1=false;
                spriteFuegoActual=spriteFuego2;
                spriteFondo.attachChild(spriteFuegoActual);
                contadorDeTiempoBoss=0;
            }
            else{
                spriteFuegoActual.detachSelf();
                esFuego1=true;
                spriteFuegoActual=spriteFuego1;
                spriteFondo.attachChild(spriteFuegoActual);
                contadorDeTiempoBoss=0;
            }
        }

        Log.i("tiempo,",contadorTiempoSalto+" salto "+spriteEnemigoBossFinal.getBrincando());


        if (spriteEnemigoBossFinal.getBrincando() == false) {


            if(contadorTiempoSalto<10) {
                contadorTiempoSalto+=aleatorio.nextInt(2);
                spriteEnemigoBossFinal.setBrincando(true);
                saltoNormal=true;
                spriteEnemigoBossFinal.setBrincando(true);
                paArribaEnemigo=true;
                fuerzaSalto=4.1f;
            }

            else{

                contadorTiempoSalto=0;
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
                if(spriteEnemigoBossFinal.getY()>500){
                    spriteEnemigoBossFinal.setY(spriteEnemigoBossFinal.getY()-55);
                }
                else{
                    superSalto=false;
                    //admMusica.vibrar(100);
                    spriteEnemigoBossFinal.setBrincando(false);
                    temblor=true;
                }
            }
        }

        if(saltoNormal==true ){
            fuerzaSalto-=0.2f;

            spriteEnemigoBossFinal.setY(spriteEnemigoBossFinal.getY()+(5*fuerzaSalto));

            if(spriteEnemigoBossFinal.getY()<=500){
                saltoNormal=false;
                spriteEnemigoBossFinal.setBrincando(false);
            }

        }

    }


    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_ESCENA_PELEA_BOSS_CAZA_JURASICA;
    }

    // Libera la escena misma del engine
    @Override
    public void liberarEscena() {
        this.detachSelf();      // La escena se deconecta del engine
        this.dispose();         // Libera la memoria

        // Libera las imágenes
        liberarRecursos();
    }

    // Libera cada una de las regiones asignadas para esta escena
    @Override
    public void liberarRecursos() {
        // Estas dos instrucciones por cada región inicializada
        //admMusica.liberarMusica();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
