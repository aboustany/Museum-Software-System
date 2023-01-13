<!-- Employee weekly schedule modifier for owner Page of our website -->

<template>
    <div style="text-align:center" id="EmployeeScheduleForOwner">
        <a @click.prevent="goBack" class="button">Back</a>
      <h1 style="text-align:center">Employee weekly schedule planner</h1>
      
      
      <h3  style="text-align:center" class="center">  Please enter the ID of an employee </h3>
      <center>
      <p   class="center">
        <td  class="center">
                <b-input  v-model="newEmployeeSchedule.accountID" placeholder="ID" trim></b-input>
                <a class="button" v-bind:disabled="(!newEmployeeSchedule.accountID)" variant="outline-success"
                @click="getSchedule(newEmployeeSchedule.accountID)">Get Schedule</a>
            </td>
      </p>
    </center>
    <table>
            <tr>
                <th>Account ID </th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
            <tr v-for="schedule in mySchedules">
                <td>{{ schedule.accountID }}</td>
                <td>{{ schedule.startDate }}</td>
                <td>{{ schedule.endDate }}</td>
            </tr>
    </table>

          <span v-if="errorMuseumSystem" style="color:red">Error: {{errorMuseumSystem}} </span>
          
      <h3 style="text-align:center"> Please enter start date </h3>
      <p style="text-align:center">
          <!-- <form action="/action_page.php"> -->
                <label style="text-align:center" for="start date">Start date:</label>
                <input style="text-align:center" type="date" v-model="newEmployeeSchedule.startDate" name="start date">
          <!-- </form> -->
          </p>

      <h3 style="text-align:center"> Please enter end date </h3>
      <p style="text-align:center">
          <!-- <form action="/action_page.php"> -->
              <label  style="text-align:center" for="end date">End date:</label>
                <input style="text-align:center" type="date" v-model="newEmployeeSchedule.endDate" name="end date">
                <a class="button" style="text-align:center" v-bind:disabled="(!newEmployeeSchedule.endDate)" variant="outline-success"
                @click="createSchedule(newEmployeeSchedule.accountID, newEmployeeSchedule.startDate,newEmployeeSchedule.endDate)">Create</a>
          <!-- </form> -->
      </p>
    </div>
  </template>

<script src="./ScheduleMaker.js">
</script>
<style>
     
      #EmployeeScheduleForOwner{
        align-items: center;
		    justify-content: center;
        text-align: center;
        margin: auto;
      }
      
      #EmployeeScheduleForOwner p {
          font-family: Georgia;
          font-size: 18px;
          text-align: left;
          justify-content: center;
            align-items: center;
      }

      #EmployeeScheduleForOwner .button{
        font-size: 15px;
        padding: 7px 10px;
      }
    
      .center {
        margin-left: auto;
        margin-right: auto;
      }  
</style>