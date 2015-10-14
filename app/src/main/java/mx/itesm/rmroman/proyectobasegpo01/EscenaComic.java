package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Campos on 10/10/15.
 */
public class EscenaComic extends EscenaBase
{
    // Regiones para imágenes

    private ITextureRegion[] regionSlides;
    private ITextureRegion regionSlideActual;
    private ITextureRegion regionsiguiente;
    private ITextureRegion regionanterior;
    private ITextureRegion regionfinal;

    private int contadorSlide;



    private final int OPCION_SIGUIENTE = 0;
    private final int OPCION_ANTERIOR = 1;
    private final int OPCION_FINAL = 2;

    private MenuScene menu;


    // Sprite para el fondo
    private Sprite spriteFondo;

    @Override
    public void cargarRecursos() {

        regionSlides = new ITextureRegion[9];
        regionSlides[0]=cargarImagen("Imagenes/Historia/IntroHistoria/uno.jpg");
        regionSlides[1] = cargarImagen("Imagenes/Historia/IntroHistoria/dos.jpg");
        regionSlides[2] = cargarImagen("Imagenes/Historia/IntroHistoria/tres.jpg");
        regionSlides[3] = cargarImagen("Imagenes/Historia/IntroHistoria/cuatro.jpg");
        regionSlides[4] = cargarImagen("Imagenes/Historia/IntroHistoria/cinco.jpg");
        regionSlides[5] = cargarImagen("Imagenes/Historia/IntroHistoria/seis.jpg");
        regionSlides[6] = cargarImagen("Imagenes/Historia/CazaJurasica/jurasicouno.jpg");
        regionSlides[7] = cargarImagen("Imagenes/Historia/CazaJurasica/jurasico2.jpg");
        regionSlides[8] = cargarImagen("Imagenes/Historia/CazaJurasica/jurasicotres.jpg");

        regionSlideActual=regionSlides[0];
        regionsiguiente = cargarImagen("Imagenes/Historia/flecha.png");
        regionanterior = cargarImagen("Imagenes/Historia/flecha.png");
        regionfinal = cargarImagen("Imagenes/Historia/flecha.png");
        contadorSlide=0;
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
        attachChild(spriteFondo);
        agregarMenu();
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
        IMenuItem opcionfinal = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_FINAL,
                regionfinal,actividadJuego.getVertexBufferObjectManager()),1.5f,1);


        // Agrega las opciones al menú
        menu.addMenuItem(opcionSiguiente);
        menu.addMenuItem(opcionanterior);
        menu.addMenuItem(opcionfinal);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionSiguiente.setPosition(450, -350);

        opcionanterior.setPosition(-450, -350);
        opcionanterior.setRotation(-180);
        opcionfinal.setPosition(450, 350);


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
                            regionSlideActual = regionSlides[contadorSlide];
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
                            attachChild(spriteFondo);
                            break;
                        } else {
                            admEscenas.liberarEscenaComic();
                            admEscenas.crearEscenaMenu();
                            admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
                        }


                        return true;

                    case OPCION_ANTERIOR:
                        // Mostrar la escena de AcercaDe
                        if (contadorSlide > 0) {
                            contadorSlide--;
                            regionSlideActual = regionSlides[contadorSlide];
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
                            attachChild(spriteFondo);
                            break;
                        }

                        return true;

                    case OPCION_FINAL:
                        if (contadorSlide < regionSlides.length - 1) {
                            contadorSlide++;
                            regionSlideActual = regionSlides[contadorSlide];
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 3, ControlJuego.ALTO_CAMARA / 3, regionSlideActual);
                            admEscenas.liberarEscenaComic();
                            admEscenas.crearEscenaCazaJurasica();
                            admEscenas.setEscena(TipoEscena.ESCENA_CAZA_JURASICA);
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
                            attachChild(spriteFondo);
                            break;
                        }

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
        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaComic();
    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

        return mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_COMIC;
    }

    @Override
    public void liberarEscena() {
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