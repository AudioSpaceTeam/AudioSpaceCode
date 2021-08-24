//package com.audiospace.demo.services;
//
//import java.io.IOException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.sendgrid.Content;
//import com.sendgrid.Email;
//import com.sendgrid.Mail;
//import com.sendgrid.Method;
//import com.sendgrid.Request;
//import com.sendgrid.Response;
//import com.sendgrid.SendGrid;
//
//
//
//@Service
//public class SendGridService {
//  private static final Logger logger = LoggerFactory.getLogger(SendGridService.class);
//
//  @Value("${SENDGRID_API_KEY}")
//  private String keySG;
//
//  public String prepareAndSend(String emailTo, String emailSubject, String emailContent) throws IOException {
//    // the sender email should be the same as we used to Create a Single Sender Verification
//    Email from = new Email("geronimo52@gkworkoutq.com");
//    String subject = emailSubject;
//    Email to = new Email(emailTo);
//    Content content = new Content("text/plain", emailContent);
//    Mail mail = new Mail(from, subject, to, content);
//
//    SendGrid sg = new SendGrid(keySG);
//    Request request = new Request();
//    try {
//      request.setMethod(Method.POST);
//      request.setEndpoint("mail/send");
//      request.setBody(mail.build());
//      Response response = sg.api(request);
//      logger.info(response.getBody());
//      return response.getBody();
//    } catch (IOException ex) {
//      throw ex;
//    }
//  }
//}
