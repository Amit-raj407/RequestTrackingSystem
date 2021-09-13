document.getElementById("warning-msg").style.display = "none";

function nullCheck(params) {
    if(params.trim()==='') {
        return true
    }
    else
        return false
}


document.getElementById("btn-submit").addEventListener("click", (e) => {
	let rqstTitle = document.getElementById("rqstTitle").value;
	let rqstDesc = document.getElementById("rqstDesc").value;
	let deptSelect = document.getElementById("deptSelect").value;
	let userSelect = document.getElementById("userSelect").value;
	let status = document.getElementById("status").value;


	

	let valid = 1;
	
	document.getElementById("warning-msg").classList.add("alert");
	document.getElementById("warning-msg").classList.add("alert-warning");
	document.getElementById("warning-msg").classList.add("text-center");
	
	if(nullCheck(rqstTitle) && nullCheck(rqstDesc) && valid == 1) {
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please Enter All The Required* Fields";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
	}
    
    if(nullCheck(rqstTitle) && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please Enter Request Title Field";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }

	if(nullCheck(rqstDesc) && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please Enter Request Description Field";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }

	if(deptSelect=="none" && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please Select A Department code";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }

	if(userSelect=="none" && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please Select A User ID";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }

	if(status == "none" && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please Select A Status";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }


	if(valid == 0) {
		e.preventDefault();
	}

});
