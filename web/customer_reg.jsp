<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
    <style type="text/css">
        table {

            border-collapse: collapse;
        }

        .boder {
            border: 1px solid #5B96D0;
        }

        .col1 {
            background-color: #A6D2FF;
            text-align: right;
            padding-right: 10px;
            border: 1px solid #5B96D0;
            line-height: 50px;
        }

        .col2 {
            padding-left: 10px;
            border: 1px solid #5B96D0;
            line-height: 50px;
        }

        .textfield {
            height: 20px;
            width: 200px;
            border: 1px solid #999999;
            text-align: left;
            font-size: medium;
            line-height: 50px;
        }

    </style>

    <script>

      function verify(myform){
        var errorMsg = "";

        if (myform.userid.value == ""){
          errorMsg += "會員帳號不能為空! \n";
        }

        if (myform.name.value == ""){
          errorMsg += "會員姓名不能為空! \n";
        }

        if (myform.password.value != myform.password2.value){
          errorMsg += "密碼不一致! \n";
        }

        var pattern = /^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$/;

        if (!pattern.test(myform.birthday.value)){
          errorMsg += "出生日期格式有誤! \n";
        }

        if ( errorMsg == ""){
          return true;
        }else{
          alert(errorMsg);
          return false;
        }
      }

    </script>
</head>


<body>
<div align="center"><h1>會員註冊</h1></div>
<hr width="100%" />
<div class="text3" align="center">請填寫以下信息</div>
<br>
<ul>
  <c:forEach var="error" items="${errors}">
    <li class="error">${error}</li>
  </c:forEach>
</ul>
<form action="controller" method="post" onsubmit="return verify(this)">
<table width="60%" border="0" align="center" class="boder">
  <tr>
    <td width="35%" height="27" class="col1">會員帳號：</td>
    <td class="col2"><input type="text" name="userid" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">會員姓名：</td>
    <td class="col2"><input type="text" name="name" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">會員密碼：</td>
    <td class="col2"><input type="password" name="password" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">再次輸入密碼：</td>
    <td class="col2"><input type="password" name="password2" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">出生日期：</td>
    <td  class="col2"><input type="text" name="birthday" />*
      格式（YYYY-MM-DD）</td>
  </tr>
  <tr>
    <td height="27" class="col1">會員地址：</td>
    <td class="col2"><input type="text" name="address"  /></td>
  </tr>
  <tr>
    <td height="27" class="col1">會員電話：</td>
    <td class="col2"><input type="text" name="phone" /></td>
  </tr>
</table>
<br>
<div align="center">
  <input type="image" src="images/submit_button.jpg" />
</div>
<br>
  <input type="hidden" name="action" value="reg" >
</form>
<%@include file="footer.jsp" %>


</body>
</html>

