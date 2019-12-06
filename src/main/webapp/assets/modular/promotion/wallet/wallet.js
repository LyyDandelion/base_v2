layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

/**
 * 用户钱包管理初始化
 */
var Wallet = {
    tableId: "walletTable",	//表格id
    condition: {
        walletId: ""
    }
};

/**
 * 初始化表格的列
 */
Wallet.initColumn = function () {
    return [[
        // {type: 'checkbox'},
         {field: 'walletId', hide: true, sort: true, title: 'id'},
         {field: 'memberId', sort: true, title: ''},
         {field: 'totalPrice', sort: true, title: '钱包总额'},
         {field: 'returnPrice', sort: true, title: '平台返还总额'},
         {field: 'ticketPrice', sort: true, title: '消费券总额'},
         {field: 'createTime', sort: true, title: '创建时间'},
        {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
    ]];
};


    /**
     * 点击查询按钮
     */
    Wallet.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['walletId'] = Wallet.condition.walletId;
        table.reload(Wallet.tableId, {where: queryData});
    };



/**
 * 打开查看用户钱包详情
 */
Wallet.openWalletDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户钱包详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wallet/wallet_edit/' + Wallet.seItem.id
        });
        this.layerIndex = index;
    }
};


    /**
     * 删除用户钱包
     *
     */
    Wallet.onDeleteWallet = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wallet/delete", function () {
                Feng.success("删除成功!");
                table.reload(Wallet.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("walletId", data.walletId);
            ajax.start();
        };
        Feng.confirm("是否删除用户钱包 ?", operation);
    };

    /**
     * 弹出添加用户钱包
     */
    Wallet.openAddWallet = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加用户钱包',
            area:["800px","420px"],
            content: Feng.ctxPath + '/wallet/wallet_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Wallet.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Wallet.exportExcel = function () {
        var checkRows = table.checkStatus(Wallet.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.wallet.id, checkRows.data, 'xls');
        }
    };
   /**
     * 点击编辑用户钱包
     *
     */
     Wallet.onEditWallet = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改用户钱包',
            area:["800px","420px"],
            content: Feng.ctxPath + '/wallet/wallet_edit?walletId=' + data.walletId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Wallet.tableId);
            }
        });
    };
 // 渲染表格
    var tableResult = table.render({
        elem: '#' + Wallet.tableId,
        url: Feng.ctxPath + '/wallet/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        limit:100,
        limits: [100,200,300,400,500],
        cols: Wallet.initColumn()
    });
 // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Wallet.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Wallet.openAddWallet();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Wallet.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Wallet.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Wallet.onEditWallet(data);
        } else if (layEvent === 'delete') {
            Wallet.onDeleteWallet(data);
        }
    });
});