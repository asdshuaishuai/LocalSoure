 /**  
* @Title: BaseService.java
* @Package org.service
* @Description: TODO�÷�������Ҫ���ã�
* @author A18ccms A18ccms_gmail_com  
* @date 2017-9-22 ����8:48:52
* @version V1.0  
*/
package com.huoshuai.face.service;

import com.huoshuai.face.user.Users;

/**   
 *    
 * ��Ŀ���ƣ�test_face_photo   
 * �����ƣ�BaseService   
 * ��������   Service�ӿ�
 * �޸ı�ע��   
 * @version    
 *    
 */
public interface IUserService {
	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã�ע�ᣬ�������
	* @Title: save
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�int   
	* @throws
	 */
	public int save(Users user);
	

	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã������û������в�ѯ
	* @Title: queryInfoByUsername
	* @param  @param username
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�Users   
	* @throws
	 */
	public Users queryInfoByUsername(String username);
	
	

}
