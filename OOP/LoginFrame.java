import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textUserId;
	private JPasswordField passwordField;
	JComboBox<String> comboBoxType;

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 250);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUserId = new JTextField();
		textUserId.setText("1720049"); //for my convenience
		textUserId.setBounds(140, 30, 142, 20);
		contentPane.add(textUserId);
		textUserId.setColumns(10);
		
		JLabel lblUserId = new JLabel("UserId");
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setBounds(84, 30, 46, 14);
		contentPane.add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(65, 64, 74, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 61, 142, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Account acc: AccountManager.accs) {
					String id = textUserId.getText();
					String pass = passwordField.getText();
					String type = (String) comboBoxType.getSelectedItem();
					System.out.println(acc.login(id, pass, type));
					if (acc.login(id, pass, type)) {
						JOptionPane.showMessageDialog(null, "Login Successful!", "Login", 
								JOptionPane.INFORMATION_MESSAGE);
						AccountManager.writeFile();
						acc.showGUIOptions();
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "Login Failed!","Login", 
						JOptionPane.WARNING_MESSAGE);
			}
		});
		btnLogin.setBounds(140, 140, 142, 23);
		contentPane.add(btnLogin);
		
		comboBoxType = new JComboBox<String>();
		comboBoxType.setBounds(140, 92, 142, 20);
		for (String s: AccountManager.types) {			
			comboBoxType.addItem(s);
		}	
		contentPane.add(comboBoxType);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setBounds(84, 95, 46, 14);
		contentPane.add(lblType);
	}
}
