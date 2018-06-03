<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%> <%@ page import="com.huoshuai.face.service.UserTimeService" %>
<%@ page import="com.huoshuai.face.service.UserServiceImpl.UserTimeServiceImpl" %>
<%@ page import="com.huoshuai.face.user.UserTime" %>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@ page import="java.sql.PreparedStatement"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
    
    <head>
        <title>后台管理</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
        <link href="assets/styles.css" rel="stylesheet" media="screen">

        <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
	<div>
    <body>
   
            <div class="main" style="text-align: center;margin: auto;position: absolute; top: 0;left: 0; right: 0; bottom: 0;">
			   <div class="container-fluid">
				<div class="row-fluid">
				 <div class="span3" id="sidebar" style="width:160px">
                  
                </div>
                <!--/span-->
                <div class="span9" id="content">
      
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">签到统计</div>
                                 <div class="pull-right">
                              
							  	<label id="date"></label>
								<script type="text/javascript">
								function today(){
   									var today=new Date();
   									var h=today.getFullYear();
   								    var m=today.getMonth()+1;
   								    var d=today.getDate();
   								    return h+"-"+m+"-"+d;
   								}
 
								document.getElementById("date").innerHTML = today();
							</script>
						
                            </div>
      <%
      	Date d=new Date();
     	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
      	String date=sdf.format(d);
     	 System.out.print(date);
        String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String DBURL="jdbc:sqlserver://localhost:1433; DatabaseName=faceDB";
        String DBUSER="sa";
        String PASSWORD="huoshuai";
        List<UserTime> userList =new ArrayList<UserTime>();
        List<UserTime> userList_z =new ArrayList<UserTime>();
        List<UserTime> userList_c =new ArrayList<UserTime>();
        List<UserTime> userList_n =new ArrayList<UserTime>();
        int count_z=0;
        int count_c=0;
        int count_a=0;
        int count_n=0;
        Connection cn=null;
        String sql_0="select count(*) as count_A from faceDB.dbo.usertimes where date=?";
        String sql_1="select * from faceDB.dbo.usertimes where date=?";
        String sql_2="select count(*) as count_C from faceDB.dbo.usertimes where userstatus='迟到' and date=?";
        String sql_3="select count(*) as count_Z from faceDB.dbo.usertimes where userstatus='正常' and date=?";
        String sql_4="select * from faceDB.dbo.usertimes  where userstatus='迟到' and date=?  ";
        String sql_5="select * from faceDB.dbo.usertimes  where userstatus='正常' and date=? ";
        String sql_6="select b.id as id ,b.username as username from users a left join usertimes b on a.username=b.username where b.userstatus is NULL and b.date=? ";
        String sql_7="select count(*) as xxx from users a left join usertimes b on a.username=b.username where b.userstatus is NULL and b.date=?";
        try{
            Class.forName(DBDRIVER);
            cn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
         //   Statement st_0=cn.createStatement();
         	PreparedStatement ps_0=cn.prepareStatement(sql_0);
         	ps_0.setString(1, date);
         	PreparedStatement ps_1=cn.prepareStatement(sql_1);
         	ps_1.setString(1, date);
         	PreparedStatement ps_2=cn.prepareStatement(sql_2);
         	ps_2.setString(1, date);
         	PreparedStatement ps_3=cn.prepareStatement(sql_3);
         	ps_3.setString(1, date);
         	PreparedStatement ps_4=cn.prepareStatement(sql_4);
         	ps_4.setString(1, date);
         	PreparedStatement ps_5=cn.prepareStatement(sql_5);
         	ps_5.setString(1, date);
         	PreparedStatement ps_6=cn.prepareStatement(sql_6);
         	ps_6.setString(1, date);
         	PreparedStatement ps_7=cn.prepareStatement(sql_7);
         	ps_7.setString(1, date);
           /* Statement st_1=cn.createStatement();
            Statement st_2=cn.createStatement();
            Statement st_3=cn.createStatement();
            Statement st_4=cn.createStatement();
            Statement st_5=cn.createStatement();*/
            ResultSet rs_0=ps_0.executeQuery();
           	ResultSet rs_1=ps_1.executeQuery();
            ResultSet rs_2=ps_2.executeQuery();
            ResultSet rs_3=ps_3.executeQuery();
            ResultSet rs_4=ps_4.executeQuery();
            ResultSet rs_5=ps_5.executeQuery();
            ResultSet rs_6=ps_6.executeQuery();
            ResultSet rs_7=ps_7.executeQuery();
           
           while(rs_0.next()){
            	String str=rs_0.getString("count_A");
            	count_a=Integer.valueOf(str);
            }
            
            while(rs_1.next()){
            	UserTime ut=new UserTime();
              /*  String sno=rs.getString("字段1");
                String sname=rs.getString("字段2");
                String sex=rs.getString("字段3");
                java.sql.Date birthday=rs.getDate("字段四");
                String sd=rs.getString("字段5");
               */
               ut.setId(rs_1.getInt("id"));
               ut.setUsername(rs_1.getString("username"));
               ut.setDate(rs_1.getString("date"));
               ut.setTime(rs_1.getString("time"));
               ut.setUserstatus(rs_1.getString("userstatus"));
               userList.add(ut);
            }
            //关闭结果集
         
            
            
            while(rs_2.next()){
            	String str=rs_2.getString("count_C");
            	count_c=Integer.valueOf(str);
            }
           
            while(rs_3.next()){
            	String str=rs_3.getString("count_Z");
            	 count_z=Integer.valueOf(str);
            }
           //关闭结果集
            while(rs_4.next()){
            	UserTime ut=new UserTime();
          
               ut.setId(rs_4.getInt("id"));
               ut.setUsername(rs_4.getString("username"));
               ut.setDate(rs_4.getString("date"));
               ut.setTime(rs_4.getString("time"));
               ut.setUserstatus(rs_4.getString("userstatus"));
               userList_c.add(ut);
            }
            //关闭结果集
            while(rs_5.next()){
            	UserTime ut=new UserTime();
 
               ut.setId(rs_5.getInt("id"));
               ut.setUsername(rs_5.getString("username"));
               ut.setDate(rs_5.getString("date"));
               ut.setTime(rs_5.getString("time"));
               ut.setUserstatus(rs_5.getString("userstatus"));
               userList_z.add(ut);
            }
            while(rs_6.next()){
            	UserTime ut=new UserTime();
 
               ut.setId(rs_6.getInt("id"));
               ut.setUsername(rs_6.getString("username"));
              
               userList_n.add(ut);
            }
            while(rs_7.next()){
             String str=rs_7.getString(1);
           	 count_n=Integer.valueOf(str);
            }
            rs_2.close();//关闭结果集
            rs_3.close();
            rs_5.close();//关闭结果集
            rs_4.close();
            rs_1.close();
            rs_6.close();
            rs_7.close();
            cn.close();//关闭操作
            }
            catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("连接异常");
            ex.printStackTrace();
            }
       	  // UserTimeService dao=new UserTimeServiceImpl();
           
           %> 
                            <!--  --><div class="block-content collapse in">
                                <div class="span3">
                                    <div class="chart" data-percent="<%= count_a %>"><%= count_a %></div>
                                    <div class="chart-bottom-heading"><span class="label label-info">签到总人数</span>

                                    </div>
                                </div>
                                <div class="span3">
                                   
                                     <div class="chart" data-percent="<%= count_n %>"><%= count_n %></div>
                                     <div class="chart-bottom-heading"><span class="label label-info">缺勤</span></div>
                                </div>
                                <div class="span3">
                                    <div class="chart" data-percent="<%= count_c %>"><%= count_c %></div>
                                    <div class="chart-bottom-heading"><span class="label label-info">迟到</span>
                                    </div>
                                </div>
                                <div class="span3">
                                    <div class="chart" data-percent="<%= count_z %>"><%= count_z %></div>
                                    <div class="chart-bottom-heading"><span class="label label-info">正常</span>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">正常</div>
                                    <div class="pull-right"><span class="badge badge-info"><%=date %></span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>用户名</th>
                                                <th>签到日期</th>
                                                <th>签到时间</th>
                                                
                                            </tr>
                                        </thead>
                                         
                                        <tbody>
                                        <%for(UserTime ul:userList_z)
                                        	 {%>
                                            <tr>
                                                <td><%=ul.getId() %></td>
                                                <td><%=ul.getUsername() %></td>
                                                <td><%=ul.getDate()%></td>
                                                <td><%=ul.getTime() %></td>
                                              
                                        
                                            </tr>
                                            <%} %>
                                        </tbody>
                                        
                                    </table>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                      
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">签到名单</div>
                                    <div class="pull-right"><span class="badge badge-info"><%=date %></span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>用户名</th>
                                                 <th>签到日期</th>
                                                <th>签到时间</th>
                                                 <th>签到状态</th>
                                                
                                            </tr>
                                        </thead>
                                         
                                        <tbody>
                                        <%for(UserTime ul:userList)
                                        	 {%>
                                            <tr>
                                                <td><%=ul.getId() %></td>
                                                <td><%=ul.getUsername() %></td>
                                                <td><%=ul.getDate() %></td>
                                                <td><%=ul.getTime() %></td>
                                                <td><%=ul.getUserstatus() %></td>
                                            </tr>
                                            <%} %>
                                        </tbody>
                                        
                                    </table>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
								<div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">迟到</div>
                                    <div class="pull-right"><span class="badge badge-info"><%=date %></span>
                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                         <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>用户名</th>
                                                 <th>签到日期</th>
                                                <th>签到时间</th>
                                                
                                            </tr>
                                        </thead>
                                         
                                        <tbody>
                                        <%for(UserTime ul:userList_c)
                                        	 {%>
                                            <tr>
                                                <td><%=ul.getId() %></td>
                                                <td><%=ul.getUsername() %></td>
                                                <td><%=ul.getDate()%></td>
                                                <td><%=ul.getTime() %></td>
                                            </tr>
                                            <%} %>
                                        </tbody>
                                        
                                    </table>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
							<!-- ------------------------- -->
							  <div class="span6">
                            <!-- block -->
                            <div class="block">
								<div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">缺勤</div>
                                    <div class="pull-right"><span class="badge badge-info"><%=date %></span>
                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                         <thead>
                                            <tr>
                                                <th>序号</th>
                                               
                                                 <th></th>
                                                  <th>用户名</th>
                                                <th></th>
                                                
                                            </tr>
                                        </thead>
                                         
                                        <tbody>
                                        <%for(UserTime ul:userList_n)
                                        	 {%>
                                            <tr>
                                                <td><%=ul.getId() %></td>
                                               
                                                <td></td>
                                                 <td><%=ul.getUsername() %></td>
                                                <td></td>
                                            </tr>
                                            <%} %>
                                        </tbody>
                                        
                                    </table>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                    </div>
                    <div class="row-fluid">
                        <!-- block -->
                 
                           
                
                        </div>
                        <!-- /block -->
                    </div>
					
				</div>
				</div>
           </div>
           

        <!--/.fluid-container-->
        <script src="vendors/jquery-1.9.1.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="vendors/easypiechart/jquery.easy-pie-chart.js"></script>
        <script src="assets/scripts.js"></script>
        <script>
        $(function() {
            // Easy pie charts
            $('.chart').easyPieChart({animate: 1000});
        });
        </script>
    </body>
</div>
</html>