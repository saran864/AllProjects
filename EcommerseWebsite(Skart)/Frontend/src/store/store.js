import { createStore } from "vuex";
import axios from 'axios'
const store = createStore({
    state: {
        products: [],
        userData:{}
      },
      
      mutations: {
        setProducts(state, products) {
          state.products = products;
        },
        SET_USERDATA(state, userdata) {
          state.userData = userdata
        }
    },
      actions: {
        fetchProducts({ commit }) {
          axios.get('/api/ProductController')
            .then(response => {
              commit('setProducts', response.data);
            })
            .catch(error => console.log(error));
        }
      },
      getters: {
        getProducts: state => state.products
      }
  });

 