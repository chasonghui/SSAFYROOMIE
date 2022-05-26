import { apiInstance } from "@/api/index.js";
const http = apiInstance();

const commentStore = {
  namespaced: true,
  state: {
    comments: [],
  },
  getters: {
    getComments(state) {
      return state.comments;
    },
  },
  mutations: {
    SET_COMMENT_LIST(state, comments) {
      state.comments = comments;
    },
    CLEAR_COMMENT_LIST(state) {
      state.comments = [];
    },
  },
  actions: {
    getCommentList({ commit }, cno) {
      console.log("cno", cno);
      http
        .get(`/comment`, { cno })
        .then(({ data }) => {
          console.log("comment get", data);
          commit("SET_COMMENT_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setCommentList({ commit }) {
      commit("CLEAR_COMMENT_LIST");
    },
  },
};

export default commentStore;
