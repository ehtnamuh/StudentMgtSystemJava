import java.util.ArrayList;
import java.util.Arrays;

public class Course {
	String id;
	String name;
	String teacherId;
	int credits;
	int seats = 40;
	public static int numCourses = 0;
	ArrayList<String> students;
	
	Course(String id, String name, int credits, String teacherId, String[] studentId){
		this.id = id;
		this.name = name;
		this.credits = credits;
		this.teacherId = teacherId;
		this.students = new ArrayList<String>(Arrays.asList(studentId));
		if (Integer.parseInt(id.substring(3,id.length())) > numCourses) {
			numCourses = Integer.parseInt(id.substring(3,id.length()));
		}
	}
	
	Course(String id, String name, int credits, String teacherId){
		this.id = id;
		this.name = name;
		this.credits = credits;
		this.teacherId = teacherId;
		this.students = new ArrayList<String>();
		if (Integer.parseInt(id.substring(3,id.length())) > numCourses) {
			numCourses = Integer.parseInt(id.substring(3,id.length()));
		}
	}
	
	Course(String info){
		String[] elements = info.split("\\|");
		this.id = elements[0];
		this.name = elements[1];
		this.credits = Integer.parseInt(elements[2]);
		this.teacherId = elements[3];
		if (elements.length<= 4 || elements[4].equals("")) {
			this.students = new ArrayList<String>();
		} else {
			this.students = new ArrayList<String>(Arrays.asList(elements[4].split(",")));
		}
		if (Integer.parseInt(id.substring(3,id.length())) > numCourses) {
			numCourses = Integer.parseInt(id.substring(3,id.length()));
		}
	}
	
	public String toText() {
		String samba = this.id + "|" + this.name + "|" + this.credits + "|" + this.teacherId + "|";
		for(String std:students) {
			samba += std + ",";
		}
		return samba; 
	}
	
	public String toString() {
		String samba = "Name: " + this.name + "\nID: " + this.id + "\nCourse Credits: " 
				+ this.credits + "\nTeacher ID: " + this.teacherId + "\nEnrolled Students listed below: \n";		
        for(String std:students) {
           samba += std + " | ";
        }
        return samba;
	}
}
