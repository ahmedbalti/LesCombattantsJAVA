/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;







/**
 *
 * @author JNOUNOU
 */
public class Mailapi1 {
    
    
    
  

         public static void send(String from,String password,String to,String sub,String msg){  
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }



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
//m.setFrom("ahmed.amidou123@gmail.com");
m.setFrom(new InternetAddress("ahmed.amidou123@gmail.com"));
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
