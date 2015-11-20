package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Campos on 17/11/15.
 */
public class Arista extends Sprite {

    private int tocado;

    public Arista(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        this.tocado=0;
    }

    public void setTocado(int tocado){
        this.tocado=tocado;
    }

    public int getTocado(){
        return this.tocado;
    }
}
