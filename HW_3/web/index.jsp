<%--
  Created by IntelliJ IDEA.
  User: yuliya
  Date: 12.10.19
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>File downloader</title>
  </head>
  <body>
  <h1>File downloader</h1>
  <p>Enter url to file</p>
  <form action="download" method="post">
    <input type="text" name="args">
    <input id="send" type="submit">
  </form>
  </body>
</html>
