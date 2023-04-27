<template>
  <div>
    <h2>Products</h2>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Product</th>
          <th>Name</th>
          <th>Price</th>
          <th>Count</th>
          <th>sold Product</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="isEmpty">
    <td colspan="7">
      <div style="text-align: center">
        <img id="success" src="https://i.pinimg.com/originals/af/55/87/af5587ad1fa5ca160048939e6cae5fa2.gif" alt="Avatar" style="height:300px; width:430px;" class="avatar">
        <h2>Sorry No Prodcuts Avaialable</h2>
      </div>
    </td>
  </tr> 
        <tr v-else  v-for="product in products" :key="product.productId">
          <td><b>{{ product.productNo}}</b></td>
          <td> <img :src="product.productImageUrl" style="height:250px; width:230px;" id="myImage" /></td>
          <td>{{ product.productName }}</td>
          <td>{{ product.productPrice }}</td>
          <td>{{ product.productCount }}</td>
          <td>{{ product.soldProduct }}</td>
          <td>
            <button @click="editProduct(product)" class="btn">Edit</button>
            <button @click="removeProduct(product)" class="btn" style="background-color:tomato;">Remove</button>
          </td>
        </tr>
      </tbody>
      <td colspan="7">
      <button  @click="addProduct" class="btn" >Add Product</button>
      <router-link to="/vendorhome">
        <button id="new" class="btn">Back</button>
      </router-link>
      </td>
    </table>
    <div v-if="showForm" class="popup">
      <form class="popup-form" @submit.prevent="saveProduct">
        <span class="close" @click="cancelAdd">&times;</span>
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="newProduct.productName" required>
        <label for="name">ImageUrl:</label>
        <input type="text" id="name" v-model="newProduct.productImageUrl" required>
        <label for="description">Description:</label>
        <textarea id="description" v-model="newProduct.productDesc"></textarea>
        <label for="category">Category:</label>
<select id="category" v-model="newProduct.productCategory">
  <div id ="category"></div>
  <option value="Clothing & Apparel">Clothing & Apparel</option>
  <option value="Footwear & Shoes">Footwear & Shoes</option>
  <option value="Electronics & Gadgets">Electronics & Gadgets</option>
  <option value="Games & Toys">Games & Toys</option>
  <option value="Veterinary & Pet Items">Veterinary & Pet Items</option>
  <option value="Stationery">Stationery</option>
  <option value="Hand & Power Tools">Hand & Power Tools</option>
  <option value="Tupperware">Tupperware</option>
  <option value="Furniture">Furniture</option>
  <option value="Sports Products">Sports Products</option>
  <option value="accessories">Accessories</option>    
  <option value="electrical appliances">Electrical Appliances</option>    
</select>
        <label for="price">Price:</label>
        <input type="number" id="price" v-model="newProduct.productPrice" required>
        <label for="price">Offer(%):</label>
        <input type="number" id="offer" v-model="newProduct.offer" v-on:blur="validateOffer" required>
        <label for="count">Count:</label>
        <input type="number" id="count" v-model="newProduct.productCount"  v-on:blur="validateCount" required>
        <input type="hidden" id="vendor" v-model="newProduct.vendorId" >
       <div id="button-container">
        <button type="submit" class="btn">Save</button>
        <button type="button" class="btn cancel" @click="cancelAdd">Cancel</button>
      </div>
      </form>
    </div>
    <div class="popups" id="status-popup" >
  <p id="status-message" style=" margin: auto;"></p>
</div>
  </div>
</template>

