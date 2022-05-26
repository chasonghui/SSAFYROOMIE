<template>
  <b-container style="margin-bottom: 300px">
    <b-row>
      <b-col></b-col>
      <div>
        <b-col
          style="
            text-align: center;
            margin-top: 200px;
            padding-right: 0;
            padding-left: 0;
          "
        >
          <div style="display: flex; justify-content: center">
            <img
              src="@/assets/houseicon.png"
              width="150px"
              height="150px"
              style="margin-bottom: 5px"
            />
            <div
              style="
                flex-direction: column;
                justify-content: center;
                margin-top: 15px;
              "
            >
              <MARQUEE class="titletxt">SSAFY<br />ROOMIE</MARQUEE>
            </div>
          </div>
          <b-col></b-col>
          <form style="width: 300px" @submit.prevent="searchData">
            <b-form-input
              type="text"
              v-model="text"
              placeholder="아파트 이름을 검색해보세요"
              class="text-center shadow"
            ></b-form-input>
            <!-- <b-button type="submit">검색</b-button> -->
          </form>
          <b-col></b-col>
        </b-col>
      </div>
      <b-col></b-col>
    </b-row>

    <div class="ssafy-search-result">
      <div>
        <div v-if="houses.length" class="search-apt text-center mb-5">
          <b-button
            style="width: 100%; display: flex; justify-content: space-between"
            variant="primary"
            class="happyhouse-search"
            v-b-toggle.table
            >아파트 정보 검색결과<b-icon icon="chevron-expand" font-scale="1" />
          </b-button>
          <b-collapse id="table">
            <b-table
              hover
              id="my-table"
              :items="houses"
              :per-page="perPage"
              :current-page="currentPage"
              @row-clicked="rowClick"
              :fields="fields"
              p-2
              small
            ></b-table>
            <b-pagination
              v-model="currentPage"
              :total-rows="houses.length"
              :per-page="perPage"
              aria-controls="my-table"
              align="center"
            ></b-pagination>
          </b-collapse>
        </div>
      </div>
      <div>
        <div class="search-list-group" v-if="searches.length">
          <b-button
            style="width: 100%; display: flex; justify-content: space-between"
            variant="primary"
            class="happyhouse-search mb-3"
            v-b-toggle.news
            >아파트 뉴스 검색결과<b-icon icon="chevron-expand" font-scale="1"
          /></b-button>
          <b-collapse id="news">
            <b-list-group v-for="(search, index) in searches" :key="index">
              <b-list-group-item style="margin-bottom: 10px">
                <h6 class="search-member">{{ search.title }}</h6>
                <b-button
                  pill
                  variant="outline-light"
                  class="search-member-pop-over"
                  :href="search.url"
                  target="_blank"
                  v-b-popover.hover.right="search.datetime"
                  :title="`${search.contents}`"
                >
                  <b-icon icon="book" variant="secondary"></b-icon>
                </b-button>
              </b-list-group-item>
            </b-list-group>
          </b-collapse>
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
import { kakaoFetch, houseDealSearchList } from "@/api/index.js";
import { mapActions } from "vuex";
const houseStore = "houseStore";
export default {
  name: "AptNewsView",

  data() {
    return {
      text: "",
      searches: [],
      houses: [],
      perPage: 10,
      currentPage: 1,
      fields: [
        {
          key: "aptName",
          label: "아파트명",
        },
        {
          key: "sidoName",
          label: "시/도",
        },
        {
          key: "gugunName",
          label: "구/군",
        },
        {
          key: "dongName",
          label: "동",
        },
      ],
    };
  },

  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    async searchData() {
      console.log("searchData");
      this.searches = [];
      this.houses = [];
      try {
        const inskaka = kakaoFetch(this.text + "아파트");
        const res = await inskaka.get();
        res.data.documents.forEach((search) => {
          search.title = search.title.replace(/<br\/>/gi, "\n");
          search.title = search.title.replace(
            /<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/gi,
            "",
          );
          search.title = search.title.replace(/(<([^>]+)>)/gi, "");
          search.title = search.title.replace(/&gt;/gi, "");
          search.title = search.title.replace(/&lt;/gi, "");
          search.title = search.title.replace(/&nbsp;/gi, "");
          search.title = search.title.replace(/&#39;/gi, "");
          search.title = search.title.replace(/&#34;/gi, "");
          search.contents = search.contents.replace(/<br\/>/gi, "\n");
          search.contents = search.contents.replace(
            /<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/gi,
            "",
          );
          search.contents = search.contents.replace(/(<([^>]+)>)/gi, "");
          search.contents = search.contents.replace(/&nbsp;/gi, "");
          search.contents = search.contents.replace(/&gt;/gi, "");
          search.contents = search.contents.replace(/&lt;/gi, "");
          search.contents = search.contents.replace(/&#39;/gi, "");
          search.contents = search.contents.replace(/&#34;/gi, "");
          search.datetime = search.datetime.substr(0, 10);
          this.searches.push(search);
          console.log(search.datetime);
        });
      } catch (error) {
        console.log(error);
      }
      let params = {
        aptName: this.text,
      };
      houseDealSearchList(
        params,
        ({ data }) => {
          this.houses = data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    rowClick(item) {
      console.log("item", item);
      this.detailHouse(item);
      this.$router.push({ name: "houseDetail" });
    },
  },
};
</script>

<style scoped>
.ssafy-search-result {
  margin-top: 5%;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
.search-member {
  display: inline;
}
.search-member-pop-over {
  float: right;
}
.happyhouse-search {
  text-align: left;
  background-color: #cce5ff;
  border: 0;
  color: #003382;
  padding: 20px;
  font-weight: bold;
}
.titletxt {
  font-weight: bold;
  font-size: xx-large;
  color: #003382;
}
</style>
