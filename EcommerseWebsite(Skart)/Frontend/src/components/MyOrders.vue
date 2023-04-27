<template>
    <h1>Order Form</h1>
  <div class="order-container">
    <div class="order-details">
      <table>
        <tr>
          <td><img style="height:400px; width:430px;" id="myImage" /></td>
          <td>
           <h1>{{ orderObj.productName }}</h1><br>
                {{ orderObj.productDetails}}<br><br>
           <div style="text-align: center;"><button  class="btn" v-on:click="incrementQuantity()">+</button>
            <b>{{ orderObj. productQuantity }}</b>
            <button class="btn" id="new" v-on:click="decrementQuantity()" >-</button ></div>     
            <h2 style="text-align: center;"><b>&#8377;{{ orderObj.productPrice*orderObj. productQuantity}}.00</b></h2>
          </td>
        </tr>
      </table> 
    </div>
    <div class="address-form">
      <h2>Enter your Shipping address</h2>
      <form @submit.prevent="placeorder"   >
        <input type="text" id="doorno" v-model="doorno" class="form-control" placeholder="DoorNo" required /><br>
        <input type="text" id="street" v-model="street" class="form-control" placeholder="Street" required /><br>
        <input type="text" id="city" class="form-control" placeholder="City" required /><br>
        <input type="text" id="state" v-model="state" class="form-control" placeholder="State" required /><br>
        <input type="text" id="address" v-model="address" class="form-control" placeholder="Pincode" required><br>
        <button type="submit" class="btn" >Place Order</button><br>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'my-order',
  data() {
    return {
      name: '',
      email: '',
      address: '',
      orderObj: '',
      isempty :false,
      statusmessage:''
    };
  },
  mounted() {
    const order = sessionStorage.getItem('order');

    if (order) {
      const orderobj = JSON.parse(order);
      this.orderObj = orderobj;
      document.getElementById('myImage').src = this.orderObj.productImage;
      console.log(this.orderObj.productImage);
    }
  },methods:{
    incrementQuantity(){
      if( this.orderObj.productLimit>this.orderObj.productQuantity)

      this.orderObj. productQuantity++;
    },
    decrementQuantity(){
      if( this.orderObj. productQuantity>1)
      this.orderObj. productQuantity--;
    },
    placeorder()
    { 
      const data={
        customer_id:JSON.parse(sessionStorage.getItem('userData')).username,
        product_id:this.orderObj.productNo,
        quantity:this.orderObj.productQuantity,
        order_date:new Date().toLocaleString()
      }
      
      const confirmed = confirm(`Are you sure you want to cancel the order for ${this.orderObj.productName}?`);
  if (!confirmed) return;
      const url='/api/OrderController'
      axios.post(url,data,{
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
          if(response.data.status==='Success')
            alert(response.data.message);
            this.$router.push('/myorder');
        })
        .catch(error => {
          console.log(error);
        });  
    } 
}
};
</script>
<style scoped>
.address-form {
    background-color: white;
    padding: 40px;
    width: 25%;
    margin: auto;
    margin-top: 100px;
    text-align: center;   
 
}
.order-container {
  width: 95%;
  background-color: white;
  height: 700px;
  display: flex;
  align-items: center;
  margin: auto;
  border-radius: 10px;
    box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.2);
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
  .order-details {
    width: 50%;
    margin-right: 2rem;
  }

</style>
