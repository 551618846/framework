/*core.js*/
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
        return eval(funcName);
    }
    return null;
}
/*corebox.js*/
(function ($) {
    var BoxList = new Array();
    $.Setting = {};
    $.ButtonBox = function (event, options,setting) {
        var boxId = event.attr("data-box-Id");
        var _this = BoxList[boxId];
        if (_this == undefined) {
            _this = this;
            _this.init(event, options,setting);
            BoxList[_this.Id] = _this;
        }
        return _this;
    };

    $.extend($.ButtonBox, {
        defaults: {
            buttons: [],
            setting: {},
            readonly: true,
            attrName: "buttonBox_",
            initParameter: function(event,control, options) {
                event.options = $.extend(true, {}, $.ButtonBox.defaults, options);
            },
            initControls:function(event, options) {
                
            },
            click: function(event, setting) {
            }
        },
        buttonDefaults: {
            code: "",
            text: "",
            icon: "glyphicon-list",
            dispaly:true
        },
        prototype: {
            //文本框
            $TextBox: null,
            //值框
            $TargetBox: null,
            //弹出层
            $AsyncBox: null,
            text: function(val) {
                if (this.$TextBox == undefined) {
                    return null;
                }
                if (val == undefined) {
                    return this.$TextBox.val();
                }
                this.$TextBox.val(val);
                this.$TextBox.focusout();
                return null;
            },
            value: function(val) {
                if (this.$TargetBox == undefined) {
                    return null;
                }
                if (val == undefined) {
                    return this.$TargetBox.val();
                }
                this.$TargetBox.val(val);
                return null;
            },
            initControls: function(isButton) {
                var _this = this;
                _this.$Buttons = new Array();
                if (isButton == undefined || !isButton) {
                    //初始化Group
                    if (_this.$TextBox.parent().attr("class") != "input-group") {
                        _this.$InputGroup = $("<div class=\"input-group left\"></div>");
                        _this.$TextBox.after(_this.$InputGroup);
                        _this.$TextBox.appendTo(_this.$InputGroup);
                    } else {
                        _this.$InputGroup = _this.$TextBox.parent();
                    }
                    //初始化Target
                    var targetId = _this.$TextBox.attr("data-target");
                    if (targetId != undefined) {
                        if ($("#" + targetId).length > 0) {
                            _this.$TargetBox = $("#" + targetId);
                        } else if ($("input[name='" + targetId + "']").length > 0) {
                            _this.$TargetBox = $("input[name='" + targetId + "']");
                        } else {
                            _this.$TargetBox = $("<input type=\"hidden\" id=\"" + targetId + "\" name =\"" + targetId + "\" />");
                            _this.$TextBox.parent().after(_this.$TargetBox);
                        }
                        _this.value(_this.$TextBox.attr("data-value"));
                    }
                    //初始化
                    _this.$ButtonGroup = $("<span class=\"input-group-btn\"></span>");
                    if (_this.$TextBox.hasClass("lg")) {
                        _this.$ButtonGroup.addClass("lg");
                    }
                    var hasButton = false;
                    $(_this.options.buttons).each(function() {
                        var btnSetting = $.extend(true, {}, $.ButtonBox.buttonDefaults, this);
                        if (btnSetting.display) {
                            hasButton = true;
                            var button = $("<button class=\"btn btn-default\" type=\"button\" value=\"\"><span class=\"glyphicon "
                                + btnSetting.icon + "\"></span>" + btnSetting.text + "</button>");
                            _this.$ButtonGroup.append(button);
                            if (btnSetting.click == undefined) {
                                button.click(function () {
                                    _this.options.click(_this, _this.options.setting);
                                });
                            } else {
                                button.click(function () {
                                    btnSetting.click(_this, _this.options.setting);
                                });
                            }
                            _this.$Buttons.push(button);
                        }
                    });
                    if (hasButton) {
                        _this.$TextBox.after(_this.$ButtonGroup);
                    }
                    if (_this.options.readonly) {
                        _this.$TextBox.attr("readonly", "readonly");
                        _this.$TextBox.click(function () {
                            _this.options.click(_this, _this.options.setting);
                        });
                    }
                } else {
                    _this.$Buttons.push(_this.$SelectButton);
                    _this.$SelectButton.click(function() {
                        _this.options.click(_this, _this.options.setting);
                    });
                    //初始化TextBox
                    var textBoxId = _this.$SelectButton.attr("name");
                    if (textBoxId != undefined) {
                        if ($("#" + textBoxId).length > 0) {
                            _this.$TextBox = $("#" + textBoxId);
                        } else if ($("input[name='" + textBoxId + "']") > 0) {
                            _this.$TextBox = $("input[name='" + textBoxId + "']");
                        } else {
                            _this.$TextBox = $("<input type=\"hidden\" id=\"" + textBoxId + "\" name =\"" + textBoxId + "\" />");
                            _this.$SelectButton.after(_this.$TargetBox);
                        }
                    }
                    //初始化Target
                    var targetId = _this.$SelectButton.attr("data-target");
                    if (targetId != undefined) {
                        if ($("#" + targetId).length > 0) {
                            _this.$TargetBox = $("#" + targetId);
                        } else if ($("input[name='" + targetId + "']") > 0) {
                            _this.$TargetBox = $("input[name='" + targetId + "']");
                        } else {
                            _this.$TargetBox = $("<input type=\"hidden\" id=\"" + targetId + "\" name =\"" + targetId + "\" />");
                            _this.$SelectButton.after(_this.$TargetBox);
                        }
                        _this.value(_this.$TargetBox.val());
                    }
                }
            },
            init: function(event,options,setting) {
                var _this = this;
                _this.options = $.extend(true, {}, $.ButtonBox.defaults, options);
                var isButton = !event.is("input");
                if (isButton) {
                    _this.$SelectButton = event;
                    if (_this.$SelectButton.attr("name") != undefined) {
                        _this.Id = _this.options.attrName + _this.$SelectButton.attr("name");
                    } else if (_this.$SelectButton.attr("id") != undefined) {
                        _this.Id = _this.options.attrName + _this.$SelectButton.attr("id");
                    }
                    _this.$SelectButton.attr("data-box-Id", _this.Id);
                } else {
                    _this.$TextBox = event;
                    _this.Id = _this.options.attrName + _this.$TextBox.attr("name");
                    _this.$TextBox.attr("data-box-Id", _this.Id);
                }
                if (options == undefined || options.initParameter == undefined) {
                    $.ButtonBox.defaults.initParameter(_this, event, setting);
                } else {
                    options.initParameter(_this, event, setting);
                }
                _this.initControls(isButton);
                _this.options.initControls(_this, _this.options);
            },
            //弹出选择
            clear: function() {
                var _this = this;
                _this.value("");
                _this.text("");
            }
        }
    });
})(jQuery);
/*ajaxform.js*/


