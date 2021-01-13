package cqjtu.edu.studentadmin.entity;

public class Student extends Root{

	private int age;

	
	public Student(int no,String name,int age){
		super(no,name);
		this.age=age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

}
