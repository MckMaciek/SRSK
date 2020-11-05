var password = document.getElementById("password");
var sPassword = document.getElementById("password_check");


function validatePass(){

if (password.value != sPassword.value){
sPassword.setCustomValidity("Passwords do not match!");
}else{
sPassword.setCustomValidity("");
}

}

password.onchange = validatePass;
sPassword.onkeyup = validatePass;