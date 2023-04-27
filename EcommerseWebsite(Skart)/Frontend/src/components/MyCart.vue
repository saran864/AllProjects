<template>
  <div >
    <h1>My Cart</h1>
    <table style="background-color:white;text-align: center;">
      <thead>
        <tr>
          <th>Product</th>
          <th>Product Name</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Total Cost</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="isEmpty">
    <td colspan="7">
      <div style="text-align: center">
        <img id="success" src="https://i.pinimg.com/originals/af/55/87/af5587ad1fa5ca160048939e6cae5fa2.gif" alt="Avatar" style="height:300px; width:430px;" class="avatar">
        <h2>No Products  in your cart</h2>
      </div>
    </td>
  </tr>
        <tr v-else v-for="(item, product_id) in cartItems" :key="product_id">
          <td  > <img :src="item.product_image" style="height:300px; width:430px;" id="myImage" /></td>
          <td><b>{{ item.product_name }}</b></td>
          <td><b>{{ item.product_price}}</b></td>
          <td>
            <button v-on:click="incrementQuantity(product_id)" class="btn">+</button>
            <b>{{ item.quantity }}</b>
            <button v-on:click="decrementQuantity(product_id)" class="btn" id="new">-</button>
         </td>
          <td><b>{{ item.quantity * item.product_price }}</b></td>
          <td><button v-on:click="removeFromCart(product_id)" class="btn" id="new">Remove</button></td>
       </tr>
       <div v-if="!isEmpty">
       <td></td>
       <td><div><b>Total Cost: Rs.{{ totalCost }}</b></div></td> 
       <td><button class="btn" v-on:click="proceedToCheckout">Checkout</button></td> 
     <td><router-link to="/userhome">
        <button id="new" class="btn">Back</button>
      </router-link></td>  
      </div>
      </tbody>
      <td colspan="7"></td>
    </table>
    <div style="margin: auto;">
   
    </div>
  </div>
  <div class="popups" id="status-popup">
  <p id="status-message"></p>
</div>
</template>
  <script>
  import axios from 'axios';
  export default {
    name: 'my-cart',
    data() {
      return {
         userData:'',
         cartItems: [],
         statusmessage:'',
         isEmpty:false
        
      };
    },
    methods: {
      checkout() {
        alert('Checkout clicked');
      },incrementQuantity(product_id) {
       
      const item = this.cartItems[product_id];
      if(item.quantity<item.quantity_limit){
      item.quantity++;  
    }
    else{
      const message = document.getElementById("message");
const popup = document.getElementById("popup");

      message.textContent ='OUT OF STACK';
    popup.classList.add("show");
    setTimeout(() => {
      popup.classList.remove("show");
    }, 2000);

    }
  },
    decrementQuantity(product_id) {
      const item = this.cartItems[product_id];
      if (item.quantity > 1) {
        item.quantity--;
      }
    },
    removeFromCart(product_id) {
      const item = this.cartItems[product_id];
      const url = `api/CartController?product_id=${item.product_id}&customer_id=${this.userData.username}`;
  axios.delete(url).then((response) => {
    this.cartItems.splice(product_id, 1);
    console.log(response.data);
    if(this.cartItems.length==0)
    this.isEmpty=true;
  }).catch((error) => {
    console.log(error.response.data);
  });   
    },
    proceedToCheckout() {


      const data={
        cartItem:this.cartItems,
        customer_id:JSON.parse(sessionStorage.getItem('userData')).username,
        order_date:new Date().toLocaleString()
      }

      const confirmed = confirm(`Are you sure you want to the order ?`);
  if (!confirmed) return;
      
      const url = '/api/OrderByCartController'
        axios.post(url,data, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
          console.log(response.data)
     
    
        })


    }
    }
  ,  created() {
      const userDataString = sessionStorage.getItem('userData');
      this.userData = JSON.parse(userDataString);
      axios.get('api/CartController?username='+this.userData.username).then((response) => {
      this.cartItems = response.data;
      if(response.data.length==0)
    this.isEmpty=true;

   
  })}, computed: {
    totalCost() {
      return this.cartItems.reduce((total, item) => total + item.quantity * item.product_price, 0);
    }
  }
  };
  </script>
  <style scoped>
  table {
    border-collapse: collapse;
    width: 95%;
    margin: auto;
  }
  th {
    background-color: #b3f0ff;
    color: black;
    font-weight: bold;
    padding: 12px 8px;
    text-align: center;
  }
  td {
    border: none;
    padding: 8px;
    text-align: center;
  }
  img {
    object-fit: contain;
  }
  tr{
    text-align:"center"
  }
  .btn {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 8px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 8px;
  }
  .btn:hover {
    background-color: green;
  }

  #total-cost {
    font-size: 18px;
    font-weight: bold;
    text-align: right;
    padding-right: 12px;
  }

  #checkout-btn {
    background-color: #4CAF50;
    color: white;
    font-weight: bold;
    font-size: 16px;
    padding: 8px 16px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.2s;
  }

  #checkout-btn:hover {
    background-color: #3e8e41;
  }
</style>

