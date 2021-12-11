package model;

import java.util.List;
import java.util.Map;

public class Student {
	private String stt;
	private String id;
	private String name;
	private String uniClass;
	private String sex;
	private int courseYear;
	private String courseDuration;
	private String pob;
	private String department;
	private String faculty;
	private String major;
	private String counselor;
	private int currentCredit;
	private List<Course> courses;
	private List<Course> remainCourses;
	private Map<String,List<Float>> markMap;
	
	public Student() {
		
	}

	public Student(String id, String name, String uniClass, String sex, int courseYear,
			String courseDuration, String pob, String department, String faculty, String major, String counselor) {
		this.id = id;
		this.name = name;
		this.uniClass = uniClass;
		this.sex = sex;
		this.courseYear = courseYear;
		this.courseDuration = courseDuration;
		this.pob = pob;
		this.department = department;
		this.faculty = faculty;
		this.major = major;
		this.counselor = counselor;
	}
	

	public Student(String id, String name, String uniClass, String sex, int courseYear,
			String courseDuration, String pob, String department, String faculty, String major, String counselor,
			List<Course> courses, Map<String,List<Float>> markMap) {
		super();
		this.id = id;
		this.name = name;
		this.uniClass = uniClass;
		this.sex = sex;
		this.courseYear = courseYear;
		this.courseDuration = courseDuration;
		this.pob = pob;
		this.department = department;
		this.faculty = faculty;
		this.major = major;
		this.counselor = counselor;
		this.courses = courses;
		this.markMap = markMap;
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
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(int courseYear) {
		this.courseYear = courseYear;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
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

	public Map<String, List<Float>> getMarkMap() {
		return markMap;
	}

	public void setMarkMap(Map<String, List<Float>> markMap) {
		this.markMap = markMap;
	}

	public List<Course> getRemainCourses() {
		return remainCourses;
	}

	public void setRemainCourses(List<Course> remainCourses) {
		this.remainCourses = remainCourses;
	}

	public int getCurrentCredit() {
		return currentCredit;
	}

	public void setCurrentCredit(int currentCredit) {
		this.currentCredit = currentCredit;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", uniClass=" + uniClass + ", sex=" + sex
				+ ", courseYear=" + courseYear + ", courseDuration=" + courseDuration + ", pob=" + pob + ", department="
				+ department + ", faculty=" + faculty + ", major=" + major + ", counselor=" + counselor + "]";
	}

}
