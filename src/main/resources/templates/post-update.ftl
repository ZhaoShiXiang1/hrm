<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公告更新子页面</title>
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
    <script>
        $(function () {
            var parent_json = eval('(' + parent.json + ')');
            $("#post-title").val(parent_json.title);
            $("#post-content").val(parent_json.content);
        });
    </script>
</head>

<form class="layui-form" method="post" style="margin-top: 20px">
    <input type="hidden" name="id" id="hidId" value="${(post.id)!}">
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block" style="width: 340px">
            <input id="post-title" type="text" name="title" required lay-verify="required" placeholder="请输入标题"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text" style="padding-right: 50px">
        <label class="layui-form-label">内容</label>
        <div class="layui-input-block">
            <textarea id="post-content" name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
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
        form.on('submit(formDemo)', function (data) {
            $.ajax({
                url: "/posts/update?id=" + $('[name="id"]').val(),
                method: 'get',
                data: {
                    title: $("#post-title").val(),
                    content: $("#post-content").val(),

                },
                success: function (result) {
                    if (result.code == 200) {
                        callbackData = {
                            title: $("#post-title").val(),
                            content: $("#post-content").val(),
                        }
                        parent.layer.msg('公告修改成功', {icon: 1});
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                        parent.location.reload();
                    } else {
                        parent.layer.msg('公告修改失败', {icon: 2});
                    }
                }
            });
            return false;
        });
    });
</script>
</html>

