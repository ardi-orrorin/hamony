<script setup lang="ts">

import {RouterView} from "vue-router";
import Menu from "@/components/menu/Menu.vue";
</script>

<template>
  <Menu />
  <br />
  <br />
<!--  <RouterView :meta="$route.meta"/>-->
  <router-view v-slot="{Component}">
      <component :is="Component" />
  </router-view>
</template>

<style scoped lang="scss">
.v-enter-from,
.v-leave-to {
  transition: opacity 0.25s;
  opacity: 0;
}
</style>