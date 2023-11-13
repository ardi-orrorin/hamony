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
  router.push("/read/"+id)
}

</script>

<template>
  <div class="container">
    <div class="subContainer">
      <template v-for="item in list">
          <RecentItem :subject="item.subject" :content="item.content" :like="item.like" :id="item.id" @click="()=>moveDiary(item.id!!)"/>
      </template>

    </div>
  </div>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/pages/diary/recent";

</style>