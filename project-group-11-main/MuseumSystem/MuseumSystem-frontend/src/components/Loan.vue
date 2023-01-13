<template>
    <div align="center" id="loan">
        <h1> Submit Loan Request </h1>
                <h3>Artwork ID</h3>
                <input type="text" id="art-id" v-model="newArtworkLoan.artworkId" placeholder="Enter Artwork ID">
                <h3>Visitor ID</h3>
                <input type="text" id="visitor-id" v-model="newArtworkLoan.visitorId" placeholder="Enter Visitor ID">
				<br><br>
		<h3 id="instruction"> Please enter start date </h3>
	
		<p>
		<!-- <form action="/action_page.php"> -->
  			<label>Start date:</label>
  			<input type="date" v-model="newArtworkLoan.startDate" id="start-date" name="start date">
		<!-- </form> -->
 	 
		</p>
	
	
		<h3 id="instruction"> Please enter end date </h3>
	
		<p>
		<!-- <form action="/action_page.php"> -->
  			<label>End date:</label>
  			<input type="date" v-model="newArtworkLoan.endDate" id="end-date" name="end date">
		<!-- </form> -->
 	 
		</p>

        <b-button v-bind:disabled="!newArtworkLoan.artworkId" @click="createArtworkLoan(newArtworkLoan.visitorId, newArtworkLoan.artworkId, newArtworkLoan.startDate, newArtworkLoan.endDate, null)"> Submit </b-button>

    </div>
</template>

<script src="./LoanTest.js">

</script>

<style>

    
	#loan #instruction{
		color: tomato;
	}

	#loan h3 {
		color: black;
		text-align: center;
		font-family: Georgia;
		font-size: 20px;
		text-shadow: 2px 2px 5px lightgrey;
		margin-top: 20px;
	}
</style>