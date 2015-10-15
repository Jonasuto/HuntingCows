package mx.itesm.rmroman.proyectobasegpo01;

import android.view.MotionEvent;

import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.input.touch.TouchEvent;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.LoopModifier;

/**
 * Created by Campos on 11/10/15.
 */
public class Jugador extends AnimatedSprite {

    private boolean saltoLargo = false;
    LoopEntityModifier loop;
    MoveByModifier movimiento;

    public Jugador(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
    }

    public void moverDerecha(){
        movimiento=new MoveByModifier(0.1f,	10,0);
        loop= new LoopEntityModifier(movimiento);
        this.registerEntityModifier(loop);
    }

    public void detener(){

        this.unregisterEntityModifier(loop);

    }

    public void moverIzquierda(){

        movimiento=new MoveByModifier(0.1f,	-10,0);
        loop= new LoopEntityModifier(movimiento);
        this.registerEntityModifier(loop);
    }

    public void moverSaltar(){

    }



}
