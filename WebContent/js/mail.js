function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function addLi(ulid, litxt) {
	document.getElementById(ulid).innerHTML += litxt;
}

function mailstatus() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>邮件概况<div class='info'>查询指定日期的发信量和收信量</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='date' id='begintime' >"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'   type='date' id='endtime' >"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>域名：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<select  class='txt' name='opersect' style='position: absolute; top: 0px; top: 2px\9; height: 26px; line-height: 26px;' id='domain'>"
			+"<option value='yzsmarts.xyz'>yzsmarts.xyz</option>"
			+"<option value='txmail.xyz'>txmail.xyz</option>"
			+ "</select></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='msajax()'>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div><div class='content_table' id='table'></div>";
}

function msajax() {
	var begintime = document.getElementById("begintime").value;
	var endtime = document.getElementById("endtime").value;
	var domain = document.getElementById("domain").value;
	$.ajax({
				type : "POST",
				url : "/HuoBiLog/queryms", // event handler url
				data : {
					begintime : begintime,
					endtime : endtime,
					domain : domain
				},
				dataType : "json",
				success : function(msg) {
					var table_ms =  '<div style="width: 100%;"><div class="caption" style="text-align: right"></div>'
							+ '<table cellspacing="0" align="center" class="infoTable domainInfo listTable" style="width: 100%; table-layout: fixed;" id="mstable">'
							+ '<tbody>' + '<tr><th style="width: 5%"></th>'
							+ '<th style="width: 15%;cursor=default;">序号</th>'
							+ '<th style="width: 18%;">时间</th>'
							+ '<th style="width: 18%;"></th>'
							+ '<th style="width: 16%;">发信量</th>'
							+ '<th style="width: 14%;">收件量</th>'
							+ '<th style="width: 14%;">详情</th>' + '</tr>';
					for ( var o in msg) {
						table_ms = table_ms + '<tr><td></td><td>' + o
								+ ' </td><td >' + msg[o].time
								+ '</td><td></td><td>' + msg[o].info.sendsum
								+ '</td><td>' + msg[o].info.recvsum
								+ '</td><td><a class="btn btnNormal" onclick="msi(\''+msg[o].time+'\')" >详情</a></td></tr>';
					}
					table_ms = table_ms
							+ '</tbody></table>'
							+ '<div class="caption" style="text-align: right"></div></div>';
					document.getElementById('table').innerHTML = table_ms;
				}
			});
}

function mailinfo() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>邮件查询<div class='info'>查询详细邮件记录</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='date' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='date' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>邮箱账号：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<input  class='txt time_li'  type='text' id='userid'></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='miajax()'>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div><div class='content_table' id='table'></div>";
}

function miajax() {
	var begintime = document.getElementById("begintime").value;
	var endtime = document.getElementById("endtime").value;
	var userid = document.getElementById("userid").value;
	$.ajax({
				type : "POST",
				url : "/HuoBiLog/querymi", // event handler url
				data : {
					begintime : begintime,
					endtime : endtime,
					userid : userid
				},
				dataType : "json",
				success : function(msg) {
					var table_ms =  '<div style="width: 100%;"><div class="caption" style="text-align: right"></div>'
							+ '<table cellspacing="0" align="center" class="infoTable domainInfo listTable" style="width: 100%; table-layout: fixed;" id="mstable">'
							+ '<tbody>' + '<tr><th style="width: 5%">序号</th>'
							+ '<th style="width: 15%;cursor=default;">时间</th>'
							+ '<th style="width: 18%;">邮件类型</th>'
							+ '<th style="width: 18%;">邮件主题</th>'
							+ '<th style="width: 16%;">发件邮箱</th>'
							+ '<th style="width: 14%;">收件邮箱</th>'
							+ '<th style="width: 14%;">邮件状态</th>' + '</tr>';
					for ( var o in msg) {
						for(var i in msg[o].info.list){
						table_ms = table_ms + '<tr><td>'+o+'</td><td>' + getLocalTime(msg[o].info.list[i].time)
								+ ' </td><td>' +getMail(msg[o].info.list[i].mailtype) 
								+ '</td><td>'+msg[o].info.list[i].subject
								+'</td><td>' + msg[o].info.list[i].sender
								+ '</td><td>' + msg[o].info.list[i].receiver
								+ '</td><td>'+getMailType(msg[o].info.list[i].status)
								+'</td></tr>';
						}
					}
					table_ms = table_ms
							+ '</tbody></table>'
							+ '<div class="caption" style="text-align: right"></div></div>';
					document.getElementById('table').innerHTML = table_ms;
				}
			});
}

