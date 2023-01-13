<template>
    <div id="thankyou">
        <h1 v-for="(item, index) in ThankYou">Thank you for your purchase {{username}}!</h1><br>
        <h3 v-for="(item, index) in ThankYou">You will receive a confirmation email shortly that constains your {{quantity}} ticket(s) and a receipt. </h3>
        <h3>We hope to see you soon.</h3>
    </div>

    
    
    
</template>
<style>
    * {
        box-sizing: border-box;
    }

    /* body {
        margin: 0;
        font-family: Arial;
    } */

    /* h2 {
        color: black;
    } */

    table {
        margin-left: auto;
        margin-right: auto;
        text-align: center;
    }

/* The grid: Four equal columns that floats next to each other */
    .column {
        float: left;
        width: 25%;
        padding: 10px;
    }

/* Style the images inside the grid */
    .column img {
        opacity: 0.8; 
        cursor: pointer; 
    }

    .column img:hover {
        opacity: 1;
    }

/* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }

/* The expanding image container */
    .container {
        position: relative;
        display: none;
    }

/* Expanding image text */
    #imgtext {
        position: absolute;
        bottom: 15px;
        left: 15px;
        color: rgb(0, 0, 0);
        font-size: 20px;
    }

/* Closable button inside the expanded image */
    .closebtn {
        position: absolute;
        top: 10px;
        right: 15px;
        color: rgb(0, 0, 0);
        font-size: 35px;
        cursor: pointer;
    }

</style>
<script>
    export default {
    name: 'thankyou',
    data () {
      return {
       ThankYou: [{"test":"karim"}],
        errorMuseumSystem: '',
        response: [],
        quantity:'',
        username:''
      }
    },
  	mounted() {
    	this.getData();
  	}, 
	methods: {
		getData: function () {
			this.quantity = localStorage.getItem("quantity");
            this.username = localStorage.getItem("username");
	}
  }
}
</script>