import javax.swing.JOptionPane;

public class Administration extends Account {

	Administration(String info) {
		super(info);
	}
	
	Administration(String id, String pass, String name ,String type){
		super(id, pass, name, type);
	}
	
	public void showGUIOptions() { 	
		if (!AdminFrame.isActive) {
			AdminFrame adm = new AdminFrame(this);
			adm.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Window Already Open", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