function msi(time) {
	var strs= new Array(); 
	strs=time.split(" ");
	var begintime = strs[0];
	var endtime = begintime;
	$.ajax({
				type : "POST",
				url : "/HuoBiLog/querymsi", // event handler url
				data : {
					begintime : begintime,
					endtime : endtime
				},
				dataType : "json",
				success : function(msg) {
					var table_ms =  '<div style="width: 100%;"><div class="caption" style="text-align: right"></div>'
							+ '<table cellspacing="0" align="center" class="infoTable domainInfo listTable" style="width: 100%; table-layout: fixed;" id="mstable">'
							+ '<tbody>' + '<tr><th style="width: 5%">序号</th>'
							+ '<th style="width: 18%;cursor=default;">时间</th>'
							+ '<th style="width: 15%;"></th>'
							+ '<th style="width: 22%;">邮箱账号</th>'
							+ '<th style="width: 12%;"></th>'
							+ '<th style="width: 14%;">发件量</th>'
							+ '<th style="width: 14%;">收件量</th>' + '</tr>';
					for ( var o in msg) {
						table_ms = table_ms + '<tr><td>'+o+'</td><td>' + msg[o].time
								+ ' </td><td>' 
								+ '</td><td>'+ msg[o].userid
								+'</td><td>' 
								+ '</td><td>' +msg[o].send
								+ '</td><td>'+ msg[o].get
								+'</td></tr>';
					}
					table_ms = table_ms
							+ '</tbody></table>'
							+ '<div class="caption" style="text-align: right"></div></div>';
					document.getElementById('table').innerHTML = table_ms;
				}
			});
}

function loginlog() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>登录查询<div class='info'>可查询最近三个月的邮件概况</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='date' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='date' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>邮箱账号：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<input  class='txt time_li'  type='text' id='userid'></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='llajax()'>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div><div class='content_table' id='table'></div>";
}

function llajax() {
	var begintime = document.getElementById("begintime").value;
	var endtime = document.getElementById("endtime").value;
	var userid = document.getElementById("userid").value;
	$.ajax({
				type : "POST",
				url : "/HuoBiLog/querylogin", // event handler url
				data : {
					begintime : begintime,
					endtime : endtime,
					userid : userid
				},
				dataType : "json",
				success : function(msg) {
					var table_ms = '<div style="width: 100%;"><div class="caption" style="text-align: right"></div>'
							+ '<table cellspacing="0" align="center" class="infoTable domainInfo listTable" style="width: 100%; table-layout: fixed;" id="mstable">'
							+ '<tbody>' + '<tr><th style="width: 5%">序号</th>'
							+ '<th style="width: 15%;cursor=default;">时间</th>'
							+ '<th style="width: 18%;"></th>'
							+ '<th style="width: 18%;"></th>'
							+ '<th style="width: 16%;">登录ip</th>'
							+ '<th style="width: 14%;">登陆类型</th>'
							+ '<th style="width: 14%;"></th>' + '</tr>';
					for ( var o in msg) {
						for(var i in msg[o].info.list){
						table_ms = table_ms + '<tr><td>'+o
								+'</td><td>' + getLocalTime(msg[o].info.list[i].time)
								+ ' </td><td>'
								+ '</td><td>'
								+'</td><td>' + msg[o].info.list[i].ip
								+ '</td><td>' +getLoginType(msg[o].info.list[i].type) 
								+ '</td><td>'
								+'</td></tr>';
						}
					}
					table_ms = table_ms
							+ '</tbody></table>'
							+ '<div class="caption" style="text-align: right"></div></div>';
					document.getElementById('table').innerHTML = table_ms;
				}
			});
}

