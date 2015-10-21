package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by rmroman on 23/09/15.
 */


public class Laser extends Sprite {

    MoveByModifier movimiento;

    private boolean volteandoDerecha;


    public Laser(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,boolean volteandoDerecha) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        this.volteandoDerecha=volteandoDerecha;
    }

    public void mover(){
        if(volteandoDerecha==true){
            this.setX(this.getX()+20);
        }
        else{
            this.setRotation(-180);
            this.setX(this.getX()-20);
        }
    }
}
