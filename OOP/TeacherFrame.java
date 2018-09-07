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

public class TeacherFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JTextArea txtrDisplaySelectedElement; 
	String[] columnHeaders = null;
	JRadioButton rdbtnStudents;
	JRadioButton rdbtnCourses;
	JScrollPane scrollPane;
	protected static boolean isActive = false;
	private JFrame pointerToThyself;
	private Account caller;
	JScrollPane txtScroller;
	
	public TeacherFrame(Account acc) {
		setTitle("Teacher");
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
		JLabel lblTeacherName = new JLabel("Teacher Name: " + acc.name);
		lblTeacherName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeacherName.setBounds(20, 11, 430, 16);

		JLabel lblTeacherId = new JLabel("Teacher Id: " + acc.id);
		lblTeacherId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeacherId.setBounds(20, 38, 430, 16);
		
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
					rdbtnStudents.setSelected(false);
					createTable("Course");
				}
			}
		});
		rdbtnStudents = new JRadioButton("Student");
		rdbtnStudents.setBounds(131, 178, 109, 23);
		rdbtnStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnStudents.isSelected()) {
					rdbtnCourses.setSelected(false);
					createTable("Student");
				}
			}
		});
		
		table = new JTable();
		table.setBounds(20, 210, 500, 220);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 210, 500, 220);
		createTable("Student");
		
		contentPane.add(scrollPane);
		contentPane.add(lblTeacherName);
		contentPane.add(lblTeacherId);
		contentPane.add(txtScroller);
		contentPane.add(rdbtnCourses);
		contentPane.add(rdbtnStudents);
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
					if (std.teachers.contains(caller.getId())) {
						row[0] = std.getId();
						row[1] = std.getName();
						row[2] = std.creditTaken;
						tableModel.insertRow(tableModel.getRowCount(), row);
					}
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
				if (crs.teacherId.equals(caller.id)) {
					row[0] = crs.id;
					row[1] = crs.name;
					row[2] = crs.credits;
					row[3] = crs.teacherId;
					tableModel.insertRow(tableModel.getRowCount(), row);
				}
			}
			columnHeaders[0] = "ID";
			columnHeaders[1] = "Name";
			columnHeaders[2] = "Credits";	
			columnHeaders[3] = "TeacherId";
		}	
		tableModel.setColumnIdentifiers(columnHeaders);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseInputAdapter() {
			  public void mouseClicked(MouseEvent e) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();

			      String samba = "";
			      /*
			      for(int i = 0; i < columnHeaders.length; i++) {
			    	  samba += columnHeaders[i] + ": " + table.getValueAt(row, i).toString() + "\n";
			    	  System.out.println(samba);
			      };*/
			      if (type.equals("Student")) {
			    	  for (Account acc1: AccountManager.accs) {
							if (acc1.getId().equals(table.getValueAt(row, 0))) {
								samba = acc1.toString();
							}
						}
			      } else {
			    	  for (Course crs: AccountManager.courses) {
							if (crs.id.equals(table.getValueAt(row, 0))) {
								samba = crs.toString();
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
