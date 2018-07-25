package com.gfbdev.service;


import com.gfbdev.utils.Constants;
import com.sendgrid.*;

import java.io.IOException;


public class EmailService {

    public static com.gfbdev.entity.Response sendEmailNewUser(String email, String message) {
        Email from = new Email("no-reply@watchlist.com");
        String subject = "Account activation";
        Email to = new Email(email);
        Content content = new Content("text/html", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(Constants.EMAIL_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return com.gfbdev.entity.Response.ok(("Email sent"));
        } catch (IOException ex) {
            return com.gfbdev.entity.Response.error(ex.getMessage());
        }
    }
}
