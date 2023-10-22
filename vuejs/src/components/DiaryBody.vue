<script lang="ts" setup>
import {computed, ref} from "vue";

const props = defineProps(['value']);
const emits = defineEmits(['update:value']);
const textareaRef = ref(null);

const value = computed({
  get() {
    return props.value
  },
  set(value) {
     emits('update:value', value);
  }
})

</script>

<template>
  <textarea class="context" v-model="value" ref="textareaRef" ></textarea>
</template>

<style lang="scss" scoped>
  .context {
    padding: 1rem;
    resize: none;
    height: 100%;
    width: 100%;
    white-space: pre-line;
    line-height: 1.5rem;
    word-break: break-all;
    overflow: auto;
    border: none;
    outline: none;
    transition: 0.35s;
    color: gray;

    &:focus {
      transition: 0.35s;
      background-color: rgba(245, 245, 245, 1);
      color: black;
    }
  }
</style>