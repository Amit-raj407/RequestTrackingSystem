const inputs = document.querySelectorAll(".input");

document.getElementById("warning-msg").style.display = "none";

function addcl(){
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl(){
	let parent = this.parentNode.parentNode;
	if(this.value == ""){
		parent.classList.remove("focus");
	}
}


inputs.forEach(input => {
	input.addEventListener("focus", addcl);
	input.addEventListener("blur", remcl);
});

function nullCheck(params) {
    if(params.trim()==='') {
        return true
    }
    else
        return false
}


document.getElementById("btn-submit").addEventListener("click", (e) => {
	let userName = document.getElementById("userName").value;
	let password = document.getElementById("password").value;

	let valid = 1;
    
    if(nullCheck(userName) && valid == 1) {
        // alert("UserName/Email cannot be empty");
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "UserName/Email cannot be empty";
        valid = 0;
    }

	if(nullCheck(password) && valid == 1) {
        // alert("Password cannot be empty");
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "Password cannot be empty";
        valid = 0;
    }

	if(valid == 0) {
		e.preventDefault();
	}

});



document.getElementById("userName").addEventListener("focusout", () => {
	let userName = document.getElementById("userName").value;
	if(userName.length <= 8) {
		document.getElementById("warning-msg").style.display = "block";
		document.getElementById("warning-msg").innerHTML = "UserName/Email length should be greater than 8";
		document.getElementById("btn-submit").disabled = true;
	} else {
		document.getElementById("warning-msg").style.display = "none";
		document.getElementById("btn-submit").disabled = false;
	}
})