package assignment2;

public class World {
    private Caterpillar caterpillar;
    private Position foodPos;
    private Region region;
    private ActionQueue actionQueue;
    private TargetQueue foodPosQueue;
    private GameState gameState;

    public World(TargetQueue foodPosQueue, ActionQueue actionQueue){
        this.foodPosQueue = foodPosQueue;
        this.actionQueue = actionQueue;
        this.region = new Region(0,0,15,15);
        this.caterpillar = new Caterpillar();
        // dequeue food positions from foodPosQueue
        this.foodPos = foodPosQueue.dequeue();
        this.gameState = GameState.MOVE;
    }

    public void step(){
        if (actionQueue.isEmpty()){
            this.gameState = GameState.NO_MORE_ACTION;
            return;
        }
        Direction headingD = this.actionQueue.dequeue();

        if (!(gameState != GameState.MOVE || gameState != GameState.EAT)){
            return;
        }

        Position headPos = this.caterpillar.getHead();
        Position newPos = new Position(headPos);

        if (headingD == Direction.NORTH){
            newPos.moveNorth();
            stepHelper(newPos);
        }
        else if(headingD == Direction.SOUTH){
            newPos.moveSouth();
            stepHelper(newPos);
        }
        else if(headingD == Direction.EAST){
            newPos.moveEast();
            stepHelper(newPos);
        }
        else if(headingD == Direction.WEST){
            newPos.moveWest();
            stepHelper(newPos);
        }
    }

    private void stepHelper(Position newPos){

        if (!(this.region.contains(newPos))){
            this.gameState = GameState.WALL_COLLISION;
        }
        else if (caterpillar.selfCollision(newPos)){
            this.gameState = GameState.SELF_COLLISION;
        }
        else if (newPos.equals(foodPos)){
            this.caterpillar.eat(newPos);

            if (foodPosQueue.isEmpty()){
                this.gameState = GameState.DONE;
            }
            else {
                this.foodPos = this.foodPosQueue.dequeue();
                this.gameState = GameState.EAT;
            }
        }
        else {
            this.caterpillar.move(newPos);
            this.gameState = GameState.MOVE; // not sure if this should gp outside the conditional
        }
    }

    public GameState getState(){
        return this.gameState;
    }

    public Caterpillar getCaterpillar() {
        return this.caterpillar;
    }

    public Position getFood(){
        return this.foodPos;
    }

    public boolean isRunning(){
        return this.getState() == GameState.MOVE || this.getState() == GameState.EAT;
    }

    /*public static void main(String[] args) {
        TargetQueue targetQueue = new TargetQueue();
        ActionQueue actionQueue = new ActionQueue();

        String food = "(7,10)";
        String direction = "2[N]";

        targetQueue.addTargets(food);
        actionQueue.loadFromEncodedString(direction);

        World world = new World(targetQueue, actionQueue);

        world.step();  // move 1 step N from (7,7) to (7,8)

        System.out.println(world.gameState);
        System.out.println(world.caterpillar.getSize());
        System.out.println(world.caterpillar.getHead());

        *//*assertEquals(GameState.MOVE, world.getState());
        assertEquals(1, world.getCaterpillar().getSize());
        assertEquals(new Position(7, 6), world.getCaterpillar().getHead());*//*
    }*/
}
