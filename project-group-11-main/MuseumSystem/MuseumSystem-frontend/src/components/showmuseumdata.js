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
        response: [],
        openingHour:'10:10',
        closingHour:'22:10',
        visitorFee:'25'
      }
    },created: function () {
    
    
  }, methods: {
    createMuseumSystem: function (visitorFee, openingHour, closingHour) {
    this.visitorFee = visitorFee;
    this.openingHour = openingHour;
    this.closingHour = closingHour;
  }
  }
  }