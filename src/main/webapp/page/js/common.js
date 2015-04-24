

String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};

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
