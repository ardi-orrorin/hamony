<script setup lang="ts">

import {computed} from "vue";

const props = defineProps(['value', 'disabled'])
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
  <input class="diarySubject" v-model="value" :disabled="props.disabled"/>
</template>

<style scoped lang="scss">
@import "@/assets/scss/components/diary/subject";

</style>