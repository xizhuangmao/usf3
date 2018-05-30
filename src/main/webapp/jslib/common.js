
/*
 * by zw
 * 下面两方法格式化easyui-datebox的时间格式为YYYY-MM-DD
 */
	function timeformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }

	function timeparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
	}
	//鼠标点击时 获取点击在页面的坐标
	function mouseMove (ev) {
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

/*
$(function(){
	//获取国家列表
	$('.getcountry').combobox({    
		url:'${pageContext.request.contextPath}/countryAction!getCountryName.action',
		onSelect:function(record){
			console.info($(this));
			var url = '${pageContext.request.contextPath}/countryAction!getCountryName.action';  
			 $(this).combobox({
		            'url': url,
		            valueField: 'id',    
					textField: 'country',	
		            }); 
		}
	}); 
	
	//从国家下拉获取省份列表
	$('.getstate').combobox({    

		onSelect:function(record){
			var url = '${pageContext.request.contextPath}/stateAction!getStateName.action?countryId='+record.id;  
			 $(this).parent().parent().parent().parent().find($('.getstate')).combobox({
		            'url': url,
		            valueField: 'id',    
					textField: 'state',	
		            }); 
		}
	}); 
	//从省份下拉获取城市列表
	$('.getcity').combobox({    
		onSelect:function(record){
			var url = '${pageContext.request.contextPath}/cityAction!getCityName.action?stateId='+record.id;  
			$(this).parent().parent().parent().parent().find($('.getcity')).combobox({
		            'url': url,
		            valueField: 'id',    
					textField: 'city',	
		            }); 
		}
	});
});*/

