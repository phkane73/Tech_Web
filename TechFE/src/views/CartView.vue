<template>
  <div class="container" style="min-height: 600px">
    <div class="row">
      <div class="col-md-8 my-3">
        <h3 class="text-center text-uppercase font-weight-bold">Giỏ Hàng</h3>
        <div v-if="length == 0" style="
            background-color: white;
            height: 500px;
            display: flex;
            justify-content: center;
          ">
          <img src="../assets/empty_cart.png" />
        </div>
        <div class="card my-2" v-for="product in products" v-bind:key="product.id">
          <div v-for="cart in product.cartDetails" v-bind:key="product.cartDetails.id">
            <div v-if="cart.id.cartId == cartId && cart.id.productId == product.id">
              <div class="card-header d-flex justify-content-between align-items-center" style="height: 48px">
                <span style="font-weight: bold">{{ product.productName }}</span>
                <button class="btn btn-danger" @click="deleteCartDetail(product.id)">
                  <i style="font-size: 15px" class="fa-solid fa-xmark"></i>
                </button>
              </div>

              <div class="card-body d-flex justify-content-between align-items-center">
                <RouterLink :to="'products/' + product.id">
                  <img v-bind:src="'images/' +
                    product.id +
                    '/infor/' +
                    product.imageProductInfor
                    " class="card-img-top p-3" style="width: 150px; height: 100px; object-fit: scale-down" />
                </RouterLink>
                <div class="d-flex flex-column">
                  <span style="font-weight: bold">Đơn giá</span>
                  <span>
                    {{
                      new Intl.NumberFormat()
                        .format(product.price)
                        .replaceAll(",", ".")
                    }}đ
                  </span>
                </div>
                <div class="d-flex flex-column">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <button class="btn btn-outline-secondary" type="button" @click="minusNumber(product.id)" value="-">
                        <i class="fa-solid fa-minus"></i>
                      </button>
                    </div>
                    <div class="input-group-prepend">
                      <input type="number" :value="cart.numberCart" style="
                          max-width: 50px;
                          text-align: center;
                          border: none;
                        " disabled />
                    </div>
                    <div class="input-group-append m-0">
                      <button class="btn btn-outline-secondary" type="button" @click="plusNumber(product.id)">
                        <i class="fa-solid fa-plus"></i>
                      </button>
                    </div>
                  </div>
                </div>
                <div class="d-flex flex-column">
                  <span style="font-weight: bold">Thành tiền</span>
                  <span>
                    {{
                      new Intl.NumberFormat()
                        .format(cart.totalPrice)
                        .replaceAll(",", ".")
                    }}đ
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-4" style="margin-top: 58px">
        <div class="row card d-flex justify-content-center align-items-center bg-dark text-white"
          style="background-color: black; height: 48px; font-weight: bold">
          <span>Thanh toán</span>
        </div>
        <div class="row mt-3 card p-3" style="font-size: 14px">
          <div class="d-flex justify-content-between mt-2">
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroup-sizing-default">Địa chỉ</span>
              </div>
              <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
                v-model="address">
            </div>
            <!-- <span>Địa chỉ</span>
            <input type="text" class="form-control" style="width: 250px;" v-model="address"> -->
          </div>
          <div class="d-flex justify-content-between mt-2">
            <span>Tổng tạm tính</span>
            <span>{{ new Intl.NumberFormat()
              .format(totalPriceByCart)
              .replaceAll(",", ".") }} đ</span>
          </div>
          <div class="d-flex justify-content-between mt-2">
            <span>Thành tiền (Đã bao gồm VAT)</span>
            <span style="color: red;">{{ new Intl.NumberFormat()
              .format(totalPriceByCart + (totalPriceByCart * 5) / 100)
              .replaceAll(",", ".") }} đ</span>
          </div>
        </div>
        <div class="row d-flex align-items-center">
          <button type="button" class="btn my-2 p-3 buy w-100 text-light" @click="addToOrder()"
            style="cursor: pointer; background-color: #1535c1; font-weight: bold; text-transform: uppercase;">Đặt
            hàng</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

<script>
import productServices from "../services/productServices";
import moment from 'moment';
export default {
  name: "cart",
  data: () => ({
    products: [],
    cartId: sessionStorage.getItem("cartId"),
    length: 0,
    totalPriceByCart: 0,
    address: sessionStorage.getItem("address"),
    date: moment().format('DD-MM-YYYY HH:mm:ss'),
  }),
  methods: {

    getProduct() {
      productServices.getCart(this.cartId).then((res) => {
        this.products = res.data;
        this.length = res.data.length;
      });
    },
    deleteCartDetail(productId) {
      productServices.deleteCartDetail(this.cartId, productId).then((res) => {
        this.getProduct();
        this.getTotalPriceByCart()
      });
    },
    minusNumber(productId) {
      productServices.decreaseNumber(this.cartId, productId).then((res) => {
        this.getProduct();
        this.getTotalPriceByCart()
      });
    },
    plusNumber(productId) {
      productServices.increaseNumber(this.cartId, productId).then((res) => {
        this.getProduct();
        this.getTotalPriceByCart()
      });
    },
    getTotalPriceByCart() {
      productServices.totalPriceByCart(this.cartId).then((res) => {
        this.totalPriceByCart = res.data
      }
      )
    },
    addToOrder() {
      productServices.addToOrder(sessionStorage.getItem("userId"), (this.totalPriceByCart + (this.totalPriceByCart * 5) / 100), this.date, this.address, this.products).then((res) => {
        alert("Bạn đã đặt hàng thành công!")
        window.location.reload()
      });
    }
  },
  created() {
    this.getProduct();
    this.getTotalPriceByCart()
  },
};
</script>
