<template>
      <h1>My Orders</h1>
    <div>
      <table>
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Product</th>
            <th>ProductName</th>
            <th>Quantity</th>
            <th>Order Date</th>
            <th>Total Cost</th>
            <th>action</th>
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
  <tr v-else v-for="(order, OrderId) in orders" :key="OrderId">
            <td><b>{{ order.OrderId }}</b></td>
          <td> <img :src="order.productImageUrl" style="height:350px; width:430px;" id="myImage" /></td>
          <td><b>{{ order.productName }}</b></td>
          <td><b>{{ order.Quanity}}</b></td>
          <td><b>{{ order.orderDate}}</b></td>
          <td><b>{{ order.totalCost}}</b></td>
          <td><button v-on:click="CancelOrder(order.OrderId)" class="btn" id="new">Cancel</button></td>
        </tr>
        </tbody>
      </table>
    </div>
  </template>
  <script>
  import axios from 'axios'
  export default {
    name: 'OrderTable',

    data()
    {
        return {
            isEmpty :false,
            orders:[]
      };
    

    },methods:{
      CancelOrder(OrderId){

        const order = this.orders.find(order => order.OrderId === OrderId);
  if (!order) return;
  const confirmed = confirm(`Are you sure you want to cancel the order for ${order.productName}?`);
  if (!confirmed) return;
  const orderId=parseInt(OrderId.split('_')[1], 10)
  const url = `api/OrderController?orderId=`+orderId+`&customerId=${JSON.parse(sessionStorage.getItem('userData')).username}`;
  axios.delete(url).then((response) => {
    console.log(response.data);
    this.orders = this.orders.filter(order => order.OrderId !== OrderId);
    if (this.orders.length === 0) {
      this.isEmpty = true;
    }
  }).catch((error) => {
    console.log(error.response.data);
  });
}  
    },
    created() {
    axios.get('api/OrderController?username='+JSON.parse(sessionStorage.getItem('userData')).username).then((response) => {
      this.orders = response.data;
    console.log(this.orders)
    if(response.data.length==0)
    this.isEmpty=true;
  })}
}
  </script>
  
  <style scoped>
  table {
    border-collapse: collapse;
    width: 95%;
    margin: auto;
    background-color: white;
  }
  th {
    background-color: #b3f0ff;
    color:black;
    font-weight: bold;
    padding: 12px 8px;
    text-align: center;

  }
  td {

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
  