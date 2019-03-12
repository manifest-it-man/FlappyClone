package entities;

import gfx.Assets;

import java.awt.*;

public class Pipe extends Entity {


    public float height, depth;

    public Pipe(float x, float y, float height, float depth) {
        super(x, y);
        this.height = height;
        this.depth= depth;
    }

    @Override
    public void tick() {

        x--;

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.toppipe, (int) x, (int) depth, null);
        g.drawImage(Assets.justpipeforTop, (int) x,  0, null);


        g.drawImage(Assets.bottompipe, (int) x, (int) height, null);
        g.drawImage(Assets.justpipeforBottom, (int) x,  364, null);





    }


    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }
}
