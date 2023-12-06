<template>
    <div class="container">
        <div class="row d-flex justify-content-center align-items-center" style="height: 100vh;">
            <form class="col-md-5 p-4 bg-white" method="post" @submit.prevent="register()" id="form">
                <RouterLink to="/">
                    <i class="fa-solid fa-circle-xmark" style="float: right; font-size: 30px; color: #D4B642;"></i>
                </RouterLink>
                <h1 class="text-center text-uppercase">Đăng ký</h1>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" placeholder="Nhập địa chỉ Email" v-model="email"
                        @change="clearError" required>
                    <span style="color: red;">{{ errorEmail }}</span>
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại</label>
                    <input type="text" class="form-control" id="phone" placeholder="Nhập số điện thoại" v-model="phone"
                        @change="clearError" required>
                    <span style="color: red;">{{ errorPhone }}</span>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" placeholder="Nhập mật khẩu" v-model="password"
                        @change="clearError" required>
                    <span style="color: red;">{{ errorPassword }}</span>
                </div>
                <div class="form-group">
                    <label for="passwordcfm">Nhập lại mật khẩu</label>
                    <input type="password" class="form-control" id="passwordcfm" placeholder="Nhập lại mật khẩu"
                        @change="clearError" required>
                    <span style="color: red;">{{ errorPasswordCfm }}</span>
                </div>
                <div class="form-group">
                    <label for="username">Họ Tên</label>
                    <input type="text" class="form-control" id="username" placeholder="Nhập tên" v-model="username"
                        @change="clearError" required>
                </div>
                <div class="form-group">
                    <label for="address">Địa chỉ</label>
                    <input type="text" class="form-control" id="address" placeholder="Nhập địa chỉ" v-model="address"
                        @change="clearError" required>
                </div>
                <button type="submit" class="btn btn-primary mt-2">Đăng Ký</button>
                <RouterLink to="/login">
                    <button type="submit" class="btn btn-dark float-right mt-2">Đăng Nhập</button>
                </RouterLink>
                <span style="color: red; display: block;">{{ error }}</span>
            </form>
        </div>
    </div>
</template>
  
<style scoped></style>
  
<script>
import userServices from "../services/userServices";
export default {
    name: "RegisterView",
    data() {
        return {
            phone: "",
            username: "",
            address: "",
            password: "",
            email: "",
            errorPhone: "",
            errorPassword: "",
            errorPasswordCfm: "",
            errorEmail: "",
            error: "",
        }
    },
    methods: {
        async login(email, password) {
            try {
                await userServices.login(email, password).then((res) => {
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
        async register() {
            const password = document.getElementById("password");
            const passwordcfm = document.getElementById("passwordcfm");
            if (password.value === passwordcfm.value) {
                await userServices.register(this.phone, this.username, this.address, this.password, this.email).then(res => {
                    if (res.data == 1) {
                        this.error = "Tài khoản đã tồn tại!"
                    }
                    if (res.data == 200) {
                        this.login(this.email, this.password)
                    }
                    res.data.map((data) => {
                        if (data.field == 'email') {
                            this.errorEmail = data.defaultMessage;
                        }
                        if (data.field == 'password') {
                            this.errorPassword = data.defaultMessage;
                        }
                        if (data.field == 'phone') {
                            this.errorPhone = data.defaultMessage;
                        }
                    })
                })
            } else {
                this.errorPasswordCfm = "Nhập lại mật khẩu không chính xác!"
            }
        },
        clearError() {
            this.error = "";
            this.errorPhone = "";
            this.errorPassword = "";
            this.errorPasswordCfm = "";
            this.errorEmail = "";
            this.error = "";
        }
    },
}
</script>
  