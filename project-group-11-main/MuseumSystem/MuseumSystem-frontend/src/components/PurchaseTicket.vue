<template>

<div id="PurchaseTickets">

  	
	
	<h1> Purchase Tickets </h1>
	

	<h3> Ticket Price:</h3>
	<div v-for="(item, index) in EntryPassesList">
		<h3>{{price}}</h3>
	</div> <br>
	<label for="ticketDate">Date:</label><br>
	<input type="date" id="ticketDate" name="ticketDate"><br><br>
	<label for="tickets"> Number of tickets (1-10):</label><br>
	<input type="number" id="numberOfTickets" name="tickets"
       min="1" max="10"><br><br>
       
    <a class="button" @click="purchase(null)">Checkout</a>
	
	
</div>

</template>

<script src="./BuyEntrypass.js">
  
 
</script>

<style>
    /* body {
		background-color: FloralWhite;
	} */

    /* h1 {
		color: tomato;
		text-align: center;
		font-family: Georgia;
		font-size: 40px;
		text-shadow: 2px 2px 5px lightgrey;
		margin-top: 25px;
	} */
	
	/* h2 {
		color: white;
		text-align: center;
	} */
    /* h3 {
		color: tomato;
		text-align: center;
		font-family: Georgia;
		font-size: 20px;
		text-shadow: 2px 2px 5px lightgrey;
        margin-top: 20px;
	} */
	
	/* p {
		font-family: Georgia;
		font-size: 20px;
	} */

	#PurchaseTickets{
		text-align: center;
		align-items: center;

	}

	#PurchaseTickets .button{
		background-color: rgba(255, 99, 71, 0.6);
	  font-family: Georgia;
	  border: none;
	  color: black;
	  padding: 6px 8px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 20px;
	  margin: 4px 2px;
	  cursor: pointer;
	  border-radius: 10px;
	  transition:0.5s;
	}

	#PurchaseTickets .button:hover{
		background-color: rgb(255, 99, 71);
		color: black;
		opacity: 1;
    	text-decoration: none;
	}

	/* .button {
	  background-color: dimgrey;
	  font-family: Georgia;
	  border: none;
	  color: white;
	  padding: 15px 32px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 20px;
	  margin: 4px 2px;
	  cursor: pointer;
	}
     */
    table{
        margin-left:auto;
        margin-right: auto;
    }
    
</style>