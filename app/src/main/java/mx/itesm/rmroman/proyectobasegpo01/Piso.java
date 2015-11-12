package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Random;

/**
 * Created by rmroman on 23/09/15.
 */


public class Piso extends Sprite {


    /*

    comportamiento

    0= empieza caminanado a la izquierda
    1= a la derecha
    2= se queda parado brincando
    3= brinca con giro

     */


    private int limiteDerecho;
    private int pasos;
    private int limitePasos;

    public Piso(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,int comportamiento,int limitePasos) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        limiteDerecho=comportamiento;
        this.limitePasos=limitePasos;
        pasos=0;
    }

    public boolean getMover(){

        if(limiteDerecho==0 || limiteDerecho==1 || limiteDerecho==2 || limiteDerecho==3){
            return true;
        }
        else{
            return false;
        }
    }

    public void mover(float tiempo){
        if(limiteDerecho==0){
            this.setX(this.getX()-6);
            pasos--;
            if(pasos<-limitePasos){
                limiteDerecho=1;
            }
        }
        else if(limiteDerecho==1){
            this.setX(this.getX()+6);
            pasos++;
            if(pasos>limitePasos){
                limiteDerecho=0;
            }
        }
        else if(limiteDerecho==2) {
        }
        else if(limiteDerecho==3) {
        }

        else{}
    }
}
