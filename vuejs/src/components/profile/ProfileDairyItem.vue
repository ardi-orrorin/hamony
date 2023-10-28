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
  .container {
    display: flex;
    border-radius: 10px;
    flex-direction: column;
    width: 100%;
    height: 100%;
    padding: 10px;
    font-size: 0.85rem;
    transition: 0.35s;
    color: gray;

    &:hover {
      color: black;
      background-color: rgba(245, 245, 245, 1);
    }
  }

  .subject {
    justify-content: center;
    padding: 5px;
  }

  .body {
    height: 100%;
    padding: 5px;
  }
</style>