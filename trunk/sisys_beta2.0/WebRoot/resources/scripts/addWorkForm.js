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
       
       //根据工序号得到工序名称
        function displayProcNo(){
          var procNo =document.getElementById("procNo").value.trim(); 
          var batNo =document.getElementById("batNo").value.trim(); 
          var proNo =document.getElementById("proNo").value.trim(); 
          if(procNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("POST", "preAddWorkFormProcNo.action?procNo="+procNo+"&batNo="+batNo+"&proNo="+proNo , true);
          xmlHttp.onreadystatechange = doworkProcNo;
          xmlHttp.send(null);
        }
    
       function doworkProcNo(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("procName").value = "";
       				alert("该批次或工序不存在！请重新输入！");
       			}else{
       				document.getElementById("procName").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }
       
       //根据工号得到员工姓名
        function displayStaNo1(){
          var staNo =document.getElementById("staNo1").value.trim(); 
          if(staNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
          xmlHttp.onreadystatechange = doworkStaNo1;
          xmlHttp.send(null);
        }
    
       function doworkStaNo1(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("staName1").value = "";
       				alert("该工号不存在！请重新输入！");
       			}else{
       				document.getElementById("staName1").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }
       
     //根据工号得到责任人姓名
       function displayRespNo(){
         var staNo =document.getElementById("respNo").value.trim(); 
         if(staNo == ""){
       	  return;
         }
         creatXmlHttp();
         xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
         xmlHttp.onreadystatechange = doworkRespNo;
         xmlHttp.send(null);
       }
   
      function doworkRespNo(){
         if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
     		if (xmlHttp.status == 200) { // 如果服务端回应OK
      			var text = xmlHttp.responseText;
      			if(text == "error"){
      				document.getElementById("respName").value = "";
      				alert("该工号不存在！请重新输入！");
      			}else{
      				document.getElementById("respName").value = text;//将内容放入
      			}       				
     		}else { //页面不正常  
               window.alert("您所请求的页面有异常。");  
           }  
   	  }
      }
      
    //根据工号得到审批人姓名
      function displayCheckNo(){
        var staNo =document.getElementById("checkNo").value.trim(); 
        if(staNo == ""){
      	  return;
        }
        creatXmlHttp();
        xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
        xmlHttp.onreadystatechange = doworkCheckNo;
        xmlHttp.send(null);
      }
  
     function doworkCheckNo(){
        if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
    		if (xmlHttp.status == 200) { // 如果服务端回应OK
     			var text = xmlHttp.responseText;
     			if(text == "error"){
     				document.getElementById("checkName").value = "";
     				alert("该工号不存在！请重新输入！");
     			}else{
     				document.getElementById("checkName").value = text;//将内容放入
     			}       				
    		}else { //页面不正常  
              window.alert("您所请求的页面有异常。");  
          }  
  	  }
     }
              
       //根据工号得到员工姓名
        function displayStaNo2(){
          var staNo =document.getElementById("staNo2").value.trim(); 
          if(staNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
          xmlHttp.onreadystatechange = doworkStaNo2;
          xmlHttp.send(null);
        }
    
       function doworkStaNo2(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("staName2").value = "";
       				alert("该工号不存在！请重新输入！");
       			}else{
       				document.getElementById("staName2").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }
              
       //根据工号得到员工姓名
        function displayStaNo3(){
          var staNo =document.getElementById("staNo3").value.trim(); 
          if(staNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
          xmlHttp.onreadystatechange = doworkStaNo3;
          xmlHttp.send(null);
        }
    
       function doworkStaNo3(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("staName3").value = "";
       				alert("该工号不存在！请重新输入！");
       			}else{
       				document.getElementById("staName3").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }
       
              
       //根据工号得到员工姓名
        function displayStaNo4(){
          var staNo =document.getElementById("staNo4").value.trim(); 
          if(staNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
          xmlHttp.onreadystatechange = doworkStaNo4;
          xmlHttp.send(null);
        }
    
       function doworkStaNo4(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("staName4").value = "";
       				alert("该工号不存在！请重新输入！");
       			}else{
       				document.getElementById("staName4").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }
       
              
       //根据工号得到员工姓名
        function displayStaNo5(){
          var staNo =document.getElementById("staNo5").value.trim(); 
          if(staNo == ""){
        	  return;
          }
          creatXmlHttp();
          xmlHttp.open("GET", "preAddWorkFormStaNo.action?staNo="+staNo , true);
          xmlHttp.onreadystatechange = doworkStaNo5;
          xmlHttp.send(null);
        }
    
       function doworkStaNo5(){
          if (xmlHttp.readyState == 4) { // 测试状态是否请求完成
      		if (xmlHttp.status == 200) { // 如果服务端回应OK
       			var text = xmlHttp.responseText;
       			if(text == "error"){
       				document.getElementById("staName5").value = "";
       				alert("该工号不存在！请重新输入！");
       			}else{
       				document.getElementById("staName5").value = text;//将内容放入
       			}       				
      		}else { //页面不正常  
                window.alert("您所请求的页面有异常。");  
            }  
    	  }
       }