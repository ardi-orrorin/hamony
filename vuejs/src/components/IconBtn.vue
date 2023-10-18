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
  @font-face {
    font-family: 'Material Symbols Outlined';
    font-style: normal;
    font-weight: 500 700;
    src: url(https://fonts.gstatic.com/s/materialsymbolsoutlined/v141/kJEhBvYX7BgnkSrUwT8OhrdQw4oELdPIeeII9v6oFsLjBuVY.woff2) format('woff2');

  }

  .material-symbols-outlined {
    font-family: 'Material Symbols Outlined';
  }

  .btn {
    border-radius: 10vw;
    border: gray solid 1px;
    font-size: 1rem;
    text-align: right;
    height: 2rem;
    width: 2rem;
    color: gray;
    margin: 0.5rem;
    background-color: white;


    &:hover{
      background: gray;
      color: white;
      transition: 0.35s;
    }
  }
</style>