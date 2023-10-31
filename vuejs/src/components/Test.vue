<script setup lang="ts">

import {ref} from "vue";

const imgFile = ref('');

function print(el: any) {
  const reader = new FileReader()
  reader.readAsDataURL(el.target.files[0])

  reader.onloadend = (ev) => {
    imgFile.value = ev.target!!.result!!.toString()!!
  }

}

</script>

<template>
  <div>
    <input type="file" accept="image/*" @change="print">
    <button @click="print">Test</button>
    <br/>
    <img :src="imgFile" width="500" />
  </div>
</template>

<style scoped lang="scss">

</style>