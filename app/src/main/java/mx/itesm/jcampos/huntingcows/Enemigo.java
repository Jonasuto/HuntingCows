package mx.itesm.jcampos.huntingcows;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by rmroman on 23/09/15.
 */
public class Enemigo
{
    private Sprite spriteEnemigo;

    public Enemigo(Sprite sprite) {

        spriteEnemigo = sprite;
    }

    public Sprite getSpriteEnemigo() {
        return spriteEnemigo;
    }

    public void setSpriteEnemigo(Sprite spriteEnemigo) {

        this.spriteEnemigo = spriteEnemigo;
    }

    public void mover(int dx, int dy) {
        spriteEnemigo.setPosition( spriteEnemigo.getX()+dx, spriteEnemigo.getY()+dy );
    }
}
