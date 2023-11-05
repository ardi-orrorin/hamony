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
  const reader = new FileReader()
  reader.readAsDataURL(file)
  reader.onloadend = (ev) => {
    imagePreview.value = ev.target!!.result!!.toString()!!
  }
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
    .sticky {
      border-radius: 100vw;
      height: 10px;
      width: 10px;
      position: absolute;
      bottom: 15px;
      right: 10px;
      color: gray;
      font-size: 1.3rem;
      font-weight: 500;
    }
</style>