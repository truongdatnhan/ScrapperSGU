package model;

public class Course {

	private String courseID;
	private String courseName;
	private int courseCredit;
	private float gradeBase10;
	private int gradeBase4;
	private boolean isPass;
	
	
	public Course() {
		
	}

	public Course(String courseID, String courseName, int courseCredit) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseCredit = courseCredit;
	}

	public Course(String courseID, String courseName, int courseCredit, float gradeBase10, int gradeBase4,boolean isPass) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseCredit = courseCredit;
		this.gradeBase10 = gradeBase10;
		this.gradeBase4 = gradeBase4;
		this.isPass = isPass;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}

	public float getGradeBase10() {
		return gradeBase10;
	}

	public void setGradeBase10(float gradeBase10) {
		this.gradeBase10 = gradeBase10;
	}

	public int getGradeBase4() {
		return gradeBase4;
	}

	public void setGradeBase4(int gradeBase4) {
		this.gradeBase4 = gradeBase4;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseName=" + courseName + ", courseCredit=" + courseCredit
				+ ", gradeBase10=" + gradeBase10 + ", gradeBase4=" + gradeBase4 + ", isPass=" + isPass + "]";
	}

}
