  var xmlHttp;        
        function creatXmlHttp(){   
        	if(window.XMLHttpRequest) { //Mozilla 浏览器  
            	xmlHttp = new XMLHttpRequest();  
       		 }  else if (window.ActiveXObject) { // IE浏览器  
            	try {  
                	xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");  
            	} catch (e) {  
               	 	try {  
                   		 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  
                	} catch (e) {//页面不正常  
                		window.alert("您所请求的页面有异常。");}  
            	}  
        	}  
        	
        }
        
        function display(){
          var proNo =document.getElementById("proNo").value.trim(); 
          if(proNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddBatch.action?proNo="+proNo , true);
          xmlHttp.onreadystatechange = dowork;
          xmlHttp.send(null);
        }
    
       function dowork(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			var flow = text.split(";");
       			if(text == "pnone"){
       				alert("输入的产品编号有误，请重新输入！");
       			} else {
       			if(flow[0].indexOf("<input") != 0){
       				flow[0] = "未找到相应流程！请重新输入产品编号";
       			}
       			document.getElementById("flowpath").innerHTML = flow[0];//将内容放入
       			document.getElementById("batchNo").value = flow[1];
       			var deptNames = flow[2];
       			
       			var sel = document.getElementById("deptId");
       			var depts;
       			if(deptNames != "") {
       				depts = flow[2].split("-");
       			}
       			if(flow.length == 4) {
       				sel.options.add(new Option(depts[0],depts[0]));
       				document.getElementById("deptId").disabled = false;
       			}
       			for(var i=0; i<depts.length; i++) {
       				sel.options.add(new Option(depts[i],depts[i]))
       			}
       			}
       			
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }