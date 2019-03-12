package entities;

import gfx.Assets;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Invader extends Entity  {


    private int speed;

    private final int intSpeed = 2;

    public Invader(float x, float y) {
        super(x, y);
        this.speed = intSpeed;
    }



    @Override
    public void tick() {
        y++;

        x = x - genRandom();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.invader, (int) x, (int) y, null);


    }


    private int genRandom(){
        int initInvaderMin = 1;
        int initInvaderMax = 3;
        int newX = ThreadLocalRandom.current().nextInt(initInvaderMin, initInvaderMax - 1);

        return -newX / 100;
    }
}
