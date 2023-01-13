<template>
<div id="setTime">
	<a @click.prevent="goBack" class="button">Back</a>
	<h3> Please select an opening hour:</h3>
	<input type="time" v-model="newMuseumSystem.openingHour" id="openingHour" name="openingHour">
	<br><br>
	<h3> Please select a closing hour:</h3>
	<input type="time" v-model="newMuseumSystem.closingHour" placeholder="Closing Hour" id="closingHour" name="closingHour">
	
	<br><br>
  	
  	<h3> Please select a ticket price:</h3>
  	<input type="number" v-model="newMuseumSystem.visitorFee" placeholder="Visitor Fee" id="PassPrice" name="1DayPassPrice">
  	
  	<br><br>
  	<a class="button" @click="createMuseumSystem(newMuseumSystem.visitorFee, newMuseumSystem.openingHour, newMuseumSystem.closingHour)">Save Changes</a>
  		
</div>
</template>
<style>
#setTime .button{
        font-size: 15px;
        padding: 7px 10px;
      }

	  #setTime{
		align-items: center;
		justify-content: center;
    	text-align: center;
	  }
</style>
<script src="./OwnerSetParam.js">
</script>