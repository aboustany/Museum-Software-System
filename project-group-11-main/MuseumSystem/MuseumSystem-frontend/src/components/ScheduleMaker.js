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
      this.getData();
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
  }, createSchedule: function (accountId, startDate, endDate) {
    var scheduleList;
    if(localStorage.getItem("employeeSchedules") == null){
      scheduleList = [];
    } else{
      scheduleList = JSON.parse(localStorage.getItem("employeeSchedules"));
    }
    console.log("GOT: " + JSON.stringify(scheduleList));
    const schedule = {
      accountID:'',
      startDate:'',
      endDate:''
    };
    schedule.accountID = accountId;
    schedule.startDate = startDate;
    schedule.endDate = endDate;
    scheduleList.push(schedule);
    console.log("schedule: " + JSON.stringify(schedule));
    console.log("scheduleList: " + JSON.stringify(scheduleList));
    localStorage.setItem("employeeSchedules", JSON.stringify(scheduleList));


    // AXIOS.post('/artwork_loan', {visitorId, artworkId, startDate, endDate, isApproved}, {})
    // .then(response => {
    // // JSON responses are automatically parsed.
    //   console.log("OBJECT[] " + JSON.stringify(response.data))
    //
    //   this.artworkLoanList.push(response.data)
    //   this.newArtworkLoan = ''
    //   this.errorArtworkLoan = ''
    // })
    // .catch(e => {
    //   var errorMsg = e.response.data.message
    //   console.log(errorMsg)
    //   this.errorArtworkLoan = errorMsg
    // })
}
  }
  }
