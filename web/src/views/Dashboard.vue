<template>
  <div class="flex w-full h-full">
    <Sidebar></Sidebar>
    <div class="flex flex-1 h-full justify-center items-center">
      <div>{{ user }}</div>
    </div>
  </div>
</template>

<style scoped>

</style>

<script setup>
import Sidebar from "@/components/Sidebar.vue";
import {useUserStore} from "@/store/userStore";

const user = useUserStore()
console.log(user)
</script>