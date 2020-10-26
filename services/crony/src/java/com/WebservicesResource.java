/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Divya
 */
@Path("webservices")
public class WebservicesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebservicesResource
     */
    public WebservicesResource() {
    }

    /**
     * Retrieves representation of an instance of com.WebservicesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/getJson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getJson() {
        //TODO return proper representation object
        return "abc";
    }

    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getUserData")
    public String getUserData(UserBean obj)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserBean objbean = null;
        ResultModel r=null;
        
        try {
            conn = DBConnection.connect();
            r=new ResultModel();
            pstmt = conn.prepareStatement("select name,password,dateofbirth,email,city,mobilenumber,profileimage from usermaster where username=?");
            pstmt.setString(1,obj.getUsername());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                objbean = new UserBean();
                objbean.setName(rs.getString("name"));
                objbean.setPassword(rs.getString("password"));
                objbean.setDob(rs.getString("dateofbirth"));
                objbean.setMobile(rs.getString("mobilenumber"));
                objbean.setCity(rs.getString("city"));
                objbean.setEmail(rs.getString("email"));
                objbean.setProfileimage(rs.getString("profileimage"));
                
            }

        } catch (Exception e) {
            System.out.println("getDATA " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
         
        if(objbean!=null)
        {
            r.setResult_code(1);
            r.setResult_data(objbean);
            return new Gson().toJson(r);
        }
        r.setResult_code(0);
        return new Gson().toJson(r);
        
    }    
    
    
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public String login(UserBean objbean)
    {
             
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultModel r=null;
        UserBean newbean=null;
        try {
            conn = DBConnection.connect();
            r=new ResultModel();
            pstmt = conn.prepareStatement("select username,password from usermaster where username=?");
            pstmt.setString(1,objbean.getUsername());
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                System.out.println("..............................................."+rs.getString("password")+"  "+objbean.getPassword());
                if (rs.getString("password").equals(objbean.getPassword()))
                {
                newbean = new UserBean();
                newbean.setUsername(rs.getString("username"));
                System.out.println("..........................dddd....................."+rs.getString("username"));
                  r.setResult_code(1);
                  r.setResult_data(newbean);
                 
                  return new Gson().toJson(r);
                }
            }
            else
            {
                r.setResult_code(0);
                r.setResult_data(newbean);
        
        
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
         return new Gson().toJson(r);
    
    }
   
    
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changePassword")
   public String changePassword(UserBean objbean)
    {
        
            Connection conn=null;
	    PreparedStatement pstmt=null;
            ResultSet rs=null;
            ResultModel r=null;
      
	    try
		{
			conn=DBConnection.connect();
                        r=new ResultModel();
                        pstmt = conn.prepareStatement("select password from usermaster where username=?");
                        pstmt.setString(1,objbean.getUsername());
                         rs = pstmt.executeQuery();
                        if (rs.next()) {
                        if (rs.getString("password").equals(objbean.getPassword())) 
                        {
           
                               pstmt=conn.prepareStatement("update usermaster set password=? where username=?");
                               pstmt.setString(1, objbean.getNewpassword());
                               pstmt.setString(2,objbean.getUsername());
                               int a=pstmt.executeUpdate();
                                if(a>0)
                                {
                                    System.out.println("Record updated");
                                    r.setResult_code(1);
                                    r.setMsg("true");
                                    return new Gson().toJson(r);
                                }
                                else
                                {
                                       System.out.println("Not Updated");
                                         r.setResult_code(0);
                                   r.setMsg("false");
                                    
                                }			
	
                               
                        }
                        else
                        {
                              r.setResult_code(0);
                                   r.setMsg("false");
                                    return new Gson().toJson(r);
                        }
                        }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                try
                {
                conn.close();
                pstmt.close();
                
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            
                                    r.setResult_code(0);
                                   r.setMsg("false");
                                    return new Gson().toJson(r);
    }
    
   
   
   
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public String update(UserBean objBean)
    {	     
	    Connection conn=null;
            ResultModel r=null;
	    PreparedStatement pstmt=null;
            
	   
	    try
		{
			conn=DBConnection.connect();
                        r=new ResultModel();
			pstmt=conn.prepareStatement("update usermaster set name=?,email=?,mobilenumber=?,dateofbirth=?,city=? where username=?");
                       // pstmt=conn.prepareStatement("update usermaster set email=?,mobilenumber=?,city=? where username=?");
			pstmt.setString(1,objBean.getName());
			pstmt.setString(2,objBean.getEmail());
                        pstmt.setString(3,objBean.getMobile());
                        pstmt.setString(4,objBean.getDob());
                        pstmt.setString(5,objBean.getCity());
                         pstmt.setString(6,objBean.getUsername());
                         System.out.println(pstmt+"----------------------check dob-------------");
            int a=pstmt.executeUpdate();
            if(a>0)
			{
              System.out.println("Record updated");
              r.setResult_code(1);
              r.setMsg("true");
              return new Gson().toJson(r);
			}
            else
			{
              System.out.println("Not Updated");
               r.setResult_code(0);
              r.setMsg("false");
              
		
			}			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}	
            finally
            {
                try
                {
                conn.close();
                pstmt.close();
                
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
                r.setResult_code(0);
              r.setMsg("false");
              return new Gson().toJson(r);
		
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signUp")
    public String signUp(UserBean objBean)
    {	     
	    Connection conn=null;
	    PreparedStatement pstmt=null;
            ResultModel r=null;
            
      
	    try
		{
			conn=DBConnection.connect();
                        r=new ResultModel();
			pstmt=conn.prepareStatement("insert into usermaster(name,username,email,password,mobilenumber,dateofbirth,city,profileimage) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1,objBean.getName());
			pstmt.setString(2,objBean.getUsername());
                        pstmt.setString(3,objBean.getEmail());
                        pstmt.setString(4,objBean.getPassword());
                        pstmt.setString(5,objBean.getMobile());
                        pstmt.setString(6,objBean.getDob());
                        pstmt.setString(7,objBean.getCity());
                        pstmt.setString(8,objBean.getProfileimage());
                        
            int a=pstmt.executeUpdate();
            if(a>0)
			{
              System.out.println("Record Inserted");
              r.setResult_code(1);
              r.setMsg("true");
              return new Gson().toJson(r);
			}
            else
			{
              System.out.println("Not Inserted");
                r.setResult_code(0);
              r.setMsg("false");
              
	
			}			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}	
            finally
            {
                try
                {
                conn.close();
                pstmt.close();
                
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }

              return new Gson().toJson(r);
	
    }
   
    /**
     * PUT method for updating or creating an instance of WebservicesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
