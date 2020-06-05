import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class WatchGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WatchGUI frame = new WatchGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WatchGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 160);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(74, 10, 400, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel lower_panel = new JPanel();
		lower_panel.setBackground(Color.DARK_GRAY);
		lower_panel.setBounds(12, 46, 376, 44);
		panel.add(lower_panel);
		lower_panel.setLayout(null);
		
		JLabel lower_segment = new JLabel("00000000");
		lower_segment.setHorizontalAlignment(SwingConstants.CENTER);
		lower_segment.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lower_segment.setForeground(Color.LIGHT_GRAY);
		lower_segment.setBounds(0, 0, 376, 44);
		lower_panel.add(lower_segment);
		
		JPanel upper_panel = new JPanel();
		upper_panel.setBounds(12, 10, 376, 26);
		panel.add(upper_panel);
		upper_panel.setBackground(Color.DARK_GRAY);
		upper_panel.setLayout(null);
		
		JLabel upper_segment = new JLabel("000000000000");
		upper_segment.setHorizontalAlignment(SwingConstants.CENTER);
		upper_segment.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		upper_segment.setBounds(0, 0, 376, 26);
		upper_panel.add(upper_segment);
		upper_segment.setForeground(Color.LIGHT_GRAY);

		System system = new System();
		system.initWatch();


		JButton button_a = new JButton("");
		button_a.setBackground(Color.GRAY);
		button_a.setBounds(0, 10, 71, 23);
		button_a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonA();
			}
		});
		contentPane.add(button_a);

		
		JButton button_c = new JButton("");
		button_c.setBackground(Color.GRAY);
		button_c.setBounds(0, 88, 71, 23);
		button_c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonC();
			}
		});
		contentPane.add(button_c);
		
		JButton button_d = new JButton("");
		button_d.setBackground(Color.GRAY);
		button_d.setBounds(475, 88, 71, 23);
		button_d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonD();
			}
		});
		contentPane.add(button_d);
		
		JButton button_b = new JButton("");
		button_b.setBackground(Color.GRAY);
		button_b.setBounds(475, 10, 71, 23);
		button_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonB();
			}
		});
		contentPane.add(button_b);

		Thread displayUpdater = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					upper_segment.setText(system.getSegmentContentUpper());
					lower_segment.setText(system.getSegmentContentLower());
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		displayUpdater.start();
	}
}
