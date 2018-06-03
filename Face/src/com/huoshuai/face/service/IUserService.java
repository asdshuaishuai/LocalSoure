 /**  
* @Title: BaseService.java
* @Package org.service
* @Description: TODO该方法的主要作用：
* @author A18ccms A18ccms_gmail_com  
* @date 2017-9-22 下午8:48:52
* @version V1.0  
*/
package com.huoshuai.face.service;

import com.huoshuai.face.user.Users;

/**   
 *    
 * 项目名称：test_face_photo   
 * 类名称：BaseService   
 * 类描述：   Service接口
 * 修改备注：   
 * @version    
 *    
 */
public interface IUserService {
	
	/**
	 * 
	* @Description: 该方法的主要作用：注册，添加数据
	* @Title: save
	* @param  @return 设定文件  
	* @return  返回类型：int   
	* @throws
	 */
	public int save(Users user);
	

	
	/**
	 * 
	* @Description: 该方法的主要作用：根据用户名进行查询
	* @Title: queryInfoByUsername
	* @param  @param username
	* @param  @return 设定文件  
	* @return  返回类型：Users   
	* @throws
	 */
	public Users queryInfoByUsername(String username);
	
	

}
