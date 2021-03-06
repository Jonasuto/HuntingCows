package mx.itesm.rmroman.huntingcows;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.color.Color;

/**
 * Created by rmroman on 11/09/15.
 */
public class EscenaHistoriaIntro extends EscenaBase
{
    // Regiones para imágenes

    private ITextureRegion[] regionSlides;
    private ITextureRegion regionSlideActual;
    private ITextureRegion regionsiguiente;
    private ITextureRegion regionanterior;
    private ITextureRegion regionfinal;
    private ITextureRegion regionMenu;

    private int contadorSlide;



    private final int OPCION_SIGUIENTE = 0;
    private final int OPCION_ANTERIOR = 1;
    private final int OPCION_FINAL = 2;
    private final int OPCION_MENU = 3;


    private MenuScene menu;

    IMenuItem opcionanterior;
    IMenuItem opcionafinal;


    // Sprite para el fondo
    private Sprite spriteFondo;

    @Override
    public void cargarRecursos() {

        regionSlides = new ITextureRegion[12];
        regionSlides[0]=cargarImagen("Imagenes/Historia/IntroHistoria/historia_00.jpg");
        regionSlides[1] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_01.jpg");
        regionSlides[2] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_02.jpg");
        regionSlides[3] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_03.jpg");
        regionSlides[4] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_04.jpg");
        regionSlides[5] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_05.jpg");
        regionSlides[6] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_06.jpg");
        regionSlides[7] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_07.jpg");
        regionSlides[8] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_08.jpg");
        regionSlides[9] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_09.jpg");
        regionSlides[10] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_10.jpg");
        regionSlides[11] = cargarImagen("Imagenes/Historia/IntroHistoria/historia_11.jpg");
        
        regionSlideActual=regionSlides[0];
        regionsiguiente = cargarImagen("Imagenes/Historia/comic_next.png");
        regionanterior = cargarImagen("Imagenes/Historia/comic_prev.png");
        regionfinal = cargarImagen("Imagenes/Historia/comic_skip.png");
        regionMenu = cargarImagen("Imagenes/menu.png");
        contadorSlide=0;
    }

    @Override
    public void crearEscena() {

        admMusica.cargarMusicaHistoria();
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
        attachChild(spriteFondo);
        agregarMenu();
        actividadJuego.camara.setHUD(new HUD());

        actividadJuego.camara.setCenter(640,400);

    }

    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2);

        // Crea las opciones (por ahora, acerca de y jugar)
        IMenuItem opcionSiguiente = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_SIGUIENTE,
                regionsiguiente, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        opcionanterior = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_ANTERIOR,
                regionanterior, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        opcionafinal = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_FINAL,
                regionfinal, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        final IMenuItem opcionMenu = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_MENU,
                regionMenu, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);


        // Agrega las opciones al menú
        menu.addMenuItem(opcionSiguiente);
        menu.addMenuItem(opcionanterior);
        menu.addMenuItem(opcionafinal);
        menu.addMenuItem(opcionMenu);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionSiguiente.setPosition(450, -350);

        opcionanterior.setPosition(-450, -350);

        opcionafinal.setPosition(450,350);

        opcionMenu.setPosition(-450,360);
        opcionMenu.setColor(Color.WHITE);


        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch (pMenuItem.getID()) {

                    case OPCION_SIGUIENTE:
                        // Mostrar la escena de AcercaDe

                        if (contadorSlide < regionSlides.length - 1) {
                            contadorSlide++;
                            if (contadorSlide == 6) {
                                opcionanterior.setPosition(-450, -290);
                            }
                            else{
                                opcionanterior.setPosition(-450, -350);
                            }
                            if (contadorSlide == 2) {
                                opcionafinal.setPosition(450, 300);
                            }
                            else{
                                opcionafinal.setPosition(450, 350);
                            }
                            if (contadorSlide == 8) {
                                opcionMenu.setPosition(-450,300);
                            }
                            else{
                                opcionMenu.setPosition(-450,360);
                            }
                            regionSlideActual = regionSlides[contadorSlide];
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
                            attachChild(spriteFondo);
                            break;
                        } else {
                            admEscenas.liberarEscenaHistoriaIntro();
                            admEscenas.crearEscenaIntroCazaJurasica();
                            admEscenas.setEscena(TipoEscena.ESCENA_INTRO_CAZA_JURASICA);
                        }
                        return true;

                    case OPCION_ANTERIOR:
                        // Mostrar la escena de AcercaDe
                        if (contadorSlide > 0) {
                            contadorSlide--;
                            if (contadorSlide == 6) {
                                opcionanterior.setPosition(-450, -290);
                            }
                            else{
                                opcionanterior.setPosition(-450, -350);
                            }
                            if (contadorSlide == 2) {
                                opcionafinal.setPosition(450,300);
                            }
                            else{
                                opcionafinal.setPosition(450,350);
                            }
                            if (contadorSlide == 8) {
                                opcionMenu.setPosition(-450,280);
                            }
                            else{
                                opcionMenu.setPosition(-450,360);
                            }
                            regionSlideActual = regionSlides[contadorSlide];
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
                            attachChild(spriteFondo);
                            break;
                        }
                        return true;

                    case OPCION_FINAL:

                        admEscenas.liberarEscenaHistoriaIntro();
                        admEscenas.crearEscenaIntroCazaJurasica();
                        admEscenas.setEscena(TipoEscena.ESCENA_INTRO_CAZA_JURASICA);

                        return true;

                    case OPCION_MENU:

                        onBackKeyPressed();

                        return true;
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
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaHistoriaIntro();
    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_HISTORIA_INTRO;
    }

    @Override
    public void liberarEscena() {
        admMusica.liberarMusicaNivel();
        liberarRecursos();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        regionSlideActual.getTexture().unload();
        regionSlideActual = null;
        regionSlides=null;
    }
}
