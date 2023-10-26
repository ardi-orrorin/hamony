<script setup lang="ts">

const props = defineProps(['text'])


</script>

<template>
  <button class="tagItem">{{props.text}}</button>
</template>

<style scoped lang="scss">
  .tagItem {
    margin:5px 5px 10px 5px;
    padding: 0.3rem 0.5rem;
    border-radius: 15px;

    box-shadow: 0 0 3px 3px rgba(245, 245, 245, 1);
    color: blue;
    transition: 0.35s;
    &:hover {
      background-color: rgba(245, 245, 245, 1);
    }
  }
</style>