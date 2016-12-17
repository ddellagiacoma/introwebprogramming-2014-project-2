<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='bootstrap.css' rel='stylesheet'>
        <link href='impostadmin.css' rel='stylesheet'>
        <link href='jquery-2.0.3.min' rel='stylesheet'>
    </head>
    <body>
         <c:set var="idgroup" value="${param.idgroup}"/>
        <c:set var="iduser" value="${param.iduser}"/>
        <div class='container'>
           
            <c:if test="${param.pubblico==false}">
<h1 class='sottotitoli'>Utenti presenti nel gruppo</h1><br>
<table border='0' class='table table-condensed table-hover'>
             <c:forEach var="utenti" items="${utenti}">   
    <tr><td align='center'>
            <c:out value="${utenti.name}"/>
            </td><td align='center'>
                <form action='RemoveUser?idgroup=${idgroup}&ideliminato=${utenti.id}&iduser=${iduser}'method='POST'>
             <input class='btn btn-lg btn-block' type='submit' value='Elimina dal gruppo' name='BtnEliminadagruppo'></form>
            </td></tr>    
            </c:forEach>
    </table>

<h2 class='sottotitoli'>Invita un nuovo utente</h2><br>
<table border='0' class='table table-condensed table-hover'>

<c:forEach var="utentiinviti" items="${utentiinviti}">  
    <tr><td align='center'>
         <c:out value="${utentiinviti.name}"/> 
         </td><td align='center'>
             <form action='InviteUser?idgroup=${idgroup}&iduser=${utentiinviti.id}&idadmin=${iduser}'method='POST'>
                 <input class='btn btn-lg btn-block' type='submit' value='Invita nel gruppo' name='BtnInvita'></form>
         </td></tr>    
         </c:forEach>
</table>
            </c:if>
        <br><br>
        <table border='0'>
            <form action='Rename?idgroup=${idgroup}&iduser=${iduser}'method='POST' >
               <tr><td><h3 class='sottotitoli'>Rinomina il gruppo</h3></td></tr>
               <tr><td>
                       <input type='text' placeholder='Nome del gruppo' autocomplete='Off' required name='InputRename' class='form-control'>
                </td><td>
                   <input type='submit' value='Rinomina' class='btn btn-lg btn-block btn-primary'name='BtnRename'> 
                </td></tr></table></form>
               
                   </div>
                   
               <form action='ChangeGroup?idgroup=${idgroup}'method='POST' class='crea'>
                   <fieldset><legend>Cambia tipologia gruppo</legend>
                       Gruppo pubblico<input type='radio' name='RdGruppo' value='pubblico'/>
                       Gruppo privato<input type='radio' name='RdGruppo' checked='checked' value='privato'/></fieldset>
                 <input type='submit' value='Cambia' name='BtnLog' class='btn btn-lg btn-primary btn-block'></form>  
               
                 <form action='ViewGroup?idgroup=${idgroup}&iduser=${iduser}'method='POST'>
                   <input type='submit' class='btn btn-lg btn-primary' value='Torna al gruppo' name='BtnTOrnagruppo' class='spaziosotto'></form>
    </body>
</html>