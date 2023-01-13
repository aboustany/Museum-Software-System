<template>
  <div id="museumsystem">
    <a @click.prevent="goBack" class="button">Back</a>
    <div id="systemBody">
    <h3>Create Employee Acccount</h3>
      <table id="systemTable">
        <tr v-for="employee in employeeList">
            <td>{{ employee.userName }}</td>
            <td>
            </td>
        </tr>
        <tr>
          <td>
            <input type="text" v-model="newEmployee.userName" placeholder="Employee Name">
          </td>
          <td>
            <input type="text" v-model="newEmployee.password" placeholder="Employee Password">
          </td>
          <td>
            <a class="button" v-bind:disabled="!newEmployee.userName" @click="createEmployee(newEmployee.userName, newEmployee.password)">Hire Employee</a>
          </td>
          <td>
            <span v-if="errorEmployee" style="color:red">Error: {{errorEmployee}} </span>
          </td>
        </tr>
    <!-- ... -->
    </table>

    <h3>Employee Management</h3>
    <a class="button" @click="getAllEmployees()">Refresh</a>

      <table id="systemTable">
        <tr>
            <th>Employees</th>
        </tr>
        <tr>
          <div v-for="item in employeeList">
              <tr v-for="(e, index) in item">
                    <td>ID:{{e.accountID}}</td>
                    <td>Username: {{e.userName}}</td>
                    <td>Password: {{e.password}}</td>
                    <td>
                      <a class="button" @click="deleteEmployee(e.accountID)">Fire Employee</a>
                    </td>
  	        <!-- {{ parentMessage }} - {{e.accountID}} / {{ e.userName}} / {{e.password}} -->
              </tr>
	        </div>
        </tr>
        
      </table>
          <span v-if="errorEmployee" style="color:red">Error: {{errorEmployee}} </span>
    <hr>
  </div>
  </div>
</template>
<style>
   #museumsystem {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f2ece8;
    }

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

  #museumsystem .button{
    font-size: 15px;
    padding: 7px 10px;
  }

  #systemBody{
    align-items: center;
		justify-content: center;
    text-align: center;
  }

  #systemTable{
    align-items: center;
		justify-content: center;
    text-align: center;
    border: 1px solid;
    margin: auto;
  }

  

  
</style>
<script src ="./employeemanagement.js">
  
</script>