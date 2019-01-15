function multiplication(){
	var quantityString = document.getElementById("myList").value; 
	var qt = Number(quantityString);
	var price = document.getElementById("amount").value;
	if(!isNaN(qt)){
	  var total = qt * price;
	  document.getElementById("total_field").value = total;
     }
	else {
		document.getElementById("total_field").value = "";	
	}
}

