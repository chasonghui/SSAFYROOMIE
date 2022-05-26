<template>
  <b-container fluid class="bv-example-row text-center">
    <b-row class="mt-2">
      <b-col cols="3">
        <house-search-bar />
        <router-view />
      </b-col>
      <b-col cols="9">
        <home-map />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import HomeMap from "@/components/map/HomeMap.vue";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";

export default {
  name: "HomeView",
  components: {
    HomeMap,
    HouseSearchBar,
  },
};
</script>
