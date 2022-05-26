<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>나의 회원정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-form @submit.prevent="submitForm">
      <b-card bg-variant="light">
        <b-form-group label="아이디" label-for="nested-id" label-cols-sm="2">
          <b-form-input id="id" v-model="user.id" readonly></b-form-input>
        </b-form-group>

        <b-form-group label="이름" label-for="nested-name" label-cols-sm="2">
          <b-form-input id="name" v-model="user.name" required></b-form-input>
        </b-form-group>

        <b-form-group label="비밀번호" label-for="nested-pw" label-cols-sm="2">
          <b-form-input
            type="text"
            id="pw"
            v-model="user.pw"
            required
          ></b-form-input>
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

        <b-button type="submit" variant="primary">회원수정</b-button> |
        <b-button type="button" variant="danger" @click="deleteUser"
          >회원탈퇴</b-button
        >
      </b-card>
    </b-form>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();
import { mapState, mapActions } from "vuex";
const userStore = "userStore";

export default {
  name: "UserInfo",
  data() {
    return {
      user: {
        id: "",
        name: "",
        pw: "",
        email: "",
        campus: 0,
      },
      options: [
        { value: 0, text: "서울" },
        { value: 1, text: "대전" },
        { value: 2, text: "광주" },
        { value: 3, text: "구미" },
        { value: 4, text: "부울경" },
      ],
    };
  },
  created() {
    http
      .get("/user/" + this.id)
      .then((response) => {
        console.log(response.data);
        const info = response.data;
        this.user.id = info.id;
        this.user.name = info.name;
        this.user.pw = info.pw;
        this.user.email = info.email;
        this.user.campus = info.campus;
      })
      .catch(() => {
        alert("정보 불러오기 에러");
      });
  },
  methods: {
    ...mapActions(userStore, ["logout", "setCampus"]),
    submitForm() {
      http
        .put("/user", {
          id: this.user.id,
          name: this.user.name,
          pw: this.user.pw,
          email: this.user.email,
          campus: this.user.campus,
        })
        .then((response) => {
          console.log(response.data);
          if (response.data === "success") {
            alert("성공적으로 수정 하였습니다.");
            this.setCampus(this.user.campus);
            this.$router.push("/");
          } else {
            alert("수정을 하지 못했습니다1.");
          }
        })
        .catch(() => {
          alert("수정을 하지 못했습니다2.");
        });
    },
    deleteUser() {
      var isDelete = confirm("탈퇴하겠습니까?");
      if (isDelete) {
        console.log(this.user.id);
        http
          .delete("/user/" + this.user.id)
          .then((response) => {
            console.log(response.data);
            if (response.data === "success") {
              alert("성공적으로 탈퇴 하였습니다.");
              // store에서도 값을 변경해주어야됨
              this.logout();
              this.$router.push("/");
            } else {
              alert("탈퇴 하지 못했습니다1.");
            }
          })
          .catch(() => {
            alert("탈퇴 하지 못했습니다2.");
          });
      }
    },
  },
  computed: {
    ...mapState(userStore, ["id"]),
  },
};
</script>

<style></style>
