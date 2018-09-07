import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCourseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JScrollPane scrollPane;
	
	JLabel lblCourseId;
	JLabel lblCourseName; 
	JLabel lblCourseCredit;
	JLabel lblCourseTeacher;
	JLabel lblCourseList;
	JLabel lblMyCourses;
	JLabel lblSeatsleft;
	
	protected static boolean isActive = false;
	private JFrame pointerToThyself;
	private Account caller;
	private String selectedCourse;
	
	public AddCourseFrame(Account acc) {
		setTitle("Add New Course");
		isActive = true;
		caller = acc;
		pointerToThyself = this;
		selectedCourse = "";
	
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
		// Center window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(550, 500);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// Content Pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Labels
		lblCourseId = new JLabel("Course Id:");
		lblCourseId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseId.setBounds(30, 20, 300, 14);
				
		lblCourseCredit = new JLabel("Course Credit:");
		lblCourseCredit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseCredit.setBounds(30, 60, 300, 14);
				
		lblCourseTeacher = new JLabel("Course Teacher:");
		lblCourseTeacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseTeacher.setBounds(30, 80, 300, 14);
		
		lblCourseList = new JLabel("Course List");
		lblCourseList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCourseList.setBounds(30, 160, 300, 14);
		
		lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseName.setBounds(30, 40, 300, 14);
		contentPane.add(lblCourseName);
		
		lblSeatsleft = new JLabel("SeatsLeft:");
		lblSeatsleft.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeatsleft.setBounds(30, 100, 300, 14);
		contentPane.add(lblSeatsleft);
		
		String tmpCourses = "";
		for (int i = 0; i < ((Student)caller).courses.size(); i++) {
			tmpCourses += ((Student)caller).courses.get(i);
		}
		lblMyCourses = new JLabel("My Courses :" + tmpCourses);
		lblMyCourses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMyCourses.setBounds(30, 120, 300, 14);
		contentPane.add(lblMyCourses);
		
		// Buttons
		JButton btnNewButton = new JButton("Add Course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(((Student)caller).courses.contains(selectedCourse)) {
					JOptionPane.showMessageDialog(pointerToThyself, "Course Already Added", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					for (Course crs: AccountManager.courses) {
						if (crs.id.equals(selectedCourse)) {
							crs.students.add(caller.id);
							((Student)caller).courses.add(crs.id);
							if (!((Student)caller).teachers.contains(crs.teacherId)) {
								((Student)caller).teachers.add(crs.teacherId);
							}
							((Student)caller).creditTaken += crs.credits;
							// forgive me Gods of modularity and efficiency
							for (Account acc: AccountManager.accs) {
								if (acc.getType().equals(AccountManager.types[2])) {
									if (acc.getId().equals(crs.teacherId)) {
										System.out.println("ADDED TO TEACHER: " + caller.id);
										if (!((Teacher)acc).students.contains(caller.id)) {
											((Teacher)acc).students.add(caller.id);
										}
										break;
									} 
								}
							}
							AccountManager.writeFile();
							AccountManager.writeCourseFile();
							JOptionPane.showMessageDialog(pointerToThyself, "Course added!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						
							String tmpCourses1 = "";
							for (int i = 0; i < ((Student)caller).courses.size(); i++) {
								tmpCourses1 += ((Student)caller).courses.get(i) + " | ";
							}
							lblMyCourses.setText("My Courses :" + tmpCourses1);
							break;
						}
					}
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setBounds(336, 148, 184, 25);
		
		// Table
		table = new JTable();
		table.setBounds(20, 210, 500, 220);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 184, 500, 266);
		createTable();
		
		contentPane.add(scrollPane);
		contentPane.add(btnNewButton);
		contentPane.add(lblCourseId);
		contentPane.add(lblCourseTeacher);
		contentPane.add(lblCourseCredit);
		contentPane.add(lblCourseList);
	}
	private void createTable() {

		DefaultTableModel tableModel =  new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};;
		String[] columnHeaders = new String[4];
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
		tableModel.setColumnIdentifiers(columnHeaders);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseInputAdapter() {
			  public void mouseClicked(MouseEvent e) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      for (Course crs: AccountManager.courses) {
						if (crs.id.equals(table.getValueAt(row, 0))) {
							selectedCourse = crs.id;
							lblCourseId.setText("Course Id: "+crs.id);
							lblCourseName.setText("Course Name: " + crs.name); 
							lblCourseCredit.setText("Course Credits: " + crs.credits);
							lblCourseTeacher.setText("Course Teacher: " + crs.teacherId);
							lblSeatsleft.setText("Seats Left: "+(crs.seats-crs.students.size()));		   
						}
			      }
			  }
		});	
		scrollPane.setViewportView(table);
	}
}
