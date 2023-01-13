import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ArtworkDto (name, onDisplay, artist, roomId, isAvailable, rentalFee, imageURL) {
    this.name = name;
    this.onDisplay = onDisplay;
    this.artist = artist;
    this.roomId = roomId;
    this.isAvailable = isAvailable;
    this.rentalFee = rentalFee;
    this.imageURL =imageURL;
  }

  export default {
    name: 'museumsystem',
    data () {
      return {
        artworkList: [],
        parentMessage: "EmployeeInstance:",
        roomList:[],
        errorRoom: '',
        newArtwork: {
          name:'',
          artist:'',
          roomId: '',
          isAvailable: '',
          rentalFee: '',
          onDisplay: '',
          imageURL: ''

        },
        errorArtwork: '',
        response: []
      }
    },created: function () {
    // Initializing persons from backend
    AXIOS.get('/room')
    .then(response => {
      // JSON responses are automatically parsed.
      this.roomList = response.data
    })
    .catch(e => {
      this.errorRoom = e
    }),

    AXIOS.get('/artwork')
    .then(response => {
      // JSON responses are automatically parsed.
      this.artworkList = response.data
    })
    .catch(e => {
      this.errorArtwork = e
    })

  }, methods: {
    
  createArtwork: function (name, artist, rentalFee, imageURL, roomId, isAvailable, onDisplay) {
      console.log("BEFORE");
      console.log("Room number " + roomId);
      if(roomId == "11"){
        onDisplay = false;
      }else{
        console.log("fewqufiewqhbfewbqk")
        onDisplay = true;
      }
      console.log("ID number: ".concat(isAvailable))
      if(isAvailable == "yes"){
        console.log("test1ID")
        isAvailable = true;
      }else{
        console.log("test2ID")
        isAvailable = false;
      }
    AXIOS.post('/artwork', {name, artist, rentalFee, imageURL, roomId, isAvailable, onDisplay}, {})
      .then(response => {
      // JSON responses are automatically parsed.
        console.log("OBJECT[] " + JSON.stringify(response.data))

        this.artworkList.push(response.data)
        this.errorArtwork = ''
        this.newArtwork = ''
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
        this.errorArtwork = errorMsg
      })
  },
   getAllArtworks: function(){
    AXIOS.get('/artwork')
    .then(response => {
      // JSON responses are automatically parsed.
      console.log("GOT DATA")
      this.artworkList = response.data
      console.log("Artworks[] " + JSON.stringify(this.artworkList))
      console.log("Error " + this.errorArtwork)
    })
    .catch(e => {
      console.log("NO DATA")
      this.errorArtwork = e
      console.log("Error" + this.errorArtwork)
    })
  }, 
  deleteArtwork: function(id){

    AXIOS.delete('/artwork/'.concat(id), {}, {})
    .then(response => {
      // JSON responses are automatically parsed.
      console.log("GOT DATA")
      console.log("Artworks[] " + JSON.stringify(this.artworkList))
      console.log("Error " + this.errorArtwork)
    })
    .catch(e => {
      console.log("NO DATA")
      this.errorArtwork = e
      console.log("Error" + this.errorArtwork)
    })
  },
  getAllRooms: function(){
    AXIOS.get('/room')
    .then(response => {
      // JSON responses are automatically parsed.
      console.log("GOT DATA")
   
      console.log(typeof(response.data))
      console.log(response.data)
      this.roomList = response.data
      console.log("Rooms[] " + JSON.stringify(this.roomList))
      console.log( (response.data["rooms"][0]["roomID"]))
      console.log(JSON.stringify(this.roomList))
      console.log("Error " + this.errorRoom)
    })
    .catch(e => {
      console.log("NO DATA")
      this.errorArtwork = e
      console.log("Error" + this.errorRoom)
    })
    
  }
  }
  }
