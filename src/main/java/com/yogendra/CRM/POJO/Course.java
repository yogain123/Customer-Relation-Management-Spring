package com.yogendra.CRM.POJO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String courseName;
	
	@Column
	private String courseAuthorName;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="Student_Course",joinColumns=@JoinColumn(name="course_id"),inverseJoinColumns=@JoinColumn(name="student_id"))
	private List<Student> student;

	
	public Course()
	{
		
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCourseAuthorName() {
		return courseAuthorName;
	}


	public void setCourseAuthorName(String courseAuthorName) {
		this.courseAuthorName = courseAuthorName;
	}


	public List<Student> getStudent() {
		return student;
	}


	public void setStudent(List<Student> student) {
		this.student = student;
	}

	
	
	
	
	
}
