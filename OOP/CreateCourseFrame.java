import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class CreateCourseFrame extends JFrame {

	private JPanel contentPanel;
	private JTable table;
	JScrollPane scrollPane;
	
	JLabel lblCourseId;
	JLabel lblCourseName; 
	JLabel lblCourseCredit;
	JLabel lblCourseTeacher;
	JLabel lblTeacherList;
	JLabel lblSeats;
	JLabel lblDepartment;
	
	protected static boolean isActive = false;
	private JFrame pointerToThyself;
	private String selectedTeacher;
	private String selectedDepartment;
	private JComboBox<String> comboBoxCredits;
	private JComboBox<String> comboBoxDepartment;
	private JTextField txtCourseName;
	
	public CreateCourseFrame() {
		setTitle("Create Course");
		isActive = true;
		pointerToThyself = this;
		selectedTeacher = "";
	
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
		this.setSize(550, 550);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// Content Pane
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		// Labels
		lblCourseId = new JLabel("Course Id:");
		lblCourseId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseId.setBounds(30, 125, 290, 14);
				
		lblCourseCredit = new JLabel("Course Credit:");
		lblCourseCredit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseCredit.setBounds(30, 45, 88, 14);
				
		lblCourseTeacher = new JLabel("Course Teacher:");
		lblCourseTeacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseTeacher.setBounds(30, 100, 290, 14);
		
		lblTeacherList = new JLabel("Teachers List");
		lblTeacherList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTeacherList.setBounds(20, 209, 300, 14);
		
		lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseName.setBounds(30, 20, 100, 14);
		contentPanel.add(lblCourseName);
		
		lblSeats = new JLabel("Seats: 40");
		lblSeats.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeats.setBounds(30, 150, 100, 14);
		contentPanel.add(lblSeats);
		
		lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDepartment.setBounds(30, 70, 79, 14);
		contentPanel.add(lblDepartment);
		
		// Text Boxes
		txtCourseName = new JTextField();
		txtCourseName.setBounds(140, 20, 184, 20);
		contentPanel.add(txtCourseName);
		
		// Buttons
		JButton btnNewButton = new JButton("Create Course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(txtCourseName.getText());
				//System.out.println(selectedTeacher);
				//System.out.println(selectedDepartment);
				if (txtCourseName.getText().equals("") || selectedDepartment.equals("") || 
						selectedTeacher.equals("")) {
					JOptionPane.showMessageDialog(pointerToThyself, "Error! Check Input" , "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String id = selectedDepartment + (++Course.numCourses);
					String name = txtCourseName.getText();
					int credits = Integer.parseInt((String) comboBoxCredits.getSelectedItem());
					String teacherId = selectedTeacher;
					for (Course crs: AccountManager.courses) {
						if (name.equals(crs.name) && teacherId.equals(crs.teacherId)) {
							JOptionPane.showMessageDialog(pointerToThyself, "Error! Course already exists" ,
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					AccountManager.courses.add(new Course(id, name, credits, teacherId));
					AccountManager.writeCourseFile();
					JOptionPane.showMessageDialog(pointerToThyself, "Course Created" , "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setBounds(336, 198, 184, 25);
		
		// Table
		table = new JTable();
		table.setBounds(20, 210, 500, 220);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 234, 500, 266);
		createTable();
		
		contentPanel.add(scrollPane);
		contentPanel.add(btnNewButton);
		contentPanel.add(lblCourseId);
		contentPanel.add(lblCourseTeacher);
		contentPanel.add(lblCourseCredit);
		contentPanel.add(lblTeacherList);
		
		comboBoxCredits = new JComboBox<String>();
		comboBoxCredits.setModel(new DefaultComboBoxModel<String>(new String[] {"3", "4", "5"}));
		comboBoxCredits.setSelectedIndex(0);
		comboBoxCredits.setBounds(140, 45, 100, 20);
		
		comboBoxDepartment = new JComboBox<String>();
		comboBoxDepartment.setModel(new DefaultComboBoxModel<String>(new String[] {"CSC", "EEE", "BBA", "ENV"}));
		comboBoxDepartment.setSelectedIndex(0);
		comboBoxDepartment.setBounds(140, 70, 100, 20);
		comboBoxDepartment.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				selectedDepartment = "" + comboBoxDepartment.getSelectedItem();
				lblCourseId.setText("Course Id: " + selectedDepartment + (Course.numCourses + 1));
			}		
		});
		
		contentPanel.add(comboBoxCredits);
		contentPanel.add(comboBoxDepartment);
	}
	private void createTable() {

		DefaultTableModel tableModel =  new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};;
		String[] columnHeaders = new String[2];
		Object[] row = new Object[2]; // row with two columns
		tableModel.setColumnCount(2);
		for (Account acc: AccountManager.accs) {
			if (acc.type.equals(AccountManager.types[2])) {
				row[0] = acc.id;
				row[1] = acc.name;
				tableModel.insertRow(tableModel.getRowCount(), row);
			}
		}
		columnHeaders[0] = "ID";
		columnHeaders[1] = "Name";
		tableModel.setColumnIdentifiers(columnHeaders);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseInputAdapter() {
			  public void mouseClicked(MouseEvent e) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      for (Account acc: AccountManager.accs) {
						if (acc.id.equals(table.getValueAt(row, 0))) {
							selectedTeacher = acc.id;
							lblCourseTeacher.setText("Course Teacher: " + acc.id);
							break;
						}
			      }
			  }
		});	
		scrollPane.setViewportView(table);
	}
}
