layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

/**
 * 现金流管理初始化
 */
var Cashflow = {
    tableId: "cashflowTable",	//表格id
    condition: {
        cashflowId: ""
    }
};

/**
 * 初始化表格的列
 */
Cashflow.initColumn = function () {
    return [[
        // {type: 'checkbox'},
         {field: 'cashflowId', hide: true, sort: true, title: 'id'},
         {field: 'memberId', sort: true, title: '用户id'},
         {field: 'flowOp', sort: true, title: '流水方向（流入 1 - 流出 - 0）'},
         {field: 'flowType', sort: true, title: '流水类型'},
         {field: 'itemCode', sort: true, title: '流水记录编码'},
         {field: 'itemName', sort: true, title: '名称'},
         {field: 'flowPrice', sort: true, title: '流水金额'},
         {field: 'createTime', sort: true, title: '创建时间'},
        {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
    ]];
};


    /**
     * 点击查询按钮
     */
    Cashflow.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['cashflowId'] = Cashflow.condition.cashflowId;
        table.reload(Cashflow.tableId, {where: queryData});
    };



/**
 * 打开查看现金流详情
 */
Cashflow.openCashflowDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '现金流详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cashflow/cashflow_edit/' + Cashflow.seItem.id
        });
        this.layerIndex = index;
    }
};


    /**
     * 删除现金流
     *
     */
    Cashflow.onDeleteCashflow = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/cashflow/delete", function () {
                Feng.success("删除成功!");
                table.reload(Cashflow.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("cashflowId", data.cashflowId);
            ajax.start();
        };
        Feng.confirm("是否删除现金流 ?", operation);
    };

    /**
     * 弹出添加现金流
     */
    Cashflow.openAddCashflow = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加现金流',
            area:["800px","420px"],
            content: Feng.ctxPath + '/cashflow/cashflow_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Cashflow.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Cashflow.exportExcel = function () {
        var checkRows = table.checkStatus(Cashflow.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.cashflow.id, checkRows.data, 'xls');
        }
    };
   /**
     * 点击编辑现金流
     *
     */
     Cashflow.onEditCashflow = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改现金流',
            area:["800px","420px"],
            content: Feng.ctxPath + '/cashflow/cashflow_edit?cashflowId=' + data.cashflowId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Cashflow.tableId);
            }
        });
    };
 // 渲染表格
    var tableResult = table.render({
        elem: '#' + Cashflow.tableId,
        url: Feng.ctxPath + '/cashflow/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        limit:100,
        limits: [100,200,300,400,500],
        cols: Cashflow.initColumn()
    });
 // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Cashflow.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Cashflow.openAddCashflow();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Cashflow.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Cashflow.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Cashflow.onEditCashflow(data);
        } else if (layEvent === 'delete') {
            Cashflow.onDeleteCashflow(data);
        }
    });
});