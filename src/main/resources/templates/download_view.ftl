<!-- 后台管理系统问题列表-->
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
</head>
<body>

<#--文件编辑按钮更新弹出页面,并触发onUpdateFile(this,rowId:全局变量，获取行ID)更新文件夹文件;-->
<script type="text/html" id="file-update">
    <form class="layui-form">
        <div class="layui-form-item" style="padding-right: 50px">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <input id="file-update-title" type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text" style="padding-right: 50px">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea id="file-update-description" name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <form id="file-form" enctype="multipart/form-data">
                    <div class="layui-form-item">
                        <button type="button" class="layui-btn" id="test1" onclick="javascript:$('input[name=\'file\']').click();">
                            <i class="layui-icon">&#xe67c;</i>更改附件
                        </button>
                        <span class="layui-form-mid" id="file-update-list"></span>
                        <div class="layui-input-block">
                            <input type="file" style="visibility: hidden" onchange="onUpdateFile(this,rowId);" name="file" >
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </form>
</script>
<#--文件上传添加弹出页面-，并触发onChangeFile(this)上传文件至文件夹-->
<script type="text/html" id="file-insert">
    <form class="layui-form">
        <div class="layui-form-item" style="padding-right: 50px">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <input id="file-title" type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text" style="padding-right: 50px">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea id="file-description" name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <form id="file-form" enctype="multipart/form-data">
                    <div class="layui-form-item">
                        <button type="button" class="layui-btn" id="test1" onclick="javascript:$('input[name=\'file\']').click();">
                            <i class="layui-icon">&#xe67c;</i>添加附件
                        </button>
                        <span class="layui-form-mid" id="file-list"></span>
                        <div class="layui-input-block">
                            <input type="file" style="visibility: hidden" onchange="onChangeFile(this);" name="file" >
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </form>

</script>
<#--表头，用于事件监听-->
<table class="layui-hide" id="file-table" lay-filter="file-table"></table>
<#--上传文件按钮-->
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addFile">上传文件</button>
    </div>
