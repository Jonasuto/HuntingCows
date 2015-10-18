package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Random;

/**
 * Created by rmroman on 23/09/15.
 */


public class Enemigo extends Sprite {

    private int limiteDerecho;
    private boolean personajeSaltando = false;
    private int pasos=0;
    private boolean voltear=true;

    private Random entero = new Random();


    public Enemigo(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        limiteDerecho=entero.nextInt(3);
    }


    public void mover(){
        if(limiteDerecho==0){

            if(voltear==true){
                this.setRotation(-25);
                voltear=false;
            }

            this.setX(this.getX()-5);
            pasos--;
            if(pasos<-40){
                limiteDerecho=1;
                this.resetRotationCenter();
                voltear=true;
            }
        }
        else if(limiteDerecho==1){

            if(voltear==true){
                this.setRotation(25);
                voltear=false;
            }

            this.setX(this.getX()+5);
            pasos++;
            if(pasos>40){
                limiteDerecho=0;
                this.resetRotationCenter();
                voltear=true;
            }
        }

    }
}
