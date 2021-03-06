<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <script src="<%=path%>/js/jquery.cityselect.js"></script>
    <script src="<%=path%>/js/fileinput.js"></script>
    <script src="<%=path%>/js/fileinput.min.js"></script>
    <script src="<%=path%>/js/zh.min.js"></script>
    <title>User Profile</title>
    <script>
        $(document).ready(function(){
            $(function() {
                $("#city_4").citySelect({
                    prov: "<s:property value='#userProfile.province'/>",
                    city: "<s:property value='#userProfile.city'/>",
                    dist: "<s:property value='#userProfile.district'/>",
                    nodata: "none",
                    required:false
                });
            });

            $('#gender').ready(function () {
                $("#gender").val('<s:property value="#userProfile.gender"/>');

            });

            $(".products .container form").hide();
            $("#update-userProfile-form").show();


            $("#current-tab ul").click(function(){
                $("#current-tab .single-bottom").slideToggle(300);
            });





            $("#update-userProfile").click(function(){
                $(".products .container form").hide();
                $("#update-userProfile-form").show();

            });

            $("#update-password").click(function(){
                $(".products .container form").hide();
                $("#update-password-form").show();
            });


            $("#update-userPicture").click(function(){
                $(".products .container form").hide();
                $("#update-userPicture-form").show();

            });



        <!--旧密码验证-->
            $("#update_oldPassword").focus();
            $("#update_oldPassword").keyup(function(){
            var password = $("#update_oldPassword").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status1").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status1").html("<span style='color:green'>密码格式正确</span>");
            }
            });


            $("#update_oldPassword").blur(function(){
            var password = $("#update_oldPassword").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status1").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status1").html("<span style='color:green'>密码格式正确</span>");
            }
            });

            $("#userPicture").fileinput({
                showUpload : false,
                allowedFileExtensions: ['jpg','jpeg','png','gif','bmp'],
                browseLabel : "浏览",
                language : 'zh'
            });

        <!--新密码验证-->
            $("#update_newPassword").focus();
            $("#update_newPassword").keyup(function(){
            var password = $("#update_newPassword").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status2").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status2").html("<span style='color:green'>密码格式正确</span>");
            }
            });



            $("#update_newPassword").blur(function(){
            var password = $("#update_newPassword").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status2").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status2").html("<span style='color:green'>密码格式正确</span>");
            }
            });

        <!--新密码确认-->
            $("#update_confirmNewPassword").focus();
            $("#update_confirmNewPassword").keyup(function(){
            var confirmpassword = $("#register_confirmpassword").val();
            var password = $("#register_password").val();
            if(confirmpassword != password){
                $("#available_status3").html("<span style='color:red'>两次密码不一致</span>");
            }else{
                $("#available_status3").html("<span></span>");
            }
            });

            $("#update_confirmNewPassword").blur(function(){
            var confirmpassword = $("#update_confirmNewPassword").val();
            var password = $("#update_newPassword").val();
            if(confirmpassword != password){
                $("#available_status3").html("<span style='color:red'>两次密码不一致</span>");
            }else{
                $("#available_status3").html("<span></span>");
            }
            });



        });




        function updatePassword() {
            var confirmpassword = $("#update_confirmNewPassword").val();
            var password = $("#update_newPassword").val();
            if(password.length < 6 || password.length > 12 || confirmpassword != password){
                showTip('修改密码失败','danger');
            }
            else {
                $.ajax({
                    url: "<%=path%>/userAction/updatePassword",
                    type: "post",
                    data: $("#update-password-form").serialize(),
                    success: function (msg) {
                        if (msg.success) {
                            showTip('修改密码成功,请重新登录', 'success');
                            window.setTimeout("window.location='<%=path%>/authAction/logout'",1500);
                        }
                        else {
                            showTip('修改密码失败', 'danger');
                        }
                    },
                    error: function (xhr, status, error) {
                        alert('status=' + status + ',error=' + error);
                    }
                });
            }

        }

        function updateUserProfile(){
            $.ajax({
                url: "<%=path%>/userAction/updateUserProfile",
                type: "post",
                data: $("#update-userProfile-form").serialize(),
                success: function(msg){
                    if (msg.success) {
                        showTip('修改个人信息成功', 'success');
                    }
                    else {
                        showTip('修改个人信息失败', 'danger');
                    }
                },
                error:function(xhr,status,error){
                    alert('status='+status+',error='+error);
                }
            });

        }

        function updateUserPicture() {
            var formData=new FormData($("#update-userPicture-form")[0]);
            $.ajax({
                url: "<%=path%>/userAction/updateUserPicture",
                type: "post",
                data: formData,
                processData: false,  // 告诉jQuery不要去处理发送的数据
                contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                success: function(msg){
                    if (msg.success) {
                        showTip('修改个人头像成功', 'success');
                        window.setTimeout("window.location='<%=path%>/userAction/showUserProfile'",1500);
                    }
                    else {
                        showTip('修改个人头像失败', 'danger');
                    }
                },
                error:function(xhr,status,error){
                    alert('status='+status+',error='+error);
                }
            });
        }

    </script>
    <style>
        @media ( min-width :768px) {

            .form-control-noNewline {
                width: 100px;
                display: inline;
            }

            .form-horizontal .form-group-auto {
                margin-right: 0px;
                margin-left: 0px;
            }
        }
    </style>




