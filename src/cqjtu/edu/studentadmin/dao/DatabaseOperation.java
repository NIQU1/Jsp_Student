package cqjtu.edu.studentadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cqjtu.edu.studentadmin.entity.Student;




public class DatabaseOperation {
    public Connection getConn() {
    	Connection conn=null;
    	String driverName="com.mysql.cj.jdbc.Driver";
		String dbUrl="jdbc:mysql://localhost:3306/studentadmin?&useSSL=false&serverTimezone=UTC";
		String userName="root";
		String password="123456";
		try {
			Class.forName(driverName);
			conn=DriverManager.getConnection(dbUrl, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("鏁版嵁搴撹繛鎺ュけ璐�");
		}	
		return conn;
    }
    
    /*
     * 鐢ㄦ埛鐧诲綍楠岃瘉
     */
    public boolean findUser(String name,String password) throws SQLException{	
    	Connection conn=getConn();
		String sql="select * from user where userName=? and password=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			ResultSet results=pst.executeQuery();
			while(results.next()) {
				return true;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				pst.close();
				conn.close();
			
		}
    	return false;   	 
    }
    /*
     * 瀛︾敓淇℃伅鏌ヨ 
     */
    public List<Student> findStuInfo(String key){
		List<Student> students=new ArrayList<Student>();
		Connection conn=getConn();
		String sql = "select * from student where name like '%"+key+"%' or no like '%"+key+"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int no=results.getInt("no");
				String name=results.getString("name");
				int age=results.getInt("age");
				Student s=new Student(no,name,age);
				students.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;	
	}
    /*
     * 鍒犻櫎瀛︾敓淇℃伅
     */
    public int deleteStuInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from student where no="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return i;
    }
    /*
     * 鎻掑叆瀛︾敓淇℃伅
     */
    public int insertStuInfo(Student s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into student(name,age) values(?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setInt(2, s.getAge());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			
		}
	}
    /*
     * 缂栬緫瀛︾敓淇℃伅
     */
    public int updateStudent(Student s){
		int i=0;
		Connection con=getConn();
		String sql="update student set name='"+s.getName()+"',age="+s.getAge()+" where"
				+ " no="+s.getNo();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
    
}
