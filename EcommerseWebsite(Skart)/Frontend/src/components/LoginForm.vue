<template>
  <div class="signup-form" id="login-form" >
    <h2>Login</h2>
    <form v-if="!forgetPassword" @submit.prevent="handleSubmit" @keydown.enter.stop="submitForm">
      <img v-if="check1" id="success" src="https://www.popsci.com/uploads/2020/01/07/WMD5M52LJFBEBIHNEEABHVB6LA.jpg?auto=webp&width=1440&height=864" alt="Avatar" class="avatar">
      <img class="failure" v-if="check2" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIqDoASOMlprgoqUvGFR3OYz_SY960ximmFWz3XvtWI5H_sL4d3d6U01Ij1iW_Il_2DLw&usqp=CAU">     
      <br>
      <input type="text" v-model="username" class="form-control" placeholder="Email" required>
      <br>
      <div class="password-input">
        <input id="password" :type="showPassword ? 'text' : 'password'" v-model="password" class="form-control" placeholder="Password" required>
        <div class="password-toggle-icon" @click="togglePasswordVisibility">
        <i :class="showPassword ? 'fa fa-eye-slash fa-lg' : 'fa fa-eye fa-lg'"></i>
        </div>
        </div>
      <p id="chk">{{ statusmessage }}</p>
      <br>
      <button id="button" class="btn">Login</button>
      <router-link to="/">
        <button id="new" class="btn">Back</button>
      </router-link>
    </form>
    <form  v-else @submit.prevent="requestOTP">
      <img v-if="check1" id="success" src="https://www.popsci.com/uploads/2020/01/07/WMD5M52LJFBEBIHNEEABHVB6LA.jpg?auto=webp&width=1440&height=864" alt="Avatar" class="avatar">
      <div v-if="!isotp">
      <input type="email" v-model="username"  v-on:blur="validMail" class="form-control" placeholder=" Verify your Email" required>
      <p id="status">{{ statusmessage }}</p>
      <input v-if="Response" type="number" v-model="otp" class="form-control" placeholder="Enter 6-digit OTP" minlength="6" maxlength="6" required>
      <button v-if="!Response" id="button" class="btn" >OTP</button>
    </div>
    <div v-else>
      <input type="password" v-model="password"  class="form-control" placeholder=" Enter password" required>
      <input type="password" v-model="confirmPassword"   class="form-control" placeholder=" Confirm password" required>
      <button v-if="Response" id="button" class="btn" @click="verifyOtp()">ChangePassword</button>
      <p v-if="passwordsMatch">Passwords match!</p>
      <p v-else>Passwords do not match.</p>
      <p id="status">{{ statusmessage }}</p>
    </div>
    </form>
    <button v-if="Response" id="button" class="btn" @click="verifyOtp()">Verify</button>
    <br>
     <p v-if="Response" style="color: red;">Time Remaining: {{ timeRemaining }} seconds</p>
     <br> 
     <a v-if="resentOtp==true"  @click=" requestOTP()">ResentOtp</a>
     <a v-if="forgetPassword==false" @click="forgetPassword=true" style="cursor: pointer;color: #0000FF ;">Forget Password</a>
     <form v-if="isotp==true&&Response==true" @submit.prevent="checkPassword">
      <img v-if="check1" id="success" src="https://www.popsci.com/uploads/2020/01/07/WMD5M52LJFBEBIHNEEABHVB6LA.jpg?auto=webp&width=1440&height=864" alt="Avatar" class="avatar">
      <input v-if="Response" type="number" v-model="otp" class="form-control" placeholder="Enter 6-digit OTP" minlength="6" maxlength="6" required>
      <button v-if="!Response" id="button" class="btn"  >OTP</button>
    </form> 
  </div>
  <div class="notification" v-if="show">
      <div class="notification-content">
        <div v-if="success" class="notification-icon success">&#10004;</div>
        <div v-else class="notification-icon error">&#10006;</div>
        <div class="notification-message">{{ message }}</div>
      </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
  name: 'login-form',
  data() {
    return {
      username: '',
      password: '',
      age:'',
      address:'',
      statusmessage:'',
      otp:'',
      showPassword:false,
      check1:true,
      check2:false,
      resetemail:'',
      forgetPassword:false,
      Response:false,
      timeRemaining: 60,
      timer: null,
       resentOtp:false,
      isotp:false,
      submit:false,
      show: false,
      success: false,
      message: '',
      confirmPassword: ''
    };
  },
  watch: {
    password(newPassword) {
      this.validatePasswords(newPassword, this.confirmPassword);
    },
    confirmPassword(newConfirmPassword) {
      this.validatePasswords(this.password, newConfirmPassword);
    }
  },
  methods: {
    startTimer() {
      if (this.timer) {
        clearInterval(this.timer)
      }
      this.timer = setInterval(() => {
        if (this.timeRemaining > 0) {
          this.timeRemaining -= 1
        } else {
          clearInterval(this.timer)
          this.resentOtp=true;
        }
      }, 1000)
    }
  ,showNotification(success) {
      this.success = success;
      this.message = success ? 'Success notification message' : 'Error notification message';
      this.show = true;
      setTimeout(() => {
        this.hideNotification();
      }, 5000);
    },
    hideNotification() {
      this.show = false;
    }
  ,
  validatePasswords(password, confirmPassword) {
      this.passwordsMatch = password === confirmPassword;
    },
  requestOTP()
    {if(this.submit==true){
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
      this.timeRemaining=60
     this.startTimer()
      this.statusmessage = ''; 
            this.Response=true;
          console.log(response.data);

    }
        })
        .catch(error => {
          console.log(error);
        });

    }},
    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    },
    submitForm() {
      if(this.submit==true)
      this.handleSubmit();
    },
    verifyOtp(){
      const url = 'api/ChangePasswordController?username='+this.username+'&otp='+this.otp;
      axios.get(url,{
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if(response.data.status==='Success')
        {
          this.showNotification(true)
          this.message='Successfully verified'
         this.isotp=true;
         this.requestOTP=false
         this.Response=false
        }
        else if(response.data.status==='Session timeout')
        {
        
          this.showNotification(false)
          this.message=response.data.status

        }
        else if(response.data.status==='Failure'){
          this.showNotification(false)
          this.message='OTP not verified'

        

        }
      
      })

    },

    handleSubmit() {
    
      
      const data = {
        username: this.username,
        password: this.password
      };
      const url = 'api/AuthenticationController?username='+data.username+'&password='+data.password;
      axios.get(url, data, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(response => {

        if(response.data.status==='Failure') {
          document.getElementById("chk").style.color = "red";
          this.statusmessage = 'Username or Password Wrong'; 
          this.check2=true;
          this.check1=false;
        }else if(response.data.status=='unAuthorized') 
        {
          document.getElementById("chk").style.color = "red";
          this.statusmessage = response.data.message; 
          this.check2=true;
          this.check1=false;
        }
        else {
          document.getElementById("chk").style.color = "green";
          this.statusmessage = 'Successfully Loggedin'; 
          this.check2=false;
          this.check1=true;
          console.log(response.data)
      const userdata= {
        name:  response.data.name,
        username :  this.username,
        role : response.data.role,
        age:response.data.age,
        address : response.data.address
      }
      const userDataString = JSON.stringify(userdata);
      sessionStorage.setItem('userData', userDataString);
        console.log(userdata)
          setTimeout(() => {
            this.$router.push('/userHome');
          }, 3000); 
        } 
          })
          .catch(error => {
            console.log(error);
            document.getElementById("chk").style.color = "red";
            this.statusmessage = 'An error occurred while logging in';
       
          })
    },
  validMail(){
        const url = '/api/UserCheck';
        const data = {
          username: this.username
        };
        console.log(data);
        axios.post(url,data,{
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response =>{
          if(response.data.status!='Failure') {
            document.getElementById("status").style.color = "red";
            if(this.username!='')
            {
            this.statusmessage = 'This Email Not Registered';
            this.submit=false
          }
            else
            {
            this.statusmessage = '';
            this.submit=true
            }
          }
          else{
            this.statusmessage = '';
            this.submit=true
          }     
})
  }
}
}

  </script>
  <style scoped>
  body{
    background-image: url("https://img.freepik.com/free-vector/ecommerce-web-page-concept-illustration_114360-8204.jpg?w=1380&t=st=1677906450~exp=1677907050~hmac=62176aa45eaaa2b5cb63f9bfee603810ad7bf299940bf6d3f1ddb7dd8a706e0d");
    background-color: #0099ff;	

  }
  .popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 20%;
  margin:auto;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.popup-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.popup-icon {
  font-size: 64px;
  margin-bottom: 20px;
  color: #fff;
  border-radius: 50%;
  width: 80px;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.success {
  background-color: #00c853;
}

.error {
  background-color: #d50000;
}
  </style>