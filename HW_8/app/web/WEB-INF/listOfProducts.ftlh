<#ftl encoding='UTF-8'>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of products</title>
</head>

<body>

<h2 style="text-align: center">List of products</h2>

<ul>
    <#list productsList as product>
        <li>
            <p>${product.name} price = ${product.price}</p>
        </li>
    </#list>
</ul>
</body>
</html>