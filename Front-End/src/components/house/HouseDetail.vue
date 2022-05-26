<template>
  <b-container
    v-if="house"
    class="bv-example-row border rounded p-4"
    style="height: 600px; overflow-y: scroll"
  >
    <b-row class="ml-auto">
      <b-col
        ><h4>{{ house.aptName }}</h4> </b-col
      ><b-icon
        icon="bookmark-star-fill"
        v-if="getMark"
        font-scale="2"
        @click="toggle"
      ></b-icon>
      <b-icon
        icon="bookmark-star"
        v-else
        font-scale="2"
        @click="toggle"
      ></b-icon>
    </b-row>
    <b-row class="mb-2 mt-1">
      <b-col
        ><div id="roadview" style="width: 100%; height: 300px"></div
      ></b-col>
    </b-row>
    <b-table stacked :items="items"></b-table>
    <b-button variant="outline-warning" @click="goBack">이전</b-button>
  </b-container>
</template>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey="
  +process.env.VUE_APP_KAKAO_MAP_KEY
></script>
<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();

import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      mark: false,
      items: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(userStore, ["id"]),
    getMark() {
      return this.mark;
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    ...mapActions(houseStore, ["setHouse"]),
    goBack() {
      this.setHouse();
      this.$router.go(-1);
    },
    toggle() {
      if (this.id == "") {
        alert("로그인이 필요한 서비스입니다.");
      } else {
        if (this.mark) {
          http
            .delete("/interest", {
              data: {
                userid: this.id,
                aptCode: this.house.aptCode,
              },
            })
            .then((response) => {
              console.log("mark-delete-reponse", response);
            })
            .catch(() => {
              console.log("mark-delete-reponse 실패");
            });
        } else {
          http
            .post("/interest", {
              userid: this.id,
              aptName: this.house.aptName,
              aptCode: this.house.aptCode,
              sidoName: this.house.sidoName,
              gugunName: this.house.gugunName,
              dongName: this.house.dongName,
              jibun: this.house.jibun,
              buildYear: this.house.buildYear,
              recentPrice: this.house.recentPrice,
              lng: this.house.lng,
              lat: this.house.lat,
            })
            .then((response) => {
              console.log("mark-add-reponse", response);
            })
            .catch(() => {
              console.log("mark-add-reponse 실패");
            });
        }
        this.mark = !this.mark;
      }
    },
    roadView() {
      console.log("home road view");
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      //console.log(this.house.lat + " " + this.house.lng);
      var position = new kakao.maps.LatLng(this.house.lat, this.house.lng);

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        if (panoId === null) {
          roadviewContainer.innerHTML = "로드뷰가 존재하지 않습니다 ㅠ ㅠ";
        } else {
          roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
        }
      });
    },
  },
  mounted() {
    if (this.id != "") {
      http
        .post("/interest/check", {
          userid: this.id,
          aptCode: this.house.aptCode,
        })
        .then((response) => {
          console.log("mark-reponse1", response);
          if (response.data == 1) {
            this.mark = true;
          } else {
            this.mark = false;
          }
        })
        .catch(() => {
          console.log("reponse1 실패");
        });
    }

    this.items.push({
      아파트명: this.house.aptName,
      주소:
        this.house.sidoName +
        " " +
        this.house.gugunName +
        " " +
        this.house.dongName +
        " " +
        this.house.jibun +
        "번지",
      매매가: parseInt(this.house.recentPrice.replace(",", "")) * 10000 + " 원",
      건축년도: this.house.buildYear + " 년",
    });

    if (window.kakao && window.kakao.maps) {
      this.roadView();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.roadView);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_KEY;
      document.head.appendChild(script);
    }
  },
};
</script>

<style>
.table.b-table.b-table-stacked > tbody > tr > [data-label]::before {
  text-align: center;
}
</style>