(function ($) {
    var ajaxFormList = new Array();
    $.ajaxForm = function (form, options) {
        var formId = form.attr("data-ajaxform-Id");
        var _this = ajaxFormList[formId];
        if (_this == undefined) {
            _this = this;
            _this.init(form, options);
            ajaxFormList[_this.formId] = _this;
        }
        return _this;
    };
    //AjaxForm
    $.fn.ajaxForm = function (options) {
        var ajaxForm =  new $.ajaxForm($(this[0]), options);
        return ajaxForm;
    };
    $.ajaxForm.submit = function(callback) {
        $("form").ajaxForm().submit(callback);
    };
    $.extend($.ajaxForm, {
        defaults: {
            //提交前执行方法
            before: function ($form) {
            },
            //验证方法
            validate: function ($form) {
                return $form.validate().form();
            },
            //操作成功响应方法
            success: function (status) {
                if (status.Message != undefined && status.Message.length > 0) {
                    $.success(status.Message, "操作成功", function() {
                        if (status.Url != undefined && status.Url.length > 0)
                            location.href =status.Url;
                    });
                } else if (status.Url != undefined && status.Url.length > 0) {
                    location.href = status.Url;
                }
            },
            //操作失败响应方法
            fail: function (status) {
                if (status.ErrorMessage != undefined && status.ErrorMessage.length > 0) {
                    $.error(status.ErrorMessage, "操作错误");
                } 
            },
            //操作传输异常响应方法
            error:  function (data) {
                $.error("系统繁忙，请稍后再试。","系统异常");
            },
            //绑定验证方法
            init: function ($form) {
                $form.validate({
                    meta: "validate",
                    showLabel: function (element, message, settings) {
                        var $element = $(element);
                        var label = $(settings.errorElement + "[for='" + element.id + "']");
                        if (label.length) {
                            label.removeClass(settings.validClass).addClass(settings.errorClass);
                            label.html(message);
                        } else {

                        }
                    }
                });
            }           
        },
        prototype: {
            $form : null,
            $submitButton :null,
            $textBoxList : null,
            options : null,
            init:function (form,options) {
                var _this = this;
                _this.$form = form;
                _this.formId = _this.$form.attr("data-ajaxform-Id");
                if (_this.formId == undefined || _this.formId == "") {
                    _this.$form.attr("data-ajaxform-Id", "ajaxForm_" + _this.$form.attr("Id"));
                    _this.formId = _this.$form.attr("data-ajaxform-Id");
                }
                _this.$submitButton = _this.$form.find("[type='submit']");
                _this.options = $.extend(true, {}, $.ajaxForm.defaults, options);
                _this.$form.submit(function() {
                        _this.submit();
                        return false;
                    });
                _this.$submitButton.submit(function() {
                        _this.submit();
                        return false;
                    });
                _this.$submitButton.click(function() {
                        _this.submit();
                        return false;
                    });
                _this.options.init(_this.$form);
            },
            submit: function (callback) {
                var _this = this;
                try {
                    _this.$submitButton.attr("disabled", "disabled");
                    _this.options.before(_this.$form);
                    if (!_this.options.validate(_this.$form)) {
                        _this.$submitButton.removeProp("disabled");
                        return false;
                    }
                    var url = _this.$form.attr("action");
                    var data = _this.$form.serialize();
                    $.ajax({
                        type: "post",
                        url: url,
                        data: data,
                        cache: false,
                        success: function(returnStatus) {
                            _this.$submitButton.removeProp("disabled");
                            if (returnStatus.Status) {
                                if (typeof callback == 'function') {
                                    callback(returnStatus);
                                } else {
                                    _this.options.success(returnStatus);
                                }
                            } else {
                                _this.options.fail(returnStatus);
                            }
                        },
                        error: function(returnData) {
                            _this.$submitButton.removeProp("disabled");
                            _this.options.error(returnData);
                        }
                    });
                } catch(ex) {
                    _this.$submitButton.removeProp("disabled");
                }
                return false;
            }
        }
    });
})(jQuery);
/*inputbox.js*/
$(function () {
    //$(".input").inputBox();
});
(function ($) {
    var inputBoxList = new Array();
    $.InputBox = function (input, options) {
        var boxId = input.attr("data-inputBox-Id");
        var _this = inputBoxList[boxId];
        if (_this == undefined) {
            _this = this;
            _this.init(input, options);
            inputBoxList[_this.Id] = _this;
        }
        return _this;
    };
    $.fn.inputBox = function (options) {
        var boxList = new Array();
        this.each(function () {
            var _this = $(this);
            var inputBox = new $.InputBox(_this, options);
            boxList.push(inputBox);
        });
        if (boxList.length == 1)
            return boxList[0];
        else {
            return boxList;
        }
    };
    $.extend($.InputBox, {
        defaults: {
            css: {
                icon: "inputIcon",
                inputting:"inputting"
            }
        },
        prototype: {
            //文本框
            $InputBox: null,
            init: function (input, options) {
                var _this = this;
                _this.options = $.extend(true, {}, $.InputBox.defaults, options);
                _this.$InputBox = input;
                _this.Id = "inputBox_" + _this.$InputBox.attr("name");
                _this.$InputBox.attr("data-inputBox-Id", _this.Id);

                _this.$InputBox.focus(function() {
                    var htmlStr = $("<span class=\""+ _this.options.css.icon +"\"></span>");
                    htmlStr.appendTo($($(this).parents()[0]));
                    var getWidth = 0;
                    var getHeight = 0;
                    if (this.type == "textarea") {
                        getWidth = _this.$InputBox.width() - 16;
                        getHeight = 10;
                    } else {
                        getWidth = _this.$InputBox.width();
                        getHeight = (_this.$InputBox.height() - 11);
                    }
                    htmlStr.css({
                        "top": _this.$InputBox.offset().top + getHeight,
                        "left": _this.$InputBox.offset().left +getWidth
                    });
                }).blur(function() {
                    $("." + _this.options.css.icon).remove();
                });
                _this.$InputBox.mouseover(function() {
                    $(this).addClass(_this.options.css.inputting);
                }).mouseout(function() {
                    $(this).removeClass(_this.options.css.inputting);
                });
            }
        }
    });
})(jQuery);

