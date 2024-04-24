function chname(txt)
	{
	val=txt.value;
	ch=/^[A-Za-z]+$/;
	if(!(val.match(ch)))
	{
	alert('Alphabets only accepted');
	txt.focus();
	}
}
function chphone(txt)
	{
	val=txt.value;
	ch=/^[0-9]+$/;
	if(!(val.match(ch)) || val.length<10 || val.length>10)
	{
	alert('Invalid Phone Number');
	txt.focus();
	return false;
	}
}
function chlandline(txt)
{
val=txt.value;
	ch=/^[0-9]+$/;
	if(!(val.match(ch)) || val.length<11 || val.length>11)
	{
	alert('Invalid Phone Number');
	txt.focus();
	return false;
	}
}
function checknum(txt)
	{
	val=txt.value;
	ch=/^[0-9]+$/;
	if(!(val.match(ch)))
	{
	alert('Integer value only accepted');
	txt.focus();
	return false;
	}
}
function chemail(txt)
	{
	val=txt.value;
	ch=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(!(val.match(ch)))
	{
	alert('Invalid Email');
	txt.focus();
	}
}
function checkuser()
{
	pass = document.getElementById("t_pass").value;
	cpass = document.getElementById("t_cpass").value;
	
	if(pass == cpass)
		return true;
	else
	{
		alert("Password Mismatch");
		return false;
	}
	
}