<script lang="ts" setup>
  import Input from "@/components/Input.vue";
  import {reactive, ref} from "vue";
  import koJson from "@/assets/lang/ko-kr.json";
  import IconBtn from "@/components/IconBtn.vue";
  import type {Lang} from "@/assets/lang/langType";
  const text: Lang = koJson;

  interface User {
    id: string;
    pwd1: string;
    pwd2: string;
    nickName: string;
    email: string;
  }
  const user = reactive<User>({
    id: '',
    pwd1: '',
    pwd2: '',
    nickName: '',
    email: '',
  })
  const emailAutoList = ref(false);
  const emailRef = ref(null);
  const emailListREf = ref([])

  const mailList = [
      '@gmail.com', '@daum.com', '@naver.com',
      '@yahoo.com', '@live.com', '@kakao.com'
  ]
  function validateId(): number | undefined {
    if(user.id.length > 0){
      if(user.id.length < 5) return 1
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
    if(user.pwd1.length > 0) {
      if(user.pwd1.length > 7) {
        return true
      } else {
        return false
      }
    }
  }
  function validatePwd(): boolean | undefined {
    if(user.pwd1.length > 0 && user.pwd2.length > 0){
      if((user.pwd1.length === user.pwd2.length)
          && (user.pwd1 === user.pwd2)) {
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
  user.email = user.email.split('@')[0]+it
  emailAutoCompleteHandler(false)

}

</script>

<template>
  <div class="container">
    <div />
    <div class="subContainer">
      <h1>{{ text.signinTitle }}</h1>
      <div>
        <Input
            :placeholder="text.enterUserId"
            v-model:value="user.id"
        />
        <transition>
          <span
              v-if="user.id.length > 0"
              :style="{'color' : validateId() === 3
                                 ? 'blue' : 'red'}"
          >
            {{
              validateId() === 1 ? text.min4Id
              : validateId() === 2 ? text.alreadyId
              : validateId() === 3 ? text.availableId
              : ''
            }}
          </span>
        </transition>
      </div>
      <div>
        <Input
            :placeholder="text.enterPwd1"
            type="password"
            v-model:value="user.pwd1"
        />
        <Transition>
          <span
              v-if="user.pwd1.length > 0 "
              :style="{'color' : pwdMinLengthValidate()
                                 ? 'blue' : 'red'}"
          >
            {{
              pwdMinLengthValidate() ? text.valiablePwd
              : !pwdMinLengthValidate()
              && pwdMinLengthValidate() !== undefined ? text.min8Pwd : ''
            }}
          </span>
        </Transition>
      </div>
      <div>
        <Input
            :placeholder="text.enterPwd2"
            type="password"
            v-model:value="user.pwd2"
        />
        <transition>
          <span
              v-if="user.pwd2.length > 0"
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
            :placeholder="text.enterEmail"
            v-model:value="user.email"
            ref="emailRef"
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
        <Input
            :placeholder="text.enterNickname"
            v-model:value="user.nickName"
            required
        />
        <span>{{}}</span>
      </div>

      <div>
        <IconBtn text="undo"/>
        <transition>
          <IconBtn v-if="user.id.length > 4" text="login" />
        </transition>
      </div>
    </div>
    <div />
  </div>
</template>

<style lang="scss" scoped>
  .container {
    display: flex;
    height: 100vh;
    text-align: center;
    justify-content: center;
  }

  .subContainer {
    margin-top: 5vh;
    width: 50%;
    justify-content: center;
    row-gap: 1rem;

    div {
      padding: 1.8vh;

      span {
        font-size: 0.8rem;
        color: red;
      }
    }
  }

  h1 {
    padding: 5vh;
  }


  .email{
    position: relative;
  }

  .list {
    position: absolute;
    z-index: 100;
    width: 100%;
    left: 0;
    top: 2rem;
    button {
      width: 100%;
      border: gray solid 1px;
      background-color: white;
      height: 4vh;
      color: gray;
      span {
        font-weight: 600;
        color: gray;
      }

      &:focus {
        background-color: rgba(180, 180, 180, 1);
        color: white;
        outline-style: none;
        span{
          color: white;
        }
      }
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