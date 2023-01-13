import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
function EmployeeDto (username, password) {
    this.userName = username;
    this.password = password;
  }
  export default {
    name: 'museumsystem',
    data () {
      return {
        employeeList: [],
        parentMessage: "EmployeeInstance:",
        newEmployee: {
          userName:'',
          password:''
        },
        errorEmployee: '',
        response: []
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
  }, methods: {
    goBack() {
      window.history.back();
    }, createEmployee: function (userName, password) {
    AXIOS.post('/employee', {userName, password}, {})
      .then(response => {
      // JSON responses are automatically parsed.
        console.log("OBJECT[] " + JSON.stringify(response.data))
        this.employeeList.push(response.data)
        this.errorEmployee = ''
        this.newEmployee = ''
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
        this.errorEmployee = errorMsg
      })
  }, getAllEmployees: function(){
    AXIOS.get('/employee')
    .then(response => {
      // JSON responses are automatically parsed.
      console.log("GOT DATA")
      this.employeeList = response.data
      console.log("Employees[] " + JSON.stringify(this.employeeList))
      console.log("Error " + this.errorEmployee)
    })
    .catch(e => {
      console.log("NO DATA")
      this.errorEmployee = e
      console.log("Error" + this.errorEmployee)
    })
  }, deleteEmployee: function(id){
    AXIOS.delete('/employee/'.concat(id), {}, {})
    .then(response => {
      // JSON responses are automatically parsed.
      console.log("GOT DATA")
      console.log("Employees[] " + JSON.stringify(this.employeeList))
      console.log("Error " + this.errorEmployee)
    })
    .catch(e => {
      console.log("NO DATA")
      this.errorEmployee = e
      console.log("Error" + this.errorEmployee)
    })
  }
  }
  }
