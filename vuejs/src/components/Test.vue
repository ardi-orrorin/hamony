<script setup lang="ts">

import {ref} from "vue";
import {imgUpload} from "@/api/diaryApi";

const previewIMG = ref<string[]>([]);
const multipart: FormData = new FormData()


function print(el: any) {

  previewIMG.value = []
  multipart.delete('file')

  for (const file of el.target.files) {
    multipart.append('file', file)

    const reader = new FileReader()
    reader.readAsDataURL(file)

    reader.onloadend = (ev) => {
      previewIMG.value.push(ev.target!!.result!!.toString()!!)
    }
  }
}

function submit() {
  imgUpload(multipart!!)
}

</script>

<template>
  <div>
    <input type="file" accept="image/*" @change="print" multiple>
    <button @click="submit">Test</button>
    <br/>
    <div class="container">
      <template v-for="img in previewIMG">
          <div class="thumbnailContainer">
            <img class="thumbnail" :src="img" />
          </div>
      </template>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .container {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 10px;
  }

  .thumbnail {
    height: 350px;
    width: 350px;
    object-fit: cover;
  }

</style>