/*datebox.js*/
$(function () {
    //$(".dateBox").dateBox();
});
(function($) {
    //SelectBox
    $.fn.dateBox = function(setting) {
        var boxList = new Array();
        this.each(function() {
            var _this = $(this);
            var dateBox = new $.ButtonBox(_this, $.Setting.DateBox, setting);
            boxList.push(dateBox);
        });
        if (boxList.length == 1)
            return boxList[0];
        else {
            return boxList;
        }
    };
    $.extend($.Setting, {
        DateBox: {
            buttons: [{
                text: "",
                icon: "glyphicon-calendar",
                display: true
            }],
            setting: {
                dateFmt: 'yyyy-MM-dd 00:00:00'
            },
            readonly: true,
            attrName: "dateBox_",
            initParameter: function (event, control, setting) {
                event.options.setting = $.extend(true, {}, $.Setting.DateBox.setting, setting);
                if (setting == undefined && control.attr("data-setting") != undefined) {
                    event.options.setting = eval('(' + control.attr("data-setting") + ')');
                } else if (setting == undefined && control.attr("data-dateFmt") != undefined) {
                    event.options.setting.dateFmt = control.attr("data-dateFmt");
                }
                if (control.attr("data-button-display") != undefined) {
                    event.options.buttons[0].display = control.attr("data-button-display") == "true";
                }
                if (control.attr("id") == undefined) {
                    control.attr("id", event.Id);
                    event.options.setting.el = event.Id;
                } else {
                    event.options.setting.el = control.attr("id");
                }
            },
            click: function (event, setting, callback) {
                WdatePicker(setting);
            }
        }
    });
})(jQuery);

