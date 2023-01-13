<template>
    <div id="artworkBody">
        <h1>Artworks</h1>
        <button @click="getAllArtworks()">Get Artworks</button>

        <table>
            <tr>
                <div v-for="item in artworkList">
                    <tr v-for="(a, index) in item">
                        <td>Title:{{a.name}}</td>
                        <img :src="a.imageURL" height="30" width="120">
                        <td>ID :{{a.artworkID}}</td>
  	        <!-- {{ parentMessage }} - {{e.accountID}} / {{ e.userName}} / {{e.password}} -->
                    </tr>
	            </div>
            </tr>
        
        </table>
          <span v-if="errorArtwork" style="color:red">Error: {{errorArtwork}} </span>
        <hr>
    </div>

    
    
    
</template>
<style>
    * {
        box-sizing: border-box;
    }

    body {
        margin: 0;
        font-family: Arial;
        background-color: floralwhite;
    }

    h2 {
        color: #000000;
    }

    /* h3{
        margin-top: 25px;
    } */

    table {
        margin-left: auto;
        margin-right: auto;
        text-align: center;
    }

/* The grid: Four equal columns that floats next to each other */
    .column {
        float: left;
        width: 25%;
        padding: 10px;
    }

/* Style the images inside the grid */
    .column img {
        opacity: 0.8; 
        cursor: pointer; 
    }

    .column img:hover {
        opacity: 1;
    }

/* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }

/* The expanding image container */
    .container {
        position: relative;
        display: none;
    }

/* Expanding image text */
    #imgtext {
        position: absolute;
        bottom: 15px;
        left: 15px;
        color: rgb(0, 0, 0);
        font-size: 20px;
    }

/* Closable button inside the expanded image */
    .closebtn {
        position: absolute;
        top: 10px;
        right: 15px;
        color: rgb(0, 0, 0);
        font-size: 35px;
        cursor: pointer;
    }

    #artworkBody button{
        background-color: rgba(255, 99, 71, 0.6);
	  font-family: Georgia;
	  border: none;
	  color: black;
	  padding: 7px 8px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 18px;
	  margin: 4px 2px;
	  cursor: pointer;
	  border-radius: 15px;
	  transition:0.4s;
    }

    #artworkBody button:hover{
        background-color: rgba(255, 99, 71);
    }


    #artworkBody{
        align-items: center;
		justify-content: center;
        text-align: center;
    }

</style>
<script src ="./artworkDisplay.js">
</script>