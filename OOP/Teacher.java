import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Teacher extends Account {

	protected ArrayList<String> courses;
	protected ArrayList<String> students;
	Teacher(String info) {
		String elements[] = info.split("\\|");
		this.id = elements[0];
		this.password = elements[1];
		this.name = elements[2];
		if (elements[3].equals("")) {
			this.courses = new ArrayList<String>();
		} else {
			this.courses = new ArrayList<String>(Arrays.asList(elements[3].split(",")));
		}
		if (elements[4].equals("")) {
			this.students = new ArrayList<String>();
		} else {
			this.students = new ArrayList<String>(Arrays.asList(elements[4].split(",")));
		}
		this.type = elements[5];
		if (Account.numAccounts < Integer.parseInt(this.id)) {
			Account.numAccounts = Integer.parseInt(this.id);
		}
	}
	
	Teacher(String id, String password, String name, String type) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.courses = new ArrayList<String>();
		this.students = new ArrayList<String>();
		this.type = type;
		if (Account.numAccounts < Integer.parseInt(this.id)) {
			Account.numAccounts = Integer.parseInt(this.id);
		}
	}

	public String toText() {
		String temp = this.id+"|"+password+"|"+this.name+"|";
		for(String course: courses) {
			temp += course + ",";
		}
		temp+= "|";
		for(String student: students) {
			temp += student + ",";
		}
		temp+= "|" + this.type;
		return temp;
	}
	
	public String toString() {
        String temp = "Name: " + this.name + "\nID: " + this.id + "\nCurrent Courses: ";
        for(String course: courses) {
                temp += course + " | ";
        }
        temp+= "\nCurrent Students: ";
        for(String student: students) {
                temp += student + " | ";
        }
        temp+= "\nType:" + this.type;
        return temp;
}
	
	public void showGUIOptions() {
		if (!TeacherFrame.isActive) {
			TeacherFrame tch = new TeacherFrame(this);
			tch.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Window Already Open", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
