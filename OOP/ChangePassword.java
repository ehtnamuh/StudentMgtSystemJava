import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JTextField txtOldPassword;
	private JTextField textField;
	private Account caller;
	protected static boolean isActive = false;
	private JFrame pointerToThyself;
	
	public ChangePassword(Account acc) {
		setTitle("Change Password");
		isActive = true;
		JFrame pointerToThyself = this;
		caller = acc;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
				isActive = false;
				pointerToThyself.dispose();
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
		this.setSize(400, 200);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setForeground(Color.WHITE);
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOldPassword.setBounds(67, 37, 85, 14);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(Color.WHITE);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewPassword.setBounds(63, 64, 89, 14);
		contentPane.add(lblNewPassword);
		
		txtOldPassword = new JTextField();
		txtOldPassword.setBounds(162, 34, 141, 20);
		contentPane.add(txtOldPassword);
		txtOldPassword.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(162, 62, 141, 20);
		contentPane.add(textField);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(pointerToThyself, "Password cannot be empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} 
				if (textField.getText().equals(txtOldPassword.getText())) {
					JOptionPane.showMessageDialog(pointerToThyself, "Cannot set to same password", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "Please confirm your choice", "Confirmation",
						JOptionPane.YES_NO_OPTION) != 0) {
					return;
				}
				for (Account acc1: AccountManager.accs) {
					if (acc1.login(caller.id, txtOldPassword.getText(), caller.type)) {
						acc1.password = textField.getText();
						AccountManager.writeFile();
						JOptionPane.showMessageDialog(pointerToThyself, "Password Changed", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}	
				}
				JOptionPane.showMessageDialog(pointerToThyself, "Wrong Password", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		btnConfirm.setBackground(new Color(0, 139, 139));
		btnConfirm.setBounds(162, 93, 141, 23);
		contentPane.add(btnConfirm);
	}
}
