<script setup lang="ts">

import SearchInput from "@/components/search/SearchInput.vue";
import {useDairys} from "@/store/diary";
import RecentItem from "@/components/recent/RecentItem.vue";

const dairylist = useDairys()


</script>

<template>
  <div class="container">
    <div class="header">
      <div class="input">
        <SearchInput />
      </div>
    </div>
    <br/>
    <div class="body">
      <template v-for="dairy in dairylist.$state.store">
        <div class="listContainer">
          <RecentItem :subject="dairy.subject" :content="dairy.content" />
        </div>
      </template>
    </div>
  </div>
</template>

<style scoped lang="scss">

  .container {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 1rem;

  }
  .header {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .input {
    width: 50%;
    min-width: 400px;
  }

  .body {
    display: flex;
    justify-content: center;
    padding: 0 5%;
    width: 100%;
    gap: 30px;
    flex-wrap: wrap;
  }

  .listContainer {
    width: 270px;
    height: 150px;
  }

</style>