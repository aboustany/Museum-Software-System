<!-- Home Page of our website -->

<template>
  <div id="WelcomePage">
    <h1>Welcome to the Montreal Museum website!</h1>
	<br>
    <div class="accountnav">
      <a href="http://127.0.0.1:8087/#/LogIn" class="button">Log In</a>
      <a href="http://127.0.0.1:8087/#/CreateAccount" class="button">Create Account</a>
    </div>
    <p>
	</p>
  </div>
</template>




<style>
	#WelcomePage{
		position: relative; 
		height: 100vh;
		width: 100%;
		align-items: center;
		justify-content: center;
	}
/* 	
	body {
		background-color: floralwhite;
	} */

	#WelcomePage h1 {
		color: tomato;
		text-align: center;
		font-family: Georgia;
		font-size: 35px;
		text-shadow: 2px 2px 5px lightgrey;
		margin-top: 23.5px;
	}

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
	} */

	/* p {
		font-family: Georgia;
		font-size: 20px;
	}	 */
	
	.accountnav .button {
	  background-color: rgba(255, 99, 71, 0.6);
	  font-family: Georgia;
	  border: none;
	  color: black;
	  padding: 10px 20px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 25px;
	  margin: 4px 2px;
	  cursor: pointer;
	  border-radius: 15px;
	  transition:0.4s;
	}
	.accountnav .button:hover{
		background-color: rgb(255, 99, 71);
		color: black;
		opacity: 1;
	}
	.accountnav a {
		margin-right: 15px;
		margin-left: 15px;
	}
	.accountnav{
		text-align: center;
		align-items: center;
		justify-content: center;
	}
	a:hover{
		text-decoration: none;
	}




</style>
<script>
</script>