/*dropdownbox.js*/
$(function () {
    //$("input.treeBox").dropDownBox();
    //$("input.listBox").dropDownBox({ selectType: "list" });
});
(function($) {
    //SelectBox
    $.fn.dropDownBox = function(setting) {
        var boxList = new Array();
        this.each(function() {
            var _this = $(this);
            var dropDownBox = new $.ButtonBox(_this, $.Setting.DropDownBox, setting);
            boxList.push(dropDownBox);
        });
        if (boxList.length == 1)
            return boxList[0];
        else {
            return boxList;
        }
    };

    $.extend($.Setting, {
        DropDownBox: {
            buttons: [{
                text: "",
                icon: "glyphicon-chevron-down",
                display: true
            }],
            setting: {
                url: undefined,
                data: undefined,
                selectType: "tree", //tree,list
                multiple: false,
                //选中事件
                onSelect:function(data) {
                }
            },
            readonly: true,
            attrName: "dropdownBox_",
            initParameter: function (event, control, setting) {
                event.options.setting = $.extend(true, {}, $.Setting.DropDownBox.setting, setting);
                if (setting == undefined || setting.url == undefined) {
                    event.options.setting.url = control.attr("data-url");
                }
                if (control.attr("data-multiple") != undefined) {
                    event.options.setting.multiple = control.attr("data-multiple") == "true";
                }
                if (control.attr("data-readonly") != undefined) {
                    event.options.readonly = control.attr("data-readonly") == "true";
                }
                if (control.attr("data-selectType") != undefined) {
                    event.options.setting.selectType = control.attr("data-selectType");
                }
                if (control.attr("data-onSelect") != undefined) {
                    //event.options.setting.onSelect = eval(control.attr("data-onSelect")+"()");
                    event.options.setting.onSelect = function(evt) {
                        callFunc(control.attr("data-onSelect"), evt);
                    };
                }
            },
            initControls:function(event, options) { 
                event.$SelectAll=$("<span class=\"checkBoxAll glyphicon glyphicon-ok-sign\"></span>");
                event.$SelectNone=$("<span class=\"checkBoxAll glyphicon glyphicon-minus-sign\"></span>");
                event.$SelectContent = $("<div class=\"selectPanel\"></div>");
               //if (event.options.setting.multiple){
               //  event.$SelectContent.append(event.$SelectNone);
               //  event.$SelectContent.append(event.$SelectAll);
               // }
                event.$SelectContent.attr("id", event.Id + "_Content");
                event.$SelectTree = $("<ul class=\"ztree\" style=\"margin-top:0;\"></ul>");
                event.$SelectTree.attr("id", event.Id + "_Tree");
                event.$ButtonGroup.append(event.$SelectButton);
                event.$ButtonGroup.parent().after(event.$SelectContent);
                event.$SelectContent.append(event.$SelectTree);
                event.$SelectContent.attr("data-maxHeight", event.$SelectContent.height());
                
                if (options.setting.url != undefined) {
                    var obj = event;
                    $.ajax({
                        url: options.setting.url,
                        dataType: "text",
                        success: function (data) {
                            data = eval(data);
                            options.initTree(obj, data);
                        }
                    });
                    //$.getJSON(_this.options.setting.url, function(data) {
                    //    _this.options.initTree(_this, data);
                    //});
                } else {
                    event.options.initTree(event, event.options.setting.data);
                }
                //checkbox all
                event.$SelectAll.click(function() {
                    var zTree = $.fn.zTree.getZTreeObj(event.$SelectTree.attr("id"));
                    zTree.checkAllNodes(true);                    
                    event.options.setValue(event);
                });
                

                 //checkbox none
                event.$SelectNone.click(function() {
                    var zTree = $.fn.zTree.getZTreeObj(event.$SelectTree.attr("id"));
                    var nodes = zTree.getCheckedNodes();
                    if (nodes.length > 0) {
                        zTree.checkAllNodes(false);
                        event.options.setValue(event);

                    }
                });
                event.close = function() {
                    event.$SelectContent.fadeOut("fast");
                    $("body").unbind("mousedown");
                };
            },
            setValue: function (event) {
                var zTree = $.fn.zTree.getZTreeObj(event.$SelectTree.attr("id")),
                        nodes,
                        value = "",
                        text = "";
                if (event.options.setting.multiple) {
                    nodes = zTree.getCheckedNodes(true);
                } else {
                    nodes = zTree.getSelectedNodes();
                }
                for (var i = 0, l = nodes.length; i < l; i++) {
                    text += nodes[i].name + ",";
                    value += nodes[i].id + ",";
                }
                if (text.length > 0) text = text.substring(0, text.length - 1);
                if (value.length > 0) value = value.substring(0, value.length - 1);
                event.value(value);
                event.text(text);
                return { text: text, value: value };
            },

            click: function (event, setting, callback) {
                var windowHeigh=$(window).height();
                var textBoxHeight= event.$TextBox.outerHeight();
                var textBoxTopHeight=event.$TextBox.offset().top;
                var bottomHeight=windowHeigh-textBoxHeight-textBoxTopHeight;
                var selectContentHeight=event.$SelectTree.find("li").length * 18 + 20;
                var topHeight = event.$TextBox.offset().top - selectContentHeight;
                if(selectContentHeight<=bottomHeight){ 
                event.$SelectContent.css(
                  {
                    left: event.$TextBox.offset().left + "px",
                    top: event.$TextBox.offset().top + event.$TextBox.outerHeight() + "px"
                   });
                 var offsetHeight = event.$SelectTree.find("li").length * 18 + 20;
                 if (offsetHeight < event.$SelectContent.height()) {
                    event.$SelectContent.height(offsetHeight);
                  }
                 event.$SelectContent.slideDown("fast");
                }
                else{
                  if(topHeight<0){
                    event.$SelectContent.css({
                    left: event.$TextBox.offset().left + "px",
                    top: event.$TextBox.offset().top + event.$TextBox.outerHeight() + "px"
                   });
                  var offsetHeight = event.$SelectTree.find("li").length * 18 + 20;
                 if (offsetHeight < event.$SelectContent.height()) {
                    event.$SelectContent.height(offsetHeight);
                  }    
                   event.$SelectContent.slideDown("fast");
                   }
                  else{
                 event.$SelectContent.css({
                           bottom:windowHeigh-textBoxTopHeight+"px"
                           });
                 $(window).resize(function () {
                 var windowHeigh=$(window).height();
                 var textBoxTopHeight=event.$TextBox.offset().top;
                 event.$SelectContent.css({
                       bottom:windowHeigh-textBoxTopHeight+"px"
                    });
                  })
                  var offsetHeight = event.$SelectTree.find("li").length * 18 + 20;
                 if (offsetHeight < event.$SelectContent.height()) {
                    event.$SelectContent.height(offsetHeight);
                  }    
                    event.$SelectContent.slideDown("fast");
                   } 
                 }
              $("body").bind("mousedown", function (evt) {
                    if (!(evt.target.id == event.$SelectContent.attr("id")
                        || $(evt.target).parents("#" + event.$SelectContent.attr("id")).length > 0)) {
                        event.close();
                    }
                });
            },
            initTree: function (event, data) {
                var setting;
                if (event.options.setting.selectType == "tree") {
                    if (event.options.setting.multiple) {
                        setting = {
                            check: {
                                enable: true,
                                chkboxType: { "Y": "s", "N": "s" },
                                autoCheckTrigger: false
                            },
                            view: {
                                dblClickExpand: false
                            },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            callback: {
                                onCheck: function (e, treeId, treeNode) {
                                    var data = event.options.setValue(event);
                                    event.options.setting.onSelect(data, event);
                                },
                                beforeClick: function (treeId, treeNode) {
                                	var zTree = $.fn.zTree.getZTreeObj(treeId);
                        			zTree.checkNode(treeNode, !treeNode.checked, null, true);
                        			return false;
                                }
                            }
                        };
                    } else {
                        setting = {
                            view: {
                                dblClickExpand: false
                            },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            callback: {
                                onClick: function (e, treeId, treeNode) {
                                    var data = event.options.setValue(event);
                                    event.options.setting.onSelect(data, event);
                                    event.close();
                                }
                            }
                        };
                    }
                } else {
                    if (event.options.setting.multiple) {
                        setting = {
                            check: {
                                enable: true,
                                chkboxType: { "Y": "", "N": "" }
                            },
                            view: {
                                dblClickExpand: false,
                                showIcon: false,
                                showLine: false
                            },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            callback: {
                                onCheck: function (e, treeId, treeNode) {
                                    var data = event.options.setValue(event);
                                    event.options.setting.onSelect(data, event);
                                }
                            }
                        };
                    } else {
                        setting = {
                            view: {
                                dblClickExpand: false,
                                selectedMulti: false,
                                showIcon: false,
                                showLine: false
                            },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            callback: {
                                onClick: function (e, treeId, treeNode) {
                                    var data = event.options.setValue(event);
                                    event.options.setting.onSelect(data, event);
                                    event.close();
                                }
                            }
                        };
                    }
                }
                $.fn.zTree.init($("#" + event.$SelectTree.attr("id")), setting, data);
            },
            reloadTree:function(event, options){//不是很规范的重载ztree,一般先设置url再调用实现联动
            	var obj = event;
                $.ajax({
                    url: options.setting.url,
                    dataType: "text",
                    success: function (data) {
                        data = eval(data);
                        options.initTree(obj, data);
                    }
                });
            }
        }
    });

})(jQuery);

