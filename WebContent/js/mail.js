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
			+ "<h1 style='margin:0;'>邮件概况<div class='info'>可查询最近三个月的邮件概况</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='text' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='text' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>域名：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<input  class='txt time_li'  type='text' id='domain'></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='msajax()'>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div>";
}

function mailinfo() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>邮件查询<div class='info'>可查询最近三个月的邮件概况</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='text' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='text' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>邮箱账号：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<input  class='txt time_li'  type='text' id='userid'></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='miajax()>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div>";
}

function loginlog() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>登录查询<div class='info'>可查询最近三个月的邮件概况</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='text' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='text' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>邮箱账号：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<input  class='txt time_li'  type='text' id='userid'></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='llajax()>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div>";
}

function oprlog() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>操作查询<div class='info'>可查询最近三个月的邮件概况</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='text' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='text' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>操作人员：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<input  class='txt time_li'  type='text' id='operator'></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='olajax()>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div>";
}

function batchlog() {
	document.getElementById('maintxt').innerHTML = "<div class='content_block'>"
			+ "<h1 style='margin:0;'>批量任务查询<div class='info'>可查询最近三个月的邮件概况</div></h1>"
			+ "<div id='domainQuery'  class='contentInner con_toolbar con_gk'>"
			+ "<span un='datepick' style='display:block;'><span class='tool_li'>"
			+ "<span class='addrtitle f_size'>开始日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt'  type='text' id='begintime'>"
			+ "<span class='arrow'></span></span></span>"
			+ "<span class='tool_li'><span class='addrtitle f_size'>结束日期：</span><span class='addrinput time_li'>"
			+ "<input  class='txt time_li'  type='text' id='endtime'>"
			+ "<span class='arrow' ></span></span></span></span><div class='tool_li'>"
			+ "<span class='addrtitle f_size'>操作人员：</span>"
			+ "<span class='addrinput' style='position:relative;'>"
			+ "<input  class='txt time_li'  type='text' id='operator'></span></div>"
			+ "<div class='tool_li_btn left'><a class='btn btnNormal' onclick='blajax()>&nbsp;查&nbsp;询&nbsp;</a>"
			+ "</div></div></div>";
}

function msajax() {
	var begintime = document.getElementById("begintime").value;
	var endtime = document.getElementById("endtime").value;
	var domain = document.getElementById("domain").value;
	$
			.ajax({
				type : "POST",
				url : "/HuoBiLog/queryms", // event handler url
				data : {
					begintime : begintime,
					endtime : endtime,
					domain : domain
				},
				dataType : "json",
				success : function(msg) {
					var table_ms = '<div class="content_table" >'
							+ '<div style="width: 100%;"><div class="caption" style="text-align: right"></div>'
							+ '<table cellspacing="0" align="center" class="infoTable domainInfo listTable" style="width: 100%; table-layout: fixed;" id="mstable">'
							+ '<tbody>' + '<tr><th style="width: 5%"></th>'
							+ '<th style="width: 15%;cursor=default;">序号</th>'
							+ '<th style="width: 18%;">时间</th>'
							+ '<th style="width: 18%;"></th>'
							+ '<th style="width: 16%;">发信量</th>'
							+ '<th style="width: 14%;">收件量</th>'
							+ '<th style="width: 14%;"></th>' + '</tr>';
					for ( var o in msg) {
						table_ms = table_ms + '<tr><td></td><td>' + msg[o].no
								+ ' </td><td>' + msg[o].time
								+ '</td><td></td><td>' + msg[o].info.sendsum
								+ '</td><td>' + msg[o].info.recvsum
								+ '</td><td></td></tr>';
					}
					table_ms = table_ms
							+ '</tbody></table>'
							+ '<div class="caption" style="text-align: right"></div></div></div>';
					document.getElementById('maintxt').innerHTML += table_ms;
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