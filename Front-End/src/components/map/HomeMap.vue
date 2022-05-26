<template>
  <div class="mb-2 mr-2">
    <div id="map" style="width: 100%; height: 700px">
      <ul id="category">
        <li id="BK9" data-order="0">
          <span class="category_bg bank"></span>
          은행
        </li>
        <li id="MT1" data-order="1">
          <span class="category_bg mart"></span>
          마트
        </li>
        <li id="PM9" data-order="2">
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li id="OL7" data-order="3">
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li id="CE7" data-order="4">
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li id="CS2" data-order="5">
          <span class="category_bg store"></span>
          편의점
        </li>
      </ul>
    </div>
  </div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  data() {
    return {
      placeOverlay: [],
      contentNode: [],
      markers: [],
      currCategory: [],
      map: [],
      ps: [],
      infowindow: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        process.env.VUE_APP_KAKAO_MAP_KEY +
        "&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["houses", "house"]),
  },
  watch: {
    house: function (val) {
      this.initMap();
    },
    houses: function () {
      this.listMap();
    },
  },
  methods: {
    initMap() {
      (this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 })),
        (this.contentNode = document.createElement("div")), // 커스텀 오버레이의 컨텐츠 엘리먼트
        (this.markers = []), // 마커를 담을 배열입니다
        (this.currCategory = ""); // 현재 선택된 카테고리를 가지고 있을 변수
      /* 1 */
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
          level: this.house != null ? 1 : 13, // 지도의 확대 레벨
        };
      this.map = new kakao.maps.Map(mapContainer, mapOption); // 지도 객체

      this.removeMarker();
      var points = [];
      console.log("init");
      // 하우스 목록에 있는 매물들 마커, 이름 표시
      for (let index = 0; index < this.houses.length; index++) {
        const element = this.houses[index];
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(element.lat, element.lng), // 마커를 표시할 위치
        });
        points.push(new kakao.maps.LatLng(element.lat, element.lng));
      }

      if (this.house != null) {
        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        console.log("house");
        var coords = new kakao.maps.LatLng(this.house.lat, this.house.lng);
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: coords, // 마커를 표시할 위치
        });
        const ioverlay = new kakao.maps.CustomOverlay({
          content:
            '<div class="customoverlay"> <span class="title">' +
            this.house.aptName +
            "</span> </div>",
          map: this.map,
          position: coords,
        });
        ioverlay.setMap(this.map);
        this.map.setCenter(coords);
      } else if (this.houses.length > 0) {
        var bounds = new kakao.maps.LatLngBounds();
        for (let i = 0; i < points.length; i++) {
          bounds.extend(points[i]);
        }
        this.map.setBounds(bounds);
      }

      this.ps = new kakao.maps.services.Places(this.map); // 장소 검색 객체
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces); // idle event 등록
      this.contentNode.classList.add("placeinfo_wrap");
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap,
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap,
      );
      this.placeOverlay.setContent(this.contentNode);

      this.addCategoryClickEvent(); // 카테고리에 addEvent등록
    },
    listMap() {
      (this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 })),
        (this.contentNode = document.createElement("div")), // 커스텀 오버레이의 컨텐츠 엘리먼트
        (this.markers = []), // 마커를 담을 배열입니다
        (this.currCategory = ""); // 현재 선택된 카테고리를 가지고 있을 변수
      /* 1 */
      console.log("list");
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(36.35531933, 127.29823375), // 지도의 중심좌표
          level: 1, // 지도의 확대 레벨
        };
      this.map = new kakao.maps.Map(mapContainer, mapOption); // 지도 객체

      this.removeMarker();
      var points = [];

      // 하우스 목록에 있는 매물들 마커, 이름 표시
      for (let index = 0; index < this.houses.length; index++) {
        const element = this.houses[index];
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(element.lat, element.lng), // 마커를 표시할 위치
        });
        points.push(new kakao.maps.LatLng(element.lat, element.lng));
      }

      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      if (this.houses.length > 0) {
        var bounds = new kakao.maps.LatLngBounds();
        for (let i = 0; i < points.length; i++) {
          bounds.extend(points[i]);
        }
        this.map.setBounds(bounds);
      } else {
        var coords = new kakao.maps.LatLng(36.35531933, 127.29823375);
        this.map.setCenter(coords);
      }

      this.ps = new kakao.maps.services.Places(this.map); // 장소 검색 객체
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces); // idle event 등록
      this.contentNode.classList.add("placeinfo_wrap");
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap,
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap,
      );
      this.placeOverlay.setContent(this.contentNode);

      this.addCategoryClickEvent(); // 카테고리에 addEvent등록
    },
    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent() {
      var category = document.getElementById("category"),
        children = category.children;
      for (var i = 0; i < children.length; i++) {
        children[i].onclick = this.onClickCategory;
      }
    },
    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(event) {
      console.log(event);
      var id = event.currentTarget.id,
        className = event.currentTarget.className;
      console.log(event.target);
      console.log(id);
      console.log(className);
      this.placeOverlay.setMap(null);
      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(this);
        this.searchPlaces();
      }
    },
    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      var category = document.getElementById("category"),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }
      if (el) {
        console.log(el);
        console.log(el.className);
        // el.className = 'on';
      }
    },
    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);
      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order,
        );
      }
    },
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      console.log("addMarker");
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions,
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
      return marker;
    },
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      console.log("removeMarker");
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
      console.log("마커", this.markers);
    },
  },
};
</script>

<style>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  font-size: 14px;
  font-weight: bold;
  padding: 10px 15px;
  background-color: rgb(255, 255, 255);
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
