<script setup lang="ts">
const props = defineProps(['subject', 'content'])
</script>

<template>
  <div>
    <div class="container">
      <div class="subject">
        {{props.subject}}
      </div>
      <div class="body">
        {{props.content}}
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/components/profile/diaryItem";
</style>