</head>
<body>
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->
<div class="products">
    <div class="container">
        <h2>个人信息</h2>
        <div class="col-md-3 rsiderbar span_1_of_left">
            <section class="sky-form">
                <div class="product_right">
                    <h3 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>操作选单</h3>

                    <div id="current-tab" class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">个人信息</a></li>
                            <li class="by"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></li>
                        </ul>
                        <div class="clearfix"> </div>
                        <div class="single-bottom">
                            <a id="update-userProfile" href="#"><p>修改个人信息</p></a>
                            <a id="update-password" href="#"><p>修改密码</p></a>
                            <a id="update-userPicture" href="#"><p>修改头像</p></a>
                        </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/userAction/showSellerCenter">卖家中心</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/bookAction/showUserReleasedBooks">我的发布</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/borrowAction/showMyBorrow">我的借阅</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/exchangeAction/showMyExchange">我的交换</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/orderAction/showMyOrder">我的购买</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="clearfix"> </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/reserveAction/showMyReservation">我的预约</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </section>
        </div>
        <div class="col-md-9 product-model-sec">

        <div id="tip"></div>
        <form id="update-password-form" enctype="multipart/form-data" role="form" class="form-horizontal" accept-charset="UTF-8">
            <h3>密码修改</h3>
            <div class="form-group form-group-auto">
                <label>输入旧密码</label>
                <input type="password" name="oldPassword" class="form-control" id="update_oldPassword"><div id="available_status1"></div>
            </div>
            <div class="form-group form-group-auto">
                <label>输入新密码</label>
                <input type="password" name="newPassword" class="form-control" id="update_newPassword"><div id="available_status2"></div>
            </div>
            <div class="form-group form-group-auto">
                <label>确认新密码</label>
                <input type="password" name="confirmNewPassword" class="form-control" id="update_confirmNewPassword"><div id="available_status3"></div>
            </div>
            <div class="clearfix"> </div>
            <a href="#" class="add-cart item_add" onclick="updatePassword()">修改密码</a>
        </form>
        <form id="update-userProfile-form" enctype="multipart/form-data" role="form" class="form-horizontal"accept-charset="UTF-8">
            <h3>修改个人信息</h3>
            <div class="form-group form-group-auto">
                <label>昵称</label>
                <input type="text" name="userProfile.nickName" value="<s:property value="#userProfile.nickName"/>" class="form-control" id="update_nickName">
            </div>
            <div class="form-group form-group-auto">
                <label>性别</label>
                <select id="gender" name="userProfile.gender" class="form-control form-control-noNewline" >
                    <option>请选择</option>
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>
            <div class="form-group form-group-auto">
                <label>手机</label>
                <input type="text" name="userProfile.mobile" value="<s:property value="#userProfile.mobile"/>" class="form-control" id="mobile"><div id="available_status4"></div>
            </div>
            <label>省市地区</label>
            <div class="form-group form-group-auto" id="city_4">
                <select class="prov form-control form-control-noNewline" id="province" name="userProfile.province"  style="width:auto"></select>
                <select class="city form-control form-control-noNewline"  disabled="disabled" id="city" name="userProfile.city"  style="width:auto"></select>
                <select class="dist form-control form-control-noNewline" disabled="disabled" id="district" name="userProfile.district" style="width: auto"></select>
            </div>
            <div class="input">
                <label>详细地址</label>
                <input type="text" class="form-control" name="userProfile.address" value="<s:property value="#userProfile.address"/>" id="address">
            </div>
            <div class="clearfix"> </div>
            <a href="#" class="add-cart item_add" onclick="updateUserProfile()">修改个人信息</a>
        </form>
        <form id="update-userPicture-form" enctype="multipart/form-data" role="form" class="form-horizontal" accept-charset="UTF-8">
            <h3>修改个人头像</h3>
            <div class="form-group form-group-auto">
                <label>个人头像</label>
                <img src="<%=path%>/imageAction/showImage?imageID=<s:property value='#userProfile.imageID'/>"  class="img-responsive" alt="">
                <div class="form-group form-group-auto">
                    <label>上传头像</label><input id="userPicture" name="userPicture" type="file" class="file">
                </div>
            </div>
            <div class="clearfix"> </div>
            <a href="#" class="add-cart item_add" onclick="updateUserPicture()">修改个人头像</a>
        </form>
        </div>
    </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>