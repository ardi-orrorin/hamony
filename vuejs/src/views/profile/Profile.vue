<script setup lang="ts">

import ProfileImg from "@/components/profile/ProfileImg.vue";
import ProfileTab from "@/components/profile/ProfileTab.vue";
import koText from "@/assets/lang/ko-kr.json"
interface Tab {
  text: string;
  path: string;
}

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
          <span class="profileId">{{ text.profileId}}</span>
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
  .container {
    width: 100%;
    display: flex;
    justify-content: center;

  }

  .subContainer {
    width: 40%;
    justify-content: center;
    box-shadow: 0 0 10px 5px rgba(230, 230, 230, 0.5);
    padding: 1vw;
    border-radius: 1vw;
  }

  .header {
    padding: 1vh 1vw;
    display: flex;
    gap: 2vw;
    height: 100%;
    align-items: center;
    span {
      width: 20%;
    }
  }

  .profileId {
    color: gray;
    font-size: 0.9rem;
  }
  .tabs {
    margin-top: 2vh;
    display: flex;
    justify-content: space-between;
    gap: 1vw;
  }

  .body {
    margin-top: 2vh;
    height: 40vh;
    flex-wrap: wrap;
    word-break: break-all;
  }

  .headerSub {
    width: 60%;
    height: 100%;
    display: flex;
    align-items: flex-end;
    flex-direction: column;
    gap: 1vh;

    button {
      border-radius: 100vw;
      padding: 0.5vh 1.5vw;
      //box-shadow: 0 0 10px 5px rgba(230, 230, 230, 0.5);
      transition: 0.35s;
      color: gray;

      &:hover {
        color: black;
        background-color: rgba(245, 245, 245, 1);
      }
    }
  }

</style>