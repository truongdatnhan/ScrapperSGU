package model;

import java.time.LocalDate;
import java.util.List;

public class Student {

	private String id;
	private String name;
	private String uniClass;
	private LocalDate dob;
	private String sex;
	private String courseYear;
	private String pob;
	private String department;
	private String faculty;
	private String major;
	private String counselor;
	private List<Course> courses;
	
	public Student() {
		
	}

	public Student(String id, String name, String uniClass, LocalDate dob, String sex, String courseYear, String pob,
			String department, String faculty, String major, String counselor) {
		this.id = id;
		this.name = name;
		this.uniClass = uniClass;
		this.dob = dob;
		this.sex = sex;
		this.courseYear = courseYear;
		this.pob = pob;
		this.department = department;
		this.faculty = faculty;
		this.major = major;
		this.counselor = counselor;
	}

	public Student(String id, String name, String uniClass, LocalDate dob, String sex, String courseYear, String pob,
			String department, String faculty, String major, String counselor, List<Course> courses) {
		this.id = id;
		this.name = name;
		this.uniClass = uniClass;
		this.dob = dob;
		this.sex = sex;
		this.courseYear = courseYear;
		this.pob = pob;
		this.department = department;
		this.faculty = faculty;
		this.major = major;
		this.counselor = counselor;
		this.courses = courses;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniClass() {
		return uniClass;
	}

	public void setUniClass(String uniClass) {
		this.uniClass = uniClass;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}

	public String getPob() {
		return pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCounselor() {
		return counselor;
	}

	public void setCounselor(String counselor) {
		this.counselor = counselor;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", uniClass=" + uniClass + ", dob=" + dob + ", sex=" + sex
				+ ", courseYear=" + courseYear + ", pob=" + pob + ", department=" + department + ", faculty=" + faculty
				+ ", major=" + major + ", counselor=" + counselor + "]";
	}
	
}
