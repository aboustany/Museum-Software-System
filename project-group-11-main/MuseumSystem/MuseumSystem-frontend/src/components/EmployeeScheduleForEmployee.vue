<!-- Employee weekly schedule modifier for employee Page of our website -->

<template>
    <div id="EmployeeScheduleForEmployee">
        <a @click.prevent="goBack" class="button">Back</a>
      <h1>My Schedule</h1>
      <input type="number" id="emp-id" v-model="newEmployeeSchedule.accountID" placeholder="Enter Employee ID">
      <b-button v-bind:disabled="!newEmployeeSchedule.accountID" @click="getSchedule(newEmployeeSchedule.accountID)"> Get Schedule </b-button>
      <table>
            <tr>
                <th>Employee ID </th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
            <tr v-for="schedule in mySchedules">
                <td>{{ schedule.accountID }}</td>
                <td>{{ schedule.startDate }}</td>
                <td>{{ schedule.endDate }}</td>
            </tr>
        </table>
      
    </div>
  </template>

<script src='./EmployeeScheduleParams.js'>
</script>
  
<style>
      body {
          background-color: FloralWhite;
      }

      #EmployeeScheduleForEmployee{
        align-items: center;
		   justify-content: center;
       text-align: center;
      }

      #EmployeeScheduleForEmployee .button{
        font-size: 15px;
        padding: 7px 10px;
      }
      
      /* h1 {
          color: tomato;
          text-align: center;
          font-family: Georgia;
          font-size: 40px;
          text-shadow: 2px 2px 5px lightgrey;
      } */
      
      
      .center {
        margin-left: auto;
        margin-right: auto;
      }
      
</style>