/*selectbox.js*/
$(function () {
    $(".selectBox").selectBox();
});
(function ($) {
    
    $.extend($.Setting, {
        SelectBox: {
            buttons: [{
                    text: "",
                    icon: "glyphicon-list",
                    display: true
            }],
            setting: {
                url: undefined,
                window: {
                    title: undefined,
                    width: 600,
                    height: 420
                },
                select: function ($window) {
                    var data = $window.getSelected();
                    return data;
                }
            },
            readonly: true,
            attrName: "selectBox_",
            initParameter: function (event, control, setting) {
                var options = $.extend(true, {}, setting);
                event.options.setting = $.extend(true, {}, $.Setting.SelectBox.setting, setting);

                if ((options.window == undefined ||
                        options.window.width == undefined)
                    && control.attr("data-window-width") != undefined) {
                    event.options.setting.window.width = control.attr("data-window-width");
                }
                if ((options.window == undefined ||
                        options.window.height == undefined)
                    && control.attr("data-window-height") != undefined) {
                    event.options.setting.window.height = control.attr("data-window-height");
                }
                if (options.url == undefined) {
                    event.options.setting.url = control.attr("data-url");
                }
                if (options.window == undefined || options.window.title == undefined) {
                    var title = control.attr("title");
                    title = title == undefined ? "选择" : title;
                    event.options.setting.window.title = title;
                }
                if (control.attr("data-select") != undefined) {
                    event.options.setting.select = function($window) {
                        callFunc(control.attr("data-select"), $window);
                    };
                }
                event.url = function(value) {
                    if (value == undefined) {
                        return event.options.setting.url;
                    } else {
                        event.options.setting.url = value;
                    }
                    return null;
                };
            },
            click: function (event, setting, callback) {
                var url = setting.url;
                if (url.indexOf("?") > 0) {
                    url = url + "&";
                } else {
                    url = url + "?";
                }
                var value = event.value();
                var text = event.text();
                if (value.length > 0) {
                    url += "value=" + value;
                } else {
                    url += "text=" + text;
                }
                if (url.length > 255) {
                    url = url.substring(0, 255);
                }
                //参数
                event.$AsyncBox = $.open({
                    url: url,
                    width: setting.window.width,
                    height: setting.window.height,
                    btnsbar: $.btn.OKCANCEL,
                    title: setting.window.title,
                    callback: function (action) {
                        var t = this;
                        if (action == "ok") {
                            var data = setting.select($.box(t.id));
                            if (typeof callback == 'function') {
                                callback(data);
                            } else if (data != undefined) {
                                event.value(data.Value);
                                event.text(data.Text);
                                $.close(t.id);
                            }
                        } else {
                            $.close(t.id);
                        }
                        return false;
                    }
                });
            }
        }
    });

    $.fn.selectBox = function (setting) {
        var boxList = new Array();
        this.each(function () {
            var _this = $(this);
            var selectBox = new $.ButtonBox(_this, $.Setting.SelectBox, setting);
            boxList.push(selectBox);
        });
        if (boxList.length == 1)
            return boxList[0];
        else {
            return boxList;
        }
    };
    
})(jQuery);

