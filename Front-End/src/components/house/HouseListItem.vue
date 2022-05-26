<template>
  <b-row
    class="m-1 mb-4 shadow-sm rounded"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    @click="selectHouse"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center p-2">
      <b-icon icon="house-fill" font-scale="3"></b-icon>
    </b-col>
    <b-col cols="10" class="text-left align-self-start p-2">
      <h6 class="font-weight-bold">{{ house.aptName }}</h6>
      <h6>
        거래금액 :
        {{ (parseInt(house.recentPrice.replace(",", "")) * 10000) | price }} ₩
      </h6>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
      this.$router.push({ name: "houseDetail" });
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
