package mx.itesm.jcampos.huntingcows;

import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Campos on 09/10/15.
 */

public class EscenaCazaJurasica extends EscenaBase{

    private ITextureRegion regionFondo;

    private Sprite spriteFondo;

    private ITextureRegion flechaIzquierda;
    private ITextureRegion flechaDerecha;


    private final int OPCION_SIGUIENTE = 0;
    private final int OPCION_ANTERIOR = 1;


    private MenuScene menu;

    private AnimatedSprite spritePersonaje;
    private TiledTextureRegion regionPersonajeAnimado;



    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 600, 158, 1, 4);

        flechaIzquierda=cargarImagen("Imagenes/Historia/flecha.png");
        flechaDerecha=cargarImagen("Imagenes/Historia/flecha.png");

    }


    public void crearEscena() {
        // Fondo animado

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);

        // Crea el fondo de la pantalla
        SpriteBackground fondo = new SpriteBackground(1,1,1, spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        spritePersonaje = new AnimatedSprite(-ControlJuego.ANCHO_CAMARA/4+ControlJuego.ANCHO_CAMARA, ControlJuego.ALTO_CAMARA/4,
                regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);   // 200ms entre frames, 1000/200 fps
        attachChild(spritePersonaje);

        admMusica.cargarMusica(2);

        agregarMenu();

    }

    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA,ControlJuego.ALTO_CAMARA/2);

        // Crea las opciones (por ahora, acerca de y jugar)
        IMenuItem opcionSiguiente = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_SIGUIENTE,
                flechaDerecha, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionanterior = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_ANTERIOR,
                flechaIzquierda, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);


        // Agrega las opciones al menú
        menu.addMenuItem(opcionSiguiente);
        menu.addMenuItem(opcionanterior);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionSiguiente.setPosition(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2);

        opcionanterior.setPosition(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2);
        opcionanterior.setRotation(-180);


        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch (pMenuItem.getID()) {

                    case OPCION_SIGUIENTE:

                        admMusica.vibrar(250);
                        admMusica.reproducirMusicaBoton();
                        break;


                    case OPCION_ANTERIOR:
                        // Mostrar la escena de AcercaDe
                        admMusica.vibrar(250);
                        admMusica.reproducirMusicaBoton();
                        break;

                }
                return true;
            }
        });

        // Asigna este menú a la escena
        setChildScene(menu);
    }


    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
    }



    public void onBackKeyPressed() {
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);

    }

    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_CAZA_JURASICA;
    }


    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();

    }

    public void liberarRecursos() {
        // Detiene el acelerómetro
        admMusica.liberarMusica();
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);
        regionFondo.getTexture().unload();
        regionFondo = null;
    }




}
