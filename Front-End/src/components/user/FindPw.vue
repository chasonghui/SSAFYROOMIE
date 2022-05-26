<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>비밀번호 찾기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit.prevent="submitForm">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="name"
                required
                placeholder="이름 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="email"
                required
                placeholder="이메일 입력...."
              ></b-form-input>
            </b-form-group>
            <b-col class="text-center">
              <b-button type="submit" variant="primary">비밀번호 찾기</b-button>
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

export default {
  data() {
    return {
      id: "",
      name: "",
      email: "",
    };
  },
  methods: {
    submitForm() {
      console.log(this.id);
      http
        .post("/user/findpw", {
          id: this.id,
          name: this.name,
          email: this.email,
        })
        .then((response) => {
          if (response.data == "") {
            alert("등록되어 있는 회원이 아닙니다.");
          } else {
            alert("회원 님의 비밀번호는 [" + response.data + "] 입니다.");
          }
        })
        .catch(() => {
          alert("등록되어 있는 회원이 아닙니다.");
        });
    },
  },
};
</script>

<style></style>
