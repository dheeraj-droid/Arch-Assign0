public class Project {
    public static void main(String[] args) {
        Clock clock = new Clock();
        int width = 5;
        Infiltrator infiltrator = new Infiltrator(width);
        float probability = 0.5f;
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


