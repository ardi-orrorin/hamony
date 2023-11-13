<script setup lang="ts">
import IconBtn from "@/components/IconBtn.vue";
import {getLike, likeDiaryToggle} from "@/api/diaryApi";
import {onMounted, ref} from "vue";

const props = defineProps(['subject', 'content', 'like', 'id'])

const isLike = ref(false)

onMounted(()=>{
  getLike(props.id).then(res => {
    isLike.value = res
  })
})

function likeClickHandler() {
  likeDiaryToggle(props.id).then(res => {
    isLike.value = res
  })
}

</script>

<template>
  <div class="container">
    <div class="subContainer">
      <div class="subject">
        {{props.subject}}
      </div>
      <div class="content">
        {{props.content}}
      </div>
      <div class="sticky">
        <!--  todo 좋아요 아이콘 -->
        <IconBtn :class="isLike && 'red'" :text="isLike? 'heart_minus' : 'heart_plus'" style="border: none" @click="likeClickHandler"/>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/components/recent";
</style>