<script setup lang="ts">

import ProfileImg from "@/components/profile/ProfileImg.vue";
import ProfileTab from "@/components/profile/ProfileTab.vue";
import koText from "@/assets/lang/ko-kr.json"
import {useToken} from "@/store/member";

interface Tab {
  text: string;
  path: string;
}

const token = useToken()
const userId = token.$state.userId

const text = koText;



const tabs: Tab[] = [
  {text: text.profileTabList, path: '/profile/list' },
  {text: text.profileTabFav, path: '/profile/favorite'},
  {text: text.profileTabOrder, path: '/profile/order'},
]
</script>

<template>
  <div class="container">
    <div class="subContainer">
      <div class="">
        <div class="header">
          <ProfileImg src="/" />
          <span class="profileId">{{userId || text.profileId}}</span>
          <div class="headerSub">
            <button>{{ text.profileModifyProfile }}</button>
            <button>{{ text.profileAddProfile }}</button>
          </div>
        </div>
        <div class="tabs">
          <template v-for="tab in tabs">
            <ProfileTab :text="tab.text" :path="tab.path" />
          </template>
        </div>
        <div class="body">
          <RouterView :meta="$route.meta" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
  @import "@/assets/scss/pages/profile/profile";
</style>