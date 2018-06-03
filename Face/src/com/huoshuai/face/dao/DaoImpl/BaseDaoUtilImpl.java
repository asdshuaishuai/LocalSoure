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
* ��Ŀ���ƣ�test_BaseDao   
* �����ƣ�BaseDaoUtilImpl   ,�̳�BaseDao
* ��������   ������ʵ��
* �޸ı�ע��   
* @version    
*
 */
public class BaseDaoUtilImpl<T> extends BaseDao  {

	
	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã�ע��
	* @Title: save
	* @param  @param entity
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�int   
	* @throws
	 */
	public  int save(T entity) {
		int	rel	=0;
		String 	sql="";
		List<Object> prams=new ArrayList<Object>();	
		Users	user=(Users) entity;//��ʼ��Users����
		//����û�
		sql	="insert into Users(id,username,password,headphoto) values(?,?,?,?)";   		//sql���
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
				closeConnnection();			//�ر�����
			}
			return rel;
		
	}

	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã������û������в�ѯ
	* @Title: queryInfoByUsername
	* @param  @param username
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�T   
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public T queryInfoByUsername(String username) {
			String sql="select * from users where username = ?";   		//sql���
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
				closeConnnection();			//�ر�����
			}
			return (T) user;
	}

	
	
}
