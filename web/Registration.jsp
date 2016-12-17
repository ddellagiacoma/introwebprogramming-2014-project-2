<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link href='bootstrap.css' rel='stylesheet'> 
        <link href='signin.css' rel='stylesheet'>
        <link href='jquery-2.0.3.min' rel='stylesheet'> 
    </head> 
    <body> 
        <form class='form-signin' action='Registration' method='POST'>
            <h2 class='form-signin-heading'>Registrazione</h2> 
            Username: <input name='Inputusername' type='text' class='form-control' placeholder='Username' required autofocus> 
            Password: <input name='InputPassword' type='password' class='form-control' placeholder='Password' required> 
            Conferma Password:<input name='InputConfermaPassword' type='password' class='form-control' placeholder='ConfermaPassword' required>
            E mail:<input name='InputMail' type='email' class='form-control' placeholder='Email' required> 
            <form action='Home.jsp' method='POST'/> 
            <br> 
            <button name='BtnLog' class='btn btn-lg btn-primary btn-block' type='submit'>Registrati</button> 
        </form> 
    </body> 
</html>