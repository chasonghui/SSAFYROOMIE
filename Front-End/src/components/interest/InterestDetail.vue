<template>
  <b-container
    v-if="interest"
    class="bv-example-row border rounded p-4"
    style="height: 650px; overflow-y: scroll"
  >
    <b-row class="ml-auto">
      <b-col
        ><h4>{{ interest.aptName }}</h4> </b-col
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
    <b-button
      style="width: 100%; display: flex; justify-content: space-between"
      variant="primary"
      class="interest-collapse"
      v-b-toggle.apt
      >아파트 상세정보<b-icon icon="chevron-expand" font-scale="1"
    /></b-button>
    <b-collapse id="apt">
      <b-table stacked :items="items"></b-table>
    </b-collapse>
    <b-button
      style="width: 100%; display: flex; justify-content: space-between"
      variant="primary"
      class="interest-collapse"
      v-b-toggle.path
      >대중교통 최단거리정보<b-icon icon="chevron-expand" font-scale="1"
    /></b-button>
    <b-collapse id="path">
      <div v-if="pathInfos.length">
        <b-table stacked :items="pathInfos"> </b-table>
      </div>
      <div v-else class="mb-4">
        <b-list-group-item>{{ msg }}</b-list-group-item>
      </div>
    </b-collapse>
    <b-button variant="outline-warning" @click="goBack">이전</b-button>
  </b-container>
</template>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey="
  +process.env.VUE_APP_KAKAO_MAP_KEY
></script>
<script>
import { apiInstance, transferFetch } from "@/api/index.js";
const http = apiInstance();

import { mapState, mapActions } from "vuex";
const interestStore = "interestStore";
const userStore = "userStore";

export default {
  name: "InterestDetail",
  data() {
    return {
      mark: false,
      pathInfos: [],
      msg: "700 m 이내 이므로 도보 가능합니다.",
      pathInfo: null,
      campusInfo: [
        {
          EX: 127.03972342142818,
          EY: 37.50130767739711,
        },
        {
          EX: 127.29823375,
          EY: 36.35531933,
        },
        {
          EX: 126.80836832443617,
          EY: 35.20429551498763,
        },
        {
          EX: 128.4113371154321,
          EY: 36.10813239991002,
        },
        {
          EX: 128.85616245785297,
          EY: 35.093716403956996,
        },
      ],
      items: [],
    };
  },
  computed: {
    ...mapState(interestStore, ["interest"]),
    ...mapState(userStore, ["campus"]),
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
    ...mapActions(interestStore, ["clearInterest"]),
    goBack() {
      this.clearInterest();
      this.$router.go(-1);
    },
    toggle() {
      if (this.mark) {
        http
          .delete("/interest", {
            data: {
              userid: this.interest.userid,
              aptCode: this.interest.aptCode,
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
            userid: this.interest.userid,
            aptName: this.interest.aptName,
            aptCode: this.interest.aptCode,
            sidoName: this.interest.sidoName,
            gugunName: this.interest.gugunName,
            dongName: this.interest.dongName,
            jibun: this.interest.jibun,
            buildYear: this.interest.buildYear,
            recentPrice: this.interest.recentPrice,
            lng: this.interest.lng,
            lat: this.interest.lat,
          })
          .then((response) => {
            console.log("mark-add-reponse", response);
          })
          .catch(() => {
            console.log("mark-add-reponse 실패");
          });
      }
      this.mark = !this.mark;
    },
    roadView() {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      console.log(this.interest.lat + " " + this.interest.lng);
      var position = new kakao.maps.LatLng(
        this.interest.lat,
        this.interest.lng,
      );

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
    async searchTransPath() {
      const insTransfer = transferFetch(
        this.interest.lng,
        this.interest.lat,
        this.campusInfo[this.campus].EX,
        this.campusInfo[this.campus].EY,
      );
      const res = await insTransfer.get();
      console.log("res", res);
      console.log("data", res.data.result);

      if (res.data.result != undefined) {
        const pathInfo = res.data.result.path[0].info;
        if (pathInfo.busStationCount != 0 && pathInfo.subwayStationCount == 0) {
          this.pathInfos.push({
            "총 이동거리": pathInfo.totalDistance + " m",
            "총 소요시간": pathInfo.totalTime + " 분",
            "이동 방법": "버스 " + pathInfo.busStationCount + " 정거장",
            "승차 정류장": pathInfo.firstStartStation,
            "하차 정류장": pathInfo.lastEndStation,
            "왕복 교통비": pathInfo.payment * 2 + " 원",
          });
        } else if (
          pathInfo.busStationCount == 0 &&
          pathInfo.subwayStationCount != 0
        ) {
          this.pathInfos.push({
            "총 이동거리": pathInfo.totalDistance + " m",
            "총 소요시간": pathInfo.totalTime + " 분",
            "이동 방법": "지하철 " + pathInfo.subwayStationCount + " 개 역",
            "승차 역": pathInfo.firstStartStation,
            "하차 역": pathInfo.lastEndStation,
            "왕복 교통비": pathInfo.payment * 2 + " 원",
          });
        } else {
          this.pathInfos.push({
            "총 이동거리": pathInfo.totalDistance + " m",
            "총 소요시간": pathInfo.totalTime + " 분",
            "이동 방법":
              "버스 " +
              pathInfo.busStationCount +
              " 정거장, 지하철 " +
              pathInfo.subwayStationCount +
              " 개 역",
            승차: pathInfo.firstStartStation,
            하차: pathInfo.lastEndStation,
            "왕복 교통비": pathInfo.payment * 2 + " 원",
          });
        }
      }
      console.log("infos", this.pathInfos);
    },
  },
  mounted() {
    http
      .post("/interest/check", {
        userid: this.interest.userid,
        aptCode: this.interest.aptCode,
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

    this.items.push({
      아파트명: this.interest.aptName,
      주소:
        this.interest.sidoName +
        " " +
        this.interest.gugunName +
        " " +
        this.interest.dongName +
        " " +
        this.interest.jibun +
        "번지",
      매매가:
        parseInt(this.interest.recentPrice.replace(",", "")) * 10000 + " 원",
      건축년도: this.interest.buildYear + " 년",
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

    this.searchTransPath();
  },
};
</script>

<style>
.interest-collapse {
  text-align: left;
  background-color: #cce5ff;
  border: 0;
  color: #003382;
  padding: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}
.table.b-table.b-table-stacked > tbody > tr > [data-label]::before {
  text-align: center;
}
</style>
