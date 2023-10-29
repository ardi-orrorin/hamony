<script setup lang="ts">

import {useDiary} from "@/store/diary";
import DiarySubject from "@/components/DiarySubject.vue";
import DiaryBody from "@/components/DiaryBody.vue";
import {onUnmounted} from "vue";

const data = useDiary()

onUnmounted(()=>{
  data.$reset()
})


</script>

<template>
  <div class="container">
    <div class="subContainer">
      <div class="info">
        <DiarySubject
            v-model:value="data.subject"
            :style="{'background-color':'white', 'text-align':'center'}"
            disabled="true"
        />
      </div>
      <div class="contentBody">
        <DiaryBody
            v-model:value="data.content"
            disabled="true"
            style="background-color: white"
        />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.container {
  display: flex;
  justify-content: center;
  align-items: center;
}
.subContainer {
  width: 40vw;
  border-radius: 15px;
  box-shadow: 0 0 10px 5px rgba(230, 230, 230, 0.5);
}

.info {
  padding: 10px;
  display: flex;
  height: 10%;
  align-items: center;
  justify-content: center;
  position: relative;
}

.contentBody {
  padding: 10px;
  min-height: 300px;
  height: 50vh;
  position: relative;
}

</style>