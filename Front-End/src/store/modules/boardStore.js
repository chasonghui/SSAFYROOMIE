const boardStore = {
  namespaced: true,
  state: {
    category: "",
  },
  mutations: {
    SET_CATEGORY(state, category) {
      state.category = category;
    },
  },
  actions: {
    setCampus({ commit }, category) {
      commit("SET_CATEGORY", category);
    },
  },
};

export default boardStore;
