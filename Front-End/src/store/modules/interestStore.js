const interestStore = {
  namespaced: true,
  state: {
    interest: null,
    interests: [],
  },
  getters: {
    getInterest(state) {
      return state.interest;
    },
    getInterests(state) {
      return state.interests;
    },
  },
  mutations: {
    SET_INTEREST(state, interest) {
      state.interest = interest;
    },
    CLEAR_INTEREST(state) {
      state.interest = null;
    },
    SET_INTERESTS(state, interests) {
      state.interests = interests;
    },
  },
  actions: {
    setInterest({ commit }, interest) {
      commit("SET_INTEREST", interest);
    },
    clearInterest({ commit }) {
      commit("CLEAR_INTEREST");
    },
    setInterests({ commit }, interests) {
      commit("SET_INTERESTS", interests);
    },
  },
};

export default interestStore;
