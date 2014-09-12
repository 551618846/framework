/dist/ ：发布压缩后的js和css
/libs/ ：各js和css源码

版本号V1.3

目录说明：
/dist						压缩后Js、Css包（直接引用至项目）
/libs						Js，Css源码库

/Demo/DemoForm.html			标准控件调试页面
/Demo/DemoForm.min.html		标准控件应用页面
/Demo/DemoForm.html			标准控件调试页面（紧凑版）
/Demo/DemoForm.min.html		标准控件应用页面（紧凑版）
/Demo/Form.html				两列三列布局页面
/Demo/SearchPanell.html		三列搜索调试页面
/Demo/SearchPanell.min.html	三列搜索应用页面
------------------------------------------------------------------------------
引用第三方Js库：
jquery.js			1.7.2
jquery.form.js
asyncbox.js			1.4
ztree.js			3.5.17-beta.1
WdatePicker			4.8 Beta3
jquery.validate		有修改
------------------------------------------------------------------------------
已整合功能：
表单(form)页面基础结构。
弹出选择层封装:selectBox
输入中样式:inputBox
表单ajax提交及验证：ajaxform
时间选择控件：dateBox
下拉控件（多选）: dropDownBox
下拉控件（树形下拉）:dropDownBox
上传控件（支持单个/多个文件上传):uploadBox（暂不支持上传进度条）
下拉控件（联动）:dropDownBox 增加 onSelect方法。
Combo下拉：dropDownBox(暂不支持联想)
搜索框界面规范：searchPanel & listBox
搜索框封装：searchPanel & listBox
------------------------------------------------------------------------------

预计完成整合：

弹出选择层界面规范

tab页封装
统一加载中效果

未规划：
GridView



