package mx.itesm.rmroman.huntingcows;

import android.util.Log;
import android.view.View;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.io.IOException;
import java.util.Random;


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
    private ITextureRegion regionBtnContinuarJuego;
    private ITextureRegion regionBtnComic;
    private ITextureRegion regionBtnArcade;
    public Engine engine;
    private int tipoMenu;

    private View ver;

    private View v;


    // Sprites sobre la escena
    private Sprite spriteFondo;

    // Un menú de tipo SceneMenu
    private MenuScene menu;     // Contenedor de las opciones


    // Constantes para cada opción
    private final int OPCION_ACERCA_DE = 0;
    private final int OPCION_JUGAR = 1;
    private final int OPCION_BOTON_ROJO = 2;
    private final int OPCION_MUSICA = 3;
    private final int OPCION_COMICS = 5;
    private final int OPCION_ARCADE = 6;
    private final int OPCION_CONTINUAR_JUEGO = 7;

    private Music musicaTodo;

    private Random aleatorio;




    @Override

    public void cargarRecursos() {
        // Fondo

        aleatorio= new Random();

        int num=aleatorio.nextInt(5);

        if(num==0){
            regionFondo = cargarImagen("Imagenes/MenuInicio/menus/menu_1.jpg");
            tipoMenu=1;
        }

        else if(num==1){
            regionFondo = cargarImagen("Imagenes/MenuInicio/menus/menu_2.jpg");
            tipoMenu=2;
        }

        else if(num==2){
            regionFondo = cargarImagen("Imagenes/MenuInicio/menus/menu_3.jpg");
            tipoMenu=3;
        }
        else{
            regionFondo = cargarImagen("Imagenes/MenuInicio/menus/menu_4.jpg");
            tipoMenu=4;
        }

        // Botones del menú
        regionBtnAcercaDe = cargarImagen("Imagenes/MenuInicio/botonesMenu/btnAcercaDe.png");
        regionBtnJugar = cargarImagen("Imagenes/MenuInicio/botonesMenu/Jugar.png");
        regionBtnRojo = cargarImagen("Imagenes/MenuInicio/botonesMenu/btnRojo.png");
        regionBtnMusica = cargarImagen("Imagenes/MenuInicio/botonesMenu/musica.png");
        regionBtnArcade = cargarImagen("Imagenes/MenuInicio/botonesMenu/arcade.png");
        regionBtnComic = cargarImagen("Imagenes/MenuInicio/botonesMenu/comic.png");
        regionBtnContinuarJuego = cargarImagen("Imagenes/MenuInicio/botonesMenu/cargar.png");

        v= new View(actividadJuego);

    }

    @Override
    public void crearEscena() {

        // Creamos el sprite de manera óptima
        actividadJuego.camara.setCenter(640, 400);


        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);

        // Crea el fondo de la pantalla
        SpriteBackground fondo = new SpriteBackground(1, 1, 1, spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        // Mostrar un recuadro atrás del menú
        // Mostrar opciones de menú

        //admMusica.cargarMusica(4);
        //admMusica.cargarMusicaBoton();

        agregarMenu();

        if(admMusica.getMusicaMenu()==null){
            admMusica.cargarMusicaMenu();
        }
        else{
            admMusica.reproducirMusicaMenu();
        }


        actividadJuego.camara.setHUD(new HUD());
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

        IMenuItem opcionBotonArcade = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_ARCADE,
                regionBtnArcade, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        IMenuItem opcionBotonComics = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_COMICS,
                regionBtnComic, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);



        // Agrega las opciones al menú
        menu.addMenuItem(opcionAcercaDe);
        menu.addMenuItem(opcionJugar);
        menu.addMenuItem(opcionBotonRojo);
        menu.addMenuItem(opcionBotonArcade);
        menu.addMenuItem(opcionBotonComics);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparentee

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)

        if(tipoMenu==4) {
            opcionAcercaDe.setPosition(-550, 340);
            opcionJugar.setPosition(200, 200);
            opcionBotonRojo.setPosition(550, -340);
            opcionBotonArcade.setPosition(450, 0);
            opcionBotonComics.setPosition(200, -200);
        }
        else if(tipoMenu==2){
            opcionAcercaDe.setPosition(-550, 340);
            opcionJugar.setPosition(200, 200);
            opcionBotonRojo.setPosition(550, -340);
            opcionBotonArcade.setPosition(450, 0);
            opcionBotonComics.setPosition(200, -200);
        }
        else if(tipoMenu==3){
            opcionAcercaDe.setPosition(-550, 340);
            opcionJugar.setPosition(200, 200);
            opcionBotonRojo.setPosition(550, -340);
            opcionBotonArcade.setPosition(450, 0);
            opcionBotonComics.setPosition(200, -200);
        }
        else if(tipoMenu==1){
            opcionAcercaDe.setPosition(-550, 340);
            opcionJugar.setPosition(200, 200);
            opcionBotonRojo.setPosition(550, -340);
            opcionBotonArcade.setPosition(450, 0);
            opcionBotonComics.setPosition(200, -200);
        }

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
                        admEscenas.setEscena(TipoEscena.ESCENA_ACERCA_DE);

                        break;

                    case OPCION_JUGAR:
                        // Mostrar la escena de AcercaDe
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaCargando(0);
                        admEscenas.setEscena(TipoEscena.ESCENA_CARGANDO);

                        break;

                    case OPCION_MUSICA:

                        break;

                    case OPCION_BOTON_ROJO:
                        // Mostrar la escena de AcercaDe
                        //admMusica.vibrar(90);
                        //admMusica.reproducirMusicaBoton();
                        admMusica.reproducirMusicaBoton();

                        break;

                    case OPCION_ARCADE:
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaArcade();
                        admEscenas.setEscena(TipoEscena.ESCENA_ARCADE);
                        break;

                    case OPCION_COMICS:
                        // Mostrar la escena de AcercaDe
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaComic();
                        admEscenas.setEscena(TipoEscena.ESCENA_COMIC);

                        break;

                    case OPCION_CONTINUAR_JUEGO:
                        // Mostrar la escena de AcercaDe
                        //admMusica.vibrar(250);
                        //admMusica.reproducirMusicaBoton();
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


        Log.i("estoy",actividadJuego.camara.getCenterX()+"-- "+actividadJuego.camara.getCenterY());
    }


    @Override
    public void onBackKeyPressed() {
        // Salir del juego, no hacemos nada
    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_MENU;
    }

    @Override
    public void liberarEscena() {
        admMusica.pararMusicaMenu();
        liberarRecursos();
        this.detachSelf();      // La escena se deconecta del engine
        this.dispose();         // Libera la memoria
    }

    @Override
    public void liberarRecursos() {
        //admMusica.liberarMusica();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
