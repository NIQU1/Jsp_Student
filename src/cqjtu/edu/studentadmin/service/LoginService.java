package cqjtu.edu.studentadmin.service;

import java.sql.SQLException;

import cqjtu.edu.studentadmin.dao.DatabaseOperation;
//
public class LoginService {
    DatabaseOperation dbOp=new DatabaseOperation();
    public boolean loginCheck(String name,String password) throws SQLException {
    	
    	return  dbOp.findUser(name, password);
    }
}
