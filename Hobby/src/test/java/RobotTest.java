import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RobotTest {

    @Test
    public void placesRobotOnValidPosition() {
        Robot robot = new Robot();
        robot.place(2, 2, "SOUTH");
        assertEquals(2, robot.getX());
        assertEquals(2, robot.getY());
        assertEquals("SOUTH", robot.getDirection());
    }

    @Test
    public void doesNotPlaceRobotOnInvalidPosition() {
        Robot robot = new Robot();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> robot.place(-1, -1, "NORTH"));
        assertEquals("Invalid position", e.getMessage());
    }

    @Test
    public void turnsWestOnLeftCommandFromNorth() {
        Robot robot = new Robot();
        robot.place(1, 1, "NORTH");
        robot.left();
        assertEquals("WEST", robot.getDirection());
    }

    @Test
    public void turnsEastOnRightCommandFromNorth() {
        Robot robot = new Robot();
        robot.place(1, 1, "NORTH");
        robot.right();
        assertEquals("EAST", robot.getDirection());
    }

    @Test
    public void movesNorthWhenFacingNorth() {
        Robot robot = new Robot();
        robot.place(1, 1, "NORTH");
        robot.move();
        assertEquals(1, robot.getX());
        assertEquals(2, robot.getY());
    }

    @Test
    public void doesNotMoveOffTableTop() {
        Robot robot = new Robot();
        robot.place(0, 0, "SOUTH");
        robot.move();
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
    }

    // Test positive test cases from file

    @Test
    public void testPlaceMoveReport() {
        Controller controller = new Controller();
        String content = controller.readTxtFile("C:\\DevStuff\\Hobby\\src\\test\\resources\\Place_Move_Report_TestCase.txt");
        controller.parseAndExecuteCommands(content);
        assertEquals(0, controller.robot.getX());
        assertEquals(1, controller.robot.getY());
        assertEquals("NORTH", controller.robot.getDirection());
    }

    @Test
    public void testPlaceLeftReport() {
        Controller controller = new Controller();
        String content = controller.readTxtFile("C:\\DevStuff\\Hobby\\src\\test\\resources\\Place_Left_Report_TestCase.txt");
        controller.parseAndExecuteCommands(content);
        assertEquals(0, controller.robot.getX());
        assertEquals(0, controller.robot.getY());
        assertEquals("WEST", controller.robot.getDirection());
    }

    @Test
    public void testMultipleCommands() {
        Controller controller = new Controller();
        String content = controller.readTxtFile("C:\\DevStuff\\Hobby\\src\\test\\resources\\Place_move_move_move_left_move_report_TestCase.txt");
        controller.parseAndExecuteCommands(content);
        assertEquals(3, controller.robot.getX());
        assertEquals(3, controller.robot.getY());
        assertEquals("NORTH", controller.robot.getDirection());
    }

    @Test
    public void testMultipleCommandsBeforePlacing() {
        Controller controller = new Controller();
        String content = controller.readTxtFile("C:\\DevStuff\\Hobby\\src\\test\\resources\\Commands_Before_Placing_TestCase.txt");
        controller.parseAndExecuteCommands(content);
        assertEquals(3, controller.robot.getX());
        assertEquals(3, controller.robot.getY());
        assertEquals("NORTH", controller.robot.getDirection());
    }

    // Negative test cases from file

    @Test
    public void testEmptyFilePath() {
        Controller controller = new Controller();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> controller.readTxtFile(""));
        assertEquals("File path is empty or null", e.getMessage());
    }

    @Test
    public void testPdf() {
        Controller controller = new Controller();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> controller.readTxtFile("C:\\DevStuff\\Hobby\\src\\test\\resources\\Robot Simulator HiQ.pdf"));
        assertEquals("File is not a text file", e.getMessage());
    }

    @Test
    public void testToDontStartPlacing() {
        Controller controller = new Controller();
        String content = controller.readTxtFile("C:\\DevStuff\\Hobby\\src\\test\\resources\\No_Place_TestCase.txt");
        controller.parseAndExecuteCommands(content);
        assertEquals(false, controller.robot.getIsPlaced());
    }
}