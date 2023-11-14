<script setup lang="ts">

import ProfileImg from "@/components/profile/ProfileImg.vue";
import ProfileTab from "@/components/profile/ProfileTab.vue";
import koText from "@/assets/lang/ko-kr.json"
import {useToken} from "@/store/member";
import type {Lang} from "@/assets/lang/langType";
import {deleteMember} from "@/api/memberApi";

interface Tab {
  text: string;
  path: string;
}

const token = useToken()
const userId = token.$state.userId

const text: Lang = koText;

const tabs: Tab[] = [
  {text: text.profileTabList, path: '/profile/list' },
  {text: text.profileTabFav, path: '/profile/favorite'},
  {text: text.profileTabOrder, path: '/profile/order'},
]

function deleteMemberHandler() {
    if( confirm("정말로 탈퇴하시겠습니까?")){
        deleteMember()
    }
}

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
            <button @click="deleteMemberHandler">{{ text.profileDeleteProfile }}</button>
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