</script>
<#--显示右侧编辑/删除/下载按钮-->
<script type="text/html" id="barTpl">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="download" id="download-btn">下载</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<#--代码，实现事件监听和数据呈现-->
<script>
    //上传后文件暂存的地址
    var realUUIDname = "";
    //上传后的文件名
    var originalFilename = "";
    //渲染的表格，展示数据库数据
    layui.use('table', function(){
        var table = layui.table;
        //展示数据代码
        table.render({
            elem: '#file-table',
            url:'/files/list',
            toolbar: '#toolbar',
            parseData: function (res) {
                console.log(res);
                return {
                    "code": 0,
                    "msg": "",
                    "count": res.size,
                    data: res.data
                }
            }
            ,cols: [[
                {field:'id', width:60, title: 'ID'},
                {field:'title', width:200, title: '文件名称', edit: true},
                {field:'description', width:300, title:'描述', edit: true},
                {field:'username', width:150, title: '创建者'},
                {field:'createdTime', width:180, title: '创建时间', sort: true},
                {field:'filename', width: 120, title: '文件名称'},
                {fixed: 'right', width:180, align:'center', toolbar: '#barTpl'}
            ]]
            ,page: true
        });

        //附件更改附件的方法
        onUpdateFile = function (obj,id) {
            var file = obj.files[0];
            console.log(file);
            var formData = new FormData();
            formData.append("file", file);
            $.ajax({
                url: 'upload?id='+id,
                method: 'post',
                data: formData,
                async: false,
                cache: false,
                processData:false,
                contentType:false,
                success:function(res) {
                    console.log(res);
                    if (res.code == 200) {
                        console.log("ceshishuju chuanshu");
                        // 添加到列表中
                        originalFilename = file.name;
                        $("#file-update-list").text(originalFilename);
                        realUUIDname = res.data;
                        console.log('更改后: ' + realUUIDname);
                        layer.tips('更改附件成功', '#file-update-list', {
                            tipsMore: true
                        });
                    } else {
                        layer.tips('更改附件失败', '#file-update-list', {
                            tipsMore: true
                        });
                    }
                }
            });
        },
            //上传附件方法
            onChangeFile = function (obj) {
                console.log("触发方法");
                //获取文件信息
                var file = obj.files[0];
                console.log(file);
                var relativePath = 'work/' + $("select[name='class']").val();
                console.log("relativePath");
                console.log(relativePath);
                var formData = new FormData();
                formData.append("file", file);
                formData.append("relativePath", relativePath);
                $.ajax({
                    url: 'upload',
                    method: 'post',
                    data: formData,
                    async: false,
                    cache: false,
                    processData:false,
                    contentType:false,
                    success:function(res) {
                        if (res.code == 200) {
                            console.log("res.code == 200");
                            // 添加到列表中
                            $("#file-list").text(file.name);
                            //原文件名
                            originalFilename = file.name;
                            //加密的文件名
                            realUUIDname = res.data;
                            console.log("上传附件后打印名字");
                            console.log(originalFilename);
                            console.log(realUUIDname);
                            layer.tips('附件上传成功', '#file-list', {
                                tipsMore: true
                            });
                        } else {
                            layer.tips('附件上传失败', '#file-update-list', {
                                tipsMore: true
                            });
                        }
                    }
                });
            }

        // 监听上方添加文件工作条
        table.on('toolbar(file-table)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            switch(obj.event){
                case 'addFile':

                    layer.open({
                        btn: '上传',
                        title: '文件上传',
                        content: $("#file-insert").text(),
                        offset: 'c',
                        area: ["500px", "350px"],
                        yes: function () {
                            var title = $("#file-title").val();
                            var description = $("#file-description").val();
                            if (title.length <= 0  || description.length <= 0||realUUIDname.length <= 0) {
                                if (title.length <= 0) {
                                    layer.tips('标题不能为空', '#file-title',  {
                                        tipsMore: true
                                    });
                                }
                                if (description.length <= 0) {
                                    layer.tips('描述不能为空', '#file-description', {
                                        tipsMore: true
                                    });
                                }
                                console.log("打印名字");
                                console.log(realUUIDname);
                                if (realUUIDname.length <= 0) {
                                    layer.tips('未选择文件', '#test1', {
                                        tipsMore: true
                                    });
                                }
                            } else {
                                // 调用新建API
                                var nowDate = new Date();
                                var empId = "${(user.id)!''}";
                                var username = "${(user.username)!'test'}";
                                $.ajax({
                                    url: '/files/add',
                                    method: 'post',
                                    data: {
                                        title: title,
                                        description: description,
                                        path: 'C:/hrm-upload/'+realUUIDname,
                                        filename: originalFilename,
                                        username: username,
                                        createdTime: nowDate
                                    },
                                    success: function (res) {
                                        console.log(res);
                                        if (res.code == 200) {
                                            // 执行局部刷新, 获取之前的TABLE内容, 再进行填充
                                            var dataBak = [];
                                            var tableBak = table.cache.file-table;
                                            for (var i = 0; i < tableBak.length; i++) {
                                                dataBak.push(tableBak[i]);      //将之前的数组备份
                                            }
                                            dataBak.push({
                                                title: $("#file-title"),
                                                content: $("#file-description"),
                                                path: realUUIDname,
                                                filename: originalFilename,
                                                username: username,
                                                createdTime: nowDate
                                            });
                                            //console.log(dataBak);
                                            table.reload("file-table",{
                                                data:dataBak   // 将新数据重新载入表格
                                            });
                                            layer.msg('上传文件成功', {icon: 1});
                                            realUUIDname="";
                                        } else {
                                            layer.msg('上传文件失败', {icon: 2});
                                        }
                                    }
                                });
                            }
                        }
                    });
            }
        });

        //监听工具条上传文件(右侧)，文件编辑/下载/删除事件
        table.on('tool(file-table)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'del'){ //删除
                layer.confirm('删除文件' + data.title + '?', {skin: 'layui-layer-molv',offset:'c', icon:'0'},function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url: '/files/delete?id=' + data.id,
                        type: 'json',
                        success: function (res) {
                            console.log(res);
                            if (res.code == 200) {
                                layer.msg('删除成功', {icon: 1, skin: 'layui-layer-molv', offset:'c'});
                            } else {
                                layer.msg('删除失败', {icon: 2, skin: 'layui-layer-molv', offset:'c'});
                            }
                        }
                    })
                    location.reload();
                });
            } else if(layEvent === 'edit'){ //编辑
                rowId = data.id;
                // 发送更新请求
                layer.open({
                    btn: '保存',
                    title: '编辑文件: ' + data.title,
                    content: $("#file-update").text(),
                    offset: 'c',
                    area: ["500px", "350px"],
                    success: function () {
                        originalFilename = data.filename;
                        $("#file-update-title").val(data.title);
                        $("#file-update-description").text(data.description);
                        $("#file-update-list").text(originalFilename);
                    },
                    yes: function () {

                        // 进行更新AJAX
                        $.ajax({
                            url: 'updateTo',
                            method: 'put',
                            data: JSON.stringify({
                                id: data.id,
                                title: $("#file-update-title").val(),
                                //将重命名后的文件名传到后台
                                path: realUUIDname,
                                description: $("#file-update-description").val(),
                                filename: originalFilename
                            }),
                            contentType: "application/json",
                            success: function (res) {
                                console.log("顺利完成文件拷贝12");
                                console.log(res);
                                if (res.code == 200) {
                                    console.log("顺利完成文件拷贝123");
                                    layer.msg('更改信息成功', {icon: 1});
                                    obj.update({
                                        title: $("#file-update-title").val(),
                                        description: $("#file-update-description").val(),
                                        filename: originalFilename
                                    });
                                } else {
                                    layer.msg('更改信息失败', {icon: 2});
                                }
                            }
                        });
                    }
                });

            } else if(layEvent === 'download'){ //下载文件
                // 文件下载
                console.log(data.id);
                window.location.href = "/files/downloadFile?id=" + data.id;
            }
        });
    });
</script>
</body>
</html>