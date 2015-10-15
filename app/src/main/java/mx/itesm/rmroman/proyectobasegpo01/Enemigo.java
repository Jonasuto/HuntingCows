package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by rmroman on 23/09/15.
 */
public class Enemigo extends Sprite {


    LoopEntityModifier loop;
    MoveByModifier movimiento;

    public Enemigo(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
    }


    public void moverDerecha(){
        movimiento=new MoveByModifier(0.1f,	20,0);
        loop= new LoopEntityModifier(movimiento);
        this.registerEntityModifier(loop);
    }

    public void detener(){

        this.unregisterEntityModifier(loop);

    }

    public void moverIzquierda(){

        movimiento=new MoveByModifier(0.1f,	-20,0);
        loop= new LoopEntityModifier(movimiento);
        this.registerEntityModifier(loop);
    }

    public void moverSaltar(){

    }



}
