 /**  
* @Title: Users.java

* @Description: TODO�÷�������Ҫ���ã�

* @version V1.0  
*/
package com.huoshuai.face.user;

import java.sql.*;

/**   
 *    
 * ��Ŀ���ƣ�test_face_photo   
 * �����ƣ�Users   
 * ��������   ʵ����
 * �޸ı�ע��   
 * @version    
 *    
 */
public class UserTime {
	
	private Integer id ;			//���
	private String username;		//�û���
	private String date;
	private String time;		//����
	private String userstatus;
//	private String headphoto;		//�沿����
	
	
	public UserTime(Integer id, String username, Date time) {
		super();
		this.id = id;
		this.username = username;
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

	public UserTime() {
		super();
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	
	

}
