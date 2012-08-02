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
       			if(text.indexOf("<input") != 0){
       				text = "未找到相应流程！请重新输入产品编号";
       			}
       			document.getElementById("flowpath").innerHTML = text;//将内容放入
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }