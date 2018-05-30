var sy = sy || {};

/** 
 * 扩展点击单元格修改数据
 */
$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

$.extend($.fn.validatebox.defaults.rules,{
	eqPwd:{
		validator:function(value,param){
				return value == $(param[0]).val();
		},
			message:'密码不一致!'
	}
});

/**
 * @author qc
 * 
 * 扩展validatebox，验证仅能输入大于等于0的数
 */
$.extend($.fn.validatebox.defaults.rules, {    
    moreThanZero: {    
        validator: function(value, param){
        	if(value != '-0'){
        		return value >= param[0];
        	}
        },    
        message: 'Please enter the number more than {0}.'   
    }    
});

/**
 * @author qc
 * 
 * 扩展validatebox，验证仅能输入大于等于0小于等于100的整数
 */
$.extend($.fn.validatebox.defaults.rules, {    
    moreLength: {    
        validator: function(value, param){
        	if(value != '-0'){
        		var reg = new RegExp(/^(100|[1-9]\d|\d)$/);
    			if(reg.test(value)){
    				return value >= param[0] && value <= param[1];
    			}
        	}
        },    
        message: 'Please enter the number for an integer between {0} and {1}.'   
    }    
});  

/**
 * @author qc
 * 
 * 扩展validatebox，验证仅能输入指定字符
 */
$.extend($.fn.validatebox.defaults.rules, {    
    onlyLength: {    
        validator: function(value, param){    
            return value.length == param[0];    
        },    
        message: 'Please enter the {0} characters.'   
    }    
});  

$.extend($.fn.datagrid.methods, {
	/**
	 * @author qc
	 * 
	 * 扩展datagrid，合并单元格
	 */
    autoMergeCells: function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var i = 0,
            j = 0,
            temp = {};
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                for (j; j < fields.length; j++) {
                    var field = fields[j];
                    var tf = temp[field];
                    if (!tf) {
                        tf = temp[field] = {};
                        tf[row[field]] = [i];
                    } else {
                        var tfv = tf[row[field]];
                        if (tfv) {
                            tfv.push(i);
                        } else {
                            tfv = tf[row[field]] = [i];
                        }
                    }
                }
            }
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;
                    if (group.length > 1) {
                        var before,
                        after,
                        megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                target.datagrid('mergeCells', {
                                    index: megerIndex,
                                    field: field,
                                    rowspan: rowspan
                                });
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
                });
            });
        });
    }
});

$.extend($.fn.datagrid.methods, {

	/**
	 * @author zw
	 * 
	 * 扩展datagrid，合并单元格
	 */
	mergeCellsArray: function (table, fields) {
		
		//本次合并列的索引
		var merge_index =0;
		//本次合并行数
		var merge_rowspan =0;

		var mergerows = fields.mergerows;
		var mergefields = fields.mergefields;
		var mergeorder = fields.mergeorder;
		
		//上一行的数据，用来对比是不是一致
		var merge_base=mergerows[0][mergeorder];

		for(var i=0; i<mergerows.length; i++){
			
			//以mergeorder为依据合并，不等就合并
			if(mergerows[i][mergeorder]!=merge_base){	
				//把mergefields里包含的列都合并
				for(var j=0; j<mergefields.length; j++){
					table.datagrid('mergeCells',{
						index: merge_index,
						field: mergefields[j],
						rowspan: merge_rowspan
					});
				}				
				//要合并的行数重置为1
				merge_rowspan=1;
				//记录下次合并的开始行数,从这一行开始合并
				merge_index = i;
				//重新记录对比的数据
				merge_base = mergerows[i][mergeorder];
			}else{
				//不合并,加1
				merge_rowspan++;
				//如果是最后一条数据了，就合并
				if(i==mergerows.length-1){				
					for(var j=0; j<mergefields.length; j++){
						table.datagrid('mergeCells',{
							index: merge_index,
							field: mergefields[j],
							rowspan: merge_rowspan
						});
					}
				}
			}
						
		}
    }
});


/**
 * @author 夏悸
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展tree，使其支持平滑数据格式
 */
$.fn.tree.defaults.loadFilter = function (data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled,
	textFiled,
	parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		
		var i,
		l,
		treeData = [],
		tmpMap = [];
		
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};


/**
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;


/**
 * 将form表单元素的值序列化成对象
 * 
 * @example sy.serializeObject($('#formId'))
 * 
 * @author 孙宇
 * 
 * @requires jQuery
 * 
 * @returns object
 */
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	return o;
};

/**
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI,jQuery cookie plugin
 * 
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};

/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
sy.loadFilter = {
	loadFilter : function(data, parent) {
		var opt = $(this).data().tree.options;
		var idField, textField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
};
$.extend($.fn.combotree.defaults, sy.loadFilter);
$.extend($.fn.tree.defaults, sy.loadFilter);

/**
 * 扩展treegrid，使其支持平滑数据格式
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});

/**
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 * 创建一个模式化的dialog
 * 
 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
 * 
 * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
 */
$.modalDialog = function(options) {
	if ($.modalDialog.handler == undefined) {// 避免重复弹出
		var opts = $.extend({
			title : '',
			width : 840,
			height : 680,
			modal : true,
			onClose : function() {
				$.modalDialog.handler = undefined;
				$(this).dialog('destroy');
			},
			onOpen : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
			}
		}, options);
		opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
		return $.modalDialog.handler = $('<div/>').dialog(opts);
	}
};

/**
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展combotree，使其支持平滑数据格式
 */
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;


