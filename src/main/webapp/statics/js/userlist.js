/*
 * 全选功能
 */
function hasAll(){
		var flag=document.getElementById("checkall").checked;
		var all=document.getElementsByName("all");
		for(var i=0;i<all.length;i++){
			all[i].checked=flag;
		}
	}
/*
 * 删除确认
 */
	function deleteOne(i){
		if(confirm("确定要删除数据吗？")){
		location.href="http://localhost:8080/Origins/userMinus?id="+i;
	}}
	/*
	 * 批量删除确认
	 */
	function deleteAll(){
		var id =document.getElementsByName("id");
		var str="";
		var all=document.getElementsByName("all");
		for(var i=0;i<all.length;i++){
		if(all[i].checked){
			str+="ids="+id[i].value+"&";
		}}
		if(str!=""&&confirm("确定要删除该数据吗？")){
		str=str.substring(0,str.length-1);
		location.href="http://localhost:8080/Origins/userAllMinus?"+str;
	}}
	/*
	 * 修改数据功能
	 */
	function change(id){
		var point;
		var allid=document.getElementsByName("id"); 
		for(var i=0;i<allid.length;i++){
			if(allid[i].value==id){
				point=i;
				break;
			}
		}
		var username=document.getElementsByName("username").value();
		var age=document.getElementsByName("age").value();
		var catagory=document.getElementsByName("catagory").value();
		var sex=document.getElementsByName("sex").value();
		var username=document.getElementsByName("username").value();
		var password=document.getElementsByName("password").value();

		if(confirm("确定修改该数据?")){
			alert("修改成功");
			window.location.href="userChange?id="+id+"&username="+username+"&age="+age+"&catagory="+catagory+"&sex="+sex+"&username="+username+"&password="+password;
		}
	}