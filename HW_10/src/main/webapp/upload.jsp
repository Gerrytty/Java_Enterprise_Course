<%--
  Created by IntelliJ IDEA.
  User: yuliya
  Date: 14.03.20
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>

    <script>
        function previewFile() {
            var preview = document.getElementById('ava');
            var file    = document.querySelector('input[type=file]').files[0];
            var reader  = new FileReader();
            reader.onloadend = function () {
                preview.src = reader.result;
            };
            if (file) {
                reader.readAsDataURL(file);
            } else {
                preview.src = "";
            }
        }
    </script>

</head>
<body>

<form method="post" action="upload" enctype="multipart/form-data">

    <input type="email" name="email" placeholder="Email...">
    <br/><br/>
    <input type="file" id="file" name="photo" multiple accept="image/*,image/jpeg" onchange="previewFile()">
    <br/><br/>
    <input type="submit" value="Save photo">

</form>

</body>
</html>
