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
        <title>Danh sách hóa đơn nhập</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/divider-text-middle.css">
        <link rel="stylesheet" href="../assets/css/MainStyle.css">
        <link href="../assets/css/sticky-dark-top-nav-with-dropdown.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/Pretty-Footer.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
         <header>
            <c:if test="${sessionScope.acc.roleac eq 1}">
                <nav class="navbar navbar-light navbar-expand-md navbar-fixed-top navigation-clean-button" style="background-color: rgb(255,255,255);">
                    <div  class="container">
                        <div><a class="navbar-brand" href="#"><span style="color: #002046">DAT THANH</span> </a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button></div>
                        <div
                            class="collapse navbar-collapse" id="navcol-1">
                            <ul class="nav navbar-nav nav-right">
                                <li class="nav-item" role="presentation"><a class="nav-link active" href="/WebManager/home.jsp" style="color: #000000;">Trang chủ</a></li>
                                <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#" style="color: #000000;">Quản lý danh sách</a>
                                    <div class="dropdown-menu" role="menu" style="background-color: rgb(255,255,255);">
                                        <a class="dropdown-item" role="presentation" href="/WebManager/admin/listbuy">Danh sách thiết bị nhập</a>
                                        <a class="dropdown-item" role="presentation" href="/WebManager/admin/listsell">Danh sách thiết bị bán</a>
                                        <a class="dropdown-item" role="presentation"href="/WebManager/admin/listwarehouse">Danh sách thiết bị trong kho</a>
                                        <a class="dropdown-item" role="presentation"href="/WebManager/admin/listbillbuy">Danh sách hóa đơn nhập</a>
                                        <a class="dropdown-item" role="presentation"href="/WebManager/admin/listbillsell">Danh sách hóa đơn bán</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#" style="color: #000000;">Thông tin đối tác</a>
                                    <div class="dropdown-menu" role="menu" style="background-color: rgb(255,255,255);">
                                        <a class="dropdown-item" role="presentation" href="/WebManager/admin/listProvider">Nhà cung cấp</a>
                                        <a class="dropdown-item" role="presentation" href="/WebManager/admin/listbuyer">Người mua hàng</a>
                                    </div>
                                </li>
                                <li class="nav-item" role="presentation"><a class="nav-link" href="/WebManager/admin/listgroup" style="color: #000000;">Tổ NV</a></li>
                                <li class="nav-item" role="presentation"><a class="nav-link" href="/WebManager/admin/request" style="color: #000000;">Yêu cầu</a></li>
                            </ul>
                            <c:if test="${sessionScope.acc != null}">
                                <p class="ml-auto navbar-text actions">Xin chào ${sessionScope.acc.username} 
                                    <a href="../logout" style="color: blue; margin-left: 10px">Đăng xuất</a></p>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </c:if>
        </header>
        <div class="container-fluid">
            <div class="row">
                <div style="text-align:center;" border="2px" class="col-lg-10">
                    <h2 class="divider-style"><span>Danh sách hóa đơn nhập</span></h2>
                </div>
            </div>
            <div>
                <a href="insertbillbuy" role="button" class="btn btn-danger" style="margin-left: 17%;width: 180px;">Thêm hóa đơn</a>
            </div>
            <div>
                <div class="row row-space">
                    <div class="col-lg-2">
                        <form action="listbillbuy" method="GET" id="idpv">
                            <h4>Tìm kiếm</h4>
                            <u>_________________________________</u>
                            <br>
                            Từ ngày
                            <br>
                            <input type="date" name="datefrom" style="width: 220px" min="2000-01-01" max="${datemax}" value="${datefrom}"/>
                            <br>
                            Đến ngày
                            <br>
                            <input type="date" name="dateto" style="width: 220px" min="2000-01-01" max="${datemax}" value="${dateto}"/>
                            <br>
                            Tên nhà cung cấp
                            <br>
                            <select name="providers_id" style="width: 220px">
                                <option value="0">Toàn  bộ nhà cung cấp</option>
                                <c:forEach items="${requestScope.provider}" var="pv">
                                    <option value="${pv.id}" ${pvid == pv.id ? 'selected' : ""}> 
                                        ${pv.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <br>
                            Mã hóa đơn
                            <br>
                            <input style="width: 220px" type="text" name="bill_search" placeholder="Tìm theo mã hóa đơn" value="${seri_search}"/>
                            <br>
                            <input type="submit" value="Tìm" style="margin-top: 5px" />
                        </form>
                    </div>
                    <div class="col-lg-10">
                        <table class="table table-bordered" >
                            <thead>
                                <tr style="background-color: #002046; color: whitesmoke">
                                    <td><h4 style="font-family:Tim New Rone;"><b>Mã hóa đơn</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Ngày nhập</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Tiền nợ</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Tổng tiền hàng</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Tiền tạm ứng</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Số lượng thiết bị</b></h4></td>
                                    <td><h4 style="font-family:Tim New Rone;"><b>Tên nhà cung cấp</b></h4></td>
                                    <td colspan="2"<h4 style="font-family:Tim New Rone;"><b>Thông tin</b></h4></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="${0}"/>
                                <c:forEach items="${requestScope.billbuy}" var="bb">
                                    <tr >
                                        <td>${bb.id}</td>
                                        <td>${bb.dateinput}</td>
                                        <td>${bb.amountowed} vnd</td>
                                        <td>${bb.sum_money} vnd</td>
                                        <td>${bb.sum_money - bb.amountowed} vnd</td>
                                        <c:set var="total" value="${total + bb.amountowed}" />
                                        <td>${bb.quantity}</td>
                                        <td>${bb.prov.name}</td>
                                        <td><a href="listbillbuy/listbuy?bill_search=${bb.id}">Xem</a></td>
                                        <td><a href="listbillbuy/updatebillbuy?bill_id=${bb.id}">Sửa</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                            <tfoot>
                                <tr>
                                    <td style="background-color: #002046; color: whitesmoke" colspan="2">Tổng nợ</td>
                                    <td colspan="7" >${total} vnd</td>
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
