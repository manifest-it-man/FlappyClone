package gfx;

import java.awt.image.BufferedImage;

public class Assets {


    public static BufferedImage wallpaper, bird, birdTiltUp, toppipe, bottompipe, floor, justpipeforTop, justpipeforBottom, invader, gameOver;



    public static void init(){  //going to load everything in to our game
        wallpaper = ImageLoader.loadImage("/textures/flapBack1.jpg");


        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
        bird = sheet.crop(115,400,20,20);

        birdTiltUp = sheet.crop(115,400,20,20);




        bottompipe = sheet.crop(82,320,28,165);
        toppipe = sheet.crop(55,323,28,165);

        justpipeforTop = sheet.crop(55,323,28,130);
        justpipeforBottom = sheet.crop(82,340,28,130);

        floor = wallpaper.getSubimage(0,494,400,119);

        invader = sheet.crop(148,328,15,10);

        gameOver = sheet.crop(290, 50,60,30);






    }
}
