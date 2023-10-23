<script setup lang="ts">
const props = defineProps(['src'])
</script>

<template>
  <div class="profileImg">
    <img class="img" :src="src" onerror="">
    <div class="sticky">+</div>
  </div>
</template>

<style scoped lang="scss">
    .profileImg {
      //border-radius: 100vw;
      height: 50px;
      width: 50px;
      position: relative;
    }
    .img {
      width: 100%;
      height: 100%;
      border-radius: 100vw;
      border: 1px solid red;
    }
    .sticky {
      border-radius: 100vw;
      height: 10px;
      width: 10px;
      position: absolute;
      bottom: 15px;
      right: 10px;
      color: gray;
      font-size: 1.3rem;
      font-weight: 500;
    }
</style>