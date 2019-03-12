package states;

import FlappyGame.Game;
import entities.Bird;
import entities.Floor;
import entities.Invader;
import entities.Pipe;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameState extends State {

    private Bird bird;

    private State gameOver;

    private ArrayList<Pipe> pipes;
    private Pipe pipe1;
    private Pipe pipe2;
    private Pipe pipe3;

    private Floor floor1;
    private Floor floor2;
    private ArrayList<Floor> floors;


    private ArrayList<Invader> invaders;

    private Invader initInvader1;
    private Invader initInvader2;
    private Invader initInvader3;
    private Invader initInvader4;
    private Invader initInvader5;


    private final float birdinitx = 40;  //inital position of bird
    private final float birdinity = 50;
    private final int floorY = 494;
    private final int pipeHeight = 165;

    public int score = 0;
    public int pipeCount = 0;



    public GameState(Game game){
        super(game);

        gameOver = new GameOver(game);

        bird = new Bird(game, birdinitx, birdinity);

        pipe1 = new Pipe(420,0,331,0);
        pipe2 = new Pipe(620,0,331,0);
        pipe3 = new Pipe(820,0,331,0);

        pipes = new ArrayList<>();

        pipes.add(pipe1);
        pipes.add(pipe2);
        pipes.add(pipe3);


        floor1 = new Floor(0,494);
        floor2 = new Floor(400,494);
        floors = new ArrayList<Floor>();
        floors.add(floor1);
        floors.add(floor2);


        invaders = new ArrayList<Invader>();
    }


    private void updatePipeCount(){
        playScoreSound();
        pipeCount++;
        System.out.println("Pipe Count:  "  +pipeCount);

        if (pipeCount == 1){
            initInvaders();
        }
    }

    private void updateScore(){
         score++;
         System.out.println("Score: "+ score);}


    private void updateFloorPos(){
        for (Floor f: floors){
            if (f.getX() == -400){
                f.setX(400);
            }
        }

    }

    private void updatePipes(){
        for (Pipe p: pipes){
            if (p.getX() == -20){
                p.setX(600);
                editPipeSize(p);
            }
        }

    }

    private void editPipeSize(Pipe p){

        // depth  (affects TopPipes)  //
        int depthMin = 0; //top of screen
        int depthMax = 45;
        int newDepth = ThreadLocalRandom.current().nextInt(depthMin, depthMax + 1);


        // height (affects BottomPipes) //
        int heightMin = 250  ;
        int heightMax = 331;  // bottom of floor
        int newHeight = ThreadLocalRandom.current().nextInt(heightMin, heightMax + 1);

        p.setDepth(newDepth);
        p.setHeight(newHeight);

    }



    private void initInvaders(){
        initInvader1 = new Invader(genRandom(),-10);
        initInvader2 = new Invader(genRandom(),-310);
        initInvader3 = new Invader(genRandom(),-610);
        initInvader4 = new Invader(genRandom(),-910);
        initInvader5 = new Invader(genRandom(),-1210);

        invaders.add(initInvader1);
        invaders.add(initInvader2);
        invaders.add(initInvader3);
        invaders.add(initInvader4);
        invaders.add(initInvader5);

    }



    private void updateInvaders(){
        for (Invader i: invaders){
            if (i.getY() == 613){
                i.setY(-310);
                i.setX(genRandom());
            }
        }
    }

    private int genRandom(){
        int initInvaderMin = 100;
        int initInvaderMax = 400;
        int newX = ThreadLocalRandom.current().nextInt(initInvaderMin, initInvaderMax + 1);

        return newX;
    }


    private void checkCollisions(){
        for (Pipe p: pipes){
            if (intersects(bird.getX(), bird.getY(), p.getX(), p.getHeight(), p.getDepth())){ //need to change the P values here

                bird.setY(1000); //TODO: end round
                State.setCurrentState(gameOver);
            }

        }
    }

    private boolean intersects(float x1, float y1, float pipeX, float height, float depth){
        Rectangle birdRec = new Rectangle((int) x1, (int )y1, 5,5);

        Rectangle topRec = new Rectangle((int) pipeX, 0, 28, (int) (depth + pipeHeight));

        Rectangle bottomRec = new Rectangle((int) pipeX, (int )height, 28,300);


        if ((birdRec.intersects(topRec) || birdRec.intersects(bottomRec) ||  ((bird.getY() + 18) > floorY ) )){
            return true;
        }
            else return false;
        }









    @Override
    public void tick() {

        // Bird
        bird.tick();

        // Pipes
        for (Pipe p: pipes){
            p.tick();
        }
        updatePipes();

        //Floor
        for (Floor f: floors){
            f.tick();
        }
        updateFloorPos();
        checkCollisions();

        for (Pipe p: pipes){
            if (bird.getX() == p.getX()){
                updatePipeCount();
            }
        }

        //Invaders//
        for (Invader i: invaders){
            i.tick();
        }

        updateInvaders();


    }



    @Override
    public void render(Graphics g) {

        // Bird //
        bird.render(g);

        // Floor //
        for (Floor f: floors){
            f.render(g);

        }

        // Pipes //
        for (Pipe p: pipes){
            p.render(g);
        }


        // Invaders //
        for (Invader i: invaders){
            i.render(g);
        }



        // Scoring //
        g.drawString("Score: "+Integer.toString(score), 12,545);

    }


    /// SOUNDS //

    public void playScoreSound(){

        try {
            URL score = new File("/Users/Michael/Desktop/Business/CODING/myprojects/2D Game/res/sounds/score.wav").toURI().toURL();
            java.applet.AudioClip clip = java.applet.Applet.newAudioClip(score);
            clip.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }




}
