package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Campos on 09/10/15.
 */
public class EscenaMundosCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionPlaneta;

    // Sprites sobre la escena
    private Sprite spriteFondo;

    // Carga todos los recursos para ESTA ESCENA.
    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/fondo_hunting.jpg");
        regionPlaneta = cargarImagen("Imagenes/Planetas/planeta_verde.png");

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

        admMusica.cargarMusica(0);

        Sprite Nivel1 = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2-300,
                regionPlaneta, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    admEscenas.liberarEscenaMundosCazaJurasica();
                    admEscenas.crearEscenaCazaJurasica();
                    admEscenas.setEscena(TipoEscena.ESCENA_CAZA_JURASICA);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(Nivel1);
        registerTouchArea(Nivel1);

        Sprite Nivel2 = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                regionPlaneta, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    admEscenas.liberarEscenaMundosCazaJurasica();
                    admEscenas.crearEscenaCazaJurasicaNivel2();
                    admEscenas.setEscena(TipoEscena.ESCENA_CAZA_JURASICA_NIVEL_2);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(Nivel2);
        registerTouchArea(Nivel2);

        Sprite Nivel3 = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2+300,
                regionPlaneta, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    admEscenas.liberarEscenaMundosCazaJurasica();
                    admEscenas.crearEscenaAleatoriedad();
                    admEscenas.setEscena(TipoEscena.ESCENA_ALEATORIEDAD);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(Nivel3);
        registerTouchArea(Nivel3);

    }

    // La escena se debe actualizar en este método que se repite "varias" veces por segundo
    // Aquí es donde programan TODA la acción de la escena (movimientos, choques, disparos, etc.)
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

    }


    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_MUNDOS_CAZA_JURASICA;
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
        admMusica.liberarMusica();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