/**
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
sy.onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		if (parent.$ && parent.$.messager) {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} else {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	}
};
$.extend($.fn.datagrid.defaults, sy.onLoadError);
$.extend($.fn.treegrid.defaults, sy.onLoadError);
$.extend($.fn.tree.defaults, sy.onLoadError);
$.extend($.fn.combogrid.defaults, sy.onLoadError);
$.extend($.fn.combobox.defaults, sy.onLoadError);
$.extend($.fn.form.defaults, sy.onLoadError);


/**  
 * 扩展两个方法  
 */  
$.extend($.fn.datagrid.methods, {   
    /**
     * 开打提示功能  
     * @param {} jq  
     * @param {} params 提示消息框的样式  
     * @return {}  
     */  
    doCellTip : function(jq, params) {   
        function showTip(data, td, e) {   
            if ($(td).text() == "")   
                return;   
            data.tooltip.text($(td).text()).css({   
                        top : (e.pageY + 10) + 'px',   
                        left : (e.pageX + 20) + 'px',   
                        'z-index' : $.fn.window.defaults.zIndex,   
                        display : 'block'   
                    });   
        };   
        return jq.each(function() {   
            var grid = $(this);   
            var options = $(this).data('datagrid');   
            if (!options.tooltip) {   
                var panel = grid.datagrid('getPanel').panel('panel');   
                var defaultCls = {   
                    'border' : '1px solid #333',   
                    'padding' : '1px',   
                    'color' : '#333',   
                    'background' : '#f7f5d1',   
                    'position' : 'absolute',   
                    'max-width' : '200px',   
                    'border-radius' : '4px',   
                    '-moz-border-radius' : '4px',   
                    '-webkit-border-radius' : '4px',   
                    'display' : 'none'   
                } ;
                var tooltip = $("<div id='celltip'></div>").appendTo('body');   
                tooltip.css($.extend({}, defaultCls, params.cls));   
                options.tooltip = tooltip;   
                panel.find('.datagrid-body').each(function() {   
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length   
                            ? $(this).find('> div.datagrid-body-inner')[0]   
                            : this;   
                    $(delegateEle).undelegate('td', 'mouseover').undelegate(   
                            'td', 'mouseout').undelegate('td', 'mousemove')   
                            .delegate('td', {   
                                'mouseover' : function(e) {   
                                    if (params.delay) {   
                                        if (options.tipDelayTime)   
                                            clearTimeout(options.tipDelayTime);   
                                        var that = this;   
                                        options.tipDelayTime = setTimeout(   
                                                function() {   
                                                    showTip(options, that, e);   
                                                }, params.delay);   
                                    } else {   
                                        showTip(options, this, e);   
                                    }   
  
                                },   
                                'mouseout' : function(e) {   
                                    if (options.tipDelayTime)   
                                        clearTimeout(options.tipDelayTime);   
                                    options.tooltip.css({   
                                                'display' : 'none'   
                                            });   
                                },   
                                'mousemove' : function(e) {   
                                    var that = this;   
                                    if (options.tipDelayTime) {   
                                        clearTimeout(options.tipDelayTime);   
                                        options.tipDelayTime = setTimeout(   
                                                function() {   
                                                    showTip(options, that, e);   
                                                }, params.delay);   
                                    } else {   
                                        showTip(options, that, e);   
                                    }   
                                }   
                            });   
                });   
  
            }   
  
        });   
    },   
    /**
     * 关闭消息提示功能  
     * @param {} jq  
     * @return {}  
     */  
    cancelCellTip : function(jq) {   
        return jq.each(function() {   
                    var data = $(this).data('datagrid');   
                    if (data.tooltip) {   
                        data.tooltip.remove();   
                        data.tooltip = null;   
                        var panel = $(this).datagrid('getPanel').panel('panel');   
                        panel.find('.datagrid-body').undelegate('td',   
                                'mouseover').undelegate('td', 'mouseout')   
                                .undelegate('td', 'mousemove') ;  
                    }   
                    if (data.tipDelayTime) {   
                        clearTimeout(data.tipDelayTime);   
                        data.tipDelayTime = null;   
                    }   
                });   
    }   
});  


/**
 * @author 孙宇
 * 
 * 定义一些小图标样式的数组
 */
