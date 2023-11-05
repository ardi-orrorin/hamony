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
  @mixin size100 {
    width: 100%;
    height: 100%;
  }
  @mixin padding05rem {
    padding: 0.5rem;
  }
  @mixin fullborderRadius {
    border-radius: 100vw;
  }

  .container {
    @include size100;
    @include padding05rem;
    @include fullborderRadius;
    font-size: 0.85rem;
    position: relative;
    box-shadow: 0 0 10px 5px rgba(230, 230, 230, 0.5);

  }
  .searchInput {
    @include size100;
    @include fullborderRadius;

    border: none;
    outline: none;
    font-size: 0.8rem;
    padding: 0.35rem 0.75rem;
    color: gray;
    transition: 0.35s;
    &:focus {
      color: black;
      background-color: rgba(245, 245, 245, 1);
    }
  }

  .btn {
    @include fullborderRadius;
    position: absolute;
    right: 0.5rem;
    top: 0.5rem;
    outline: none;
    font-size: 0.85rem;
    padding: 0.3rem 0.5rem;
    color: gray;
    transition: 0.35s;
    &:hover {
      color: black;
      background-color: rgba(245, 245, 245, 1);
    }
  }

</style>