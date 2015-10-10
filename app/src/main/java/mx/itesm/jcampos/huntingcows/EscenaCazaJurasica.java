package mx.itesm.jcampos.huntingcows;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Campos on 09/10/15.
 */

public class EscenaCazaJurasica extends EscenaBase {

    private ITextureRegion regionFondo;
    private ITextureRegion regionFondoFrente;

    private AnimatedSprite spritePersonaje;
    private TiledTextureRegion regionPersonajeAnimado;

    private boolean personajeSaltando = false;


    public void cargarRecursos() {
        regionFondo = cargarImagen("Imagenes/Niveles/fondo.jpg");
        regionFondoFrente = cargarImagen("Imagenes/starsFront.png");
        regionPersonajeAnimado = cargarImagenMosaico("Imagenes/kiki.png", 600, 158, 1, 4);

    }


    public void crearEscena() {
        // Fondo animado
        AutoParallaxBackground fondoAnimado = new AutoParallaxBackground(1, 1, 1, 5);


        // Fondo atrás
        Sprite spriteFondoAtras = cargarSprite(ControlJuego.ANCHO_CAMARA/2,
                ControlJuego.ALTO_CAMARA/2, regionFondo);
        fondoAnimado.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-10, spriteFondoAtras));
        // Fondo frente
        Sprite spriteFondofrente = cargarSprite(ControlJuego.ANCHO_CAMARA/2,
                ControlJuego.ALTO_CAMARA / 2, regionFondoFrente);
        fondoAnimado.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-8, spriteFondofrente));

        setBackground(fondoAnimado);

        spritePersonaje = new AnimatedSprite(-ControlJuego.ANCHO_CAMARA/4+ControlJuego.ANCHO_CAMARA, ControlJuego.ALTO_CAMARA/4,
                regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);   // 200ms entre frames, 1000/200 fps
        attachChild(spritePersonaje);

        admMusica.cargarMusica(2);

    }

    public void onBackKeyPressed() {
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);

    }

    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_CAZA_JURASICA;
    }


    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();

    }
    
    public void liberarRecursos() {
        // Detiene el acelerómetro
        admMusica.liberarMusica();
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);
        regionFondo.getTexture().unload();
        regionFondo = null;
        regionFondoFrente.getTexture().unload();
        regionFondoFrente = null;
    }




}
