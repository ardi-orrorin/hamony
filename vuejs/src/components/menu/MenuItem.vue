<script setup lang="ts">
  const props = defineProps(['text'])

</script>

<template>
  <button class="menuItem">{{ props.text }}</button>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/components/menu/menuItem";

</style>