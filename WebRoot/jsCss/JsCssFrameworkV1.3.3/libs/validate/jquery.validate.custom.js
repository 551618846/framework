/*邮箱格式*/
$.validator.addMethod("myEmail", function (value, element) {
    return this.optional(element) || isEmail(value);
}, "请输入正确的邮箱地址");
/*手机号码格式验证*/
$.validator.addMethod("mobile", function (value, element) {
    var mobile = /^((\+86)|(86))?(13|18|15)\d{9}$/;
    return this.optional(element) || mobile.test(value);
}, "请输入正确的手机号码");
/*密码格式*/
$.validator.addMethod("myPassword", function (value, element) {
    return this.optional(element) || passworLength(value);
}, "只能输入：字母、数字、下划线,长度在4~20位字符之间");
/*HTML格式*/
$.validator.addMethod("htmlText", function (value, element) {
    return this.optional(element) || isHtml(value);
}, "输入了非法字符");
/*只能输入中英文*/
$.validator.addMethod("htmlEnCn", function (value, element) {
    return this.optional(element) || isEnCn(value);
}, "输入了非法字符");

/*只能输入英文*/
$.validator.addMethod("myEnglish", function (value, element) {
    return this.optional(element) || isEn(value);
}, "请输入英文");

/*验证邮政编码的格式*/
$.validator.addMethod("myZipCode", function (value, element) {
    var reg = /^\d{6}$/;
    return this.optional(element) || reg.test(value);
}, "请输入正确的邮政编码");

/*验证QQ是否正确*/
$.validator.addMethod("myQQ", function (value, element) {
    var reg = /^\d{5,12}$/;
    if (value == "" || reg.test(value)) {
        return this.optional(element) || true;
    }
    return this.optional(element) || false;
}, "QQ输入有误，请重新输入");

/*验证身份证是否正确*/
$.validator.addMethod("myIdCard", function (value, element,params) {
    return this.optional(element) || validateIdCard(params);
}, "身份证输入有误，请重新输入");

/*验证电话号码是否正确*/
$.validator.addMethod("myHomePhone", function (value, element) {
    return this.optional(element) || validateHomePhone(value);
}, "请输入正确的电话号码");

/*验证身高是否正确*/
$.validator.addMethod("myHeight", function (value, element) {
    return this.optional(element) || validateHeight(value);
}, "请输入正确的身高");


//异步验证邮箱是否存在
//params[0]:URL
//params[1]:本来邮箱：编辑时使用
$.validator.addMethod("ajaxEmail", function (value, element, params) {
    var result = false;
    //验证的值与原来的值一样
    if (value == params[1])
        return true;

    $.ajax({
        url: params[0],
        data: { email: value },
        dataType: "json",
        contentType: "application/json",
        async: false,
        cache: false,
        success: function (msg) {
            result = msg.Status;
        }
    })
    return result;
}, "邮箱地址已存在，请重新输入");

//验证年龄
$.validator.addMethod("compareAge", function (value, element, parms) {

    var startAge = parseInt($(parms).val());
    var endAge = parseInt(value);
    if (startAge == "NaN" && endAge == "NaN")
        return true;
    return startAge < endAge;

}, "结束年龄必须大于开始年龄");

//验证日期
$.validator.addMethod("compareDate", function (value, element, params) {

    var startDate = params[0].realValue == undefined ? params[0].value : params[0].realValue;
    var endDate = params[1].realValue == undefined ? params[1].value : params[1].realValue;
    if (startDate == "" || endDate == "" || startDate == "NaN" || endDate == "NaN")
        return true;
    return (Date.parse(new Date(startDate.replace(/-/g, "/"))) - Date.parse(new Date(endDate.replace(/-/g, "/")))) < 0;
}, "结束日期必须大于开始日期");

//验证邮箱规则是否正确
function isEmail(value) {
    var reg = /^[a-z0-9](([_.-]*[a-z0-9]+)*)@([a-z0-9]+)(([_.-]*[a-z0-9]+)*)\.([a-z]{2,})$/i;
    if (reg.test(value))
        return true;
    return false;
}

//验证密码
function passworLength(value) {
    var reg = /^\w{4,20}$/;
    if (reg.test(value))
        return true;
    return false;
}

/*** 非法字符判断 ***/
function isHtml(value) {
    var reg = /<(S*?)[^>]*>.*?|< .*?\/>/;
    if (reg.test(value)) {
        return false;
    }
    return true;
}

/****  身份证验证 *****/
function validateIdCard(params) {
    if (params[0].find("option:selected").text().trim() == "身份证") {
        if (!isIdCardNo(params[1].val())) {
            return false;
        }
    }
    return true;
}

/*** 身份证号码验证 ***/
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intVal;
    var lngProd = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < "0" || varArray[i] > "9") && (i != 17)) {
            return false;
        }
        else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        for (var i = 0; i < 17; i++) {
            lngProd = lngProd + varArray[i];
        }
        intCheckDigit = parityBit[lngProd % 11];
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {       
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}
/*** 判断是否为“YYYYMMDD”式的时期 ***/
function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);

    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    if (year < 1700 || year > 2500) return false;
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false;
    if (day < 1 || day > iaMonthDays[month - 1]) return false;
    return true;
}

/*** 判断是否为“YYYYMM”式的时期 ***/
function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false;
    if (month < 1 || month > 12) return false;
    return true;
}

/*** 非法字符判断只能输入中文和英文 ***/
function isEnCn(value) {
    var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
    if (reg.test(value)) {
        return true;
    }
    return false;
}

/*** 非法字符判断只能输入英文 ***/
function isEn(value) {
    var reg = /^[a-zA-Z]+$/;
    if (reg.test(value)) {
        return true;
    }
    return false;
}

/****  电话号码验证 *****/
function validateHomePhone(value) {
    var reg2 = /^\d{7,8}$/;
    var reg3 = /^0\d{2,3}\-\d{7,8}$/
    if (!reg2.test(value)) {
        if (reg3.test(value)) {
            return true;
        }
        return false;
    } else {
        return true;
    }
}
/*验证身高，如果发现巨人族得改改高度*/
function validateHeight(value) {
    var reg = /^\d{1,3}$/;
    if (reg.test(value) && value <= 350) {
        return true;
    }
    return false;
}
