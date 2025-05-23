import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int min=1;
    private int max=6;
    private int diceCount;

    public Dice(int diceCount){
        this.diceCount = diceCount;
    }

    public int rollDice(){
        int totalSum=0;
        int diceUsed=0;
        while(diceUsed<diceCount){
            totalSum+= ThreadLocalRandom.current().nextInt(min,max+1);
            diceUsed++;
        }

        return totalSum;
    }


}
