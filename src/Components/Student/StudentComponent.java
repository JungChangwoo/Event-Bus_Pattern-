/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in MyungJi University 
 */

package Components.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import Framework.Event;

public class StudentComponent {
	protected ArrayList<Student> vStudent;
	
	public StudentComponent(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Student>();
		while (bufferedReader.ready()) {
			String stuInfo = bufferedReader.readLine();
			if (!stuInfo.equals("")) this.vStudent.add(new Student(stuInfo));
		}
		bufferedReader.close();
	}
	public StudentComponent(Event event) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new StringReader(event.getMessage()));
		this.vStudent = new ArrayList<Student>();
		while (bufferedReader.ready()) {
			String stuInfo = bufferedReader.readLine();
			if(stuInfo == null) {
				bufferedReader.close();
				return;
			}
			if ((!stuInfo.equals(""))) this.vStudent.add(new Student(stuInfo));
		}
		bufferedReader.close();
	}
	public ArrayList<Student> getStudentList() {
		return vStudent;
	}
	public void setvStudent(ArrayList<Student> vStudent) {
		this.vStudent = vStudent;
	}
	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			if (((Student) this.vStudent.get(i)).match(sSID)) return true;
		}
		return false;
	}
	public boolean deleteStudent(String studentId) {
		for (int i = 0; i < this.vStudent.size(); i++) {
            if(((Student) this.vStudent.get(i)).match(studentId)) {
            	this.vStudent.remove(i);
            	return true;
            }
        }
        return false;
	}
	public Student getStudent(String studentId) {
		for(int i=0; i<this.vStudent.size(); i++) {
			if(this.vStudent.get(i).match(studentId)) {
				return this.vStudent.get(i);
			}
		}
		return null;
	}
}
