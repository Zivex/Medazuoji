$(function(){
	$("body").addClass("theme-blue");
});

String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
Date.prototype.Format = function(fmt) 
{
    //author: meizz 
    var o =
    { 
        "M+" : this.getMonth() + 1, //月份 
        "d+" : this.getDate(), //日 
        "h+" : this.getHours(), //小时 
        "m+" : this.getMinutes(), //分 
        "s+" : this.getSeconds(), //秒 
        "q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
        "S" : this.getMilliseconds() //毫秒 
    }; 
    if (/(y+)/.test(fmt)) 
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
    for (var k in o) 
        if (new RegExp("(" + k + ")").test(fmt)) 
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length))); 
    return fmt; 
};


Date.prototype.addDays = function(d)
{
    this.setDate(this.getDate() + d);
};


Date.prototype.addWeeks = function(w)
{
    this.addDays(w * 7);
};


Date.prototype.addMonths= function(m)
{
    var d = this.getDate();
    this.setMonth(this.getMonth() + m);

    if (this.getDate() < d)
        this.setDate(0);
};


Date.prototype.addYears = function(y)
{
    var m = this.getMonth();
    this.setFullYear(this.getFullYear() + y);

    if (m < this.getMonth()) 
    {
        this.setDate(0);
    }
};

/*functions for query form*/
function initQueryForm(){
	 var options = { 
		       beforeSubmit:  showQueryRequest,  // pre-submit callback 
		       success:       showQueryResponse,  // post-submit callback 
		        type:      'post'      // 'get' or 'post', override for form's 'method' attribute 
		    }; 
		 
		    // bind to the form's submit event 
	 		//$('#command').ajaxForm(options);	
		    $('#command').submit(function() { 
		        $(this).ajaxSubmit(options); 
		        return false; 
		    });
}

function showQueryRequest(formData, jqForm, options) { 
	if($("#pageNo").length>0){
		$("#pageNo").val(1);
	}
	if($("#borad").length>0){
		$("#borad").hide();
	}
	$("#displayDiv").mask("正在完成请求，请稍候！");
	return true;
} 

function showQueryResponse(responseText, statusText, xhr, $form)  { 
	$("#resultDiv").html(responseText);
	$("#displayDiv").unmask();
} 

function queryItems(){
	 $("#command").submit();
}

/*functions for item operation*/
function initListForm(){
	 var options = { 
		       beforeSubmit:  showListRequest,  // pre-submit callback 
		       success:       showListResponse,  // post-submit callback 
		       dataType:  'json',        // 'xml', 'script', or 'json' (expected server response type)
		        type:      'post'      // 'get' or 'post', override for form's 'method' attribute 
		    }; 
		 
		    // bind to the form's submit event 
		    $('#listForm').submit(function() { 
		        $(this).ajaxSubmit(options); 
		        return false; 
		    });
}


function showListRequest(formData, jqForm, options) { 
	$("#displayDiv").mask("正在完成请求，请稍候！");
	return true;
} 

function showListResponse(responseText, statusText, xhr, $form)  { 
	$("#displayDiv").unmask();
	var suc=responseText.success;
	var type=responseText.type;
	var message=responseText.message;
	queryItems();
	$("#message").addClass("alert-"+type);
	$("#message").html(message);
	$("#borad").slideDown();
	setTimeout(function(){$("#borad").slideUp();},5000);
} 

function pageselectCallback(page_index, jq){
	 page_index=page_index+1;//设置第一个为1
	// $("#resultDiv").mask("正在获取数据，请稍候！");
	 $("#pageNo").val(page_index);
	 $("#command").submit();
}

/** 
* Callback function for the AJAX content loader.
*/
function initPagination(total,pageSize,currentPage) {
   var num_entries =total;
   if(pageSize>=total){currentPage=1;}
   // Create pagination element
   $("#result_page").pagination(num_entries, {
       num_edge_entries: 2,
       num_display_entries: 5,
       current_page: currentPage-1,
       callback: pageselectCallback,
       items_per_page:pageSize,
       load_first_page:false,
       next_text:"后一页",
       prev_text:"前一页"
   });
}

function pageselectCallbackSE(page_index, jq){
	page_index=page_index+1;//设置第一个为1
	// $("#resultDiv").mask("正在获取数据，请稍候！");
	 $("#pageNo").val(page_index);
	 $("#command").submit();
}

/** 
* Callback function for the AJAX content loader.
*/
function initPaginationSE(pageDiv,total,pageSize,currentPage) {
   var num_entries =total;
   if(pageSize>=total){currentPage=1;}
   // Create pagination element
   $("#"+pageDiv).pagination(num_entries, {
       num_edge_entries: 2,
       num_display_entries: 5,
       current_page: currentPage-1,
       callback: pageselectCallbackSE,
       items_per_page:pageSize,
       load_first_page:false,
       next_text:"后一页",
       prev_text:"前一页"
   });
}

/*functions for check items*/
function checkAllItems(el){
	var allChecked=$(el).attr("checked");
	if(allChecked){
		$("input[name=ids]").attr("checked",true);
	}else{
		$("input[name=ids]").attr("checked",false);
	}
}

