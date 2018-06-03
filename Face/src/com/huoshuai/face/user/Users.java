 /**  
* @Title: Users.java

* @Description: TODO该方法的主要作用：

* @version V1.0  
*/
package com.huoshuai.face.user;

 /**   
 *    
 * 项目名称：test_face_photo   
 * 类名称：Users   
 * 类描述：   实体类
 * 修改备注：   
 * @version    
 *    
 */
public class Users {
	
	private Integer id ;			//编号
	private String username;		//用户名
	private String password;		//密码
	private String headphoto;		//面部数据
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadphoto() {
		return headphoto;
	}
	public void setHeadphoto(String headphoto) {
		this.headphoto = headphoto;
	}
	public Users(Integer id, String username, String password, String headphoto) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.headphoto = headphoto;
	}
	public Users() {
		super();
	}
	
	

}
