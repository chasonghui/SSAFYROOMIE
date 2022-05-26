<template>
  <b-container class="bv-example-row mt-3">
    <b-row> </b-row>
    <form class="form-inline" role="form" style="justify-content: center">
      <b-form-select
        v-model="selected"
        :options="options"
        class="mb-3 mr-1"
        style="width: auto"
        value-field="item"
        text-field="name"
        disabled-field="notEnabled"
        @change="onChange($event)"
      ></b-form-select>
      <b-form-input
        id="search"
        type="text"
        class="mb-3 mr-1"
        v-model="searchKeyword"
        style="width: auto"
      ></b-form-input>
      <b-button
        variant="outline-primary"
        class="mb-3 mr-1"
        @click="searchBoard()"
        >검색</b-button
      >
      <b-button variant="outline-primary" class="mb-3" @click="moveWrite()"
        >글쓰기</b-button
      >
    </form>
    <b-row class="mb-5">
      <b-col v-if="articles.length">
        <b-table
          class="text-center"
          head-variant="dark"
          hover
          :items="articles"
          :per-page="perPage"
          :current-page="currentPage"
          id="my-table"
          @row-clicked="rowClick"
          :fields="fields"
        ></b-table>
        <b-pagination
          v-model="currentPage"
          :total-rows="articles.length"
          :per-page="perPage"
          aria-controls="my-table"
          align="center"
        ></b-pagination>
      </b-col>
      <b-container v-else class="bv-example-row mt-3">
        <b-row>
          <b-col><b-alert show>글 목록이 없습니다.</b-alert></b-col>
        </b-row>
      </b-container>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();

import { mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "BoardList",
  data() {
    return {
      currentPage: 1,
      perPage: 10,
      searchKeyword: "",
      selected: "all",
      articles: [],
      options: [
        { item: "all", name: "전체보기" },
        { item: "free", name: "자유게시판" },
        { item: "get", name: "방 구해요" },
        { item: "sell", name: "방 내놔요" },
        { item: "mate", name: "룸메이트 찾기" },
      ],
      fields: [
        {
          key: "articleno",
          label: "글번호",
        },
        {
          key: "title",
          label: "제목",
        },
        {
          key: "userid",
          label: "글쓴이",
        },
        {
          key: "regtime",
          label: "작성일",
        },
      ],
    };
  },
  created() {
    console.log("boardlist created");
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
      console.log(this.articles);
    });
  },
  methods: {
    moveWrite() {
      if (this.id == "") {
        alert("로그인이 필요한 서비스입니다.");
        return;
      }
      this.$router.push({ name: "boardRegister" });
    },
    onChange(event) {
      console.log("option: " + event);
      document.getElementById("search").value = "";
      if (event === "all") {
        http.get(`/board`).then(({ data }) => {
          this.articles = data;
          console.log(this.articles);
        });
      } else {
        http.get("/board/category/" + event).then(({ data }) => {
          this.articles = data;
          console.log(this.articles);
        });
      }
    },
    searchBoard() {
      console.log("cate: " + this.selected + "  word: " + this.searchKeyword);
      if (this.searchKeyword === "") {
        alert("검색어를 입력하세요");
        return;
      } else if (this.selected === "all") {
        http.get(`/board/search/${this.searchKeyword}`).then(({ data }) => {
          this.articles = data;
          console.log("글:", this.articles);
        });
      } else {
        http
          .get(`/board/search/${this.selected}/${this.searchKeyword}`)
          .then(({ data }) => {
            this.articles = data;
            console.log("글:", this.articles);
          });
      }
    },
    rowClick(item) {
      console.log(item.articleno);
      this.$router.push({
        name: "boardDetail",
        params: { articleno: item.articleno },
      });
    },
  },
  computed: {
    ...mapState(userStore, ["id"]),
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
