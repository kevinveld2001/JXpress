package example;

public class Counter {
    private int counter = 0;

    public void add() {
        counter++;
    }

    public void add(int num) {
        counter += num;
    }

    public int getCounter() {
        return counter;
    }
}
