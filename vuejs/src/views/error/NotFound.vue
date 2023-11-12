<script lang="ts" setup>
import koText from "@/assets/lang/ko-kr.json"
import router from "@/router";

const text = koText;
const props = defineProps(['pathMatch', 'path']);
const path: string = props.pathMatch.join('/');

</script>

<template>
  <div class="container">
    <h1>{{ text.error404 }}</h1>
    <span>{{path }} {{ text.notFoundPage }}</span>
    <button @click="router.back()">뒤로가기</button>
  </div>
</template>

<style lang="scss" scoped>
  .container {
    display: grid;
    width: 100vw;
    height: 98.5vh;
    align-content: center;

    h1 {
      display: flex;
      justify-content: center;
      font-weight: 700;
      font-size: 2.5rem;
      margin: 1rem 0;
      //color: rgba(200, 200, 200, 1)
    }

    span {
      display: flex;
      justify-content: center;
      align-items: center;
      margin: 1rem 0;
    }
  }



</style>