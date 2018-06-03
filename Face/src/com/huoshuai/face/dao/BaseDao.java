package com.huoshuai.face.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * 
*    
* ��Ŀ���ƣ�test_face_photo   
* �����ƣ�BaseDao   
* ��������   ���ݿ����ӹ�����

* �޸ı�ע��   
* @version    
*
 */
public class BaseDao {

	private static 	Connection con	=	null;

	//���ݿ�����
	private static String drive	="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//�����ַ���
	private static String url="jdbc:sqlserver://localhost:1433; DatabaseName=faceDB";
	
	//���ݿ��û���
	private static String user="sa";
	
	//����
	private static String pwd ="huoshuai";
	
	//��ʼ��PreparedStatement������sql���
	private static PreparedStatement ps=null;
	
	//��ʼ��ResultSet���ݼ�
	private static ResultSet rs=null;
	
	  

	/**
	 * 
	* @Description: �÷�������Ҫ���ã������ӣ��������ݿ�
	* @Title: getConnection
	* @param  @return
	* @param  @throws ClassNotFoundException
	* @param  @throws SQLException �趨�ļ�  
	* @return  �������ͣ�Connection   
	* @throws
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(drive);
		if(con==null){
			con = DriverManager.getConnection(url,user,pwd);
		}
		return con;
	}
	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã���ɾ��ͨ�õķ���
	* @Title: executeUpdate
	* @param  @param sql
	* @param  @param prams
	* @param  @return
	* @param  @throws ClassNotFoundException
	* @param  @throws SQLException �趨�ļ�  
	* @return  �������ͣ�int   
	* @throws
	 */
	public static int executeUpdate(String sql,List<Object> prams) throws ClassNotFoundException, SQLException{
		int rel=0;
		con=getConnection();
		ps=con.prepareStatement(sql);
		if(prams!=null){
			for (int i = 0; i <prams.size(); i++) {
				ps.setObject(i+1, prams.get(i));
			}
		}
		rel=ps.executeUpdate();	
		return rel;
	}
	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã���ѯ��ͨ�÷���
	* @Title: executeQuery
	* @param  @param sql
	* @param  @param prams
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�ResultSet   
	* @throws
	 */
	public static ResultSet executeQuery(String sql,List<Object>prams) throws ClassNotFoundException, SQLException{
		con=getConnection();
		ps=con.prepareStatement(sql);
		if(prams!=null){
			for (int i = 0; i < prams.size(); i++) {
				ps.setObject(i+1, prams.get(i));
			}
		}
		rs=ps.executeQuery();
		return rs;
	}
	public static ResultSet executeQuery(String sql) throws ClassNotFoundException, SQLException{
		con=getConnection();
		ps=con.prepareStatement(sql);
		
		rs=ps.executeQuery();
		return rs;
	}
	/**
	 * 
	* @Description: �÷�������Ҫ���ã��ر���Դ
	* @Title: closeConnnection
	* @param   �趨�ļ�  
	* @return  �������ͣ�void   
	* @throws
	 */
	public static void closeConnnection(){
	//�ر�֮ǰ���ж��Ƿ�Ϊnull
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(con!=null){
			try {
				con.close();
				con=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
//	/**
//	 * 
//	* @Description: �÷�������Ҫ���ã������������ݿ����
//	* @Title: main
//	* @param  @param args �趨�ļ�  
//	* @return  �������ͣ�void   
//	* @throws
//	 */
//	public static void main(String[] args) {
//		try {
//			System.out.println(getConnection());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
