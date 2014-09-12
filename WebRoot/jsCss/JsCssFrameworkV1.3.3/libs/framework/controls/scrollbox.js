

(function ($) {
    $.ScrollBox = function (box, setting) {
        var _this = this;
        _this.init(box, setting);
    };
    $.fn.scrollBox = function (setting) {
        var _this = this;
        var scrollBox = new $.ScrollBox(_this, setting);
        return scrollBox;
    };
    $.extend($.ScrollBox, {
        defaults: {
            url: undefined,
            getParams:function() {
                
            },
            success: function(event, html) {
                event.$Box.append(html);
            },
            fail: function (event, html) {

            },
        },
        prototype: {
            $Box: null,
            AutoLoad:true,
            initParameter: function (setting) {
                var _this = this;
                _this.Setting = $.extend(true, {}, $.ScrollBox.defaults, setting);
                if (_this.Setting.url == undefined && _this.$Box.attr("data-url") != undefined) {
                    _this.Setting.url = _this.$Box.attr("data-url");
                }
                if (_this.$Box.attr("success") != undefined) {
                    _this.Setting.success = function(event, html) {
                        callFunc(_this.$Box.attr("success"), event, html);
                    };
                }
                if (_this.$Box.attr("fail") != undefined) {
                    _this.Setting.fail = function (event, data) {
                        callFunc(_this.$Box.attr("fail"), event,html);
                    };
                }
            },
            initControls: function () {
                var _this = this;
                _this.$Box.scroll(function() {
                    if ((_this.$Box.scrollTop() + _this.$Box.height() >= _this.$Box[0].scrollHeight - 20)
                        && _this.AutoLoad) {
                        _this.AutoLoad = false;
                        _this.loadMore();
                    }
                });
            },
            init: function (box, setting) {
                var _this = this;
                _this.$Box = box;
                _this.$Box.attr("data-box-Id", "scroll_box_" + getRandNum());
                _this.initParameter(setting);
                _this.initControls();
            },
            loadMore: function () {
                var _this = this;
                $.ajax({
                    url: _this.Setting.url,
                    cache: false,
                    data: _this.Setting.getParams(),
                    success: function(html) {
                        if (html.length > 0) {
                            _this.Setting.success(_this, html);
                            _this.AutoLoad = true;
                        } else {

                        }
                    },
                    error: function(data) {
                        _this.Setting.fail(data);
                    }
                });
            }
        }
    });
})(jQuery);