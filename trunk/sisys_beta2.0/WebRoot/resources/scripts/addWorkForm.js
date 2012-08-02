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
        //根据产品编号得到产品名称
        function displayProNo(){
          var proNo =document.getElementById("proNo").value.trim(); 
          if(proNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormProNo.action?proNo="+proNo , true);
          xmlHttp.onreadystatechange = doworkProNo;
          xmlHttp.send(null);
        }
    
       function doworkProNo(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("proName").value = "";
       				alert("该产品编号不存在！请重新输入！");
       			}else{
       				document.getElementById("proName").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }
       
       //根据工号得到员工姓名
        function displayStaNo(){
          var staNo =document.getElementById("staNo").value.trim(); 
          if(staNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
          xmlHttp.onreadystatechange = doworkStaNo;
          xmlHttp.send(null);
        }
    
       function doworkStaNo(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("staName").value = "";
       				alert("该工号不存在！请重新输入！");
       			}else{
       				document.getElementById("staName").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }
       
       //根据工序号得到工序名称
        function displayProcNo(){
          var procNo =document.getElementById("procNo").value.trim(); 
          if(procNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormProcNo.action?procNo="+procNo , true);
          xmlHttp.onreadystatechange = doworkProcNo;
          xmlHttp.send(null);
        }
    
       function doworkProcNo(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("procName").value = "";
       				alert("该工序号不存在！请重新输入！");
       			}else{
       				document.getElementById("procName").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }