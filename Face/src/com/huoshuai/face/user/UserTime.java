 /**  
* @Title: Users.java

* @Description: TODO该方法的主要作用：

* @version V1.0  
*/
package com.huoshuai.face.user;

import java.sql.*;

/**   
 *    
 * 项目名称：test_face_photo   
 * 类名称：Users   
 * 类描述：   实体类
 * 修改备注：   
 * @version    
 *    
 */
public class UserTime {
	
	private Integer id ;			//编号
	private String username;		//用户名
	private String date;
	private String time;		//密码
	private String userstatus;
//	private String headphoto;		//面部数据
	
	
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