$.iconData = [ {
	value : '',
	text : '默认'
}, {
	value : 'folder_wrench',
	text : 'folder_wrench'
}, {
	value : 'anchor',
	text : 'anchor'
}, {
	value : 'arrow_green',
	text : 'arrow_green'
}, {
	value : 'asterisk_orange',
	text : 'asterisk_orange'
}, {
	value : 'asterisk_yellow',
	text : 'asterisk_yellow'
}, {
	value : 'attach',
	text : 'attach'
}, {
	value : 'bell',
	text : 'bell'
}, {
	value : 'bell_add',
	text : 'bell_add'
}, {
	value : 'bell_delete',
	text : 'bell_delete'
}, {
	value : 'bell_error',
	text : 'bell_error'
}, {
	value : 'bell_go',
	text : 'bell_go'
}, {
	value : 'bell_link',
	text : 'bell_link'
}, {
	value : 'bin',
	text : 'bin'
}, {
	value : 'bin_closed',
	text : 'bin_closed'
}, {
	value : 'bin_empty',
	text : 'bin_empty'
}, {
	value : 'bomb',
	text : 'bomb'
}, {
	value : 'book',
	text : 'book'
}, {
	value : 'book_add',
	text : 'book_add'
}, {
	value : 'book_addresses',
	text : 'book_addresses'
}, {
	value : 'book_delete',
	text : 'book_delete'
}, {
	value : 'book_edit',
	text : 'book_edit'
}, {
	value : 'book_error',
	text : 'book_error'
}, {
	value : 'book_go',
	text : 'book_go'
}, {
	value : 'book_key',
	text : 'book_key'
}, {
	value : 'book_link',
	text : 'book_link'
}, {
	value : 'book_next',
	text : 'book_next'
}, {
	value : 'book_open',
	text : 'book_open'
}, {
	value : 'book_previous',
	text : 'book_previous'
}, {
	value : 'box',
	text : 'box'
}, {
	value : 'brick',
	text : 'brick'
}, {
	value : 'bricks',
	text : 'bricks'
}, {
	value : 'brick_add',
	text : 'brick_add'
}, {
	value : 'brick_delete',
	text : 'brick_delete'
}, {
	value : 'brick_edit',
	text : 'brick_edit'
}, {
	value : 'brick_error',
	text : 'brick_error'
}, {
	value : 'brick_go',
	text : 'brick_go'
}, {
	value : 'brick_link',
	text : 'brick_link'
}, {
	value : 'briefcase',
	text : 'briefcase'
}, {
	value : 'building',
	text : 'building'
}, {
	value : 'building_add',
	text : 'building_add'
}, {
	value : 'building_delete',
	text : 'building_delete'
}, {
	value : 'building_edit',
	text : 'building_edit'
}, {
	value : 'building_error',
	text : 'building_error'
}, {
	value : 'building_go',
	text : 'building_go'
}, {
	value : 'building_key',
	text : 'building_key'
}, {
	value : 'building_link',
	text : 'building_link'
}, {
	value : 'bullet_add',
	text : 'bullet_add'
}, {
	value : 'bullet_arrow_bottom',
	text : 'bullet_arrow_bottom'
}, {
	value : 'bullet_arrow_down',
	text : 'bullet_arrow_down'
}, {
	value : 'bullet_arrow_top',
	text : 'bullet_arrow_top'
}, {
	value : 'bullet_arrow_up',
	text : 'bullet_arrow_up'
}, {
	value : 'bullet_black',
	text : 'bullet_black'
}, {
	value : 'bullet_blue',
	text : 'bullet_blue'
}, {
	value : 'bullet_delete',
	text : 'bullet_delete'
}, {
	value : 'bullet_disk',
	text : 'bullet_disk'
}, {
	value : 'bullet_error',
	text : 'bullet_error'
}, {
	value : 'bullet_feed',
	text : 'bullet_feed'
}, {
	value : 'bullet_go',
	text : 'bullet_go'
}, {
	value : 'bullet_green',
	text : 'bullet_green'
}, {
	value : 'bullet_key',
	text : 'bullet_key'
}, {
	value : 'bullet_orange',
	text : 'bullet_orange'
}, {
	value : 'bullet_picture',
	text : 'bullet_picture'
}, {
	value : 'bullet_pink',
	text : 'bullet_pink'
}, {
	value : 'bullet_purple',
	text : 'bullet_purple'
}, {
	value : 'bullet_red',
	text : 'bullet_red'
}, {
	value : 'bullet_star',
	text : 'bullet_star'
}, {
	value : 'bullet_toggle_minus',
	text : 'bullet_toggle_minus'
}, {
	value : 'bullet_toggle_plus',
	text : 'bullet_toggle_plus'
}, {
	value : 'bullet_white',
	text : 'bullet_white'
}, {
	value : 'bullet_wrench',
	text : 'bullet_wrench'
}, {
	value : 'bullet_yellow',
	text : 'bullet_yellow'
}, {
	value : 'cake',
	text : 'cake'
}, {
	value : 'cancel',
	text : 'cancel'
}, {
	value : 'clock',
	text : 'clock'
}, {
	value : 'clock_add',
	text : 'clock_add'
}, {
	value : 'clock_delete',
	text : 'clock_delete'
}, {
	value : 'clock_edit',
	text : 'clock_edit'
}, {
	value : 'clock_error',
	text : 'clock_error'
}, {
	value : 'clock_go',
	text : 'clock_go'
}, {
	value : 'clock_link',
	text : 'clock_link'
}, {
	value : 'clock_pause',
	text : 'clock_pause'
}, {
	value : 'clock_play',
	text : 'clock_play'
}, {
	value : 'clock_red',
	text : 'clock_red'
}, {
	value : 'clock_stop',
	text : 'clock_stop'
}, {
	value : 'cog',
	text : 'cog'
}, {
	value : 'cog_add',
	text : 'cog_add'
}, {
	value : 'cog_delete',
	text : 'cog_delete'
}, {
	value : 'cog_edit',
	text : 'cog_edit'
}, {
	value : 'cog_error',
	text : 'cog_error'
}, {
	value : 'cog_go',
	text : 'cog_go'
}, {
	value : 'coins',
	text : 'coins'
}, {
	value : 'coins_add',
	text : 'coins_add'
}, {
	value : 'coins_delete',
	text : 'coins_delete'
}, {
	value : 'color_swatch',
	text : 'color_swatch'
}, {
	value : 'color_wheel',
	text : 'color_wheel'
}, {
	value : 'comment',
	text : 'comment'
}, {
	value : 'comments',
	text : 'comments'
}, {
	value : 'comments_add',
	text : 'comments_add'
}, {
	value : 'comments_delete',
	text : 'comments_delete'
}, {
	value : 'comment_add',
	text : 'comment_add'
}, {
	value : 'comment_delete',
	text : 'comment_delete'
}, {
	value : 'comment_edit',
	text : 'comment_edit'
}, {
	value : 'compress',
	text : 'compress'
}, {
	value : 'computer',
	text : 'computer'
}, {
	value : 'computer_add',
	text : 'computer_add'
}, {
	value : 'computer_delete',
	text : 'computer_delete'
}, {
	value : 'computer_edit',
	text : 'computer_edit'
}, {
	value : 'computer_error',
	text : 'computer_error'
}, {
	value : 'computer_go',
	text : 'computer_go'
}, {
	value : 'computer_key',
	text : 'computer_key'
}, {
	value : 'computer_link',
	text : 'computer_link'
}, {
	value : 'connect',
	text : 'connect'
}, {
	value : 'contrast',
	text : 'contrast'
}, {
	value : 'contrast_decrease',
	text : 'contrast_decrease'
}, {
	value : 'contrast_high',
	text : 'contrast_high'
}, {
	value : 'contrast_increase',
	text : 'contrast_increase'
}, {
	value : 'contrast_low',
	text : 'contrast_low'
}, {
	value : 'controller',
	text : 'controller'
}, {
	value : 'controller_add',
	text : 'controller_add'
}, {
	value : 'controller_delete',
	text : 'controller_delete'
}, {
	value : 'controller_error',
	text : 'controller_error'
}, {
	value : 'creditcards',
	text : 'creditcards'
}, {
	value : 'cup',
	text : 'cup'
}, {
	value : 'cup_add',
	text : 'cup_add'
}, {
	value : 'cup_delete',
	text : 'cup_delete'
}, {
	value : 'cup_edit',
	text : 'cup_edit'
}, {
	value : 'cup_error',
	text : 'cup_error'
}, {
	value : 'cup_go',
	text : 'cup_go'
}, {
	value : 'cup_key',
	text : 'cup_key'
}, {
	value : 'cup_link',
	text : 'cup_link'
}, {
	value : 'cursor',
	text : 'cursor'
}, {
	value : 'cut',
	text : 'cut'
}, {
	value : 'cut_red',
	text : 'cut_red'
}, {
	value : 'database',
	text : 'database'
}, {
	value : 'database_add',
	text : 'database_add'
}, {
	value : 'database_connect',
	text : 'database_connect'
}, {
	value : 'database_delete',
	text : 'database_delete'
}, {
	value : 'database_edit',
	text : 'database_edit'
}, {
	value : 'database_error',
	text : 'database_error'
}, {
	value : 'database_gear',
	text : 'database_gear'
}, {
	value : 'database_go',
	text : 'database_go'
}, {
	value : 'database_key',
	text : 'database_key'
}, {
	value : 'database_lightning',
	text : 'database_lightning'
}, {
	value : 'database_link',
	text : 'database_link'
}, {
	value : 'database_refresh',
	text : 'database_refresh'
}, {
	value : 'database_save',
	text : 'database_save'
}, {
	value : 'database_table',
	text : 'database_table'
}, {
	value : 'delete',
	text : 'delete'
}, {
	value : 'disconnect',
	text : 'disconnect'
}, {
	value : 'disk',
	text : 'disk'
}, {
	value : 'disk_multiple',
	text : 'disk_multiple'
}, {
	value : 'door',
	text : 'door'
}, {
	value : 'door_in',
	text : 'door_in'
}, {
	value : 'door_open',
	text : 'door_open'
}, {
	value : 'door_out',
	text : 'door_out'
}, {
	value : 'drink',
	text : 'drink'
}, {
	value : 'drink_empty',
	text : 'drink_empty'
}, {
	value : 'dvd',
	text : 'dvd'
}, {
	value : 'dvd_add',
	text : 'dvd_add'
}, {
	value : 'dvd_delete',
	text : 'dvd_delete'
}, {
	value : 'dvd_edit',
	text : 'dvd_edit'
}, {
	value : 'dvd_error',
	text : 'dvd_error'
}, {
	value : 'dvd_go',
	text : 'dvd_go'
}, {
	value : 'dvd_key',
	text : 'dvd_key'
}, {
	value : 'dvd_link',
	text : 'dvd_link'
}, {
	value : 'emoticon_evilgrin',
	text : 'emoticon_evilgrin'
}, {
	value : 'emoticon_grin',
	text : 'emoticon_grin'
}, {
	value : 'emoticon_happy',
	text : 'emoticon_happy'
}, {
	value : 'emoticon_smile',
	text : 'emoticon_smile'
}, {
	value : 'emoticon_surprised',
	text : 'emoticon_surprised'
}, {
	value : 'emoticon_tongue',
	text : 'emoticon_tongue'
}, {
	value : 'emoticon_unhappy',
	text : 'emoticon_unhappy'
}, {
	value : 'emoticon_waii',
	text : 'emoticon_waii'
}, {
	value : 'emoticon_wink',
	text : 'emoticon_wink'
}, {
	value : 'error',
	text : 'error'
}, {
	value : 'error_add',
	text : 'error_add'
}, {
	value : 'error_delete',
	text : 'error_delete'
}, {
	value : 'error_go',
	text : 'error_go'
}, {
	value : 'exclamation',
	text : 'exclamation'
}, {
	value : 'eye',
	text : 'eye'
}, {
	value : 'female',
	text : 'female'
}, {
	value : 'find',
	text : 'find'
}, {
	value : 'font',
	text : 'font'
}, {
	value : 'font_add',
	text : 'font_add'
}, {
	value : 'font_delete',
	text : 'font_delete'
}, {
	value : 'font_go',
	text : 'font_go'
}, {
	value : 'heart',
	text : 'heart'
}, {
	value : 'heart_add',
	text : 'heart_add'
}, {
	value : 'heart_delete',
	text : 'heart_delete'
}, {
	value : 'help',
	text : 'help'
}, {
	value : 'hourglass',
	text : 'hourglass'
}, {
	value : 'hourglass_add',
	text : 'hourglass_add'
}, {
	value : 'hourglass_delete',
	text : 'hourglass_delete'
}, {
	value : 'hourglass_go',
	text : 'hourglass_go'
}, {
	value : 'hourglass_link',
	text : 'hourglass_link'
}, {
	value : 'house',
	text : 'house'
}, {
	value : 'house_go',
	text : 'house_go'
}, {
	value : 'house_link',
	text : 'house_link'
}, {
	value : 'html',
	text : 'html'
}, {
	value : 'html_add',
	text : 'html_add'
}, {
	value : 'html_delete',
	text : 'html_delete'
}, {
	value : 'html_go',
	text : 'html_go'
}, {
	value : 'html_valid',
	text : 'html_valid'
}, {
	value : 'image',
	text : 'image'
}, {
	value : 'images',
	text : 'images'
}, {
	value : 'images_send',
	text : 'images_send'
}, {
	value : 'image_add',
	text : 'image_add'
}, {
	value : 'image_delete',
	text : 'image_delete'
}, {
	value : 'image_edit',
	text : 'image_edit'
}, {
	value : 'image_link',
	text : 'image_link'
}, {
	value : 'information',
	text : 'information'
}, {
	value : 'joystick',
	text : 'joystick'
}, {
	value : 'joystick_add',
	text : 'joystick_add'
}, {
	value : 'joystick_delete',
	text : 'joystick_delete'
}, {
	value : 'joystick_error',
	text : 'joystick_error'
}, {
	value : 'key',
	text : 'key'
}, {
	value : 'key_add',
	text : 'key_add'
}, {
	value : 'key_delete',
	text : 'key_delete'
}, {
	value : 'key_go',
	text : 'key_go'
}, {
	value : 'layers',
	text : 'layers'
}, {
	value : 'lightbulb',
	text : 'lightbulb'
}, {
	value : 'lightbulb_add',
	text : 'lightbulb_add'
}, {
	value : 'lightbulb_delete',
	text : 'lightbulb_delete'
}, {
	value : 'lightbulb_off',
	text : 'lightbulb_off'
}, {
	value : 'lightning',
	text : 'lightning'
}, {
	value : 'lightning_add',
	text : 'lightning_add'
}, {
	value : 'lightning_delete',
	text : 'lightning_delete'
}, {
	value : 'lightning_go',
	text : 'lightning_go'
}, {
	value : 'link',
	text : 'link'
}, {
	value : 'link_add',
	text : 'link_add'
}, {
	value : 'link_break',
	text : 'link_break'
}, {
	value : 'link_delete',
	text : 'link_delete'
}, {
	value : 'link_edit',
	text : 'link_edit'
}, {
	value : 'link_error',
	text : 'link_error'
}, {
	value : 'link_go',
	text : 'link_go'
}, {
	value : 'lorry',
	text : 'lorry'
}, {
	value : 'lorry_add',
	text : 'lorry_add'
}, {
	value : 'lorry_delete',
	text : 'lorry_delete'
}, {
	value : 'lorry_error',
	text : 'lorry_error'
}, {
	value : 'lorry_flatbed',
	text : 'lorry_flatbed'
}, {
	value : 'lorry_go',
	text : 'lorry_go'
}, {
	value : 'lorry_link',
	text : 'lorry_link'
}, {
	value : 'male',
	text : 'male'
}, {
	value : 'medal_bronze_1',
	text : 'medal_bronze_1'
}, {
	value : 'medal_bronze_2',
	text : 'medal_bronze_2'
}, {
	value : 'medal_bronze_3',
	text : 'medal_bronze_3'
}, {
	value : 'medal_bronze_add',
	text : 'medal_bronze_add'
}, {
	value : 'medal_bronze_delete',
	text : 'medal_bronze_delete'
}, {
	value : 'medal_gold_1',
	text : 'medal_gold_1'
}, {
	value : 'medal_gold_2',
	text : 'medal_gold_2'
}, {
	value : 'medal_gold_3',
	text : 'medal_gold_3'
}, {
	value : 'medal_gold_add',
	text : 'medal_gold_add'
}, {
	value : 'medal_gold_delete',
	text : 'medal_gold_delete'
}, {
	value : 'medal_silver_1',
	text : 'medal_silver_1'
}, {
	value : 'medal_silver_2',
	text : 'medal_silver_2'
}, {
	value : 'medal_silver_3',
	text : 'medal_silver_3'
}, {
	value : 'medal_silver_add',
	text : 'medal_silver_add'
}, {
	value : 'medal_silver_delete',
	text : 'medal_silver_delete'
}, {
	value : 'money',
	text : 'money'
}, {
	value : 'money_add',
	text : 'money_add'
}, {
	value : 'money_delete',
	text : 'money_delete'
}, {
	value : 'money_dollar',
	text : 'money_dollar'
}, {
	value : 'money_euro',
	text : 'money_euro'
}, {
	value : 'money_pound',
	text : 'money_pound'
}, {
	value : 'money_yen',
	text : 'money_yen'
}, {
	value : 'mouse',
	text : 'mouse'
}, {
	value : 'mouse_add',
	text : 'mouse_add'
}, {
	value : 'mouse_delete',
	text : 'mouse_delete'
}, {
	value : 'mouse_error',
	text : 'mouse_error'
}, {
	value : 'music',
	text : 'music'
}, {
	value : 'new',
	text : 'new'
}, {
	value : 'package',
	text : 'package'
}, {
	value : 'package_add',
	text : 'package_add'
}, {
	value : 'package_delete',
	text : 'package_delete'
}, {
	value : 'package_go',
	text : 'package_go'
}, {
	value : 'package_green',
	text : 'package_green'
}, {
	value : 'package_link',
	text : 'package_link'
}, {
	value : 'paintbrush',
	text : 'paintbrush'
}, {
	value : 'paintcan',
	text : 'paintcan'
}, {
	value : 'palette',
	text : 'palette'
}, {
	value : 'pencil',
	text : 'pencil'
}, {
	value : 'pencil_add',
	text : 'pencil_add'
}, {
	value : 'pencil_delete',
	text : 'pencil_delete'
}, {
	value : 'pencil_go',
	text : 'pencil_go'
}, {
	value : 'phone',
	text : 'phone'
}, {
	value : 'phone_add',
	text : 'phone_add'
}, {
	value : 'phone_delete',
	text : 'phone_delete'
}, {
	value : 'phone_sound',
	text : 'phone_sound'
}, {
	value : 'pilcrow',
	text : 'pilcrow'
}, {
	value : 'pill',
	text : 'pill'
}, {
	value : 'pill_add',
	text : 'pill_add'
}, {
	value : 'pill_delete',
	text : 'pill_delete'
}, {
	value : 'pill_go',
	text : 'pill_go'
}, {
	value : 'plugin',
	text : 'plugin'
}, {
	value : 'plugin_add',
	text : 'plugin_add'
}, {
	value : 'plugin_delete',
	text : 'plugin_delete'
}, {
	value : 'plugin_disabled',
	text : 'plugin_disabled'
}, {
	value : 'plugin_edit',
	text : 'plugin_edit'
}, {
	value : 'plugin_error',
	text : 'plugin_error'
}, {
	value : 'plugin_go',
	text : 'plugin_go'
}, {
	value : 'plugin_link',
	text : 'plugin_link'
}, {
	value : 'rainbow',
	text : 'rainbow'
}, {
	value : 'resultset_first',
	text : 'resultset_first'
}, {
	value : 'resultset_last',
	text : 'resultset_last'
}, {
	value : 'resultset_next',
	text : 'resultset_next'
}, {
	value : 'resultset_previous',
	text : 'resultset_previous'
}, {
	value : 'rosette',
	text : 'rosette'
}, {
	value : 'rss',
	text : 'rss'
}, {
	value : 'rss_add',
	text : 'rss_add'
}, {
	value : 'rss_delete',
	text : 'rss_delete'
}, {
	value : 'rss_go',
	text : 'rss_go'
}, {
	value : 'rss_valid',
	text : 'rss_valid'
}, {
	value : 'ruby',
	text : 'ruby'
}, {
	value : 'ruby_add',
	text : 'ruby_add'
}, {
	value : 'ruby_delete',
	text : 'ruby_delete'
}, {
	value : 'ruby_gear',
	text : 'ruby_gear'
}, {
	value : 'ruby_get',
	text : 'ruby_get'
}, {
	value : 'ruby_go',
	text : 'ruby_go'
}, {
	value : 'ruby_key',
	text : 'ruby_key'
}, {
	value : 'ruby_link',
	text : 'ruby_link'
}, {
	value : 'ruby_put',
	text : 'ruby_put'
}, {
	value : 'server',
	text : 'server'
}, {
	value : 'server_add',
	text : 'server_add'
}, {
	value : 'server_chart',
	text : 'server_chart'
}, {
	value : 'server_compressed',
	text : 'server_compressed'
}, {
	value : 'server_connect',
	text : 'server_connect'
}, {
	value : 'server_database',
	text : 'server_database'
}, {
	value : 'server_delete',
	text : 'server_delete'
}, {
	value : 'server_edit',
	text : 'server_edit'
}, {
	value : 'server_error',
	text : 'server_error'
}, {
	value : 'server_go',
	text : 'server_go'
}, {
	value : 'server_key',
	text : 'server_key'
}, {
	value : 'server_lightning',
	text : 'server_lightning'
}, {
	value : 'server_link',
	text : 'server_link'
}, {
	value : 'server_uncompressed',
	text : 'server_uncompressed'
}, {
	value : 'shading',
	text : 'shading'
}, {
	value : 'shape_align_bottom',
	text : 'shape_align_bottom'
}, {
	value : 'shape_align_center',
	text : 'shape_align_center'
}, {
	value : 'shape_align_left',
	text : 'shape_align_left'
}, {
	value : 'shape_align_middle',
	text : 'shape_align_middle'
}, {
	value : 'shape_align_right',
	text : 'shape_align_right'
}, {
	value : 'shape_align_top',
	text : 'shape_align_top'
}, {
	value : 'shape_flip_horizontal',
	text : 'shape_flip_horizontal'
}, {
	value : 'shape_flip_vertical',
	text : 'shape_flip_vertical'
}, {
	value : 'shape_group',
	text : 'shape_group'
}, {
	value : 'shape_handles',
	text : 'shape_handles'
}, {
	value : 'shape_move_back',
	text : 'shape_move_back'
}, {
	value : 'shape_move_backwards',
	text : 'shape_move_backwards'
}, {
	value : 'shape_move_forwards',
	text : 'shape_move_forwards'
}, {
	value : 'shape_move_front',
	text : 'shape_move_front'
}, {
	value : 'shape_rotate_anticlockwise',
	text : 'shape_rotate_anticlockwise'
}, {
	value : 'shape_rotate_clockwise',
	text : 'shape_rotate_clockwise'
}, {
	value : 'shape_square',
	text : 'shape_square'
}, {
	value : 'shape_square_add',
	text : 'shape_square_add'
}, {
	value : 'shape_square_delete',
	text : 'shape_square_delete'
}, {
	value : 'shape_square_edit',
	text : 'shape_square_edit'
}, {
	value : 'shape_square_error',
	text : 'shape_square_error'
}, {
	value : 'shape_square_go',
	text : 'shape_square_go'
}, {
	value : 'shape_square_key',
	text : 'shape_square_key'
}, {
	value : 'shape_square_link',
	text : 'shape_square_link'
}, {
	value : 'shape_ungroup',
	text : 'shape_ungroup'
}, {
	value : 'shield',
	text : 'shield'
}, {
	value : 'shield_add',
	text : 'shield_add'
}, {
	value : 'shield_delete',
	text : 'shield_delete'
}, {
	value : 'shield_go',
	text : 'shield_go'
}, {
	value : 'sitemap',
	text : 'sitemap'
}, {
	value : 'sitemap_color',
	text : 'sitemap_color'
}, {
	value : 'sound',
	text : 'sound'
}, {
	value : 'sound_add',
	text : 'sound_add'
}, {
	value : 'sound_delete',
	text : 'sound_delete'
}, {
	value : 'sound_low',
	text : 'sound_low'
}, {
	value : 'sound_mute',
	text : 'sound_mute'
}, {
	value : 'sound_none',
	text : 'sound_none'
}, {
	value : 'spellcheck',
	text : 'spellcheck'
}, {
	value : 'sport_8ball',
	text : 'sport_8ball'
}, {
	value : 'sport_basketball',
	text : 'sport_basketball'
}, {
	value : 'sport_football',
	text : 'sport_football'
}, {
	value : 'sport_golf',
	text : 'sport_golf'
}, {
	value : 'sport_raquet',
	text : 'sport_raquet'
}, {
	value : 'sport_shuttlecock',
	text : 'sport_shuttlecock'
}, {
	value : 'sport_soccer',
	text : 'sport_soccer'
}, {
	value : 'sport_tennis',
	text : 'sport_tennis'
}, {
	value : 'star',
	text : 'star'
}, {
	value : 'status_away',
	text : 'status_away'
}, {
	value : 'status_busy',
	text : 'status_busy'
}, {
	value : 'status_offline',
	text : 'status_offline'
}, {
	value : 'status_online',
	text : 'status_online'
}, {
	value : 'stop',
	text : 'stop'
}, {
	value : 'style',
	text : 'style'
}, {
	value : 'style_add',
	text : 'style_add'
}, {
	value : 'style_delete',
	text : 'style_delete'
}, {
	value : 'style_edit',
	text : 'style_edit'
}, {
	value : 'style_go',
	text : 'style_go'
}, {
	value : 'sum',
	text : 'sum'
}, {
	value : 'tab',
	text : 'tab'
}, {
	value : 'tab_add',
	text : 'tab_add'
}, {
	value : 'tab_delete',
	text : 'tab_delete'
}, {
	value : 'tab_edit',
	text : 'tab_edit'
}, {
	value : 'tab_go',
	text : 'tab_go'
}, {
	value : 'tag',
	text : 'tag'
}, {
	value : 'telephone',
	text : 'telephone'
}, {
	value : 'telephone_add',
	text : 'telephone_add'
}, {
	value : 'telephone_delete',
	text : 'telephone_delete'
}, {
	value : 'telephone_edit',
	text : 'telephone_edit'
}, {
	value : 'telephone_error',
	text : 'telephone_error'
}, {
	value : 'telephone_go',
	text : 'telephone_go'
}, {
	value : 'telephone_key',
	text : 'telephone_key'
}, {
	value : 'telephone_link',
	text : 'telephone_link'
}, {
	value : 'textfield',
	text : 'textfield'
}, {
	value : 'textfield_add',
	text : 'textfield_add'
}, {
	value : 'textfield_delete',
	text : 'textfield_delete'
}, {
	value : 'textfield_key',
	text : 'textfield_key'
}, {
	value : 'textfield_rename',
	text : 'textfield_rename'
}, {
	value : 'text_align_center',
	text : 'text_align_center'
}, {
	value : 'text_align_justify',
	text : 'text_align_justify'
}, {
	value : 'text_align_left',
	text : 'text_align_left'
}, {
	value : 'text_align_right',
	text : 'text_align_right'
}, {
	value : 'text_allcaps',
	text : 'text_allcaps'
}, {
	value : 'text_bold',
	text : 'text_bold'
}, {
	value : 'text_columns',
	text : 'text_columns'
}, {
	value : 'text_dropcaps',
	text : 'text_dropcaps'
}, {
	value : 'text_heading_1',
	text : 'text_heading_1'
}, {
	value : 'text_heading_2',
	text : 'text_heading_2'
}, {
	value : 'text_heading_3',
	text : 'text_heading_3'
}, {
	value : 'text_heading_4',
	text : 'text_heading_4'
}, {
	value : 'text_heading_5',
	text : 'text_heading_5'
}, {
	value : 'text_heading_6',
	text : 'text_heading_6'
}, {
	value : 'text_horizontalrule',
	text : 'text_horizontalrule'
}, {
	value : 'text_indent',
	text : 'text_indent'
}, {
	value : 'text_indent_remove',
	text : 'text_indent_remove'
}, {
	value : 'text_italic',
	text : 'text_italic'
}, {
	value : 'text_kerning',
	text : 'text_kerning'
}, {
	value : 'text_letterspacing',
	text : 'text_letterspacing'
}, {
	value : 'text_letter_omega',
	text : 'text_letter_omega'
}, {
	value : 'text_linespacing',
	text : 'text_linespacing'
}, {
	value : 'text_list_bullets',
	text : 'text_list_bullets'
}, {
	value : 'text_list_numbers',
	text : 'text_list_numbers'
}, {
	value : 'text_lowercase',
	text : 'text_lowercase'
}, {
	value : 'text_padding_bottom',
	text : 'text_padding_bottom'
}, {
	value : 'text_padding_left',
	text : 'text_padding_left'
}, {
	value : 'text_padding_right',
	text : 'text_padding_right'
}, {
	value : 'text_padding_top',
	text : 'text_padding_top'
}, {
	value : 'text_replace',
	text : 'text_replace'
}, {
	value : 'text_signature',
	text : 'text_signature'
}, {
	value : 'text_smallcaps',
	text : 'text_smallcaps'
}, {
	value : 'text_strikethrough',
	text : 'text_strikethrough'
}, {
	value : 'text_subscript',
	text : 'text_subscript'
}, {
	value : 'text_superscript',
	text : 'text_superscript'
}, {
	value : 'text_underline',
	text : 'text_underline'
}, {
	value : 'text_uppercase',
	text : 'text_uppercase'
}, {
	value : 'Thumbs.db',
	text : 'Thumbs.db'
}, {
	value : 'thumb_down',
	text : 'thumb_down'
}, {
	value : 'thumb_up',
	text : 'thumb_up'
}, {
	value : 'tick',
	text : 'tick'
}, {
	value : 'time',
	text : 'time'
}, {
	value : 'timeline_marker',
	text : 'timeline_marker'
}, {
	value : 'time_add',
	text : 'time_add'
}, {
	value : 'time_delete',
	text : 'time_delete'
}, {
	value : 'time_go',
	text : 'time_go'
}, {
	value : 'transmit',
	text : 'transmit'
}, {
	value : 'transmit_add',
	text : 'transmit_add'
}, {
	value : 'transmit_blue',
	text : 'transmit_blue'
}, {
	value : 'transmit_delete',
	text : 'transmit_delete'
}, {
	value : 'transmit_edit',
	text : 'transmit_edit'
}, {
	value : 'transmit_error',
	text : 'transmit_error'
}, {
	value : 'transmit_go',
	text : 'transmit_go'
}, {
	value : 'tux',
	text : 'tux'
}, {
	value : 'vector',
	text : 'vector'
}, {
	value : 'vector_add',
	text : 'vector_add'
}, {
	value : 'vector_delete',
	text : 'vector_delete'
}, {
	value : 'wand',
	text : 'wand'
}, {
	value : 'weather_clouds',
	text : 'weather_clouds'
}, {
	value : 'weather_cloudy',
	text : 'weather_cloudy'
}, {
	value : 'weather_lightning',
	text : 'weather_lightning'
}, {
	value : 'weather_rain',
	text : 'weather_rain'
}, {
	value : 'weather_snow',
	text : 'weather_snow'
}, {
	value : 'weather_sun',
	text : 'weather_sun'
}, {
	value : 'webcam',
	text : 'webcam'
}, {
	value : 'webcam_add',
	text : 'webcam_add'
}, {
	value : 'webcam_delete',
	text : 'webcam_delete'
}, {
	value : 'webcam_error',
	text : 'webcam_error'
}, {
	value : 'wrench',
	text : 'wrench'
}, {
	value : 'wrench_orange',
	text : 'wrench_orange'
} ];

