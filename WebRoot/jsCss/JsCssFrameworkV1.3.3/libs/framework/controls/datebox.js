$(function () {
    $(".dateBox").dateBox();
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
