<script lang="ts" setup>
const props = defineProps(['pathMatch']);
const path: string = props.pathMatch[props.pathMatch.length - 1];


</script>

<template>
  <div class="container">
    <h1>404 NOT FOUND</h1>
    <span>{{ path }} 페이지를 찾을 수 없습니다.</span>
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