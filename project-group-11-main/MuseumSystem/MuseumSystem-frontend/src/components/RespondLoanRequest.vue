<template>

    <div id ="RespondLoanRequestPage">
        <a @click.prevent="goBack" class="button">Back</a>

        <div class="page">


            <h3>
                Please <u>Approve</u> or <u>Deny</u> the following Artwork Loan Requests.
            </h3>

            <div class="PageBody">

                <table>
                    <tr>
                        <th>Visitor ID</th>
                        <th>Artwork ID</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th> Status </th>
                    </tr>
                    <tr v-for="artworkLoan in artworkLoans">
                        <td>{{ artworkLoan.visitorId }}</td>
                        <td>{{ artworkLoan.artworkId }}</td>
                        <td>{{ artworkLoan.startDate }}</td>
                        <td>{{ artworkLoan.endDate }}</td>

                        <td> 
                            <!-- also want it to print "Artowork Approved" -->
                            <button @click="approveLoan(artworkLoan.visitorId, artworkLoan.artworkId)"> Approve </button>

                             <!-- also want it to print "Artwork Deleted" -->
                            <button @click="deleteArtworkLoan(artworkLoan.visitorId, artworkLoan.artworkId)"> Deny </button>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <h3>Approved Loans</h3>
                <table>
                    <tr>
                        <th>Visitor ID </th>
                        <th>Artwork ID</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                    </tr>
                    <tr v-for="artworkLoan in approvedLoans">
                        <td>{{ artworkLoan.visitorId }}</td>
                        <td>{{ artworkLoan.artworkId }}</td>
                        <td>{{ artworkLoan.startDate }}</td>
                        <td>{{ artworkLoan.endDate }}</td>
                    </tr>
                </table>

                
            </div>



        </div>



    </div>
</template>



<style>

    /* body {
		background-color: FloralWhite;
	} */

    /* h1 {
		color: tomato;
		text-align: center;
		font-family: Georgia;
		font-size: 40px;
		text-shadow: 2px 2px 5px lightgrey;
		margin-top: 25px;
	} */
	
	/* h2 {
		color: white;
		text-align: center;
	} */

    /* h3 {
		color: tomato;
		text-align: center;
		font-family: Georgia;
		font-size: 20px;
		text-shadow: 2px 2px 5px lightgrey;
        margin-top: 20px;
	}
	 */
	/* p {
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
    #RespondLoanRequestPage{
        align-items: center;
		justify-content: center;
    	text-align: center;

    }

    #RespondLoanRequestPage .button{
        font-size: 15px;
        padding: 7px 10px;
      }

    table{
        margin-left:auto;
        margin-right: auto;
    }

   

    html{
        height:100%;
    }

   

</style>


<script src="./request.js">

</script>