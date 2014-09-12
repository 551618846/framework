

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