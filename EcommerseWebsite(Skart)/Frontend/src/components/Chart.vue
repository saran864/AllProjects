<template>
    <div>
      <div v-for="(product, index) in products" :key="index">
    <h2>{{ product.productName }}</h2>
  </div >
      <canvas ref="donutChart"></canvas>
    </div>
  </template>
  <script  >
import Chart from 'chart.js/auto';
  import {mapGetters,  mapActions } from 'vuex'
  export default {
    name: 'Donut-Chart',
    mounted() {
      this.renderChart();
    },
    methods: {
      renderChart() {
        console.log(this.soldProductsData)
        const data = {
          datasets: [
            {
              data: this.soldProductsData,
              backgroundColor: [
                '#FF6384',
                '#36A2EB',
                '#FFCE56',
              ],
            },
          ],
          labels: [
            'Product 1',
            'Product 2',
            'Product 3',
          ],
        };
        const options = {
          responsive: true,
          cutoutPercentage: 70,
        };
        const ctx = this.$refs.donutChart.getContext('2d');
        new Chart(ctx, {
          type: 'doughnut',
          data: data,
          options: options,
        });
      },
      ...mapActions(['fetchVendorProducts']),
    },
    computed: {
      soldProductsData() {
        return this.products.map((product) => product.soldProduct);
      },
     
    ...mapGetters(['getVendorProducts']),
    products() {
      return this.getVendorProducts;
    }
    },
    created()
    {
        this.fetchVendorProducts();
         }

  
}

  </script>
  