<template>
  <div  class="signup-form" id="otp-form">
    <h2>Verify OTP</h2>
    <form @submit.prevent="verifyOTP">
      <input type="text" v-model="otp" class="form-control" placeholder="Enter OTP" required>
      <p id="otp-status">{{ otpStatus }}</p>
      <button type="submit" class="btn">Submit</button>
    </form>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  name: 'otp-verify',
  data() {
    return {
      otp: '',
      otpStatus: '',
      
    };
  },
  methods: {
    verifyOTP() {
      const url = '/api/AuthenticationController';
      const data = {
        otp: this.otp
      };
      console.log(data.userdata);
      axios.post(url, data, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if(response.data.status === 'Success') {
          this.otpStatus = 'OTP verification successful. Redirecting to Home page.';
          setTimeout(() => {
            this.$router.push('/userhome');
          }, 2000); 
        } else {
          this.otpStatus = 'Invalid OTP. Please try again.';
        }
      })
      .catch(error => {
        console.log(error);
      });
    }
  },

};
</script>