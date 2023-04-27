import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import { createStore } from "vuex";
const store = createStore({
    state: {
        products: [],
        cartLength:0,
        vendorProducts:[]
      },
      mutations: {
        setProducts(state, products) {
          state.products = products;
        },
        setCartLength(state, cartLength) {
          state.cartLength = cartLength
        },
        setVendorProducts(state, vendorProducts) {
          state.vendorProducts = vendorProducts
        }
    },
      actions: {
        fetchCart({ commit }) {
          axios.get('api/CartController?username='+JSON.parse(sessionStorage.getItem('userData')).username).then((response) => {
              commit('setCartLength', response.data.length);
            })
            .catch(error => console.log(error));
        },
        fetchProducts({ commit }) {
          axios.get('/api/ProductController')
            .then(response => {
              commit('setProducts', response.data);
            })
            .catch(error => console.log(error));
        }
        , fetchVendorProducts({ commit }) {
          axios.get('/api/VendorProductController?vendorId='+JSON.parse(sessionStorage.getItem('userData')).username)
            .then(response => {
              commit('setVendorProducts', response.data);
            })
            .catch(error => console.log(error));
        }
      },
      getters: {
        getProducts: state => state.products,
        getCartLength: state => state.cartLength,
        getVendorProducts:state =>state.vendorProducts

      }
  });

 
const app = createApp(App);
app.use(router);
app.use(store);
app.mount('#app');