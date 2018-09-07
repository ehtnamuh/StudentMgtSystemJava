import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

public class StudentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JTextArea txtrDisplaySelectedElement; 
	String[] columnHeaders = null;
	JRadioButton rdbtnTeachers;
	JRadioButton rdbtnCourses;
	JScrollPane scrollPane;
	protected static boolean isActive = false;
	private JFrame pointerToThyself;
	private Account caller;
	JScrollPane txtScroller;
	
	public StudentFrame(Account acc) {
		setTitle("Student");
		isActive = true;
		pointerToThyself = this;
		caller = acc;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// On close operation
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
		
		JMenu mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);
		JMenuItem mntmAddCourse = new JMenuItem("Add New Course");
		mntmAddCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!AddCourseFrame.isActive) {
					AddCourseFrame tch = new AddCourseFrame(caller);
					tch.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Window Already Open", "Error", JOptionPane.ERROR_MESSAGE);
				}
				//JOptionPane.showMessageDialog(pointerToThyself, "Course Adder" , "Add Course", JOptionPane.INFORMATION_MESSAGE);
			}});
		mnAdd.add(mntmAddCourse);
		
		// Content Pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Labels
		JLabel lblStudentName = new JLabel("Student Name: " + acc.name);
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentName.setBounds(20, 10, 430, 16);

		JLabel lblStudentId = new JLabel("Student Id: " + acc.id);
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentId.setBounds(20, 30, 430, 16);
		
		JLabel lblCredits = new JLabel("Credits Taken: " + ((Student)acc).creditTaken);
		lblCredits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCredits.setBounds(20, 50, 430, 16);
		
		txtrDisplaySelectedElement = new JTextArea();
		txtrDisplaySelectedElement.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrDisplaySelectedElement.setEditable(false);
		txtrDisplaySelectedElement.setForeground(Color.WHITE);
		txtrDisplaySelectedElement.setBackground(Color.DARK_GRAY);
		txtrDisplaySelectedElement.setText("Display Selected Element");
		txtrDisplaySelectedElement.setBounds(20, 70, 500, 106);
		
		txtScroller = new JScrollPane(txtrDisplaySelectedElement);
		txtScroller.setBounds(20, 70, 500, 106);
		
		// Radio Buttons
		rdbtnCourses = new JRadioButton("Course");
		rdbtnCourses.setBounds(20, 178, 109, 23);
		rdbtnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCourses.isSelected()) {
					rdbtnTeachers.setSelected(false);
					createTable("Course");
				}
			}
		});
		rdbtnTeachers = new JRadioButton("Teacher");
		rdbtnTeachers.setBounds(131, 178, 109, 23);
		rdbtnTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTeachers.isSelected()) {
					rdbtnCourses.setSelected(false);
					createTable("Teacher");
				}
			}
		});
		
		table = new JTable();
		table.setBounds(20, 210, 500, 220);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 210, 500, 220);
		createTable("Course");
		
		contentPane.add(scrollPane);
		contentPane.add(lblStudentId);
		contentPane.add(lblStudentName);
		contentPane.add(lblCredits);
		contentPane.add(txtScroller);
		contentPane.add(rdbtnCourses);
		contentPane.add(rdbtnTeachers);
	}
	
	private void createTable(String type) {

		DefaultTableModel tableModel =  new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};;
		if (type.equals("Teacher")) {
			columnHeaders = new String[2];
			Object[] row = new Object[2]; // row with two columns
			tableModel.setColumnCount(3);
			for (Account acc1: AccountManager.accs) {
				if (acc1.getType().equals(AccountManager.types[2])) {
					Teacher std = (Teacher) acc1;
					if (std.students.contains(caller.getId())) {
						row[0] = std.getId();
						row[1] = std.getName();
						tableModel.insertRow(tableModel.getRowCount(), row);
					}
				}
				
			}
			columnHeaders[0] = "ID";
			columnHeaders[1] = "Name";	
		} else if (type.equals("Course")){
			columnHeaders = new String[4];
			Object[] row = new Object[4]; // row with two columns
			tableModel.setColumnCount(4);
			for (Course crs: AccountManager.courses) {
				if (crs.students.contains(caller.getId())) {
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
			      if (type.equals("Teacher")) {
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
