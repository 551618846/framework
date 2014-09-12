//过滤所有html标签
String.prototype.filterHTML = function () {
    return this.replace(/<[^>]+>/gi, "");
};
//获取字符串长度（一个中文两个字符）
String.prototype.getLength = function() {
    return this.replace(/[^x00-xff]/g, '  ').length;
};
//截取字符串（一个中文两个字符）
String.prototype.subString = function (maxLength) {
    var reg = /[^\x00-\xff]/g;
    var tmpStr = this;
    if (this.replace(reg, "**").length <= maxLength) {
        return this;
    }
    for (var i = 0; i < this.length; i++) {
        tmpStr = this.substring(0, i);
        if (tmpStr.replace(reg, "**").length >= maxLength) {
            return tmpStr + "...";
        }
    }
    return tmpStr;
};
//获取文件名
String.prototype.getFileName = function() {
    var pos = this.lastIndexOf("\\");
    return this.substring(pos + 1);
};
//去除字符串左右空格
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};
//字符串是否为空
String.prototype.isEmpty = function () {
    return this.trim().length == 0 ? true : false;
};
//是否电子邮件
String.prototype.isEmail = function () {
    var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    //var reg = "/^[a-z0-9](([_.-]*[a-z0-9]+)*)@([a-z0-9]+)(([_.-]*[a-z0-9]+)*)\.([a-z]{2,})$/i";
    return reg.test(this);
};
//获取数字
String.prototype.getDateStrNumber = function () {
    var reg = /(\d+){1}/,
        arrDateMatches = this.match(reg);
    if (arrDateMatches.length == 0) {
        return "";
    } else {
        return arrDateMatches[0];
    }
};
//是否IP地址
String.prototype.isIp = function () {
    var reg = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    return reg.test(this);
};
// 判断输入的邮编(只能为六位)是否正确
String.prototype.isZIP = function () {
    var reg = /^\d{6}$/;
    return reg.test(this);
};
// 判断输入的身份证号(15，18位)是否正确
String.prototype.isIDCard = function () {
    var reg = /(\d{15}$)|(\d{17}(?:\d|x|X)$)/;
    return reg.test(this);
};
//是否整数
String.prototype.isInteger = function () {
    var reg = /^\d+$/;
    return reg.test(this);
};
//格式化日期
Date.prototype.format = function (formatStr) {
    formatStr = formatStr.replace(/"/g, "");
    var o = {
        "y+": this.getFullYear(),
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds()
    };
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(formatStr)) {
            var value = o[k];
            formatStr = formatStr.replace(RegExp.$1, value.toString().length == 1 ? ("0" + value.toString()) : value);
        }
    }
    return formatStr;
};
//数组索引查找
if (!Array.indexOf) {
    Array.prototype.indexOf = function(obj) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == obj) {
                return i;
            }
        }
        return -1;
    };
}
//获取url参数
String.prototype.getUrlParms = function(url) {
    var obj = {},
        str,
        arrStr;
    if (typeof url == "undefined") {
        return null;
    }
    if (url.indexOf("?") == -1) {
        return null;
    }
    str = url.substring(url.indexOf("?") + 1, url.length - 1);
    arrStr = str.split("&");
    for (var i = 0; i < arrStr.length; i++) {
        obj[arrStr[i].split("=")[0]] = arrStr[i].split("=")[1];
    }
    return obj;
};

//随机数字
function getRandNum() {
    return Math.round(Math.random() * 10000);
}
//调用方法
function callFunc(funcName, param1, param2, param3, param4, param5) {
    if (funcName != undefined) {
        if (funcName.indexOf("(") < 0) {
            funcName += "(";
            if (param1 != undefined) {
                funcName += "param1";
            }
            if (param2 != undefined) {
                funcName += ",param2";
            }
            if (param3 != undefined) {
                funcName += ",param3";
            }
            if (param4 != undefined) {
                funcName += ",param4";
            }
            if (param5 != undefined) {
                funcName += ",param5";
            }
            funcName += ")";
        }
        eval(funcName);
    }
}