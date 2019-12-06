layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

/**
 * 地区管理初始化
 */
var Region = {
    tableId: "regionTable",	//表格id
    condition: {
        regionId: ""
    }
};

/**
 * 初始化表格的列
 */
Region.initColumn = function () {
    return [[
        // {type: 'checkbox'},
         {field: 'regionId', hide: true, sort: true, title: 'id'},
         {field: 'name', sort: true, title: '名称'},
         {field: 'parentId', sort: true, title: '父级id'},
         {field: 'shortName', sort: true, title: '缩写'},
         {field: 'levelType', sort: true, title: '层级'},
         {field: 'cityCode', sort: true, title: '编码'},
         {field: 'zipCode', sort: true, title: '邮编'},
         {field: 'mergerName', sort: true, title: '合称'},
         {field: 'lng', sort: true, title: '经度'},
         {field: 'lat', sort: true, title: '纬度'},
         {field: 'pinyin', sort: true, title: '拼音'},
         {field: 'createTime', sort: true, title: '创建时间'},
        {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
    ]];
};


    /**
     * 点击查询按钮
     */
    Region.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['regionId'] = Region.condition.regionId;
        table.reload(Region.tableId, {where: queryData});
    };



/**
 * 打开查看地区详情
 */
Region.openRegionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '地区详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/region/region_edit/' + Region.seItem.id
        });
        this.layerIndex = index;
    }
};


    /**
     * 删除地区
     *
     */
    Region.onDeleteRegion = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/region/delete", function () {
                Feng.success("删除成功!");
                table.reload(Region.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("regionId", data.regionId);
            ajax.start();
        };
        Feng.confirm("是否删除地区 ?", operation);
    };

    /**
     * 弹出添加地区
     */
    Region.openAddRegion = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加地区',
            area:["800px","420px"],
            content: Feng.ctxPath + '/region/region_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Region.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Region.exportExcel = function () {
        var checkRows = table.checkStatus(Region.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.region.id, checkRows.data, 'xls');
        }
    };
   /**
     * 点击编辑地区
     *
     */
     Region.onEditRegion = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改地区',
            area:["800px","420px"],
            content: Feng.ctxPath + '/region/region_edit?regionId=' + data.regionId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Region.tableId);
            }
        });
    };
 // 渲染表格
    var tableResult = table.render({
        elem: '#' + Region.tableId,
        url: Feng.ctxPath + '/region/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        limit:100,
        limits: [100,200,300,400,500],
        cols: Region.initColumn()
    });
 // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Region.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Region.openAddRegion();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Region.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Region.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Region.onEditRegion(data);
        } else if (layEvent === 'delete') {
            Region.onDeleteRegion(data);
        }
    });
});