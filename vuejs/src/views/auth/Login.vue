<script setup lang="ts">

import IconBtn from "@/components/IconBtn.vue";
import {reactive, ref, watchEffect} from "vue";
import LoginInput from "@/components/LoginInput.vue";
import koJson from '@/assets/lang/ko-kr.json';
import router from "@/router";
import {login} from "@/api/memberApi";

const text = koJson;


interface User {
  id: string,
  pwd: string
}

const user = reactive<User>({
  id: '',
  pwd: ''
});

const pwd = ref<any>(null);
const id = ref<any>(null);
const errorMsg = ref('');


watchEffect(()=>{
  if(id.value) {
    id.value['inputRef'].focus()
  }
})

function userInit() {
  user.pwd = ''
  user.id = ''
}

function pwdFocusHandler() {
  if (user.id.length > 3){
    if (pwd.value){
      pwd.value['inputRef'].focus()
    }
  }else {
    errorMsg.value = text.min4Id
  }
}

async function submitHandler() {
  if(user.pwd.length > 7) {

    const result = await login({userId: user.id, userPwd: user.pwd})
        .then(res => res)

    if(result.status !== 200) {
      errorMsg.value = result.data
      if(id.value) id.value['inputRef'].focus();
    } else if(result.status === 200) {
      router.push('/')
    } else {
      errorMsg.value = ''
    }
  } else{
    errorMsg.value = text.min8Pwd
  }
}

function moveIdInputHandler() {
  if(user.pwd.length === 0){
    if(id.value) id.value['inputRef'].focus()
  }else {
    console.log('')
  }
}

</script>


<template>
  <div class="container">
    <div>
      <h1>{{text.loginTitle}}</h1>
    </div>

    <div>
      <LoginInput
          ref="id"
          v-model:value="user.id"
          position="up"
          :placeholder="text.enterUserId"
          @keyup.enter="pwdFocusHandler"
      />
    </div>
    <Transition>
      <div v-if="user.id.length > 3">
          <LoginInput
              ref="pwd"
              v-model:value="user.pwd"
              position="bottom"
              :placeholder="text.enterPwd1"
              @keyup.enter="submitHandler"
              @keyup.delete="moveIdInputHandler"
              @keydown.delete="moveIdInputHandler"
              type="password"
          />
      </div>
    </Transition>

    <transition>
      <div v-if="errorMsg.length > 0" class="error">
        <span>{{ errorMsg }}</span>
      </div>
    </transition>

    <div class="menu">
      <TransitionGroup>
        <IconBtn text="person_add" @click="router.push('/signin')"/>
        <IconBtn text="person_search" @click="router.push('/searchid')"/>
        <IconBtn
            v-if="user.pwd.length > 0 || user.id.length > 0"
            text="ink_eraser"
            @click="userInit"
        />
        <IconBtn
            v-if="user.pwd.length > 8"
            text="login"
            @click="submitHandler"
        />
      </TransitionGroup>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  @import "@/assets/scss/pages/auth/login";

</style>