<template>
  <div class="footer">
    <nav>
      <a href="https://github.com/chasonghui/SSAFYROOMIE" target="_blank"
        >Github</a
      >
    </nav>
    <p class="ml-3 mb-2">
      <span>저자 : 이예은, 차송희</span><br />
      <span>이메일 : dpdms828@naver.com, chasonghui@naver.com</span><br />
      <span>Copyright 2022. 옌송. All Rights Reserved.</span>
    </p>
  </div>
</template>

<script>
export default {
  name: "FooterBar",
};
</script>

<style>
.footer {
  width: 100%;
  height: 90px;
  bottom: 0px;
  border-top: 1px solid #c4c4c4;
  padding-top: 15px;
  color: #808080;
  position: inherit;
  font-size: 11px;
}

.footer a {
  display: inline-block;
  margin: 0 20px 10px 20px;
  color: #808080;
  font-size: 11px;
}

.footer a:visited {
  color: #808080;
}
</style>
