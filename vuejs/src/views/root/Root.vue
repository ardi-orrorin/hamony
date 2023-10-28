<script setup lang="ts">

import {RouterView} from "vue-router";
import Menu from "@/components/menu/Menu.vue";
</script>

<template>
  <Menu />
  <br />
  <br />
  <RouterView :meta="$route.meta" />
</template>

<style scoped lang="scss">

</style>