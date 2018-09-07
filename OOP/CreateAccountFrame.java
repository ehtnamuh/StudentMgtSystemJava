import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;

public class CreateAccountFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textUserName;
	private JTextField textPassword;
	private JLabel lblType;
	private JComboBox<String> comboBoxType;
	private JLabel lblUserId;
	private JFrame pointerToThyself;
	protected static boolean isActive = false;	


	public CreateAccountFrame() {
		setTitle("Create New Account");
		pointerToThyself = this;
		isActive = true;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// on close operation
		addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				isActive = false;
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				if (JOptionPane.showConfirmDialog(pointerToThyself,"Confirm Exit?","Logout",
						JOptionPane.YES_NO_OPTION) == 0) {
					isActive = false;
					pointerToThyself.dispose();
				}
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(450, 300);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserId = new JLabel("User Id:   " + (Account.numAccounts + 1));
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserId.setBounds(73, 40, 244, 14);
		contentPane.add(lblUserId);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserName.setBounds(51, 65, 68, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(59, 91, 60, 14);
		contentPane.add(lblPassword);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textPassword.getText().equals("") || textUserName.getText().equals("")
						|| comboBoxType.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(pointerToThyself, "Error! Check Inputs!" , "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (JOptionPane.showConfirmDialog(pointerToThyself, "Confirm Creation of Account!" , 
						"Confirmation Request", JOptionPane.YES_NO_OPTION) != 0)  {
					return;
				}
				/*
				for (Account acc: AccountManager.accs) {
					if (textUserName.getText().equals(acc.getName())) {
						JOptionPane.showMessageDialog(pointerToThyself, "Error! Name Taken !" , "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}*/  // People may have duplicate names in real life
				String id = "" + (++Account.numAccounts);
				String userName = textUserName.getText();
				String pass = textPassword.getText();
				String type = (String) comboBoxType.getSelectedItem();
				if (type.equals(AccountManager.types[0])){
					AccountManager.accs.add(new Administration(id, pass, userName , type));
					AccountManager.writeFile();
					JOptionPane.showMessageDialog(pointerToThyself, "Account Created" , "Success!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if(type.equals(AccountManager.types[1])) {
					AccountManager.accs.add(new Student(id, pass, userName , 0, type));
					AccountManager.writeFile();
					JOptionPane.showMessageDialog(pointerToThyself, "Account Created" , "Success!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (type.equals(AccountManager.types[2])){
					AccountManager.accs.add(new Teacher(id, pass, userName, type));
					AccountManager.writeFile();
					JOptionPane.showMessageDialog(pointerToThyself, "Account Created" , "Success!",
							JOptionPane.INFORMATION_MESSAGE);
				}
				textPassword.setText("");
				textUserName.setText("");
				lblUserId.setText("User Id:   " + (Account.numAccounts + 1));
			}
		});
		btnCreateAccount.setBounds(129, 170, 135, 23);
		contentPane.add(btnCreateAccount);
		
		textUserName = new JTextField();
		textUserName.setBounds(129, 63, 188, 20);
		contentPane.add(textUserName);
		textUserName.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(129, 88, 188, 20);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblType.setBounds(86, 121, 33, 14);
		contentPane.add(lblType);
		
		comboBoxType = new JComboBox<String>();
		comboBoxType.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin", "Student", "Teacher"}));
		comboBoxType.setBounds(129, 119, 135, 20);
		contentPane.add(comboBoxType);
		
		
	}

}
