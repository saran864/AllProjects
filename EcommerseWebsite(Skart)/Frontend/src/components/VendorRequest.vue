<template>
  <div class="signup-form" id="vendor-register">
    <h2>Request Form</h2>
    <form @submit.prevent="register" enctype="multipart/form-data">
      <img style="width:180px; " src="https://i.pinimg.com/474x/0c/be/29/0cbe29a70dddfb7427dc142a3d14174a.jpg"
        alt="Avatar" class="avatar"> <br>
      <input type="text" v-model="name" class="form-control" placeholder="Name" required>
      <input type="text" v-model="companyname" class="form-control" placeholder="CompanyName" required>
      <input type="number" v-model="age" class="form-control" placeholder="age" required>
      <input type="email" v-model="username" id="email" v-on:blur="checkEmailValidity" class="form-control"
        placeholder="Email" required>
      <p id="status">{{ statusmessage }}</p>
      <div class="password-input">
        <input id="password" :type="showPassword ? 'text' : 'password'" v-model="password" class="form-control"
          placeholder="Password" required>
        <div class="password-toggle-icon" @click="togglePasswordVisibility">
          <i :class="showPassword ? 'fa fa-eye-slash fa-lg' : 'fa fa-eye fa-lg'"></i>
        </div>
      </div> <input type="text" v-model="address" class="form-control" placeholder="Address" required>
      <div>
        <label for="file-input" class="file-label">Upload File:</label>
        <input id="file-input" type="file" @change="handleFileChange" accept=".pdf" required>
      </div>
      <p class="red-text">NOTE:* Upload product details and image in this pdf.</p>
      <button id="button"
        class="btn">Request</button><router-link to="/"><button class="btn" id="new">Back</button></router-link> 
      <p id="check"></p>
    </form>
  </div>


  <div class="popup" v-if="showPopup">
    <div class="popup-content">
      <p><b>Thanks for Visting Skart</b></p>
      <img id='success'
        src="https://s3.getstickerpack.com/storage/uploads/sticker-pack/memoji-1/sticker_24.png?8e28f1cebdf7fbd062f6432338ee7a3d&d=200x200"
        alt="Avatar" class="avatar">
      <p><b>Dear [{{ name }}],
          I am pleased to inform you that we have received your vendor request and would like to express our gratitude for
          your interest in joining our team. We appreciate your willingness to collaborate with us, and we are excited
          about the opportunity to work together.
          plese Check your Inbox</b></p>
      <router-link to="/"><button @click="showPopup = false" class="close">close</button></router-link>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: 'vendor-register',
  data() {
    return {
      name: '',
      companyname: '',
      age: '',
      username: '',
      password: '',
      address: '',
      statusmessage: '',
      showPassword: false,
      submit: false,
      selectedFile: null,
      showPopup: false
    };
  },
  methods: {
    handleFileChange(event) {
      this.selectedFile = event.target.files[0];
      console.log(this.selectedFile);
    },

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
        this.statusmessage = 'Enter a Valid Gamil Address';
        return;

      } else {

        this.validMail();
      }
    }, register() {
      if (this.submit == true) {
        const formData = new FormData();
        formData.append('pdfFile', this.selectedFile);
        const url = '/api/VendorsController';
        const data = {
          name: this.name,
          companyname: this.companyname,
          age: this.age,
          username: this.username,
          password: this.password,
          address: this.address,
          role: 'vendor',
          pdfFile: this.selectedFile
        };
        document.getElementById("status").style.color = "green";
        document.getElementById("status").innerHTML = "Processing....!!Please wait";

        axios.post(url, data, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }

        }).then(response => {
          if (response.data.status === "Success") {
            this.name=response.data.message;
            this.showPopup = true;

          }

        }).catch(error => {
          console.log(error)
          document.getElementById("status").style.color = "red";
          document.getElementById("status").innerHTML = "Something Went Wrong";

        });

      }
    },

    validMail() {
      const url = '/api/UserCheck';
      const data = {
        username: this.username
      };
      console.log(data);
      axios.post(url, data, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (response.data.status === 'Failure') {
            document.getElementById("status").style.color = "red";
            this.statusmessage = 'Sorry !! this Email Already Registered';
          } else {
            this.submit = true
          }
        })
    }
  }
};
</script>
<style scoped>
.file-label {
  font-size: 16px;
  font-weight: bold;
  margin-right: 10px;
}

button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin-top: 10px;
  cursor: pointer;
}

button:hover {
  background-color: #3e8e41;
}

.red-text {
  color:green
}</style>