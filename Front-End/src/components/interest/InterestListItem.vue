<template>
  <b-row
    class="m-1 mb-4 shadow-sm rounded"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    @click="selectInterest"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center p-2">
      <b-icon icon="house-fill" font-scale="3"></b-icon>
    </b-col>
    <b-col cols="10" class="text-left align-self-start p-2">
      <h6 class="font-weight-bold">{{ interest.aptName }}</h6>
      <h6>
        거래금액 :
        {{ (parseInt(interest.recentPrice.replace(",", "")) * 10000) | price }}
        ₩
      </h6>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";
const interestStore = "interestStore";

export default {
  name: "InterestListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    interest: Object,
  },
  methods: {
    ...mapActions(interestStore, ["setInterest"]),
    selectInterest() {
      console.log("listRow : ", this.interest);
      this.setInterest(this.interest);
      this.$router.push({ name: "interestDetail" });
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: #e2e2e29f;
}
</style>
