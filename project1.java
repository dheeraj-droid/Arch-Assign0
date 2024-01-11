class Border {
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
 class Clock {
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

 class Infiltrator {
    int[] position = new int[2];
    int width;
    public Infiltrator(int width) {
        this.position[0] = -1;
        this.position[1] = -1;
        this.width=width;
    }

    public void move(Border border) {
        if(this.position[0] == this.width-1)
        {
            if(!border.isSensorActive(this.position[0], this.position[1]))
            {
                this.position[0]++;
            }
        }
        else if (this.position[1] != 0 && this.position[1] != 999 ) {
            if (!border.isSensorActive(this.position[0], this.position[1])) {
                int i = 1;
                for (int j = -1; j <= 1; j++) {
                    int newX = this.position[0] + i;
                    int newY = this.position[1] + j;

                    if (!border.isSensorActive(newX, newY)) {
                        this.position[0] = newX;
                        this.position[1] = newY;
                        return;
                    }
                }
            }

        } else if (this.position[1] == 0) {
            if (!border.isSensorActive(this.position[0], this.position[1])) {
                int i = 1;
                for (int j = 0; j <= 1; j++) {
                    int newX = this.position[0] + i;
                    int newY = this.position[1] + j;

                    if (!border.isSensorActive(newX, newY)) {
                        this.position[0] = newX;
                        this.position[1] = newY;
                        return;
                    }
                }
            }
        } else if (this.position[1] == 999) {
            if (!border.isSensorActive(this.position[0], this.position[1])) {
                int i = 1;
                for (int j = -1; j <= 0; j++) {
                    int newX = this.position[0] + i;
                    int newY = this.position[1] + j;

                    if (!border.isSensorActive(newX, newY)) {
                        this.position[0] = newX;
                        this.position[1] = newY;
                        return;
                    }
                }
            }
        }
        
    }
}

 class Sensor {
    float probability;
    int state;

    public Sensor(float probability) {
        this.probability = probability;
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

class project1 {
    public static void main(String[] args) {
        Clock clock = new Clock();
        int width = 50;
        float probability =0.5f;
        
        Infiltrator infiltrator = new Infiltrator(width);
        int height = 1000;
        Border border = new Border(width, probability, height);
        for (Sensor[] row : border.sensors) {
                    for (Sensor sensor : row) {
                        sensor.change();
                    }
                }
                for (int i = 0; i < height; i++) {
                    if (border.sensors[0][i].state == 0) {
                      
                        infiltrator.position[0] = 0;
                        infiltrator.position[1] = i;
                        break;  // Break once a valid position is found
                    }
                }
                clock.time+=10;
        while (infiltrator.position[0] < width) {
          if (clock.time % 10 == 0) {
                for (Sensor[] row : border.sensors) {
                    for (Sensor sensor : row) {
                        sensor.change();
                    }
                }
                infiltrator.move(border);
                
            }
             clock.time+=10;
        }
        System.out.printf("Infiltrator crossed the border! and time taken is %d secs",clock.getTime());
    }
}


