<template>
    <div>
        <h2>Notification</h2>
      <table>
        <thead>
          <tr>
            <th>Vendor ID</th>
            <th>Company Name</th>
            <th>Verify Status</th>
            <th>Product File</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-if="isEmpty">
    <td colspan="7">
      <div style="text-align: center">
        <img id="success" src="https://i.pinimg.com/originals/af/55/87/af5587ad1fa5ca160048939e6cae5fa2.gif" alt="Avatar" style="height:300px; width:430px;" class="avatar">
        <h2>Sorry No Orders Found</h2>
      </div>
    </td>
  </tr>
          <tr  v-else v-for="vendor in vendors" :key="vendor.vendorID">
            <td>{{ vendor.vendorID }}</td>
            <td>{{ vendor.companyName }}</td>
            <td>{{ vendor.VerifyStatus }}</td>
            <td>
          <a href="#" @click.prevent="downloadPdf(getDownloadUrl(vendor.productFile))">Download</a>
              </td>
        <td  v-if="vendor.VerifyStatus === 'NotVerified'"  >
  <button v-on:click="sendMail(vendor.vendorID,true)" class="btn" style="background-color: green;" id="new">Approve</button>
  <button v-on:click="sendMail(vendor.vendorID,false)" class="btn"  id="new">Cancel</button>
</td>
<td v-else> <button  class="btn" >Verfied</button></td>
          </tr>
        </tbody>
      </table>
      <div  v-if="showPopup" class="pop"  id="pop-up" >
      <div class="popup-content">
        <p><b>Send Mail</b></p>
        <div id="message-box">
          <label for="name">To:</label>
        <input type="text" id="name" v-model="this.vendor" readonly  required>
          <textarea id="message" name="message-input"  v-model="mailtext"  placeholder="Enter mail message"></textarea>
          
        </div>
        <button v-on:click="approveRequest()"   class="close" style="background-color: green;">send</button>
        <button v-on:click="showPopup=false"  class="close">close</button>
        <p style="color:green" id="status"></p>
      </div>
    </div>
    </div>
  </template>
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        vendors: [],
        showPopup:false,
        mailtext:'',
        vendor:'',
        methodCall:''

      };
    },
    created() {
      this.fetchVendors();
    },
    methods: {
      fetchVendors() {
        axios.get('/api/VendorsController')
          .then(response => {
            this.vendors = response.data;
            console.log(response.data)
          })
          .catch(error => {
            console.log(error);
          });
      },
      getDownloadUrl(productFile) {
  if (productFile) {
    const blob = new Blob([productFile], { type: 'application/pdf' });
    console.log(blob)
    const url = URL.createObjectURL(blob);
    return url;
  }
},
downloadPdf(url) {
  const link = document.createElement('a');
  link.href = url;
  link.download = 'product.pdf';
  link.dispatchEvent(new MouseEvent('click'));
}
,
sendMail(vendor,data)
{ this.vendor=vendor;
  this.showPopup=true;
  if(data)
  {
  this.methodCall=data
  this.mailtext="Dear Vendor\n\n\nWe are Happy to inform you that your application for vendor has been approved!\n\n"+
  "Congratulations and welcome aboard!To access your account,please use the your login credentials\nPlease keep this information safe and secure, as it will be used to access your vendor account.\n"+
  "Once you have logged in, you will be able to view and manage your vendor profile, update your product listings, and view any pending orders.\n"+
  "As a valued vendor, we are excited to work with you and help you grow your business.\n"+
  "We look forward to seeing the unique products and services that you will bring to our platform and our customers.If you have any questions or concerns, please do not hesitate to contact our support team.\n\nThey are always ready to assist you.Thank you for choosing to be a part of our vendor community. We wish you all the success and growth."+
 "\n\nBest regards,\n[Saran.R]Admin,\n [Skart]";
  }
  if(data==false){
    this.methodCall=data
    this.mailtext="Dear [Vendor] \nI regret to inform you that we have decided to cancel your request as your product is not eligible for our category list. After careful consideration, we have determined that your product does not meet our criteria for inclusion in our list of eligible items.\n\n\nWe understand that this may be disappointing news for you, but please understand that we have strict guidelines and standards that we must adhere to in order to maintain the quality of our offerings. We appreciate your interest in our platform and hope that you will continue to explore other options that may better suit your product.\nThank you for your understanding.\n\nBest regards\n[Admin Skart]";
  }
}
,callrequest(){
  if(this.methodCall===true)
  this.approveRequest()
  if(this.methodCall===false)
  this.cancelRequest()

},approveRequest(){
  const url='/api/VendorsController'
  const data={
    mailText:this.mailtext,
    vendorId:this.vendor
   }
   document.getElementById("status").innerHTML="Please Wait"
   axios.put(url,data, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
            alert(response.data.message)
            this.showPopup=false;
        })   
      },
      cancelRequest()
      {
        
        const data={
    mailText:this.mailtext,
    vendorId:this.vendor
   }
        document.getElementById("status").innerHTML="Please Wait"
        const url = `api/VendorsConsroller?vendorId=${this.vendor}`;
  axios.delete(url,data).then((response) => {
    console.log(response.data);
    alert(response.data.message);
    this.showPopup=false;
  
  }).catch((error) => {
    console.log(error.response.data);
  });   

      }
    }
    }
  
  </script>
  
<style scoped>
table{
  width:90%;
  margin:auto;
  border-collapse: collapse;
  background-color: white;
  text-align: center;
 
}
th{
  background-color: #b3f0ff;
  margin:auto;
  padding: 10px 3px;
}
td{

  padding: 20px;
}


#message-box {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #fff;
        box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
        font-size: 16px;
        line-height: 1.5;
        height: 150px;
        width:230;
        resize: vertical;
      }
      #message{
        height:90%;
        width:100%;
      }
      input[type="text"]:focus,
textarea:focus,
input[type="number"]:focus {
  outline: none;
  border-color: #1a202c;
}
#pop-up{
  width:50%;
  height: 50%;
}
</style>
