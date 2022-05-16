<!-- 员工管理-->
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
</head>
<body>
<table class="layui-hide" id="employee-table" lay-filter="employee-table" style="height: 100%"></table>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addPost">添加用户</button>
    </div>
</script>

<script type="text/html" id="barTpl">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script>
    layui.use(['table', 'layer'], function () {
        var layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            table = layui.table;
        //对查询到的数据，进行表格渲染
        layui.use(['table', 'form'], function () {
            var table = layui.table;
            var form = layui.form;
            table.render({
                elem: '#employee-table',
                url: '/emp/list',
                toolbar: '#toolbar',
                parseData: function (result) {
                    console.log(result);
                    return {
                        "code": 0,
                        "msg": "",
                        data: result.data,
                        count: result.size
                    }
                }
                , cols: [[
                    <!--{type:'checkbox', fixed:'left'},-->
                    {field: 'id', width: 60, title: 'ID'},
                    {field: 'name', width: 100, title: '员工姓名'},
                    {field: 'sex', width: 70, title: '性别'},
                    {field: 'phone', width: 120, title: '手机号'},
                    {field: 'email', width: 110, title: '邮箱'},
                    {field: 'education', width: 70, title: '学历',templet: function (row) {
                            if (row.education == 0) {
                                return "<div >本科</div>";
                            } else if(row.education == 1) {
                                return "<div >硕士</div>";
                            }else if(row.education == 2) {
                                return "<div >博士</div>";
                            }
                        }},
                    {field: 'idcard', width: 120, title: '身份证'},
                    {field: 'address', width: 100, title: '地址'},
                    {field: 'positionId', width: 50, title: '职位编号'},
                    {field: 'positionName', width: 80, title: '职位'},
                    {field: 'deptName', width: 80, title: '部门'},
                    {field: 'deptId', width: 80, title: '部门编号'},
                    {field: 'createdTime', width: 180, title: '创建时间', sort: true},
                    {fixed: 'right', width: 150, align: 'center', toolbar: '#barTpl'}
                ]],
                page: true
            });


            /*右侧工具栏监听*/
            table.on('tool(employee-table)', function (obj) {
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                var url = "/emp/toAddUpdate?id=" + data.id;
                var layerCallback;
                switch (obj.event) {
                    case 'edit'://编辑触发
                        layerCallback = function (callbackData) {
                            // 执行局部刷新, 获取之前的TABLE内容, 再进行填充
                            var dataBak = [];
                            var tableBak = table.cache.auth - table;
                            for (var i = 0; i < tableBak.length; i++) {
                                dataBak.push(tableBak[i]);      //将之前的数组备份
                            }
                            dataBak.push(callbackData);
                            table.reload("employee-table", {
                                data: dataBak   // 将新数据重新载入表格
                            });
                        };
                        layer.open({
                            title: '修改用户信息',
                            content: url,
                            type: 2,
                            offset: 'c',
                            area: ["500px", "400px"]
                        });
                        break;
                    case 'del'://删除触发
                        layer.confirm('删除用户' + data.username + '?', {
                            skin: 'layui-layer-molv',
                            offset: 'c',
                            icon: '0'
                        }, function (index) {
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);
                            //向服务端发送删除指令
                            $.ajax({
                                url: '/user_auth/delete?id=' + data.id,
                                success: function (res) {
                                    console.log(res);
                                    if (res.code == 200) {
                                        layer.msg('删除成功', {icon: 1, skin: 'layui-layer-molv', offset: 'c'});
                                    } else {
                                        layer.msg('删除失败', {icon: 2, skin: 'layui-layer-molv', offset: 'c'});
                                    }
                                }
                            })
                        });
                        break;
                }
            });

            /*上方工具栏监听*/
            table.on('toolbar(employee-table)', function (obj) {
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                var url = "/emp/toAddUpdate";
                var layerCallback;
                switch (obj.event) {
                    case 'addPost':
                        layerCallback = function (callbackData) {
                            // 执行局部刷新, 获取之前的TABLE内容, 再进行填充
                            var dataBak = [];
                            var tableBak = table.cache.auth - table;
                            for (var i = 0; i < tableBak.length; i++) {
                                dataBak.push(tableBak[i]);      //将之前的数组备份
                            }
                            dataBak.push(callbackData);
                            table.reload("employee-table", {
                                data: dataBak   // 将新数据重新载入表格
                            });
                        };
                        layer.open({
                            title: '添加员工',
                            content: url,
                            type: 2,
                            offset: 'c',
                            area: ["500px", "400px"]
                        });
                }
            });
        });


    });


</script>
</body>
</html>