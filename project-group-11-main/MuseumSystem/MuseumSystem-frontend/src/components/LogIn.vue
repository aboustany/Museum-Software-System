<template>
  <div id="LogIn" class="center">
    <h1>
        Welcome to Museum Management System 321!
    </h1>
    <h3>
       Please Log-In Below
    </h3>
    <table class="center">
        <tr>
            <td class="center">
                Username:
            </td>
            <td class="center">
                <b-input v-model="userName" placeholder="Enter Username" trim></b-input>
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
        <tr>
            <td class="center">
                ID:
            </td>
            <td class="center">
                <b-input v-model="accountID" placeholder="Enter ID" trim></b-input>
            </td>
        </tr>
    </table>
    <table class="center">
        <tr>
            <td>
                <br>
                <label>LOG-IN AS:</label>
                    <select v-model="userType">
                    <option value="Owner">Owner</option>
                    <option value="Visitor">Visitor</option>
                    <option value="Employee">Employee</option>
                </select>
                <!-- Button is disabled untill user enters text. Clicking triggers login-->
                <button class="button" v-bind:disabled="(!userName || !password)" variant="outline-success"
                @click="LogInUser(userType,userName,password, accountID)" >Login</button>
            </td>
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
export default {
name: "LogIn",
data () {
return {
  users: [],
  accountID:"",
  userName: "",
  password: "",
  userType: "",
  errorMessage: ""
}
},
methods: {
//use AXIOS.post to create a user 
//But doesn't want to connect to server
resetInfo: function(userName,password,userType){
    this.userName='';
    this.password='';
    this.userType='';
    this.accountID='';
},
LogInUser(userType,userName,password, accountID){
   if(userType=="Owner"){
    if(this.password == "MKsMuseum" && this.userName == "admin" && this.accountID == "1"){
        localStorage.setItem("username", this.userName);
        //reset variables
        this.resetInfo(this.userName,this.password,this.userType);
        //direct to next page depending on owner/employee/visitor
        this.$router.push('ownerportal');
    }else{
    AXIOS.get("/owner/".concat(accountID), {}).then(response =>{
      //check if credentials match
        if (response.data.password == this.password &&
            response.data.accountID == this.accountID&&
            response.data.userName == this.userName
        ) {
          //save information
          console.log("Successfully Logged in as ".concat(userType))
          window.sessionStorage.setItem('userType', 'Owner');
          window.sessionStorage.setItem('password', this.password);
          window.sessionStorage.setItem('userName', this.userName);
          localStorage.setItem("username", this.userName);
          //reset variables
          this.resetInfo(this.userName,this.password,this.userType);
          //direct to next page depending on owner/employee/visitor
          this.$router.push('ownerportal');
        }
        else{
          this.errorMessage = "Credentials are Invalid. Please Try Again";
        }
        }).catch(
            error =>{
                this.errorMessage = "Error, If this error persists contact 604-321-321";
               // this.errorMessage=error.response.data
            }
        );
    }
   }
   //check if user is employee
   else if(userType=="Employee"){
    AXIOS.get("/employee/".concat(accountID), {}).then(response =>{
      //check if credentials match
        if (response.data.password == this.password &&
            response.data.accountID == this.accountID&&
            response.data.userName == this.userName
        ) {
          //save information
          console.log("Successfully Logged in as ".concat(userType))
          window.sessionStorage.setItem('userType', 'Employee');
          window.sessionStorage.setItem('password', this.password);
          window.sessionStorage.setItem('userName', this.userName);
          localStorage.setItem("username", this.userName);
          ///reset variables
          this.resetInfo(this.userName,this.password,this.userType);
          //direct to next page depending on owner/employee/visitor
          this.$router.push('employeeportal');
        }
        else{
          this.errorMessage = "Credentials are Invalid. Please Try Again";
        }
        }).catch(
            error =>{
                this.errorMessage = "Error, If this error persists contact 604-321-321";
               // this.errorMessage=error.response.data
            }
        );
   }
   //check if user is visitor
   else{
    AXIOS.get("/visitor/".concat(accountID), {}).then(response =>{
      //check if credentials match
        if (response.data.password == this.password &&
            response.data.accountID == this.accountID&&
            response.data.userName == this.userName
        ) {
          console.log("Successfully Logged in as".concat(userType))
          //save information
          window.sessionStorage.setItem('userType', 'Visitor');
          window.sessionStorage.setItem('password', this.password);
          window.sessionStorage.setItem('userName', this.userName);
          localStorage.setItem("username", this.userName);
          //reset variables
          this.resetInfo(this.userName,this.password,this.userType);
          //direct to next page depending on owner/employee/visitor
          this.$router.push('art');
        }
        else{
            this.errorMessage = "Credentials are Invalid. Please Try Again";
        }
        }).catch(
            error =>{
                this.errorMessage = "Error, If this error persists contact 604-321-321";
               // this.errorMessage=error.response.data
            }
        );
   }
}
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

/* h2 {
    color:tomato;
    text-align: center;
}

p {
    font-family: Georgia;
    font-size: 20px;

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

.center .button{
    padding: 7px 10px;
    font-size: 18px;
}

.center {
margin-left: auto;
margin-right: auto;
}
</style>