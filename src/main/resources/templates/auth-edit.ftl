<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户数据修改子页面</title>
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
</head>
<body>
<form class="layui-form" method="post" style="margin-top: 20px">
    <input type="hidden" name="id" id="hidId" value="${(userAuth.id)!}">
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-block">
            <input id="auth-username" type="text" name="title" required  lay-verify="required" placeholder="请输入登录名" autocomplete="off" class="layui-input" value="${(userAuth.username)!""}">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input id="auth-password" type="text" name="title" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${(userAuth.password)!""}">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">管理员</label>
        <div class="layui-input-block">
            <input id="auth-isAdmin" type="checkbox" lay-filter="insert_switch" lay-skin="switch" name="close" lay-text="是|否" <#if userAuth.isAdmin == true>checked</#if>>
        </div>
    </div>

    <div class="layui-form-item" style="padding-right: 40px; width: 300px; margin: 0 auto; margin-top: 40px">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        var callbackData;
        var layer = layui.layer;
        console.log("/user_auth/update?id="+$('[name="id"]').val())
        //监听提交
        form.on('submit(formDemo)', function(data){
            $.ajax({
                url: "/user_auth/update?id="+$('[name="id"]').val(),
                method: 'get',
                data: {
                    username: $("#auth-username").val(),
                    password: $("#auth-password").val(),
                    isAdmin: $("#auth-isAdmin").prop("checked"),

                },
                success: function (result) {
                    if (result.code == 200) {
                        callbackData = {
                            username: $("#auth-username").val(),
                            password: $("#auth-password").val(),
                            isAdmin: $("#auth-isAdmin").prop("checked"),

                        }
                        //parent.layerCallback(callbackData);
                        parent.layer.msg('用户修改成功', {icon: 1});
                        var index = parent.layer.getFrameIndex(window.name);
                        console.log("frameIndex");
                        console.log(index);
                        // parent.layer.close(index);
                        //刷新父页面，将添加的新数据展示
                        //window.location.href = "/user_auth/auth";
                        parent.location.reload();
                        // layer.closeAll("iframe");
                    }else if(result.msg =="该角色已存在!"){
                        parent.layer.msg('该角色已存在!', {icon: 2});
                    }
                    else {
                        parent.layer.msg('用户修改失败', {icon: 2});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>