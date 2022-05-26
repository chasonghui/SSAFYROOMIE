<template>
  <b-container class="bv-example-row mt-3">
    <hr class="mb-5" />
    <comment-list-item
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
    />
    <b-row class="mb-1 mt-5">
      <b-col style="padding: 0 !important">
        <b-form @submit.prevent="commentSubmit">
          <b-card
            class="mb-2"
            no-body
            style="border: none; padding: 0 !important"
          >
            <b-card-body style="padding: 0 !important">
              <hr class="mb-5" />
              <b-form-group>
                <b-form-textarea
                  v-model="content"
                  placeholder="댓글을 입력하세요."
                  required
                  style="width: 100%"
                ></b-form-textarea>
                <b-col class="text-right">
                  <b-row style="justify-content: right">
                    <b-form-checkbox v-model="isSecret" class="mr-2 mt-2"
                      >비밀글</b-form-checkbox
                    >
                    <b-button type="submit" variant="primary" class="mt-1"
                      >등록</b-button
                    >
                  </b-row>
                </b-col>
              </b-form-group>
              <hr class="mt-5" />
            </b-card-body>
          </b-card>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import CommentListItem from "@/components/board/item/CommentListItem";
import { apiInstance } from "@/api/index.js";
const http = apiInstance();

import { mapState, mapActions } from "vuex";
const userStore = "userStore";
const commentStore = "commentStore";
export default {
  name: "CommentList",
  components: {
    CommentListItem,
  },
  data() {
    return {
      content: "",
      isSecret: false,
      comments: [],
      ano: this.$route.params.articleno,
    };
  },
  computed: {
    ...mapState(userStore, ["id"]),
    //...mapState(commentStore, ["comments"]),
  },
  created() {
    console.log("comment1", this.ano);
    http.get("/comment/" + this.$route.params.articleno).then(({ data }) => {
      this.comments = data;
      console.log("data", this.comments);
    });
  },
  methods: {
    ...mapActions(commentStore, ["getCommentList"]),
    commentSubmit() {
      console.log("cn1", this.ano);
      http
        .post("/comment", {
          ano: this.ano,
          cid: this.id,
          content: this.content,
          issecret: this.isSecret,
        })
        .then((response) => {
          console.log(response.data);
          this.content = "";
          this.setComments();
        })
        .catch(() => {
          console.log("댓글 등록 실패");
        });
    },
    setComments() {
      http.get("/comment/" + this.$route.params.articleno).then(({ data }) => {
        this.comments = data;
        console.log("data", this.comments);
      });
    },
  },
};
</script>

<style></style>
