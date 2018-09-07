import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Student extends Account {

	protected ArrayList<String> courses;
	protected ArrayList<String> teachers;
	protected int creditTaken;
	Student(String info) {
		String elements[] = info.split("\\|");
		this.id = elements[0];
		this.password = elements[1];
		this.name = elements[2];
		this.creditTaken = Integer.parseInt(elements[3]);
		if (elements[4].equals("")) {
			this.courses = new ArrayList<String>();
		} else {
			this.courses = new ArrayList<String>(Arrays.asList(elements[4].split(",")));
		}
		if (elements[5].equals("")) {
			this.teachers = new ArrayList<String>();
		} else {
			this.teachers = new ArrayList<String>(Arrays.asList(elements[5].split(",")));
		}
		this.type = elements[6];
		if (Account.numAccounts < Integer.parseInt(this.id)) {
			Account.numAccounts = Integer.parseInt(this.id);
		}
	}
	
	Student(String id, String password, String name, int creditsTaken, String type) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.creditTaken = creditsTaken;
		this.courses = new ArrayList<String>();
		this.teachers = new ArrayList<String>();
		if (Account.numAccounts < Integer.parseInt(this.id)) {
			Account.numAccounts = Integer.parseInt(this.id);
		}
		this.type = type;
	}

	public String toText() {
		String temp = this.id+"|"+password+"|"+this.name+"|"+this.creditTaken+"|";
		for(String course: courses) {
			temp += course + ",";
		}
		temp+= "|";
		for(String teacher: teachers) {
			temp += teacher + ",";
		}
		temp+= "|" + this.type;
		return temp;
	}
	
	public String toString() {
		String temp = "Name: " + this.name + "\nID: " + this.id  + "\nCredits Taken: " + this.creditTaken + "\nCourses: ";
		for(String course: courses) {
			temp += course + " | ";
		}
		temp+= "\nTeachers: ";
		for(String teacher: teachers) {
			temp += teacher + " | ";
		}
		temp+= "\nType:" + this.type;
		return temp;
	}
	
	public void showGUIOptions() {
		if (!StudentFrame.isActive) {
			StudentFrame std = new StudentFrame(this);
			std.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Window Already Open", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