/*uploadbox.js*/
$(function () {
    //$(".uploadBox").uploadBox();
});
(function ($) {

    $.fn.uploadBox = function (setting) {
        var boxList = new Array();
        this.each(function () {
            var _this = $(this);
            var selectBox = new $.ButtonBox(_this, $.Setting.UpLoadBox, setting);
            boxList.push(selectBox);
        });
        if (boxList.length == 1)
            return boxList[0];
        else {
            return boxList;
        }
    };
    $.extend($.Setting, {
        UpLoadBox: {
            buttons: [{
                text: "上传",
                icon: "glyphicon-floppy-open",
                display: true
            }],
            setting: {
                name: 'file',
                url: undefined,
                autoSubmit: true,
                infoPanel: {
                    id: undefined,
                    display: true
                },
                onSelect: function (event) {

                },
                onSubmit: function (event) {
                    var fileline = null;
                    var setting = event.options.setting;
                    var count = event.$UploadPanel.find("li").length;
                    fileline = $('<li><span class="left">[' + event.filename() +
                        ']<input type="hidden" name="' + event.$SelectButton.attr("name") + "_" + count
                        + '" value=""/></span><span class="right glyphicon glyphicon-transfer"></span></li>');
                    event.$UploadPanel.append(fileline);
                    event.$UploadPanel.parent().show();
                    return fileline;
                },
                onDelete: function (event, url, item) {
                },
                success: function (event, status, fileline) {
                    var setting = event.options.setting;
                    if (event.$UploadPanel != undefined && setting.infoPanel.display) {
                        fileline.find(".right").removeClass("glyphicon").removeClass("glyphicon-transfer");
                        if (status.Status) {
                            fileline.find(".left").append('<span class="success glyphicon glyphicon-ok"></span>');
                            fileline.find("input").val(status.Url);
                            fileline.find(".left").addClass("success");
                            fileline.find(".right").addClass("warnning glyphicon glyphicon-remove");
                            fileline.find(".right").click(function() {
                                event.removeFile(fileline);
                            });
                        } else {
                            fileline.find(".left").addClass("error");
                            fileline.find(".left").append('<span class="success glyphicon glyphicon-remove"></span>');
                            fileline.find(".right").addClass("error glyphicon glyphicon-remove");
                        }
                    }
                },
                fail: function (event, status) {
                },
                error: function (data, message) {
                },
                params: {}
            },
            readonly: false,
            attrName: "uploadBox_",
            initParameter: function (event, control, setting) {
                event.options.setting = $.extend(true, {}, $.Setting.UpLoadBox.setting, setting);
                setting = $.extend(true, {}, setting);
                if (setting.url == undefined && control.attr("data-url") != undefined) {
                    event.options.setting.url = control.attr("data-url");
                }
                if (control.attr("data-readonly") != undefined) {
                    event.options.readonly = control.attr("data-readonly") == "true";
                }
                if (control.attr("data-infoPanel-id") != undefined) {
                    event.options.setting.infoPanel.id = control.attr("data-infoPanel-id");
                }
                if (control.attr("data-infoPanel-display") != undefined) {
                    event.options.setting.infoPanel.display = control.attr("data-infoPanel-display") == "true";
                }
                if (control.attr("data-params") != undefined) {
                    event.options.setting.params = eval("(" + control.attr("data-params") + ")");
                }
                if (control.attr("data-onSelect") != undefined) {
                    event.options.setting.onSelect = function (evt) {
                        callFunc(control.attr("data-onSelect"), evt);
                    };
                }
                if (control.attr("data-onDelete") != undefined) {
                    event.options.setting.onDelete = function (evt, url, item) {
                        callFunc(control.attr("data-onDelete"), evt, url, item);
                    };
                }
                if (control.attr("data-onSubmit") != undefined) {
                    event.options.setting.onSubmit = function (evt) {
                        return callFunc(control.attr("data-onSubmit"), evt);
                    };
                }
                if (control.attr("data-success") != undefined) {
                    event.options.setting.success = function (evt, status, li) {
                        callFunc(control.attr("data-success"), evt, status, li);
                    };
                }
                if (control.attr("data-fail") != undefined) {
                    event.options.setting.fail = function (evt, status) {
                        callFunc(control.attr("data-fail"), evt, status);
                    };
                }
                if (control.attr("data-error") != undefined) {
                    event.options.setting.error = function (data, message) {
                        callFunc(control.attr("data-error"), data, message);
                    };
                }
            },
            initControls: function (event, options) {
                /** A unique id so we can find our elements later */
                var id = new Date().getTime().toString().substr(8);
                var setting = options.setting;
                event.$Form = $(
                    '<form ' +
                    'method="post" ' +
                    'enctype="multipart/form-data"' +
                    'action="' + setting.url + '" ' +
                    '></form>'
                ).css({
                    margin: 0,
                    padding: 0
                });
                /** File Input */
                event.$FileInput = $(
                    '<input ' +
                    'name="' + setting.name + '" ' +
                    'type="file" ' +
                    '/>'
                ).css({
                    position: 'relative',
                    display: 'block',
                    marginLeft: -175 + 'px',
                    opacity: 0
                });
                event.initInput = function ($button) {
                    event.$Container = $button.parent().css({
                        position: 'relative',
                        height: $button.outerHeight() + 'px',
                        width: $button.outerWidth() + 'px',
                        overflow: 'hidden',
                        cursor: 'pointer',
                        margin: 0,
                        padding: 0
                    });
                    event.$FileInput.css('marginTop', -event.$Container.height() - 10 + 'px');
                    event.$Container.mousemove(function (e) {
                        event.$FileInput.css({
                            top: e.pageY - event.$Container.offset().top + 'px',
                            left: e.pageX - event.$Container.offset().left + 'px'
                        });
                    });
                };
                /** Put everything together */
                if (event.$SelectButton == undefined) {
                    event.$Buttons[0].wrap('<div></div>');
                    event.$Form.append(event.$FileInput);
                    event.$Buttons[0].after(event.$Form);
                    event.initInput(event.$Buttons[0]);
                } else {
                    event.$SelectButton.addClass("btn btn-default");
                    var text = event.$SelectButton.text();
                    event.$SelectButton.css("width", text.length * 20 + 25 + "px");
                    event.$SelectButton.html("");
                    event.$SelectButton.append("<span class=\"glyphicon glyphicon-floppy-open\"></span>" + text);
                    event.$SelectButton.wrap('<div></div>');
                    event.$Form.append(event.$FileInput);
                    event.$SelectButton.after(event.$Form);
                    if (setting.infoPanel.id != undefined) {
                        event.$UploadPanel = $("#" + setting.infoPanel.id);
                    }
                    if (event.$UploadPanel == undefined) {
                        event.$UploadPanel = $('<div class="uploadPanel"><ul></ul><div class="clear"></div></div>').css("display", "none");
                        event.$SelectButton.parent().after(event.$UploadPanel);
                    }
                    if (event.$UploadPanel.find("ul").length == 0) {
                        event.$UploadPanel.append("<ul></ul>");
                    }
                    event.$UploadPanel = event.$UploadPanel.find("ul");
                    event.initInput(event.$SelectButton);
                }

                event.submit = function () {
                    var fileline = null;
                    if (event.$UploadPanel != undefined && setting.infoPanel.display) {
                        fileline = setting.onSubmit(event);
                    } else if (event.$TextBox != undefined) {
                        event.$TextBox.attr("disabled", "disabled");
                        event.text("上传中...");
                        fileline = event.$TextBox;
                    }
                    event.$Form.ajaxSubmit({
                        type: "post",
                        url: setting.url,
                        params: setting.params,
                        cache: false,
                        dataType: "json",
                        success: function (status) {
                            if (status.Status) {
                                
                                if (event.$TextBox != undefined) {
                                    event.$TextBox.removeAttr("disabled");
                                    event.text(status.Url);
                                }
                                setting.success(event, status, fileline);
                            } else {
                                setting.fail(event, status);
                            }
                        },
                        error: function (data, message) {
                            setting.error(data, message);
                        }
                    });


                };
                event.removeFile = function (item) {
                    $.confirm("是否确认删除？", "确认删除", function (status) {
                        if (status == "ok") {
                            var panel = item.parents().find(".uploadPanel");
                            if (panel.find("li").length == 1) {
                                panel.hide();
                            }
                            setting.onDelete(event, item.find("input").val(), item);
                            item.remove();
                        }
                    });

                };
                event.filename = function () {
                    return event.$FileInput.val().getFileName();
                };
                event.selectFile = function () {
                    event.$FileInput.click();
                };
                event.$FileInput.click(function () {
                    event.$FileInput.val("");
                });
                event.$FileInput.change(function () {
                    if (event.$FileInput.val() != "") {
                        /** Do something when a file is selected. */
                        setting.onSelect(event, event.$FileInput);
                        /** Submit the form automaticly after selecting the file */
                        if (setting.autoSubmit) {
                            event.submit();
                        }
                    }
                });
            },
            click: function (event, setting, callback) {
                event.$FileInput.click();
            }
        }
    });



})(jQuery);

