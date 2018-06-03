/**
 * 
 */
package com.huoshuai.face.dao;

/**
 * 
*    
* ��Ŀ���ƣ�test_face_photo   
* �����ƣ�IBaseDaoUtil   
* ��������   ���ýӿ�

*
 */
public interface IBaseDaoUtil<T> {
	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã��������
	* @Title: save
	* @param  @param entity    ����Ķ���
	* @param  @param tag		������Emp����Dept
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�int   
	* @throws
	 */
	public int save(T entity);
	

	
	/**
	 * 
	* @Description: �÷�������Ҫ���ã����ݱ�Ų�ѯ����
	* @Title: queryById
	* @param  @param id
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�T   
	* @throws
	 */
	public T queryInfoByUsername(String username);
	
	
	
	
}
