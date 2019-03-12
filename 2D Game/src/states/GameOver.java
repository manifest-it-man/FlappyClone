package states;

import FlappyGame.Game;
import gfx.Assets;

import java.awt.*;

public class GameOver extends State {


    public GameOver(Game game) {
        super(game);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.gameOver, 100,100,null);



    }
}
