<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<script lang="ts" setup>

import IconBtn from "@/components/IconBtn.vue";

interface User {
  id: string,
  pwd: string
}

import Input from "@/components/Input.vue";
import {reactive} from "vue";

const user = reactive<User>({
  id: '',
  pwd: ''
})

function userInit() {
  user.pwd = ''
  user.id = ''
}

</script>


<template>

  <div class="container">
    <div>
      <Input v-model:value="user.id" position="up" placeholder="아이디를 입력하세요" />
    </div>
    <Transition>
      <div v-if="user.id.length > 3">
          <Input  v-model:value="user.pwd" position="bottom" placeholder="비밀번호를 입력하세요"/>
      </div>
    </Transition>

    <div >
      <TransitionGroup>
        <IconBtn v-if="user.pwd.length > 0 || user.id.length > 0" text="ink_eraser" @click="userInit"/>
        <IconBtn v-if="user.pwd.length > 5" text="arrow_forward_ios" />
      </TransitionGroup>
    </div>

  </div>

</template>

<style lang="scss" scoped>
  .container {
    //width: 100vw;
    height: 98vh;
    display: flex;
    flex-direction: column;
    justify-content: center;

    div {
      text-align: center;
    }

  }

  .v-enter-active,
  .v-leave-active {
    transition: opacity 0.35s ease;
  }

  .v-enter-from,
  .v-leave-to {
    opacity: 0;
  }





</style>