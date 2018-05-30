<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(function(){
			$.ajax({
				url : 'warehouseAction!findWareHousePic.action',
				type : 'post',
				data : {"id" : "<%= request.getParameter("id")%>"},
				dataType : 'text',
				success : function(data){
					var obj = $.parseJSON(data);
					$("#showPic_body").append("<td><img src=\"warehouseAction!showWhesdtlPics.action?path="+obj.path+"\"/></td>");
				}
			});
		});
	</script>

  	<div id="showPic_div" class="easyui-layout" data-options="fit:true" style="width:900px;height:700px;">   
		<div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
			<table align="center">
					<tbody id="showPic_body">
							
					</tbody>  	
			</table>
		</div>   
	</div>  

