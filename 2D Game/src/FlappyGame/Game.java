package FlappyGame;

import gfx.Assets;
import gfx.Display;
import input.KeyManager;
import states.GameOver;
import states.GameState;
import states.MenuState;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {


    private Display display;
    public int width, height;
    public String title;
    private boolean running = false;
    private Thread thread;  // like a mini program within our larger program


    private BufferStrategy bs;  // tells the computer how to ddraw things to the screen
    private Graphics g;
    private final int pipeheight = 163;

    private double birdspeed = .5 ;

    //States
    private State gameState;
    private State menuState;
    private State gameOver;

    //Input
    private KeyManager keyManager;


    //Entities

    //Bird



    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();


    }

    private void init(){ //initializes our game
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init(); //gets images and stuff
        gameState = new GameState(this);
        menuState = new MenuState(this);
        gameOver = new GameOver(this);
        State.setCurrentState(gameState);

    }

    private void tick(){
        keyManager.tick();

        if (State.getCurrentState() != null)
            State.getCurrentState().tick();

    }



    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        //Clear Screen


        //Draw Here


        if (State.getCurrentState() != null)
            State.getCurrentState().render(g);


        g.drawImage(Assets.wallpaper,0,0,null);
        g.drawImage(Assets.floor,400,494,null); //TODO
        State.getCurrentState().render(g); //TODO




        //End Drawing
        bs.show();
        g.dispose();

    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps ; //gives us maximum amount of time we should run our tick and render methods
        double delta = 0;
        long now;
        long lasttime = System.nanoTime();
        long timer = 0;
        int ticks = 0;


        while (running){
            now = System.nanoTime();
            delta += (now - lasttime) / timePerTick;
            timer += now - lasttime;
            lasttime = now;


            if(delta >= 1){
                tick();
                render();  //ensures the game maintains 60fps
                ticks ++;
                delta --;

            }

            if (timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }


        }

        stop();


    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public synchronized void start(){
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start(); //calls the run method above
    }

    public synchronized void stop(){
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
