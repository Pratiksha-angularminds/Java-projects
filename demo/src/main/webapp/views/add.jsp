<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Two Number</title>
</head>
<body>
<h1 align="center" style="color:red">ADD TWO NUMBER</h1>

<%
   String result=(String)request.getAttribute("result");
%>

<%
    if(result!=null)
    {
%>
       <h3 align="center" style="color:red">Sum of two number is <span style="color:blue"><%=result %></span></h3>
<%
    }
%>
<div align="center">

<form action="AddNoServlet" method="post">
   <table>
       <tr>
           <td>Enter 1st number: </td>
           <td><input type="text" name="no1"></td>
       </tr>
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       .
       
       <tr>
           <td>Enter 2nd number: </td>
           <td><input type="text" name="no2"></td>
       </tr>
       
       <tr>
           <td><input type="submit" value="Add Numbers"></td>
       </tr>
   </table>
</form>

</div>
</body>
</html>