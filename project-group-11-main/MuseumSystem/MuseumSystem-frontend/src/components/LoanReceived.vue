<template>
    <div id="loanreceived">
        <h1 v-for="(item, index) in LoanRecieved">Thank you for your loan request {{username}}!</h1><br>
        <h3 v-for="(item, index) in LoanRecieved">Once a decision has been made, you will receive information on how to proceed. </h3>
        <h3>We hope to see you soon.</h3>
    </div>

    
    
    
</template>
<style>
    * {
        box-sizing: border-box;
    }

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
    name: 'loanrecieved',
    data () {
      return {
       LoanRecieved: [{"test":"karim"}],
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
            this.username = localStorage.getItem("username");
	}
  }
}
</script>