import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.oreilly.servlet.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailServlet extends HttpServlet{
	PrintWriter out;
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{

	try{
		
	res.setContentType("text/html");
	out=res.getWriter();
	out.println("<html><body>");
	out.println("<head><link rel='stylesheet' type='text/css' href='style.css'></head>");
	String path=getServletContext().getRealPath("Attachment");
	MultipartRequest mpr=new MultipartRequest(req,path,500*1024*1024);
	String att=mpr.getFilesystemName("file");
	
	//out.println("ckpint1");
	String to=mpr.getParameter("to");
	Properties props=new Properties();
	
	props.put("mail.smtp.host","smtp.gmail.com");
	//props.put("mail.smtp.socketFactory.port","587");
		props.put("mail.smtp.socketFactory.port","465");
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth","true");
	//props.put("mail.smtp.port","587");
	props.put("mail.smtp.port","465");
	
	String from=mpr.getParameter("from");
	String pass=mpr.getParameter("pass");
	
	//out.println("ckpint2");
		//Session session=Session.getDefaultInstance(props,new MyAuth(from,pass));
		Session session=Session.getInstance(props, new MyAuth(from,pass));
		//out.println("chkpnt 3");
		
		//composing mail...
		
		MimeMessage message=new MimeMessage(session);
		//message.setFrom(new InternetAddress("ducat2020@gmail.com"));
		//out.println("chkpnt 3.1");
		//out.println(to+":");
		message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		String subject=mpr.getParameter("sub");
		
		message.setSubject(subject);
		//out.println("chkpnt 3.2");
		Multipart body=new MimeMultipart();
		
		MimeBodyPart p1=new MimeBodyPart();
		String b=mpr.getParameter("body");
		//out.println("chkpnt 3.3");
		p1.setText(b);
		body.addBodyPart(p1);
		//out.println("ckpint4");
		
		//out.println("chkpnt 3.4");
		if(att!=null){
		MimeBodyPart p2=new MimeBodyPart();
		String s1=getServletContext().getRealPath("/Attachment/"+att);
		
		FileDataSource fds=new FileDataSource(s1);
		//out.println("chkpnt 3.5");
		p2.setDataHandler(new DataHandler(fds));
		p2.setFileName(fds.getName());
		body.addBodyPart(p2);}
		//out.println("chkpnt4");
		message.setContent(body);
		//out.println("chkpnt4.1");
		//out.println(to+" "+from+" "+pass+" "+subject+" "+b+" "+att);
		
		Transport.send(message);
		
		
		
	
	//out.println("ckpint5");
	

	
  
	
	out.println("<h1 class='cent'>Message succesfully sent!</h1>");
	out.println("Redirecting to Mailing page in 5 seconds...");
	res.setHeader("Refresh","5;index.html");
	
	out.println("</body></html>");
//out.println("ckpint6");
	}catch(Exception ee){
		res.sendRedirect("error");
		
		//out.println("exception");
		//out.println(ee);
	}}

}