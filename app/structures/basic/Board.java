package structures.basic;
import akka.actor.ActorRef;
import structures.basic.Tile;
import commands.BasicCommands;
import structures.GameState;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;



public class Board {

    private final ActorRef out;
    public static final int WIDTH = 9;
    public static final int HEIGHT = 5;

    private Tile[][] tiles = new Tile[WIDTH][HEIGHT];

    public Board(ActorRef out){
        this.out = out;
        this.initializeBoard();
    }

    private void initializeBoard(){

        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){

                //create Tile by config file
                Tile tile = BasicObjectBuilders.loadTile(x, y);

                //set grid
                tiles[x][y] = tile;

                BasicCommands.drawTile(out, tile, 0);

            }
        }
    }


    //Getter method to return tiles objects of a board
    public Tile[][] getTiles() {
        return this.tiles;
    }

    //this method will take in an x and y parameter and return the tile object at that position.
    public Tile returnTile(int x, int y) {
        return tiles[x][y];
    }

    public void addUnitToBoard(int x, int y, Unit unit) {
        tiles[x][y].setUnitToTile(unit);

    }






}
