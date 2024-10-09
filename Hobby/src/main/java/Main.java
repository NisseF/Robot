public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();

        try {
            String content = controller.readTxtFile("C:\\DevStuff\\Hobby2\\src\\test\\resources\\Place_move_move_move_left_move_report_TestCase.txt");
            controller.parseAndExecuteCommands(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}