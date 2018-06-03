package com.huoshuai.face.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.huoshuai.face.service.IUserService;
import com.huoshuai.face.service.UserTimeService;
import com.huoshuai.face.service.UserServiceImpl.UserServiceImpl;
import com.huoshuai.face.service.UserServiceImpl.UserTimeServiceImpl;
import com.huoshuai.face.user.UserTime;
import com.huoshuai.face.user.Users;
import com.huoshui.util.TimeUtil;
import com.huoshuai.*;

import sun.misc.BASE64Decoder;

public class FaceServlet extends HttpServlet {
	
	
	
	private static final String USER_STATUS_Z="����";
	private static final String USER_STATUS_C="�ٵ�";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * (�� Javadoc)
	* <p>Description(����):get��������Ҫ����post </p>
	* <p>Title: doGet</p>
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	* @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	/**
	 * (�� Javadoc)
	* <p>Description(����):post��������Ҫ����ҵ����� </p>
	* <p>Title: doPost</p>
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	* @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��ֹ����
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		String 		img			=	 	request.getParameter("img");			//ͼ������
		String 		username 	= 		request.getParameter("username");		//�û���
		String 		tag			= 		request.getParameter("tag");
		String APP_ID = "10984171";
	    String API_KEY = "dejSGLpy7iWGiqXWkoUG2VFl";
	    String SECRET_KEY = "upr4EQyg2EkayOuGlwh6glYESvTkvqfQ";
	    AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		if(tag.equals("reg")){
			//ע��
			face(username, img, response,request,client);
		}else if(tag.endsWith("login")){
			//��½
			try {
				login(img, response, username,request,client);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	* @Title: face
	* @Description: �÷�������Ҫ���ã�ע��
	* 1.��base64�����ͼƬ����
	* 2.��ͼƬ·�����������ݿ�����
	* 3.������ͼƬʶ������������ע��
	* @param   �趨�ļ�  
	* @return  �������ͣ�void   
	* @throws
	 */
	public void face(String username,String img,HttpServletResponse response,HttpServletRequest request,AipFace client) {
		Users user = new Users();
		IUserService userService = new UserServiceImpl();
		// ͼƬ����
		String fileName = System.currentTimeMillis() + ".png";
		String basePath = request.getSession().getServletContext()
				.getRealPath("picture/");

		// �����ݿ��������ע����Ϣ
		user.setId(((Long)System.currentTimeMillis()).intValue());
		user.setUsername(username);
		user.setHeadphoto("/picture/" + fileName);
		userService.save(user);
		// �������������ϴ�ͼƬ
		GenerateImage(img, basePath + "/" + fileName);
		// ���������м���һ����
		boolean flag = facesetAddUser(client, basePath + "/" + fileName,
				username);
		try {
			PrintWriter out = response.getWriter();
			// �������룬д��Ӣ�ıȽ�רҵ ����

			if (flag == false) {
				out.print("���������!!");//�����������
			} else {
				out.print("ע��ɹ�!!");  //ע��ɹ�
			}
		} catch (IOException e) {
			// TODO �쳣ִ�п飡
			e.printStackTrace();
		}
	}


	/**
	 * @throws ParseException 
	 * @throws IOException 
	 * 
	* @Title: login
	* @Description: �÷�������Ҫ���ã���½
	* ʵ��ԭ��
	* 1.��ǰ̨�����û���
	* 2.Ȼ��Ѵ�ǰ̨��������ͼƬ���ϴ���������
	* 3.���ϴ��������������������е�������Ӧ
	* @param  �趨�ļ�  
	* @return  �������ͣ�String   
	* @throws
	 */
	public void login(String img,HttpServletResponse response,String username,HttpServletRequest request,AipFace client) throws IOException, ParseException {
		UserTime userTime=new UserTime();
		SimpleDateFormat sd=new SimpleDateFormat("HH:mm"); 
		Date time2=sd.parse("08:00");
		TimeUtil tu=new TimeUtil();
		tu.setTime(time2);
		UserTimeService utService=new UserTimeServiceImpl();
		Date dateTime = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat sdft=new SimpleDateFormat("HH:mm"); 
		String date=sdf.format(dateTime);
		String time=sdft.format(dateTime);
		Date time1=sdft.parse(time);
		
		userTime.setUsername(username);
		userTime.setDate(date);
		userTime.setTime(time);
		
		if(time1.before(tu.getTime())) {
			userTime.setUserstatus(USER_STATUS_Z);
		}else {
			userTime.setUserstatus(USER_STATUS_C);
		}
		// ͼƬ����
		String fileName = System.currentTimeMillis() + ".png";
		String basePath = request.getSession().getServletContext()
				.getRealPath("picture/");
		Double result = null;
		JSONObject js=new JSONObject();
		// �������������ϴ�ͼƬ
		
		GenerateImage(img, basePath + "/" + fileName);
		response.setContentType("text/html,charset=utf-8;");
	
			PrintWriter out = response.getWriter();
			result = verifyUser(client, basePath + "/" + fileName,username);
			if (result > 90) {
				
				// ƥ��ɹ�
				out.print("��½�ɹ�");  
				utService.timesUser(userTime);
				
			} else {
				//ƥ��ʧ��
				out.print("���棺ƥ��ʧ��");
				
			}
		
		
	}
	
	/**
	 * 
		* @Title: GenerateImage
		* @Description: �÷�������Ҫ���ã�// ���ֽ������ַ�������Base64���벢����ͼƬ
		* @param  @param imgStr
		* @param  @param imgFilePath
		* @param  @return �趨�ļ�  
		* @return  �������ͣ�boolean   
		* @throws
		 */
	public boolean GenerateImage(String imgStr, String imgFilePath) {
		if (imgStr == null) // ͼ������Ϊ��
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64����
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// �����쳣����
					bytes[i] += 256;
				}
			}
			// ����jpegͼƬ
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
		/** 
		 * @Title: facesetAddUser
	    * @Description: �÷�������Ҫ���ã�����ע��,����������ע��һ������
	    * @param  @param client �趨�ļ�  
	    * @return  �������ͣ�void   
	    * @throws
	     */
	public boolean facesetAddUser(AipFace client, String path, String username) {
		// ����Ϊ���ݿ���ע�������
		HashMap<String, String> options = new HashMap<String, String>();
		JSONObject res = client.addUser(username, "test_users_info",
				Arrays.asList("group1", "group2"), path, options);
		 System.out.println(res.toString(2));
		// {"log_id": 3621903690062422}
		if (res.keySet().contains("error_code")) {
			return false;
		}
		return true;

	}
	/**
	 * 
	* @Title: verifyUser
	* @Description: �÷�������Ҫ���ã�������֤
	* @param  @param client
	* @param  @param path
	* @param  @param username
	* @param  @return �趨�ļ�  
	* @return  �������ͣ�Double   
	* @throws
	 */
	    
	public Double verifyUser(AipFace client, String path, String username) {
		HashMap<String, Object> options = new HashMap<String, Object>(1);
		options.put("top_num", 5);
	
		JSONObject res = client.verifyUser(username,
				Arrays.asList("group1", "group2"), path, options);
		Double result = Double.parseDouble(res.getJSONArray("result").get(0)
				.toString());
		return result;
	}


}
