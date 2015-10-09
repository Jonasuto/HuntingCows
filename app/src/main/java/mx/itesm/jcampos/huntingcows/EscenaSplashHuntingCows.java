package mx.itesm.jcampos.huntingcows;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Campos on 09/10/15.
 */
public class EscenaSplashHuntingCows extends EscenaBase {

    private ITextureRegion regionFondo;
    // Sprites sobre la escena
    private Sprite spriteFondo;

    // Carga todos los recursos para ESTA ESCENA.
    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("logoHuntingCows.png");
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
    public mx.itesm.jcampos.huntingcows.TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_SPLASH_HUNTING_COWS;
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
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