/**
 * 茅华添加的
 */
utf16to8 = function(str){
	
	 var out, i, len, c;
	  out = "";
	  len = str.length;
	  for (i = 0; i < len; i++) {
	   c = str.charCodeAt(i);
	   if ((c >= 0x0001) && (c <= 0x007F)) {
	    out += str.charAt(i);
	   } else if (c > 0x07FF) {
	    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
	    out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
	    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
	   } else {
	    out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
	    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
	   }
	  }
	  return out;
};

/** */
//点击二维码  获取到点击是的坐标  并实现其相关功能
	mouseMove=function (ev) {
		Ev = ev || window.event;
		var mousePos = mouseCoords(ev);
		return {xx : mousePos.x,yy : mousePos.y};
		
		function mouseCoords(ev) {
			if (ev.pageX || ev.pageY) {
				return {
					x : ev.pageX,
					y : ev.pageY
				};
			}
			return {
				x : ev.clientX + document.body.scrollLeft - document.body.clientLeft,
				y : ev.clientY + document.body.scrollTop - document.body.clientTop
			};
		}
};
/**图片导出*/
	
	imgDownload = function(canvas){
		// 图片导出为 png 格式
		var type = 'png';
		var imgData = canvas.toDataURL(type);
		/**获取mimeType*/
		var _fixType = function(type) {
		    type = type.toLowerCase().replace(/jpg/i, 'jpeg');
		    var r = type.match(/png|jpeg|bmp|gif/)[0];
		    return 'image/' + r;
		};
		// 加工image data，替换mime type
		imgData = imgData.replace(_fixType(type),'image/octet-stream');
		
		// 下载后的问题名
		var filename = 'baidufe_' + (new Date()).getTime() + '.' + type;
		// download
		saveFile(imgData,filename);
	};

	/**
	 * 在本地进行文件保存
	 * @param  {String} data     要保存到本地的图片数据
	 * @param  {String} filename 文件名
	 */
	saveFile = function(data, filename){
	    var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
	    save_link.href = data;
	    save_link.download = filename;
	   
	    var event = document.createEvent('MouseEvents');
	    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
	    save_link.dispatchEvent(event);
	};

	 //将上传的图片统一大小
	 size = function(docObj,imgObjPreview,width,height){
					 if(docObj.files &&    docObj.files[0]){
		                 //火狐下，直接设img属性
		                 //imgObjPreview.style.width = '60px';
		                 //imgObjPreview.style.height = '60px';  
		                 imgObjPreview.style.width = width;
		                 imgObjPreview.style.height = height;
		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		         }else{
		                 //IE下，使用滤镜
		                 docObj.select();
		                 var imgSrc = document.selection.createRange().text;
		                 var localImagId = document.getElementById("localImag");
		                 //必须设置初始大小
		                 //localImagId.style.width = "60px";
		                 //localImagId.style.height = "60px";
		                 localImagId.style.width = width;
		                 localImagId.style.height = height;
		                 //图片异常的捕捉，防止用户修改后缀来伪造图片
						try{
		                         localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
		                         localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		                 }catch(e){
		                         alert("您上传的图片格式不正确，请重新选择!");
		                         return false;
		                 }
		         }
	 };