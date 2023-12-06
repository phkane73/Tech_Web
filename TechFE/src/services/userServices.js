import API from "./API";

export default {
  login(email, password) {
    return API().post("/user/login", { email: email, password: password });
  },
  register(phone, username, address, password, email) {
    return API().post("/user/register", { username: username, email: email, phone: phone, address: address, password: password });
  }
};
