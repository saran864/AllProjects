<template>
  <header>
    <nav>
      <ul>
        <li><router-link to="" class="router"><b>MyProfile</b></router-link></li>
        <li><router-link to="/myproduct" class="router"><b>MyProducts</b></router-link></li>
        <li><router-link to="" class="router"><b>ViewOrders</b></router-link></li>
        <li><router-link to="/userHome" class="router"><b>User Console</b></router-link></li>
      </ul> 
      <li><router-link to="/" class="router"><b>Logout</b></router-link></li>
    </nav>
    <h1 style="margin-left: 450px; font-family: Syncopate, sans-serif"><b>SKART</b></h1>
  </header>
  <br><br><br>
<div id="chart">
      <canvas ref="donutChart"  ></canvas>
    </div>

</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import Chart from 'chart.js/auto';

export default {
  name: 'vendor-home',
  data() {
    return {
      vendorProduct: [],
    }
  },
  style: {
     backgroundColor:'white'
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
          labels: this.vendorProduct.map(product => product.productName)
        };
        
        const options = {
          responsive: true,
          cutoutPercentage: 50,
        };
        const ctx = this.$refs.donutChart.getContext('2d');
        new Chart(ctx, {
          type: 'doughnut',
          data: data,
          options: options,
        });
      },
    ...mapActions(['fetchVendorProducts']),
    ...mapGetters(['getVendorProducts']),
  },
  computed: {
   
    products() {
      
      return this.getVendorProducts;
    },
   
  },
  mounted() {
    this.renderChart();
   },
  created() {
    this.fetchVendorProducts();
    this.vendorProduct=this.mapGetters;
  }
};
</script>

<style scoped>
canvas{
  background-color:white;
  max-width: 2000px;
  max-height: 800px;


}
#chart{
  width: 100%;
}


</style> 
