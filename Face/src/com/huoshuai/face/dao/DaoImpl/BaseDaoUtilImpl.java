/**
 * 
 */
package com.huoshuai.face.dao.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huoshuai.face.dao.BaseDao;
import com.huoshuai.face.user.UserTime;
import com.huoshuai.face.user.Users;



/**
 * 
*    
* 项目名称：test_BaseDao   
* 类名称：BaseDaoUtilImpl   ,继承BaseDao
* 类描述：   公共的实现
* 修改备注：   
* @version    
*
 */
public class BaseDaoUtilImpl<T> extends BaseDao  {

	
	
	/**
	 * 
	* @Description: 该方法的主要作用：注册
	* @Title: save
	* @param  @param entity
	* @param  @return 设定文件  
	* @return  返回类型：int   
	* @throws
	 */
	public  int save(T entity) {
		int	rel	=0;
		String 	sql="";
		List<Object> prams=new ArrayList<Object>();	
		Users	user=(Users) entity;//初始化Users对象
		//添加用户
		sql	="insert into Users(id,username,password,headphoto) values(?,?,?,?)";   		//sql语句
			try {
				prams.add(user.getId());
				prams.add(user.getUsername());
				prams.add(user.getPassword());
				prams.add(user.getHeadphoto());
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

	
	/**
	 * 
	* @Description: 该方法的主要作用：根据用户名进行查询
	* @Title: queryInfoByUsername
	* @param  @param username
	* @param  @return 设定文件  
	* @return  返回类型：T   
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public T queryInfoByUsername(String username) {
			String sql="select * from users where username = ?";   		//sql语句
			Users user=null;
			List<Object> prams=new ArrayList<Object>();
			try {
				prams.add(username);
				ResultSet rs=executeQuery(sql, prams);
				if(rs.next()){
						user = new Users();	
						user.setId(rs.getInt(1));
						user.setUsername(rs.getString(2));
						user.setPassword(rs.getString(3));
						user.setHeadphoto(rs.getString(4));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
				closeConnnection();			//关闭连接
			}
			return (T) user;
	}

	
	
}
