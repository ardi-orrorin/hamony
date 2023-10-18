<script lang="ts" setup>
  import {computed} from "vue";

  const props = defineProps(['value', 'position'])
  const emit = defineEmits(['update:value'])

  const value = computed({
    get() {
      return props.value
    },
    set(value) {
      emit('update:value', value)
    }
  })
</script>

<template>
  <input v-model="value" :class="props.position === 'up' ? value.length > 3 ? 'up' : 'all' : 'bottom'" />
</template>

<style lang="scss" scoped>
  input {
    width: 35%;
    height: 2rem;
    padding: 0 0.7rem;
    color: gray;
    outline-style: none;
    border: gray solid 1px;
    text-align: center;
    align-self: center;
  }
  .up {
    border-radius: 0.7vw 0.7vw 0 0;
  }
  .bottom {
    border-radius: 0 0 0.7vw 0.7vw;
  }
  .all {
    border-radius: 0.7vw;
  }
</style>
