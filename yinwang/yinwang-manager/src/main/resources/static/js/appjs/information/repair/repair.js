
var prefix = "/information/repair"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								userName:$('#userName').val(),
								phone:$('#phone').val(),
								reNames:$('#reNames').val(),
								repair_statue:$('#repair_statue').val(),
								addStaTime:$('#addStaTime').val(),
								addEndTime:$('#addEndTime').val(),
								rstaTime:$('#rstaTime').val(),
								rendTime:$('#rendTime').val(),
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
								{
									field : 'id', 
									title : '' 
								},
								{
									field : 'userName', 
									title : '用户名称' 
								},
								{
									field : 'phone', 
									title : '电话' 
								},
								{
									field : 'typeName', 
									title : '报修类型' 
								},
								{
									field : 'categoryName', 
									title : '报修类别' 
								},
								{
									field : 'add_time', 
									title : '报修时间' 
								},
								{
									field : 'repair_time', 
									title : '维修时间' 
								},
								{
									field : 'address', 
									title : '地址' 
								},
								{
									field : 'reNames', 
									title : '维修师傅' 
								},
								{
									field : 'rePhone', 
									title : '师傅电话' 
								},
								{
									field : 'money', 
									title : '价格' 
								},
								{
									field : 'details', 
									title : '备注' 
								},

								
								
								{
									field : 'statue', 
									title : '状态' ,
									formatter : function(value, row, index) {
										//状态:0：申请、待报价；1：代付款；2：待评价；3：已完成；5：删除
										//维修状态1:未接单；2：已接单；3：已报价，服务中；5：完成；
										var str = '';
										/*if(row.statue == 0){
											str = '待报价';
										}
										if(row.statue == 1){
											str = '待付款';
										}
										if(row.statue == 2 || row.statue == 3){
											str = '已完成';
										}*/
										if(row.repair_statue == 1){
											str = '已发单';
										}
										if(row.repair_statue == 2){
											str = '已接单';
										}
										if(row.repair_statue == 3){
											str = '已报价、服务中';
										}
										if(row.repair_statue == 5){
											str = '完成';
										}
										if(row.statue == 5){
											str = '取消';
										}
										return str;
									}
								}
								,
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="updateEnable(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										return d ;
									}
								}
								]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}
function updateEnable(id){
	
	$.ajax({
		url : prefix + "/updateEnable",
		type : "post",
		data : {
			'id' : id,
		},
		dataType: 'JSON',
		async : false,
		success : function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
			} else {
				layer.msg(r.msg);
			}
		}
	});
}
function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}