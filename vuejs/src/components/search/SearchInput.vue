<script setup lang="ts">

import {ref} from "vue";
import {searchDiary} from "@/api/diaryApi";

const inputRef = ref<string>('')

function submitHandler() {
  if (inputRef.value === '') return
  searchDiary(inputRef.value)
      .then(_ => inputRef.value = '')
}

</script>

<template>
  <div class="container">
    <input class="searchInput" type="text" placeholder="검색어를 입력하세요" v-model="inputRef" @keyup.enter="submitHandler" />
    <button class="btn" @click="submitHandler">Search</button>
  </div>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/components/search";
</style>