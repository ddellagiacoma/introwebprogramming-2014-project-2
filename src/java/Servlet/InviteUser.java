package Servlet;

import DB.DBManager;
import java.io.IOException;
import java.security.Security;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

public class InviteUser extends HttpServlet {

    int idutente, idgroup, admin;
    String email, percorso, nomegruppo, nomeamministratore;
    DBManager manager;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AddressException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
        idutente = Integer.parseInt(request.getParameter("iduser"));
        idgroup = Integer.parseInt(request.getParameter("idgroup"));
        admin = Integer.parseInt(request.getParameter("idadmin"));
        email = manager.emailutente(idutente);
        nomegruppo=manager.prendinome(idgroup);
        nomeamministratore=manager.trovanomepost(admin);
        //trovare percorso
percorso = request.getScheme()
      + "://"
      + request.getServerName()
      + ":"
      + request.getServerPort()
      
      ;
        inviamail(email, idutente,idgroup, nomegruppo,percorso,nomeamministratore);
        manager.invitautente(idgroup, idutente);
        // la successiva pagina visualizzata è quella delle impostazioni dell'admin
        response.sendRedirect("AdminSetting?idgroup=" + idgroup + "&iduser=" + admin);
    }
    public void inviamail(String email, int idutente, int idgroup, String nomegruppo,String percorso, String nomeamministratore) throws MessagingException {
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
       
        msg.setSubject("Sei stato invitato nel gruppo "+nomegruppo);
        
        //aggiungere testo
        msg.setContent("<b>"+nomeamministratore+"</b> ti ha invitato a entrare nel gruppo <b>"+nomegruppo+"</b>. <br><br>Se vuoi accettare l'invito <a href='"+percorso+"/Progetto2k/InviteAccepted?idgroup="+idgroup+"&iduser="+idutente+"'>clicca qui</a>.","text/html; charset=utf-8");
        msg.setSentDate(Calendar.getInstance().getTime());
        Transport.send(msg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (AddressException ex) {
            Logger.getLogger(InviteUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(InviteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (AddressException ex) {
            Logger.getLogger(InviteUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(InviteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}