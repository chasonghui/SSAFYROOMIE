<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디" label-for="id">
              <b-form-input
                id="id"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="pw">
              <b-form-input
                type="password"
                id="pw"
                v-model="user.pw"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-col class="text-center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="loginForm"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="outline-primary"
                class="m-1"
                @click="moveToFind"
                >비밀번호 찾기</b-button
              >
            </b-col>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();
import { mapActions } from "vuex";
const userStore = "userStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: "",
        pw: "",
      },
    };
  },
  methods: {
    ...mapActions(userStore, ["login", "setCampus"]),
    loginForm() {
      if (this.user.id != null && this.user.pw != null) {
        http
          .post("/user/login", {
            id: this.user.id,
            pw: this.user.pw,
          })
          .then((response) => {
            console.log("response: ", response);
            if (response.data == "") {
              alert("아이디/패스워드를 확인해주세요.");
            } else {
              //  console.log("profile: ", this.profile);
              console.log("로그인 성공.");
              this.login(this.user.id);
              this.setCampus(response.data.campus);
              this.$router.push("/");
            }
          })
          .catch(() => {
            console.log("로그인 실패.");
          });
      } else alert("아이디/비밀번호 입력하세요!");
    },
    confirm() {
      alert("로그인!!!");
    },
    moveToFind() {
      this.$router.push("/user/findPw");
    },
  },
};
</script>

<style></style>
