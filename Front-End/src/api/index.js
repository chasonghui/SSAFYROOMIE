import axios from "axios";
const api = apiInstance();
// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: "http://localhost:9999/vue",
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
console.log(process.env.VUE_APP_ODSAY_REST_API_KEY);
function kakaoFetch(keyword) {
  const instance = axios.create({
    baseURL: "https://dapi.kakao.com/v2/search/web",
    headers: {
      Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_REST_API_KEY}`,
    },
    params: { query: keyword },
  });
  return instance;
}

function transferFetch(sx, sy, ex, ey) {
  const instance = axios.create({
    baseURL: `https://api.odsay.com/v1/api/searchPubTransPath?SX=${sx}&SY=${sy}&EX=${ex}&EY=${ey}&apiKey=${process.env.VUE_APP_ODSAY_REST_API_KEY}`,
  });
  return instance;
}

function callMapObjApiAJAX(mabObj) {
  const instance = axios.create({
    baseURL: `https://api.odsay.com/v1/api/loadLane?mapObject=0:0@${mabObj}&apiKey=${process.env.VUE_APP_ODSAY_REST_API_KEY}`,
  });
  return instance;
}

async function houseDealSearchList(params, success, fail) {
  await api.get(`/map/search`, { params: params }).then(success).catch(fail);
}

export {
  apiInstance,
  kakaoFetch,
  houseDealSearchList,
  transferFetch,
  callMapObjApiAJAX,
};
