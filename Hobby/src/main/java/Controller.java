import java.nio.file.*;

public class Controller {

    Robot robot = new Robot();

    public String readTxtFile(String filepath) {

        validateFilePath(filepath);

        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read file" + e.getMessage());
        }
        return content;
    }

    private void validateFilePath(String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            throw new IllegalArgumentException("File path is empty or null");
        }
        if (!filepath.endsWith(".txt")) {
            throw new IllegalArgumentException("File is not a text file");
        }
    }

    public void parseAndExecuteCommands(String input) {
        String[] lines = input.split("\r\n");
        for (String line : lines) {
            String[] params = line.split(",");
            if (params.length == 1) {
                handleCommand(params[0]);
            } else if (params[0].equals("PLACE")) {
                handlePlaceCommand(params);
            }
        }
    }

    private void handleCommand(String command) {
        if (robot.getIsPlaced()) {
            switch (command) {
                case "LEFT" -> robot.left();
                case "RIGHT" -> robot.right();
                case "MOVE" -> robot.move();
                case "REPORT" -> robot.report();
            }
        } else {
            System.out.println("Robot first move must to be placed");
        }
    }

    private void handlePlaceCommand(String[] params) {
        int x = Integer.parseInt(params[1]);
        int y = Integer.parseInt(params[2]);
        String direction = params[3];
        robot.place(x, y, direction);
    }

}
