const userStore = {
  namespaced: true,
  state: {
    id: "",
    campus: 5,
  },
  getters: {
    getId(state) {
      return state.id;
    },
  },
  mutations: {
    LOGIN(state, id) {
      state.id = id;
    },
    LOGOUT(state) {
      state.id = "";
    },
    SET_CAMPUS(state, campus) {
      state.campus = campus;
    },
  },
  actions: {
    login({ commit }, id) {
      commit("LOGIN", id);
    },
    logout({ commit }) {
      commit("LOGOUT");
    },
    setCampus({ commit }, campus) {
      commit("SET_CAMPUS", campus);
    },
  },
};

export default userStore;
