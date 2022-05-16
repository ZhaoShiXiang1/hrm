<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工添加修改子页面</title>
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
</head>
<body>
<form class="layui-form" method="post" style="margin-top: 20px">
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">员工姓名</label>
        <div class="layui-input-block">
            <input id="employee-username" type="text" name="title" required  lay-verify="required" placeholder="请输入登录名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="employee-sex" value="0" title="男" checked>
            <input type="radio" name="employee-sex" value="1" title="女">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input id="employee-phone" type="text" name="title" required  lay-verify="required" placeholder="请输入登录名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input id="employee-email" type="text" name="title" required  lay-verify="required" placeholder="请输入登录名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">受教育程度</label>
        <div class="layui-input-block">
            <select id="employee-education" lay-filter="employee-education">
                <option value="0" selected>本科</option>
                <option value="1">硕士</option>
                <option value="2">博士</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">身份证</label>
        <div class="layui-input-block">
            <input id="employee-idcard" type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input id="employee-address" type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">职位编号</label>
        <div class="layui-input-block">
            <select id="position-select" name="position" lay-verify="required">
                <option value="">请选择职位</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">部门编号</label>
        <div class="layui-input-block">
            <select id="department-select" name="department" lay-verify="required">
                <option value="">请选择部门</option>
            </select>
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
        //监听提交
        form.on('submit(formDemo)', function(data){
            $.ajax({
                url: '/user_auth/add',
                method: 'post',
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
                            isAdmin: $("#auth-isAdmin").prop("checked")

                        }
                        // parent.layerCallback(callbackData);
                        parent.layer.msg('添加用户成功', {icon: 1});
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        //刷新父页面，将添加的新数据展示
                        parent.location.reload();
                    } else {
                        parent.layer.msg('添加用户失败', {icon: 2});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>