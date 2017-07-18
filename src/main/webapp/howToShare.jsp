<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>

<html>
<head>

    <title>How To Share</title>
</head>
<body>
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->

<div class="contact">
    <div class="container">
        <h2>图书分享教程</h2>
        <div class="contact-infom">
            <h4>简介：</h4>
            <p>     我们每个人都有一些书，多的数百本，少的也有几本，大多读完之后闲置在一边。如果能够和朋友相互借阅，最大限度地利用这些图书资源就好了；
                有些书籍已不可能再看，如果能与其他同样爱读书的朋友交换，既省去了昂贵的购书成本，又能让自己爱书的价值延续下去那该多好；
                有时读完一本好书，很想和朋友分享自己的感受，或者向朋友推荐一些好书；
                目前，有很多图书借阅管理系统、图书交换平台等，但大多只能满足以上部分需要。
                因此，为了便于朋友间进行图书相互借阅、相互交换、相互推荐、相互分享等，我们希望开发一个图书分享交流平台(网站)，该平台具体功能要求如下。
                此题目有实际用户需求，希望在系统设计与开发过程中，注意系统的可扩展性及兼容性，便于增加或修改功能。
                同学们也可以发挥主观能动性，增加认为合理必要的功能，对于目前题目中提出的功能若有疑问，可以在与助教及老师讨论后适当变更。
                （可参考<a href="http://www.huanshuwang.com/">换书网</a>及其他图书借阅、交换、管理等软件。）
            </p>
        </div>
    </div>
</div>
<!--//contact-->

<!-- footer -->
<jsp:include page="footer.jsp"/>

</body>
</html>
