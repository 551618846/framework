$(function () {
    $("input.treeBox").dropDownBox();
    $("input.listBox").dropDownBox({ selectType: "list" });
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
                                chkboxType: { "Y": "", "N": "" }
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
            }
        }
    });

})(jQuery);
