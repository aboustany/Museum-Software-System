import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function EntryPassDto (price, date) {
    this.price = price;
    this.date = date;
  }

  export default {
    name: 'museumsystem',
    data () {
      return {
       EntryPassesList: [[{"test":"karim"}]],
       price: '',
        newTicket: {
          price:'',
          date:''
        },
        errorTicket: '',
        response: [],
        quantity: '',
        finalPrice:''
      }
    },created: function () {
    // Initializing persons from backend
    AXIOS.get('/employee')
    .then(response => {
      // JSON responses are automatically parsed.
      this.employeeList = response.data
    })
    .catch(e => {
      this.errorPerson = e
    })
  },mounted() {
    	this.getData();
  	}, methods: {
    getData: function () {
			this.price = localStorage.getItem("visitorFee");
			console.log(this.visitorFee);
	}, purchase: function(quantity){
    quantity = document.getElementById("numberOfTickets").value;
    localStorage.setItem("quantity", quantity);
    this.finalPrice = quantity*10;
    this.$router.push('thankyou');
  }
  }
  }
