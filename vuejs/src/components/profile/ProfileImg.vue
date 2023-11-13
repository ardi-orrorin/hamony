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
  @import "@/assets/scss/components/profile/img";
</style>