

String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt) { //author: meizz
	var o = {
		"M+" : this.getMonth()+1,                 //月份
		"d+" : this.getDate(),                    //日
		"h+" : this.getHours(),                   //小时
		"m+" : this.getMinutes(),                 //分
		"s+" : this.getSeconds(),                 //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
}
var post = function(url, data,sucCallback,errorCallback,options) {
	if (!url) {
		alert("url 错误");
	}
	if (!sucCallback) {
		sucCallback = function(data) {
			if (data.message) {
				alert(data.message);
			} else {
				alert("操作成功");
			}
		}
	}
	$.ajax($.extend({
		url : url,
		type : 'post',
		data : data,
		dataType : "json",
		timeout : 10000000,
		error : function(data) {
			alert(data);
			if (errorCallback) {
				errorCallback(data);
			}
		},
		success : sucCallback
	},options));
	return false;
};

var syspost = function(url, data, sucCallback, datatype) {
	if (!url) {
		alert("url 错误");
	}
	if (!datatype) {
		datatype = "json";
	}
	if (!sucCallback) {
		sucCallback = function(data) {
			if (data.message) {
				alert(data.message);
			} else {
				alert("操作成功");
			}
		}
	}
	$.ajax({
		async : false,

		url : url,

		type : 'POST',

		data : data,

		dataType : datatype,

		timeout : 1000000,

//		error : function(){
//            alert("error");
//        },

		success : sucCallback

	});
	return false;
};

var getFormJson = function(form) {
	var o = {};
	var a = $(form).serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined && this.value) {
			o[this.name] = o[this.name] + "," + this.value;
			// if (!o[this.name].push) {
			// o[this.name] = [ o[this.name] ];
			// }
			// o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
var isArray = function(value) {
	if (value instanceof Array
			|| (!(value instanceof Object) && (Object.prototype.toString.call((value)) == '[object Array]') || typeof value.length == 'number'
					&& typeof value.splice != 'undefined'
					&& typeof value.propertyIsEnumerable != 'undefined'
					&& !value.propertyIsEnumerable('splice'))) {
		return 'array';
	}
};
var indexOf = function(arr, val) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == val)
			return i;
	}
	return -1;
};
var remove = function(arr, val) {
	var index = indexOf(arr, val);
	if (index > -1) {
		arr.splice(index, 1);
	}
};
var lengthOfMap = function(map) {
	return $.map(map, function(n, i) {
		return i;
	}).length;
};

var useTemplateId = function(id, data) {
    var source = $("#" + id).html();
    return useTemplate(source, data);
};
var useTemplate = function(source, data) {
    var template = Handlebars.compile(source);
    return template(data);
};
Handlebars.registerHelper('equal', function(v1, v2, options) {
    if (v1 == v2) {
        // 满足添加继续执行
        return options.fn(this);
    } else {
        // 不满足条件执行{{else}}部分
        return options.inverse(this);
    }
});
Handlebars.registerHelper('ifeq', function(v1, v2, options) {
    if (v1 == v2) {
        // 满足添加继续执行
        return options.fn(this);
    } else {
        // 不满足条件执行{{else}}部分
        return options.inverse(this);
    }
});

Handlebars.registerHelper('ifne', function(v1, v2, options) {
    if (v1 != v2) {
        // 满足添加继续执行
        return options.fn(this);
    } else {
        // 不满足条件执行{{else}}部分
        return options.inverse(this);
    }
});

Handlebars.registerHelper('sub_show', function(str, length, options) {
    if (!str)
        str = "";
    str = "" + str;
    if (str.length > length) {
        return str.substr(0, length) + "...";
    } else {
        return str;
    }
});

Handlebars.registerHelper('toFixed', function(data, length, options) {
    if (!data){
        return data;
    }
    return data.toFixed(length);
});
