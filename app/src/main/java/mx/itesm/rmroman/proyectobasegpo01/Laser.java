package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Random;

/**
 * Created by rmroman on 23/09/15.
 */


public class Laser extends Sprite {

    private boolean volteandoDerecha;
    private boolean disparoOvni;
    private Random aleatorio;
    private int movimiento;

    private boolean yaDespegue;


    public Laser(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,boolean volteandoDerecha,boolean disparoOvni) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        this.volteandoDerecha=volteandoDerecha;
        this.disparoOvni=disparoOvni;
        aleatorio= new Random();
        movimiento=aleatorio.nextInt(3);
        yaDespegue=false;
    }

    public void mover(){

        if(disparoOvni==false) {
            if (volteandoDerecha == true) {
                this.setX(this.getX() + 30);
            } else {
                this.setRotation(-180);
                this.setX(this.getX() - 30);
            }
        }

        else{
            if(movimiento==0){
                this.setRotation(-270);
                this.setY(this.getY() - 20);
            }
            else if(movimiento==1){
                this.setRotation(-225);
                this.setY(this.getY() - 20);
                this.setX(this.getX() - 20);
            }
            else{
                this.setRotation(45);
                this.setY(this.getY() - 20);
                this.setX(this.getX() + 20);
            }
        }
    }

    public boolean getYaDespegue(){
        return this.yaDespegue;
    }

    public void setYaDespegue(boolean yaDespegue){
        this.yaDespegue=yaDespegue;
    }
}
