public class Clock {
    int time;

    public Clock() {
        this.time = 0;
    }

    public void increase() {
        this.time++;
    }

    public int getTime() {
        return this.time;
    }
}
