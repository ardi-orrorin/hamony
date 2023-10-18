<script lang="ts" setup>
  import {computed} from "vue";

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

</script>

<template>
    <input v-model="value" />
</template>

<style lang="scss" scoped>
input {
  width: 100%;
  height: 2rem;
  padding: 0 0.7rem;
  color: gray;
  outline-style: none;
  border: gray solid 1px;
  text-align: center;
  align-self: center;
  border-radius: 0.7vw;
}

</style>