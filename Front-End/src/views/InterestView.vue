<template>
  <b-container fluid class="bv-example-row text-center">
    <b-row class="mt-5">
      <b-col cols="3">
        <h2 class="underline-hotpink">
          <b-icon icon="house"></b-icon> 관심 목록
        </h2>
        <router-view />
      </b-col>
      <b-col cols="9">
        <interest-map />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import InterestMap from "@/components/map/InterestMap.vue";

export default {
  name: "InterestView",
  components: {
    InterestMap,
  },
  created() {
    //alert("관심");
  },
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 34, 239, 0.3) 30%
  );
}
</style>
