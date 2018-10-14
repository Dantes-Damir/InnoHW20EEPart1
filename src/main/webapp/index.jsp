<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title</title>
</head>
<body>
<H1>Заголовок страницы</H1>
<p>
    Абзац текста
</p>
<div>
    <a href="/hello">Hello link</a><BR>
    <a href="/iterator">Iterator link</a><BR>
    <a href="/students">Students</a><BR><BR>
    <form method="post" action="/hello">
        <input type="text" name="testText">
        <input type="submit">
    </form>

    <form method="post" action="/save">
        <fieldset>
            <legend>Добавление студента</legend>
            <p>Имя: <input name="name"></p>
            <p>Фамилия: <input name="family"></p>
            <p>Возраст: <input name="age"></p>
            <p>Город: <input name="city"></p>
            <p>Контакт: <input name="contact"></p>
            <p><input type="submit" value="Добавить"></p>
        </fieldset>
    </form>
</div>
</body>
</html>
