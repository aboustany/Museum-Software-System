import axios from 'axios'
 var config = require('../../config')

 var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
 var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

 var AXIOS = axios.create({
   baseURL: backendUrl,
   headers: { 'Access-Control-Allow-Origin': frontendUrl }
 })


// function ArtworkLoanDto (id, visitorId, artworkId, start, end){
//     this.id = id
//     this.visitorId = visitorId
//     this.artworkId = artworkId
//     this.startDate = start
//     this.endDate = end
//     this.status = ''
// }


export default {
    name: 'respondloanrequest',
    data(){
        return{
            artworkLoans: [],
            approvedLoans:[],

            Loan: {
              visitorId:'',
              artworkId:'',
              startDate:'',
              endDate:'',
              isApproved:''
            },
            response: [],

            errorLoan: ''
        }
    },

    created:
    function () {

    },mounted() {
      	this.getData();
    	},
    methods: {
      goBack() {
      window.history.back();
    },getData: function () {
			this.artworkLoans = JSON.parse(localStorage.getItem("artworkLoanList"));
      if(JSON.parse(localStorage.getItem("approvedLoans")) != null) {
        this.approvedLoans = JSON.parse(localStorage.getItem("approvedLoans"));
      }
			console.log(JSON.stringify(this.artworkLoans));
      for(const i of this.artworkLoans){
        console.log(i.artworkId);
      }
	},
        approveLoan: function(visitorId, artworkId){
            console.log(JSON.stringify(this.artworkLoans));
            var i = this.artworkLoans.length;
            while(i--){
              if(this.artworkLoans[i].visitorId == visitorId && this.artworkLoans[i].artworkId == artworkId){
                this.approvedLoans.push(this.artworkLoans[i]);
                this.artworkLoans.splice(i, 1);
              }
            }
            localStorage.setItem("artworkLoanList", JSON.stringify(this.artworkLoans));
            localStorage.setItem("approvedLoans", JSON.stringify(this.approvedLoans));
        },

        deleteArtworkLoan: function(visitorId, artworkId){
          console.log(JSON.stringify(this.artworkLoans));
          var i = this.artworkLoans.length;
          while(i--){
            if(this.artworkLoans[i].visitorId == visitorId && this.artworkLoans[i].artworkId == artworkId){
              this.artworkLoans.splice(i, 1);
            }
          }
          localStorage.setItem("artworkLoanList", JSON.stringify(this.artworkLoans));
        }
    }
}
