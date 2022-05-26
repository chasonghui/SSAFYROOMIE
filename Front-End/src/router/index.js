import Vue from "vue";
import store from "@/store/index.js";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const requireLogin = () => (to, from, next) => {
  const userid = store.getters["userStore/getId"];

  if (userid !== "") {
    return next();
  }
  alert("로그인이 필요한 서비스 입니다.");
  next("/user/signin");
};

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/AptNewsView.vue"),
  },
  {
    path: "/map",
    name: "map",
    component: () => import("@/views/MapView.vue"),
    redirect: "/map/houselist",
    children: [
      {
        path: "houselist",
        name: "houseList",
        component: () => import("@/components/house/HouseList.vue"),
      },
      {
        path: "housedetail",
        name: "houseDetail",
        component: () => import("@/components/house/HouseDetail.vue"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "signin",
        name: "signIn",
        component: () => import("@/components/user/UserLogin.vue"),
      },
      {
        path: "signup",
        name: "signUp",
        component: () => import("@/components/user/UserRegister.vue"),
      },
      {
        path: "findpw",
        name: "findPw",
        component: () => import("@/components/user/FindPw.vue"),
      },
      {
        path: "userinfo",
        name: "userInfo",
        component: () => import("@/components/user/UserInfo.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    beforeEnter: requireLogin(),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/interest",
    name: "interest",
    component: () => import("@/views/InterestView.vue"),
    beforeEnter: requireLogin(),
    redirect: "/interest/interestlist",
    children: [
      {
        path: "interestlist",
        name: "interestList",
        component: () => import("@/components/interest/InterestList.vue"),
      },
      {
        path: "interestdetail",
        name: "interestDetail",
        component: () => import("@/components/interest/InterestDetail.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
