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
       EmployeeScheduleLists: [],
       mySchedules: [],
        errorMuseumSystem: '',
        newEmployeeSchedule: {
        	startDate:'',
        	endDate:'',
        	accountID:''
        },
        response: [],
        startDate:'',
        endDate:'',
        accountID:''
      }
    },created: function () {
  }, mounted() {
      this.getData();
    },methods: {
      goBack() {
      window.history.back();
    },getSchedule: function (accountID) {
      this.mySchedules = [];
    var i = this.EmployeeScheduleLists.length;
    while(i--){
      if(this.EmployeeScheduleLists[i].accountID == accountID){
        this.mySchedules.push(this.EmployeeScheduleLists[i]);
      }
    }

  },getData: function () {
    if(JSON.parse(localStorage.getItem("employeeSchedules")) != null) {
      this.EmployeeScheduleLists = JSON.parse(localStorage.getItem("employeeSchedules"));
    }
    console.log(JSON.stringify(this.EmployeeScheduleLists));
  }
  }
  }
