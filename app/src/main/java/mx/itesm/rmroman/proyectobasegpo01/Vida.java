package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by rmroman on 23/09/15.
 */


public class Vida extends Sprite {

    public Vida(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
    }
}
