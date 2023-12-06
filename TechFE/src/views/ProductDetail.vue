<template>
  <div class="container">
    <div v-if="message" class="alert alert-success alert-dismissible offset-5" style="position: absolute; left: 0">
      <a href="#" class="close" data-dismiss="alert" aria-label="close" @click="clear()">&times;</a>
      <strong><i class="fa-solid fa-circle-check text-success"></i></strong>
      {{ message }}
    </div>
    <div class="row my-1" style="height: 40px">
      <RouterLink class="link" to="/" style="line-height: 40px">Trang chủ</RouterLink>
      <i class="fa-solid fa-chevron-right" style="line-height: 40px; margin-left: 3px; margin-right: 3px"></i>
      <RouterLink class="link" to="/products" style="line-height: 40px">Sản phẩm</RouterLink>
      <i class="fa-solid fa-chevron-right" style="line-height: 40px; margin-left: 3px; margin-right: 3px"></i>
      <RouterLink class="link" :to="'/products/' + product.categoryName + 's'"
        style="line-height: 40px; text-transform: capitalize">{{ product.categoryName }}</RouterLink>
      <i class="fa-solid fa-chevron-right" style="line-height: 40px; margin-left: 3px; margin-right: 3px"></i>
      <span style="line-height: 40px; cursor: default">Chi tiết sản phẩm</span>
    </div>
    <div class="row">
      <h3 class="product-name w-100">{{ product.productName }}</h3>
      <div class="col-md-8 p-0">
        <div>
          <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" v-bind:data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" v-bind:data-slide-to="n"
                v-for="n in product.productImages.length - 1" v-bind:key="n" class=""></li>
            </ol>
            <div class="carousel-inner" style="border: 2px solid white; border-radius: 5px">
              <div class="carousel-item active">
                <img class="d-block w-100" v-bind:src="'/images/' +
                  product.id +
                  '/' +
                  product.productImages[0].image
                  " style="height: 430px; border-radius: 5px" />
              </div>
              <div class="carousel-item" v-for="n in product.productImages.length - 1" v-bind:key="n">
                <img class="d-block w-100" v-bind:src="'/images/' +
                  product.id +
                  '/' +
                  product.productImages[n].image
                  " style="height: 430px; border-radius: 5px" />
              </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <img src="../assets/product-detail-img-best-price.png" style="height: 113px; margin: 0 30px" />
        <h2 style="
            color: red;
            font-weight: 700;
            position: relative;
            margin-bottom: 20px;
            margin-left: 82px;
          ">
          {{
            new Intl.NumberFormat().format(product.price).replaceAll(",", ".")
          }}
          <i class="fa-solid fa-dong-sign" style="font-size: 20px; position: absolute; top: 3px"></i>
        </h2>
        <h6>Bảo hành chính hãng {{ product.categoryName }} 1 năm</h6>
        <h6>Hãng sản xuất: {{ product.brand }}</h6>
        <h6 style="margin-bottom: 33px;">Số lượng trong kho: {{ product.number }}</h6>
        <a href="#configure" class="btn btn-primary my-3 w-100" style="padding: 12px; text-transform: uppercase;">Chi tiết
          cấu hình sản phẩm</a>
        <div class="btn btn-danger d-block disabled" v-if="product.isDeleted == 1"
          style="padding: 12px; font-weight: bold; text-transform: uppercase; margin-top: 20px;">
          Sản phẩm đã ngưng bán
        </div>
        <div class="btn btn-danger d-block" v-if="number != 0 && product.isDeleted == 0"
          style="padding: 12px; font-weight: bold; text-transform: uppercase; margin-top: 20px;" @click="addToCart()">
          Thêm sản phẩm vào giỏ hàng
        </div>
        <div class="btn btn-danger d-block disabled" v-if="number == 0 && product.isDeleted == 0"
          style="padding: 12px; font-weight: bold; text-transform: uppercase; margin-top: 20px;">
          Sản phẩm này tạm hết hàng!
        </div>
      </div>
    </div>
    <div class="row my-5" id="configure">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <h2 class="text-center">Cấu hình chi tiết của</h2>
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col"></th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="product.configure.cpu != null">
              <td>CPU:</td>
              <td>{{ product.configure.cpu }}</td>
            </tr>
            <tr v-if="product.configure.ram != null">
              <td>Ram:</td>
              <td>{{ product.configure.ram }}</td>
            </tr>
            <tr v-if="product.configure.chip != null">
              <td>Chip:</td>
              <td>{{ product.configure.chip }}</td>
            </tr>
            <tr v-if="product.configure.diskSpace != null">
              <td>Dung lượng lưu trữ:</td>
              <td>{{ product.configure.diskSpace }}</td>
            </tr>
            <tr v-if="product.configure.hardDrive != null">
              <td>Ổ cứng:</td>
              <td>{{ product.configure.hardDrive }}</td>
            </tr>
            <tr v-if="product.configure.screen != null">
              <td>Màn hình:</td>
              <td>{{ product.configure.screen }}</td>
            </tr>
            <tr v-if="product.configure.screenCard != null">
              <td>Card màn hình:</td>
              <td>{{ product.configure.screenCard }}</td>
            </tr>
            <tr v-if="product.configure.os != null">
              <td>Hệ điều hành:</td>
              <td>{{ product.configure.os }}</td>
            </tr>
            <tr v-if="product.configure.sim != null">
              <td>Khả năng gắn sim:</td>
              <td>{{ product.configure.sim }}</td>
            </tr>
            <tr v-if="product.configure.frontCamera != null">
              <td>Camera trước:</td>
              <td>{{ product.configure.frontCamera }}</td>
            </tr>
            <tr v-if="product.configure.backCamera != null">
              <td>Camera sau:</td>
              <td>{{ product.configure.backCamera }}</td>
            </tr>
            <tr v-if="product.configure.chargePort != null">
              <td>Cổng sạc:</td>
              <td>{{ product.configure.chargePort }}</td>
            </tr>
            <tr v-if="product.configure.connectWithOS != null">
              <td>Có thể kết nối với hệ điều hành:</td>
              <td>{{ product.configure.connectWithOS }}</td>
            </tr>
            <tr v-if="product.configure.connectSupport != null">
              <td>Hỗ trợ kết nối:</td>
              <td>{{ product.configure.connectSupport }}</td>
            </tr>
            <tr v-if="product.configure.timeContinuousUse != null">
              <td>Thời gian sử dụng liên tục:</td>
              <td>{{ product.configure.timeContinuousUse }}</td>
            </tr>
            <tr v-if="product.configure.timeEarPhone != null">
              <td>Thời gian tai nghe:</td>
              <td>{{ product.configure.timeEarPhone }}</td>
            </tr>
            <tr v-if="product.configure.timeChargeBox != null">
              <td>Thời gian hộp sạc:</td>
              <td>{{ product.configure.timeChargeBox }}</td>
            </tr>
            <tr v-if="product.configure.weight != null">
              <td>Khối lượng:</td>
              <td>{{ product.configure.weight }}</td>
            </tr>
            <tr v-if="product.configure.pin != null">
              <td>Dung lượng pin:</td>
              <td>{{ product.configure.pin }}</td>
            </tr>
            <tr v-if="product.configure.clockFace != null">
              <td>Mặt đồng hồ:</td>
              <td>{{ product.configure.clockFace }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</template>

<style scoped>
.product-name {
  display: -webkit-box;
  overflow: hidden;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
}
</style>
<script>
import productServices from "../services/productServices";
import moment from 'moment';
export default {
  data() {
    return {
      product: [],
      message: "",
      number: 0,
      date: moment().format('DD-MM-YYYY HH:mm:ss'),
    };
  },
  methods: {
    async getProduct(id) {
      await productServices.getProduct(id).then((res) => {
        this.product = res.data;
        this.number = res.data.number;
      });
    },
    async addToCart() {
      if (sessionStorage.getItem("cartId") != null) {
        await productServices
          .addToCart(sessionStorage.getItem("cartId"), this.$route.params.id, this.date)
          .then((res) => {
            this.message = res.data;
          })
      } else {
        this.$router.push("/login")
      }
    },
    clear() {
      this.message = ""
    },
    scroll() {
      window.scroll({
        top: 0
      })
    },
  },
  created() {
    this.getProduct(this.$route.params.id);
    this.scroll();
  },
};
</script>
