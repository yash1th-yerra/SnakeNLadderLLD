import java.util.Deque;
import java.util.LinkedList;

public class Game {
    private Board board;
    private Dice dice;
    private Deque<Player> players = new LinkedList<>();
    private Player winner;

    public Game(){
        initializeGame();
    }

    private void initializeGame(){
        board = new Board(10,5,4);
        dice = new Dice(1);
        winner = null;
        addPlayers();

    }

    private void addPlayers(){
        Player player1 = new Player("p1",0);
        Player player2 = new Player("p2",0);
        players.add(player1);
        players.add(player2);


    }

    private Player findPlayerTurn(){
        Player playerTurn = players.removeFirst();
        players.addLast(playerTurn);
        return playerTurn;
    }


    public void startGame(){
        while(winner==null){
            Player playerTurn = findPlayerTurn();
            System.out.println("player turn is:" + playerTurn.getId() + " current position is: " + playerTurn.getCurrentPosition());


            int diceNumber = dice.rollDice();


            int playerPosition = playerTurn.getCurrentPosition() + diceNumber;
            if (playerPosition> board.getSize()*board.getSize()-1){
                System.out.println("Invalid move skipping turn ");
                playerPosition=playerTurn.getCurrentPosition();
                continue;
            }
            playerPosition = jumpCheck(playerPosition);
            playerTurn.setCurrentPosition(playerPosition);

            System.out.println("player turn is:" + playerTurn.getId()+ " new Position is: " + playerPosition);
            if(playerPosition==board.getSize()*board.getSize()-1){
                winner=playerTurn;
                System.out.println("WINNER IS : "+winner.getId());
            }

        }
    }

    private int jumpCheck(int playerPosition){
        Cell cell = board.getCell(playerPosition);
        if(cell.getJump()!=null && cell.getJump().getStart()==playerPosition){
            String jumpBy =(cell.getJump().getStart()<cell.getJump().getEnd())?"Ladder":"Snake";
            System.out.println("Jump done by : "+jumpBy);
            return cell.getJump().getEnd();
        }
        return playerPosition;
    }
}
