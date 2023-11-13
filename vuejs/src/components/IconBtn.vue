<script lang="ts" setup>
  defineProps(['text'])
</script>
<template>
  <button class="btn">
    <span class="material-symbols-outlined">
      {{ text }}
    </span>
  </button>
</template>

<style lang="scss" scoped>
  @import "@/assets/scss/components/iconBtn";

  @font-face {
    font-family: 'Material Symbols Outlined';
    font-style: normal;
    font-weight: 500 700;
    src: url(https://fonts.gstatic.com/s/materialsymbolsoutlined/v141/kJEhBvYX7BgnkSrUwT8OhrdQw4oELdPIeeII9v6oFsLjBuVY.woff2) format('woff2');

  }

  .material-symbols-outlined {
    font-family: 'Material Symbols Outlined';
  }

</style>