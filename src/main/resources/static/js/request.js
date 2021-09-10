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
	let comments = document.getElementById("comments").value;


	

	let valid = 1;
	
	document.getElementById("warning-msg").classList.add("alert");
	document.getElementById("warning-msg").classList.add("alert-warning");
    
    if(nullCheck(rqstTitle) && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please enter request title field";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }

	if(nullCheck(rqstDesc) && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please enter request Description field";
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
		document.getElementById("warning-msg").innerHTML = "Please Select A User iD";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }

	if(nullCheck(comments) && valid == 1) {
		
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Please Give Some Comments";
		setTimeout(()=> {
			document.getElementById("warning-msg").style.display = "none";
		}, 4000);
		
        valid = 0;
    }

	if(valid == 0) {
		e.preventDefault();
	}

});
