package mx.itesm.rmroman.huntingcows;

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
    private boolean voltear=true;
    private int pasos=0;
    private boolean brinco;
    private boolean rotacion;
    private int limitePasos;
    private boolean rotaCuandoCamina;
    private boolean puedeDisparar;
    private boolean noPuedeSerDestruido;
    private int disparara;

    private boolean brincando=false;

    private boolean persigue;

    private Random regaloAleatorio=new Random();
    private int regalo=0;

    public Enemigo(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,int comportamiento,boolean brinco,boolean rotacion, int limitePasos,boolean rotaCuandoCamina,boolean puedeDisparar,boolean noPuedeSerDestruido) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        limiteDerecho=comportamiento;
        regalo=regaloAleatorio.nextInt(5);
        this.brinco=brinco;
        this.rotacion=rotacion;
        this.limitePasos=limitePasos;
        this.rotaCuandoCamina=rotaCuandoCamina;
        this.puedeDisparar=puedeDisparar;
        this.noPuedeSerDestruido=noPuedeSerDestruido;
        persigue=false;
    }

    public boolean getNoPuedeSerDestruido(){
        return noPuedeSerDestruido;
    }

    public boolean getRotacion(){
        return rotacion;
    }

    public boolean getPersigue(){
        return persigue;
    }

    public void setPersigue(boolean persigue){
        this.persigue=persigue;
    }

    public boolean getPuedeDisparar(){
        return puedeDisparar;
    }

    public int getDisparara(){
        disparara= regaloAleatorio.nextInt(70);
        return disparara;
    }

    public void setLimiteDerecho(int decision){
        this.limiteDerecho=decision;
    }

    public int getRegalo(){

        return regalo;
    }

    public boolean getBrinco(){
        return brinco;
    }

    public boolean getBrincando(){
        return brincando;
    }

    public void setBrincando(boolean brincando){
        this.brincando=brincando;
    }

    public void mover(float tiempo){
        if(limiteDerecho==0){

            if(voltear==true){
                if(rotaCuandoCamina==true) {
                    this.setRotation(-20);
                }
                voltear=false;
            }

            this.setX(this.getX()-6);

            pasos--;
            if(pasos<-limitePasos){
                limiteDerecho=1;
                this.resetRotationCenter();
                voltear=true;
            }
        }
        else if(limiteDerecho==1){

            if(voltear==true){
                if(rotaCuandoCamina==true) {
                    this.setRotation(20);
                }
                voltear=false;
            }

            this.setX(this.getX()+6);
            pasos++;
            if(pasos>limitePasos){
                limiteDerecho=0;
                this.resetRotationCenter();
                voltear=true;
            }
        }
        else if(limiteDerecho==19){
            this.setY(this.getY()-1);
            pasos--;
            if(pasos<-10){
                limiteDerecho=20;
            }
        }
        else if(limiteDerecho==20){
            this.setY(this.getY()+1);
            pasos++;
            if(pasos>10){
                limiteDerecho=19;
            }
        }

        else{

        }

    }
}
