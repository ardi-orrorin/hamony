<script setup lang="ts">
import type {Diary} from "@/store/diary";
import ProfileDairyItem from "@/components/profile/ProfileDairyItem.vue";
import router from "@/router";
import {onMounted, onUpdated} from "vue";
import {profileList} from "@/api/diaryApi";

const itemList: Diary[] = []

onMounted(()=>{
  profileList(router.currentRoute.value.meta.tab!!)
});

onUpdated(()=>{
  profileList(router.currentRoute.value.meta.tab!!)
});

</script>

<template>
  <div class="container">
    <template v-for="item in itemList">
      <ProfileDairyItem :subject="item.subject" :content="item.content" />
    </template>
  </div>
</template>

<style scoped lang="scss">
  .container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    min-height: 100%;
    overflow-y: scroll;
    gap: 10px;
    div {
      border-collapse: collapse;
      //border: 1px solid red;
      height: 15vh;
    }
  }

</style>