package cqjtu.edu.studentadmin.entity;

public class Course extends Root{
	
	
	private double score;
	
	public Course(int no,String name,double score){
		super(no,name);
		this.score=score;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}


	
}
