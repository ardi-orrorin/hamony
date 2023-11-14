<script lang="ts" setup>
import Input from "@/components/Input.vue";
import {ref, watchEffect} from "vue";
import koJson from "@/assets/lang/ko-kr.json";
import IconBtn from "@/components/IconBtn.vue";
import type {Lang} from "@/assets/lang/langType";
import {useSignIn} from "@/store/member";
import router from "@/router";
import {idDuplicateChk, signIn} from "@/api/memberApi";

const text: Lang = koJson;
const user = useSignIn();
const idCheck = ref<number>(0);
const idRef = ref<any>(null);
const pwd1Ref = ref<any>(null);
const pwd2Ref = ref<any>(null);
const nickNameRef = ref<any>(null);
const emailAutoList = ref<any>(false);
const emailRef = ref<any>(null);
const emailListREf = ref<any>([])

const mailList = [
  '@gmail.com', '@daum.com', '@naver.com',
  '@yahoo.com', '@live.com', '@kakao.com'
]

watchEffect(()=>{
  if(idRef.value) idRef.value['inputRef'].focus()
})
function validateId(){
    console.log(user.userId)
    if(user.userId.length > 0){
      if(user.userId.length < 5) {
        idCheck.value = 1
        return ;
      }
        idDuplicateChk(user.userId)
            .then(res => idCheck.value = parseInt(res.data))

      //   중복 여부에 따라 2, 3
    }
  }

  function validateEmail(): boolean | undefined {
    if(user.email.length > 0) {
      if(user.email.includes('@') && user.email.includes('.')){
        return true
      } else {
        return false
      }
    }
  }

  function pwdMinLengthValidate(): boolean | undefined{
    if(user.userPwd.length > 0) {
      if(user.userPwd.length > 7) {
        return true
      } else {
        return false
      }
    }
  }
  function validatePwd(): boolean | undefined {
    if(user.userPwd.length > 0 && user.userPwd2.length > 0){
      if((user.userPwd.length === user.userPwd2.length)
          && (user.userPwd === user.userPwd2)) {
        return true
      } else {
        return false
      }
    }
}

function emailAutoCompleteHandler(toggle: boolean): void {
    if(user.email.length > 0){
      emailAutoList.value = toggle
    } else {
      emailAutoList.value = false
    }
}

function emailClickHandler(it: string) {
  user.email = user.email.split('@')[0]+it;
  emailAutoCompleteHandler(false);
}

function formInitHandler() {
  user.$reset()
  router.back()
}

function onSubmitHandler(){
  signIn(user).then(res => {
    if(res.status === 201) {
      console.log('1')
      router.push("/")
      user.$reset()
    } else {
      alert(text.signSubmitError)
    }
  })
}

</script>

<template>
  <div class="container">
    <div />
    <div class="subContainer">
      <h1>{{ text.signinTitle }}</h1>
      <div>
        <Input
            ref="idRef"
            :placeholder="text.enterUserId"
            v-model:value="user.userId"
            @keyup.enter="pwd1Ref['inputRef'].focus()"
            @keyup="validateId"
        />
        <transition>
          <span
              v-if="user.userId.length > 0"
              :style="{'color' : idCheck === 3
                                 ? 'blue' : 'red'}"
          >
            {{
              idCheck  === 1 ? text.min4Id
              : idCheck === 2 ? text.alreadyId
              : idCheck === 3 ? text.availableId
              : ''
            }}
          </span>
        </transition>
      </div>
      <div>
        <Input
            ref="pwd1Ref"
            :placeholder="text.enterPwd1"
            type="password"
            v-model:value="user.userPwd"
            @keyup.enter="pwd2Ref['inputRef'].focus()"
        />
        <Transition>
          <span
              v-if="user.userPwd.length > 0 "
              :style="{'color' : pwdMinLengthValidate()
                                 ? 'blue' : 'red'}"
          >
            {{
              pwdMinLengthValidate()
                  ? text.valiablePwd
                  : !pwdMinLengthValidate()
                  && pwdMinLengthValidate() !== undefined
                      ? text.min8Pwd : ''
            }}
          </span>
        </Transition>
      </div>
      <div>
        <Input
            ref="pwd2Ref"
            :placeholder="text.enterPwd2"
            type="password"
            v-model:value="user.userPwd2"
            @keyup.enter="emailRef['inputRef'].focus()"
        />
        <transition>
          <span
              v-if="user.userPwd2.length > 0"
              :style="{'color' : validatePwd()
                                 ? 'blue' : 'red'
              }"
          >
            {{
              validatePwd() ? text.matchPwd
              : !validatePwd() && validatePwd() !== undefined
              ? text.notMatchPwd : ''
            }}
          </span>
        </transition>
      </div>

      <div class="email">
        <Input
            ref="emailRef"
            :placeholder="text.enterEmail"
            v-model:value="user.email"
            v-on:focus="() => emailAutoCompleteHandler(true)"
            @keyup="emailAutoCompleteHandler"
            @keydown.down="emailListREf[0].focus()"
        />
        <Transition>
          <div v-if="emailAutoList" class="list">
            <template v-for="(it, index) in mailList">
              <button
                  class="listbtn"
                  @click="() => emailClickHandler(it)"
                  ref="emailListREf"
                  @keydown.down="emailListREf[index+1].focus()"
                  @keydown.up="emailListREf[index-1].focus()"
              >
                <span>{{user.email.split('@')[0]}}</span>{{it}}
              </button>
            </template>
          </div>
        </Transition>
        <Transition>
          <span
              v-if="user.email.length > 0"
              :style="{'color' : validateEmail()
                                 ? 'blue' : 'red'
              }"
          >
            {{
              validateEmail() ? text.matchEmail
              : !validateEmail() && validateEmail() !== undefined
              ? text.notMatchEmail : ''
            }}
          </span>
        </Transition>
      </div>

      <div>
        <IconBtn text="undo" @click="formInitHandler" />
        <transition>
          <IconBtn
              v-if="user.userId.length > 4 && idCheck === 3"
              text="login"
              @click="onSubmitHandler"
          />
        </transition>
      </div>
    </div>
    <div />
  </div>
</template>

<style lang="scss" scoped>
  @import "@/assets/scss/pages/auth/signIn";
</style>