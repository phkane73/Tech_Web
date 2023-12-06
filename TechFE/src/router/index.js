import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import ProductView from "../views/ProductsView.vue";
import LaptopView from "../views/LaptopsView.vue";
import TabletView from "../views/TabletsView.vue";
import SmartWatchView from "../views/SmartWatchView.vue";
import EarphoneView from "../views/EarphoneView.vue";
import ProductDetail from "../views/ProductDetail.vue";
import Error from "../views/Error.vue";
import CartView from "../views/CartView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import ContactView from "../views/ContactView.vue";
import ServiceView from "../views/ServiceView.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/cart",
      name: "cart",
      component: CartView,
    },
    {
      path: "/about",
      name: "about",
      component: AboutView,
    },
    {
      path: "/contact",
      name: "contact",
      component: ContactView,
    },
    {
      path: "/service",
      name: "service",
      component: ServiceView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
    },
    {
      path: "/products",
      name: "products",
      component: ProductView,
    },
    {
      path: "/products/laptops",
      name: "laptops",
      component: LaptopView,
    },
    {
      path: "/products/smartwatchs",
      name: "smartwatchs",
      component: SmartWatchView,
    },
    {
      path: "/products/tablets",
      name: "tablets",
      component: TabletView,
    },
    {
      path: "/products/earphones",
      name: "earphones",
      component: EarphoneView,
    },
    {
      path: "/products/:id",
      name: "productdetail",
      component: ProductDetail,
    },
    {
      path: "/:catchAll(.*)",
      component: Error,
    },
  ],
});

export default router;
