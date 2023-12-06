import API from "./API";

export default {
  getAllProduct() {
    return API().get("/products");
  },
  getPage(page, brand, category, sort) {
    return API().get("/products/page?", {
      params: { p: page, brand: brand, category: category, sort: sort },
    });
  },
  getProduct(id) {
    return API().get("/products/product?", {
      params: { id: id },
    });
  },
  getCart(cart_id) {
    return API().get("/products/allcart", {
      params: { cart_id: cart_id }
    })
  },
  addToCart(cart_id, product_id, date) {
    return API().get("/products/addtocart", {
      params: { cart_id: cart_id, product_id: product_id, date: date }
    })
  },
  deleteCartDetail(cart_id, product_id) {
    return API().delete("/products/deletecartdetail", {
      params: { cart_id: cart_id, product_id: product_id }
    })
  },
  numberCartDetail(cart_id) {
    return API().get("/products/getnumbercart", {
      params: { cart_id: cart_id }
    })
  },
  increaseNumber(cart_id, product_id) {
    return API().get("/products/increase", {
      params: { cart_id: cart_id, product_id: product_id }
    })
  },
  decreaseNumber(cart_id, product_id) {
    return API().get("/products/decrease", {
      params: { cart_id: cart_id, product_id: product_id }
    })
  },
  totalPriceByCart(cart_id) {
    return API().get("/products/totalpricebycart", {
      params: { cart_id: cart_id }
    })
  },
  addToOrder(user_id, totalprice, date, address, products) {
    return API().get("/products/addtoorder", {
      params: {
        user_id: user_id,
        totalprice: totalprice,
        date: date,
        address: address,
        products: products
      }
    })
  },
  getOrder(user_id) {
    return API().get("/products/orders", { params: { user_id: user_id } })
  }
};
