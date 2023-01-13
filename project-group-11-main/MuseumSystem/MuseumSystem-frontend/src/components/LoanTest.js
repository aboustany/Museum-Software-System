import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


  export default {
    name: 'artworkloan',
    data () {
      return {
        newArtworkLoan: {
            visitorId:'',
            artworkId:'',
            startDate:'',
            endDate:'',
            isApproved:''
        },
        artworkLoanList: [],
        parentMessage: "ArtworkLoanInstance:",
        errorArtworkLoan: '',
        response: []
      }
    },created: function () {
  }, methods: {
    createArtworkLoan: function (visitorId, artworkId, startDate, endDate, isApproved) {
      isApproved = false;
      var artworkLoanList;
      if(localStorage.getItem("artworkLoanList") == null){
        artworkLoanList = [];
      } else{
        artworkLoanList = JSON.parse(localStorage.getItem("artworkLoanList"));
      }
      console.log("GOT: " + JSON.stringify(artworkLoanList));
      const loan = {
        visitorId:'',
        artworkId:'',
        startDate:'',
        endDate:'',
        isApproved:''
      };
      loan.visitorId = visitorId;
      loan.artworkId = artworkId;
      loan.startDate = startDate;
      loan.endDate = endDate;
      loan.isApproved = isApproved;
      artworkLoanList.push(loan);
      console.log("LOAN: " + JSON.stringify(loan));
      console.log("LOANLIST: " + JSON.stringify(artworkLoanList));
      localStorage.setItem("artworkLoanList", JSON.stringify(artworkLoanList));
      this.$router.push('loanreceived');

      // AXIOS.post('/artwork_loan', {visitorId, artworkId, startDate, endDate, isApproved}, {})
      // .then(response => {
      // // JSON responses are automatically parsed.
      //   console.log("OBJECT[] " + JSON.stringify(response.data))
      //
      //   this.artworkLoanList.push(response.data)
      //   this.newArtworkLoan = ''
      //   this.errorArtworkLoan = ''
      // })
      // .catch(e => {
      //   var errorMsg = e.response.data.message
      //   console.log(errorMsg)
      //   this.errorArtworkLoan = errorMsg
      // })
  }, deleteArtworkLoan: function(id){
    AXIOS.delete('/artwork_loan/'.concat(id), {}, {})
    .then(response => {
      // JSON responses are automatically parsed.
      console.log("GOT DATA")
      console.log("Artwork Loans[] " + JSON.stringify(this.artworkList))
      console.log("Error" + this.errorArtworkLoan)
    })
    .catch(e => {
      console.log("NO DATA")
      this.errorArtworkLoan = e
      console.log("Error" + this.errorArtworkLoan)
    })
  }
  }
  }
