$(function() {
    $("dl.listBox").listBox();
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
            multiple: false,
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
                for (index = 0; ddCount < _this.options.showCount - 1 && index < ddList.length; index++) {
                    ddCount += initDD($(ddList[index]));
                }
                if (index < ddList.length) {
                    _this.$MorePanel = $('<dl class="listBox"  style="display:none;"></dl>').attr("data-box-Id", _this.Id);
                    _this.options.showCount = index;
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