package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;


@Entity
@Table(name="sinhvien")
public class Ranking implements Serializable {

	@Id
	@Column
	private String id;
	@Column(name="tensinhvien")
	private String name;
	@Column(name="khoa")
	private String department;
	@Column(name="namhoc")
	private int courseYear;
	@Column(name="nganh")
	private String faculty;
	@Column(name="diemhebon")
	private float diem;
	@Column(name="ranking")
	private int stt;
	
	public Ranking() {
		
	}

	public Ranking(String id, String name, String department, int courseYear, String faculty, float diem) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.courseYear = courseYear;
		this.faculty = faculty;
		this.diem = diem;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(int courseYear) {
		this.courseYear = courseYear;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public float getDiem() {
		return diem;
	}

	public void setDiem(float diem) {
		this.diem = diem;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", department=" + department + ", courseYear=" + courseYear
				+ ", faculty=" + faculty + ", diem=" + diem + ", stt=" + stt + "]";
	}
	
}
