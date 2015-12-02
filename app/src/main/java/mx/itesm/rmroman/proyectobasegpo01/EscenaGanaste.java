package mx.itesm.rmroman.proyectobasegpo01;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * La escena que se muestra cuando corre la aplicación (Logo del TEC)
 *
 * @author Roberto Martínez Román
 */
public class EscenaGanaste extends EscenaBase
{
    // Imágenes
    private ITextureRegion regionFondo;
    // Sprites sobre la escena
    private Sprite spriteFondo;

    // Carga todos los recursos para ESTA ESCENA.
    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/victoria.jpg");
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

        //admMusica.cargarMusica(3);
    }

    // La escena se debe actualizar en este método que se repite "varias" veces por segundo
    // Aquí es donde programan TODA la acción de la escena (movimientos, choques, disparos, etc.)
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

    }

    @Override
    public void onBackKeyPressed() {
        admEscenas.liberarEscenaGanaste();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_MENU);
    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_GANASTE;
    }

    // Libera la escena misma del engine
    @Override
    public void liberarEscena() {
        //admMusica.liberarMusica();
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
