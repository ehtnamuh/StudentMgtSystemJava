import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AccountManager {
	public static ArrayList<Account> accs = new ArrayList<Account>();
	public static ArrayList<Course> courses = new ArrayList<Course>();
	public static String[] types = {"Admin","Student","Teacher"};
	
	public static void readFile() {
		try {
			FileInputStream fin = new FileInputStream("src/info.txt");
			int c;
			c = fin.read();
			String samba = "";
			while(c != -1) {
				if ((char)c == '\r') {
					System.out.println(samba);
					if (samba.trim().endsWith(types[0])){
						System.out.println(samba);
						Administration  adAcc = new Administration(samba);
						accs.add(adAcc);
					} else if(samba.trim().endsWith(types[1])) {
						Student std = new Student(samba);
						accs.add(std);
					} else if(samba.trim().endsWith(types[2])) {
						Teacher tch = new Teacher(samba);
						accs.add(tch);
					}
					samba = "";
				} else if ((char)c != '\n') {
					samba+=(char)c;
				}
				c = fin.read();
			}
			if (!samba.equals("")) {
				if (samba.endsWith(types[0])){
					Administration  adAcc = new Administration(samba);
					accs.add(adAcc);
				} else if(samba.trim().endsWith(types[1])) {
					Student std = new Student(samba);
					accs.add(std);
				} else if(samba.trim().endsWith(types[2])) {
					Teacher tch = new Teacher(samba);
					accs.add(tch);
				}
			}
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void ReadCourseFile() {
		try {
			FileInputStream fin = new FileInputStream("src/courses.txt");
			int c;
			c = fin.read();
			String samba = "";
			while(c != -1) {
				if ((char)c == '\r') {
					courses.add(new Course(samba));
					samba = "";
				} else if ((char)c != '\n') {
					samba+=(char)c;
				}
				c = fin.read();
			}
			if (!samba.equals("")) {
				courses.add(new Course(samba));
			}
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	public static void writeFile() {
		try {
			FileOutputStream fout = new FileOutputStream("src/info.txt");
			for (Account acc: accs) {
				fout.write(acc.toText().getBytes());
				fout.write('\r');
				fout.write('\n');
			}
			System.out.println("----------------------------");
			System.out.println("File Saved!");
			System.out.println("----------------------------");
			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void writeCourseFile() {
		try {
			FileOutputStream fout = new FileOutputStream("src/courses.txt");
			for (Course crs: courses) {
				fout.write(crs.toText().getBytes());
				fout.write('\r');
				fout.write('\n');
			}
			System.out.println("----------------------------");
			System.out.println("File Saved!");
			System.out.println("----------------------------");
			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static  void showGUILoginScreen() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}

	
}
