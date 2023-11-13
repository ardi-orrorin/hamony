<script lang="ts" setup>
import {computed, ref} from "vue";

const props = defineProps(['value', 'disabled']);
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
  <textarea class="context" v-model="value" ref="textareaRef" :disabled="props.disabled"></textarea>
</template>

<style lang="scss" scoped>
  @import "@/assets/scss/components/diary/body";

</style>