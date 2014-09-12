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
                    event.options.setting.select = function ($window){
                        callFunc(control.attr("data-select"),$window);
                    }
                }
            },
            click: function (event, setting, callback) {
                var url = setting.url;
                if (url.indexOf("?") > 0) {
                    url = url + "&text=" + event.text() + "&value=" + event.value();
                } else {
                    url = url + "?text=" + event.text() + "&value=" + event.value();
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
