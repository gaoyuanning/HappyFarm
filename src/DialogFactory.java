import javax.swing.JDialog;


public class DialogFactory {
	public static void createDialog(int x, int y, String s) {
		try {
			Dialog dialog = new Dialog(x + 150, y - 50, s, 2000);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	public static void createDialog(int x, int y, String s, int t) {
		try {
			Dialog dialog = new Dialog(x + 150, y - 50, s, 2000);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
}
