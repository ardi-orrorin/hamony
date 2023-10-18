<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <RouterView />
</template>

<style lang="scss" scoped>
//.container{
//  display: flex;
//  justify-content: center;
//  text-align: center;
//}
.v-enter-active,
.v-leave-active {
  transition: opacity 0.35s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
