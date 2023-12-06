<template>
  <main>
    <div class="container">
      <div class="row my-4" style="height: 40px">
        <RouterLink class="link" to="/" style="line-height: 40px">Trang chủ</RouterLink>
        <i class="fa-solid fa-chevron-right" style="line-height: 40px; margin-left: 3px; margin-right: 3px"></i>
        <span style="line-height: 40px; cursor: default">Sản phẩm</span>
      </div>
      <div class="row bg-white category" style="height: 170px; border-radius: 10px">
        <ul class="category-list d-flex">
          <RouterLink to="/products/laptops">
            <li class="category-item">
              <img src="../assets/category_laptop.jpg" alt="" />
              <span class="d-block text-center">Laptop</span>
            </li>
          </RouterLink>
          <RouterLink to="/products/tablets">
            <li class="category-item">
              <img src="../assets/category_tablet.jpg" alt="" />
              <span class="d-block text-center">Tablet</span>
            </li>
          </RouterLink>
          <RouterLink to="/products/smartwatchs">
            <li class="category-item">
              <img src="../assets/category_smartwatch.jpg" alt="" />
              <span class="d-block text-center">SmartWatch</span>
            </li>
          </RouterLink>
          <RouterLink to="/products/earphones">
            <li class="category-item" style="margin-right: 40px">
              <img src="../assets/category_earphone.jpg" style="height: 118px; margin-top: 7px" />
              <span class="d-block text-center" style="margin-top: 5px">Earphone</span>
            </li>
          </RouterLink>
        </ul>
      </div>
      <div class="btn btn-dark mx-5" @click="getPage(0, 'asc')">
        Giá từ thấp đến cao
      </div>
      <div class="btn btn-dark" @click="getPage(0, 'desc')">
        Giá từ cao đến thấp
      </div>
      <div class="row my-5">
        <div class="col-md-3 mt-4" v-for="product in products.content" v-bind:key="product.id">
          <RouterLink :to="'/products/' + product.id">
            <div class="card" style="width: 100%; border-radius: 10px">
              <img v-bind:src="'images/' + product.id + '/infor/' + product.imageProductInfor
                " class="card-img-top p-3" style="
                  width: 100%;
                  height: 200px;
                  object-fit: scale-down;
                  border-top-left-radius: 10px;
                  border-top-right-radius: 10px;
                " />
              <div class="card-body">
                <p>{{ product.productName }}</p>
                <p>
                  {{
                    new Intl.NumberFormat()
                      .format(product.price)
                      .replaceAll(",", ".")
                  }}đ
                </p>
                <p>Hãng: {{ product.brand }}</p>
                <p v-if="product.number != 0 && product.isDeleted == 0">Số lượng còn trong kho: {{ product.number }}</p>
                <p v-else-if="product.isDeleted == 1" style="color: red;">Sản phẩm đã ngưng bán</p>
                <p v-else style="color: red;">Hết hàng</p>
              </div>
            </div>
          </RouterLink>
        </div>
      </div>
      <nav aria-label="Page navigation example" v-if="products.totalPages > 1">
        <ul class="pagination">
          <li class="page-item">
            <button class="page-link text-dark" @click="getPage(products.number - 1)" v-if="products.number > 0">
              <i class="fa-solid fa-angles-left"></i>
            </button>
          </li>
          <li class="page-item">
            <button class="page-link text-dark" @click="getPage(0)">
              First
            </button>
          </li>
          <li class="page-item" v-for="page in products.totalPages - 2" v-bind:key="page">
            <button class="page-link text-dark" @click="getPage(page)">
              {{ page }}
            </button>
          </li>
          <li class="page-item">
            <button class="page-link text-dark" @click="getPage(products.totalPages - 1)">
              Last
            </button>
          </li>
          <li class="page-item">
            <button class="page-link text-dark" @click="getPage(products.number + 1)"
              v-if="products.number < products.totalPages - 1">
              <i class="fa-solid fa-angles-right"></i>
            </button>
          </li>
        </ul>
      </nav>
    </div>
  </main>
</template>

<style scoped>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.card p {
  font-size: 14px;
  display: -webkit-box;
  overflow: hidden;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
}

.category {
  display: inline-block;
}

.card-body {
  padding: 0 20px 20px 20px;
}
</style>

<script>
import productServices from "../services/productServices";
export default {
  name: "Product",
  data() {
    return {
      products: [],
    };
  },
  methods: {
    getPage(page, sort) {
      if (sort == undefined) {
        productServices.getPage(page, "all", "all", "").then((res) => {
          this.products = res.data;
        });
      } else {
        productServices.getPage(page, "all", "all", sort).then((res) => {
          this.products = res.data;
        });
      }
    },
    scroll() {
      window.scroll({
        top: 0
      })
    },
  },
  created() {
    this.getPage(0);
    this.scroll()
  },
};
</script>
