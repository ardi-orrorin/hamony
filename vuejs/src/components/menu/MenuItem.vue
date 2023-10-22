<script setup lang="ts">
  const props = defineProps(['text'])

</script>

<template>
  <button class="menuItem">{{ props.text }}</button>
</template>

<style scoped lang="scss">
  .menuItem {
    font-size: 0.8rem;
    padding: 0.4rem 0.8rem;
    border-radius: 100vw;
    //border: 1px solid red;
    text-align: center;
    transition: 0.35s;
    color: gray;

    &:hover {
      color: black;
      background-color: rgba(245, 245, 245, 1);;
      transition: 0.35s;
    }
  }
</style>