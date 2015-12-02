package mx.itesm.rmroman.huntingcows;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by rmroman on 11/09/15.
 */
public class EscenaArcade extends EscenaBase
{
    // Regiones para imágenes

    private ITextureRegion[] regionSlides;
    private ITextureRegion[] regionFlechas;
    private ITextureRegion regionSlideActual;
    private ITextureRegion regionFlechaActual;
    private ITextureRegion regionsiguiente;
    private ITextureRegion regionanterior;

    private int contadorSlide;

    private final int OPCION_SIGUIENTE = 0;
    private final int OPCION_ANTERIOR = 1;

    private MenuScene menu;


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFlecha;

    @Override
    public void cargarRecursos() {

        regionSlides = new ITextureRegion[3];
        regionSlides[0]=cargarImagen("Imagenes/Arcade/jurassic_level.jpg");
        regionSlides[1] = cargarImagen("Imagenes/Arcade/pirates_level.jpg");
        regionSlides[2] = cargarImagen("Imagenes/Arcade/eyptian_level.jpg");

        regionFlechas = new ITextureRegion[3];
        regionFlechas[0]=cargarImagen("Imagenes/Arcade/play_green.png");
        regionFlechas[1] = cargarImagen("Imagenes/Arcade/play_blue.png");
        regionFlechas[2] = cargarImagen("Imagenes/Arcade/play_yellow.png");

        regionSlideActual=regionSlides[0];
        regionFlechaActual=regionFlechas[0];

        regionsiguiente = cargarImagen("Imagenes/Historia/comic_next.png");
        regionanterior = cargarImagen("Imagenes/Historia/comic_prev.png");
        contadorSlide=0;
    }

    @Override
    public void crearEscena() {

        actividadJuego.camara.setCenter(640,400);

        actividadJuego.camara.setHUD(new HUD());

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
        attachChild(spriteFondo);

        Sprite spriteFlecha = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 4,
                regionFlechaActual, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    if(contadorSlide==0) {
                        admEscenas.liberarEscenaArcade();
                        admEscenas.crearEscenaMundosCazaJurasica();
                        admEscenas.setEscena(TipoEscena.ESCENA_MUNDOS_CAZA_JURASICA);
                    }
                    else if(contadorSlide==1) {
                        //admMusica.vibrar(90);
                        admEscenas.liberarEscenaArcade();
                        admEscenas.crearEscenaCargando(4);
                        admEscenas.setEscena(TipoEscena.ESCENA_CARGANDO);
                    }
                    else {
                        admEscenas.liberarEscenaArcade();
                        admEscenas.crearEscenaCargando(7);
                        admEscenas.setEscena(TipoEscena.ESCENA_CARGANDO);
                    }
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteFlecha);
        registerTouchArea(spriteFlecha);


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

        // Agrega las opciones al menú
        menu.addMenuItem(opcionSiguiente);
        menu.addMenuItem(opcionanterior);

        // que es esto??


        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionSiguiente.setPosition(450, -350);

        opcionanterior.setPosition(-450, -350);

        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch (pMenuItem.getID()) {

                    case OPCION_SIGUIENTE:
                        // Mostrar la escena de AcercaDe
                        if (contadorSlide < regionSlides.length-1) {
                            contadorSlide++;
                            regionSlideActual = regionSlides[contadorSlide];
                            regionFlechaActual = regionFlechas[contadorSlide];
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
                            attachChild(spriteFondo);
                            spriteFlecha = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 4, regionFlechaActual);
                            attachChild(spriteFlecha);
                            break;
                        }

                        return true;

                    case OPCION_ANTERIOR:
                        // Mostrar la escena de AcercaDe
                        if (contadorSlide > 0) {
                            contadorSlide--;
                            regionSlideActual = regionSlides[contadorSlide];
                            spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionSlideActual);
                            attachChild(spriteFondo);
                            regionFlechaActual = regionFlechas[contadorSlide];
                            spriteFlecha = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 4, regionFlechaActual);
                            attachChild(spriteFlecha);
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
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaArcade();
    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_ARCADE;
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
