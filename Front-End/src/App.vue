<template>
  <div id="app" class="bg">
    <navi-bar />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "./components/layout/FooterBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
    FooterBar,
  },
};
</script>

<style>
a {
  text-decoration: none;
}
a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}
</style>
