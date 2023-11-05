<script setup lang="ts">

import {ref} from "vue";
import {imgUpload} from "@/api/diaryApi";

const previewIMG = ref<string[]>([]);
const multipart: FormData = new FormData()
const data = ref<string>('')


function inputdata(el: any) {
  data.value = el.target.value
}

function print(el: any) {

  previewIMG.value = []
  multipart.delete('file')

  for (const file of el.target.files) {
    multipart.append('file', file);
    previewIMG.value.push(URL.createObjectURL(file));
  }
}

function submit() {
  const input = new Blob([JSON.stringify({data: data.value})], {type: "application/json"})
  multipart.append('input', input)
  imgUpload(multipart!!)
}

</script>

<template>
  <div>
    <div class="inputContainer">
    <input type="text" @change="inputdata" />
    <input type="file" accept="image/*" @change="print" multiple>
    <button @click="submit" class="summitBtn">Test</button>
    </div>
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
  .inputContainer {
    display: flex;
    justify-content: center;
  }

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

  .summitBtn {
    margin-top: 10px;
    border: 1px solid gray;
    padding: 5px 20px;
    border-radius: 5px;
    transition: 0.35s;
    &:hover {
      background-color: gray;
      color: white;
    }
  }

</style>