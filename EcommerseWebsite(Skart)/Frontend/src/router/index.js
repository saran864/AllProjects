import HomePage from '@/components/HomePage'
import LoginForm from '@/components/LoginForm'
import RegisterForm from '@/components/RegisterForm'
import AboutUs from '@/components/AboutUs'
import ProductsPage from '@/components/ProductsPage'
import UserHome from '@/components/UserHome'
import VenorRequest from '@/components/VendorRequest'
import Otp from '@/components/Otp'
import MyOrders from'@/components/MyOrders'
import VendorHome from '@/components/VendorHome'
import AdminHome from '@/components/AdminHome'
import MyCart from '@/components/MyCart'
import CustomerOrders from'@/components/CustomerOrders'
import MyProduts from'@/components/MyProduts'
import ViewCustomers from '@/components/ViewCustomers'
import AdminNotification from '@/components/AdminNotification'
import { createRouter,  createWebHistory } from 'vue-router'
import Chart from '@/components/Chart'

const routes=[
{
    path:'/',
    component :HomePage 
},
{
    path:'/login',
    component :LoginForm
},
{
    path:'/signup',
    component :RegisterForm
},
{
    path:'/aboutus',
    component :AboutUs
},{
    path:'/products',
    component : ProductsPage
},
{
    path:'/userhome',
    component : UserHome
},{
    path:'/vendorregister',
    component : VenorRequest
}
,
{
    path:'/otp',
    component:Otp
},
{
    path:'/myorders',
    component:MyOrders
},
{
    path:'/vendorhome',
    component:VendorHome
},
{
    path:'/adminHome',
    component:AdminHome
},
{
    path:'/myCart',
    component:MyCart
},
{
    path:'/myorder',
    component:CustomerOrders
},
{
    path:'/myproduct',
    component:MyProduts
},
{
    path:'/view',
    component:ViewCustomers
},
{
    path:'/notification',
    component:AdminNotification
},
{
    path:'/chart',
    component:Chart
},
];
const router =createRouter({
    history:createWebHistory(process.env.BASE_URL),
    routes
});
export default router;
