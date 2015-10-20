package mx.itesm.rmroman.proyectobasegpo01;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Campos on 09/10/15.
 */
public class EscenaSplashHuntingCows extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionLogo;
    private ITextureRegion regionPress;
    // Sprites sobre la escena
    private Sprite spriteFondo;
    private Sprite spriteLogo;
    private Sprite spritePress;

    // Carga todos los recursos para ESTA ESCENA.
    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/fondo_hunting.jpg");
        regionLogo = cargarImagen("Imagenes/titulo.png");
        regionPress = cargarImagen("Imagenes/press.png");
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

        spriteLogo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionLogo);
        attachChild(spriteLogo);

        spritePress = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionPress);
        attachChild(spritePress);

        admMusica.cargarMusica(0);
        admMusica.setMusicaTodo();


    }

    // La escena se debe actualizar en este método que se repite "varias" veces por segundo
    // Aquí es donde programan TODA la acción de la escena (movimientos, choques, disparos, etc.)
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

    }

    @Override
    public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {

        if(pSceneTouchEvent.isActionDown()){
            admEscenas.liberarEscenaSplashHuntingCows();
            admEscenas.crearEscenaMenu();
            admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        }
        return super.onSceneTouchEvent(pSceneTouchEvent);
    }


    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscena() {

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
        admMusica.liberarMusica();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
