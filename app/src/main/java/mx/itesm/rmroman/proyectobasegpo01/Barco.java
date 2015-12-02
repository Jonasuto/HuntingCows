package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Random;

/**
 * Created by Campos on 11/10/15.
 */
public class Barco extends AnimatedSprite {

    private int regalo=0;

    private boolean boss;

    private Random regaloAleatorio=new Random();

    public Barco(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
        regalo=regaloAleatorio.nextInt(5);
        boss=false;

    }

    public boolean getBoss(){
        return boss;
    }

    public void setBoss(boolean boss){
        this.boss=boss;
    }
    public int getRegalo(){

        return regalo;
    }



}
