layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

/**
 * 用户信息管理初始化
 */
var Member = {
    tableId: "memberTable",	//表格id
    condition: {
        memberId: ""
    }
};

/**
 * 初始化表格的列
 */
Member.initColumn = function () {
    return [[
        // {type: 'checkbox'},
         {field: 'memberId', hide: true, sort: true, title: 'id'},
         {field: 'phone', sort: true, title: '手机号码'},
         {field: 'uid', sort: true, title: 'uid'},
         {field: 'type', sort: true, title: '用户类型(0:临时用户 1:会员 2:矿主 3：大矿主 4：超级矿主） '},
         {field: 'password', sort: true, title: '密码'},
         {field: 'area', sort: true, title: '手机号码属于0：国内 1：国外'},
         {field: 'salt', sort: true, title: '密码加盐'},
         {field: 'payPassword', sort: true, title: '交易密码'},
         {field: 'paySalt', sort: true, title: '交易密码加盐'},
         {field: 'inviteCode', sort: true, title: '邀请码'},
         {field: 'parentRefereeId', sort: true, title: '用户关系链'},
         {field: 'refereeId', sort: true, title: '推荐人'},
         {field: 'mnemonic', sort: true, title: '助记词（备用）'},
         {field: 'loginEquipment', sort: true, title: '登录设备（备用）'},
         {field: 'mallPoints', sort: true, title: '累计收益（备用）'},
         {field: 'restDay', sort: true, title: '算力剩余天数'},
         {field: 'state', sort: true, title: '系统是否修改过用户类型0：否 1：是'},
         {field: 'nickName', sort: true, title: '昵称'},
         {field: 'realStatus', sort: true, title: '是否实名认证0：否 1：是 2 :审核中'},
         {field: 'areaCode', sort: true, title: '区号'},
         {field: 'failureSign', sort: true, title: '登录失败记录'},
         {field: 'head', sort: true, title: '头像'},
         {field: 'points', sort: true, title: '是否开通积分划转(0:否 1：是）'},
         {field: 'realName', sort: true, title: '真实姓名'},
         {field: 'lastLogin', sort: true, title: '最近一次登录'},
         {field: 'registerTime', sort: true, title: '注册时间'},
         {field: 'directRate', sort: true, title: '直推比例（备用）'},
         {field: 'teamRate', sort: true, title: '团队比例（备用）'},
         {field: 'provinceId', sort: true, title: '省'},
         {field: 'cityId', sort: true, title: '市'},
         {field: 'areaId', sort: true, title: '区'},
         {field: 'provinceProxy', sort: true, title: '是否省代理（0：否 1：是）'},
         {field: 'cityProxy', sort: true, title: '是否市代理（0：否 1：是）'},
         {field: 'areaIdProxy', sort: true, title: '是否区/县代理（0：否 1：是）'},
         {field: 'director', sort: true, title: '是否理事（0：否 1：是'},
         {field: 'cooperation', sort: true, title: '是否合创(0:否 1：是）'},
         {field: 'version', sort: true, title: '版本号'},
         {field: 'createTime', sort: true, title: '创建时间'},
        {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
    ]];
};


    /**
     * 点击查询按钮
     */
    Member.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['memberId'] = Member.condition.memberId;
        table.reload(Member.tableId, {where: queryData});
    };



/**
 * 打开查看用户信息详情
 */
Member.openMemberDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/member/member_edit/' + Member.seItem.id
        });
        this.layerIndex = index;
    }
};


    /**
     * 删除用户信息
     *
     */
    Member.onDeleteMember = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/member/delete", function () {
                Feng.success("删除成功!");
                table.reload(Member.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("memberId", data.memberId);
            ajax.start();
        };
        Feng.confirm("是否删除用户信息 ?", operation);
    };

    /**
     * 弹出添加用户信息
     */
    Member.openAddMember = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加用户信息',
            area:["800px","420px"],
            content: Feng.ctxPath + '/member/member_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Member.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Member.exportExcel = function () {
        var checkRows = table.checkStatus(Member.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.member.id, checkRows.data, 'xls');
        }
    };
   /**
     * 点击编辑用户信息
     *
     */
     Member.onEditMember = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改用户信息',
            area:["800px","420px"],
            content: Feng.ctxPath + '/member/member_edit?memberId=' + data.memberId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Member.tableId);
            }
        });
    };
 // 渲染表格
    var tableResult = table.render({
        elem: '#' + Member.tableId,
        url: Feng.ctxPath + '/member/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        limit:100,
        limits: [100,200,300,400,500],
        cols: Member.initColumn()
    });
 // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Member.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Member.openAddMember();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Member.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Member.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Member.onEditMember(data);
        } else if (layEvent === 'delete') {
            Member.onDeleteMember(data);
        }
    });
});