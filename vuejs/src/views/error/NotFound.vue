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
  @import "@/assets/scss/pages/error/notFound";
</style>