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