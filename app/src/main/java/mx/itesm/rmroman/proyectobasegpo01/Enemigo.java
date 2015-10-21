package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.SequenceModifier;

import java.util.Random;

/**
 * Created by rmroman on 23/09/15.
 */


public class Enemigo extends Sprite {


    /*

    comportamiento

    0= empieza caminanado a la izquierda
    1= a la derecha
    2= se queda parado brincando
    3= brinca con giro

     */
    private int limiteDerecho;
    private boolean personajeSaltando = false;
    private int pasos=0;
    private boolean voltear=true;
    private Random regaloAleatorio;
    private int regalo=0;

    public Enemigo(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,int comportamiento) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        limiteDerecho=comportamiento;
        regalo=0;

    }


    public void setLimiteDerecho(int decision){

        this.limiteDerecho=decision;
    }

    public int getRegalo(){

        return 0;
    }

    public void mover(){
        if(limiteDerecho==0){

            if(voltear==true){
                this.setRotation(-25);
                voltear=false;
            }

            this.setX(this.getX()-3);

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

            this.setX(this.getX()+3);
            pasos++;
            if(pasos>40){
                limiteDerecho=0;
                this.resetRotationCenter();
                voltear=true;
            }
        }
        else if(limiteDerecho==2){

        }

        else{

        }

    }
}