function oprlog() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>操作查询<div class='info'>查询详细操作记录</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='date' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='date' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>操作类型：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<select  class='txt' name='opersect' style='position: absolute; top: 0px; top: 2px\9; height: 26px; line-height: 26px;' id='type'>"
			+"<option value='all'>全部</option>"
			+"<option value='1'>登录</option>"
			+"<option value='2'>修改密码</option>"
			+"<option value='3'>添加域名</option>"
			+"<option value='4'>注销域名</option>"
			+"<option value='5'>设置LOGO</option>"
			+"<option value='6'>删除LOGO</option>"
			+"<option value='7'>修改密保邮箱</option>"
			+"<option value='8'>修改管理员邮箱</option>"
			+"<option value='9'>发表公告</option>"
			+"<option value='10'>群发邮件</option>"
			+"<option value='11'>新增黑名单</option>"
			+"<option value='12'>删除黑名单</option>"
			+"<option value='13'>清空黑名单</option>"
			+"<option value='14'>新增白名单</option>"
			+"<option value='15'>删除白名单</option>"
			+"<option value='16'>清空白名单</option>"
			+"<option value='17'>新增域白名单</option>"
			+"<option value='18'>删除域白名单</option>"
			+"<option value='19'>新增用户</option>"
			+"<option value='20'>删除用户</option>"
			+"<option value='21'>启用用户</option>"
			+"<option value='22'>禁用用户</option>"
			+"<option value='23'>编辑用户</option>"
			+"<option value='24'>编辑别名</option>"
			+"<option value='25'>批量导入用户</option>"
			+"<option value='26'>添加分级管理员</option>"
			+"<option value='27'>删除分级管理员</option>"
			+"<option value='28'>新增部门</option>"
			+"<option value='29'>删除部门</option>"
			+"<option value='30'>编辑部门</option>"
			+"<option value='31'>移动部门</option>"
			+"<option value='32'>新增邮件组</option>"
			+"<option value='33'>删除邮件组</option>"
			+"<option value='34'>编辑邮件组</option>"
			+"<option value='35'>设置邮件备份</option>"
			+"<option value='36'>邮件转移</option>"
			+"<option value='37'>IP登录权限</option>"
			+"<option value='38'>限制成员外发</option>"
			+"<option value='39'>开启接口</option>"
			+"<option value='40'>重新获取KEY</option>"
			+"<option value='41'>停用接口</option>"
			+ "</select></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='olajax()'>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div><div class='content_table' id='table'></div>";
}

function olajax() {
	var begintime = document.getElementById("begintime").value;
	var endtime = document.getElementById("endtime").value;
	var type = document.getElementById("type").value;
	$
			.ajax({
				type : "POST",
				url : "/HuoBiLog/queryoper", // event handler url
				data : {
					begintime : begintime,
					endtime : endtime,
					type : type
				},
				dataType : "json",
				success : function(msg) {
					var table_ms =  '<div style="width: 100%;"><div class="caption" style="text-align: right"></div>'
							+ '<table cellspacing="0" align="center" class="infoTable domainInfo listTable" style="width: 100%; table-layout: fixed;" id="mstable">'
							+ '<tbody>' + '<tr><th style="width: 5%">序号</th>'
							+ '<th style="width: 15%;cursor=default;">时间</th>'
							+ '<th style="width: 18%;">操作人</th>'
							+ '<th style="width: 18%;">操作类型</th>'
							+ '<th style="width: 16%;">关联数据</th>'
							+ '<th style="width: 14%;">备注信息</th>'
							+ '<th style="width: 14%;"></th>' + '</tr>';
					for ( var o in msg) {
						for(var i in msg[o].info.list){
							var re=msg[o].info.list[i].remark;
						table_ms = table_ms + '<tr><td>'+o
								+'</td><td>' + getLocalTime(msg[o].info.list[i].time)
								+ ' </td><td>'+ msg[o].info.list[i].operator
								+ '</td><td>' + getOperType(msg[o].info.list[i].type)
								+'</td><td>' + msg[o].info.list[i].operand
								+ '</td><td>' ;
								if(typeof(re)=="undefined"){
									table_ms = table_ms + '</td><td>'
									+'</td></tr>';
								}else{
									table_ms = table_ms +re+ '</td><td>'
									+'</td></tr>';
								}
						}
					}
					table_ms = table_ms
							+ '</tbody></table>'
							+ '<div class="caption" style="text-align: right"></div></div>';
					document.getElementById('maintxt').innerHTML = table_ms;
				}
			});
}

function batchlog() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>批量任务查询<div class='info'>查询详细批量任务记录</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='date' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='date' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>操作类型：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<select  class='txt' name='opersect' style='position: absolute; top: 0px; top: 2px\9; height: 26px; line-height: 26px;' id='type'>"
			+"<option value='all'>全部</option>"
			+"<option value='1'>群发邮件</option>"
			+"<option value='2'>批量导入成员</option>"
			+"<option value='3'>删除公告</option>"
			+"<option value='4'>批量添加别名</option>"
			+"<option value='5'>发布公告</option>"
			+"<option value='6'>RTX帐号关联</option>"
			+"<option value='7'>设置企业签名档</option>"
			+"<option value='8'>取消企业签名档</option>"
			+"<option value='9'>开通成员</option>"
			+"<option value='0'>其他</option></select>"
			+"</span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='blajax()'>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div><div class='content_table' id='table'></div>";
}

