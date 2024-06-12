<template>
  <div class="flex w-full h-full">
    <Sidebar></Sidebar>
    <div class="flex flex-1 h-full justify-center items-center">
      <div>Current Count: {{ counter.count }}</div>
    </div>
  </div>
</template>

<style scoped>

</style>

<script setup>
import Sidebar from "../components/Sidebar.vue";
import { useCounterStore } from '../store.js'

const counter = useCounterStore()
counter.count++
</script>