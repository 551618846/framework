$(function () {
    $(".input").inputBox();
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
