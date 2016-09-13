package thk.mvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class ThkServlet extends HttpServlet
{
	
	private Map map = new HashMap();
	
	public void init(ServletConfig config)
	{
		//properties 파일 읽어들임
		String propertiesPath = "C:\\Java\\thk\\WebContent\\WEB-INF\\properties\\action.properties";
		Properties pr = new Properties(); //util package
		FileInputStream f = null;
		
		try
		{
			f = new FileInputStream(propertiesPath);
			
			pr.load(f); //load method : properties 파일의 내용을 key와 value로 변환하는 작업(map)
		}
		catch(Exception e){}
		finally
		{
			if(f != null) try{ f.close(); } catch(IOException io){}
		}
		
		Iterator keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext())
		{
			String key = (String)keyIter.next();
			String className = pr.getProperty(key);
			
			try
			{
				Class cla = Class.forName(className);
				Object instance = cla.newInstance();
				
				map.put(key, instance);
			}
			catch(Exception e){}
		}
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		String saw = "";
		SuperAction action = null;
		
		try
		{
			String uri = request.getRequestURI();
			
			action = (SuperAction)map.get(uri);
			saw = action.excuteAction(request, response);
			
			RequestDispatcher rd = request.getRequestDispatcher(saw);
			
			rd.forward(request, response);
		}
		catch(Exception e){}
	}
}
