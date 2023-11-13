<script setup lang="ts">

const props = defineProps(['text'])


</script>

<template>
  <button class="tagItem">{{props.text}}</button>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/components/diary/item";
</style>