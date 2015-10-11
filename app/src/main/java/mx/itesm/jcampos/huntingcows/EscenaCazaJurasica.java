package mx.itesm.jcampos.huntingcows;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;


/**
 * Representa la escena del MENU PRINCIPAL
 *
 * @author Roberto Martínez Román
 */
public class EscenaCazaJurasica extends EscenaBase
{
    // Regiones para las imágenes de la escena
    private ITextureRegion regionFondo;
    private ITextureRegion regionBtnFlechaIzquierda;
    private ITextureRegion regionBtnFlechaDerecha;


    // Sprites sobre la escena
    private Sprite spriteFondo;

    // Un menú de tipo SceneMenu
    private MenuScene menu;     // Contenedor de las opciones


    // Constantes para cada opción
    private final int OPCION_IZQUIERDA = 0;
    private final int OPCION_DERECHA = 1;




    @Override

    public void cargarRecursos() {
        // Fondo
        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        // Botones del menú
        regionBtnFlechaDerecha = cargarImagen("Imagenes/Historia/flecha.png");
        regionBtnFlechaIzquierda = cargarImagen("Imagenes/Historia/flecha.png");

    }

    @Override
    public void crearEscena() {
        // Creamos el sprite de manera óptima
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);

        // Crea el fondo de la pantalla
        SpriteBackground fondo = new SpriteBackground(1,1,1, spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        // Mostrar un recuadro atrás del menú
        // Mostrar opciones de menú

        admMusica.cargarMusica(2);
        admMusica.cargarMusicaBoton();

        agregarMenu();


    }


    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2);

        // Crea las opciones (por ahora, acerca de y jugar)
        IMenuItem opcionDerecha = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_DERECHA,
                regionBtnFlechaDerecha, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionIzquierda = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_IZQUIERDA,
                regionBtnFlechaIzquierda, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);



        // Agrega las opciones al menú
        menu.addMenuItem(opcionDerecha);
        menu.addMenuItem(opcionIzquierda);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparentee

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionDerecha.setPosition(300, -320);
        opcionIzquierda.setPosition(340, 190);
        opcionIzquierda.setRotation(-180);

        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch(pMenuItem.getID()) {

                    case OPCION_IZQUIERDA:
                        // Mostrar la escena de AcercaDe
                        admMusica.reproduceio();
                        break;

                    case OPCION_DERECHA:
                        // Mostrar la escena de AcercaDe
                        admMusica.reproduceio();
                        break;

                }

                return true;
            }
        });

        // Asigna este menú a la escena
        setChildScene(menu);
    }

    // La escena se debe actualizar en este método que se repite "varias" veces por segundo
    // Aquí es donde programan TODA la acción de la escena (movimientos, choques, disparos, etc.)
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

    }


    @Override
    public void onBackKeyPressed() {
        // Salir del juego, no hacemos nada
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaHistoriaIntro();
    }

    @Override
    public mx.itesm.jcampos.huntingcows.TipoEscena getTipoEscena() {

        return mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_CAZA_JURASICA;
    }


    @Override
    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();      // La escena se deconecta del engine
        this.dispose();         // Libera la memoria
    }

    @Override
    public void liberarRecursos() {
        admMusica.liberarMusica();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
