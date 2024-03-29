<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <script src="static/js/jquery-3.4.1.min.js"></script>
    <script src="static/layui/layui.js"></script>
    <link rel="stylesheet" href="static/layui/css/layui.css" media="all">
</head>


<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin" style="height: 100%">

    <div class="layui-header">
        <div style="padding-left: 5px; width: 600px; height: 60px">
            <!-- 在这里替换具体的LOGO和标语 -->
            <img width="120px" src="static/images/logos/diandian-logo.png" style="float: left; margin-top: 7px">
            <div style="margin-left:20px;float:left;width:300px;height:100%;line-height:60px;text-align:left;color:#009688;font-size:16px;">
                员工后台管理系统
            </div>
            <#--<div style="margin-left:20px;float:left;width:300px;height:100%;line-height:60px;text-align:left;color:#009688;font-size:16px;">SpringBoot+LayUI后台管理系统</div>-->
        </div>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a id="name-a" href="javascript:;">
                    <img src="static/images/icons/diandian-icon.png" class="layui-nav-img">
                    ${(user.username)!"未登陆"}
                </a>
                <#--常坤：无前端页面 不实现了-->
                <#--             <dl class="layui-nav-child">
                                 <dd><a href="">更改密码</a></dd>
                             </dl>-->
            </li>
            <li class="layui-nav-item"><a id="logout-a" href="/">注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">

        <div class="layui-side-scroll">
            <#if user??>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <#if user.isAdmin==true>
                    <li class="layui-nav-item layui-this"><a href="/user_auth/auth" target="admin-list">权限管理</a></li>
                </#if>
                <li class="layui-nav-item"><a href="/dep/management" target="admin-list">部门管理</a></li>
                <li class="layui-nav-item"><a href="/positions/index" target="admin-list">职位管理</a></li>
                <li class="layui-nav-item"><a href="/emp/index" target="admin-list">员工管理</a></li>
                <li class="layui-nav-item"><a href="/posts/index" target="admin-list">公告管理</a></li>
                <li class="layui-nav-item"><a href="files/download" target="admin-list">下载中心</a></li>
            </ul>
            </#if>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 - 局部刷新, 使用iframe进行实现 ;首次刷新时，显示的页面-->
        <div style="padding: 15px; width: 100%; height: 100%">
            <iframe style="width: 100%; height: 100%;" name="admin-list" scrolling="no" src="dep/management" frameborder="0"></iframe>
        </div>
    </div>


</div>

</body>

<script src="static/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['element', 'layer'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            //console.log(elem)
            layer.msg(elem.text());
        });

        $("#logout-a").click(function () {
            $.ajax({
                url: '/user_auth/logout',
                success: function (data) {
                    if (data.code == 200) {
                        window.location.href = "/login";
                    }
                }
            });
        });
    });


</script>
</html>