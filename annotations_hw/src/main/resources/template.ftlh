<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Generated Html Form</title>
</head>
<body>
<form action="${form.action}" method="${form.method}">
    <#list form.inputs as input>
        <input type="${input.type}" name="${input.name}" placeholder="${input.placeholder}">
    </#list>
</form>
</body>
</html>