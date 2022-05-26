import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);
import interestStore from "@/store/modules/interestStore.js";
import commentStore from "@/store/modules/commentStore.js";
import boardStore from "@/store/modules/boardStore.js";
import houseStore from "@/store/modules/houseStore.js";
import userStore from "@/store/modules/userStore.js";

const store = new Vuex.Store({
  modules: {
    interestStore,
    commentStore,
    boardStore,
    houseStore,
    userStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
