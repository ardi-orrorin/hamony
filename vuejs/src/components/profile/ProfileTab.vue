<script setup lang="ts">
import router from "@/router";

const props = defineProps(['text', 'path'])
</script>

<template>
  <button class="btn" @click="router.push(props.path)">{{ props.text }}</button>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/components/profile/tab";
</style>