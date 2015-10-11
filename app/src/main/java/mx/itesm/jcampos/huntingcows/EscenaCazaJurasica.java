package mx.itesm.jcampos.huntingcows;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Campos on 11/10/15.
 */
public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionsiguiente;
    private ITextureRegion regionanterior;
    private ITextureRegion regionFondo;

    private AnimatedSprite spritePersonaje;

    private TiledTextureRegion regionPersonajeAnimado;

    private final int OPCION_SIGUIENTE = 0;
    private final int OPCION_ANTERIOR = 1;



    private MenuScene menu;


    // Sprite para el fondo
    private Sprite spriteFondo;

    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionsiguiente = cargarImagen("Imagenes/Historia/flecha.png");
        regionanterior = cargarImagen("Imagenes/Historia/flecha.png");

        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 600, 158, 1, 4);


    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
        agregarMenu();

        spritePersonaje = new AnimatedSprite(-ControlJuego.ANCHO_CAMARA/4+ControlJuego.ANCHO_CAMARA, ControlJuego.ALTO_CAMARA/4,regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);
        attachChild(spritePersonaje);

        admMusica.cargarMusica(2);

    }

    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2);

        // Crea las opciones (por ahora, acerca de y jugar)
        IMenuItem opcionSiguiente = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_SIGUIENTE,
                regionsiguiente, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionanterior = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_ANTERIOR,
                regionanterior, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);


        // Agrega las opciones al menú
        menu.addMenuItem(opcionSiguiente);
        menu.addMenuItem(opcionanterior);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionSiguiente.setPosition((-ControlJuego.ANCHO_CAMARA/5)-30, -ControlJuego.ANCHO_CAMARA/4);

        opcionanterior.setPosition((-ControlJuego.ANCHO_CAMARA/3)-80, -ControlJuego.ANCHO_CAMARA/4);
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

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_MENU);
    }

    @Override
    public mx.itesm.jcampos.huntingcows.TipoEscena getTipoEscena() {

        return mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_CAZA_JURASICA;
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
}
