package mx.itesm.rmroman.huntingcows;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by rmroman on 11/09/15.
 */
public class EscenaAcercaDe extends EscenaBase
{
    // Regiones para imágenes

    // Dream Team


    private ITextureRegion regionFondo;
    // Sprite para el fondo
    //sprite
    private Sprite spriteFondo;
    private Sprite spritebtnIrAMenu;
    private ITextureRegion regionIrAMenu;
    private Vibrator vibrador;

    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/MenuInicio/acercaDe.jpg");
        regionIrAMenu=cargarImagen("Imagenes/Ajustes2/pausa_menu.png");
        vibrador=(Vibrator)actividadJuego.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void crearEscena() {

        actividadJuego.camara.setCenter(640,400);
        actividadJuego.camara.setHUD(new HUD());
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
        //admMusica.cargarMusica(1);
        vibrador.vibrate(10000);

        spritebtnIrAMenu = new Sprite(1100, 200,
                regionIrAMenu, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    vibrador.cancel();
                    admEscenas.liberarEscenaAcercaDe();
                    admEscenas.crearEscenaMenu();
                    admEscenas.setEscena(TipoEscena.ESCENA_MENU);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spritebtnIrAMenu);
        registerTouchArea(spritebtnIrAMenu);

        admMusica.cargarMusicaAcercaDe();

    }


    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        vibrador.cancel();
        admEscenas.liberarEscenaAcercaDe();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);

    }

    @Override
    public TipoEscena getTipoEscena() {

        return TipoEscena.ESCENA_ACERCA_DE;
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
        //admMusica.liberarMusica();
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
