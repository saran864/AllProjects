<template>
  <header>
    <nav>
      <ul>
        <li><a ><b class="router"  style="color: #0073e6">{{ this.userData.name}}</b></a></li>
        <li ><b class="router" @click="showProfile" >MyProfile</b></li>
        <li><router-link to="/myorder" class="router"><b>MyOrders</b></router-link></li>
        <li><router-link to="/myCart" class="router"><b>MyCart </b><span v-if="isempty" class="badge"> {{ this.cartLength}}</span></router-link></li>
        <li v-if="checkAdmin"><router-link to="/adminhome" class="router"><b>Admin Console</b></router-link></li>
        <li v-if="checkVendor"><router-link to="/vendorhome" class="router"><b>VendorConsole</b></router-link></li>
        <li><b b class="router" @click="logout" >Logout</b></li>
        <li>
        <div class="search-box">
          <form>
            <input type="text" v-model="searchTerm" placeholder="Search products...">
            <button type="submit" ><i class="fa fa-search"></i></button>
          </form>
        </div>
      </li>
      </ul>
    </nav>
    <h1 style="margin-left: 150px; font-family: Syncopate, sans-serif"><b>SKART</b></h1>
  </header>
<br><br> <br>
<div v-if="showProfileModal" class="popup-overlay">
        <div class="popup-content">
        <div class="modal-header">
          <h2 style="text-align: center;">User Profile</h2>
          <span class="close" @click="closeProfile">&times;</span>
        </div>
        <div class="modal-body">
          <table style="border-collapse: collapse; width: 100%; max-width: 600px;">
  <tr>
    <td ><b>Name:</b></td>
    <td >{{ this.userData.name }}</td>
  </tr>
  <tr>
    <td ><b>Email:</b></td>
    <td>{{ this.userData.username }}</td>
  </tr>
  <tr>
    <td ><b>Age:</b></td>
    <td >{{ this.userData.age }}</td>
  </tr>
  <tr>
    <td ><b>Address:</b></td>
    <td >{{ this.userData.address }}</td>
  </tr>
</table>
        </div>
      </div>
    </div>
    <br>
    <div v-for="product in  filteredProducts" :key="product.productNo">
      <div class="content" id="product-data">
        <br />
        <img :src="product.productImageUrl" style="height:250px; width:230px;" id="myImage" />
        <h4 id="productName">{{ product.productName }}</h4>
        <p id="productDesc">{{ product.productDesc }}</p>
        <h3 id="productPrice">&#8377;{{ product.productPrice }}</h3>
        <h4 id="price"><del style="color: #999;">MRP &#8377;{{ product.Price }}</del> <span id="discountedPrice" style="color: green;">({{ product.offer }}% OFF)</span></h4>
        <ul>
        
          <button id="cart" class="cart" v-on:click="addToCart(product.productNo)" >ADD TO CART</button>
          <button class="buynow" id="buynow" v-on:click="addToOrder(product.productNo)" >BUY NOW</button>
        </ul>
      </div>
      <div class="popups" id="status-popup" >
  <p id="status-message" style=" margin: auto;"></p>
</div>
    </div>
</template>
<script>
import axios from 'axios';
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'user-home',
  data() {
    return {
      showProfileModal: false,
      checkAdmin:false,
      checkVendor:false,
      statusmessage:'',
      isempty:true,
      orderedPrducts:[],
      userData:'',
      searchTerm:''

    };
  },
  methods: {

    showProfile() {
      this.showProfileModal = true;
    },
    closeProfile() {
      this.showProfileModal = false;
    },
    addToCart(productNo){
      const url = '/api/CartController';
      const data={
        product_id:productNo,
        customer_id:this.userData.username
      }
      axios.post(url,data, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
  const popup = document.getElementById("status-popup");
  const message = document.getElementById("status-message");
  if(response.data.status=="Success") {
    this.fetchCart();
    message.textContent = response.data.message;
    popup.classList.add("show");
    setTimeout(() => {
      popup.classList.remove("show");
    }, 2000);
  } else {
    message.textContent = response.data.message;
    popup.classList.add("show");
    setTimeout(() => {
      popup.classList.remove("show");
    }, 2000);
  }  
})
.catch(error => {
  console.error(error);
});
    },
    addToOrder(productNo){
      const selectedProduct = this.products.find(product => product.productNo === productNo);
const order = {
  productNo: selectedProduct.productNo,
  productName: selectedProduct.productName,
  productPrice: selectedProduct.productPrice,
  productImage:selectedProduct.productImageUrl,
  productDetails:selectedProduct.productDesc,
  productLimit:selectedProduct.productCount,
  productQuantity:1
,
  customer_id:this.userData.username,
};
console.log(order)
sessionStorage.setItem('order',  JSON.stringify(order));
this.$router.push('/myorders')
    },
    ...mapActions(['fetchProducts']),
    ...mapActions(['fetchCart']),
    logout()
    {
      this.email='';
      this.sessionStorage=null;
      this.$router.push('/'); 
    }
  },
  mounted() {
    const userDataString = sessionStorage.getItem('userData');
    this.userData = JSON.parse(userDataString);
    console.log(this.userData.username)
    if(this.userData.role==='admin')
    this.checkAdmin=true;
    if(this.userData.role==='vendor')
    this.checkVendor=true;
    if(this.userData.username===null || sessionStorage==null )
    {
      alert('Unauthorized Action Please Login');
            this.$router.push('/');
    }  
}, computed: {
    ...mapGetters(['getProducts']),
    products() {
      return this.getProducts;
    }
    , ...mapGetters(['getCartLength']),
    cartLength() {
      return this.getCartLength;
    },
    filteredProducts() {
    return this.products.filter(product => {
      return product.productName.toLowerCase().includes(this.searchTerm.toLowerCase())
    })

  }
},
  created() {
    this.fetchProducts(),
    this.fetchCart();
  }, 
  }
  </script>
  <style scoped>
   .badge {
    background-color:red;
    border-radius: 50%;
    color: #fff;
    display: inline-block;
    font-size: 12px;
    height: 20px;
    line-height: 20px;
    text-align: center;
    vertical-align: middle;
    width: 20px;
  }
  td{
    border: 1px solid #ddd; 
    padding: 8px;
  }
  </style>
  