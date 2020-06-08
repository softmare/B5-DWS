import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
		setBounds(100, 100, 312, 178);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Font font = null;
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("DIGITALDREAM.ttf");
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.PLAIN, is));
		} catch (IOException |FontFormatException e) {
			e.printStackTrace();
		}

		System system = new System();
		system.initWatch();


		JButton button_a = new JButton("");
		button_a.setBackground(Color.GRAY);
		button_a.setBounds(0, 10, 15, 40);
		button_a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonA();
			}
		});
		contentPane.add(button_a);

		
		JButton button_c = new JButton("");
		button_c.setBackground(Color.GRAY);
		button_c.setBounds(0, 90, 15, 40);
		button_c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonC();
			}
		});
		contentPane.add(button_c);
		
		JButton button_d = new JButton("");
		button_d.setBackground(Color.GRAY);
		button_d.setBounds(277, 90, 15, 40);
		button_d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonD();
			}
		});
		contentPane.add(button_d);
		
		JButton button_b = new JButton("");
		button_b.setBackground(Color.GRAY);
		button_b.setBounds(277, 10, 15, 40);
		button_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				system.pressButtonB();
			}
		});
		contentPane.add(button_b);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(16, 10, 260, 120);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
				JPanel panel = new JPanel();
				panel.setBounds(3, 3, 254, 114);
				panel_1.add(panel);
				panel.setBackground(Color.WHITE);
				panel.setLayout(null);
				
				JPanel lower_panel = new JPanel();
				lower_panel.setBackground(Color.WHITE);
				lower_panel.setBounds(0, 46, 254, 58);
				panel.add(lower_panel);
				lower_panel.setLayout(null);
				
				JLabel lower_segment = new JLabel("00000000");
				lower_segment.setHorizontalAlignment(SwingConstants.CENTER);
				lower_segment.setFont(new Font("Digital dream", Font.PLAIN, 38));
				lower_segment.setForeground(Color.LIGHT_GRAY);
				lower_segment.setBounds(0, 0, 254, 58);
				lower_panel.add(lower_segment);
				
				JPanel upper_panel = new JPanel();
				upper_panel.setBounds(0, 10, 254, 26);
				panel.add(upper_panel);
				upper_panel.setBackground(Color.WHITE);
				upper_panel.setLayout(null);
				
				JLabel upper_segment = new JLabel("000000000000");
				upper_segment.setHorizontalAlignment(SwingConstants.CENTER);
				upper_segment.setFont(new Font("Digital dream", Font.PLAIN, 25));
				upper_segment.setBounds(0, 0, 254, 26);
				upper_panel.add(upper_segment);
				upper_segment.setForeground(Color.LIGHT_GRAY);

		Thread displayUpdater = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					upper_segment.setText(system.getSegmentContentUpper());
					lower_segment.setText(system.getSegmentContentLower());
					upper_segment.setForeground(system.getTextColor());
					lower_segment.setForeground(system.getTextColor());
					upper_panel.setBackground(system.getBackGroundColor());
					lower_panel.setBackground(system.getBackGroundColor());
					panel.setBackground(system.getBackGroundColor());
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
