<%@ page language="java"  pageEncoding="UTF-8"%>
<script type="text/javascript">
	function addTab(opts){
		var t = $('#layout_center_tabs');
		if(t.tabs('exists',opts.title)){
			//t.tabs('select',opts.title);
			t.tabs('close',opts.title);
			t.tabs('add',opts);
		}else{
			t.tabs('add',opts);
		}
		
	}
</script>

<div id="layout_center_tabs" class="easyui-tabs"
	data-options="fit:true,border:false">
	<div title="Welcome">
		<div><label id="layout_center_welcome_label">Welcome To USF</label></div>
		<div id="layout_center_welcome_show" style="display:none;width: 1700px;height: 700px;">
		</div>
	</div>
</div>
