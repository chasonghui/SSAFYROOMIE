<template>
  <b-container class="bv-example-row mt-3 mb-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-5">
      <b-col>
        <b-card
          :header-html="`<h5>${article.articleno}.
          ${article.title}</h5></br><span><h6>작성자: ${article.userid}</span><span>&nbsp&nbsp&nbsp${article.regtime}</h6></span>`"
          class="mb-2 border"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <!-- 여기 댓글 뷰  -->
    <comment-list />
  </b-container>
</template>

<script>
// import moment from "moment";
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";
const http = apiInstance();
const userStore = userStore;

import CommentList from "./CommentList.vue";

export default {
  components: { CommentList },
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(userStore, ["id"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      if (this.article.userid === this.id) {
        this.$router.replace({
          name: "boardModify",
          params: { articleno: this.article.articleno },
        });
      } else {
        alert("수정 권한이 없습니다.");
      }
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (this.article.userid === this.id && confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boardDelete",
          params: { articleno: this.article.articleno },
        });
      } else {
        alert("삭제 권한이 없습니다.");
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
