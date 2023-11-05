<script setup lang="ts">
import RecentItem from "@/components/recent/RecentItem.vue";
import {onMounted, reactive} from "vue";
import {recentDiary} from "@/api/diaryApi";
import router from "@/router";

interface RecentItems {
  id?: number | null;
  subject: string;
  content: string;
  like: boolean;
}

const list: RecentItems[] = reactive<RecentItems[]>([
])

onMounted(()=> {
  recentDiary().then(res => {
    res.data.forEach(it => {
      list.push({id: it.id, subject: it.subject,  content: it.content, like: false})
    })
  })
})

function moveDiary(id: number){
  console.log(id)
  router.push("/read/"+id)
}

</script>

<template>
  <div class="container">
    <div class="subContainer">
      <template v-for="item in list">
          <RecentItem :subject="item.subject" :content="item.content" :like="item.like" @click="()=>moveDiary(item.id!!)"/>
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