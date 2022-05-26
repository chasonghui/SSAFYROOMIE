<template>
  <b-container>
    <div style="display: flex; justify-content: space-between">
      <span>
        <span class="m-1">{{ comment.cid }}</span>
        <b-icon icon="lock-fill" v-if="comment.issecret"></b-icon>
      </span>
      <span>
        <span class="m-1">{{ comment.regtime }}</span>
        <a
          href="#"
          @click="deleteComment"
          v-if="commentUser"
          class="m-1"
          style="color: red; font-weight: bold"
          >삭제</a
        >
      </span>
    </div>

    <b-row
      v-if="secretCheck"
      class="m-1 mb-4 rounded"
      style="background-color: rgb(243, 243, 243)"
    >
      <br />
      <b-col class="align-self-center p-3"
        ><h6>{{ comment.content }}</h6></b-col
      >
    </b-row>
    <b-row
      v-else
      class="m-1 mb-3 shadow-sm rounded"
      style="background-color: rgb(243, 243, 243)"
    >
      <br />
      <b-col class="text-center p-3" variant="secondary"
        ><h6 class="mt-1">비밀글 입니다.</h6></b-col
      >
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();

import { mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "CommentListItem",
  data() {
    return {};
  },
  props: {
    comment: Object,
  },
  computed: {
    ...mapState(userStore, ["id"]),
    secretCheck() {
      if (
        !this.comment.issecret ||
        this.id === this.comment.cid ||
        this.id === this.comment.bid
      ) {
        return true;
      } else {
        return false;
      }
    },
    commentUser() {
      if (this.id === this.comment.cid) return true;
      else return false;
    },
  },
  created() {
    console.log("item", this.comment);
  },
  methods: {
    deleteComment() {
      http
        .delete("/comment/" + this.comment.cno)
        .then((response) => {
          console.log("댓글 삭제", response.data);
          this.$parent.setComments();
        })
        .catch((error) => {
          console.log("댓글 삭제 에러", error);
        });
    },
  },
};
</script>

<style></style>
