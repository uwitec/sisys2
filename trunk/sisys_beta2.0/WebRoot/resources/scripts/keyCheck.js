document.onkeypress = check 

function check(e) {
	var keynum  
	var keychar  
  
	if(window.event) // IE  
    {  
    	keynum = e.keyCode  
    	alert(keynum)  
    }  
	else if(e.which) // Netscape/Firefox/Opera  
    {  
    	keynum = e.which  
    }  
	keychar = String.fromCharCode(keynum)//得到输入的字符，不包括键盘的那些功能键，如enter，shift，delete，tab等，  
	//但一些功能键确实也有自己的keyCode，比如enter的keyCode是13 
	if(keychar=="\r\n" || keychar=="\n" || keynum == 13) {
		document.getElementById("barCode").style.readonly = true;
		var barCode = document.getElementById("barCode");
		document.getElementById("myForm1").submit();
	} 
}

function recheck() {
	var barCode = document.getElementById("barCode").value;
	barCode.replace(/\r\n/gi, " ");
	var array = barCode.split(' ');
	barCode = array[0];
	document.getElementById("barCode").value = barCode;
	document.getElementById("barCode").style.readonly = true;
	var barCode = document.getElementById("barCode");
	document.getElementById("myForm1").submit();
	
}