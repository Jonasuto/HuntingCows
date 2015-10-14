package mx.itesm.rmroman.proyectobasegpo01;

import android.view.MotionEvent;

import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.input.touch.TouchEvent;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Campos on 11/10/15.
 */
public class Jugador extends AnimatedSprite {

    public Jugador(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
    }

    public void moverDerecha(){
        MoveByModifier movimiento=new MoveByModifier(0.1f,	10,	0);
        this.registerEntityModifier(movimiento);
    }

    public void moverIzquierda(){
        MoveByModifier movimiento=new MoveByModifier(0.1f,	-10,	0);
        this.registerEntityModifier(movimiento);    }

    public void moverSaltar(){
        this.setX(this.getX()+10);
    }



}
