<script lang="ts" setup>
import {computed, ref} from "vue";

  const props = defineProps(['value', 'position']);
  const emit = defineEmits(['update:value']);

  const value = computed({
    get() {
      return props.value
    },
    set(value) {
      emit('update:value', value)
    }
  });

  const inputRef = ref(null)
  defineExpose({
    inputRef
  });

</script>

<template>
  <input
      ref="inputRef"
      v-model="value"
      :class="props.position === 'up'
              ? value.length > 3
              ? 'up' : 'all'
              : 'bottom'"
  />
</template>

<style lang="scss" scoped>
  @import "@/assets/scss/components/login/input";

</style>
