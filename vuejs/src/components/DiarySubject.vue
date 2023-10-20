<script setup lang="ts">

import {computed, ref} from "vue";

const props = defineProps(['value'])
const emits = defineEmits(['update:value'])

const value = computed({
  get(){
    return props.value
  },
  set(value) {
    emits('update:value', value)
  }
})

</script>

<template>
  <input class="diarySubject" v-model="value" />
</template>

<style scoped lang="scss">
  .diarySubject {
    padding: 0.5rem 1rem;
    width: 100%;
    height: 100%;
    outline-style: none;
    border: none;
    transition: 0.35s;
    color: gray;

    &:focus {
      background-color: rgba(245, 245, 245, 1);
      transition: 0.35s;
      color: black;
    }
  }
</style>