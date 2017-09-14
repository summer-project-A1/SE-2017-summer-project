<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Release Book</title>

    <style>
#wrapper {
    background-color: #5D4B33;
    margin-right:70%;
}
@media ( min-width :768px) {
    .sidebar {
        z-index: 1;
        position: absolute;
        width:100%;
        margin-top: 51px;
        background-color: #5D4B33;
    }
}
#bookinfo
{
    margin-top: 51px;
    margin-left:20%;
    margin-right:20%;
}
.form-control-noNewline {
    width:140px;
    display:inline;
}
.form-horizontal .form-group-auto {
  margin-right: 0px;
  margin-left: 0px;
}

    </style>
</head>
<body>
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->
<div id=bookinfo>
    <form id="form" action="<%=path%>/bookAction/uploadBook" method=post enctype="multipart/form-data" role="form" class="form-horizontal" accept-charset="UTF-8">
        <h3 style="text-align: center;">发布图书</h3>
        <div class="form-group form-group-auto">
            <label>书名</label><font color="#FF0000">*</font><input id="bookName" name="bookProfile.bookName" type="text" class="form-control">
        </div>
        <div class="form-group form-group-auto">
            <label>作者</label><font color="#FF0000">*</font><input id="author" name="bookProfile.author" type="text" class="form-control">
        </div>
        <div class="form-group form-group-auto">
            <label>ISBN</label><font color="#FF0000">*</font>(输入isbn号可自动获取部分图书信息)<input id="isbn" name="bookProfile.isbn" type="text" class="form-control">
        </div>
        <div class="form-group form-group-auto">
            <label>出版社</label><font color="#FF0000">*</font><input id="press" name=bookProfile.press type="text" class="form-control">
        </div>
        <div class="form-group form-group-auto">
            <label>出版时间</label><font color="#FF0000">*</font>&nbsp;
            <input name="bookProfile.publishYear" type="number" min="0" step="1" class="form-control form-control-noNewline">&nbsp;<label>年</label>&nbsp;
            <input name="bookProfile.publishMonth" type="number" min="1" max="12" step="1" class="form-control form-control-noNewline">&nbsp;<label>月</label>
        </div>
        <div class="form-group form-group-auto">
            <label>版次</label><font color="#FF0000">*</font>&nbsp;
            <input name="bookProfile.editionYear" type="number" min="0" step="1" class="form-control form-control-noNewline">&nbsp;<label>年</label>&nbsp;
            <input name="bookProfile.editionMonth" type="number" min="1" max="12" step="1" class="form-control form-control-noNewline">&nbsp;<label>月</label>&nbsp;
            <label>第</label>&nbsp;<input name="bookProfile.editionVersion" type="number" min="1" step="1" class="form-control form-control-noNewline">&nbsp;<label>版</label>
        </div>
        <div class="form-group form-group-auto">
            <label>类别</label><font color="#FF0000">*</font>&nbsp;
            <select id="cate" name="bookProfile.category1" class="form-control form-control-noNewline">
                <s:iterator value="#category1List">
                    <option value="<s:property value="category1Name"/>"><s:property value="category1Name"/></option>
                </s:iterator>
            </select>&nbsp;
            <select id="category" name="bookProfile.category2" class="form-control form-control-noNewline">
                <s:iterator value="#category1List" begin="0" end="0">
                <s:iterator value="category2List">
                    <option value="<s:property value="category2Name"/>"><s:property value="category2Name"/></option>
                </s:iterator>
                </s:iterator>

                </select>

        </div>
        <div class="form-group form-group-auto">
            <label>页数</label><font color="#FF0000">*</font>&nbsp;
            <input id="pages" name="bookProfile.page" type="number" step="50" min="0" class="form-control form-control-noNewline">&nbsp;&nbsp;&nbsp;
            <label>装帧</label><font color="#FF0000">*</font>&nbsp;
            <select name="bookProfile.bookBinding" class="form-control form-control-noNewline">
                <option value="0">线装</option>
                <option value="1">平装</option>
                <option value="2">精装</option>
            </select>&nbsp;&nbsp;&nbsp;
            <label>开本</label><font color="#FF0000">*</font>&nbsp;
            <select name="bookProfile.bookFormat" class="form-control form-control-noNewline">
                <option value="0">正度</option>
                <option value="1">对开</option>
                <option value="2">4开</option>
                <option value="3">8开</option>
                <option value="4">16开</option>
                <option value="5">32开</option>
            </select>&nbsp;&nbsp;&nbsp;
            <label>成色</label><font color="#FF0000">*</font>&nbsp;
            <select name="bookProfile.bookQuality" class="form-control form-control-noNewline">
                <option value="10">全新</option>
                <option value="9">9成新</option>
                <option value="7">7成新</option>
                <option value="5">5成新</option>
                <option value="3">3成新</option>
                <option value="1">1成新</option>
            </select>
        </div>
        <div class="form-group form-group-auto">
            <label>是否可交换</label><font color="#FF0000">*</font>&nbsp;
            <input id="ce1" type="radio" name="bookProfile.canExchange" value="1"><label>是</label>&nbsp;
            <input id="ce0" type="radio" name="bookProfile.canExchange" value="0"><label>否</label>&nbsp;&nbsp;&nbsp;
            <div id="exchangeCredit" style="display: none">
                <label>购买所需积分</label><font color="#FF0000">*</font>&nbsp;
                <input name="bookProfile.buyCredit" type="number" step="1" min="0" class="form-control form-control-noNewline">&nbsp;&nbsp;&nbsp;
            </div>
        </div>
        <div class="form-group form-group-auto">
            <label>是否可借阅</label><font color="#FF0000">*</font>&nbsp;
            <input id="cb1" type="radio" name="bookProfile.canBorrow" value="1"><label>是</label>&nbsp;
            <input id="cb0" type="radio" name="bookProfile.canBorrow" value="0"><label>否</label>&nbsp;&nbsp;&nbsp;
            <div id="borrowCredit" style="display:none;">
                <label>借阅所需积分</label><font color="#FF0000">*</font>&nbsp;
                <input type="number" step="1" min="0" name="bookProfile.borrowCredit" class="form-control form-control-noNewline">
            </div>
        </div>
        <div class="form-group form-group-auto">
            <label>简介</label><font color="#FF0000">*</font><textarea id="intro" name="bookProfile.intro" class="form-control" rows="3"></textarea>
        </div>
        <div class="form-group form-group-auto">
            <label>图书封面</label><font color="#FF0000">*</font><input name="bookProfile.coverPicture" type="file" accept="image">
        </div>
        <div class="form-group form-group-auto">
            <label>其他图片（一）</label><font color="#FF0000">*</font><input name="bookProfile.otherPicture"  type="file" accept="image" class="file">
        </div>
        <div class="form-group form-group-auto">
            <label>其他图片（二）</label><font color="#FF0000">*</font><input name="bookProfile.otherPicture"  type="file" accept=image class="file">
        </div>

        <div class="form-group form-group-auto">
            <label id=warning></label>
        </div>
        <div class="clearfix"> </div>
        <div id=confirm class="register-but">
            <input type="button" id=commit value="确认发布">
        </div>
        <div class="clearfix"> </div>
    </form>

