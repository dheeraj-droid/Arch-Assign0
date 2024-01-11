public class Infiltrator {
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
