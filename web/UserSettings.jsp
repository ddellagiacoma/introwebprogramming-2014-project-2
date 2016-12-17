<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <h1>Impostazioni</h1>
        <br> <br> 
        <h2> Cambia password</h2> 
        <c:if test="${param.errore!='null'}">
            <c:out value="${param.errore}"/> 
        </c:if>

        <form action='ChangePassword?iduser=${param.iduser}'method='post'>
            <input name='Inputpasswordvecchia' type='text' class='form-control' placeholder='Vecchia password' required autofocus>
            <br> 
            <input name='ReInputPassword' type='password' class='form-control' placeholder='Nuova password' required>
            <br>
            <input name='InputPassword' type='password' class='form-control' placeholder='Ripeti nuova password' required>
            <br>
            <button name='BtnCambiaPassword' class='btn btn-lg btn-primary btn-block' type='submit'>Cambia</button>
        </form> 
        <br> <br> 
        <h2>Cambia Avatar</h2>
        <form action='UploadAvatar?iduser=${param.iduser}'method='post' class='upload' enctype='multipart/form-data'> 
            <input type='file' class='btn btn-lg btn-primary btn-block' name='avatar'/> <input type='submit' class='btn btn-lg btn-primary btn-block' value='Upload Avatar' />
        </form> 
        <br> <br>
        <a href='Home2?iduser=${param.iduser}' class='btn btn-primary btn-lg' class='spaziosotto'>Home</a>
    </body> 
</html>