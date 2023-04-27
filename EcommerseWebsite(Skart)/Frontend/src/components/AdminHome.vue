<template>
    <header>
<nav>
<ul>
<li><router-link to="" class="router" @click="showProfile" ><b>MyProfile</b> </router-link></li>
<li><router-link to="/view" class="router"><b>ViewVendors</b> </router-link></li>
<li><router-link to="/view" class="router"><b>ViewCustomers</b> </router-link></li>
<li><router-link to="/notification" class="router"><b>Admin Notification</b> </router-link></li>
<li><router-link to="/userHome" class="router"><b>User Console</b> </router-link></li>
<li><router-link to="/" class="router"><b>Logout</b> </router-link></li>
</ul> 
</nav>
<h1 style="margin-left: 450px; font-family: Syncopate, sans-serif"><b>SKART</b></h1>
</header>
<br><br><br>
    <div>
    
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'user-home',
    data() {
      return {
        products: [],
      };
    },
    created() {
      axios.get('api/ProductController').then((response) => {
        this.products = response.data;
        console.log(this.products);
      });
    },
    methods:{
        popup()
        {
            alert('working');
        }
    }
  };
  </script>
  
  <style scoped>
  </style>
  