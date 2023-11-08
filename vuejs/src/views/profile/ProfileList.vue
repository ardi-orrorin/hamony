<script setup lang="ts">
import {useDairys} from "@/store/diary";
import ProfileDairyItem from "@/components/profile/ProfileDairyItem.vue";

const itemList = useDairys()


</script>

<template>
  <div class="container">
    <template v-for="item in itemList.$state.store">
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