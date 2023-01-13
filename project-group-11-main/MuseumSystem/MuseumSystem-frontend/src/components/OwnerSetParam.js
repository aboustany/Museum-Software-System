import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



export default {
    name: 'museumsystem',
    data () {
      return {
       EntryPassesList: [{"test":"sami"}],
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
    },created: function () {


  }, methods: {
    goBack() {
    window.history.back();
    },
    createMuseumSystem: function (visitorFee, openingHour, closingHour) {
    this.visitorFee = visitorFee;
    this.openingHour = openingHour;
    this.closingHour = closingHour;
    localStorage.setItem("openingHour", openingHour);
    localStorage.setItem("closingHour", closingHour);
    localStorage.setItem("visitorFee", visitorFee);
    console.log("Opening Hour: " + this.openingHour);
    console.log("Opening Hour: " + this.closingHour);
    console.log("Opening Hour: " + this.visitorFee);
  }
  }
  }
