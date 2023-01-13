<template>
  <div id="CreateAccount" class="center">
    <h1>
        Welcome to Museum Management System 321!
    </h1>
    <h3>
       Create Your Visitor Account Below
    </h3>
    <table class="center">
        <tr>
            <td class="center">
                Username:
            </td>
            <td class="center">
                <b-input  v-model="userName" placeholder="Enter Username" trim></b-input>
            </td>
        </tr>
        <tr class="center">
            <td class="center">
                Password:
            </td>
            <td>
                <b-input type="password"  v-model="password" placeholder="Enter Password" trim></b-input>
            </td>
        </tr>
        <tr class="center">
            <td class="center">
                Verify Password:
            </td>
            <td>
                <b-input type="password"  v-model="Verifypassword" placeholder="Enter Password " trim></b-input>
            </td>
            <b-button class="center" variant="outline-success"
                @click="createVisitor(userName,password,Verifypassword)" >Create</b-button>
        </tr>
    </table>
    <p v-if="this.errorMessage" style="color: red">
    {{ this.errorMessage }}
  </p>
</div>
</template>

<script>
//import below to make calls to backend
//////////////////////
import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
baseURL: backendUrl,
headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
//////////////////////
function VisitorDto (userName, password) {
  this.userName = userName;
  this.password = password;
}
export default {
name: "museumsystem",
data () {
return {
  users: [],
  userName: "",
  Verifypassword: "",
  userType: "",
  password: "",
  errorUser: "",
  errorMessage: ""
}
},
methods: {
//use AXIOS.post to create a user 
//But doesn't want to connect to server
createVisitor: function(userName, password,Verifypassword) {
    AXIOS.post('/visitor', {
      userName: userName,
      password: password,
      Verifypassword: Verifypassword,
  }, {})
  .then(response => {
    if(password==Verifypassword){
      console.log("OBJECT[] " + JSON.stringify(response.data))
      this.users.push(response.data)
      this.$router.push("app")
    }
    else{
      this.errorMessage = "Passwords do not match. Please Try Again";
    }
  })
  .catch(error => {
      var errorMsg = e.response.data.message
      console.log(errorMsg)
      this.errorMessage = errorMsg
  })
},
resetInfo: function(userName,password){
    this.userName='';
    this.password='';
    this.userType='';
},
}
}

</script>

<style>
body {
    background-color: FloralWhite;
}

/* h1 {
    color: tomato;
    text-align: center;
    font-family: Georgia;
    font-size: 40px;
    text-shadow: 2px 2px 5px lightgrey;
} */



/* .button {
  background-color: dimgrey;
  font-family: Georgia;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  margin: 4px 2px;
  cursor: pointer;
} */
.center {
margin-left: auto;
margin-right: auto;
}
</style>