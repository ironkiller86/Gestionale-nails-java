function activeCheckbox() {
	var checkBox = document.getElementsByClassName("myCheck");
	var text = document.getElementById("button");
	var i;
	for (i = 0; i < checkBox.length; i++) {
		if (checkBox[i].checked == true) {
			text.style.display = "block";
			break;
		} else {
			text.style.display = "none";
		}
	}
}