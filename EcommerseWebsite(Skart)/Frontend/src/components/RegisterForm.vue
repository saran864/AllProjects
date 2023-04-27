<template>
  <div  v-if="showform" class="signup-form" id="register-form" >
      <h2>Sign Up</h2>
      <form  @submit.prevent="register" >
        <img src="https://www.popsci.com/uploads/2020/01/07/WMD5M52LJFBEBIHNEEABHVB6LA.jpg?auto=webp&width=1440&height=864"
          alt="Avatar" class="avatar"> <br>
          <input type="text" v-model="name"  class="form-control" placeholder="Name" required>
          <input type="text"  v-model="age" class="form-control" placeholder="age" required >
          <input type="email" v-model="username" id="email" v-on:blur="checkEmailValidity"  class="form-control" placeholder="Email" required >
          <p id="status">{{ statusmessage }}</p>
          <div class="password-input">
<input id="password" :type="showPassword ? 'text' : 'password'" v-model="password" class="form-control" placeholder="Password" required>
<div class="password-toggle-icon" @click="togglePasswordVisibility">
  <i :class="showPassword ? 'fa fa-eye-slash fa-lg' : 'fa fa-eye fa-lg'"></i>
</div>
</div>
  <input type="text" v-model="address"  class="form-control" placeholder="Address" required >
         
        
          <div v-if="showbutton">
          <button  id="button"  class="btn" type="submit">Verify</button>
          <router-link to="/"  ><button class="btn" id="new" >Back</button></router-link> 
          </div>
          <div v-if="showOtp" >
            <input type="text" v-model="userotp" class="form-control" placeholder="OTP" required>
            <button id="otp-submit" class="btn" type="submit" @click.prevent="sendDetails">Verify OTP</button>
          </div>
          <p id="check"></p>
      </form>
  </div>
  </template>
  
  <script>

import axios from 'axios';
  
  export default {
    name: 'register-form',
    data() {
      return {
        name :'',
        age:'',
        username: '',
        password: '',
        address: '',
        userotp:'',
        role:'user',
        statusmessage:'',
        showform:true,
        showOtp:false,
        showbutton:true,
        showPassword:false,
        submit:false

      };
    },
    methods: {
      togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  },
      validateEmail(email) {
    const regex = /\S+@\S+\.\S+/
    return regex.test(email) && email.endsWith("@gmail.com")
  },
  checkEmailValidity() {
    this.statusmessage = '';
    if (!this.validateEmail(this.username)) {
      document.getElementById("status").style.color = "red"
      this.statusmessage ='Enter a Valid Gamil Address';
      
      
    }else{

      this.validMail();
    }
  },
      register() {
        if(this.submit==true)
        {
        document.getElementById("status").style.color = "green";
        this.statusmessage = 'Please Wait !';
        const url = '/api/UserController';
        const data = {
          username: this.username
        };
        console.log(data);
        axios.post(url,data, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
    if(response.data.status==='Success')
    {
      this.statusmessage = ''; 
            this.showOtp=true;
            this.showbutton=false;
            console.log(response.data);

    }
        })
        .catch(error => {
          console.log(error);
        });
      }},
      sendDetails()
      {
        const data = {
          name :this.name,
          age :this.age,
          username: this.username,
          password: this.password,
          address :this.address,
          role:this.role,
          userotp:this.userotp,

        };
        console.log(data);
        const url = '/api/AuthenticationController';
        axios.post(url,data, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
          if(response.data.status==='Success') {
            document.getElementById("status").style.color = "green";
          this.statusmessage = 'Successfully Registered redirecting...!'; 
          const userdata= {
        name:  this.name,
        username :  this.username,
        role : this.role,
        age:this.age,
        address :this.address
      }
      const userDataString = JSON.stringify(userdata);
      sessionStorage.setItem('userData', userDataString);
          setTimeout(() => {
            this.statusmessage = ''; 
            this.$router.push('/userHome');
            }, 3000); 
          }
          else{
            document.getElementById("status").style.color = "red";
          this.statusmessage = 'Wrong Otp TryAgain...!'; 
          }
      
        })
        },
      validMail(){
        const url = '/api/UserCheck';
        const data = {
          username: this.username
        };
        console.log(data);
        axios.post(url,data, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
          if(response.data.status==='Failure') {
            document.getElementById("status").style.color = "red";
            this.statusmessage = 'Sorry !! this Email Already Registered';
          }
          else{
            this.submit=true;
          }
        })
  
}   }, };
  
  </script>
  
  