public class Robot {

    private int x;
    private int y;
    private String direction;

    private boolean isPlaced = false;
    private final TableTop tableTop = new TableTop();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setIsPlaced(Boolean isPlaced) {
        this.isPlaced = isPlaced;
    }

    public boolean getIsPlaced() {
        return isPlaced;
    }

    public void place(int x, int y, String direction) {
        if (tableTop.isValidPosition(x, y)) {
            setX(x);
            setY(y);
            setDirection(direction);
            setIsPlaced(true);
        } else {
            throw new IllegalArgumentException("Invalid position");
        }
    }

    public void left() {
        switch (getDirection()) {
            case "NORTH" -> this.setDirection("WEST");
            case "EAST" -> this.setDirection("NORTH");
            case "SOUTH" -> this.setDirection("EAST");
            case "WEST" -> this.setDirection("SOUTH");
        }
    }

    public void right() {
        switch (getDirection()) {
            case "NORTH" -> this.setDirection("EAST");
            case "EAST" -> this.setDirection("SOUTH");
            case "SOUTH" -> this.setDirection("WEST");
            case "WEST" -> this.setDirection("NORTH");
        }
    }

    public void move() {
        switch (getDirection()) {
            case "NORTH":
                if (tableTop.isValidPosition(this.getX(), this.getY() + 1)) {
                    this.setX(x);
                    this.setY(y + 1);
                }
                break;
            case "EAST":
                if (tableTop.isValidPosition(this.getX() + 1, this.getY())) {
                    this.setX(x + 1);
                    this.setY(y);
                }
                break;
            case "SOUTH":
                if (tableTop.isValidPosition(this.getX(), this.getY() - 1)) {
                    this.setX(x);
                    this.setY(y - 1);
                }
                break;
            case "WEST":
                if (tableTop.isValidPosition(this.getX() - 1, this.getY())) {
                    this.setX(x - 1);
                    this.setY(y);
                }
                break;
        }

    }

    public void report() {
        System.out.println(this.getX() + "," + this.getY() + "," + this.direction);
    }

}