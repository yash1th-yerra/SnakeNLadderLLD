import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Cell[][] cells;
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }



    public Board(int boardSize, int numberOfSnakes, int numberOfLadders){
        setSize(boardSize);
        initializeBoard(boardSize);
        addSnakesLadders(numberOfSnakes,numberOfLadders);
    }

    public void initializeBoard(int boardSize){
        cells = new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                cells[i][j] = new Cell();
            }
        }
    }


    public void addSnakesLadders(int noOfSnakes,int noOfLadders){
        while(noOfSnakes>0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);

            if(snakeTail>snakeHead) continue;

            Jump snakeJump = new Jump();
            snakeJump.setStart(snakeHead);
            snakeJump.setEnd(snakeTail);

            Cell cell = getCell(snakeHead);
            cell.setJump(snakeJump);
            noOfSnakes--;

        }


        while(noOfLadders>0){
            int ladderBottom = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);
            int ladderTop = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);
            if(ladderBottom>ladderTop) continue;

            Jump ladderJump = new Jump();
            ladderJump.setStart(ladderBottom);
            ladderJump.setEnd(ladderTop);

            Cell cell = getCell(ladderBottom);
            cell.setJump(ladderJump);
            noOfLadders--;
        }
    }


    public Cell getCell(int currentPosition){
        int boardRow = currentPosition/ cells.length;
        int boardCol = currentPosition% cells.length;
        return cells[boardRow][boardCol];
    }

}
