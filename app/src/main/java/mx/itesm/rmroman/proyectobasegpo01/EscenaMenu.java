package mx.itesm.rmroman.proyectobasegpo01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.io.IOException;
import java.util.Random;
import java.util.ResourceBundle;


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
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);

        // Crea el fondo de la pantalla
        SpriteBackground fondo = new SpriteBackground(1, 1, 1, spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        // Mostrar un recuadro atrás del menú
        // Mostrar opciones de menú

        admMusica.cargarMusica(4);
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
        menu.addMenuItem(opcionMusica);
        menu.addMenuItem(opcionBotonArcade);
        menu.addMenuItem(opcionBotonComics);
        menu.addMenuItem(opcionContinuarJuego);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparentee

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)

        if(tipoMenu==4) {
            opcionAcercaDe.setPosition(540, 320);
            opcionJugar.setPosition(-320, 226);
            opcionBotonRojo.setPosition(540, -340);
            opcionMusica.setPosition(-570, 310);
            opcionBotonArcade.setPosition(-480, 10);
            opcionBotonComics.setPosition(-320, -70);
            opcionContinuarJuego.setPosition(-290, 126);
        }
        else if(tipoMenu==2){
            opcionAcercaDe.setPosition(-570, -320);
            opcionJugar.setPosition(360, 220);
            opcionBotonRojo.setPosition(520, -340);
            opcionMusica.setPosition(-570, 310);
            opcionBotonArcade.setPosition(430, -60);
            opcionBotonComics.setPosition(-570, -370);
            opcionContinuarJuego.setPosition(90, 95);
        }
        else{
            opcionAcercaDe.setPosition(-570, -320);
            opcionJugar.setPosition(360, 220);
            opcionBotonRojo.setPosition(520, -340);
            opcionMusica.setPosition(-570, 310);
            opcionBotonArcade.setPosition(210, -60);
            opcionBotonComics.setPosition(90, 95);
            opcionContinuarJuego.setPosition(430, -60);
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
                        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_ACERCA_DE);

                        break;

                    case OPCION_JUGAR:
                        // Mostrar la escena de AcercaDe
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaCargando(0);
                        admEscenas.setEscena(TipoEscena.ESCENA_CARGANDO);

                        break;

                    case OPCION_MUSICA:
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaAjustes();
                        admEscenas.setEscena(TipoEscena.ESCENA_AJUSTES);

                        break;

                    case OPCION_BOTON_ROJO:
                        // Mostrar la escena de AcercaDe
                        admMusica.vibrar(90);
                        admMusica.reproducirMusicaBoton();
                        admEscenas.liberarEscenaMenu();
                        admEscenas.crearEscenaCazaJurasicaRunner();
                        admEscenas.setEscena(TipoEscena.ESCENA_CAZA_JURASICA_RUNNER);

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
                        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_COMIC);

                        break;

                    case OPCION_CONTINUAR_JUEGO:
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
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU;
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
