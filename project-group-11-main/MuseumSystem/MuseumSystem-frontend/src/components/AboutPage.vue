<template>
	<div id="aboutPage">
	<br>
	<p align = "justify">
		Founded in 2022, the Montreal Museum is the youngest art museum in Canada and a leading museum in North America.
		The outstanding permanent collection includes international art, antiquities from around the world, decorative arts and design, and Quebec and Canadian art.
		The museum's 10 connected rooms may feel sprawling at first, but it's pretty easy to get around once you start, and tours are offered daily in French and English.
	</p>	
	<br>
	<p>
	Opening Hour: 
	</p>
	<div v-for="(item, index) in About">
		{{openingHour}}
	</div>
	<p>
	Closing Hour: 
	</p>
	<div v-for="(item, index) in About">
		{{closingHour}}
	</div>
	<br>
	<p>
	Ticket Price: 
	</p>
	<div v-for="(item, index) in About">
		{{visitorFee}}
	</div>
	</div>
</template>


<script>
function getData() {
        this.openingHour = localStorage.getItem("openingHour");
        this.closingHour = localStorage.getItem("closingHour");
		this.visitorFee = localStorage.getItem("visitorFee");
		console.log("DATA");
		console.log(this.openingHour);
		console.log(this.closingHour);
		console.log(this.visitorFee);

}

export default {
    name: 'museumabout',
    data () {
      return {
       About: [{"test":"karim"}],
        errorMuseumSystem: '',
        newMuseumSystem: {
        	openingHour:'',
        	closingHour:'',
        	visitorFee:''
        },
        response: [],
        openingHour:'',
        closingHour:'',
        visitorFee:''
      }
    },
  	mounted() {
    	this.getData();
  	}, 
	methods: {
		getData: function () {
			this.openingHour = localStorage.getItem("openingHour");
        	this.closingHour = localStorage.getItem("closingHour");
			this.visitorFee = localStorage.getItem("visitorFee");
			console.log("DATA");
			console.log(this.openingHour);
			console.log(this.closingHour);
			console.log(this.visitorFee);
	}
  }
}

</script>


<style>
    body {
		background-color: FloralWhite;
        /* background-image: url("../assets/displayBackground.jpeg"); */
	}
    
	#aboutPage{
		align-items: center;
		justify-content: center;
    	text-align: center;
	}
	#aboutPage p {
		font-family: Georgia;
		font-size: 20px;
		margin-top: 15px;
		margin-right: 20px;
		margin-left: 20px;
		margin-bottom: 10px;
		align-items: center;
		justify-content: center;
		text-align: center;
	}
	
    table{
        margin-left:auto;
        margin-right: auto;
    }
   
</style>



