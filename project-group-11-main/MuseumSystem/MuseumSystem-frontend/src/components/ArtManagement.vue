<template>
    <div id="artworkBody">
        <a @click.prevent="goBack" class="button">Back</a>
        <h3>Create Artwork</h3>
        <table>
            <tr v-for="artwork in artworkList">
                <td>{{ artwork.name }}</td>
                <td>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" v-model="newArtwork.name" placeholder="Artwork Title">
                </td>
                <td class="center">
                    <label for="roomSelection">Choose a Room:</label>
                    <select v-model="newArtwork.roomId" id="room-options" >
                       <option value="1">Big Room 1</option>
                        <option value="2">Big Room 2</option>
                        <option value="3">Big Room 3</option>
                        <option value="4">Big Room 4</option>
                        <option value="5">Big Room 5</option>
                        <option value="6">Small Room 1</option>
                        <option value="7">Small Room 2</option>
                        <option value="8">Small Room 3</option>
                        <option value="9">Small Room 4</option>
                        <option value="10">Small Room 5</option>
                        <option value="11">Storage</option>
                    </select>
                </td>
                <td>
                    <label for="isAvailable">Is this piece available for loan?</label>
                    <select v-model="newArtwork.isAvailable" id="loan-options" >
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                    </select>
                </td>
                <td>
                    <input type="number" v-model="newArtwork.rentalFee" placeholder="Rental Fee">
                </td>
                <td>
                    <input type="text" v-model="newArtwork.imageURL" placeholder="Artwork URL">
                </td>
                <td>
                    <a class="button" v-bind:disabled="!newArtwork.imageURL" @click="createArtwork(newArtwork.name, newArtwork.rentalFee, newArtwork.imageURL, newArtwork.roomId, newArtwork.isAvailable, newArtwork.onDisplay)">Add Artwork</a>
                </td>
                <td>
                    <span v-if="errorArtwork" style="color:red">Error: {{errorArtwork}} </span>
                </td>
            </tr>
    <!-- ... -->
        </table>
        <div id="testBody">

        <h3>Artworks</h3>
        <a id="getButton" class="button" @click="getAllArtworks()">Get Artworks</a>

        <table>
            <tr>
                <th>Artworks</th>
            </tr>
            <tr>
                <div v-for="item in artworkList">
                    <tr v-for="(a, index) in item">
                        <td>Title: {{a.name}}</td>
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
    </div>

    
    
    
</template>
<style>
    * {
        box-sizing: border-box;
    }

    #testBody{
        align-items: center;
		justify-content: center;
        text-align: center;
        margin: auto;
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

    #artworkBody .button{
    font-size: 15px;
    padding: 7px 10px;
  }

</style>
<script src ="./artworkMan.js">
</script>