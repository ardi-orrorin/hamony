<script setup lang="ts">
import {RouterView} from 'vue-router'
import Menu from "@/components/menu/Menu.vue";

</script>

<template>
<!-- 조건 추가 예정-->
  <RouterView />
</template>

<style lang="scss" scoped>

.v-enter-active,
.v-leave-active {
  transition: opacity 0.35s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
