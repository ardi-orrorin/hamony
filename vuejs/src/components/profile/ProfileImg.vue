<script setup lang="ts">
import {ref} from "vue";

const props = defineProps(['src'])
const fileRef = ref<HTMLInputElement | null>(null)
const imagePreview = ref<string>('')
function addImgClickHandler() {
  if(fileRef.value !== null){
    fileRef.value.click()
  }
}
function addImgHandler(el: any) {
  const file = el.target.files[0]
  imagePreview.value = URL.createObjectURL(file)
}

</script>

<template>
  <div class="profileImg" @click="addImgClickHandler">
    <img class="img" :src="imagePreview" alt="profile" onerror="this.src='/imgerror.png'" />
    <input ref="fileRef" type="file" accept="image/*" @change="addImgHandler" hidden/>
  </div>
</template>

<style scoped lang="scss">
    .profileImg {
      height: 70px;
      width: 70px;
      position: relative;
    }
    .img {
      width: 100%;
      height: 100%;
      border-radius: 100vw;
      object-fit: cover;
      border: 3px solid rgba(230, 230, 230, 1);
    }
</style>