/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class Mailapi {
   private  void sendMail(){
String from,host,sub,content;
from ="ahmed.amidou123@gmail.com";

host ="localhost";
sub="";
content="xxxxxxxxxxx ";   
    Properties p =new Properties();
p.put("mail.smtp.auth","true");
p.put("mail.smtp.starttls.enable","true");
p.put("mail.smtp.host","smtp.gmail.com");
p.put("mail.smtp.port","587");
 Session s =Session.getInstance(p,new  Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication(){
return new PasswordAuthentication("adresse email","mot de ^passe compte email");
}
});
try{
    MimeMessage m =new MimeMessage(s);
m.setFrom("ahmed.amidou123@gmail.com");
m.addRecipient(Message.RecipientType.TO, new InternetAddress("ahmed.balti@esprit.tn"));
m.setSubject(sub);
m.setText(content);
Transport.send(m);
System.out.println("send");
}catch(Exception ex){
    ex.printStackTrace();
}
}
}