<script>
import axios from 'axios'
export default
{
  data() {
    return {
      showForm: false,
      newProduct: {
        productName: '',
        productDesc: '',
        productImageUrl:'',
        productCategory:'',
        productPrice: '',
        productCount: '',
        offer:'',
        vendorId: ''
      },
    
      editingProduct: null,
      products:[],
      isEmpty:false,

    }
  },
  methods: {
    editProduct(product) {
      this.editingProduct = product;
      this.newProduct = Object.assign({}, product);
      this.showForm = true;
    },
    addProduct() {
      this.showForm = true
    },
  
    validateOffer()
    {
     if(this.newProduct.offer>100)
     this.newProduct.offer=100;
     if(this.newProduct.offer<0)
    this.newProduct.offer=0;

    },
    cancelAdd() {
       this.showForm = false;
       this.editingProduct = null;
       this.newProduct = {
        productName: '',
        productImageUrl:'',
        productCategory:'',
        productPrice: 0,
        productCount: 0,
        offer:0,
        vendorId:'',
        isempty:false
      };
    },
    saveProduct() {
      if (this.editingProduct) {
        const index = this.products.findIndex(p => p.productId === this.editingProduct.productId);
        this.$emit('update-product', index, this.newProduct);
        const url = '/api/VendorProductController';
        const confirmed = confirm(`Are you sure you want to update this product ?`);
  if (!confirmed) return;
  const popup = document.getElementById("status-popup");
  const message = document.getElementById("status-message");
        axios.put(url,this.newProduct, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => {
          if(response.data.status=="Success") {
    this.fetchCart();
    message.textContent = response.data.message;
    popup.classList.add("show");
    setTimeout(() => {
      popup.classList.remove("show");
    }, 2000);
  }
          
        })
       
      } else {
        this.newProduct.vendorId=JSON.parse(sessionStorage.getItem('userData')).username;
         const url = '/api/VendorProductController';
        axios.post(url,this.newProduct, {
          headers: {
            'Content-Type': 'application/json'
          }
        }) .then(response => {
          console.log(response.data.message)
          alert(response.data.message)
        })
      }
      this.showForm = false;
      this.editingProduct = null;
      this.newProduct = {
        productName: '',
        productDescription: '',
        productImageUrl:'',
        productCategory:'',
        productPrice: 0,
        productCount: 0,
        offer:0,
        vendorId: this.vendorId
      };
    }
  },
  created()
  {
      axios.get('api/VendorProductController?vendorId='+JSON.parse(sessionStorage.getItem('userData')).username).then((response) => {
      this.products = response.data;
      if (this.products.length === 0) {
      this.isEmpty = true;
    }
      console.log(response.data)
      this.vendorId=JSON.parse(sessionStorage.getItem('userData')).username;
  })
}
}
</script>
<style scoped>

h2 {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

table {
  position: relative;
  width: 94%;
  border-collapse: collapse;
  background-color:white;
  text-align: center;
  margin: auto;
}

thead th {
  font-weight: bold;
  text-align: center;
  border-bottom: 2px solid #ccc;
  padding-bottom: 0.5rem;
  background-color:#0879;
}

tbody td {
  padding: 0.5rem 0;
}


.add-button {
  background-color: #1a202c;
  color: #fff;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  cursor: pointer;
  margin-top: 1rem;
}

.save-button {
  background-color: #1a202c;
  color: #fff;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  cursor: pointer;
  margin-top: 1rem;
}

.label {
  font-weight: bold;
  display: block;
  margin-top: 1rem;
}

input[type="text"],
textarea,
input[type="number"] {
  width: 100%;
  padding: 0.5rem;
  margin-top: 0.5rem;
  border: 2px solid #ccc;
  border-radius: 0.25rem;
  font-size: 1rem;
}

input[type="text"]:focus,
textarea:focus,
input[type="number"]:focus {
  outline: none;
  border-color: #1a202c;
}

@media only screen and (min-width: 768px) {
  .product-list {
    max-width: 800px;
    margin: 2rem auto;
  }
}
.popup {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}

.popup-form {
  background-color: #fefefe;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
  padding: 20px;
  margin: 0px auto; 
  width: 40%;

  position: absolute;
  top: 30%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.popup-form label {
  margin-bottom: 10px;
}



.popup-form button.cancel {
  background-color: #f44336;
}

.popup-form button:hover {
  opacity: 0.8;
}

.popup-form .close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  font-weight: bold;
  color: #aaa;
  cursor: pointer;
}

#category {
  display: block;
  margin-bottom: 10px;
  font-size: 16px;
  padding: 10px;
  width: 100%;
  border: 1px solid white;
  border-radius: 4px;
  box-sizing: border-box;
}

/* Style the select options */
#category option {
  font-size: 16px;
  padding: 10px;
}
#button-container{
  display: inline;
  margin: 5%;
}
.addproduct {
  display: block;
  margin: 0 auto;
}
</style>


