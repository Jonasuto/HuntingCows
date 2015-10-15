package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by rmroman on 23/09/15.
 */


public class Enemigo extends Sprite {

    private boolean limiteIzquierda=false;
    MoveByModifier movimiento;


    public Enemigo(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        mover();
    }


    public void mover() {
        movimiento=new MoveByModifier(0.1f,	10,0);
    }
}
