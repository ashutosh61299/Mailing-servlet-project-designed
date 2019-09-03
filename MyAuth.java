import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.oreilly.servlet.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MyAuth extends javax.mail.Authenticator{

String from,pass;

MyAuth(String from,String pass){

this.from=from;
this.pass=pass;

}

protected PasswordAuthentication getPasswordAuthentication(){


return new PasswordAuthentication(from,pass);



}



}