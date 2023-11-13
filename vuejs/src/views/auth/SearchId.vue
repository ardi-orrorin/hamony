<script lang="ts" setup>
import koJson from '@/assets/lang/ko-kr.json';
import Input from "@/components/Input.vue";
import IconBtn from "@/components/IconBtn.vue";
import {reactive, ref} from "vue";
import router from "@/router";

interface SearchId {
  email: string
  code: string
  codeVerify: boolean
}

const text = koJson;
const codeRef = ref(null);

const searchId = reactive<SearchId>({
  email: '',
  code: '',
  codeVerify: false
});


function verityCodeSubmitHandler(){
  searchId.codeVerify = true

}

function verifyCodeHandler() {

}

function initSearchIdHandler() {
  searchId.code = ''
  searchId.email = ''
  searchId.codeVerify = false
}

</script>

<template>
  <div class="container">
      <h1>{{ text.searchId }}</h1>
      <div>
        <Input
            :placeholder="text.enterEmail"
            v-model:value="searchId.email"
        />
      </div>
      <div class="verityCode">
        <Transition>
          <Input
              ref="codeRef"
              v-if="searchId.codeVerify"
              :placeholder="text.verify6Code"
              v-model:value="searchId.code"
              maxlength="6"
          />
        </Transition>
      </div>
      <div class="menu">
        <IconBtn
            text="undo"
            @click="router.back()"
        />
        <IconBtn
            text="close"
            @click="initSearchIdHandler"
        />
        <Transition>
          <IconBtn
              v-if="searchId.email.includes('@') && searchId.email.includes('.')"
              text="search"
              @click="verityCodeSubmitHandler"
          />
        </Transition>
        <Transition>
          <IconBtn
              v-if="searchId.codeVerify && searchId.code.length === 6"
              text="done"
              @click="verifyCodeHandler"
          />
        </Transition>
      </div>
  </div>
</template>

<style lang="scss" scoped>
  @import "@/assets/scss/pages/auth/searchId";
</style>