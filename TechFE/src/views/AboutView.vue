<template>
  <div class="container">
    <div class="row">
      <h1 class="pt-4 font-weight-bold mb-4" style="color: #3edbf0; border-bottom: 2px solid;">Về Chúng Tôi
      </h1>
      <div class="d-flex">
        <div class="mr-4">
          <p style="line-height: 1.8; text-align: justify; ">Cửa hàng bán đồ điện tử Tech là một trong những cửa hàng bán
            lẻ đa kênh đồng nhất, chuyên cung cấp các sản phẩm công nghệ mới và nóng nhất. Cửa hàng này cung cấp các sản
            phẩm chính hãng, đảm bảo chất lượng và giá cả phải chăng. Ngoài ra, Tech còn có chính sách bảo hành tốt và
            dịch vụ hậu mãi chuyên nghiệp. Nếu bạn đang tìm kiếm một cửa hàng bán đồ điện tử uy tín và chất lượng, Tech là
            một lựa chọn tuyệt vời. Chúc bạn tìm được sản phẩm ưng ý!</p>
        </div>
        <img src="../assets/about.jpeg" style="width: 35rem; border-radius: 20px; margin-bottom: 50px;">
      </div>
    </div>
    <div id="accordion" class=" mb-5">
      <h1 class="text-center" style="font-weight: 700; ">FAQ</h1>
      <div class="card">
        <div class="card-header" id="headingOne">
          <h5 class="mb-0">
            <button class="btn btn-link " data-toggle="collapse" data-target="#collapseOne" aria-expanded="true"
              aria-controls="collapseOne">
              Chính sách bảo hành của TECH như thế nào?
            </button>
          </h5>
        </div>
        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
          <div class="card-body">
            Bảo hành chính hãng, đổi trả trong vòng 3 tháng.
          </div>
        </div>
      </div>
      <div class="card">
        <div class="card-header" id="headingTwo">
          <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false"
              aria-controls="collapseTwo">
              TECH có bao nhiêu chi nhánh?
            </button>
          </h5>
        </div>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
          <div class="card-body">
            TECH hiện tại có 2 chi nhánh chính:
            <li>Chi nhánh 1: Phường 1, Quận 3, Hồ Chí Minh</li>
            <li>Chi nhánh 2: Xã Tấn Mỹ, Huyện Chợ Mới, An Giang</li>
            <li>Chi nhánh 3: Thị Trấn Thứ Ba, An Biên, Kiên Giang</li>
          </div>
        </div>
      </div>
      <div class="card">
        <div class="card-header" id="headingThree">
          <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree"
              aria-expanded="false" aria-controls="collapseThree">
              TECHcó hoạt động vào những ngày lễ, Tết không?
            </button>
          </h5>
        </div>
        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
          <div class="card-body">
            Tất cả các ngày lễ, TECH luôn mở cửa sẵn sàng đón tiếp tất cả khách hàng vào các khung giờ mở cửa.
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }

  .text-about {
    position: absolute;
    bottom: 40%;
    left: 15%;
    font-size: 70px;
    font-weight: 700;
    color: rgb(0, 96, 88);
    text-decoration: none !important;
    ;
    box-shadow: 10px 10px 10px rgb(39, 39, 39);
    text-shadow: 2px 2px rgb(241, 239, 238);
    padding: 10px;
  }

  .btn-link {
    text-decoration: none !important;
    color: black;
    font-weight: bolder;
  }
}
</style>

<script setup></script>
