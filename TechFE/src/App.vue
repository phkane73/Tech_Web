<template>
  <Header v-if="this.$route.path != '/login' && this.$route.path != '/register'"></Header>
  <RouterView />
  <Footer v-if="this.$route.path != '/login' && this.$route.path != '/register'"></Footer>
</template>

<style scoped></style>
<script>
import { RouterLink, RouterView } from "vue-router";
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";

export default {
  name: "App",
  components: {
    Header,
    Footer,
  },
  methods: {
    scroll() {
      window.scroll({
        top: 0
      })
    },
  },
  created() {
    this.scroll();
  },
};
</script>