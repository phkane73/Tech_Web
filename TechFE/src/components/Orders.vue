<template>
    <button class="fa-solid fa-receipt" type="button" data-toggle="modal" data-target=".bd-example-modal-lg"
        style="font-size: 27px; margin-right: 15px; position: relative; padding: 0; border: none;">
    </button>
    <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
        aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content p-3">
                <div class="container">
                    <h2 class="text-center">Đơn hàng đã đặt</h2>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card mt-3" v-for="order in orders" :key="order.id">
                                <div class="card-header" style="font-weight: bold;">
                                    Đặt vào {{ order.date }}
                                </div>
                                <div class="card-body d-flex justify-content-between">
                                    <div class="d-flex flex-column">
                                        <span style="font-weight: bold">Thành tiền</span>
                                        <span>
                                            {{
                                                new Intl.NumberFormat()
                                                    .format(order.totalPrice)
                                                    .replaceAll(",", ".")
                                            }}đ
                                        </span>
                                    </div>
                                    <div class="d-flex flex-column">
                                        <span style="font-weight: bold">Gửi đến</span>
                                        <span>
                                            {{ order.address }}
                                        </span>
                                    </div>
                                    <div class="d-flex flex-column">
                                        <span style="font-weight: bold">Trạng thái</span>
                                        <span style="color: red;" v-if="order.status == 0">Đang chờ duyệt</span>
                                        <span style="color: green;" v-if="order.status != 0">Đang giao hàng</span>
                                    </div>
                                </div>
                                <a class="btn btn-primary" data-toggle="collapse"
                                    :href="'#multiCollapseExample1' + order.id" role="button" aria-expanded="false"
                                    aria-controls="multiCollapseExample1">Chi tiết đơn
                                    hàng</a>
                                <div class="row">
                                    <div class="col">
                                        <div class="collapse multi-collapse" :id="'multiCollapseExample1' + order.id">
                                            <div class="card card-body " v-for="product in order.orderDetails"
                                                :key="product.id.productId">
                                                <div class="d-flex justify-content-between">
                                                    <img v-bind:src="'images/' +
                                                        product.product.id +
                                                        '/infor/' +
                                                        product.product.imageProductInfor
                                                        " class="card-img-top p-3"
                                                        style="width: 100px; height: 80px; object-fit: scale-down" />
                                                    <div class="d-flex flex-column">
                                                        <span style="font-weight: bold">Tên sản phẩm:</span>
                                                        <span>{{ product.product.productName }}</span>
                                                    </div>
                                                    <div class="d-flex flex-column">
                                                        <span style="font-weight: bold">Số lượng:</span>
                                                        <span>{{ product.number }}</span>
                                                    </div>
                                                    <div class="d-flex flex-column">
                                                        <span style="font-weight: bold">Số lượng:</span>
                                                        <span>{{ new Intl.NumberFormat()
                                                            .format(product.totalPriceOne)
                                                            .replaceAll(",", ".") }}đ</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>

<script>
import productServices from "../services/productServices";
export default {
    name: "order",
    data: () => ({
        orders: []
    }),
    methods: {
        getOrder() {
            productServices.getOrder(sessionStorage.getItem("userId")).then((res) => {
                this.orders = res.data
            })
        },
    },
    created() {
        this.getOrder()
    },
};
</script>