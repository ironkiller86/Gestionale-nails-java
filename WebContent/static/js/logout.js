/**
 * 
 */

 $(document).ready(function() {
	 $( "#logOut" ).click(function() {
		    $( "#dialog-logOut" ).dialog({
		        resizable: false,
		        height: "auto",
		        width: 400,
		        modal: true,
		        buttons: {
		          "NO": function() {
		            $( this ).dialog( "close" );
		          },
		          SI: function() {
		        	  window.location.href ='adminController?page=logOut';
		          }
		        }
		      });
		 
	 });
	 
 });