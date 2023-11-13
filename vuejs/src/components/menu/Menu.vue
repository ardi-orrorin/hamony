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
  {text: text.menuRecent, path: '/recent'},
  {text: text.menuRecommend, path: '/upload'},
  {text: text.menuSearch, path: '/search'},
  {text: text.menuProfile, path: '/profile'},
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
  @import "@/assets/scss/components/menu/menu";

</style>