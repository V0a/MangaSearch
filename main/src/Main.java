import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                GUI	gui = new GUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}