/*searchpanel.js*/
$(function() {
    $(".searchPanel").searchPanel();
});

(function($) {
    $.SearchPanel = function(panel, setting) {
        var _this = this;
        _this.init(panel, setting);
    };
    $.fn.searchPanel = function (setting) {
        var _this = this;
        var searchPanel = new $.SearchPanel(_this, setting);
        return searchPanel;
    };
    $.extend($.SearchPanel, {
        defaults: {
            icon: {
                show: "glyphicon glyphicon-chevron-down",
                hide: "glyphicon glyphicon-chevron-up",
                search: "glyphicon glyphicon-search",
                reset: "glyphicon glyphicon-refresh"
            },
            //是否有展开
            drap:false,
            //动画效果
            flash: true
        },
        prototype: {
            $SearchButton: null,
            $ResetButton: null,
            $ExtInfoPanel:null,
            $Panel: null,
            initParameter: function (setting) {
                var _this = this;
                _this.Setting = $.extend(true, {}, $.SearchPanel.defaults, setting);
                if (_this.$Panel.attr("data-drap") != undefined) {
                    _this.Setting.drap = _this.$Panel.attr("data-drap") == "true";
                }
                if (_this.$Panel.attr("data-flash") != undefined) {
                    _this.Setting.flash = _this.$Panel.attr("data-flash") == "true";
                }
            },
            initControls: function () {
                var _this = this;
                
                _this.$ExtInfoPanel = _this.$Panel.find(".body .extInfo");
                _this.$SearchButton.addClass(_this.Setting.icon.search);
                _this.$ResetButton.addClass(_this.Setting.icon.reset);
                _this.$ResetButton.click(function() {
                    _this.$Panel.find("input[type='hidden']").val("");
                });
                if (_this.$ExtInfoPanel.length > 0) {
                    _this.$DisplayButton = _this.$Panel.find(".displayBox button");
                    if (_this.$DisplayButton.length == 0) {
                        var displayBox = _this.$Panel.find(".displayBox");
                        if (displayBox.length == 0) {
                            displayBox = $("<div></div>").addClass("displayBox");
                            _this.$Panel.find(".body").after(displayBox);
                        }
                        _this.$DisplayButton = $("<button type='button'></button>");

                        displayBox.append(_this.$DisplayButton);
                    }
                    var body = _this.$Panel.find(".body");
                    if (!_this.Setting.drap) {
                        _this.$DisplayButton.addClass(_this.Setting.icon.show);
                        _this.$ExtInfoPanel.css("display", "none");
                    } else {
                        _this.$DisplayButton.addClass(_this.Setting.icon.hide);
                        _this.$ExtInfoPanel.css("display","block");
                    }
                    
                    _this.$DisplayButton.click(function () {
                        var time = _this.Setting.flash ? 500 : 0;
                        if (_this.$DisplayButton.attr("class") == _this.Setting.icon.show) {
                            _this.$DisplayButton.attr("class", _this.Setting.icon.hide);
                            body.css({
                                height: body.height() + "px",
                                "overflow": "hidden"
                            });
                            _this.$ExtInfoPanel.css("display", "block");
                            body.animate({ height: body.find("table").height() + "px" }, time, function () {
                                body.removeAttr("style");
                            });
                        } else {
                            _this.$DisplayButton.attr("class", _this.Setting.icon.show);
                            body.animate({ height: body.height() - _this.$ExtInfoPanel.height() + "px" }, time,
                                function () {
                                    body.removeAttr("style");
                                    _this.$ExtInfoPanel.css("display", "none");
                                });
                        }
                    });
                }
            },
            init:function(panel,setting) {
                var _this = this;
                _this.$Panel = panel;
                _this.initParameter(setting);
                _this.$SearchButton = _this.$Panel.find(".button-bar button[type='submit']");
                _this.$ResetButton = _this.$Panel.find(".button-bar button[type='reset']");
                _this.initControls();
                
                //////控件小图标修改后引起样式问题,增加清除
                _this.$Panel.find(".button-bar").after("<div class='clear'></div>");
            },
            reset:function() {
                this.$ResetButton.click();
            },
            search:function() {
                this.$SearchButton.click();
            }
        }
    });
})(jQuery);
/*listbox.js*/
$(function() {
    //$("dl.listBox").listBox();
});

