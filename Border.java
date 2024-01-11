public class Border {
    int width;
    float probability;
    Sensor sensors[][];

    public Border(int width, float prob, int height) {
        this.width = width;
        this.probability = prob;
        sensors = new Sensor[this.width][height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < height; j++) {
                sensors[i][j] = new Sensor(this.probability);
            }
        }
    }

    public boolean isSensorActive(int x, int y) {
        return  sensors[x][y].state == 1;
    }
}
