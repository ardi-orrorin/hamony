<script setup lang="ts">
import IconBtn from "@/components/IconBtn.vue";

const props = defineProps(['subject', 'content', 'like'])

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
        <IconBtn :text="props.like? 'favorite' : 'favorite'" style="border: none"/>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .container {
    border-radius: 10px;
    width: 200px;
    height: 150px;
    padding: 10px;
    flex-direction: column;
    color: gray;
    text-overflow: ellipsis;
    box-shadow: 0 0 5px 5px rgba(240, 240, 240, 0.5);
    position: relative;
  }

  .subContainer {
    width: 100%;
    height: 100%;
    padding: 10px;
    border-radius: 10px;
    transition: 0.35s;
    &:hover {
      color: black;
      background-color: rgba(245, 245, 245, 1);
    };
  }

  .subject {
    font-size: 0.9rem;
    height: 20%;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    word-break: break-all;
  }
  .content {
    font-size: 0.8rem;
    margin-top: 10px;
    overflow: hidden;
    height: 80%;
    word-break: break-all;
    text-overflow: ellipsis;
    white-space: pre-line;
  }

  .sticky {
    z-index: 100;
    position: absolute;
    bottom: 5px;
    right: 5px;
  }

</style>