function blajax() {
	var begintime = document.getElementById("begintime").value;
	var endtime = document.getElementById("endtime").value;
	var type = document.getElementById("type").value;
	$
			.ajax({
				type : "POST",
				url : "/HuoBiLog/querybatch", // event handler url
				data : {
					begintime : begintime,
					endtime : endtime,
					type : type
				},
				dataType : "json",
				success : function(msg) {
					var table_ms = '<div style="width: 100%;"><div class="caption" style="text-align: right"></div>'
							+ '<table cellspacing="0" align="center" class="infoTable domainInfo listTable" style="width: 100%; table-layout: fixed;" id="mstable">'
							+ '<tbody>' + '<tr><th style="width: 5%">序号</th>'
							+ '<th style="width: 15%;cursor=default;">时间</th>'
							+ '<th style="width: 18%;"></th>'
							+ '<th style="width: 18%;">操作人</th>'
							+ '<th style="width: 16%;"></th>'
							+ '<th style="width: 14%;">操作类型</th>'
							+ '<th style="width: 14%;"></th>' + '</tr>';
					for ( var o in msg) {
						for(var i in msg[o].info.list){
						table_ms = table_ms + '<tr><td>'+o
								+'</td><td>' + getLocalTime(msg[o].info.list[i].time)
								+ ' </td><td>'
								+ '</td><td>'+ msg[o].info.list[i].operator
								+ '</td><td>' 
								+ '</td><td>'+ getBatchType(msg[o].info.list[i].type)
								+ '</td><td>' 
								+ '</td></tr>';
						}
					}
					table_ms = table_ms
							+ '</tbody></table>'
							+ '<div class="caption" style="text-align: right"></div></div>';
					document.getElementById('table').innerHTML = table_ms;
				}
			});
}



$(document)
		.ready(
				function() {
					$("#btnsb")
							.click(
									function() {
										var userid = $("#userid").val();
										var password = $("#password").val();
										if (userid == "" || password == "") {
											alert("用户名和密码不可以为空!");
											return false;
										} else {
											$.ajax({
														type : "POST",
														url : "/HuoBiLog/login",
														data : {
															userid : userid,
															password : password
														},
														beforeSend : function() {
															$("#loading").css("display","block");
															$("#btnLogin").css("display","none");
														},
														dataType : "json",
														success : function(msg) {
															$("#loading").hide();
															if (msg.rs == "unregistered") {
																alert("对不起，该用户未注册!");
															}
															if (msg.rs == "fail") {
																alert("对不起，密码错误!");
															}
															if (msg.rs == "success") {
																parent.document.location.href = "index.html?userid="
																		+ msg.userid;
															}
														},
														complete : function(
																data) {
															$("#loading").css("display","none");
															$("#btnLogin").css("display","block");
														}
													});
										}
										return false;
									});

				});

function goPage(pno, psize, table) {
	var itable = document.getElementById("table");
	var num = itable.rows.length;// 表格所有行数(所有记录数)
	console.log(num);
	var totalPage = 0;// 总页数
	var pageSize = psize;// 每页显示行数
	// 总共分几页
	if (num / pageSize > parseInt(num / pageSize)) {
		totalPage = parseInt(num / pageSize) + 1;
	} else {
		totalPage = parseInt(num / pageSize);
	}
	var currentPage = pno;// 当前页数
	var startRow = (currentPage - 1) * pageSize + 1;// 开始显示的行 31
	var endRow = currentPage * pageSize;// 结束显示的行 40
	endRow = (endRow > num) ? num : endRow;
	40
	console.log(endRow);
	// 遍历显示数据实现分页
	for ( var i = 1; i < (num + 1); i++) {
		var irow = itable.rows[i - 1];
		if (i >= startRow && i <= endRow) {
			irow.style.display = "block";
		} else {
			irow.style.display = "none";
		}
	}
	var pageEnd = document.getElementById("pageEnd");
	var tempStr = "共" + num + "条记录 分" + totalPage + "页 当前第" + currentPage + "页";
	if (currentPage > 1) {
		tempStr += "<a href=\"#\" onClick=\"goPage(" + (1) + "," + psize
				+ ")\">首页</a>";
		tempStr += "<a href=\"#\" onClick=\"goPage(" + (currentPage - 1) + ","
				+ psize + ")\"><上一页</a>"
	} else {
		tempStr += "首页";
		tempStr += "<上一页";
	}

	if (currentPage < totalPage) {
		tempStr += "<a href=\"#\" onClick=\"goPage(" + (currentPage + 1) + ","
				+ psize + ")\">下一页></a>";
		tempStr += "<a href=\"#\" onClick=\"goPage(" + (totalPage) + ","
				+ psize + ")\">尾页</a>";
	} else {
		tempStr += "下一页>";
		tempStr += "尾页";
	}

	document.getElementById("barcon").innerHTML = tempStr;

}

