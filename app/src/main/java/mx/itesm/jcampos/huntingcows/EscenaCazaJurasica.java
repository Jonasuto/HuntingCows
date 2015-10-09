package mx.itesm.jcampos.huntingcows;

import android.content.Context;
import android.os.Vibrator;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Campos on 09/10/15.
 */

public class EscenaCazaJurasica extends mx.itesm.jcampos.huntingcows.EscenaBase
{
    // Regiones para imágenes
    private ITextureRegion regionFondo;
    private ITextureRegion spritePersonaje;
    private ITextureRegion regionPersonajeAnimado;



    // Sprite para el fondo
    //sprite
    private Sprite spriteFondo;
    private Vibrator vibrador;

    private boolean personajeSaltando = false;
    private boolean juegoCorriendo = true;

    private int escudo = 100;

    private CameraScene escenaPausa;    // La escena que se muestra al hacer pausa
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;


    @Override
    public void cargarRecursos() {

        regionFondo = cargarImagen("Imagenes/Niveles/fondoJuego.jpg");
        vibrador=(Vibrator)actividadJuego.getSystemService(Context.VIBRATOR_SERVICE);

        regionBtnPausa = cargarImagen("Imagenes/MenuInicio/musica.png");
        regionPausa = cargarImagen("Imagenes/logoTec.png");
        spritePersonaje =cargarImagen("Imagenes/player.png");
        regionPersonajeAnimado = cargarImagen("Imagenes/player.png");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
        admMusica.cargarMusica(1);

        Sprite btnPausa = new Sprite(regionBtnPausa.getWidth(), ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight(),
                regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnPausa);
        registerTouchArea(btnPausa);

        escenaPausa = new CameraScene(actividadJuego.camara);
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2,
                regionPausa);
        escenaPausa.attachChild(fondoPausa);
        escenaPausa.setBackgroundEnabled(false);

        spritePersonaje = new AnimatedSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2,
                regionPersonajeAnimado, actividadJuego.getVertexBufferObjectManager());
        spritePersonaje.animate(200);   // 200ms entre frames, 1000/200 fps
        attachChild(spritePersonaje);

    }


    private void pausarJuego() {
        if (juegoCorriendo) {
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        } else {
            clearChildScene();
            juegoCorriendo = true;
        }
    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.liberarEscenaCazaJurasica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_MENU);

    }

    @Override
    public mx.itesm.jcampos.huntingcows.TipoEscena getTipoEscena() {

        return mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_CAZA_JURASICA;
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


    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }
    }

    public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {

        if (pSceneTouchEvent.isActionDown() && !personajeSaltando) {
            personajeSaltando = true;
            // Animar sprite central
            JumpModifier salto = new JumpModifier(1, spritePersonaje.getX(), spritePersonaje.getX(),
                    spritePersonaje.getY(), spritePersonaje.getY(),-200);
            RotationModifier rotacion = new RotationModifier(1, 360, 0);
            ParallelEntityModifier paralelo = new ParallelEntityModifier(salto,rotacion)
            {
                @Override
                protected void onModifierFinished(IEntity pItem) {
                    personajeSaltando = false;
                    unregisterEntityModifier(this);
                    super.onModifierFinished(pItem);
                }
            };
            spritePersonaje.registerEntityModifier(paralelo);
        }

        if (pSceneTouchEvent.isActionDown()) {
            // El usuario toca la pantalla
            float x = pSceneTouchEvent.getX();
            float y = pSceneTouchEvent.getY();
            spritePersonaje.setPosition(x, y);
        }
        if (pSceneTouchEvent.isActionMove()) {
            // El usuario mueve el dedo sobre la pantalla
            float x = pSceneTouchEvent.getX();
            float y = pSceneTouchEvent.getY();
            spritePersonaje.setPosition(x, y);
        }
        if (pSceneTouchEvent.isActionUp()) {
            // El usuario deja de tocar la pantalla
        }

        return super.onSceneTouchEvent(pSceneTouchEvent);
    }



}