(function ($) {
    var BoxList = new Array();
    $.ListBox = function (panel, setting) {
        var boxId = panel.attr("data-box-Id");
        var _this = BoxList[boxId];
        if (_this == undefined) {
            _this = this;
            _this.init(panel, setting);
            BoxList[_this.Id] = _this;
        }
        return _this;
        
    };
    $.fn.listBox = function (setting) {
        var boxList = new Array();
        this.each(function () {
            var _this = $(this);
            var listBox = new $.ListBox(_this, setting);
            boxList.push(listBox);
        });
        if (boxList.length == 1)
            return boxList[0];
        else {
            return boxList;
        }
    };
    $.extend($.ListBox, {
        defaults: {
            splitCount: 10,
            fold:true,
            showCount: 30,
            multiple: false
        },
        attrName:"listBox_",
        prototype: {
            $MoreButton: null,
            //文本框
            $TextBox: null,
            //值框
            $TargetBox: null,
            $Dl: null,
            $MorePanel:null,
            $Content:null,
            text:function() {
                var _this = this;
                var text;
                _this.$Dl.find("dd.selected").each(function() {
                    var item = $(this);
                    text += item.text() + ",";
                });
                if (text.length > 0) {
                    text = text.substring(0, text.length - 1);
                }
                return text;
            },
            value: function(val) {
                var _this = this;
                if (val == undefined) {
                    return _this.$TargetBox.val();
                } else {
                    _this.$TargetBox.val(val);
                    var list = val.split(",");
                    var count = 1;
                    if (_this.options.multiple) {
                        count = list.length;
                    }
                    for (var i = 0; i < count; i++) {
                        _this.select(_this.$Content.find("dd[data-value='" + list[i] + "']"));
                    }
                    
                }
                return null;
            },
            initParameter: function () {
                var _this = this;
                if (_this.$Dl.attr("data-setting") != undefined) {
                    var setting = eval("(" + _this.$Dl.attr("data-setting") + ")");
                    _this.options = $.extend(true, {}, _this.options, setting);
                }
            },
            initControls: function () {
                var _this = this;
                //初始化Target
                var targetId = _this.$Dl.attr("data-target");
                if (targetId != undefined) {
                    if ($("#" + targetId).length > 0) {
                        _this.$TargetBox = $("#" + targetId);
                    } else if ($("input[name='" + targetId + "']").length > 0) {
                        _this.$TargetBox = $("input[name='" + targetId + "']");
                    } else {
                        _this.$TargetBox = $("<input type=\"hidden\" id=\"" + targetId + "\" name =\"" + targetId + "\" />");
                        _this.$Dl.parent().after(_this.$TargetBox);
                    }
                } else {
                    
                }
                _this.$MoreButton = $('<dd class="more">更多<span class="glyphicon glyphicon-chevron-down"></span></dd>');
                function initDD(ddItem) {
                    var ddLength = 1;
                    var length = ddItem.text().getLength();
                    if (length > _this.options.splitCount * 2) {
                        ddItem.addClass("xl");
                        ddLength = 3;
                    } else if (length > _this.options.splitCount) {
                        ddItem.addClass("lg");
                        ddLength = 2;
                    }
                    if (ddItem.attr("title") == undefined) {
                        ddItem.attr("title", ddItem.text());
                    }
                    ddItem.unbind("click");
                    ddItem.click(function() {
                        _this.select(ddItem);
                    });
                    return ddLength;
                }
                
                var ddCount = 0;
                var index;
                var ddList = _this.$Dl.find("dd");
                for (index = 0; ddCount < _this.options.showCount+1 && index < ddList.length; index++) {
                    ddCount += initDD($(ddList[index]));
                }
                if (index < ddList.length) {
                    index = index - 2;
                    _this.$MorePanel = $('<dl class="listBox"  style="display:none;"></dl>').attr("data-box-Id", _this.Id);
                    var splitItem = _this.$Dl.find("dd:eq(" + index + ")");
                    _this.$MorePanel.append(splitItem.nextAll());
                    splitItem.nextAll().remove();
                    _this.$Dl.after(_this.$MorePanel);
                    _this.$Dl.after('<div class="clear" ></div>');
                    _this.$Dl.append(_this.$MoreButton);
                    _this.$MoreButton.click(function () {
                        _this.$MorePanel.toggle();
                        if (_this.$MoreButton.find("span").hasClass("glyphicon-chevron-down")) {
                            _this.$MoreButton.find("span").removeClass("glyphicon-chevron-down");
                            _this.$MoreButton.find("span").addClass("glyphicon-chevron-up");
                        } else {
                            _this.$MoreButton.find("span").addClass("glyphicon-chevron-down");
                            _this.$MoreButton.find("span").removeClass("glyphicon-chevron-up");
                        }
                    });
                    _this.$MorePanel.find("dd").each(function () {
                        initDD($(this));
                    });
                }
            },
            init:function(panel,setting) {
                var _this = this;
                _this.options = $.extend(true, {}, $.ListBox.defaults, setting);
                _this.$Dl = panel;
                _this.$Content = panel.parent();
                if (_this.$Dl.attr("data-target") != undefined) {
                    _this.Id = $.ListBox.attrName + _this.$Dl.attr("data-target");
                } else if (_this.$Dl.attr("id") != undefined) {
                    _this.Id = $.ListBox.attrName + _this.$Dl.attr("id");
                    _this.$Dl.attr("data-target", _this.$Dl.attr("id"));
                } else {
                    _this.Id = $.ListBox.attrName + getRandNum();
                }
                _this.$Dl.attr("data-box-Id", _this.Id);
                _this.initParameter();
                _this.initControls();
                if (_this.options.value != undefined) {
                    _this.value(_this.options.value);
                } else {
                    _this.value(_this.$Content.find("dd.all").attr("data-value"));
                }
            },
            select:function($dd) {
                var _this = this;
                if ($dd.hasClass("all") || !_this.options.multiple ||
                    (_this.options.multiple && _this.$Content.find("dd.all").hasClass("selected"))) {
                    _this.$Content.find("dd").removeClass("selected");
                    _this.$TargetBox.val("");
                }
                var value = "";
                if (_this.options.multiple && $dd.hasClass("selected")) {
                    $dd.removeClass("selected");
                    _this.$Content.find("dd.selected").each(function() {
                        value += $(this).attr("data-value") + ",";
                    });
                    if (value.length > 0) {
                        value = value.substring(0, value.length - 1);
                    }
                } else {
                    $dd.addClass("selected");
                    if (_this.options.multiple && _this.value().length > 0) {
                        value = _this.value() + ",";
                    }
                    value = value + $dd.attr("data-value");
                }
                _this.$TargetBox.val(value);
            },
            clear: function() {
                var _this = this;
                if (_this.options.value != undefined) {
                    _this.value(_this.options.value);
                } else {
                    _this.value(_this.$Content.find("dd.all").attr("data-value"));
                }
            }
        }
    });
})(jQuery);



