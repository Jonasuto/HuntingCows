package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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
    private boolean vaPaBajo =false;
    private boolean voltear=true;
    private int pasos=0;

    private int alturaBrinco=0;

    private Random regaloAleatorio=new Random();
    private int regalo=0;

    public Enemigo(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,int comportamiento) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        limiteDerecho=comportamiento;
        regalo=regaloAleatorio.nextInt(5);

    }


    public void setLimiteDerecho(int decision){

        this.limiteDerecho=decision;
    }

    public int getRegalo(){

        return regalo;
    }

    public void mover(float tiempo){
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

            if(vaPaBajo ==false){
                this.setY(this.getY()+10);
                alturaBrinco++;

                if(alturaBrinco>100){
                    vaPaBajo =true;
                }
            }
            else{
                this.setY(this.getY()-10);
                alturaBrinco--;

                if(alturaBrinco<=0){
                    vaPaBajo =false;
                }
            }
        }

        else{

        }

    }
}
