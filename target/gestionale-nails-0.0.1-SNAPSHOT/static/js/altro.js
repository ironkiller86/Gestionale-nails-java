/**
 * 
 */
function disable(){
	var checkbox = document.getElementById("checkEnd").checked;
	var one = document.getElementById("data1"); 
	var two = document.getElementById("data2"); 
	var label1 = document.getElementById("l1"); 
	var label2 = document.getElementById("l2"); 
	
	if(checkbox == true){
		one.style.display = "none";
		two.style.display = "none";
		label1.style.display = "none";
		label2.style.display = "none";
	}
	else{
		one.style.display = "block";
		two.style.display = "block";
		label1.style.display = "block";
		label2.style.display = "block";
	}
		
	
}