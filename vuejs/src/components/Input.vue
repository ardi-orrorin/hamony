<script lang="ts" setup>
import {computed, ref} from "vue";

const props = defineProps(['value'])
  const emits = defineEmits(['update:value'])

  const value = computed({
    get() {
      return props.value
    },
    set(value){
      emits('update:value', value)
    }
  })
  const inputRef = ref(null)
  defineExpose({
    inputRef
  });


</script>

<template>
    <input class="input" v-model="value" ref="inputRef" />
</template>

<style lang="scss" scoped>
  @import "@/assets/scss/components/input";

</style>