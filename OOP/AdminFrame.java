import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JTextArea txtrDisplaySelectedElement; 
	String[] columnHeaders = null;
	JRadioButton rdbtnAccount;
	JRadioButton rdbtnCourses;
	JScrollPane scrollPane;
	JComboBox<String> comboBoxType;
	protected static boolean isActive = false;
	private JFrame pointerToThyself;
	private Account caller;
	JScrollPane txtScroller;
	
	public AdminFrame(Account acc) {
		setTitle("Administrator");
		isActive = true;
		pointerToThyself = this;
		caller = acc;
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
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {	
			}

			@Override
			public void windowOpened(WindowEvent e) {			
			}
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(550, 500);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenuItem mntmSaveFile = new JMenuItem("Save File");
		mntmSaveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountManager.writeFile();
				JOptionPane.showMessageDialog(pointerToThyself, "File Saved" , "Save File", JOptionPane.INFORMATION_MESSAGE);
			}});
		mnFile.add(mntmSaveFile);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!ChangePassword.isActive) {
					ChangePassword tch = new ChangePassword(caller);
					tch.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(pointerToThyself, "Window Already Open", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnOptions.add(mntmChangePassword);
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isActive = false;
				pointerToThyself.dispose();
			}
		});
		mnOptions.add(mntmLogout);
		
		JMenu mnCreate = new JMenu("Create");
		menuBar.add(mnCreate);
		
		JMenuItem mntmCreateCourse = new JMenuItem("Create Course");
		mntmCreateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!CreateCourseFrame.isActive) {
					CreateCourseFrame tch = new CreateCourseFrame();
					tch.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(pointerToThyself, "Window Already Open", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnCreate.add(mntmCreateCourse);
		
		JMenuItem mntmCreateAccount = new JMenuItem("Create Account");
		mntmCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!CreateAccountFrame.isActive) {
					CreateAccountFrame tch = new CreateAccountFrame();
					tch.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(pointerToThyself, "Window Already Open", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnCreate.add(mntmCreateAccount);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Helpline");
		mnHelp.add(mntmHelp);
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pointerToThyself, "Contact: 0016518327" , "Helpline", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// Content Pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Labels
		JLabel lblAdminName = new JLabel("Admin Name: " + caller.id);
		lblAdminName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdminName.setBounds(20, 10, 430, 16);

		JLabel lblAdminId = new JLabel("Admin Id: " + caller.name);
		lblAdminId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdminId.setBounds(20, 38, 430, 16);
		
		// Combo BOX FOR ACCOUNTS
		comboBoxType = new JComboBox<String>();
		comboBoxType.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin", "Student", "Teacher"}));
		comboBoxType.setSelectedIndex(1);
		comboBoxType.setBounds(246, 179, 123, 20);
		comboBoxType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (rdbtnAccount.isSelected()) {
					rdbtnCourses.setSelected(false);
					String selectedAccType = "" + arg0.getItem();
					createTable(selectedAccType);
				}
			}		
		});
		
		// Text Area
		txtrDisplaySelectedElement = new JTextArea();
		txtrDisplaySelectedElement.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrDisplaySelectedElement.setEditable(false);
		txtrDisplaySelectedElement.setForeground(Color.WHITE);
		txtrDisplaySelectedElement.setBackground(Color.DARK_GRAY);
		txtrDisplaySelectedElement.setText("Display Selected Element");
		txtrDisplaySelectedElement.setBounds(20, 65, 500, 106);
		
		txtScroller = new JScrollPane(txtrDisplaySelectedElement);
		txtScroller.setBounds(20, 65, 500, 106);
		
		// Radio Buttons
		rdbtnCourses = new JRadioButton("Course");
		rdbtnCourses.setBounds(20, 178, 109, 23);
		rdbtnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCourses.isSelected()) {
					rdbtnAccount.setSelected(false);
					//comboBoxType.setVisible(false);
					createTable("Course");
				}
			}
		});
		rdbtnAccount = new JRadioButton("Account");
		rdbtnAccount.setBounds(131, 178, 109, 23);
		rdbtnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAccount.isSelected()) {
					rdbtnCourses.setSelected(false);
					//comboBoxType.setVisible(true);
					String selectedAccType = "" + comboBoxType.getSelectedItem();
					createTable(selectedAccType);
				}
			}
		});
		
		table = new JTable();
		table.setBounds(20, 210, 500, 220);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 210, 500, 220);
		
		rdbtnCourses.setSelected(true);
		createTable("Course");
		
		contentPane.add(scrollPane);
		contentPane.add(lblAdminName);
		contentPane.add(lblAdminId);
		contentPane.add(txtScroller);
		contentPane.add(rdbtnCourses);
		contentPane.add(rdbtnAccount);
		contentPane.add(comboBoxType);
	}
	
	private void createTable(String type) {
		DefaultTableModel tableModel =  new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};;
		if (type.equals("Student")) {
			columnHeaders = new String[3];
			Object[] row = new Object[3]; // row with two columns
			tableModel.setColumnCount(3);
			for (Account acc1: AccountManager.accs) {
				if (acc1.getType().equals(AccountManager.types[1])) {
					Student std = (Student) acc1;
					row[0] = std.getId();
					row[1] = std.getName();
					row[2] = std.creditTaken;
					tableModel.insertRow(tableModel.getRowCount(), row);
				}
				
			}
			columnHeaders[0] = "ID";
			columnHeaders[1] = "Name";
			columnHeaders[2] = "Credits";	
		} else if (type.equals("Course")){
			columnHeaders = new String[4];
			Object[] row = new Object[4]; // row with two columns
			tableModel.setColumnCount(4);
			for (Course crs: AccountManager.courses) {
					row[0] = crs.id;
					row[1] = crs.name;
					row[2] = crs.credits;
					row[3] = crs.teacherId;
					tableModel.insertRow(tableModel.getRowCount(), row);
			}
			columnHeaders[0] = "ID";
			columnHeaders[1] = "Name";
			columnHeaders[2] = "Credits";	
			columnHeaders[3] = "TeacherId";
		} else if (type.equals("Teacher")) {
			columnHeaders = new String[2];
			Object[] row = new Object[2]; // row with two columns
			tableModel.setColumnCount(2);
			for (Account acc1: AccountManager.accs) {
				if (acc1.getType().equals(AccountManager.types[2])) {
					row[0] = acc1.getId();
					row[1] = acc1.getName();
					tableModel.insertRow(tableModel.getRowCount(), row);
				}	
			}
			columnHeaders[0] = "ID";
			columnHeaders[1] = "Name";
		} else if (type.equals("Admin"))	{
			columnHeaders = new String[2];
			Object[] row = new Object[2]; // row with two columns
			tableModel.setColumnCount(2);
			for (Account acc1: AccountManager.accs) {
				if (acc1.getType().equals(AccountManager.types[0])) {
					row[0] = acc1.getId();
					row[1] = acc1.getName();
					tableModel.insertRow(tableModel.getRowCount(), row);
				}	
			}
			columnHeaders[0] = "ID";
			columnHeaders[1] = "Name";
		}
		tableModel.setColumnIdentifiers(columnHeaders);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseInputAdapter() {
			  public void mouseClicked(MouseEvent e) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();

			      String samba = "";
			      if (type.equals("Course")) {
			    	  for (Course crs: AccountManager.courses) {
							if (crs.id.equals(table.getValueAt(row, 0))) {
								samba = crs.toString();
							}
			    	  }
			      } else {
			    	  for (Account acc1: AccountManager.accs) {
							if (acc1.getId().equals(table.getValueAt(row, 0))) {
								samba = acc1.toString();
							}
			    	  }
			      }
			      txtrDisplaySelectedElement.setText(samba);
			      txtScroller.setViewportView(txtrDisplaySelectedElement);
			  }
		});	
		scrollPane.setViewportView(table);
	}
}
