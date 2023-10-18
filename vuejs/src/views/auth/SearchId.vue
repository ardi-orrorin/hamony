<script lang="ts" setup>
import koJson from '@/assets/lang/ko-kr.json';
import Input from "@/components/Input.vue";
import IconBtn from "@/components/IconBtn.vue";
import {ref} from "vue";
const text = koJson;

const email = ref('');
</script>

<template>
  <div class="container">
      <h1>{{ text.searchId }}</h1>
      <div>
        <Input
            :placeholder="text.enterEmail"
            v-model:value="email"
        />
      </div>
      <div class="menu">
        <Transition>
          <IconBtn v-if="email.includes('@') && email.includes('.')" text="search" />
        </Transition>
      </div>
  </div>
</template>

<style lang="scss" scoped>
  .container {
    display: flex;
    height: 100vh;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    div {
      width: 40%;
      text-align: center;
    }
    h1{
      margin-bottom: 5vh;
    }
  }

  .menu {
    margin-top: 1vh;
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