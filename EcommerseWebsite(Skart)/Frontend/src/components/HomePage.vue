<template>
<header id=home-page>
<nav>
<ul>
<li><router-link to="/signup" class="router"><b>Signup </b></router-link></li>
<li><router-link to="/login" class="router"><b>Login</b> </router-link></li>
<li><router-link to="/aboutus" class="router"><b>About us</b> </router-link></li>
<li><router-link to="/vendorregister" class="router"><b>Become a Seller</b> </router-link></li>
<li><b  class="router" >Customer Care : 1800 232 546</b></li></ul> 
<li>
        <div class="search-box">
          <form>
            <input type="text" placeholder="Search...">
            <button type="submit" ><i class="fa fa-search"></i></button>
          </form>
        </div>
      </li>
</nav>
<h1 style="font-family: 'Syncopate', sans-serif;"><b>SKART</b></h1>
</header>
<br>
  <br>
  <br>
  <div class="carousel">
    <div class="carousel-inner" :style="`transform: translateX(-${currentIndex * 100}%)`">
      <div v-for="(slide, index) in slides" :key="index" class="carousel-item">
        <img :src="slide.image" alt="slide" style="height=500px;width: 100%;">
      </div>
    </div>
  </div>
    <products-page/>
  </template>
  <script>
import ProductsPage from './ProductsPage.vue';
  export default {
  components: { ProductsPage },
    name: 'home-page', method:{
      },data() {
    return {
      slides: [
        { image: 'https://assets.ajio.com/medias/sys_master/images/images/h29/h6e/47141072240670/04022022-D-MHP-exclusive-brands-trends-topwear-startingat249.jpg' },
        { image: 'https://assets.ajio.com/medias/sys_master/images/images/h28/h5d/47141071585310/04022022-D-MHP-exclusive-brands-trends-upto70.jpg' },
        { image: 'https://img.freepik.com/premium-vector/fashion-social-media-post-template_434448-1036.jpg?w=1380' },
        { image: 'https://assets.ajio.com/medias/sys_master/images/images/h0e/h95/47141071781918/04022022-D-MHP-exclusive-brands-trends-toppicks-upto70.jpg' },
      ],
      currentIndex: 0,
      intervalId: null,
    };
  },
  mounted() {
    this.intervalId = setInterval(this.next, 3000);
  },
  methods: {
    next() {
      this.currentIndex = (this.currentIndex + 1) % this.slides.length;
    },
  },
  beforeUnmount() {
    clearInterval(this.intervalId);
  },
};
  </script >
  <style scoped >
  
 body {
  background-image: url("https://img.freepik.com/free-photo/modern-stationary-collection-arrangement_23-2149309652.jpg?w=1380&t=st=1677839141~exp=1677839741~hmac=d8cfea3fa85721bb92400227dd942019f013469faa3cd9f7688d43b68ad17060");

  }
  .carousel {
  overflow: hidden;
  background-color: #fff;
  height: 400px;
  width:100%;
}

.carousel-inner {
  display: flex;
  transition: transform 0.5s ease;
}

.carousel-item {
  min-width: 100%;
}

  </style>