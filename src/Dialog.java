import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;


public class Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Dialog dialog = new Dialog(200, 200, "123456");
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public Dialog(int x, int y, String string) {
		setBounds(x, y, 185, 69);
		setBackground(new Color(216, 225, 106));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(216, 225, 106));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		new Thread(new RemoveThread()).start();
		
		JLabel label = new JLabel(string);
		label.setBackground(new Color(255, 165, 0));
		label.setForeground(new Color(255, 0, 255));
		label.setFont(new Font("ººÒÇÀÖß÷Ìå¼ò", Font.PLAIN, 17));
		label.setBounds(0, 0, 175, 37);
		contentPanel.add(label);
	}
	
	public Dialog(int x, int y, String string, int t) {
		this(x, y, string);
		this.t = t;
	}
	
	int t = 1000;
	class RemoveThread implements Runnable {
		public void run() {
			try {
				Thread.sleep(t);
				Dialog.this.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}












