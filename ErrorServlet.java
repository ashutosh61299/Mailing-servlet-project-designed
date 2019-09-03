import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ErrorServlet extends HttpServlet{

public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{

res.setContentType("text/html");
PrintWriter out=res.getWriter();
out.println("<html><head>");
	out.println("<title>Error!!</title>");
	out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
out.println("</head>");
out.println("<body>");
out.println("<h1 class='cent'>ERROR!!!<h1>");
out.println("<br>");
out.println("Redirecting to Mailing page in 5 seconds...");
res.setHeader("Refresh","5;index.html");
out.println("</body></html>");


}




}