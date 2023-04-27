<template>
      <div >
    <h1>My Customers</h1>
    <table style="background-color:white;text-align: center;">
      <thead>
       <tr>
          <th>RegID</th>
          <th>Name</th>
          <th>Age</th>
          <th>Gmail</th>
          <th>Address</th>
          </tr>
      </thead>
 <tbody>
        <tr v-for="customer in customers" :key="customer.Regno">
          <td><b>{{ customer.Regno}}</b></td>
          <td><b>{{ customer.name }}</b></td>
          <td><b>{{ customer.age }}</b></td>
          <td><b>{{ customer.mail}}</b></td>
          <td><b>{{ customer.address}}</b></td>
       </tr>

     <td colspan="7"><router-link to="/adminhome">
        <button id="new" class="btn">Back</button>
      </router-link></td> 
      </tbody>
    </table>
  </div>
</template>
<script>
import axios from 'axios'
  export default {
    name: 'cutomers-list',
    data() {
      return {
         userData:'',
         customers: [],
         vendors: [],
         statusmessage:'',
         isEmpty:false
          
      };
    },
    created()
    {

      axios.get('api/VendorController').then((response) => {
      this.vendors = response.data;
      if(response.data.length==0)
    this.isEmpty=true;

    }
      )
      axios.get('api/UserController?').then((response) => {
      this.customers = response.data;
      console.log(response.data)
      if(response.data.length==0)
      this.isEmpty=true;

    }
      )
}
}
</script>
<style scoped>
table{
    width:90%;
    margin:auto;
    border-collapse: collapse;
   
}
th{
    background-color: #b3f0ff;
    margin:auto;
    padding: 10px 3px;
}
td{
    padding: 15px;
}

</style>
