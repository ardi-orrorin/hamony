<script setup lang="ts">
import MenuItem from "@/components/menu/MenuItem.vue";
import koText from '@/assets/lang/ko-kr.json'
import router from "@/router";

interface Menu {
  text: string,
  clickHandler? : Function,
  path?: string
}

const text = koText;

const menuList: Menu[] = [
  {text: text.menuWrite, path: '/write'},
  {text: text.menuRecent},
  {text: text.menuRecommend},
  {text: text.menuSearch},
  {text: text.menuProfile},
]

</script>

<template>
  <div class="container">
    <div class="subContainer">
      <template v-for="menu in menuList">
        <MenuItem :text="menu.text" @click="router.push(menu.path || '')" />
      </template>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .container {
    margin-top: 4vh;
    min-height: 40px;
    height: 3.5vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .subContainer {
    padding: 0 1.5vw;
    height: 100%;
    border-radius: 100px;
    box-shadow: 0 0 10px 5px rgba(230, 230, 230, 0.5);
    display: flex;
    align-items: center;
    gap: 1rem;
  }

</style>