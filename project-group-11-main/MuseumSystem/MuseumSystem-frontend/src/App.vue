<template>
  <div id="app">

    <div class="topnav">  
            
            <a id="logo" href="http://127.0.0.1:8087/#/app"><img href="http://127.0.0.1:8087/#/app" src="./assets/home page logo.png" /></a>
            <img id= "headerImg" src="./assets/3-Michelangelo_-_Creation_of_Adam__cropped.jpeg" />

            <table id="header">
                <tr>
                        <th> <a href="http://127.0.0.1:8087/#/app">Home</a></th>
                        <th><a href="http://127.0.0.1:8087/#/loan">Loan</a></th>
                        <th><a href="http://127.0.0.1:8087/#/art">Exhibitions</a></th>
                        <th> <a href="http://127.0.0.1:8087/#/tickets">Tickets</a> </th>
                        <th> <a href="http://127.0.0.1:8087/#/contact">Contact</a> </th>
                        <th> <a href="http://127.0.0.1:8087/#/about">About</a> </th>
                </tr>
            </table>
            <!-- <a id="logo" href="http://127.0.0.1:8087/#/app"><img src="./assets/home page logo.png" /></a>

            <img id= "headerImg" src="./assets/3-Michelangelo_-_Creation_of_Adam__cropped.jpeg" /> -->

        </div>

    <router-view> </router-view>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>

<style>

  body {
		background-color: floralwhite;
	}

  h1 {
		color: tomato;
		text-align: center;
		font-family: Georgia;
		font-size: 40px;
		text-shadow: 2px 2px 5px lightgrey;
		margin-top: 25px;
	}

	h2 {
		color: white;
		text-align: center;
	}
  h3 {
		color: tomato;
		text-align: center;
		font-family: Georgia;
		font-size: 20px;
		text-shadow: 2px 2px 5px lightgrey;
		margin-top: 20px;
	}

  p {
    font-family: Georgia;
    font-size: 20px;
}

  #headerImg{
    width: 30%;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    /* margin-top: 60px; */
  }

  #logo{
    float: left;
    justify-content: left;
    text-align: left;
    padding:0;
  }

  #header{
    float: right;
    justify-content: right;
    text-align: right;
    align-items: right;
    align-self: center;
    margin-right: 25px;
    margin-left: auto;
    width: 45%;
    vertical-align: middle;
    margin: 32px;
  }
  
  img{
    height: 110px;
    width: auto;
  }
/* Add a black background color to the top navigation */
  .topnav {
	  background-color: dimgrey;
	}
	/* Style the links inside the navigation bar */
	.topnav a {
	  font-family: Georgia;
	  float: left;
	  color: #f2f2f2;
	  text-align: center;
	  padding: 10px 12px;
	  text-decoration: none;
	  font-size: 18px;
    border-radius: 12px;
    transition: 0.3s;
	}
	/* Change the color of links on hover */
	.topnav a:hover {
	  background-color: rgba(255, 99, 71, 0.754);
	  color: #f2f2f2;
	}
	/* Add a color to the active/current link */
	.topnav a.active {
	  background-color: tomato;
	  color: white;
	}
	.button {
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
	  transition:0.5s;
	}

	.button:hover{
		background-color: rgb(255, 99, 71);
		color: black;
		opacity: 1;
    text-decoration: none;
	}

</style>