package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Campos on 16/11/15.
 */
public class Teletransportadora extends Sprite {

    private boolean puedeTeletransportar;

    public Teletransportadora(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        puedeTeletransportar =true;
    }

    public boolean getpuedeTeletransportar(){
        return puedeTeletransportar;
    }

    public void setpuedeTeletransportar(boolean puedeTeletransportar){
        this.puedeTeletransportar = puedeTeletransportar;
    }
}
