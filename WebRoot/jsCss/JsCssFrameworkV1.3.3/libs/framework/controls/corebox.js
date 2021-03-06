﻿(function ($) {
    var BoxList = new Array();
    $.Setting = {};
    $.ButtonBox = function (event, options,setting) {////$.ButtonBox是全局函数;event指id对应的jQuery对象,options对应控件的全局设置,setting初始化参数
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
        defaults: {//$.ButtonBox的全局属性
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
        buttonDefaults: {//$.ButtonBox的全局属性
            code: "",
            text: "",
            icon: "glyphicon-list",
            dispaly:true
        },
        prototype: {//重定义$.ButtonBox的prototype属性
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
                        _this.value(_this.$TextBox.attr("data-value"));
                    }
                }
            },
            init: function(event,options,setting) {
                var _this = this;//this指调用函数的对象或者全局对象
                _this.options = $.extend(true, {}, $.ButtonBox.defaults, options);//设置options属性
                var isButton = !event.is("input");
                if (isButton) {//button元素
                    _this.$SelectButton = event;
                    if (_this.$SelectButton.attr("name") != undefined) {
                        _this.Id = _this.options.attrName + _this.$SelectButton.attr("name");
                    } else if (_this.$SelectButton.attr("id") != undefined) {
                        _this.Id = _this.options.attrName + _this.$SelectButton.attr("id");
                    }
                    _this.$SelectButton.attr("data-box-Id", _this.Id);
                } else {//非button
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