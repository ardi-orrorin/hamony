<script setup lang="ts">

import IconBtn from "@/components/IconBtn.vue";
import {reactive, ref} from "vue";
import LoginInput from "@/components/LoginInput.vue";
import koJson from '@/assets/lang/ko-kr.json';

const text = koJson;


interface User {
  id: string,
  pwd: string
}

const user = reactive<User>({
  id: '',
  pwd: ''
});

const pwd = ref(null);
const id = ref(null);
const errorMsg = ref('');

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

function submitHandler() {
  if(user.pwd.length > 7) {
    errorMsg.value = ''
  } else{
    errorMsg.value = text.min8Pwd
  }
}

function moveIdInputHandler() {
  if(user.pwd.length === 0){
    if(id.value){
      id.value['inputRef'].focus()
    }
  }else {
    console.log('')
  }
}

</script>


<template>
  <div class="container">
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
          />
      </div>
    </Transition>

    <div>
      <TransitionGroup>
        <IconBtn text="person_add"/>
        <IconBtn text="person_search"/>
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

    <transition>
      <div v-if="errorMsg.length > 0" class="error">
          <span>{{ errorMsg }}</span>
      </div>
    </transition>
  </div>
</template>

<style lang="scss" scoped>
  .container {
    height: 98vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-collapse: separate;

    div {
      text-align: center;
    }
  }

  .error {
    padding: 0.5rem;
    font-size: 0.7rem;
    color: red;
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