function getLocalTime(nS) {     
   return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');     
}

function getOperType(type) { 
switch (type)
{
case 1:
  x="登录";
  break;
case 2:
  x="修改密码";
  break;
case 3:
  x="添加域名";
  break;
case 4:
  x="注销域名";
  break;
case 5:
  x="设置LOGO";
  break;
case 6:
  x="删除LOGO";
  break;
case 7:
  x="修改密保邮箱";
  break;
case 8:
x="修改管理员邮箱";
break;
case 9:
x="发表公告";
break;
case 10:
x="群发邮件";
break;
case 11:
x="新增黑名单";
break;
case 12:
x="删除黑名单";
break;
case 13:
x="清空黑名单";
break;
case 14:
x="新增白名单";
break;
case 15:
x="删除白名单";
break;
case 16:
x="清空白名单";
break;
case 17:
x="新增域白名单";
break;
case 18:
x="删除域白名单";
break;
case 19:
x="新增用户";
break;
case 20:
x="删除用户";
break;
case 21:
x="启用用户";
break;
case 22:
x="禁用用户";
break;
case 23:
x="编辑用户";
break;
case 24:
x="编辑别名";
break;
case 25:
x="批量导入用户";
break;
case 26:
x="添加分级管理员";
break;
case 27:
x="删除分级管理员";
break;
case 28:
x="新增部门";
break;
case 29:
x="删除部门";
break;
case 30:
x="编辑部门";
break;
case 31:
x="移动部门";
break;
case 32:
x="新增邮件组";
break;
case 33:
x="删除邮件组";
break;
case 34:
x="编辑邮件组";
break;
case 35:
x="设置邮件备份";
break;
case 36:
x="邮件转移";
break;
case 37:
x="IP登录权限";
break;
case 38:
x="限制成员外发";
break;
case 39:
x="开启接口";
break;
case 40:
x="重新获取KEY";
break;
case 41:
x="停用接口";
break;
}
   return x;     
}

function getBatchType(type) { 
switch (type)
{
case 1:
  x="群发邮件";
  break;
case 2:
  x="批量导入成员";
  break;
case 3:
  x="删除公告";
  break;
case 4:
  x="批量添加别名";
  break;
case 5:
  x="发布公告";
  break;
case 6:
  x="RTX帐号关联";
  break;
case 7:
  x="设置企业签名档";
  break;
case 8:
x="取消企业签名档";
break;
case 9:
x="开通成员";
break;
case 0:
x="其他";
break;
}
   return x;     
}

function getLoginType(type) { 
switch (type)
{
case 1:
  x="网页登录";
  break;
case 2:
  x="手机登录";
  break;
case 3:
  x="QQ邮箱App登录";
  break;
case 4:
  x="客户端登录:包括imap,pop,exchange";
  break;
case 5:
  x="其他登录方式";
  break;
}
   return x;     
}

function getMailType(type) { 
switch (type)
{
case 1:
x="发信中";
break;
case 2:
x="被退信";
break;
case 3:
x="发信成功";
break;
case 4:
x="发信失败";
break;
case 11:
x="收信被拦截";
break;
case 12:
x="收信，邮件进入垃圾箱";
break;
case 13:
x="收信成功，邮件在收件箱";
break;
case 14:
x="收信成功，邮件在个人文件夹";
break;
case 0:
x="其他状态";
break;
   return x;     
}
}

function getMail(type) { 
switch (type)
{
case 1:
x="发信";
break;
case 2:
x="收信";
break;
   return x;     
}
}
