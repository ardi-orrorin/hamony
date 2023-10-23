<script setup lang="ts">
import RecentItem from "@/components/recent/RecentItem.vue";

interface RecentItems {
  subject: string;
  content: string;
  like: boolean;
}
const list: RecentItems[] = [
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
  {subject: 'test', content: '23123', like: true},
]
</script>

<template>
  <div class="container">
    <div class="subContainer">
      <template v-for="item in list">
        <RecentItem :subject="item.subject" :content="item.content" :like="item.like" />
      </template>

    </div>
  </div>
</template>

<style scoped lang="scss">
  .container {
     display: flex;
     justify-content: center;
  }

  .subContainer {
    min-width: 70%;
    max-width: 70%;
    padding: 20px;
    display: flex;
    flex-wrap: wrap;
    word-break: break-all;
    justify-content: center;
    border-radius: 20px;
    gap: 20px;
    transition: 0.35s;
    
    @media (max-width: 1000px) {
      max-width: 80%;
    }
    @media (max-width: 650px) {
      max-width: 100%;
    }
  }

</style>