$(function () {
    $(".uploadBox").uploadBox();
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
                    display:true
                },
                onSelect: function(event) {
                },
                onDelete:function(event,url,item) {
                },
                success: function (event, status) {
                },
                fail: function (event, status) {
                },
                error:function(data,message) {
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
                    }
                }
                if (control.attr("data-onDelete") != undefined) {
                    event.options.setting.onDelete = function (evt, url, item) {
                        callFunc(control.attr("data-onDelete"), evt, url, item);
                    }
                }
                if (control.attr("data-success") != undefined) {
                    event.options.setting.success = function (evt, status) {
                        callFunc(control.attr("data-success"), evt, status);
                    }
                }
                if (control.attr("data-fail") != undefined) {
                    event.options.setting.fail = function (evt, status) {
                        callFunc(control.attr("data-fail"), evt, status);
                    }
                }
                if (control.attr("data-error") != undefined) {
                    event.options.setting.error = function (data, message) {
                        callFunc(control.attr("data-error"), data, message);
                    }
                }
            },
            initControls: function(event, options) {
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
                    'name="' + options.name + '" ' +
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
                    event.$SelectButton.append("<span class=\"glyphicon glyphicon-floppy-open\"></span>"+text);
                    event.$SelectButton.wrap('<div></div>');
                    event.$Form.append(event.$FileInput);
                    event.$SelectButton.after(event.$Form);
                    if (setting.infoPanel.id != undefined) {
                        event.$UploadPanel = $("#" + setting.infoPanel.id);
                    }
                    if (event.$UploadPanel == undefined)
                    {
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
                    var fileline;
                    if (event.$UploadPanel != undefined && setting.infoPanel.display) {
                        var count = event.$UploadPanel.find("li").length;
                        fileline = $('<li><span class="left">[' + event.filename() +
                            ']<input type="hidden" name="' + event.$SelectButton.attr("name") + "_" + count
                            + '" value=""/></span><span class="right glyphicon glyphicon-transfer"></span></li>');
                        event.$UploadPanel.append(fileline);
                        event.$UploadPanel.parent().show();
                    } else if (event.$TextBox != undefined) {
                        event.$TextBox.attr("disabled", "disabled");
                        event.text("上传中...");
                    }
                    event.$Form.ajaxSubmit({
                        type: "post",
                        url: setting.url,
                        params: setting.params,
                        cache:false,
                        dataType: "json",
                        success: function (status) {
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
                            } else if (event.$TextBox != undefined) {
                                event.$TextBox.removeAttr("disabled");
                                event.text(status.Url);
                            }
                            if (status.Status) {
                                setting.success(event,status);
                            } else {
                                setting.fail(event, status);
                            }
                        },
                        error: function(data,message) {
                            setting.error(data,message);
                        }
                    });
                    
                    
                };
                event.removeFile = function(item) {
                    $.confirm("是否确认删除？", "确认删除", function(status) {
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
                event.filename = function() {
                    return event.$FileInput.val().getFileName();
                };
                event.selectFile = function() {
                    event.$FileInput.click();
                };
                event.$FileInput.click(function() {
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
