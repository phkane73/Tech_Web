<template>
  <div class="container">
    <div class="row d-flex justify-content-center align-items-center" style="height: 100vh;">
      <form class="col-md-5 bg-white p-4" method="post" @submit.prevent="login()">
        <RouterLink to="/">
          <i class="fa-solid fa-circle-xmark" style="float: right; font-size: 30px; color: #D4B642;"></i>
        </RouterLink>
        <h2 class="text-center">Đăng nhập</h2>
        <div class="form-group">
          <label for="exampleInputEmail1">Địa chỉ Email:</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
            placeholder="Nhập địa chỉ Email" v-model="email" required />
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Mật khẩu:</label>
          <input type="password" class="form-control" id="password" placeholder="Nhập mật khẩu" required
            v-model="password" />
        </div>
        <div v-if="error" style="color: red">
          {{ error }}
        </div>
        <div class="d-flex justify-content-between">
          <button type="submit" class="btn btn-primary">Đăng nhập</button>
          <RouterLink to="/register">
            <div class="btn btn-dark">Đăng ký</div>
          </RouterLink>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped></style>

<script>
import userServices from "../services/userServices";
export default {
  name: "login",
  data: () => ({
    email: "",
    password: "",
    error: "",
  }),
  methods: {
    async login() {
      try {
        await userServices.login(this.email, this.password).then((res) => {
          sessionStorage.setItem("userId", res.data.user.id);
          sessionStorage.setItem("userName", res.data.user.username);
          sessionStorage.setItem("cartId", res.data.id);
          sessionStorage.setItem("address", res.data.user.address);
          window.location.replace("/");
        });
      } catch (e) {
        this.error = e.response.data;
      }
    },
  },
};
</script>
