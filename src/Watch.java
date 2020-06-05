import javax.swing.*;

public class Watch extends JFrame{
    private JPanel main_panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel upper_segment;
    private JLabel lower_segment;
    private JPanel right_pane;
    private JPanel left_pane;

    public Watch(){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Watch");
        frame.setContentPane(new Watch().main_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
