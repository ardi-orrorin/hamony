<script setup lang="ts">
import router from "@/router";

const props = defineProps(['text', 'path'])
</script>

<template>
  <button class="btn" @click="router.push(props.path)">{{ props.text }}</button>
</template>

<style scoped lang="scss">
  .btn {
    width: 30%;
    height: 3vh;
    font-size: 0.9rem;
    color: gray;
    border-radius: 100px;
    transition: 0.35s;

    &:hover {
      color: black;
      background-color: rgba(245, 245, 245, 1);
    }
  }
</style>