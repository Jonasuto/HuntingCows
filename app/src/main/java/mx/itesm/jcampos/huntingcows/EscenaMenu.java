package mx.itesm.jcampos.huntingcows;
import android.os.Vibrator;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;


/**
 * Representa la escena del MENU PRINCIPAL
 *
 * @author Roberto Martínez Román
 */
public class EscenaMenu extends EscenaBase
{
    // Regiones para las imágenes de la escena
    private ITextureRegion regionFondo;
    private ITextureRegion regionBtnAcercaDe;
    private ITextureRegion regionBtnJugar;
    private ITextureRegion regionBtnRojo;
    private ITextureRegion regionBtnMusica;
    private ITextureRegion regionBtnSonido;
    private ITextureRegion regionBtnContinuarJuego;
    private ITextureRegion regionBtnComic;
    private ITextureRegion regionBtnArcade;


    // Sprites sobre la escena
    private Sprite spriteFondo;

    // Un menú de tipo SceneMenu
    private MenuScene menu;     // Contenedor de las opciones


    // Constantes para cada opción
    private final int OPCION_ACERCA_DE = 0;
    private final int OPCION_JUGAR = 1;
    private final int OPCION_BOTON_ROJO = 2;
    private final int OPCION_MUSICA = 3;
    private final int OPCION_SONIDO = 4;
    private final int OPCION_COMICS = 5;
    private final int OPCION_ARCADE = 6;
    private final int OPCION_CONTINUAR_JUEGO = 7;




    @Override

    public void cargarRecursos() {
        // Fondo
        regionFondo = cargarImagen("Imagenes/MenuInicio/fondoMenu.jpg");
        // Botones del menú
        regionBtnAcercaDe = cargarImagen("Imagenes/MenuInicio/btnAcercaDe.png");
        regionBtnJugar = cargarImagen("Imagenes/MenuInicio/btnNewGame.png");
        regionBtnRojo = cargarImagen("Imagenes/MenuInicio/btnRojo.png");
        regionBtnMusica = cargarImagen("Imagenes/MenuInicio/musica.png");
        regionBtnSonido = cargarImagen("Imagenes/MenuInicio/sonido.jpg");
        regionBtnArcade = cargarImagen("Imagenes/MenuInicio/btnArcade.png");
        regionBtnComic = cargarImagen("Imagenes/MenuInicio/btnHistoria.png");
        regionBtnContinuarJuego = cargarImagen("Imagenes/MenuInicio/btnLoadGame.png");

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

        admMusica.cargarMusica(0);
        admMusica.cargarMusicaBoton();

        agregarMenu();


    }


    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2);

        // Crea las opciones (por ahora, acerca de y jugar)
        IMenuItem opcionAcercaDe = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_ACERCA_DE,
                regionBtnAcercaDe, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionJugar = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_JUGAR,
                regionBtnJugar, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionBotonRojo = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_BOTON_ROJO,
                regionBtnRojo, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionSonido = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_SONIDO,
                regionBtnSonido, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionMusica = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_MUSICA,
                regionBtnMusica, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionBotonArcade = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_ARCADE,
                regionBtnArcade, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionBotonComics = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_COMICS,
                regionBtnComic, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionContinuarJuego = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_CONTINUAR_JUEGO,
                regionBtnContinuarJuego, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);


        // Agrega las opciones al menú
        menu.addMenuItem(opcionAcercaDe);
        menu.addMenuItem(opcionJugar);
        menu.addMenuItem(opcionBotonRojo);
        menu.addMenuItem(opcionSonido);
        menu.addMenuItem(opcionMusica);
        menu.addMenuItem(opcionBotonArcade);
        menu.addMenuItem(opcionBotonComics);
        menu.addMenuItem(opcionContinuarJuego);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparentee

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionAcercaDe.setPosition(280, -300);
        opcionJugar.setPosition(300, 300);
        opcionBotonRojo.setPosition(460, -300);
        opcionMusica.setPosition(-500, 270);
        opcionSonido.setPosition(-300, 270);
        opcionBotonArcade.setPosition(-300, -100);
        opcionBotonComics.setPosition(0, 0);
        opcionContinuarJuego.setPosition(300, -50);

        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch(pMenuItem.getID()) {

                    case OPCION_ACERCA_DE:
                        // Mostrar la escena de AcercaDe
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaAcercaDe();
                        admEscenas.setEscena(mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_ACERCA_DE);

                        break;

                    case OPCION_JUGAR:
                        // Mostrar la escena de AcercaDe
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaHistoriaIntro();
                        admEscenas.setEscena(mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_HISTORIA_INTRO);

                        break;

                    case OPCION_MUSICA:
                        // Mostrar la escena de AcercaDe
                        admMusica.reproduceio();
                        break;

                    case OPCION_SONIDO:
                        // Mostrar la escena de AcercaDe
                        admMusica.setMusicaTodo();
                        break;

                    case OPCION_BOTON_ROJO:
                        // Mostrar la escena de AcercaDe
                        admMusica.vibrar(500);
                        admMusica.reproducirMusicaBoton();

                        break;

                    case OPCION_ARCADE:
                        // Mostrar la escena de AcercaDe
                        admMusica.reproduceio();
                        break;

                    case OPCION_COMICS:
                        // Mostrar la escena de AcercaDe
                        admMusica.reproduceio();
                        break;

                    case OPCION_CONTINUAR_JUEGO:
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
    }

    @Override
    public mx.itesm.jcampos.huntingcows.TipoEscena getTipoEscena() {

        return mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_MENU;
    }


    @Override
    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();      // La escena se deconecta del engine
        this.dispose();         // Libera la memoria
    }

    @Override
    public void liberarRecursos() {
        admMusica.liberarMusicaMenu();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
