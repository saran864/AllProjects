<template>
    <div>
      <div v-for="product in products" :key="product.productNo">
        <div class="content" id="product-data">
          <br/>
          <img :src="product.productImageUrl" style="height:250px; width:230px;" id="myImage" />
          <h4 id="productName">{{ product.productName }}</h4>
          <p id="productDesc">{{ product.productDesc }}</p>
            <h3 id="productPrice">&#8377;{{ product.productPrice }}</h3>
  <h4 id="price"><del>MRP &#8377;{{ product.Price }}</del> <span id="discountedPrice">({{ product.offer }}% OFF)</span></h4>
          <ul>
            <br /><br />
            <button class="cart" @click="showPopup = true">ADD TO CART</button>
          <button class="buynow" @click="showPopup = true">BUY NOW</button>
        </ul>
      </div>
    </div>
    </div>
    <div class="popup" v-if="showPopup">
      <div class="popup-content">
        <p><b>Thanks for Visiting Skart</b></p>
        <img  id='success' src="https://i.pinimg.com/474x/55/32/f3/5532f32f514cdfc36e80a5408922383e.jpg"
          alt="Avatar" class="avatar">
        <p><b>Please choose an option to continue</b></p>
        <router-link to="/login" ><button class="cart">Login</button></router-link>
         <router-link to="/signup" ><button class="buynow">signup</button></router-link>
            <router-link to="/" ><button @click="showPopup = false" class="close">close</button></router-link>
      </div>
    </div>

  </template>
  
  <script>
  import { mapGetters, mapActions } from 'vuex';
  
  export default {
  
    name: 'ProductList',
    data() {
      return {
        showPopup: false,
            };
    },
  computed: {
    ...mapGetters(['getProducts']),
    products() {
      return this.getProducts;
    }
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    ...mapActions(['fetchProducts'])
  }
};
      
  </script>
  <style scoped>




#productPrice {
  font-size: 24px;
  color: #333;
}
#price {
  margin: 0;
}

#price del {
  color: #999;
  text-decoration: line-through;
}

#discountedPrice {
  font-weight: bold;
  color: green;
}

  </style>
  