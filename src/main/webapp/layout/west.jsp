<%@ page language="java" pageEncoding="UTF-8"%>

<div class="easyui-panel"
	data-options="title:'menu',border:false,fit:true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="Warehouse" data-options="iconCls:'icon-save'">
			<ul id='layout_west_tree' class="easyui-tree" data-options="onLoadSuccess:function(node, data){$(this).tree('collapseAll')}">    
				<li><span>Welcome</span>
					<ul><li><span>Welcome</span></li></ul>
				</li>
			</ul>
			<!--  不使用权限，开始全部加载树
			<ul class="easyui-tree"
				data-options="
		        	url:'${pageContext.request.contextPath}/menuAction!doNotNeedSessionAndSecurity_getAllTreeNode.action',
		        	parentField:'pid',
		        	lines:true,
		        	onLoadSuccess:function(node, data){$(this).tree('collapseAll')},
		        	onClick:function(node){
		        		if(node.attributes.url){
			        		var url ='${pageContext.request.contextPath}'+node.attributes.url;
			        		addTab({title:node.text,href:url,closable:true});
		        		}
		        		
		        	}"></ul>
		     -->
		</div>
		
		<div title="Base date" data-options="iconCls:'icon-reload'"></div>
		<div title="Report" data-options="iconCls:'icon-reload'"></div>
		<div title="Admin" data-options="iconCls:'icon-reload'"></div>
		
	</div>
</div>
