package mx.itesm.jcampos.huntingcows;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.content.Context;
import android.os.Vibrator;


/**
 * Created by rmroman on 11/09/15.
 */
public class EscenaAcercaDe extends mx.itesm.jcampos.huntingcows.EscenaBase
{
    // Regiones para imágenes
    private ITextureRegion regionFondo;
    // Sprite para el fondo
    //sprite
    private Sprite spriteFondo;
    private Vibrator vibrador;

    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/MenuInicio/creditos.jpg");
        vibrador=(Vibrator)actividadJuego.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
        admMusica.cargarMusica(1);
        vibrador.vibrate(10000);

    }


    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        vibrador.cancel();
        admEscenas.liberarEscenaAcercaDe();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_MENU);

    }

    @Override
    public mx.itesm.jcampos.huntingcows.TipoEscena getTipoEscena() {

        return mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_ACERCA_DE;
    }

    @Override
    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        admMusica.liberarMusica();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
