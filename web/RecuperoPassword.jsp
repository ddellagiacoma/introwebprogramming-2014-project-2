<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>JSP Page</title> 
        <link href='bootstrap.css' rel='stylesheet'> 
        <link href='signin.css' rel='stylesheet'> 
        <link href='jquery-2.0.3.min' rel='stylesheet'> 
    </head>
    <body> 
        <div class='container'> 
            <form class='form-signin' action='RecuperoPassword'> 
                <h2 class='form-signin-heading'>La tua nuova password ti verrà inviata tramite e-mail</h2> 
                <input name='Username' type='text' class='form-control' placeholder='Username' required autofocus> 
                <input name='NuovaPassword' type='password' class='form-control' placeholder='Nuova Password' required> 
                <button name='BtnInvia' class='btn btn-lg btn-primary btn-block' type='submit'>Invia</button> 
            </form> 
        </div> 
    </body> 
</html>