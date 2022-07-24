<%-- 
    Document   : login
    Created on : Mar 10, 2022, 1:17:30 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Untitled</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
        <script>
            window.onload = function () {
                if (document.getElementById("checkacc").value === "false")
                    alert("Tài khoản hoặc mật khẩu sai")
            }
        </script>
    </head>

    <body>
        <div class="login-card" style="background-color: #9ef2ee"><img class="profile-img-card" style="width: 200px;height: 200px" src="assets\img\IMG_4875.PNG">
            <p class="profile-name-card"> </p>
            <form action="login" method="POST" class="form-signin">
                <span class="reauth-email"> </span>
                <input type="hidden" value="${check}" id="checkacc"/>
                <input name="username" class="form-control" type="text" id="inputEmail" required="" placeholder="Tên tài khoản" autofocus="">
                <input name="password" class="form-control" type="password" id="inputPassword" required="" placeholder="Mật khẩu">
                <button class="btn btn-primary btn-block btn-lg btn-signin" type="submit">Đăng nhập</button>
            </form>
          ${thongbao}
                </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
