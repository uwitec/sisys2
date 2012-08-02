function addDK(){

   var re =window.showModalDialog("DisqKindAdd.jsp",null,"dialogWidth=400px;" +
   "dialogHeight=400px;toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, status=no");
    if(re==1){
        window.location.reload();
    }
}


function openwin() {
	window.open ("DisqKindAdd.jsp", "newwindow", "height=100, width=400, toolbar=no, " +
		"menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	
} 

function subForm(){
	
    document.getElementById("myForm").submit();
}

function addMore(height) {
	
	var quaNum = document.getElementsByName("quaNum").value;
	window.location.href='formAdd.action?height='+height;
	
}