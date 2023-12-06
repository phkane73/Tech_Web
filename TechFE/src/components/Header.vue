<template>
  <div class="container-fluid bg-white">
    <div class="row m-0" style="height: 50px; background-color: #EDEAE6">
      <div class="container d-flex justify-content-between">
        <span style="line-height: 50px; cursor: default">Hotline: 0857487577</span>
        <div>
          <div style="line-height: 50px" v-if="!username">
            <RouterLink to="/login" class="link">Đăng nhập</RouterLink>
            <span>/</span>
            <RouterLink to="/register" class="link">Đăng ký</RouterLink>
          </div>
          <span style="line-height: 50px" v-if="username">Xin chào: {{ username }}</span>
          <a v-if="username" @click="logout()" style="
              border: none;
              cursor: pointer;
              margin-left: 20px;
            ">
            <i class="fa-solid fa-right-from-bracket"></i>
          </a>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row bg-white" style="height: 130px">
        <div class="col-md-3">
          <RouterLink to="/">
            <img src="../assets/logo.png" style="margin-left: -38px; width: 300px; height: 130px" />
          </RouterLink>
        </div>
        <div class="col-md-8 d-flex">
          <ul class="menu m-0 p-0">
            <li class="menu-item">
              <RouterLink class="link" to="/">Trang chủ</RouterLink>
            </li>
            <li class="menu-item">
              <RouterLink class="link" to="/about">Giới thiệu</RouterLink>
            </li>
            <li class="menu-item">
              <RouterLink class="link" to="/products">Sản phẩm</RouterLink>
            </li>
            <li class="menu-item">
              <RouterLink class="link" to="/service">Dịch vụ</RouterLink>
            </li>
            <li class="menu-item">
              <RouterLink class="link" to="/contact">Liên hệ</RouterLink>
            </li>
          </ul>
        </div>
        <div class="col-md-1 d-flex justify-content-end align-items-center">
          <Orders></Orders>
          <RouterLink class="link" to="/cart">
            <i class="fa-solid fa-bag-shopping cart" style="font-size: 30px; position: relative"></i>
          </RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.menu {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}

.menu-item .link {
  font-weight: 700;
  font-size: 18px;
  text-transform: uppercase;
  padding: 10px;
  border-radius: 5px;
  transition: all 0.2s linear;
}

.menu .link:hover {
  color: #3edbf0;
}

.cart {
  transition: all 0.2s linear;
}

.cart:hover {
  color: #3edbf0;
}
</style>
<script>
import { RouterLink } from "vue-router";
import productServices from "../services/productServices";
import Orders from "../components/Orders.vue"
export default {
  components: {
    Orders
  },
  data() {
    return {
      username: sessionStorage.getItem("userName"),
      number: 0,
      cartId: sessionStorage.getItem("cartId"),
    };
  },
  methods: {
    logout() {
      sessionStorage.clear();
      this.username = sessionStorage.getItem("userName");
      this.cartId = sessionStorage.getItem("cartId");
    },
    count(cart_id) {
      productServices.numberCartDetail(cart_id).then((res) => {
        this.number = res.data;
      });
    },
  },
  created() {
    this.count(this.cartId);
  },
};
</script>
