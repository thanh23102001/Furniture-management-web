<%-- 
    Document   : buy
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
        <title>Danh sách thiết bị nhập</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="../../assets/css/divider-text-middle.css">
        <link rel="stylesheet" href="../../assets/css/MainStyle.css">
        <link href="../../assets/css/sticky-dark-top-nav-with-dropdown.css" rel="stylesheet" type="text/css"/>
        <link href="../../assets/css/Pretty-Footer.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-light navbar-expand-md navbar-fixed-top navigation-clean-button" style="background-color: rgb(255,255,255);">
                <div class="container">
                  <div><a class="navbar-brand" href="#"><span style="color: #002046">DAT THANH</span> </a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button></div>
                    <div
                        class="collapse navbar-collapse" id="navcol-1">
                        <ul class="nav navbar-nav nav-right">
                            <li class="nav-item" role="presentation"><a class="nav-link active" href="/WebManager/home.jsp" style="color: #000000;">Trang chủ</a></li>
                            <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#" style="color: #000000;">Danh sách</a>
                                <div class="dropdown-menu" role="menu" style="background-color: rgb(255,255,255);">
                                    <a class="dropdown-item" role="presentation" href="../listbuy">Danh sách thiết bị nhập</a>
                                    <a class="dropdown-item" role="presentation" href="../listsell">Danh sách thiết bị bán</a>
                                    <a class="dropdown-item" role="presentation"href="../listwarehouse">Danh sách thiết bị trong kho</a>
                                    <a class="dropdown-item" role="presentation"href="../listbillbuy">Danh sách hóa đơn nhập</a>
                                    <a class="dropdown-item" role="presentation"href="../listbillsell">Danh sách hóa đơn bán</a>
                                </div>
                            </li>
                            <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#" style="color: #000000;">Người mua, bán</a>
                                <div class="dropdown-menu" role="menu" style="background-color: rgb(255,255,255);">
                                    <a class="dropdown-item" role="presentation" href="../listProvider">Danh sách nhà cung cấp</a>
                                    <a class="dropdown-item" role="presentation" href="../listbuyer">Danh sách người mua</a>
                                </div>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../listgroup" style="color: #000000;">Tổ NV</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="../request" style="color: #000000;">Yêu cầu</a></li>
                        </ul>
                        <c:if test="${sessionScope.acc != null}">
                            <p class="ml-auto navbar-text actions">Xin chào, ${sessionScope.acc.username} 
                                <a href="../logout" style="color: blue; margin-left: 10px">Đăng xuất</a></p>
                            </c:if>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container-fluid">
            <div class="row">
                <div style="text-align:center;" border="2px" class="col-lg-10">
                    <h2 class="divider-style"><span>Danh sách thiết bị nhập</span></h2>
                </div>
            </div>
            <div>
                <div class="row row-space">
                    <div class="col-lg-2">
                        <form action="listbuy" method="GET" id="idpv">
                            <h4>Tìm kiếm</h4>
                            <u>_________________________________</u>
                            <br>
                            Mã seri
                            <br>
                            <input style="width: 220px" type="text" name="seri_search" placeholder="Tìm theo mã Seri" value="${seri_search}"/>
                            <br>
                            <input type="hidden" name="bill_search" placeholder="Tìm theo mã Seri" value="${bill_id}"/>
                            <input type="submit" value="Tìm" style="margin-top: 5px" />
                        </form>
                    </div>
                    <div class="col-lg-10">
                        <table class="table table-bordered" >
                            <thead>
                                <tr style="background-color: #002046; color: whitesmoke">
                                    <td><h4 style="font-family:Tim New Rone;"><b>Số seri</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Tên thiết bị</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Phiên bản</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Giá tiền</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Độ mới</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Hạn khấu hao</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Xem chi tiết</b></h4></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="${0}"/>
                                <c:forEach items="${listbuy}" var="b">
                                    <tr >
                                        <td>${b.seri}</td>
                                        <td>${b.name}</td>
                                        <td>${b.model}</td>
                                        <td>${b.price} vnd</td>
                                        <c:set var="total" value="${total + b.price}"/>
                                        <td>${b.newness}</td>
                                        <td>${b.depreciation}</td>
                                        <td><a href="../updatebuy?seri=${b.seri}&bill_search=${bill_id}&action=3" value="${b.seri}">Xem và sửa</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td style="background-color: #002046; color: whitesmoke" colspan="2">Tổng tiền</td>
                                    <td colspan="5" >${total} vnd</td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>  
        </div>
        <footer style="background-color: #002046">
            <div class="row">
                <div class="col-sm-6 col-md-4 footer-navigation">
                    <p class="links"><a href="/projectWeb/index.jsp">TRANG CHỦ</a></p>
                    <p class="company-name">Copyright &copy;2022<a href="#"> DTdeveloper</a>  </p>  </p>
                </div>
                <div class="col-md-4 footer-about">
                    <h6 style="font-weight: bold">ĐỊA CHỈ</h6>
                    <p> 138A Khu đô thị Thăng Long TP Thái Bình tỉnh Thái Bình
                        <br>
                        SDT: 0222 666 222
                        <br>
                        EMAIL: pdthanh@gmail.com
                        <br>
                    </p>
                </div>
                <div class="clearfix"></div>
                 <div class="col-md-4 footer-about">
                    <h4>CÔNG TY NỘI THẤT NHÀ THÔNG MINH DT</h4>
                    <p> ✪ Chuyên bán các loại máy kết hợp nội thất cho nhà ở, chung cư, công ty, trường học
                        <br>
                        ✪ Nhập khẩu và xuất khẩu sang thị trường nước ngoài đã nhiều năm và được đánh giá cao
                        <br>
                        ✪ Với đội ngũ nhân công tay nghề cao, hệ thống máy móc hiện đại, tiên tiến, công ty chúng tôi cam kết đáp ứng được những yêu cầu khắt khe của quý khách hàng và những đơn hàng số lượng lớn trong thời gian ngắn. Sự hài lòng của quý khách là điều chúng tôi luôn hướng tới.
                        <br>
                        Xin chân thành cảm ơn! </p>
                </div>
            </div>
        </footer>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
