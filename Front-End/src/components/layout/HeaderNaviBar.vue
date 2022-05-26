<template>
  <div>
    <b-navbar toggleable="lg" style="background-color: rgb(234, 243, 253)">
      <b-navbar-brand href="#"> </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'home' }" class="titleName"
              ><h1 style="color: #003382" class="titletxt">
                SSAFY ROOMIE
              </h1></router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#" class="mr-4"
            ><router-link :to="{ name: 'map' }" class="link text-dark titleName"
              ><b-icon
                icon="map"
                style="color: #003382"
                class="titleName mr-1"
                font-scale="1"
              ></b-icon
              ><a style="color: #003382">집찾기</a></router-link
            ></b-nav-item
          >
          <b-nav-item href="#" class="mr-4"
            ><router-link
              :to="{ name: 'board' }"
              class="link text-dark titleName"
              style="color: #003382"
              ><b-icon
                icon="journal"
                style="color: #003382"
                font-scale="1"
                class="mr-1"
              ></b-icon
              ><a style="color: #003382" class="titleName"
                >게시판</a
              ></router-link
            ></b-nav-item
          >
          <b-nav-item href="#" class="mr-4"
            ><router-link
              :to="{ name: 'interest' }"
              class="link text-dark titleName"
              ><b-icon
                icon="house"
                style="color: #003382"
                class="mr-1"
                font-scale="1"
              ></b-icon>
              <a style="color: #003382">관심목록</a></router-link
            ></b-nav-item
          >
          <b-nav-item-dropdown right class="mr-4">
            <template #button-content>
              <b-icon
                icon="people"
                style="color: #003382"
                font-scale="1"
              ></b-icon>
            </template>
            <b-dropdown-item href="#" v-if="getUser"
              ><router-link :to="{ name: 'userInfo' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 내 정보</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-else
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-if="getUser"
              ><a href="#" @click="goLogout"
                ><b-icon icon="key"></b-icon> 로그아웃</a
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-else
              ><router-link :to="{ name: 'signIn' }"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";

export default {
  name: "HeaderNaviBar",
  methods: {
    ...mapActions(userStore, ["logout"]),
    goLogout() {
      console.log("로그아웃");
      this.logout();
      this.$router.push("/");
    },
  },
  computed: {
    ...mapState(userStore, ["id"]),
    getUser() {
      //console.log(this.profile);
      if (this.id == "") {
        return false;
      } else {
        return true;
      }
    },
  },
};
</script>

<style>
.titletxt {
  color: "#003382";
}

.titleName:hover {
  text-decoration: none;
}
</style>
