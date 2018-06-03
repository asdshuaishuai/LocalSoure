package com.huoshuai.face.dao.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huoshuai.face.dao.BaseDao;
import com.huoshuai.face.user.UserTime;

public class TimeBaseDaoUtilImpl extends BaseDao{
	
	public  int timesUser(UserTime entity) {
		int	rel	=0;
		String 	sql="";
		List<Object> prams=new ArrayList<Object>();	
		UserTime userTime=(UserTime) entity;//初始化Users对象
		//添加用户
		sql	="insert into usertimes(username,date,time,userstatus) values(?,?,?,?)";   		//sql语句
			try {
				prams.add(userTime.getUsername());
				prams.add(userTime.getDate());
				
				prams.add(userTime.getTime());
				prams.add(userTime.getUserstatus());
				rel=executeUpdate(sql, prams);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
				closeConnnection();			//关闭连接
			}
			return rel;
			
		
	}

	
	
	public List<UserTime> userTimes() {
		String sql="select * from usertime";   		//sql语句
		 List<UserTime> userList =new ArrayList<UserTime>();
		
//		List<Object> prams=new ArrayList<Object>();
		try {
//			prams.add(username);
			ResultSet rs=executeQuery(sql);
			UserTime usertime=null;
			while(rs.next()){
				usertime=new UserTime();
				
				usertime.setId(rs.getInt(1));
				usertime.setUsername(rs.getString(2));
//				usertime.setTime(rs.getTime(3));
//				usertime.setHeadphoto(rs.getString(4));
				
				userList.add(usertime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			closeConnnection();			//关闭连接
		}
		return userList;
}

}