function checkSingleItem(el){
	var allChecked=true;
	$("input[name=ids]").each(function(index){
		var checked=$(this).attr("checked");
		if(!checked){
			allChecked=false;
		}
	});
	$("#allItems").attr("checked",allChecked);
}

function columnChecked(){
	var oneChecked=false;
	$("input[name=ids]").each(function(index){
			var checked=$(this).attr("checked");
			if(checked){
				oneChecked=true;
			}
		});
	return oneChecked;
}

function getCheckedColumns(){
	var ids="";
	$("input[name=ids]").each(function(index){
			var checked=$(this).attr("checked");
			if(checked){
				var val=$(this).val();
				if(""==ids){
					ids=val;
				}else{
					ids=ids+","+val;
				}
			}
		});
	return ids;
}

function initItemHoverEvent(){
	//hover item
	$(".itemview").hover(
		function () {
	    	$(this).addClass("tr_hover");
	 	 },
	  function () {
	    $(this).removeClass("tr_hover");
	  });   
}

/**
 * Ajax 方式提交FORM
 */
function initSaveForm(){
	 var options = { 
		       // target:        'command',   // target element(s) to be updated with server response 
		        beforeSubmit:  showFormRequest,  // pre-submit callback 
		        success:       showFormResponse,  // post-submit callback 
		        // other available options: 
		        //url:       url         // override for form's 'action' attribute 
		        type:      'post',        // 'get' or 'post', override for form's 'method' attribute 
		        dataType:  'json'        // 'xml', 'script', or 'json' (expected server response type) 
		        //clearForm: true        // clear all form fields after successful submit 
		        //resetForm: true        // reset the form after successful submit 
		        // $.ajax options can be used here too, for example: 
		        //timeout:   3000 
		    }; 
		    // bind to the form's submit event 
		    $('#command').submit(function() { 
		        // inside event callbacks 'this' is the DOM element so we first 
		        // wrap it in a jQuery object and then invoke ajaxSubmit 
		        $(this).ajaxSubmit(options); 
		        // !!! Important !!! 
		        // always return false to prevent standard browser submit and page navigation 
		        return false; 
		    });
}

/*
*显示动态对话框
*/
function showDynamicModal(url) {
	$("body").css("height", screen.availHeight);
	$("body").mask("正在加载,请稍候......");
	$("#modal").html("loadding......");
	$.ajaxSetup({cache:false});
	$.get(url, function(result) {
		$("body").unmask();
		$("#modal").html(result);
		$('#modal').modal({
			show : true,
			keyboard : false,
			backdrop : "static"
		});
	});
	
}

/*
*关闭动态对话框
*/
function closeDynamicModal() {
	$('#modal').modal("hide");
	$('#modal').html("");
}

function initialFormValidate(formId){
	$('#'+formId).validate({
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },  
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },  
        errorPlacement : function(error, element) {
			error.addClass("text-danger control-label");
			if (element.is(":radio")) {
				error.appendTo(element.parent().next().next());
			} else if (element.is(":checkbox")) {
				error.appendTo(element.next());
			} else {
				error.appendTo(element.parent());
			}
		}
	});
}

/**
 * 读取卡芯片
 */
function populateCode() {
	var CVR_IDCard = document.getElementById("CVR_IDCard");
	var code = CVR_IDCard.Property1;
	if ("undefined" == code) {
		$("#cardCode").val("");
	}else if ("1" == code) {
		$("#cardCode").val("");
	}else if("2" == code) {
		$("#cardCode").val("");
	}else{
		$("#cardCode").val(code);
	}
}

/**
 * 读取卡芯片获取卡号
 */
function readCard(baseUrl,elementId) {
	var CVR_IDCard = document.getElementById("CVR_IDCard");
	var code = CVR_IDCard.Property1;
	if ("undefined" == typeof(code)) {
		alert("控件加载错误，请正确安装控件后再试。");
		return false;
	}
	if ("1" == code) {
		alert("未检测到读卡器，请确认读卡器已连接。");
		return false;
	}
	if ("2" == code) {
		alert("未检测到卡片，请确认卡片已放置到读卡器上。");
		return false;
	}
	//
	var url=baseUrl+'biz/card/get_cardno_by_code.shtml?cardCode='+code;
	$.get(url,function(data,status){
		$("#"+elementId).val(data);
	});
}
/**
 * 将form的get提交变为post
 */
function  formGetToPost(url){
	//url = "business/matter.shtml?data1=v1&data2=v2"
	var action = url.substring(0,url.indexOf("?"));
	var data = url.substring(url.indexOf("?")+1, url.length).split("&");
	$("#postFrom").remove();
	$("html").append("<form id='postFrom' method='post'></form>");
	for(var i=0;i<data.length;i++){
		$("#postFrom").append("<input type='text' name='"+data[i].substring(0,data[i].indexOf("="))+"' value= '"+data[i].substring(data[i].indexOf("=")+1,data[i].length)+"'></form>")
	}
	$("#postFrom").attr("action",action);
	$("#postFrom").submit();
}