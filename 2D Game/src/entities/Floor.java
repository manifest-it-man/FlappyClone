package entities;

import gfx.Assets;

import java.awt.*;

public class Floor extends Entity {

    public Floor(float x, float y) {
        super(x, y);
    }

    @Override
    public void tick() {
        x --;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.floor, (int) x, (int) y, null);

    }
}
