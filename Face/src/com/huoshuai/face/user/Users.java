 /**  
* @Title: Users.java

* @Description: TODO�÷�������Ҫ���ã�

* @version V1.0  
*/
package com.huoshuai.face.user;

 /**   
 *    
 * ��Ŀ���ƣ�test_face_photo   
 * �����ƣ�Users   
 * ��������   ʵ����
 * �޸ı�ע��   
 * @version    
 *    
 */
public class Users {
	
	private Integer id ;			//���
	private String username;		//�û���
	private String password;		//����
	private String headphoto;		//�沿����
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
