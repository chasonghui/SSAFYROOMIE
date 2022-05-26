<template>
  <b-container class="bv-example-row mt-3 mb-5">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-form @submit.prevent="submitForm">
      <b-card bg-variant="light">
        <b-form-group label="아이디" label-for="nested-id" label-cols-sm="2">
          <b-form-input
            id="id"
            v-model="user.id"
            type="text"
            @blur="idcheck"
            :state="idValidation"
            required
          ></b-form-input>
          <b-form-valid-feedback :state="idValidation">
            사용 가능한 아이디입니다.
          </b-form-valid-feedback>
        </b-form-group>

        <b-form-group label="이름" label-for="nested-name" label-cols-sm="2">
          <b-form-input id="name" v-model="user.name" required></b-form-input>
        </b-form-group>

        <b-form-group label="비밀번호" label-for="nested-pw" label-cols-sm="2">
          <b-form-input
            type="password"
            id="pw"
            v-model="user.pw"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="비밀번호 확인"
          label-for="nested-pwcheck"
          label-cols-sm="2"
        >
          <b-form-input
            type="password"
            id="pwcheck"
            v-model="pwcheck"
            @blur="passcheck"
            :state="pwValidation"
            required
          ></b-form-input>
          <b-form-valid-feedback :state="pwValidation">
            비밀번호가 일치합니다.
          </b-form-valid-feedback>
        </b-form-group>

        <b-form-group label="이메일" label-for="nested-email" label-cols-sm="2">
          <b-form-input
            type="email"
            id="email"
            v-model="user.email"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="교육 캠퍼스"
          label-for="nested-email"
          label-cols-sm="2"
        >
          <b-form-select
            v-model="user.campus"
            :options="options"
          ></b-form-select>
        </b-form-group>
        <b-button class="m-1" size="lg" type="submit" variant="primary"
          >회원가입</b-button
        >
      </b-card>
    </b-form>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();
export default {
  name: "UserRegister",
  data() {
    return {
      user: {
        id: "",
        pw: "",
        name: "",
        email: "",
        campus: 0,
      },
      pwcheck: "",
      idVaild: false,
      pwVaild: false,
      options: [
        { value: 0, text: "서울" },
        { value: 1, text: "대전" },
        { value: 2, text: "광주" },
        { value: 3, text: "구미" },
        { value: 4, text: "부울경" },
      ],
    };
  },
  methods: {
    idcheck() {
      if (this.user.id == "") {
        return;
      }
      console.log("id확인");
      http
        .get("/user/idcheck/" + this.user.id)
        .then((response) => {
          console.log(response.data);
          if (response.data == 0) {
            this.idVaild = true;
          } else {
            alert("사용할 수 없는 아이디 입니다.");
            this.idVaild = false;
          }
        })
        .catch(() => {
          alert("사용할 수 없는 아이디입니다.");
        });
    },
    passcheck() {
      if (this.user.pw == "") return;
      if (this.user.pw == this.pwcheck) {
        this.pwVaild = true;
      } else {
        this.pwVaild = false;
      }
    },
    submitForm() {
      http
        .post("/user", {
          id: this.user.id,
          pw: this.user.pw,
          name: this.user.name,
          email: this.user.email,
          campus: this.user.campus,
        })
        .then((response) => {
          console.log("response");
          if (response.data === "success") {
            alert("성공적으로 가입 하였습니다. 로그인 하십시오.");
            this.$router.push("/user/signin");
          } else {
            alert("가입을 하지 못했습니다.1");
          }
        })
        .catch(() => {
          alert("가입을 하지 못했습니다.2");
        });
    },
    movePage() {
      this.$router.push({ name: "signIn" });
    },
  },
  computed: {
    idValidation() {
      return this.idVaild;
    },
    pwValidation() {
      return this.pwVaild;
    },
  },
};
</script>

<style></style>
