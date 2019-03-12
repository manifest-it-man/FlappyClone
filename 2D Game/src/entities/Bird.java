package entities;

import gfx.Assets;
import FlappyGame.Game;

import java.awt.*;

public class Bird extends Entity {

    protected int flapvelocity = 5;
    private Game game;


    public Bird(Game game, float x, float y) {
        super(x, y);
        this.game = game;
    }


    @Override
    public void tick(){

        y = (float) (y + 2);

        if (game.getKeyManager().up)
            y = (float) (y - flapvelocity);

        if (game.getKeyManager().up2)
            y = (float) (y - flapvelocity);





    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bird, (int) x, (int) y, null);

    }




    private void tiltUp(Graphics g){
        g.drawImage(Assets.bird, (int) x, (int) y, null);





    }
}
