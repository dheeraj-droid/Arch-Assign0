public class Sensor {
    float probability;
    int state;

    public Sensor(float prob) {
        this.probability = prob;
        state = 1;
    }

    public void change() {
        if ( Math.random() < this.probability) {
            state = 0;
        }
        else{
            state=1;
        }
    }
}
