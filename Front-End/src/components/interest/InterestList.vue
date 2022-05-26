<template>
  <b-container
    v-if="interests && interests.length != 0"
    class="bv-example-row"
    style="height: 650px; overflow-y: scroll"
  >
    <interest-list-item
      v-for="(interest, index) in interests"
      :key="index"
      :interest="interest"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>관심 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();

import { mapState, mapActions } from "vuex";
const userStore = "userStore";
const interestStore = "interestStore";
import InterestListItem from "@/components/interest/InterestListItem.vue";

export default {
  name: "InterestList",
  components: {
    InterestListItem,
  },
  data() {
    return {
      interests: [],
    };
  },
  created() {
    console.log(this.id);
    http
      .get("/interest/" + this.id)
      .then((response) => {
        console.log("interestList", response.data);
        this.setInterests(response.data);
        this.interests = response.data;
        this.clearInterest();
      })
      .catch(() => {
        alert("정보 불러오기 에러");
      });
  },
  methods: {
    ...mapActions(interestStore, ["setInterests", "clearInterest"]),
  },
  computed: {
    ...mapState(userStore, ["id"]),
  },
};
</script>

<style></style>
