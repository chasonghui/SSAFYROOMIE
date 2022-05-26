<template>
  <b-container class="bv-example-row mt-3">
    <div class="text-center">
      <h3
        class="underline-hotpink text-center"
        style="text-align: center !important"
      >
        <b-icon icon="journals"></b-icon> 게시판
      </h3>
    </div>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "BoardView",
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
