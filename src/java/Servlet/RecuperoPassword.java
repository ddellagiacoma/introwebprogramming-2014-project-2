package Servlet;

import DB.DBManager;
import java.io.IOException;
import java.security.Security;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecuperoPassword extends HttpServlet {

    int idutente;
    DBManager manager;
    String email, nomeutente, nuovapassword,percorso;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        nomeutente = request.getParameter("Username");
nuovapassword=request.getParameter("NuovaPassword");
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
        idutente = manager.trovaidutente(nomeutente);
        email = manager.emailutente(idutente);
        manager.aggiornarecuperopassword(idutente);
        
        percorso = request.getScheme()
      + "://"
      + request.getServerName()
      + ":"
      + request.getServerPort();
        
        inviamail(email, idutente, nuovapassword,percorso);
        
        response.sendRedirect("Login.jsp");
    }
    public void inviamail(String email, int idutente, String nuovapassword,String percorso) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("YourEmail", "YourPassword");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("YourEmail"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        msg.setSubject("Questa è la tua nuova password");
       
        
        //aggiungere testo 
        msg.setContent("La tua nuova password è <b>"+nuovapassword+"</b>. <br><br>Per procedere al cambio della password <a href='"+percorso+"/Progetto2k/ChangePasswordMail?iduser="+idutente+"&nuovapassword="+nuovapassword+"'>clicca qui</a> entro 90 secondi dalla richiesta.","text/html; charset=utf-8");
        msg.setSentDate(Calendar.getInstance().getTime());
        Transport.send(msg);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(RecuperoPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(RecuperoPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