</div>






    

    <script src="<%=path%>/js/fileinput.js"></script>
    <script src="<%=path%>/js/fileinput.min.js"></script>
    <script src="<%=path%>/js/zh.min.js"></script>
    <script>
    $("#cate").change(function(){
        $("#category").empty();
        switch ($("#cate").val())
        {
            <s:iterator value="#category1List">
            case '<s:property value="category1Name"/>':
                <s:iterator value="category2List">
                $("#category").append($("<option>").val("<s:property value='category2Name'/>").text("<s:property value='category2Name'/>"));
            </s:iterator>
                break;
            </s:iterator>
        }
    });
    $("#isbn").focus();
    $("#isbn").blur(function(){
       var isbn = $("#isbn").val();
       if(isbn.length == 13){
           $.ajax({
               url:'<%=path%>/bookAction/getInfoByIsbn',
               type:'POST',
               data:{'isbn':isbn},
               success:function(msg){
                   if(msg.success){
                       var title = msg.title;
                       var author = msg.author;
                       var press = msg.publisher;
                       var intro = msg.summary;
                       var page = msg.pages;
                       $("#bookName").val(title);
                       $("#author").val(author);
                       $("#press").val(press);
                       $("#intro").val(intro);
                       $("#pages").val(page);
                   }
               },
           });
       }
    });
    $("#cb1").click(function(){
        $("#borrowCredit").show();
    });
    $("#cb0").click(function(){
        $("#borrowCredit").hide();
    });
    $("#ce1").click(function(){
        $("#exchangeCredit").show();
    });
    $("#ce0").click(function(){
        $("#exchangeCredit").hide();
    });
    $("input[name='bookProfile.coverPicture']").fileinput({
        showUpload : false,
        allowedFileExtensions: ['jpg','jpeg','png','gif','bmp'],
        browseLabel : "浏览",
        language : 'zh'
    });
    $("input[name='bookProfile.otherPicture']").fileinput({
        showUpload : false,
        allowedFileExtensions: ['jpg','jpeg','png','gif','bmp'],
        browseLabel : "浏览",
        language : 'zh'
    });
    $("#commit").click(function(){
        var obj=$('#bookinfo').find('#warning');
        if($("input[name='bookProfile.coverPicture']").val()=="")
            {obj.html("图书封面必须上传");return;}
        if($("input[name='bookProfile.otherPicture']").val()=="")
            {obj.html("必须上传");return;}
        if($("input[name='bookProfile.bookName']").val()=="")
            {obj.html("图书名称必须填写");return;}
        if($("input[name='bookProfile.author']").val()=="")
            {obj.html("图书作者必须填写");return;}
        if($("input[name='bookProfile.isbn']").val()=="")
            {obj.html("图书ISBN必须填写");return;}
        if($("input[name='bookProfile.press']").val()=="")
            {obj.html("图书出版社必须填写");return;}
        var publishYear=$("input[name='bookProfile.publishYear']").val(); 
        if(publishYear=="")
            {obj.html("图书出版年份必须填写");return;}
        if(!(publishYear>=0))
            {obj.html("出版年份不合法");return;}
        var publishMonth=$("input[name='bookProfile.publishMonth']").val();
        if(publishMonth=="")
            {obj.html("图书出版月份必须填写");return;}
        if(!(publishMonth>=1 && publishMonth<=12))
            {obj.html("出版月份不合法");return;}
        var editionYear=$("input[name='bookProfile.editionYear']").val();
        var editionMonth=$("input[name='bookProfile.editionMonth']").val();
        var editionVersion=$("input[name='bookProfile.editionVersion']").val();
        if($("input[name='bookProfile.editionMonth']").val()==""||$("input[name='bookProfile.editionYear']").val()==""
                ||$("input[name='bookProfile.editionVersion']").val()=="")
            {obj.html("图书版次信息不全");return;}
        if(!(editionYear>=0))
            {obj.html("版次年份不合法");return;}
        if(!(editionMonth>=1 && editionMonth<=12))
            {obj.html("版次月份不合法");return;}
        if(!(editionVersion>=0))
            {obj.html("版次不合法");return;}
        var page=$("input[name='bookProfile.page']").val();
        if(page=="")
            {obj.html("图书页数必须填写");return;}
        if(!(page>=0))
            {obj.html("图书页数不合法");return;}
        if($("input[name='bookProfile.canExchange']:checked").val()=='')
            {obj.html("请确认是否可交换");return;}
        if($("input[name='bookProfile.canExchange']:checked").val()=='1'&&$("input[name='bookProfile.buyCredit']").val()=='')
            {obj.html("请输入购买所需积分");return;}
        if($("input[name='bookProfile.canBorrow']:checked").val()=="")
            {obj.html("请确认是否可借阅");return;}
        if($("input[name='bookProfile.canBorrow']:checked").val()=='1'&&$("input[name='bookProflie.borrowCredit']").val()=='')
            {obj.html("请输入借阅所需积分");return;}
        if($("#introduction").val()=='')
            {obj.html("请输入简介");return;}
        obj.html("提交中...");
        $("#form").submit();
    });
    </script>
    <jsp:include page="footer.jsp"/>
</body>
</html>

