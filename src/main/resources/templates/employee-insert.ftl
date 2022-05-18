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

    <#--当前用户的positionId-->
    <input type="hidden" name="positionId" id="positionId" value="${(employee.positionId)!}">
    <#--当前用户的deptId-->
    <input type="hidden" name="deptId" id="deptId" value="${(employee.deptId)!}">
    <#--当前用户id-->
    <input type="hidden" name="id" id="id" value="${(employee.id)!}">
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">员工姓名</label>
        <div class="layui-input-block">
            <input id="employee-name" type="text" name="title" required  lay-verify="required" placeholder="请输入员工姓名" autocomplete="off" class="layui-input" value="${(employee.name)!}">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="employee-sex" value="1" checked title="男" <#if employee??><#if employee.sex == 1>checked</#if></#if>>
            <input type="radio" name="employee-sex" value="0" title="女" <#if employee??><#if employee.sex == 0>checked</#if></#if>>
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input id="employee-phone" type="text" name="title" required  lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input" value="${(employee.phone)!}">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input id="employee-email" type="text" name="title" required  lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input" value="${(employee.email)!}">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px">
        <label class="layui-form-label">学历</label>
        <div class="layui-input-block">
            <select id="employee-education"  lay-filter="employee-education" >
                <option value="0" <#if employee??><#if employee.education == "0">selected</#if></#if>>本科</option>
                <option value="1" <#if employee??><#if employee.education == "1">selected</#if></#if>>硕士</option>
                <option value="2" <#if employee??><#if employee.education == "2">selected</#if></#if>>博士</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">身份证</label>
        <div class="layui-input-block">
            <input id="employee-idcard" type="text" name="title" required  lay-verify="required" placeholder="请输入身份证" autocomplete="off" class="layui-input" value="${(employee.idcard)!}">
        </div>
    </div>
    <div class="layui-form-item" style="padding-right: 50px; width: 300px; margin: 0 auto; margin-top: 15px ">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input id="employee-address" type="text" name="title" required  lay-verify="required" placeholder="请输入地址" autocomplete="off" class="layui-input" value="${(employee.address)!}">
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



        //加载职位下拉框
        $.post("/emp/queryAllPosition",function (data){
            //获取下拉框
            var am = $("#position-select");
            //获取当前员工的id
            var positionId  = $("#positionId").val();
            console.log(data);
            if (data != null){
                for (var i = 0 ; i < data.length ;i++){
                    if (positionId == data[i].id){
                        //回显数据
                        var opt = "<option selected value="+data[i].id+">"+data[i].name+"</option>";
                    }else {
                        var opt = "<option value="+data[i].id+">"+data[i].name+"</option>";
                    }
                    am.append(opt);
                }
            }
            //重新渲染
            layui.form.render("select");
        })

        //加载部门下拉框
        $.post("/emp/queryAllDept",function (data){
            //获取下拉框
            var am = $("#department-select");
            //获取当前员工的id
            var deptId  = $("#deptId").val();
            console.log(data);
            if (data != null){
                for (var i = 0 ; i < data.length ;i++){
                    if (deptId == data[i].id){
                        //回显数据
                        var opt = "<option selected value="+data[i].id+">"+data[i].name+"</option>";
                    }else {
                        var opt = "<option value="+data[i].id+">"+data[i].name+"</option>";
                    }
                    am.append(opt);
                }
            }
            //重新渲染
            layui.form.render("select");
        })


        //监听提交
        form.on('submit(formDemo)', function(data){
            var url = '/emp/addEmp'

            if ($("#id").val()){
                url = '/emp/updateEmp'
            }
            $.ajax({
                url: url,
                method: 'post',
                data: {
                    id: $("#id").val(),
                    name: $("#employee-name").val(),
                    sex: $('input:radio[name="employee-sex"]:checked').val(),
                    phone: $("#employee-phone").val(),
                    email: $("#employee-email").val(),
                    education: $("#employee-education").val(),
                    idcard: $("#employee-idcard").val(),
                    address: $("#employee-address").val(),
                    positionId: $("#position-select").val(),
                    deptId: $("#department-select").val(),
                },
                dataType : 'json',
                success: function (result) {
                    if (result.code == 200) {
                        callbackData = {
                            username: $("#auth-username").val(),
                            password: $('[name="employee-sex"]').val(),
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

    $("#employee-phone").blur(function (){
        var regPhone = /^((1[3,5,8,7,9][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
        if (!regPhone.test($("#employee-phone").val().trim())){
            $("#employee-phone").val("")
            $("#employee-phone").attr("placeholder","请输入正确的手机号");
        }
    })

    $("#employee-email").blur(function (){
        var regEmail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
        if (!regEmail.test($("#employee-email").val().trim())){
            $("#employee-email").val("")
            $("#employee-email").attr("placeholder","请输入正确的邮箱");
        }
    })

    $("#employee-idcard").blur(function (){
        var regIdcard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!regIdcard.test($("#employee-idcard").val().trim())){
            $("#employee-idcard").val("")
            $("#employee-idcard").attr("placeholder","请输入正确的身份证号");
        }
    })

</script>
</body>
</html>