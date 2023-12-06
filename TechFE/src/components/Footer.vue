<script setup></script>

<template>
  <div class="container-fluid">
    <div
      class="row m-0 banner text-uppercase"
      style="position: relative; cursor: default"
    >
      <h1>Sắp ra mắt</h1>
      <h2>Laptop gaming asus rog</h2>
      <div class="banner-list">
        <h3 class="banner-item1">Mạnh mẽ</h3>
        <h3 class="banner-item2">Thiết kế đột phá</h3>
        <h3 class="banner-item3">Công nghệ mới</h3>
      </div>
      <img
        src="../assets/banner-footer.jpg"
        style="height: 400px; width: 100%"
      />
    </div>
    <div class="container">
      <div class="row footer-top" style="height: 130px">
        <img
          src="../assets/logo.png"
          style="width: 300px; height: 130px; margin: 0 auto"
        />
      </div>
      <div class="row" style="height: 250px; margin-top: 50px">
        <div class="col-md-3 pl-0">
          <h4>Thông tin liên hệ</h4>
          <ul class="p-0">
            <li>
              <i class="fa-solid fa-location-dot"></i>
              <span> KTX B Đại Học Cần Thơ</span>
            </li>
            <li>
              <i class="fa-solid fa-phone"></i>
              <span> 0857487577</span>
            </li>
            <li>
              <i class="fa-solid fa-envelope"></i>
              <span> phkane732002@gmail.com</span>
            </li>
          </ul>
        </div>
        <div class="col-md-3">
          <h4>Chính sách</h4>
          <ul class="p-0">
            <li>Chính sách bảo hành</li>
            <li>Chính sách bảo mật</li>
            <li>Chính sách đổi trả</li>
            <li>Mua hàng trả góp</li>
          </ul>
        </div>
        <div class="col-md-3">
          <h4>Hướng dẫn</h4>
          <ul class="p-0">
            <li>Hướng dẫn mua hàng</li>
            <li>Hướng dẫn thanh toán</li>
            <li>Hương dẫn nhận hàng</li>
            <li>Hướng dẫn dịch vụ</li>
          </ul>
        </div>
        <div class="col-md-3">
          <h4>Mạng xã hội</h4>
          <ul class="p-0 m-0 d-flex flex-wrap">
            <li>
              <img
                src="../assets/footer-facebook.png"
                style="width: 50px; height: auto; margin-right: 16px"
              />
            </li>
            <li>
              <img
                src="../assets/footer-itg.png"
                style="width: 50px; height: auto; margin-right: 16px"
              />
            </li>
            <li>
              <img
                src="../assets/footer-ytb.png"
                style="width: 50px; height: auto; margin-right: 16px"
              />
            </li>
            <li>
              <img
                src="../assets/footer-tiktok.png"
                style="width: 50px; height: auto"
              />
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.footer-top {
  border-bottom: 2px black solid;
}
.banner h1 {
  position: absolute;
  color: #3edbf0;
  top: 150px;
  left: 350px;
  font-size: 70px;
  font-weight: 700;
  transition: all 0.25s linear;
}
.banner h2 {
  position: absolute;
  color: white;
  top: 250px;
  left: -430px;
  transition: all 0.5s linear;
  font-weight: 600;
}
.banner-list {
  color: currentColor;
  z-index: -1;
  transition: all 0.5s linear;
  font-size: 30px;
}
.banner:hover h2 {
  text-shadow: 3px 5px rgb(186, 17, 17);
  left: 250px;
}
.banner:hover .banner-list {
  z-index: 10;
  color: rgb(186, 17, 17);
  text-shadow: 3px 5px rgb(0, 0, 0);
}
.banner:hover h1 {
  left: 100px;
  text-shadow: 3px 5px rgb(186, 17, 17);
}
.banner-item1 {
  position: absolute;
  top: 27px;
  right: 680px;
  font-weight: 700;
}
.banner-item2 {
  position: absolute;
  bottom: 50px;
  right: 50px;
  font-weight: 700;
}
.banner-item3 {
  position: absolute;
  top: 40px;
  right: 50px;
  font-weight: 700;
}

.col-md-3 h4 {
  color: black;
  text-transform: uppercase;
  font-weight: 700;
  margin-bottom: 30px;
}
.col-md-3 li {
  margin-bottom: 15px;
}
.col-md-3 a i {
  margin-left: 50px;
  font-size: 40px;
}
</style>
